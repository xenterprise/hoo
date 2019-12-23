package com.hoocontrols.hoodb;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

//import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class controlsScenes extends Fragment {


    public controlsScenes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ImageView homeImage;
        // Inflate the layout for this fragment
        View _view =  _inflater.inflate(R.layout.fragment_controls_hall, container, false);




        LinearLayout controlsRow = _view.findViewById(R.id.controlsHall);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view;
        final Button btnBase, btnControl1, btnControl2, btnControl3, btnControl4;

        //Reading the States Before Rendering
        ((HomeActivity)getActivity()).readBackTrackStates();


        //1st button
        view = inflater.inflate(R.layout.button4g, controlsRow, false);
        btnControl1 = view.findViewById(R.id.btn4g_1);
        btnControl1.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.breaker_blue));
        btnControl1.setCompoundDrawablesWithIntrinsicBounds( 0, R.drawable.ic_all_on2, 0, 0);
        btnControl1.setText("All ON");

        btnControl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Breaker_AllOnOff, "AllON");
            }
        });
        controlsRow.addView(view);

//2nd Button
        view = inflater.inflate(R.layout.button4g, controlsRow, false);
        btnControl2 = view.findViewById(R.id.btn4g_1);
        btnControl2.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.breaker_red));
        btnControl2.setCompoundDrawablesWithIntrinsicBounds( 0, R.drawable.ic_all_off2, 0, 0);
        btnControl2.setText("All OFF");

        btnControl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Breaker_AllOnOff, "AllOFF");
            }
        });
        controlsRow.addView(view);

        return _view;
    }

}
