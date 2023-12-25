
public class Throttle {
    private int position;
    private int top;

    public Throttle(int size) {
        top = size;
    }

    public double getFlow() {
        return 1.0 * (position / top);
    }

    public boolean isOn() {
        return (position > 0);
    }

    public void shift(int amount) {
        if (amount > top - position) {
            position = top;
        } else if (position + amount < 0) {
            position = 0;
        } else {
            position = position + amount;
        }
    }

    public void shutOff() {
        position = 0;
    }
}
