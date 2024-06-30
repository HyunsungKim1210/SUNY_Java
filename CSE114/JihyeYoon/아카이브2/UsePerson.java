public class UsePerson {
    public static void main(String[] args) {
        Employee[] employees = new Employee[] {
                new Employee("Jihye Yoon", "223 Songdo St", "010-1234", "jihye@example.com", "808", 50000),
                new Faculty("Amily Kim", "334 Songdo St", "010-5678", "amily@example.com", "905", 30000, "AMS", "TA"),
                new Staff("Emma Kang", "445 Songdo St", "010-9012", "emma@example.com", "704", 20000, "Tutor")
        };

        for (int i = 0; i < employees.length ; i++) {
            System.out.println("Original" + employees[i].toString());
            employees[i].raise(10);
            System.out.println("Changed: " + employees[i].toString());
        }
    }
}
