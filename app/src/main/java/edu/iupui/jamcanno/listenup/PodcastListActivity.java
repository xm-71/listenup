package edu.iupui.jamcanno.listenup;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by alexc on 4/10/2017.
 */

public class PodcastListActivity extends SingleFragmentActivity {
    public static final String EXTRA_TAG_ID = "edu/iupui/jamcanno/listenup.tag_id";

    @Override
    protected Fragment createFragment() {
        String SelectedTag = (String) getIntent().getSerializableExtra(EXTRA_TAG_ID);
  return new PodcastListFragment().newInstance(SelectedTag);

        }

public static Intent newIntent(Context packageContext, String title) {
        Intent intent = new Intent(packageContext, PodcastListActivity.class);
        intent.putExtra(EXTRA_TAG_ID, title);
        return intent;
        }
        }
