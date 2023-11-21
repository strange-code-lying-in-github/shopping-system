
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the TestJunit class.
 */
public class TestJunit {

    @BeforeAll
    static void setUp() {
    }

    @BeforeEach
    void reloadData() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testNotEqual() {
        assertNotEquals("1", "2");
    }
}