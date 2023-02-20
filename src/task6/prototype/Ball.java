package task6.prototype;

public class Ball implements Copyable {

    private String color;

    private int size;

    public Ball(String color, int size) {
        this.color = color;
        this.size = size;
    }

    @Override
    public String toString() {
        return color + " ball is " + size + " sm in diameter.";
    }

    @Override
    public Object copy() {
        return new Ball(this.color, this.size);
    }
}
