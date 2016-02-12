package yangm.mycars;

import java.util.ArrayList;

/**
 * Created by Michael on 1/24/2016.
 */
public class DataManager {

    private static DataManager dm;
    private ArrayList<Car> carList;
    private int addCarId;

    private DataManager() {
        carList = new ArrayList<>();
    }
//if DataManager doesn't exist, create it
    public static DataManager getDataManager(){
        if (null == dm)
            dm = new DataManager();
        return dm;
    }

    public ArrayList<Car> getCarList() {
        return carList;
    }

    public Car getCar(int carId){
        int i = 0;
        int bounds = carList.size();
        while(carList.get(i).getCarId() != carId) {
            i++;
            if(i >= bounds){
                return null;
            }
        }
        return carList.get(i);
    }
}
