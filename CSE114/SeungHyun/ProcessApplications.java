



import java.io.*;

public class ProcessApplications {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Usage: java ProcessApplications input_file");
            return;
        }

        String inputFile = args[0];

        try (BufferedReader input = new BufferedReader(new FileReader(inputFile))) {

            int numApplications = Integer.parseInt(input.readLine());
            Application[] applications = new Application[numApplications];

            String idString;
            int i = 0;

            while ((idString = input.readLine()) != null) {
                int id = Integer.parseInt(idString);
                String[] nameParts = input.readLine().split(" ");
                String firstName = nameParts[0];
                String middleName = nameParts.length > 2 ? nameParts[1] : "";
                String lastName = nameParts[nameParts.length - 1];
                String intendedMajor = input.readLine();
                double highSchoolGPA = Double.parseDouble(input.readLine());
                boolean isScholarshipApplicant = input.readLine().equalsIgnoreCase("Yes");
                String streetAddress = input.readLine();
                String city = input.readLine();
                String state = input.readLine();
                String zipCode = input.readLine();
                String phoneNumber = input.readLine();

                applications[i] = new Application(id, firstName, middleName, lastName,
                        intendedMajor, highSchoolGPA, isScholarshipApplicant,
                        streetAddress, city, state, zipCode, phoneNumber);

                i++;
            }

            // Scholarship recipients
            System.out.println("Scholarship Recipients:");
            for (Application application : applications) {
                if (application != null && application.getHighSchoolGPA() > 3.5 &&
                        (application.getIntendedMajor().equals("Philosophy") ||
                                application.getIntendedMajor().equals("History") ||
                                application.getIntendedMajor().equals("Psychology")) &&
                        (application.getState().equals("Montana") ||
                                application.getState().equals("Wyoming") ||
                                application.getState().equals("New York")) &&
                        application.isScholarshipApplicant()) {
                    System.out.println(application.getFirstName() + " " +
                            application.getLastName());
                }
            }

            // Average GPA of applicants from Oklahoma
            double totalGPAOklahoma = 0;
            int numOklahoma = 0;
            for (Application application : applications) {
                if (application != null && application.getState().equals("Oklahoma")) {
                    totalGPAOklahoma += application.getHighSchoolGPA();
                    numOklahoma++;
                }
            }
            double averageGPAOklahoma = numOklahoma > 0 ? totalGPAOklahoma / numOklahoma : 0;
            System.out.println("Average GPA of applicants from Oklahoma: " +
                    averageGPAOklahoma);

            // Highest GPA applicant from New York
            double highestGPA = 0;
            String highestGPAName = "";
            for (Application application : applications) {
                if (application != null && application.getState().equals("New York") &&
                        application.getHighSchoolGPA() > highestGPA) {
                    highestGPA = application.getHighSchoolGPA();
                    highestGPAName = application.getFirstName() + " " +
                            application.getLastName();
                }
            }
            System.out.println("Highest GPA applicant from New York: " +
                    highestGPAName + ", GPA: " + highestGPA);

            // Number of applications from each state
            System.out.println("Number of applications from each state:");
            countApplicationsByState(applications);
        }
    }

    public static void countApplicationsByState(Application[] applications) {
        // Create an array to store the counts for each state
        int[] stateCounts = new int[Application.STATE_VALUES.length];

        // Initialize the counts to zero
        for (int i = 0; i < stateCounts.length; i++) {
            stateCounts[i] = 0;
        }

        // Count the applications for each state
        for (Application application : applications) {
            if (application != null) {
                int stateIndex = Application.getStateIndex(application.getState());
                if (stateIndex != -1) {
                    stateCounts[stateIndex]++;
                }
            }
        }

        // Print the number of applications from each state
        for (int i = 0; i < Application.STATE_VALUES.length; i++) {
            if (stateCounts[i] > 0) {
                System.out.println(Application.STATE_VALUES[i] + ", " + stateCounts[i]);
            }
        }
    }
}
