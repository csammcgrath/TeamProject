package com.example.sam.testing2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class fragment_barcodescanner extends Fragment implements ZBarScannerView.ResultHandler {

    private ZBarScannerView scanner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //there will be no xml layout file for this activity
        //because the zbarscanner library already utilizes the
        //whole fragment window
        scanner = new ZBarScannerView(getActivity());
        return scanner;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        //allows us to handle the results
        scanner.setResultHandler(this);

        //begin the scanning process
        scanner.startCamera();
    }

    @Override
    public void handleResult(Result finalResult) {
        //displaying the results in the log
        String content = finalResult.getContents();
        Toast.makeText(fragment_barcodescanner.this.getActivity(), content, Toast.LENGTH_SHORT).show();

        scanner.resumeCameraPreview(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        //the scanner has been paused!
        scanner.stopCamera();
    }
}
