package mcdull.com.yue.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mcdull.com.yue.R;

public class OneFragment extends Fragment {


    public OneFragment() {

    }


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

}
