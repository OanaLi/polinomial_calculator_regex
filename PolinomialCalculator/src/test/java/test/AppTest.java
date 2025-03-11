package test;

import org.junit.Test;
import polinoame.Polinom;
import polinoame.RezultatImpartire;

import static org.junit.Assert.*;
public class AppTest{

    Polinom a=new Polinom("2x^4+2x^3");
    Polinom b=new Polinom("-4x+4");

    @Test
    public void testAdd() {
        Polinom rez=a.add(b);
        assertEquals("+2x^4+2x^3-4x+4", rez.polinomToString());
    }

    @Test
    public void testSubstract() {
        Polinom rez=a.substract(b);
        assertEquals("+2x^4+2x^3+4x-4", rez.polinomToString());
    }

    @Test
    public void testMultiply() {
        Polinom rez=a.multiply(b);
        assertEquals("-8x^5+8x^3", rez.polinomToString());
    }

    @Test
    public void testDivide() {
        RezultatImpartire rez=a.divide(b);
        assertEquals("-0.5x^3-x^2-x-1", rez.getCat().polinomToString());
        assertEquals("+4", rez.getRest().polinomToString());
    }

    @Test
    public void testIntegreaza() {
        Polinom rez=a.integreaza();
        assertEquals("+0.4x^5+0.5x^4", rez.polinomToString());
    }

    @Test
    public void testDeriveaza() {
        Polinom rez=a.deriveaza();
        assertEquals("+8x^3+6x^2", rez.polinomToString());
    }
}
