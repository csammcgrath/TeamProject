package com.example.sam.testing2;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class maps extends Fragment {
    Button power;
    Button country;
    Button baseline;
    Button greenfield;
    private TextView mapTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        return inflater.inflate(R.layout.activity_maps, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        power = (Button)getView().findViewById(R.id.power);
        country = (Button)getView().findViewById(R.id.country);
        baseline = (Button)getView().findViewById(R.id.signalbutte);
        greenfield = (Button)getView().findViewById(R.id.greenfield);
        mapTitle = (TextView) getView().findViewById(R.id.textView12);
        mapTitle.setTextColor(Color.parseColor("#D9251C"));


        buttonWatcher();
    }

    public void buttonWatcher() {
        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=33.414220, -111.682497"));
                startActivity(intent);
            }
        });

        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=33.436512, -111.839102"));
                startActivity(intent);
            }
        });

        baseline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=33.382494, -111.599695"));
                startActivity(intent);
            }
        });

        greenfield.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=33.466825, -111.737851"));
                startActivity(intent);
            }
        });
    }

}
