package com.example.tabrezahmad.createresume;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AcademicQualificationFrag extends Fragment {
    View root;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.education, container, false);
        return root;
    }


}