package DevirationInClass;

public class Employee extends Person {

    private int id;
    private String position;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String dob, int id, String position) {

        super(firstName, lastName, dob); // Constructor of the parent class

        this.dob = dob; // This only possible because dob PROTECTED

        this.id = id;
        this.position = position;
    }

    public int getId() { return id; }
    public String getPosition() { return position; }

    public void setId(int id) { this.id = id; }
    public void setPosition(String position) { this.position = position; }

    @Override
    public String toString() {

        return String.format("#%d: %s, %s", getId(), getLastName(), getFirstName());
    }

}
