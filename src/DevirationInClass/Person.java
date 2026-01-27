package DevirationInClass;

public class Person {

    private String firstName;
    private String lastName;
    protected String dob; // Protected -> Child can access

    public Person() {

    }

    public Person(String firstName, String lastName, String dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getDOB() { return dob; }

    public void setFirstName(String firstname) { this.firstName = firstname; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setDOB(String dob) { this.dob = dob; }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("%s, %s", lastName, firstName);
    }
}
