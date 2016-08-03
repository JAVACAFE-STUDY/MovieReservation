package net.chandol.study.sample;

import net.chandol.study.CustomFluentTest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class SampleIntegrationTest extends CustomFluentTest {

    @Test
    public void hasPageTitle() throws InterruptedException {
        goTo(serverUrl + "/index");
        assertThat("Hello World!").isEqualTo(title());
    }
}