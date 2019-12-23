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
import android.widget.Toast;

//import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class controlsOffc extends Fragment {


    public controlsOffc() {
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
        final Button btnBase, btnControl1, btnControl2, btnControl3, fanControl1, fanControl2, fanControl3, FanLvlButton, btnControl10, btnControl11;

        //Reading the States Before Rendering
        ((HomeActivity)getActivity()).readBackTrackStates();




        // 2 Gang

        view = inflater.inflate(R.layout.button2g, controlsRow, false);
        btnControl10 = view.findViewById(R.id.btn2g_1);
        if(((HomeActivity)getActivity()).Hall_2G_A_1.compareTo("OF1")==0) {
            btnControl10.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));
        }
        else {
            btnControl10.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));
        }
        btnControl10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((HomeActivity)getActivity()).Hall_2G_A_1.compareTo("OF1")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_2G_A, "ON1");
                    ((HomeActivity)getActivity()).Hall_2G_A_1="ON1";
                    btnControl10.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));
                }
                else {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_2G_A, "OF1");
                    ((HomeActivity)getActivity()).Hall_2G_A_1="OF1";
                    btnControl10.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));
                }
            }
        });
        btnControl11 = view.findViewById(R.id.btn2g_2);
        if(((HomeActivity)getActivity()).Hall_2G_A_2.compareTo("OF3")==0) {
            btnControl11.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));
        }
        else {
            btnControl11.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));
        }
        btnControl11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((HomeActivity)getActivity()).Hall_2G_A_2.compareTo("OF3")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_2G_A, "ON3");
                    ((HomeActivity)getActivity()).Hall_2G_A_2="ON3";
                    btnControl11.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));
                }
                else {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_2G_A, "OF3");
                    ((HomeActivity)getActivity()).Hall_2G_A_2="OF3";
                    btnControl11.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));
                }
            }
        });
        controlsRow.addView(view);

        //3 Gang Fan
        view = inflater.inflate(R.layout.fan3g, controlsRow, false);
        FanLvlButton = view.findViewById(R.id.btn3g_);
        fanControl1 = view.findViewById(R.id.btn3g_1);

        fanControl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((HomeActivity)getActivity()).Hall_Fan_A_Level.compareTo("FanLvl1")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_Fan_A, "FanLvl1");
                    ((HomeActivity)getActivity()).Hall_Fan_A_Level="FanLvl1";
                    FanLvlButton.setBackgroundResource(R.drawable.fanlvl_1);
                }
                else if(((HomeActivity)getActivity()).Hall_Fan_A_Level.compareTo("FanLvl2")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_Fan_A, "FanLvl1");
                    ((HomeActivity)getActivity()).Hall_Fan_A_Level="FanLvl1";
                    FanLvlButton.setBackgroundResource(R.drawable.fanlvl_1);
                }
                else if(((HomeActivity)getActivity()).Hall_Fan_A_Level.compareTo("FanLvl3")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_Fan_A, "FanLvl2");
                    ((HomeActivity)getActivity()).Hall_Fan_A_Level="FanLvl2";
                    FanLvlButton.setBackgroundResource(R.drawable.fanlvl_2);
                }
                else if(((HomeActivity)getActivity()).Hall_Fan_A_Level.compareTo("FanLvl4")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_Fan_A, "FanLvl3");
                    ((HomeActivity)getActivity()).Hall_Fan_A_Level="FanLvl3";
                    FanLvlButton.setBackgroundResource(R.drawable.fanlvl_3);
                }
                else if(((HomeActivity)getActivity()).Hall_Fan_A_Level.compareTo("FanLvl5")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_Fan_A, "FanLvl4");
                    ((HomeActivity)getActivity()).Hall_Fan_A_Level="FanLvl4";
                    FanLvlButton.setBackgroundResource(R.drawable.fanlvl_4);
                }
            }
        });
        fanControl2 = view.findViewById(R.id.btn3g_2);

        Toast.makeText((HomeActivity)getActivity(), ((HomeActivity)getActivity()).Hall_Fan_A_State, Toast.LENGTH_LONG).show();

        if(((HomeActivity)getActivity()).Hall_Fan_A_State.compareTo("FanOFF")==0) {
            fanControl2.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.fan_center_red));
        }
        else if(((HomeActivity)getActivity()).Hall_Fan_A_State.compareTo("FanON")==0) {
            fanControl2.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.fan_center_blue));
        }
        fanControl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((HomeActivity)getActivity()).Hall_Fan_A_State.compareTo("FanOFF")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_Fan_A, "FanON");
                    ((HomeActivity)getActivity()).Hall_Fan_A_State="FanON";
                    fanControl2.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.fan_center_blue));
                }
                else {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_Fan_A, "FanOFF");
                    ((HomeActivity)getActivity()).Hall_Fan_A_State="FanOFF";
                    fanControl2.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.fan_center_red));
                }
            }
        });
        fanControl3 = view.findViewById(R.id.btn3g_3);
        fanControl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((HomeActivity)getActivity()).Hall_Fan_A_Level.compareTo("FanLvl1")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_Fan_A, "FanLvl2");
                    ((HomeActivity)getActivity()).Hall_Fan_A_Level="FanLvl2";
                    FanLvlButton.setBackgroundResource(R.drawable.fanlvl_2);
                }
                else if(((HomeActivity)getActivity()).Hall_Fan_A_Level.compareTo("FanLvl2")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_Fan_A, "FanLvl3");
                    ((HomeActivity)getActivity()).Hall_Fan_A_Level="FanLvl3";
                    FanLvlButton.setBackgroundResource(R.drawable.fanlvl_3);
                }
                else if(((HomeActivity)getActivity()).Hall_Fan_A_Level.compareTo("FanLvl3")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_Fan_A, "FanLvl4");
                    ((HomeActivity)getActivity()).Hall_Fan_A_Level="FanLvl4";
                    FanLvlButton.setBackgroundResource(R.drawable.fanlvl_4);
                }
                else if(((HomeActivity)getActivity()).Hall_Fan_A_Level.compareTo("FanLvl4")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_Fan_A, "FanLvl5");
                    ((HomeActivity)getActivity()).Hall_Fan_A_Level="FanLvl5";
                    FanLvlButton.setBackgroundResource(R.drawable.fanlvl_5);
                }
                else if(((HomeActivity)getActivity()).Hall_Fan_A_Level.compareTo("FanLvl5")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_Fan_A, "FanLvl5");
                    ((HomeActivity)getActivity()).Hall_Fan_A_Level="FanLvl5";
                    FanLvlButton.setBackgroundResource(R.drawable.fanlvl_5);
                }
            }
        });
        controlsRow.addView(view);


        return _view;
    }
}
