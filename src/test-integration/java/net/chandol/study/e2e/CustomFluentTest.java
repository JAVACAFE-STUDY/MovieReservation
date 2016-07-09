package net.chandol.study.e2e;

import org.fluentlenium.core.ConditioalStartWebDriverFluentTest;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Arrays;


/**
 * integration 테스트 뼈대
 */
@ActiveProfiles("integration-test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class CustomFluentTest extends ConditioalStartWebDriverFluentTest {

    @Value("${local.server.port}")
    private int port;
    protected String serverUrl;

    //TODO 효율적인 방법이 있는지 조사할 것.
    @Before
    public void setServerUrl() {
        serverUrl = "http://localhost:" + port;
    }


    /**
     * skip-integration-test 프로파일이 존재하지 않을때만 동작하도록 설정한다.
     */
    @Override
    public boolean checkFluentTestRunnable() {
        return !hasProfile("skip-integration-test");
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
        // 윈도환경에서만 exe파일이 실행되도록 설정한다.
        String chromeDriverFileName = isWindows() ? "chromedriver" : "chromedriver";

        String classpath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        String chromeDriverPath = classpath + "bin" + File.separator + chromeDriverFileName;
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        return new ChromeDriver();
    }

    private boolean isWindows() {
        String osName = System.getProperty("os.name").toLowerCase();
        return osName.contains("windows");
    }

}
