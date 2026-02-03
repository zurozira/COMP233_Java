package Polymorphism;

/**
 * Represent a person with full name components (First name, Last name, Middle initial)
 * @author Vu Cong Bui
 */
public class Person {

    private String firstName;
    private String lastName;
    private char middleInit;

    public Person() {

    }

    public Person(String firstName, String lastName, char middleInit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleInit = middleInit;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public char getMiddleInit() { return middleInit; }

    public void setFirstName(String firstname) { this.firstName = firstname; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setMiddleInit(char middleInit) { this.middleInit = middleInit; }

    /**
     *
     * @return person's full name as formatted
     */
    @Override
    public String toString() {
        return String.format("%s, %s %c", lastName, firstName, middleInit);
    }
}
