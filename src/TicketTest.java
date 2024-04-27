import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import entity.Ticket;

/**
 * This class contains test cases for the Ticket class.
 */
public class TicketTest {
    
    // Reference to another Ticket object
    static Ticket otherTicket;
    
    /**
     * Sets up a Ticket object before each test.
     */
    @BeforeEach
    public void setup() {
        // Creating a Ticket object for testing
        otherTicket = new Ticket("Pickup", "Toyota Tacoma", 2012, "Red", "Batman", 37757.99, 12);
    }

    /**
     * Tears down the Ticket object after each test.
     */
    @AfterEach
    public void teardown() {
        // Resetting the reference to the other Ticket object
        otherTicket = null;
    }

    /**
     * Tests the equality of two identical tickets.
     */
    @Test
    public void testEquality1() {
        // Asserting that two identical tickets are equal
        assertTrue(otherTicket.equals(new Ticket("Pickup", "Toyota Tacoma", 2012, "Red", "Batman", 37757.99, 12)));
    }

    /**
     * Tests the inequality of two tickets with different owners.
     */
    @Test
    public void testEquality2() {
        // Asserting that two tickets with different owners are not equal
        assertFalse(otherTicket.equals(new Ticket("Pickup", "Toyota Tacoma", 2012, "Red", "John Wick", 37757.99, 12)));
    }

    /**
     * Tests the equality of two identical tickets via their string representations.
     */
    @Test
    public void testEqualityViaStringFormat1() {
        // Asserting that the string representations of two identical tickets are equal
        assertEquals(otherTicket.toString(), new Ticket("Pickup", "Toyota Tacoma", 2012, "Red", "Batman", 37757.99, 12).toString());
    }

    /**
     * Tests the inequality of two tickets with different owners via their string representations.
     */
    @Test
    public void testEqualityViaStringFormat2() {
        // Asserting that the string representations of two tickets with different owners are not equal
        assertNotEquals(otherTicket.toString(), new Ticket("Pickup", "Toyota Tacoma", 2012, "Red", "John Wick", 37757.99, 12).toString());
    }

}
