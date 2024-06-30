public class SportsCar extends Car{
    private int maxSpeed;
    private int seconds;
    private boolean isConvertible;

public SportsCar(String make, String model, int year, String color, String owner, int maxSpeed, int seconds, boolean isConvertible) {
    super(make, model, year, color, owner);
    this.maxSpeed = maxSpeed;
    this.seconds = seconds;
    this.isConvertible = isConvertible;
    }
public boolean isSnazzy() {//괄호
    return (maxSpeed > 150 && isConvertible && (color.equals("red")) || (color.equals("purple")) || (color.equals("yellow")) );
}
public static void main(String[] args) {
    SportsCar car1 = new SportsCar("Lamborghini", "488", 2023, "red", "Lam Bo", 300, 3, true);
    SportsCar car2 = new SportsCar("Porsche", "GT4", 2020, "black", "Bao Pu", 215, 3, false);

    System.out.println("Car1 Snazzy: " + car1.isSnazzy());
    System.out.println("Car2 Snazzy: " + car2.isSnazzy());
}

} 
