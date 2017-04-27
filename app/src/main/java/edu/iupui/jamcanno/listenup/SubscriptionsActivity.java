package edu.iupui.jamcanno.listenup;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by alexc on 4/27/2017.
 */

public class SubscriptionsActivity extends SingleFragmentActivity {

    public static final String EXTRA_SUB_ID="edu/iupui/jamcanno/sub_id";

    public static Intent newIntent(Context packageContext, String id) {
        Intent intent = new Intent(packageContext, SubscriptionsActivity.class);
        intent.putExtra(EXTRA_SUB_ID, id);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new SubscriptionsFragment();

    }

}