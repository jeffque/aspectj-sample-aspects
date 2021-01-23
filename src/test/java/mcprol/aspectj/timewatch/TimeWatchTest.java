package mcprol.aspectj.timewatch;

import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class TimeWatchTest 
{
    @Test
    @TimeWatch
    public void timeWatching() throws InterruptedException
    {
        Thread.sleep(1000);
        assertTrue(true);
    }
}
