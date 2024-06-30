public class Faculty extends Employee {
    private String belongsTo;
    private String rank;
    public Faculty(String name, String address, String phoneNumber, String emailAddress,
                   String office, double salary, String belongsTo, String rank){
        super(name,address, phoneNumber, emailAddress, office, salary);
        this.belongsTo = belongsTo;
        this.rank = rank;
    }
    public String toString(){
        return super.toString() + ", Belongs to: " + belongsTo + ", Rank: " + rank;
    }
}
