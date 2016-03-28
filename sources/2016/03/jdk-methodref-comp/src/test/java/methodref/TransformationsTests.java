package methodref;

import static methodref.Transformations.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TransformationsTests {

    @Test
    public void testIntegerToString1() {
        assertEquals("13", doStuff(1,2));
        assertEquals("13", doMoreStuff(1,2));
        assertEquals("13", doEvenMoreStuff(1,2));
    }

}
