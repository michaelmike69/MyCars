package yangm.mycars;

import java.util.ArrayList;

/**
 * Created by Michael on 1/24/2016.
 */
public class DataManager {

    private static DataManager dm;
    private ArrayList<Car> carList;

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
}
