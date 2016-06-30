package net.chandol.study;

import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.adapter.util.SharedDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

@SharedDriver(type = SharedDriver.SharedType.ONCE)
public class CustomFluentTest extends FluentTest {

    private static WebDriver chromeWebDriver;
    {
        String classpath  = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        String chromeDriverPath = classpath + "bin"+ File.separator + "chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        chromeWebDriver = new ChromeDriver();
    }

    @Override
    public WebDriver getDefaultDriver() {
        //현재는 윈도만 작동함. 자동으로 파일 다운로드 받도록 설정할 것!
        return chromeWebDriver;
    }

}
