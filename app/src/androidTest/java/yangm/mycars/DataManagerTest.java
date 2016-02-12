package yangm.mycars;

import android.content.Context;
import android.test.AndroidTestCase;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 2/7/2016.
 */


public class DataManagerTest extends AndroidTestCase {

    DataManager dm;

    @Test
    public void testGetItemList() throws Exception {
        List<Car> list = dm.getCarList();
        assertNotNull("null returned", list);
        assertTrue("size returned != 2", list.size() == 2);
        System.out.println("textValidGetItem - PASSED");
    }

    public void setUp() throws Exception {
        super.setUp();
        Context context = getContext();
        dm = DataManager.getDataManager(getContext());
    }

    public void testGetCarList() throws Exception {
        List<Car> list = dm.getCarList();
        assertNotNull("getCarList failed", list);
    }

    public void testValidGetCar() throws Exception {
        Car car = new Car("Toyota", 1986, "Cressida", "Japanese");
        dm.addCar(car);
        Car p = dm.getCar(car.getCarId());
        assertNotNull("getCar failed", p);
    }

    public void testInvalidGetCar() throws Exception {
        Car p10 = dm.getCar(10);
        assertNull("getCar failed", p10);
    }

    public void testValidAddCar() throws Exception {
        int size1 = dm.getCarList().size();
        Car p1 = new Car("Toyota", 1986, "Cressida", "Japanese");
        dm.addCar(p1);
        int size2 = dm.getCarList().size();
        assertTrue("addCar failed", size2 - size1 == 1);
    }

    public void testInvalidAddCar() throws Exception {
        int size1 = dm.getCarList().size();
        Car p1 = null;
        dm.addCar(p1);
        int size2 = dm.getCarList().size();
        assertTrue("addCar failed", size2 - size1 == 0);
    }


    public void testValidDeleteCar() throws Exception {
        int size1 = dm.getCarList().size();
        dm.deleteCar(0);
        int size2 = dm.getCarList().size();
        assertTrue("deleteCar failed", size1 - size2 == 1);
    }

    public void testInvalidDeleteCar() throws Exception {
        int size1 = dm.getCarList().size();
        dm.deleteCar(10);
        int size2 = dm.getCarList().size();
        assertTrue("deleteCar failed", size1 - size2 == 0);


    }

}
