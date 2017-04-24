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

import edu.iupui.jamcanno.listenup.model.Tag;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexc on 4/24/2017.
 */

public class DeviceListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //MAKE CONNECTION TO THE API
        GPodderAPI client = ServiceGenerator.createService(GPodderAPI.class, "username here", "password here");

        //GET LIST OF TAG FROM API
        Call<String> call = client.getDevices("");
        Log.d("ListenUP",  "Making devcices call to the api");

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

        return null;
    }

public static DeviceListFragment newInstance(){
    DeviceListFragment fragment = new DeviceListFragment();
    return fragment;
}


}
