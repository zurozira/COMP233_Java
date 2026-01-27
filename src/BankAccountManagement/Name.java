package BankAccountManagement;

/**
 * Represents a person's full name with first name, last name, and middle initial
 */
public class Name {

    private String firstName;
    private String lastName;
    private char middleInit;

    public Name() {
    }

    public Name(String firstName, String lastName, char middleInit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleInit = middleInit;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public char getMiddleInit() {
        return middleInit;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleInit(char middleInit) {
        this.middleInit = middleInit;
    }

    /**
     *
     * @return the String of user full name
     */
    public String toString() {
        return firstName + ' ' + middleInit + ' ' + lastName;
    }
}
