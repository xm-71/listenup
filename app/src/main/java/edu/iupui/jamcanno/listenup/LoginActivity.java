package edu.iupui.jamcanno.listenup;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by alexc on 4/26/2017.
 */

public class LoginActivity extends SingleFragmentActivity{

        @Override
        protected Fragment createFragment(){

            return new LoginFragment().newInstance();
        }

//    public static Intent newIntent(Context packageContext) {
//
//        Intent intent = new Intent(packageContext, DeviceListActivity.class);
//        return intent;
//    }

}
