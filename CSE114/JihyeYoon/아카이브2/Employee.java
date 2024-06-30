public class Employee extends Person{
    private String office;
    private double salary;
    public Employee(String name, String address, String phoneNumber, String emailAddress,
                     String office, double salary){
        super(name, address, phoneNumber, emailAddress);
        this.office = office;
        this.salary = salary;
    }
    public void raise(double percentage) {
        salary += salary * percentage / 100;
    }
    public String toString(){
        return super.toString() + ", Office: " + office + ", Salary: " + salary;
    }
}
