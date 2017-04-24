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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import edu.iupui.jamcanno.listenup.model.Tag;
import edu.iupui.jamcanno.listenup.model.Podcast;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexc on 4/3/2017.
 */

public class TagListFragment extends Fragment {

    private RecyclerView mTagListRecyclerView;
    private TagAdapter mTagAdapter;
    private List<Tag> mAPITags;
    public ProgressBar mProgressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //INFLATE LAYOUT FILE
        final View view = inflater.inflate(R.layout.fragment_taglist, container, false);

        //WIRE UP RECYCLER VIEW
        mTagListRecyclerView = (RecyclerView) view.findViewById(R.id.taglist_recycler_view);
        mTagListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mProgressBar = (ProgressBar) view.findViewById(R.id.taglist_progress);

        //MAKE CONNECTION TO THE API
        GPodderAPI client = ServiceGenerator.createService(GPodderAPI.class);

        //GET LIST OF TAG FROM API
        Call<Tag[]> call = client.getTopTags("10");

        call.enqueue(new Callback<Tag[]>() {
            @Override
            public void onResponse(Call<Tag[]> call, Response<Tag[]> response) {
                Log.d("LISTENUP", "Success!");
                Tag[] tags = response.body();
                Log.d("LISTENUP", String.valueOf(tags.length));

                updateUI(tags);
                mProgressBar.setVisibility(view.GONE);
                mTagListRecyclerView.setVisibility(view.VISIBLE);
                mTagAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<Tag[]> call, Throwable t) {
                Log.d("LISTENUP", "womp womp");
            }
        });

        return view;
    }

    private void updateUI(Tag[] tags) {

        mTagAdapter = new TagAdapter(Arrays.asList(tags));
        mTagListRecyclerView.setAdapter(mTagAdapter);

    }

    private class TagListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Tag mTag;
        public TextView mTagTextView;

        public TagListHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTagTextView = (TextView) itemView;
        }

        public void bindTag(Tag tag) {
            mTag = tag;
            this.mTagTextView.setText(tag.getTag());
        }

        @Override
        public void onClick(View v) {

            Intent intent = PodcastListActivity.newIntent(getActivity(), mTag.getTag() );
            startActivity(intent);

            Toast.makeText(getActivity(), mTag.getTag() + " clicked!", Toast.LENGTH_SHORT).show();
        }
    }

    private class TagAdapter extends RecyclerView.Adapter<TagListHolder> {

        private List<Tag> mTagList;

        public TagAdapter(List<Tag> allTags) {
            mTagList = allTags;
        }

        @Override
        public TagListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new TagListHolder(view);
        }

        @Override
        public void onBindViewHolder(TagListHolder holder, int position) {
            Tag thisTag = mTagList.get(position);
            holder.mTagTextView.setText(thisTag.getTag());
            holder.bindTag(thisTag);

        }

        @Override
        public int getItemCount() {
            return mTagList.size();
        }



    }
}