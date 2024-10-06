package com.management.user;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/features",  // Path to your feature files
        glue = "com.management.user.steps"      // Path to your step definitions
)
public class CucumberRunnerTest {
}
