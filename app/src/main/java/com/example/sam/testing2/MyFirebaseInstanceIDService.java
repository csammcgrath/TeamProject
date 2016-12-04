package com.example.sam.testing2;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.firebase.client.Firebase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Sam on 11/28/2016.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIDService";

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "New token: " + refreshedToken);
        sendRegistrationToServer(refreshedToken);

    }

    //store token on server
    private void sendRegistrationToServer(String token) {
        saveTokenToPrefs(token);
    }

    private void saveTokenToPrefs(String token)
    {
        // Access Shared Preferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        // Save to SharedPreferences
        editor.putString("notification", token);
        editor.apply();
    }
}
