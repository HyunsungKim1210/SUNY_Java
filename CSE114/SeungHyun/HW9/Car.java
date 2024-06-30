import java.util.Objects;

public class Car implements Comparable<Car> {
    protected String make;
    protected String model;
    protected int year;
    protected String color;
    protected String owner;
    protected int odometer;
    protected int numRepairs;

    public Car(String make, String model, int year, String color, String owner) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.owner = owner;
        this.odometer = 0;
        this.numRepairs = 0;
    }

    public void sellTo(String newOwner) {
        this.owner = newOwner;
    }

    public void repair() {
        numRepairs++;
    }

    public void travel(int miles) {
        odometer += miles;
    }

    public boolean isReliable() {
        return numRepairs <= 2024-year;
    }

    public boolean isHighMileage() {
        return (double) odometer / (2024 - year) > 12000;
    }

    @Override
    public int compareTo(Car other) {
        int makeComparison = this.make.compareTo(other.make);
        if (makeComparison != 0) {
            return makeComparison;
        } else {
            return this.model.compareTo(other.model);
        }
    }

    public static void main(String[] args) {
        Car car1 = new Car("Ford", "Taurus", 2010, "blue", "Amy Jones");
        Car car2 = new Car("Chevy", "Malibu", 2015, "red", "Bob Smith");

        // Test methods
        System.out.println("Car 1: " + car1);
        System.out.println("Car 2: " + car2);

        car1.sellTo("Alice Brown");
        System.out.println("New owner of Car 1: " + car1.owner);

        car1.repair();
        System.out.println("Number of repairs for Car 1: " + car1.numRepairs);

        car1.travel(5000);
        System.out.println("Odometer reading for Car 1: " + car1.odometer);

        System.out.println("Is Car 1 reliable? " + car1.isReliable());
        System.out.println("Is Car 1 high mileage? " + car1.isHighMileage());

        // Test compareTo
        System.out.println("Comparison of Car 1 and Car 2: " + car1.compareTo(car2));
    }

    @Override
    public String toString() {
        return "Make: " + make + ", Model: " + model + ", Year: " + year + ", Color: " + color + ", Owner: " + owner
                + ", Odometer: " + odometer + ", Num Repairs: " + numRepairs;
    }
}
