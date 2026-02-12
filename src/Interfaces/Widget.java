package Interfaces;

public class Widget implements Comparable{

    private int id;
    private String name;
    private int amount;

    public Widget() {}

    public Widget(int id, String name, int amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    @Override
    public String toString() {
        return String.format("%d %s %d\n", id, name, amount);
    }

    /**
     * Compares the value of this widget with another object for ordering.
     * Comparison with other widgets will be done based on id
     * @param o Object - the other object we are comparing with
     * @return -1, 0 or 1 if the other object is less than, equal or greater than this
     */
    public int compareTo(Object o) {
        int result = 0;

        // Check to see if other widget is a Widget, so we can check the id
        if (o instanceof Widget otherWidget) {

            if (id < otherWidget.getId()) {
                result = -1;
            }
            else if (id == otherWidget.getId()) {
                result = 0;
            }
            else if (id > otherWidget.getId()) {
                result = 1;
            }
        }
        return result;
    }
}
