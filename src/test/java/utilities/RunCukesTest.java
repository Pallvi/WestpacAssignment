package utilities; /**
 * Created by Pallvi on 8/1/2018.
 */

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		monochrome = false,
		plugin = {"pretty","json:target/cucumber.json"} ,
		features = "src/test/java/featureFiles/",
		glue = {"src"},
		tags = "~@ignore"
)
public class RunCukesTest {
}

