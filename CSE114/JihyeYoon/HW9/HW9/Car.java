//Jihye Yoon_115960721_jihye.yoon@stonybrook.edu

public class Car implements Comparable<Car> {
    protected String make;
    protected String model;
    protected int year;
    protected String color;
    protected String owner;
    protected int odometer;
    protected int numRepairs;

    public Car(String make, String model, int year, String color, String owner){
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.owner = owner;
        this.odometer = 0;
        this.numRepairs = 0;
    }

    public void sellTo(String newOwner){
        this.owner = newOwner;
    }
    public void repair(){
        numRepairs++;
    }
    public void travel(int miles){
        odometer += miles;
    }
    public boolean isReliable(){
        return numRepairs <= (2024 - year);
    }
    public boolean isHighMilage(){
        if (year == 2024){
        return false;
        }
        return (odometer / 2024 - year) > 12000;
    }

    public String getMake() {
        return make;
    }
    public String getModel() {
        return model;
    }
    public int getYear() {
        return year;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getOwner() {
        return owner;
    }
    public double getOdometer() {
        return odometer;
    }
    public int getNumRepairs() {
        return numRepairs;
    }



    @Override
    public int compareTo(Car otherCar) {
        int comparison = this.make.compareTo(otherCar.getMake());
        if (comparison != 0) {
            return comparison;
        }
        return this.model.compareTo(otherCar.getModel());
    }
    public static void main(String[] args) {
        Car car1 = new Car("Chevy", "Corvette", 1998, "Blue", "Jisun Lee");
        Car car2 = new Car("Chevy", "Malibu", 2024, "Black", "Hannah Kim");
        Car car3 = new Car("Ford", "Explorer", 2002, "red", "Jihye Yoon");
        Car car4 = new Car("Ford", "Taurus", 2023, "White", "Olive White");

        car1.travel(50000);
        car1.repair();
        car2.travel(5000);
        car2.repair();
        car3.travel(20000);
        car3.repair();
        car4.travel(45000);
        car4.repair();


        System.out.println("Car1 Reliable: " + car1.isReliable());
        System.out.println("Car1 High Mileage: " + car1.isHighMilage());
        System.out.println("Car2 Reliable: " + car2.isReliable());
        System.out.println("Car2 High Mileage: " + car2.isHighMilage());
        System.out.println("Car3 Reliable: " + car3.isReliable());
        System.out.println("Car3 High Mileage: " + car3.isHighMilage());
        System.out.println("Car4 Reliable: " + car4.isReliable());
        System.out.println("Car4 High Mileage: " + car4.isHighMilage());

        System.out.println("Comparing Car1 to Car2: " + car1.compareTo(car2));
        System.out.println("Comparing Car2 to Car3: " + car2.compareTo(car3));
        System.out.println("Comparing Car3 to Car4: " + car3.compareTo(car4));
        System.out.println("Comparing Car1 to Car4: " + car1.compareTo(car4));
        System.out.println("Comparing Car4 to Car2: " + car4.compareTo(car2));
    }
}

