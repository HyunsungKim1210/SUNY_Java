public class Student {
    private String classYear;
    private String major;

    public Student(String name, String address, String phoneNumber, String emailAddress, String classYear, String major){
        super(name, address, phoneNumber, emailAddress);
        this.classYear = classYear;
        this.major =major;
    }
    public String toString(){
        return super.toString()+"\nClass Year: "+classYear+"\nMajor: "+major;
    }
}
