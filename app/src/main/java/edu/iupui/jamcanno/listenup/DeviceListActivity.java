package edu.iupui.jamcanno.listenup;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by alexc on 4/24/2017.
 */

public class DeviceListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment(){

        return new DeviceListFragment().newInstance();
    }

    public static Intent newIntent(Context packageContext) {

        Intent intent = new Intent(packageContext, DeviceListActivity.class);
        return intent;
    }

}
