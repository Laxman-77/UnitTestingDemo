package com.company;

import org.junit.Test;
//import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class demoTest1 {
    @Test
    public void addNumbers() {
        demo test= new demo();
        int res=test.addNumbers(5,7);
        assertEquals(12,res);
        //assertEquals(13,res);
    }

    @Test
    public void addStrings() {
        demo test = new demo();
        String res= test.addStrings("Laxman","Goliya");
        assertEquals("LaxmanGoliya",res);
    }
}
