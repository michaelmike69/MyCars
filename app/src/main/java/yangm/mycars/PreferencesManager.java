package yangm.mycars;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Michael on 2/7/2016.
 */
public class PreferencesManager {

    private static PreferencesManager pm;
    private boolean listYear;
    private boolean listMake;
    private boolean sortAZ;
    private boolean warnBeforeDelete;
    private final SharedPreferences PREFS;
    private String justify;

    /**
     * Singleton pattern preferences manager instance.
     *
     * @param context the application context
     * @return the single instance
     */
    public static PreferencesManager getInstance(Context context) {
        if (pm == null) {
            pm = new PreferencesManager(context);
        }
        return pm;
    }

    /**
     * private constructor
     *
     * @param ctx the application context
     */
    private PreferencesManager(Context ctx) {
        PREFS = ctx.getSharedPreferences("yangm.mycars",Context.MODE_PRIVATE);
        justify = PREFS.getString("justify", "left");
        listYear = PREFS.getBoolean("listYear", true);
        Car.listYear = listYear;
        listMake = PREFS.getBoolean("listMake", false);
        Car.listMake = listMake;
        sortAZ = PREFS.getBoolean("sortAZ", true);
        warnBeforeDelete = PREFS.getBoolean("warnBeforeDelete", false);
    }

    public boolean isListYear() {
        return listYear;
    }

    public void setListYear(boolean listYear) {
        this.listYear = listYear;
        PREFS.edit().putBoolean("listYear", listYear).apply();
        Car.listYear = listYear;
    }

    public boolean isListMake() {
        return listMake;
    }

    public void setListMake(boolean listClient) {
        this.listMake = listMake;
        PREFS.edit().putBoolean("listMake", listMake).apply();
        Car.listMake = listMake;
    }

    public boolean isSortAZ() {
        return sortAZ;
    }

    public void setSortAZ(boolean sortAZ) {
        this.sortAZ = sortAZ;
        PREFS.edit().putBoolean("sortAZ", sortAZ).apply();
    }

    public boolean isWarnBeforeDelete() {
        return warnBeforeDelete;
    }

    public void setWarnBeforeDelete(boolean warnBeforeDelete) {
        this.warnBeforeDelete = warnBeforeDelete;
        PREFS.edit().putBoolean("warnBeforeDelete", warnBeforeDelete).apply();
    }

    public String getJustify() {
        return justify;
    }
    public void setJustify(String justify) {
        this.justify = justify;
        PREFS.edit().putString(
                "justify", justify).apply();
    }
}
