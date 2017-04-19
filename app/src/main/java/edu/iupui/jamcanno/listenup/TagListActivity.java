package edu.iupui.jamcanno.listenup;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by alexc on 4/3/2017.
 */

public class TagListActivity extends SingleFragmentActivity {

    public static final String EXTRA_TAG_ID="edu/iupui/jamcanno/tag_id";

    public static Intent newIntent(Context packageContext, String id) {
        Intent intent = new Intent(packageContext, TagListActivity.class);
        intent.putExtra(EXTRA_TAG_ID, id);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new TagListFragment();

    }

}
