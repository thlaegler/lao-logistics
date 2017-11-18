package feature.facility;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(strict = false, features = "src/test/resources/feature/facility/facility.feature", glue = "feature.facility",
		plugin = {"pretty", "html:target/cucumber/report", "json:target/cucumber/report.json"})
public class FacilityFeature {

}
