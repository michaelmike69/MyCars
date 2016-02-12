package yangm.mycars;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CarListActivity extends AppCompatActivity {

    public static final String CAR_ID = "carId";
    private EditText txtFilter;
    private ListView lstCar;
    ArrayAdapter<Car> lstAdapter;
    List<Car> carList;
    List<Car> filteredList;
    boolean isFiltered;
    private static Context appContext;

    /**
     * Android onCreate method.
     *
     * @param savedInstanceState the class state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appContext = this.getApplicationContext();
        setContentView(R.layout.activity_car_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //set up GUI components
        lstCar = (ListView) findViewById(R.id.lstCar);
        txtFilter = (EditText) findViewById(R.id.txtFilter);
        //set up car list
        //activityContext = this;
        DataManager dm = DataManager.getDataManager(this.getApplicationContext());
        carList = dm.getCarList();
        //get saved state
        if (savedInstanceState != null) {
            isFiltered = savedInstanceState.getBoolean("isFiltered");
        }
        //set up list view adapter
        lstAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, carList);
        lstCar.setAdapter(lstAdapter);
        //create list view click event
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Car thisPet = (Car) lstCar.getItemAtPosition(position);
                Intent intent = new Intent(CarListActivity.this, CarDetailsActivity.class);
                intent.putExtra(CAR_ID, thisPet.getCarId());
                startActivity(intent);
                clearFilter();
            }
        };
        lstCar.setOnItemClickListener(itemClickListener);
    }

    /**
     * Saves the state of the activity.
     *
     * @param outState the class state
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isFiltered", isFiltered);
    }

    /**
     * On resume, set up the list adapter and filter if was filtered.
     */
    @Override
    protected void onResume() {
        super.onResume();
        lstAdapter.notifyDataSetChanged();
        if (isFiltered) {
            filterList();
        }
    }

    public static Context getContext() {
        return appContext;
    }

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
    /**
     * Event handler for Filter button click event.
     *
     * @param view the Filter button
     */
    public void btnFilterOnClick(View view) {
        //hide keyboard
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(
                (null == getCurrentFocus()) ? null : getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        //handle filter request
        if (txtFilter.getText().toString().isEmpty()) {
            clearFilter();
            isFiltered = false;
        } else {
            isFiltered = true;
            filterList();
        }
    }

    /**
     * Filters the list based on user selection.
     */
    public void filterList() {
        String filter = txtFilter.getText().toString().toLowerCase();
        //filter list
        filteredList = new ArrayList<>();
        for (Car p : carList) {
            if ((p.getMake().toLowerCase().contains(filter))
                    || (p.getModel().toLowerCase().contains(filter)))
                    //|| (p.getYear().toLowerCase().contains(filter)))
            {
                filteredList.add(p);
            }
        }
        lstAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, filteredList);
        lstCar.setAdapter(lstAdapter);
        lstCar.destroyDrawingCache();
        lstCar.setVisibility(View.INVISIBLE);
        lstCar.setVisibility(View.VISIBLE);
    }

    //Event handler for Details button click event
    public void btnDetailsOnClick(View view) {
        ListView lstCar = (ListView) findViewById(R.id.lstCar);
        Car thisCar = (Car)lstCar.getSelectedItem();
        Intent intent = new Intent(this, CarDetailsActivity.class);
        intent.putExtra(CAR_ID, thisCar.getCarId());
        startActivity(intent);
    }

    public void btnAddCarOnClick(View view) {
        Intent intent = new Intent(this, CarEditActivity.class);
        startActivity(intent);
        clearFilter();
    }

    /**
     * Event handler for Cancel button click event
     */
    public void btnCancelOnClick(View view) {
        finish();
    }

    /**
     * Clears the list filter.
     */
    public void clearFilter() {
        lstAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, carList);
        lstCar.setAdapter(lstAdapter);
        lstCar.destroyDrawingCache();
        lstCar.setVisibility(View.INVISIBLE);
        lstCar.setVisibility(View.VISIBLE);
    }

}
