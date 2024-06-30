public class Staff extends Employee {
    private String role;
    public Staff (String name, String address, String phoneNumber, String emailAddress,
                 String office, double salary, String role) {
        super(name, address, phoneNumber, emailAddress, office, salary);
        this.role = role;
    }
    public String toString(){
        return super.toString() + ", Role: " + role;
    }

}
