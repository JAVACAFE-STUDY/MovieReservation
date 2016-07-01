package net.chandol.study.e2e.sample;

import net.chandol.study.e2e.CustomFluentTest;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class SampleIntegrationTest extends CustomFluentTest {

    @Test
    public void hasPageTitle() throws InterruptedException {
        goTo(serverUrl + "/index");
        assertThat("Hello World!", is(title()));
    }
}