package edu.iupui.jamcanno.listenup;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import edu.iupui.jamcanno.listenup.model.Podcast;

/**
 * Created by alexc on 4/10/2017.
 */

public class PodcastDetailActivity extends SingleFragmentActivity{

    public static final String EXTRA_TAG_ID = "edu/iupui/strissle/listenup.tag_id";

    @Override
    protected Fragment createFragment() {
        Podcast SelectedPodcast = (Podcast) getIntent().getSerializableExtra(EXTRA_TAG_ID);
        return new PodcastDetailFragment().newInstance(SelectedPodcast);

    }

    public static Intent newIntent(Context packageContext, Podcast PODCAST_ID) {
        Intent intent = new Intent(packageContext, PodcastDetailActivity.class);
        intent.putExtra(EXTRA_TAG_ID, PODCAST_ID);
        return intent;
    }
}
