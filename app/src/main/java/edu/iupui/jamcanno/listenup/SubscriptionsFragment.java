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
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import edu.iupui.jamcanno.listenup.model.Subscriptions;
import edu.iupui.jamcanno.listenup.model.Tag;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexc on 4/27/2017.
 */

public class SubscriptionsFragment extends Fragment {


    private RecyclerView mSubscriptionsRecyclerView;
    private SubscriptionsAdapter mSubscriptionsAdapter;
    private List<Subscriptions[]> mAPISubscriptions;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //INFLATE LAYOUT FILE
        final View view = inflater.inflate(R.layout.fragment_taglist, container, false);

        //WIRE UP RECYCLER VIEW
        mSubscriptionsRecyclerView = (RecyclerView) view.findViewById(R.id.subscriptions_recycler_view);
        mSubscriptionsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        GPodderAPI client = ServiceGenerator.createService(GPodderAPI.class);

        Call<String> call = client.getSubscriptions("xm71");

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d("LISTENUP", "Success!");
                Log.d("LISTENUP", String.valueOf(response.code()) + String.valueOf(response.isSuccessful()));
                Log.d("LISTENUP", String.valueOf(response.body()));

            }

            @Override
            public void onFailure(Call <String> call, Throwable t) {
                Log.d("LISTENUP", "womp womp");
            }
        });

        return view;
    }

    private void updateUI(Subscriptions Subscriptions) {

        mSubscriptionsAdapter = new SubscriptionsFragment.SubscriptionsAdapter(Arrays.asList(Subscriptions));
        mSubscriptionsRecyclerView.setAdapter(mSubscriptionsAdapter);

    }

    private class SubscriptionsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Subscriptions mSubscription;
        public TextView mSubscriptionsTextView;

        public SubscriptionsHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mSubscriptionsTextView = (TextView) itemView;
        }

        public void bindSubscriptions(Subscriptions subscription) {
            mSubscription = subscription;
            this.mSubscriptionsTextView.setText(subscription.getTitle());
        }

        @Override
        public void onClick(View v) {

            Intent intent = SubscriptionsActivity.newIntent(getActivity(), mSubscription.getTitle() );
            startActivity(intent);

            Toast.makeText(getActivity(), mSubscription.getTitle() + " clicked!", Toast.LENGTH_SHORT).show();
        }
    }

    private class SubscriptionsAdapter extends RecyclerView.Adapter<SubscriptionsFragment.SubscriptionsHolder> {

        private List<Subscriptions> mSubscriptionsList;

        public SubscriptionsAdapter(List<Subscriptions> allSubscriptions) {
            mSubscriptionsList = allSubscriptions;
        }

        @Override
        public SubscriptionsFragment.SubscriptionsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new SubscriptionsFragment.SubscriptionsHolder(view);
        }

        @Override
        public void onBindViewHolder(SubscriptionsFragment.SubscriptionsHolder holder, int position) {
            Subscriptions thisSubscriptions = mSubscriptionsList.get(position);
            holder.mSubscriptionsTextView.setText(thisSubscriptions.getTitle());
            holder.bindSubscriptions(thisSubscriptions);

        }

        @Override
        public int getItemCount() {
            return mSubscriptionsList.size();
        }



    }


}
