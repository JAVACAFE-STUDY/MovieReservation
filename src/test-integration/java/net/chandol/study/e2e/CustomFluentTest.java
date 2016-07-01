package net.chandol.study.e2e;

import org.fluentlenium.core.ConditioalStartWebDriverFluentTest;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Arrays;


@RunWith(SpringRunner.class)
@ActiveProfiles("integration-test")
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
public class CustomFluentTest extends ConditioalStartWebDriverFluentTest {

    @Override
    public boolean checkFluentTestRunnable() {
        return hasProfile("integration-test");
    }

    private boolean hasProfile(String profile) {
        TestContextManager testContextManager = new TestContextManager(getClass());
        TestContext testContext = testContextManager.getTestContext();
        Environment environment = testContext.getApplicationContext().getEnvironment();
        String[] activeProfiles = environment.getActiveProfiles();

        return Arrays.asList(activeProfiles).contains(profile);
    }

    @Override
    public WebDriver getDefaultDriver() {
        String classpath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        String chromeDriverPath = classpath + "bin" + File.separator + "chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        return new ChromeDriver();
    }
}
