package mcprol.aspectj.dummy;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for DummyCounter.
 */
public class DummyCounterTest 
{
    DummyCounter sut;
    
    @Before
    public void beforeTest() {
        sut = new DummyCounter();
    }

    @After
    public void afterTest() {
    }
    
    @Test
    public void runAdd()
    {
        int actual = sut.add(1);
        assertEquals(1, actual);
    }
    
    @Test
    public void runAddTwice()
    {
        sut.add(1);
        int actual = sut.add(1);
        assertEquals(2, actual);
    }
    
    @Test
    public void runAddOverflow()
    {
        int actual = sut.add(11);
        assertEquals(DummyCounter.LIMIT, actual);
        // System.out.println(actual);
    }

    @Test
    public void runRandom()
    {
        int actual = sut.random(11, 12, 3);
        System.out.println(actual);
        // assertEquals(DummyCounter.LIMIT, actual);
        // System.out.println(actual);
    }
}
