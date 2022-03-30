import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

public class TestingExercise {

    @BeforeAll
    static void startup() {
        System.out.println("Let's do some testing!\n");
    }
    @BeforeEach
    void before(TestInfo info) {
        System.out.println("Starting: " + info.getDisplayName() + "...");
    }
    @AfterEach
    @Disabled
    void after(TestInfo info) {
        System.out.println(info.getDisplayName() + " finished.\n");
    }
    @AfterAll
    static void close() {
        System.out.println("Good job! All tests are completed.");
    }

    @Nested
    class StringTests {

        @Test
        @DisplayName("String length test")
        void strLen() {
            int strLen = "Axel Sundin".length();
            int expectedLen = 11;
            assertEquals(expectedLen, strLen);
        }

        @Test
        @DisplayName("To upper test")
        void toUpper() {
            String str = "jack sparrow";
            String result = str.toUpperCase();
            assertNotNull(str);
            assertEquals("JACK SPARROW", result);
        }

        @Test
        @DisplayName("String contains test")
        void strContains() {
            String str = "Indonesia";
            boolean result = str.contains("Java");
            assertFalse(result);
        }

        @ParameterizedTest
        @CsvSource(value= {"Axel,AxEL", "IT,it", "lTu,LtU"})
        @DisplayName("Equals ignore case test")
        void stringIgnoreCase(String str1, String str2) {
            assertTrue(str1.equalsIgnoreCase(str2));
        }

        @Test
        @DisplayName("Split string test")
        void split() {
            String str = "test,av,IT";
            String[] result = str.split(",");
            String[] expected = { "test", "av", "IT" };
            assertArrayEquals(expected, result);
        }

        @Test
        @DisplayName("Null exception test")
        void nullException() {
            String nStr = null;
            assertThrows(NullPointerException.class, () -> nStr.length());
        }
    }


    @Nested
    class TestsWithMathAndNumbers {

        @Test
        @DisplayName("Min value test")
        void minValue() {
            int mini = Math.min(1000, 2000);
            assertEquals(1000, mini);
        }

        @Test
        @RepeatedTest(30)
        @DisplayName("Dice roll test")
        void dice() {
            int rnd = (int)(Math.random() * (6) + 1);
            assertTrue(1 <= rnd && rnd <= 6);
        }

        @ParameterizedTest(name = "{0} is prime.")
        @ValueSource(ints = {2, 3, 5, 7, 11, 13, 17})
        @DisplayName("Prime numbers test")
        void primeNumbers(int n) {
            assertTrue(isPrime(n));
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 4, 6, 8, 9, 10, 12, 14, 15, 16})
        @DisplayName("Not prime numbers test")
        void notPrimeNumbers(int n) {
            assertFalse(isPrime(n));
        }

        static boolean isPrime(int n) {
            if (n == 1) return false;
            for (int i = 2; i < n; i++) {
                if (n % i == 0) return false;
            }
            return true;
        }
    }

    @Test
    @DisplayName("Alphabet performance test")
    // This test fails half of the time on my 16Gb RAM computer.
    void performanceTest() {
        assertTimeout(Duration.ofMillis(1), () -> {
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 26; j++) {
                    System.out.print((char)('A' + j));
                }
                System.out.println();
            }
        } );
    }
}