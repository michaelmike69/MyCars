package yangm.mycars;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Michael on 1/24/2016.
 */
public class DataManager {

    private static DataManager dm;
    private List<Car> carList;
    private int addCarId;
    private PreferencesManager pm;

    /*
     * Private constructor that will eventually be populated
     * from a data store.
     *
     * @param context the application context
     */
    private DataManager(Context context) {
        pm = PreferencesManager.getInstance(context);
        carList = new ArrayList<>();
        Car testCar = Car.getTestCar();
        addCar(testCar);
    }

    /**
     * Singleton implementation - returns the single instance of
     * the DataManager class.
     *
     * @param context the application context
     */
    public static DataManager getDataManager(Context context) {
        if (dm == null) {
            dm = new DataManager(context);
        }
        return dm;
    }

    /**
     * Provides access to a sorted list of all cars.
     * Sorts in reverse if user preferences require it.
     *
     * @return List<Car> - the list of cars
     */
    public List<Car> getCarList() {
        Collections.sort(carList);
        if (!pm.isSortAZ()) {
            Collections.reverse(carList);
        }
        return carList;
    }

    /**
     * Provides access to one car.
     *
     * @param id the car's id number
     * @return the Car object
     */
    public Car getCar(int id) {
        //search for id
        int index = -1;
        for (int i = 0; i < carList.size(); i++) {
            Car p = carList.get(i);
            if (p.getCarId() == id) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            return carList.get(index);
        }
        else {
            return null;
        }
    }

    /*
     *Adds a Car object to the list of cars, maintaining sorted order
     *
     * @param newCar the new Car object
     */
    public void addCar(Car newCar) {
        if (newCar == null) {
            return;
        }
        newCar.setCarId((addCarId));
        carList.add(newCar);
        addCarId++;
        Collections.sort(carList);
        if (!pm.isSortAZ()) {
            Collections.reverse(carList);
        }
    }

    /**
     * Deletes the given pet from the list.
     *
     * @param id the id of the pet to delete
     */
    public void deleteCar(int id) {
        int index = -1;
        //find car in list
        for (int i = 0; i<carList.size(); i++) {
            Car p = carList.get(i);
            if (p.getCarId() == id) {
                index = i;
                break;
            }
        }
        //if found
        if (index >= 0) {
            //delete
            carList.remove(index);
        }
    }

}
