package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.Product;

@DisplayName("Product Tests")
public class ProductTest {

    Product p;

    @BeforeEach
    void setUp() {
        p = new Product("Laptop", 100.0);
    }

    @Test
    @DisplayName("Test valid product creation")
    void testValidConstructor() {
        assertEquals("Laptop", p.getName());
        assertEquals(100.0, p.getPrice());
        assertEquals(0, p.getDiscount());
    }

    @Test
    @DisplayName("Test negative price in constructor")
    void testInvalidConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product("Phone", -50);
        });
    }

    @Test
    @DisplayName("Test zero discount")
    void testZeroDiscount() {
        p.applyDiscount(0);
        assertEquals(100.0, p.getFinalPrice());
    }

    @Test
    @DisplayName("Test max discount 50%")
    void testMaxDiscount() {
        p.applyDiscount(50);
        assertEquals(50.0, p.getFinalPrice());
    }

    @Test
    @DisplayName("Test valid discount")
    void testValidDiscount() {
        p.applyDiscount(20);
        assertEquals(20, p.getDiscount());
        assertEquals(80, p.getFinalPrice());
    }

    @Test
    @DisplayName("Test invalid discount > 50")
    void testInvalidDiscountHigh() {
        assertThrows(IllegalArgumentException.class, () -> {
            p.applyDiscount(60);
        });
    }

    @Test
    @DisplayName("Test invalid discount negative")
    void testInvalidDiscountNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            p.applyDiscount(-10);
        });
    }

    @ParameterizedTest
    @ValueSource(doubles = {10, 25, 50})
    @DisplayName("Parameterized discount test")
    void testMultipleDiscounts(double discount) {
        p.applyDiscount(discount);
        assertTrue(p.getFinalPrice() <= 100);
    }

    @Test
    @DisplayName("Test getters")
    void testGetters() {
        assertEquals("Laptop", p.getName());
        assertEquals(100.0, p.getPrice());
    }

    @Test
    @Timeout(2)
    @DisplayName("Timeout test")
    void testTimeout() {
        p.getFinalPrice();
    }
    
    @Test
    @DisplayName("Test zero price product")
    void testZeroPrice() {
        Product p2 = new Product("Free", 0);
        assertEquals(0, p2.getFinalPrice());
    }
    
    @Test
    @DisplayName("Test getters after discount")
    void testGettersAfterDiscount() {
        p.applyDiscount(30);

        assertEquals("Laptop", p.getName());
        assertEquals(100.0, p.getPrice());
        assertEquals(30, p.getDiscount());
    }


    @Test
    @Disabled("Failing test example")
    @DisplayName("Disabled failing test")
    void failingTest() {
        assertEquals(200, p.getFinalPrice());
    }
}

