package Polymorphism;


/**
 * Customer class inherits Person class and add customer ID component
 * @author Vu Cong Bui
 */
public class Customer extends Person {

    private int customerID;

    public Customer() {
    }

    public Customer(int customerID, String firstName, String lastName, char middleInit) {

        super(firstName, lastName, middleInit);

        this.customerID = customerID;
    }

    public int getCustomerID() { return customerID; }

    public void setCustomerID(int customerID) { this.customerID = customerID; }

    public String writeAsRecord() {

        return String.format("%d %s %c %s", customerID, getFirstName(), getMiddleInit(), getLastName());
    }

    public String toString() {

        return String.format("%d %s", customerID, super.toString());
    }

    public String getFullName() {

        return super.toString();
    }
}
