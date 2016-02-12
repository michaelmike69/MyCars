package yangm.mycars;

import android.test.AndroidTestCase;

import junit.framework.TestCase;

/**
 * Created by Michael on 2/8/2016.
 */
public class PreferencesManagerTest extends AndroidTestCase {

    PreferencesManager pm;

    public void setUp(){
        pm = PreferencesManager.getInstance(getContext());
    }

    public void testSetListYear() throws Exception {
        pm.setListYear(false);
        assertFalse("setListYear failed", pm.isListYear());
    }

    public void testSetMake() throws Exception {
        pm.setListMake(true);
        assertTrue("setMake failed", pm.isListMake());
    }

    public void testSetSortAZ() throws Exception {
        pm.setSortAZ(false);
        assertFalse("setSortAZ failed", pm.isSortAZ());
    }

    public void testSetWarnBeforeDelete() throws Exception {
        pm.setWarnBeforeDelete(true);
        assertTrue("SetWarnBeforeDelete failed", pm.isWarnBeforeDelete());
    }

}