package testList;

import cn.geeklbd.app.polynomial.TermX;
import org.junit.Test;

public class TestTermX {

    @Test
    public void test01(){

        TermX a = new TermX(1,1);
        System.out.println("a = " + a); // x

        TermX b = new TermX(-1,0);
        System.out.println("b = " + b); // -1

        TermX c = new TermX(4,6);
        System.out.println("c = " + c); // 4x^6

    }

    @Test
    public void test02(){

        TermX a = new TermX(1,1);
        System.out.println("a = " + a); // x

        TermX c = new TermX(4,6);
        System.out.println("c = " + c); // 4x^6

        System.out.println(a.compareTo(c));

        TermX d = new TermX(4,6);
        a.add(d);

        System.out.println("a = " + a);

        System.out.println(a.equals(c));
        System.out.println(c.equals(d));
    }

    @Test
    public void test03(){


    }
}
