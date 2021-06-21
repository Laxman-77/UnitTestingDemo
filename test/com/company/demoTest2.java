package com.company;

//import org.junit.jupiter.api.Test;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class demoTest2 {

    demo d = new demo();
    @Test
    public void addNumbers() {
        assertEquals(10,d.addNumbers(7,3));
        assertEquals(15,d.addNumbers(18,-3));
    }

    @Test
    public void addStrings() {
        assertEquals("Hello World",d.addStrings("Hello ","World"));
        assertEquals("ramgopal",d.addStrings("ram","gopal"));
    }

}