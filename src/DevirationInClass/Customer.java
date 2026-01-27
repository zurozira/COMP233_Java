package DevirationInClass;

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

        return
    }

    @Override
    public String toString() {

        return String.format("""
                ID: %d
                Name: %s
                """, customerID, super.toString());
    }
}