package yangm.mycars;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class CarEditActivity extends AppCompatActivity {
    public final static int EDIT_CAR_REQUEST = 2;
    private final static String EXTRA_CAR_ID = "yangm.mycars.CAR_ID";
    private DataManager dm = DataManager.getDataManager();
    private Car car = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        //if the intent has the carID then it looks for the car in DataManager and assigns it to the
        //car
        if (intent.hasExtra(EXTRA_CAR_ID)) {
            car = dm.getCarList().get(intent.getIntExtra(EXTRA_CAR_ID, -1));
        }
        //If it doesn't find the carID, then we create a car and add it to the DataManager
        else {
            if (null == car) {
                car = Car.getTestCar();
                dm.getCarList().add(car);
            }
        }
        populate(car);
    }

    public void populate(Car car) {
        EditText txtDataMake = (EditText) findViewById(R.id.txtDataMake);
        txtDataMake.setText(car.getMake());
        EditText txtDataYear = (EditText) findViewById(R.id.txtDataYear);
        txtDataYear.setText(String.valueOf(car.getYear()));
        EditText txtDataModel = (EditText) findViewById(R.id.txtDataModel);
        txtDataModel.setText(car.getModel());
        //EditText txtDataOrigin = (EditText)findViewById(R.id.txtDataOrigin);
        //txtDataOrigin.setText(car.getOrigin());


    }

}
