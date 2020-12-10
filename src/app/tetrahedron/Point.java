package app.tetrahedron;

public class Point {

    private String name; //名称

    private int x; //横坐标

    private int y; //纵坐标

    private int z; //竖坐标

    public Point(String name, int x, int y, int z) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point() {
    }

    public Point(String name) {

        this.name = name;

        switch (name){
            case "A":
                this.x = 5;
                this.y = 1;
                this.z = 0;
                break;
            case "B":
                this.x = 5;
                this.y = 6;
                this.z = 1;
                break;
            case "C":
                this.x = 2;
                this.y = 4;
                this.z = 1;
                break;
            case "D":
                this.x = 3;
                this.y = 5;
                this.z = 4;
                break;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "Point{" +
                "name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
