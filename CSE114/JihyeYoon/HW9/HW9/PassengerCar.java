//Jihye Yoon_115960721_jihye.yoon@stonybrook.edu

public class PassengerCar extends Car {
    private int numPassengers;
    private int numDoors;
    private String transmissionType;

    public PassengerCar(String make, String model, int year, String color, String owner, int numPassengers, int numDoors, String transmissionType) {
        super(make, model, year, color, owner);
        this.numPassengers = numPassengers;
        this.numDoors = numDoors;
        this.transmissionType = transmissionType;
    }
    public boolean isComfortable() {
         return ((numPassengers >= 5) && (numDoors >= 4) && (2024 - year) <= 5);
    }
    public boolean isHardToDrive() {
        return "manual".equals(transmissionType);
    }
    public int getNumPassengers() {
        return numPassengers;
    }

    public int getNumDoors() {
        return numDoors;
    }

    public String getTransmissionType(){
        return transmissionType;
    }
    public static void main(String[] args) {
        PassengerCar car1 = new PassengerCar("KIA", "Mohave", 2019, "Black", "Jaeyong Lee", 7, 4, "automatic");
        PassengerCar car2 = new PassengerCar("HYUNDAI", "Venu", 2020, "Blue", "Daewoo Kim", 5, 4, "manual");

        System.out.println("Car1 Comfortable: " + car1.isComfortable());
        System.out.println("Car1 Hard to Drive: " + car1.isHardToDrive());
        System.out.println("Car2 Comfortable: " + car2.isComfortable());
        System.out.println("Car2 Hard to Drive: " + car2.isHardToDrive());
    }

}
