package cn.element.app.tetrahedron;

public class Tetrahedron {

    private Plane abc;

    private Plane abd;

    private Plane acd;

    private Plane bcd;

    public Tetrahedron() {

        this.abc = new Plane("ABC");

        this.abd = new Plane("ABD");

        this.acd = new Plane("ACD");

        this.bcd = new Plane("BCD");

    }

    public Tetrahedron(Plane abc, Plane abd, Plane acd, Plane bcd) {
        this.abc = abc;
        this.abd = abd;
        this.acd = acd;
        this.bcd = bcd;
    }

    public Plane getAbc() {
        return abc;
    }

    public void setAbc(Plane abc) {
        this.abc = abc;
    }

    public Plane getAbd() {
        return abd;
    }

    public void setAbd(Plane abd) {
        this.abd = abd;
    }

    public Plane getAcd() {
        return acd;
    }

    public void setAcd(Plane acd) {
        this.acd = acd;
    }

    public Plane getBcd() {
        return bcd;
    }

    public void setBcd(Plane bcd) {
        this.bcd = bcd;
    }

    @Override
    public String toString() {
        return "Tetrahedron{" +
                "abc=" + abc +
                ", abd=" + abd +
                ", acd=" + acd +
                ", bcd=" + bcd +
                '}';
    }
}
