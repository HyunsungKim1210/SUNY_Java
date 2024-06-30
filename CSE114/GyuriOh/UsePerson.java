public class UsePerson {
    public static void main (String[] args) {
	Person person = new Person ("John Doe", "123 Main St", "555-1234", "john.does@gmail.com" );
	System.out.println(person);

	Student student = new Student ("Alice Smith", "456 Elm St", "555-5678", "alice.smith@gmail.com", "senior", "Technology and Society");
	System.out.println(student);

	Employee employee = new Employee ("Bob Johnson", "789 Oak St", "555-9101", "bob.johnson@gmail.com", "101", 5000);
	System.out.println(employee);

	Faculty faculty = new Faculty ("Jane Brown", "321 Pine St", "555-1122", "jane.brown@gmail.com", "201", 80000, "Computer Science", "Associate");
	System.out.println(faculty);

	Staff staff = new Staff ("Mike Wilson", "654 Cedar St", "555-1314", "mike.wilson@gmail.com", "301", 40000, "Admin Assistant");
	System.out.println(staff);

	Employee[] employees = new Employee[3];
	employees[0] = new Employee ("John Doe", "123 Main St", "555-1234", "john.doe@gmail.com", "101", 50000);
	employees [1] = new Faculty ( "Jane Brown", "321 Pine St", "555-1122", "jane.brown@gmail.com", "201", 80000, "Computer Science", "Associate");
	employees [2] = new Staff ( "Mike Wilson", "654 Cedar St", "555-1314", "mike.wilson@gmail.com", "301", 40000, "Admin Assistant");

	for (int i = 0; i < employees.length ; i++) {
	    employee = employees[i];
	    employee.raiseSalary(10);

	System.out.println(employees.toString());
	}
	
    }
}
