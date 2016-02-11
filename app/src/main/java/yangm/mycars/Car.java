package yangm.mycars;

import android.graphics.Bitmap;

/**
 * Created by Michael on 1/21/2016.
 */
public class Car implements Comparable<Car> {

    private String make;
    private int year;
    private String model;
    private String origin;
    private int carId;
    private Bitmap photo;
    public static boolean listYear;
    public static boolean listMake;

    public Car() {
        //carId = count++;

    }

    public Car(String make, int year, String model, String origin) {
        this.make = make;
        this.year = year;
        this.model = model;
        this.origin = origin;
        //carId = count++;
    }
    public static Car getTestCar(){
        Car p = new Car();
        p.carId = 0;
        p.make = "Toyota";
        p.year = 1986;
        p.model = "Cressida";
        p.origin = "Japanese";
        return p;
    }

   /* public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
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
    }*/



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car other = (Car) o;
        return (carId == other.carId);
/*
        if (year != car.year) return false;
        if (carId != car.carId) return false;
        if (make != null ? !make.equals(car.make) : car.make != null) return false;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;
        return !(origin != null ? !origin.equals(car.origin) : car.origin != null);*/

    }

    @Override
    public int hashCode() {
        int result = make.hashCode();
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
        return this.toString().compareTo(another.toString());
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
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
}
