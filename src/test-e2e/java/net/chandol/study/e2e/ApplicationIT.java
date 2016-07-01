package net.chandol.study.e2e;

import org.assertj.core.api.Assertions;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationIT extends CustomFluentTest {

    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(true);
    }

    @Test
    public void hasPageTitle() {
        goTo("http://localhost:8080/index");
        Assertions.assertThat(title()).contains("Hello World!");
    }
}