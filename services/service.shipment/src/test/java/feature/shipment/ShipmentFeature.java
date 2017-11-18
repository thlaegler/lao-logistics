package feature.shipment;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(strict = false, features = "src/test/resources/feature/shipment/shipment.feature", glue = "feature.shipment",
		plugin = {"pretty", "html:target/cucumber/report", "json:target/cucumber/report.json"})
public class ShipmentFeature {

}
