package io.github.lucasiferreira.facecomparison;

import io.github.lucasiferreira.facecomparison.config.FacecomparisonApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = FacecomparisonApplication.class)
@ActiveProfiles(value = "test")
class FacecomparisonApplicationTests {

    @Test
    void contextLoads() {
    }

}
