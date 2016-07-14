package net.chandol.study.e2e.sample;

import net.chandol.study.e2e.CustomFluentTest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class SampleIntegrationTest extends CustomFluentTest {

    @Test
    public void hasPageTitle() throws InterruptedException {
        goTo(serverUrl + "/index");
        assertThat("Hello World!").isEqualTo(title());
    }
}