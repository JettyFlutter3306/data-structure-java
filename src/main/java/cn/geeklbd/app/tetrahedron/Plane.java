package cn.geeklbd.app.tetrahedron;

public class Plane {

    private String name; //名称

    private Line a;

    private Line b;

    private Line c;

    public Plane(String name, Line a, Line b, Line c) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Plane() {
    }

    public Plane(String name) {

        this.name = name;

        switch (name) {
            case "ABC":
                this.a = new Line("AB");
                this.b = new Line("AC");
                this.c = new Line("BC");

                break;
            case "ABD":
                this.a = new Line("AB");
                this.b = new Line("AD");
                this.c = new Line("BD");

                break;
            case "ACD":
                this.a = new Line("AC");
                this.b = new Line("AD");
                this.c = new Line("CD");

                break;
            case "BCD":
                this.a = new Line("BC");
                this.b = new Line("BD");
                this.c = new Line("CD");
                break;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Line getA() {
        return a;
    }

    public void setA(Line a) {
        this.a = a;
    }

    public Line getB() {
        return b;
    }

    public void setB(Line b) {
        this.b = b;
    }

    public Line getC() {
        return c;
    }

    public void setC(Line c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "name='" + name + '\'' +
                ", a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
