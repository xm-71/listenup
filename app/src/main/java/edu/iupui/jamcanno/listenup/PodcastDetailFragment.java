package edu.iupui.jamcanno.listenup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import edu.iupui.jamcanno.listenup.model.Tag;
import edu.iupui.jamcanno.listenup.model.Podcast;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




/**
 * Created by alexc on 4/10/2017.
 */

public class PodcastDetailFragment extends Fragment {

    private static final String ARG_PODCAST_ID = "podcast_id";

    private Podcast mPodcast;
    private TextView mPodcastTitle;
    private TextView mPodcastDescription;
    private ImageView mPodcastLogo;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final Podcast PodcastVariable = (Podcast) getArguments().getSerializable(ARG_PODCAST_ID);

        //INFLATE LAYOUT FILE
        View view = inflater.inflate(R.layout.fragment_podcast_detail, container, false);

        mPodcast = PodcastVariable;
        mPodcastTitle = (TextView) view.findViewById(R.id.title_detail_textview);
        mPodcastDescription = (TextView) view.findViewById(R.id.description_detail_textview);
        mPodcastLogo = (ImageView) view.findViewById(R.id.mdetailthumbnail);

        mPodcastTitle.setText(mPodcast.getTitle());
        mPodcastDescription.setText(mPodcast.getDescription());

        String uri = mPodcast.getLogo_url();
        Glide.with(getContext()).load(uri).into(mPodcastLogo);


        return view;

    }


    public static PodcastDetailFragment newInstance(Podcast PodcastId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_PODCAST_ID, PodcastId);

        PodcastDetailFragment fragment = new PodcastDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


}