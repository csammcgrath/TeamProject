package com.example.sam.testing2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

import uk.co.senab.photoview.PhotoViewAttacher;

public class homeScreen extends Fragment {

    private FirebaseAuth firebaseAuth;
    private UserInfo userInfo;
    private DatabaseReference databaseReference;
    private Firebase firebase;

    private TextView email;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        return inflater.inflate(R.layout.homescreen, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //utilizing chrisbanes's library
        ImageView imageView = (ImageView) getView().findViewById(R.id.imageView4);
        PhotoViewAttacher photoViewer = new PhotoViewAttacher(imageView);
        photoViewer.update();
    }
}
