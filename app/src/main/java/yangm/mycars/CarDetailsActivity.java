package yangm.mycars;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class CarDetailsActivity extends AppCompatActivity {
    //global variables can be accessed throughout activity
    public final static String EXTRA_CAR_ID = "yangm.mycar.CAR_ID";
    private DataManager dm = DataManager.getDataManager();
    private Car car = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        car = Car.getTestCar();
        dm.getCarList().add(car); //add TestCar to the DataManager
        populator(car);

    }

    //populate items to the labels (set the text on labels)
    public void populator(Car car){
        TextView lblDataMake = (TextView)findViewById(R.id.lblDataMake);
        lblDataMake.setText(car.getMake());
        TextView lblDataYear = (TextView)findViewById(R.id.lblDataYear);
        lblDataYear.setText(String.valueOf(car.getYear()));
        TextView lblDataModel = (TextView)findViewById(R.id.lblDataModel);
        lblDataModel.setText(car.getModel());
        TextView lblDataOrigin = (TextView)findViewById(R.id.lblDataOrigin);
        lblDataOrigin.setText(car.getOrigin());


    }

    public void btnEditOnClick(View view){
        Intent intent = new Intent(this, CarEditActivity.class);
        intent.putExtra(EXTRA_CAR_ID, car.getCarId());
        startActivityForResult(intent, CarEditActivity.EDIT_CAR_REQUEST);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_car_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
