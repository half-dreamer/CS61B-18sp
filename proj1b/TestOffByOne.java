import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();
    // Your tests go here.
    @Test
    public void testOffByOne() {
        assertTrue(offByOne.equalChars('a','b'));
        assertFalse(offByOne.equalChars('x','z'));
        assertTrue(offByOne.equalChars('y','x'));
    }
    @Test
    public void testOffByN() {
        CharacterComparator OffBy3 = new OffByN(3);
        assertTrue(OffBy3.equalChars('a','d'));
        assertFalse(OffBy3.equalChars('a','b'));
    }
}
