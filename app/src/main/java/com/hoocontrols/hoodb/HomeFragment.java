package com.hoocontrols.hoodb;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

//import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Button button;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View _view =  _inflater.inflate(R.layout.fragment_home, container, false);
//        button = _view.findViewById(R.id.b1);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new FirstFragment(), null).commit();
//            }
//        });
//
//
//        LinearLayout controlsRow = _view.findViewById(R.id.controls);
//        LayoutInflater inflater = LayoutInflater.from(getActivity());
//
//        View view = inflater.inflate(R.layout.button, controlsRow, false);
//        controlsRow.addView(view);



        return _view;
    }

}
