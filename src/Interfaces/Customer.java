package Interfaces;


/**
 * Customer class inherits Person class and add customer ID component
 * @author Vu Cong Bui
 */
public class Customer extends Person implements Comparable {

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

    /**
     * Return the customer full name as a String
     * @return customer full name
     */
    public String getFullName() {

        return super.toString();
    }

    /**
     * Compares the value of this Customer with another object for ordering.
     * Comparison with other customers will be done based on customerId
     * @param o Object - the other object we are comparing with
     * @return -1, 0 or 1 if the other object is less than, equal or greater than this
     */
    public int compareTo(Object o) {

        int result = 0;

        if (o instanceof Customer customer)
        {
            if (this.customerID > customer.getCustomerID()) {
                result = 1;
            }
            else if (this.customerID < customer.getCustomerID()) {
                result = -1;
            }
            else {
                result = 0;
            }
        }

        return result;
    }
}
