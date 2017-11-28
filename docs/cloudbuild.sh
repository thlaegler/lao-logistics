#!/bin/bash


stepPreprocess() { 
	echo "stepPreprocess($1, $2, $3)"
	
	setProperty "googleProjectId" $1
	setProperty "buildTarget" $2
	setProperty "branchName" $3
	setProperty "commitSha" $4
	
	cat docs/cloudbuild.properties
}

stepMaven() { 
	echo "stepMaven()"

	#setProperty artifactVersion $(mvn org.apache.maven.plugins:maven-help-plugin:2.1.1:evaluate -Dexpression=project.version | grep -v '\[')

	cd parents
	mvn clean install -q -fae
	cd ..
	
	mvn clean install -q -fae
}

stepDocker() { 
	echo "stepDocker()"
	
	local containerRegistry="$(getProperty containerRegistry)"
	local imageVersion="$(getProperty imageVersion)"
	
	cd daemons
	containerizables=( $(find . -maxdepth 1 -type d -printf '%P\n') )
	for artifact in "${containerizables[@]}"; do
    	privateDocker $artifact $containerRegistry $imageVersion
    done
	
	cd ../services	
	containerizables=( $(find . -maxdepth 1 -type d -printf '%P\n') )
	for artifact in "${containerizables[@]}"; do
    	privateDocker $artifact $containerRegistry $imageVersion
    done
    cd ..
}

stepHelm() { 
	echo "stepHelm()"

	local chartName="$(getProperty chartName)"
	local releaseName="$(getProperty releaseName)"
	local namespace="$(getProperty namespace)"
	local googleProjectId="$(getProperty googleProjectId)"
	local clusterName="$(getProperty clusterName)"
	local timeZone="$(getProperty timeZone)"
	
	gcloud config set project $PROJECT_ID
	gcloud container clusters get-credentials "$clusterName" --zone "$timeZone" --project "$googleProjectId"
	
	cd docs/helm
	helm init --debug
	helm version --debug
	helm lint --debug "./$chartName"
	helm package --debug "./$chartName"

	# check if previous deployment is existing.
	helm get "$releaseName" 2>&1
	rc=$?
	if [[ $rc != 0 ]]; then
	    helm install --name="$releaseName" --namespace="$namespace" --debug "./$chartName"
	else
	    helm upgrade "$releaseName" --force --recreate-pods --debug "./$chartName"
	fi
	helm ls
	cd ../..
}

stepPostprocess() {
	echo "stepPostprocess()"
	
}

stepTest() {
	echo "stepTest()"

	mvn clean install -q -fae -Premote-test
}

privateDocker() {
	local artifact=$1
	local containerRegistry=$2
	local imageVersion=$3
	
	if [ -f $artifact/Dockerfile ]; then
   		cd $artifact
    	echo "Building image: $artifact"
    	docker build -q -t "$containerRegistry/$artifact:$imageVersion" .
    	docker push "$containerRegistry/$artifact:$imageVersion"
    	cd ..
    fi
}

getProperty() {
	echo "getProperty($1)"

	local key=$1
	local properties="docs/cloudbuild.properties"
	
	return $(grep "$key" "$properties" | cut -d'=' -f2)
}

setProperty() { 
	echo "setProperty($1, $2)"

	local key=$1
	local value=$2
	local properties="docs/cloudbuild.properties"
	
	echo "$key=$value" >> "$properties"
}
