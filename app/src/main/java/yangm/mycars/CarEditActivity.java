package yangm.mycars;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class CarEditActivity extends AppCompatActivity {

    private DataManager dm;
    private int carId;
    private int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap newPhoto;

    /**
     * Android onCreate method.
     *
     * @param savedInstanceState the class state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("LOG", "CarEditActivity::onCreate");
        setContentView(R.layout.activity_car_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //final ImageButton btnPhoto = (ImageButton) findViewById(R.id.imgPhoto);
        //get car info
        dm = DataManager.getDataManager(this.getApplicationContext());
        Intent intent = getIntent();
        carId = intent.getIntExtra(CarListActivity.CAR_ID, -1);
        setTitle(R.string.title_activity_car_details);
        if (carId >= 0) {
            Car thisCar = dm.getCar(carId);
            TextView btnApply = (TextView) findViewById(R.id.btnApply);
            btnApply.setText(getResources().getString(R.string.btnApplyStr));
            //Make
            EditText txtDataMake = (EditText) findViewById(R.id.txtDataMake);
            txtDataMake.setText(thisCar.getMake());
            //Year
            EditText txtDataYear = (EditText) findViewById(R.id.txtDataYear);
            txtDataYear.setText(String.valueOf(thisCar.getYear()));
            //Model
            EditText txtDataModel = (EditText) findViewById(R.id.txtDataModel);
            txtDataModel.setText(thisCar.getModel());
        /*} else {
            setTitle(R.string.title_a);*/
        }
        /*btnPhoto.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(CarEditActivity.this)
                        .setTitle("Confirm")
                        .setMessage("Are you sure you want to delete this photo?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                btnPhoto.setImageDrawable(ContextCompat.getDrawable(
                                        CarEditActivity.this, R.drawable.camera));
                                btnPhoto.setScaleType(ImageView.ScaleType.CENTER);
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .show();
                return true;
            }
        });*/
    }

    /**
     * Saves the class state.
     *
     * @param outState the class state
     */
    @Override
    protected void onSaveInstanceState (Bundle outState){
        super.onSaveInstanceState(outState);
        if (newPhoto != null) {
            outState.putParcelable("newPhoto", newPhoto);
        }
    }

    /**
     * Creates the top menu.
     *
     * @param menu the top menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_car_details, menu);
        return true;
    }

    /**
     * Handles the top menu item selection.
     *
     * @param item the item selected
     * @return true or false
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, PreferencesActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Event handler for Save button click event.
     *
     * @param view the save button
     */
    public void btnApplyOnClick(View view) {
        Car p;
        boolean add = false;
        if (carId > -1) {
            p = dm.getCar(carId);
        } else {
            p = new Car();
            add = true;
        }
        //Make
        EditText txtDataMake = (EditText) findViewById(R.id.txtDataMake);
        p.setMake(txtDataMake.getText().toString());
        //Year
        EditText txtDataYear = (EditText) findViewById(R.id.txtDataYear);
        p.setYear(Integer.valueOf(txtDataYear.getText().toString()));
        //Model
        EditText txtDataModel = (EditText) findViewById(R.id.txtDataModel);
        p.setModel(txtDataModel.getText().toString());
        //gender
        /*RadioButton rbtnFemale = (RadioButton) findViewById(R.id.rbtnFemale);
        if (rbtnFemale.isChecked()) {
            p.setFemale(true);
        } else {
            p.setFemale(false);
        }*/
        /*//photo
        p.setPhoto(newPhoto);
        //care
        EditText txtCare = (EditText) findViewById(R.id.txtCare);
        p.setCare(txtCare.getText().toString());
        //client
        EditText txtClient = (EditText) findViewById(R.id.txtClient);
        p.setClient(txtClient.getText().toString());
        //vet
        EditText txtVet = (EditText) findViewById(R.id.txtVet);
        p.setVet(txtVet.getText().toString());
        //save to list
        if (add) {
            dm.addPet(p);
            petId = p.getPetId();
        }*/
        finish();
    }


    @Override
    protected void onDestroy () {
        super.onDestroy();
    }

    /**
     * Event handler for Cancel button click event.
     *
     * @param view the cancel button
     */
    public void btnCancelOnClick(View view) {
        finish();
    }

    /**
     * Event handler for the photo button click event.
     *
     * @param view the photo button
     */
    public void btnPhotoOnClick(View view) {
        dispatchTakePictureIntent();
    }

    /**
     * Sends control to a camera app to get a thumbnail.
     */
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    /**
     * Gets the thumbnail returned from the camera app.
     *
     * @param requestCode the code sent to identify the reply
     * @param resultCode the code that indicates if the camera has an image to return
     * @param data the bitmap of the photo
     */
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            newPhoto = (Bitmap) extras.get("data");
            ImageButton btnPhoto = (ImageButton) findViewById(R.id.imgPhoto);
            btnPhoto.setImageBitmap(newPhoto);
            btnPhoto.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
    }*/

}



