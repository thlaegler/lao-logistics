# LAO logistics

A microservice architecture for a fictional logistics provider.


## Quickstart


### Install tools

* Git
* Maven
* Docker
* VM (e.g. VirtualBox or KVM); Secure Boot must be disabled on Linux-systems.

Build the whole project with Maven `mvn clean install` in the projects root folder.


### Run with Docker compose

`cd deploy`

`docker-compose up`


### Run with local kubernetes (Minikube)

Precondition is that the following tools are installed:
* Docker
* GCloud (to get the base image)
* Kubernetes (kubectl)
* Minikube
* VirtualBox (or similar tools)
* Helm (optional)

Minikube should be installed and running and should use your local Docker registry:

`minikube start --vm-driver=kvm`

`kubectl proxy`

Your local Docker daemon should be linked with the minikube Docker daemon. This command applies only for the current terminal session. When you switch to another terminal session, you have to run this command again:

`eval $(minikube docker-env)`


`cd deploy/helm`

`helm init --debug`

`helm lint --debug ./lao`

`helm package --debug ./lao`

`helm install --name="my-first-release" --namespace="my-namespace" --debug ./lao`
`kubectl proxy`

Then open the [Kubernetes (minikube) Dashboard](http://127.0.0.1:8001/ui) in the browser


### Run with kubernetes in the cloud

Same steps like above. If you use Google Cloud run `gcloud init` before.