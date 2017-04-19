package edu.iupui.jamcanno.listenup;

import android.content.Intent;
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

import java.util.Arrays;
import java.util.List;

import edu.iupui.jamcanno.listenup.model.Podcast;
import edu.iupui.jamcanno.listenup.model.Tag;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.attr.tag;
import static edu.iupui.jamcanno.listenup.TagListActivity.EXTRA_TAG_ID;
import static edu.iupui.jamcanno.listenup.R.id.mThumbnail;

/**
 * Created by alexc on 4/10/2017.
 */

public class PodcastListFragment extends Fragment {

    private static final String ARG_TAG_ID = "tag_id";

    private RecyclerView mPodcastRecyclerView;
    private PodcastListAdapter mPodcastListAdapter;
    private List<Podcast> mAPIPodcasts;
    private ImageView mThumbnailImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //INFLATE LAYOUT FILE
        View view = inflater.inflate(R.layout.fragment_podcastlist, container, false);

        //WIRE UP RECYCLER VIEW
        mPodcastRecyclerView = (RecyclerView) view.findViewById(R.id.podcastlist_recycler_view);
        mPodcastRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mThumbnailImageView = (ImageView) view.findViewById(R.id.mThumbnail);

        //MAKE CONNECTION TO THE API
        GPodderAPI client = ServiceGenerator.createService(GPodderAPI.class);
        String tag_id = (String) getArguments().getSerializable(ARG_TAG_ID);

        //GET LIST OF PODCASTS FROM API
        Call<Podcast[]> call = client.getTaggedPodcasts(tag_id, "20");

        call.enqueue(new Callback<Podcast[]>() {
            @Override
            public void onResponse(Call<Podcast[]> call, Response<Podcast[]> response) {
                Log.d("LISTENUP", "Success!");
                Podcast[] podcasts = response.body();
                Log.d("LISTENUP", String.valueOf(podcasts.length));

                updateUI(podcasts);

            }

            @Override
            public void onFailure(Call<Podcast[]> call, Throwable t) {
                Log.d("LISTENUP", "womp womp");
            }
        });

        return view;
    }

    private void updateUI(Podcast[] podcasts) {

        mPodcastListAdapter = new PodcastListFragment.PodcastListAdapter(Arrays.asList(podcasts));
        mPodcastRecyclerView.setAdapter(mPodcastListAdapter);
    }


    private class PodcastListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Podcast mPodcast;
        public TextView mPodcastTextView;
        public ImageView mThumbnail;

        public PodcastListHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mPodcastTextView = (TextView) itemView.findViewById(R.id.podcastTitle);
            mThumbnail = (ImageView) itemView.findViewById(R.id.mThumbnail);
        }

        public void bindPodcast(Podcast podcast) {
            mPodcast = podcast;
            this.mPodcastTextView.setText(podcast.getTitle());

            String uri = podcast.getLogo_url();



            Glide.with(getContext()).load(uri).into(this.mThumbnail);

        }

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), mPodcast.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();

            Intent intent = PodcastDetailActivity.newIntent(getActivity(), mPodcast);
            startActivity(intent);
        }
    }

    private class PodcastListAdapter extends RecyclerView.Adapter<PodcastListHolder> {

        private List<Podcast> mPodcastList;

        public PodcastListAdapter(List<Podcast> allPodcasts) {
            mPodcastList = allPodcasts;
        }

        @Override
        public PodcastListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_podcast, parent, false);
            return new PodcastListFragment.PodcastListHolder(view);
        }

        @Override
        public void onBindViewHolder(PodcastListFragment.PodcastListHolder holder, int position) {
            Podcast thisPodcast = mPodcastList.get(position);
            holder.mPodcastTextView.setText(thisPodcast.getTitle());
            holder.bindPodcast(thisPodcast);




        }

        @Override
        public int getItemCount() {
            return mPodcastList.size();
        }

    }

    public static PodcastListFragment newInstance(String TagId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TAG_ID, TagId);

        PodcastListFragment fragment = new PodcastListFragment();
        fragment.setArguments(args);
        return fragment;
    }






}

