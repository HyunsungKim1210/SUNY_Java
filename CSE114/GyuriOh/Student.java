class Student extends Person {
    
    private String classYear;
    private String major;

    public Student (String name, String address, String phoneNumber, String emailAddress, String classYear, String major) {
	super (name, address, phoneNumber, emailAddress);
	this.classYear = classYear;
	this.major = major;
    }

    @Override
    public String toString() {
	return "Student" + super.toString() + " \n Class year: " + classYear + " \n Major: " + major ;
    }

}
