public class PassengerCar extends Car {
    private int numPassengers;
    private int numDoors;
    private String transmissionType;

    public PassengerCar(String make, String model, int year, String color, String owner,
                        int numPassengers, int numDoors, String transmissionType) {
        super(make, model, year, color, owner);
        this.numPassengers = numPassengers;
        this.numDoors = numDoors;
        this.transmissionType = transmissionType;
    }

    public boolean isComfortable() {
        return numPassengers >= 5 && numDoors >= 4 && year >= 2020;
    }

    public boolean isHardToDrive() {
        return transmissionType.equals("manual");
    }

    public static void main(String[] args) {
        PassengerCar car1 = new PassengerCar("Toyota", "Camry", 2022, "silver", "Alice Brown",
                5, 4, "automatic");
        PassengerCar car2 = new PassengerCar("Honda", "Civic", 2015, "black", "Bob Smith",
                4, 2, "manual");


        System.out.println("Car 1 is comfortable? " + car1.isComfortable());
        System.out.println("Car 2 is comfortable? " + car2.isComfortable());

        System.out.println("Car 1 is hard to drive? " + car1.isHardToDrive());
        System.out.println("Car 2 is hard to drive? " + car2.isHardToDrive());
    }

    @Override
    public String toString() {
        return super.toString() + ", Num Passengers: " + numPassengers + ", Num Doors: "
                + numDoors + ", Transmission Type: " + transmissionType;
    }
}
