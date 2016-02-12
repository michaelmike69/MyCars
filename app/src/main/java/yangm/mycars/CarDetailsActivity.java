package yangm.mycars;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class CarDetailsActivity extends AppCompatActivity {
    //global variables can be accessed throughout activity
    private int carId;
    private DataManager dm;
    private Car thisCar;
    private PreferencesManager pm;


    /**
     * Android onCreate method.
     *
     * @param savedInstanceState the class state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("LOG", "CarDetailsActivity::onCreate");
        setContentView(R.layout.activity_car_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //get current car
        dm = DataManager.getDataManager(this.getApplicationContext());
        Intent intent = getIntent();
        carId = intent.getIntExtra(CarListActivity.CAR_ID, -1);
        if (carId >= 0) {
            thisCar = dm.getCar(carId);
        } else {
            finish();
        }
    }

    /**
     * On resume, reset all of the cars data.
     */
    @Override
    protected void onResume() {
        super.onResume();
        //Make
        TextView lblDataMake = (TextView) findViewById(R.id.lblDataMake);
        lblDataMake.setText(thisCar.getMake());
        //Year
        TextView lblDataYear = (TextView) findViewById(R.id.lblDataYear);
        lblDataYear.setText(String.valueOf(thisCar.getYear()));
        //Model
        TextView lblDataModel = (TextView) findViewById(R.id.lblDataModel);
        lblDataModel.setText(thisCar.getModel());
        /*//Origin
        RadioButton rbtnFemale = (RadioButton)findViewById(R.id.rbtnFemale);
        RadioButton rbtnMale = (RadioButton)findViewById(R.id.rbtnMale);
        if (thisCar.isFemale()) {
            rbtnFemale.setChecked(true);
        } else {
            rbtnMale.setChecked(true);
        }*/
        /*//photo
        ImageView imgPhoto = (ImageView) findViewById(R.id.imgPhoto);
        if (thisPet.getPhoto() != null) {
            imgPhoto.setImageBitmap(thisPet.getPhoto());
            imgPhoto.setScaleType(ImageView.ScaleType.FIT_CENTER);*/
        }

    public void btnEditOnClick(View view) {
        //CarEditActivity edit = new CarEditActivity();
        Intent intent = new Intent(this, CarEditActivity.class);
        //intent.putExtra(EXTRA_CAR_ID, car.getCarId());
        startActivity(intent);
        //edit.setVisible(true);
        /*Intent intent = new Intent(this, CarEditActivity.class);
        intent.putExtra(EXTRA_CAR_ID, car.getCarId());
        startActivityForResult(intent, CarEditActivity.EDIT_CAR_REQUEST);

        if (intent.hasExtra(CarListActivity.CAR_ID)) {
            carId = intent.getIntExtra(CarListActivity.CAR_ID, -1);
        } else
            finish();

        if (carId >= 0) {
            thisCar = dm.getCar(carId);
        } else {
            finish();
        }

        if (thisCar == null)
            finish();*/
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
            Intent intent = new Intent(this, PreferencesActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void btnBackOnClick(View view) {
        finish();
    }

    /**
     * Event handler for one of the option buttons click event.
     *
     * @param view the option button that was clicked
     */
    public void btnOptionOnClick(View view) {
        int id = view.getId();
        if (id == R.id.btnAdd) {
            //add a new pet
            Intent intent = new Intent(this, CarEditActivity.class);
            startActivity(intent);
        } else if (id == R.id.btnEdit) {
            //edit the current pet
            Intent intent = new Intent(this, CarEditActivity.class);
            intent.putExtra(CarListActivity.CAR_ID, carId);
            startActivity(intent);
        } else if (id == R.id.btnDelete){
            //delete the current pet
            if (pm.isWarnBeforeDelete()) {
                new AlertDialog.Builder(this)
                        .setTitle("Confirm")
                        .setMessage("Are you sure you want to delete this pet?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dm.deleteCar(carId);
                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            } else {
                dm.deleteCar(carId);
            }
        } else {
            finish();
        }
    }

}



    /*
    Event handler for Select button click event
    */

    /*public void btnEditOnClick(View view) {
        //Spinner spnPetOptions = (Spinner)findViewById(R.id.spnPetOptions);
        //int index = spnPetOptions.getSelectedItemPosition();
        if (index == 2) {
            //add a new car
            Intent intent = new Intent(this, CarEditActivity.class);
            startActivity(intent);
            finish();
        } else if (index == 0) {
            //edit the current car
            Intent intent = new Intent(this, CarEditActivity.class);
            intent.putExtra(CarEditActivity.CAR_ID, carId);
            startActivity(intent);
            finish();
        } else {
            //delete the current car
            dm.deleteCar(carId);
            finish();
        }
    }
}*/
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == CarEditActivity.EDIT_CAR_REQUEST){
//            if(resultCode == RESULT_OK){
//                if(data.hasExtra(EXTRA_CAR_ID)){
//                    car = dm.getCar(data.getIntExtra(EXTRA_CAR_ID))-1;
//                }
//            }
//        }
//    }
//}
