public class Employee {
    private String office;
    private String salary;
    public Employee(String name, String address, String phoneNumber, String emailAddress, String office, String salary){
        super(name,address, phoneNumber, emailAddress);
        this.office = office;
        this.salary = salary;
    }
}
