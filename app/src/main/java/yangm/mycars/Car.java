package yangm.mycars;

/**
 * Created by Michael on 1/21/2016.
 */
public class Car implements Comparable<Car> {
    private String make;
    private int year;
    private String model;
    private String origin;
    private static int count;
    private int carId;

    public Car() {
        carId = count++;

    }

    public Car(String make, int year, String model, String origin) {
        this.make = make;
        this.year = year;
        this.model = model;
        this.origin = origin;
        carId = count++;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public static Car getTestCar(){
        Car car = new Car("Toyota", 1986, "Cressida", "Japanese");
        return car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (year != car.year) return false;
        if (carId != car.carId) return false;
        if (make != null ? !make.equals(car.make) : car.make != null) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;
        return !(origin != null ? !origin.equals(car.origin) : car.origin != null);

    }

    @Override
    public int hashCode() {
        int result = make != null ? make.hashCode() : 0;
        result = 31 * result + year;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + carId;
        return result;
    }

    @Override
    public String toString() {
        return year + " " + make + " " + model;
    }

    @Override
    public int compareTo(Car another) {
        return this.make.compareTo(another.make);
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
}
