package test_list;

import org.codeart.datastructure.list.PolySinglyList;
import org.codeart.datastructure.polynomial.Polynomial;
import org.codeart.datastructure.polynomial.TermX;
import org.junit.Test;

public class TestPolySinglyList {

    /**
     * 测试两条多项式单链表相加
     */
    @Test
    public void test01(){

        TermX[] terms = new TermX[]{new TermX(1,1),new TermX(2,4),new TermX(2,9),
        new TermX(3,0),new TermX(6,7)};

        TermX[] terms1 = new TermX[]{new TermX(2,1),new TermX(-2,4),new TermX(-2,9),
        new TermX(7,8),new TermX(-6,11)};

        PolySinglyList<TermX> list = new PolySinglyList<>(terms);
        PolySinglyList<TermX> list1 = new PolySinglyList<>(terms1);

        System.out.println("list = " + list);
        System.out.println("list1 = " + list1);

        list.addAll(list1);

        System.out.println("list = " + list);
    }

    /**
     * 测试多项式单链表拷贝构造方法
     */
    @Test
    public void test02(){

        TermX[] terms = new TermX[]{new TermX(1,1),new TermX(2,4),new TermX(2,9),
                new TermX(3,0),new TermX(6,7)};

        PolySinglyList<TermX> list = new PolySinglyList<>(terms);

        System.out.println("list = " + list);

        PolySinglyList<TermX> list1 = new PolySinglyList<>(list);

        System.out.println("list1 = " + list1);

    }

    /**
     * 测试多项式类相加
     */
    @Test
    public void test03(){

        TermX[] terms = new TermX[]{new TermX(1,1),new TermX(2,4),new TermX(2,9),
                new TermX(3,0),new TermX(6,7)};

        TermX[] terms1 = new TermX[]{new TermX(2,1),new TermX(-2,4),new TermX(-2,9),
                new TermX(7,8),new TermX(-6,11)};

        Polynomial aPoly = new Polynomial(terms);
        System.out.println("aPoly = " + aPoly);
        Polynomial bPoly = new Polynomial(terms1);
        System.out.println("bPoly = " + bPoly);

        aPoly.addAll(bPoly);
        System.out.println("aPoly = " + aPoly);


    }
}
