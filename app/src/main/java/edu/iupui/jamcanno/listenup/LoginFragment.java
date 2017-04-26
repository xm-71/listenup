package edu.iupui.jamcanno.listenup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by alexc on 4/26/2017.
 */

public class LoginFragment extends Fragment {

    private EditText mUsername;
    private EditText mPassword;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_login, container, false);

            mUsername = (EditText) view.findViewById(R.id.usernameTxt);
            mPassword = (EditText) view.findViewById(R.id.password);

            String user = mPassword.getText().toString();
            String pass = mUsername.getText().toString();

        //MAKE CONNECTION TO THE API
        GPodderAPI client = ServiceGenerator.createService(GPodderAPI.class, user, pass);

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

    public static LoginFragment newInstance(){
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }


}