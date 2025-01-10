package mcprol.aspectj.dummy;

/**
 * This class is used as a test class for our aspects
 */
@DummyAnnotationClass
public class DummyCounter 
{
    final static int LIMIT = 10;
    int value = 0;
    
    public static void main( String[] args )
    {
        final var dummy = new DummyCounter();
        System.out.println( "Hello World!" );
        System.out.println(dummy.random(11, 12, 3));
    }

    public int random(int a, @DummyAnnotation int b, int c) {
    // public int random(int a, int b, int c) {
        return a + b + c;
    }

    public int add(@DummyAnnotation int inc) {
        value += inc;
        
        return value;
    }
}
