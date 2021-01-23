package mcprol.aspectj.dummy;

/**
 * This class is used as a test class for our aspects
 */
public class DummyCounter 
{
    final static int LIMIT = 10;
    int value = 0;
    
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
    
    public int add(int inc) {
        value += inc;
        
        return value;
    }
}
