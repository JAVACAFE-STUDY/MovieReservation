package net.chandol.study;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationIT extends CustomFluentTest {

    @Test
    public void hasPageTitle() {
        goTo("http://localhost:8080/index");
        Assertions.assertThat(title()).contains("Hello World!");
    }
}