package yangm.mycars;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Switch;

public class PreferencesActivity extends AppCompatActivity {

    PreferencesManager pm;
    CheckBox chkYear;
    CheckBox chkMake;
    RadioButton rbtnAZ;
    RadioButton rbtnZA;
    Switch swchWarnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //set up preferences screen
        pm = PreferencesManager.getInstance(this.getApplicationContext());
        chkYear = (CheckBox) findViewById(R.id.chkYear);
        chkMake = (CheckBox) findViewById(R.id.chkMake);
        rbtnAZ = (RadioButton) findViewById(R.id.rbtnAZ);
        rbtnZA = (RadioButton) findViewById(R.id.rbtnZA);
        swchWarnDelete = (Switch) findViewById(R.id.swchWarnDelete);
        chkYear.setChecked(pm.isListYear());
        chkMake.setChecked(pm.isListMake());
        if (pm.isSortAZ()) {
            rbtnAZ.setChecked(true);
        } else {
            rbtnZA.setChecked(true);
        }
        swchWarnDelete.setChecked(pm.isWarnBeforeDelete());
    }

    public void btnSaveOnClick(View view) {
        pm.setListYear(chkYear.isChecked());
        pm.setListMake(chkMake.isChecked());
        pm.setSortAZ(rbtnAZ.isChecked());
        pm.setWarnBeforeDelete(swchWarnDelete.isChecked());
        finish();
    }
}
