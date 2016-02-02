package yangm.mycars;

import android.content.Intent;
import android.os.Bundle;
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
        loadCar(car);
    }

    public void loadCar(Car car) {
        EditText txtDataMake = (EditText) findViewById(R.id.txtDataMake);
        txtDataMake.setText(car.getMake());
        EditText txtDataYear = (EditText) findViewById(R.id.txtDataYear);
        txtDataYear.setText(String.valueOf(car.getYear()));
        EditText txtDataModel = (EditText) findViewById(R.id.txtDataModel);
        txtDataModel.setText(car.getModel());
        //EditText txtDataOrigin = (EditText)findViewById(R.id.txtDataOrigin);
        //txtDataOrigin.setText(car.getOrigin());


    }

    public void save(){
        EditText txtDataMake = (EditText)findViewById(R.id.txtDataMake);
        car.setMake(String.valueOf(txtDataMake.getText()));
        EditText txtDataYear = (EditText)findViewById(R.id.txtDataYear);
        car.setYear(Integer.parseInt(String.valueOf(txtDataYear.getText())));
        EditText txtDataModel = (EditText)findViewById(R.id.txtDataModel);
        car.setModel(String.valueOf(txtDataModel.getText()));


    }

    public void btnApplyOnClick(View view) {
        save();
        finish();
    }




}
