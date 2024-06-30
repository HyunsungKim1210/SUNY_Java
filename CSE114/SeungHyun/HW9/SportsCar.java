public class SportsCar extends Car {
    private int maxSpeed;
    private int seconds;
    private boolean isConvertible;

    public SportsCar(String make, String model, int year, String color, String owner,
                     int maxSpeed, int seconds, boolean isConvertible) {
        super(make, model, year, color, owner);
        this.maxSpeed = maxSpeed;
        this.seconds = seconds;
        this.isConvertible = isConvertible;
    }

    public boolean isSnazzy() {
        return maxSpeed > 150 && isConvertible && (color.equals("red") || color.equals("purple") || color.equals("yellow"));
    }

    public static void main(String[] args) {
        SportsCar car1 = new SportsCar("Ferrari", "488 GTB", 2023, "red", "John Doe",
                200, 4, true);
        SportsCar car2 = new SportsCar("Porsche", "911", 2018, "blue", "Jane Smith",
                180, 5, false);


        System.out.println("Car 1 is snazzy? " + car1.isSnazzy());
        System.out.println("Car 2 is snazzy? " + car2.isSnazzy());
    }

    @Override
    public String toString() {
        return super.toString() + ", Max Speed: " + maxSpeed + ", Seconds to 100 mph: " + seconds
                + ", Convertible: " + isConvertible;
    }
}

