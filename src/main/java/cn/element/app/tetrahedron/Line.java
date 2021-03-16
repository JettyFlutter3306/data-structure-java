package cn.element.app.tetrahedron;

public class Line {

    private String name; //名称

    private Point a;

    private Point b;

    public Line(String name, Point a, Point b) {
        this.name = name;
        this.a = a;
        this.b = b;
    }

    public Line() {
    }

    public Line(String name) {

        this.name = name;

        switch (name){
            case "AB":
                this.a = new Point("A");
                this.b = new Point("B");
                break;
            case "AC":
                this.a = new Point("A");
                this.b = new Point("C");
                break;
            case "AD":
                this.a = new Point("A");
                this.b = new Point("D");
                break;
            case "BC":
                this.a = new Point("B");
                this.b = new Point("C");
                break;
            case "BD":
                this.a = new Point("B");
                this.b = new Point("D");
                break;
            case "CD":
                this.a = new Point("C");
                this.b = new Point("D");
                break;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getA() {
        return a;
    }

    public void setA(Point a) {
        this.a = a;
    }

    public Point getB() {
        return b;
    }

    public void setB(Point b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "Line{" +
                "name='" + name + '\'' +
                ", a=" + a +
                ", b=" + b +
                '}';
    }
}
