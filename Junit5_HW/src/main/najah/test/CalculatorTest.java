package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.Calculator;

@DisplayName("Calculator Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculatorTest {

    Calculator calc;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
    }
    
   @Test
    @Order(1)
    @DisplayName("Test addition with valid numbers")
    void testAdd() {
        assertEquals(5, calc.add(2, 3));
        assertEquals(0, calc.add(-1, 1));
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    @DisplayName("Parameterized addition test")
    void testAddParameterized(int num) {
        assertTrue(calc.add(num, num) > 0);
    }

  
    @Test
    @DisplayName("Test add with no numbers")
    void testAddEmpty() {
        assertEquals(0, calc.add());
    }

  
    @Test
    @Order(2)
    @DisplayName("Test factorial values")
    void testFactorial() {
        assertEquals(120, calc.factorial(5));
        assertEquals(1, calc.factorial(0));
        assertEquals(2, calc.factorial(2)); // 🔥 زيادة تغطية
    }

  
    @Test
    @Order(3)
    @DisplayName("Test factorial negative input")
    void testFactorialNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            calc.factorial(-1);
        });
    }


    @Test
    @Order(4)
    @DisplayName("Test division valid")
    void testDivide() {
        assertEquals(2, calc.divide(4, 2));
        assertEquals(0, calc.divide(1, 2)); // 🔥 integer division
    }

   
    @Test
    @Order(5)
    @DisplayName("Test division by zero")
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> {
            calc.divide(5, 0);
        });
    }

  
    @Test
    @Timeout(2)
    @DisplayName("Timeout test")
    void testTimeout() {
        calc.add(1000, 2000);
    }
    
    @Test
    @DisplayName("Test divide two negative numbers")
    void testDivideTwoNegatives() {
        assertEquals(2, calc.divide(-4, -2));
    }
    
    @Test
    @DisplayName("Test divide negative by negative")
    void testDivideNegativeByNegative() {
        assertEquals(1, calc.divide(-2, -2));
    }
    
    @Test
    @DisplayName("Test factorial of 1")
    void testFactorialOne() {
        assertEquals(1, calc.factorial(1));
    }
    
    @Test
    @DisplayName("Test add many numbers")
    void testAddManyNumbers() {
        assertEquals(55, calc.add(1,2,3,4,5,6,7,8,9,10));
    }






    @Test
    @Disabled("Failing test example")
    @DisplayName("Disabled failing test")
    void failingTest() {
        assertEquals(10, calc.divide(10, 0));
    }
}

