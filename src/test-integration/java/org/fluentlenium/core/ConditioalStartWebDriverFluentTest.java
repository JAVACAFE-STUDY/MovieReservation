package org.fluentlenium.core;

import org.fluentlenium.adapter.util.SharedDriver;
import org.fluentlenium.adapter.util.SharedDriver.SharedType;
import org.fluentlenium.adapter.util.ShutdownHook;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;

import static org.fluentlenium.adapter.util.SharedDriverHelper.*;

/**
 *
 */
@SharedDriver(type = SharedType.ONCE)
public class ConditioalStartWebDriverFluentTest extends FluentAdapter {
    private static WebDriver sharedDriver;
    private static boolean isSharedDriverPerClass;

    @Rule
    public TestName name = new TestName();
    @Rule
    public TestRule watchman = new TestWatcher() {
        @Override
        public void starting(Description description) {
            // 테스팅 환경에 따라서 통합 테스트가 불가능한 경우도 있다.
            // 이 경우에는 테스트환경을 초기화 하지 않도록 설정이 필요하다.
            if(!checkFluentTestRunnable())
                return;

            super.starting(description);
            if (isSharedDriverOnce(description.getTestClass())) {
                synchronized (this) {
                    if (sharedDriver == null) {
                        initFluentFromDefaultDriver();
                        sharedDriver = getDriver();
                        killTheBrowserOnShutdown();
                    } else {
                        initFluentWithExistingDriver();
                    }
                }
            } else if (isSharedDriverPerClass(description.getTestClass())) {
                synchronized (this) {
                    if (!isSharedDriverPerClass) {
                        initFluentFromDefaultDriver();
                        sharedDriver = getDriver();
                        isSharedDriverPerClass = true;
                    } else {
                        initFluentWithExistingDriver();
                    }
                }
            } else {
                initFluentFromDefaultDriver();
            }

            initTest();
            setDefaultConfig();
        }


        @Override
        public void finished(Description description) {
            super.finished(description);
            if (isSharedDriverPerMethod(description.getTestClass()) ||
                    isDefaultSharedDriver(description.getTestClass())) {
                quit();
            } else if (isDeleteCookies(description.getTestClass())) {
                if (sharedDriver != null) {
                    sharedDriver.manage().deleteAllCookies();
                }
            }
        }

        @Override
        public void failed(Throwable e, Description description) {
            if (screenshotMode == TriggerMode.ON_FAIL) {
                takeScreenShot(description.getTestClass().getSimpleName() + "_" +
                        description.getMethodName() + ".png");
            }
            if (htmlDumpMode == TriggerMode.ON_FAIL) {
                takeHtmlDump(description.getTestClass().getSimpleName() + "_"
                        + description.getMethodName() + ".html");
            }
        }

    };

    private void killTheBrowserOnShutdown() {
        Runtime.getRuntime().addShutdownHook(new ShutdownHook("fluentlenium", this));
    }

    private void initFluentWithExistingDriver() {
        initFluent(sharedDriver).withDefaultUrl(getDefaultBaseUrl());
    }

    private void initFluentFromDefaultDriver() {
        initFluent(getDefaultDriver()).withDefaultUrl(getDefaultBaseUrl());
    }


    public ConditioalStartWebDriverFluentTest() {
        super();
    }


    @AfterClass
    public static void afterClass() {
        if (isSharedDriverPerClass) {
            sharedDriver.quit();
            sharedDriver = null;
            isSharedDriverPerClass = false;
        }
    }

    /**
     * Override this method to change the default time to wait for a page to be loaded
     */
    public void setDefaultConfig() {
    }

    public static void assertAt(FluentPage fluent) {
        fluent.isAt();
    }


    public boolean checkFluentTestRunnable(){
        return true;
    }


}
