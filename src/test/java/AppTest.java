import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AppTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    void test_printHelloWorld() {
        System.setOut(new PrintStream(outContent));
        App.main(new String[]{});
        assertEquals("Hello World!", outContent.toString().trim());
        System.setOut(originalOut);
    }

    @Test
    void test_defaultConstructor() {
        App app = new App();
        assertNotNull(app);
    }
}