package app.polynomial;

public class Polynomial {

    private PolySinglyList<TermX> list; //多项式排序单链表

    public Polynomial(){

    }

    public Polynomial(TermX[] terms){

        this();

        this.list = new PolySinglyList<>(terms);
    }

    public Polynomial(String polyStr){

    }

    //深拷贝
    public Polynomial(Polynomial poly){

        this();

        this.list = new PolySinglyList<>(poly.list);
    }

    //多项式相加 this += poly
    public void addAll(Polynomial poly){

        this.list.addAll(poly.list);
    }

    //加法 +,返回this + poly多项式
    public Polynomial union(Polynomial polynomial){

        Polynomial cPoly = new Polynomial(this);

        cPoly.addAll(polynomial);

        return cPoly;
    }

    //比较两个多项式是否相等
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {

        return this.list.toString();
    }
}
