package extra;

public class Coordinates {
    float x;
    float y;

    public Coordinates(float x,float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public boolean equals(Coordinates coordinates) {
        if ((getX() == coordinates.getX())&&(getY() == coordinates.getY())) {
            return true;
        }
        return false;
    }
}
