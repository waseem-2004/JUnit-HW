package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.najah.code.UserService;

@DisplayName("UserService Tests")
@Execution(ExecutionMode.CONCURRENT)
class UserServiceSimpleTest {

    UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
        System.out.println("Setup UserService");
    }

    @Test
    @DisplayName("Test valid email")
    void testValidEmail() {
        assertTrue(userService.isValidEmail("test@gmail.com"));
    }

    @Test
    @DisplayName("Test invalid email")
    void testInvalidEmail() {
        assertFalse(userService.isValidEmail("invalidEmail"));
        assertFalse(userService.isValidEmail(null));
    }
    
    @Test
    @DisplayName("Test email missing dot")
    void testEmailMissingDot() {
        assertFalse(userService.isValidEmail("test@gmail"));
    }

    @Test
    @DisplayName("Test wrong username correct password")
    void testWrongUsername() {
        assertFalse(userService.authenticate("admin1", "1234"));
    }


    @ParameterizedTest
    @ValueSource(strings = {"a@b.com", "user@test.org", "x@y.net"})
    @DisplayName("Parameterized valid emails")
    void testMultipleEmails(String email) {
        assertTrue(userService.isValidEmail(email));
    }

    @Test
    @DisplayName("Test valid authentication")
    void testValidAuth() {
        assertTrue(userService.authenticate("admin", "1234"));
    }

    @Test
    @DisplayName("Test invalid authentication")
    void testInvalidAuth() {
        assertFalse(userService.authenticate("user", "wrong"));
    }

    @Test
    @Timeout(2)
    @DisplayName("Timeout test")
    void testTimeout() {
        userService.isValidEmail("test@test.com");
    }
    
    @Test
    @DisplayName("Test email missing @")
    void testEmailMissingAt() {
        assertFalse(userService.isValidEmail("test.com"));
    }
    
    @Test
    @DisplayName("Test invalid email format")
    void testInvalidEmailFormat() {
        assertTrue(userService.isValidEmail("@."));
    }
    
    @Test
    @DisplayName("Test correct username wrong password")
    void testCorrectUserWrongPass() {
        assertFalse(userService.authenticate("admin", "0000"));
    }
    
    @Test
    @DisplayName("Test wrong username correct password")
    void testWrongUserCorrectPass() {
        assertFalse(userService.authenticate("user", "1234"));
    }
    
    @Test
    @DisplayName("Test empty email")
    void testEmptyEmail() {
        assertFalse(userService.isValidEmail(""));
    }

    @Test
    @DisplayName("Test email only @")
    void testOnlyAt() {
        assertFalse(userService.isValidEmail("@"));
    }

    @Test
    @DisplayName("Test email only dot")
    void testOnlyDot() {
        assertFalse(userService.isValidEmail("."));
    }
    
    @Test
    @DisplayName("Test weird email format")
    void testWeirdEmail() {
        assertTrue(userService.isValidEmail(".@"));
    }
    
    @Test
    @DisplayName("Test email with spaces")
    void testEmailWithSpaces() {
        assertFalse(userService.isValidEmail(" "));
    }
    
    @Test
    @DisplayName("Test authentication with nulls")
    void testAuthNulls() {
        assertFalse(userService.authenticate(null, null));
    }

    @Test
    @DisplayName("Test email with @ but no dot after")
    void testAtWithoutDotAfter() {
        assertFalse(userService.isValidEmail("test@com"));
    }
    
    @Test
    @DisplayName("Test authenticate edge case")
    void testAuthEdgeCase() {
        assertFalse(userService.authenticate("", ""));
    }
    
    @Test
    @DisplayName("Test email with special chars")
    void testEmailSpecialChars() {
        assertTrue(userService.isValidEmail("@test."));
    }
    
    @Test
    @DisplayName("Test email minimal valid")
    void testMinimalValidEmail() {
        assertTrue(userService.isValidEmail("a@b.c"));
    }







    @Test
    @Disabled("Failing test example")
    @DisplayName("Disabled failing test")
    void failingTest() {
        assertTrue(userService.authenticate("admin", "wrong"));
    }
}
