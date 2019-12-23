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
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class controlsHall extends Fragment {



    public controlsHall() {
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
        final Button btnBase, btnControl1, btnControl2, btnControl3;
        final Button btnControl4, btnControl5, btnControl6;
        final Button btnControl7, btnControl8, btnControl9;
        final Button btnControl11, btnControl12,btnControl13, fanControl1, fanControl2, fanControl3, FanLvlButton;

        //Reading the States Before Rendering
        ((HomeActivity)getActivity()).readBackTrackStates();

        //3 Gang

        view = inflater.inflate(R.layout.button3g, controlsRow, false);
        btnControl1 = view.findViewById(R.id.btn3g_1);

        if(((HomeActivity)getActivity()).Conf_3G_A_1.compareTo("OF1")==0) {
            btnControl1.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));
        }
        else {
            btnControl1.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));
        }
        btnControl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText((HomeActivity) getActivity(), "B1 Clicked", Toast.LENGTH_LONG).show();
                if(((HomeActivity)getActivity()).Conf_3G_A_1.compareTo("OF1")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Conf_3G_A, "ON1");
                    ((HomeActivity)getActivity()).Conf_3G_A_1="ON1";
                    btnControl1.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));
                }
                else {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Conf_3G_A, "OF1");
                    ((HomeActivity) getActivity()).Conf_3G_A_1 = "OF1";
                    btnControl1.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));
                }
            }
        });
        btnControl2 = view.findViewById(R.id.btn3g_2);
        if(((HomeActivity)getActivity()).Conf_3G_A_2.compareTo("OF2")==0) {
            btnControl2.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));
        }
        else {
            btnControl2.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));
        }
        btnControl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText((HomeActivity) getActivity(), "B2 Clicked", Toast.LENGTH_LONG).show();
                if(((HomeActivity)getActivity()).Conf_3G_A_2.compareTo("OF2")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Conf_3G_A, "ON2");
                    ((HomeActivity)getActivity()).Conf_3G_A_2="ON2";
                    btnControl2.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));
                }
                else {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Conf_3G_A, "OF2");
                    ((HomeActivity) getActivity()).Conf_3G_A_2 = "OF2";
                    btnControl2.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));

                }
            }
        });
        btnControl3 = view.findViewById(R.id.btn3g_3);
        if(((HomeActivity)getActivity()).Conf_3G_A_3.compareTo("OF3")==0) {
            btnControl3.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));
        }
        else {
            btnControl3.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));
        }
        btnControl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText((HomeActivity) getActivity(), "B3 Clicked", Toast.LENGTH_LONG).show();
                if(((HomeActivity)getActivity()).Conf_3G_A_3.compareTo("OF3")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Conf_3G_A, "ON3");
                    ((HomeActivity)getActivity()).Conf_3G_A_3="ON3";
                    btnControl3.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));

                }
                else {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Conf_3G_A, "OF3");
                    ((HomeActivity) getActivity()).Conf_3G_A_3 = "OF3";
                    btnControl3.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));

                }
            }
        });
        controlsRow.addView(view);


        //3 Gang
        view = inflater.inflate(R.layout.button3g, controlsRow, false);
        btnControl4 = view.findViewById(R.id.btn3g_1);
        if(((HomeActivity)getActivity()).Hall_3G_B_1.compareTo("OF1")==0) {
            btnControl4.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));
        }
        else {
            btnControl4.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));
        }
        btnControl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((HomeActivity)getActivity()).Hall_3G_B_1.compareTo("OF1")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_3G_B, "ON1");
                    ((HomeActivity)getActivity()).Hall_3G_B_1="ON1";
                    btnControl4.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));

                }
                else {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_3G_B, "OF1");
                    ((HomeActivity)getActivity()).Hall_3G_B_1="OF1";
                    btnControl4.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));

                }
            }
        });
        btnControl5 = view.findViewById(R.id.btn3g_2);
        if(((HomeActivity)getActivity()).Hall_3G_B_2.compareTo("OF2")==0) {
            btnControl5.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));
        }
        else {
            btnControl5.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));
        }
        btnControl5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((HomeActivity)getActivity()).Hall_3G_B_2.compareTo("OF2")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_3G_B, "ON2");
                    ((HomeActivity)getActivity()).Hall_3G_B_2="ON2";
                    btnControl5.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));

                }
                else {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_3G_B, "OF2");
                    ((HomeActivity)getActivity()).Hall_3G_B_2="OF2";
                    btnControl5.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));

                }
            }
        });
        btnControl6 = view.findViewById(R.id.btn3g_3);
        if(((HomeActivity)getActivity()).Hall_3G_B_3.compareTo("OF3")==0) {
            btnControl6.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));
        }
        else {
            btnControl6.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));
        }
        btnControl6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((HomeActivity)getActivity()).Hall_3G_B_3.compareTo("OF3")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_3G_B, "ON3");
                    ((HomeActivity)getActivity()).Hall_3G_B_3="ON3";
                    btnControl6.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));

                }
                else {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_3G_B, "OF3");
                    ((HomeActivity)getActivity()).Hall_3G_B_3="OF3";
                    btnControl6.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));

                }
            }
        });
        controlsRow.addView(view);

        //3 Gang

        view = inflater.inflate(R.layout.button3g, controlsRow, false);
        btnControl7 = view.findViewById(R.id.btn3g_1);
        if(((HomeActivity)getActivity()).Hall_3G_C_1.compareTo("OF1")==0) {
            btnControl7.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));
        }
        else {
            btnControl7.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));
        }
        btnControl7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((HomeActivity)getActivity()).Hall_3G_C_1.compareTo("OF1")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_3G_C, "ON1");
                    ((HomeActivity)getActivity()).Hall_3G_C_1="ON1";
                    btnControl7.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));

                }
                else {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_3G_C, "OF1");
                    ((HomeActivity)getActivity()).Hall_3G_C_1="OF1";
                    btnControl7.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));

                }
            }
        });
        btnControl8 = view.findViewById(R.id.btn3g_2);
        if(((HomeActivity)getActivity()).Hall_3G_C_2.compareTo("OF2")==0) {
            btnControl8.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));
        }
        else {
            btnControl8.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));
        }
        btnControl8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((HomeActivity)getActivity()).Hall_3G_C_2.compareTo("OF2")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_3G_C, "ON2");
                    ((HomeActivity)getActivity()).Hall_3G_C_2="ON2";
                    btnControl8.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));
                }
                else {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_3G_C, "OF2");
                    ((HomeActivity)getActivity()).Hall_3G_C_2="OF2";
                    btnControl8.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));
                }
            }
        });
        btnControl9 = view.findViewById(R.id.btn3g_3);
        if(((HomeActivity)getActivity()).Hall_3G_C_3.compareTo("OF3")==0) {
            btnControl9.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));
        }
        else {
            btnControl9.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));
        }
        btnControl9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((HomeActivity)getActivity()).Hall_3G_C_3.compareTo("OF3")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_3G_C, "ON3");
                    ((HomeActivity)getActivity()).Hall_3G_C_3="ON3";
                    btnControl9.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));
                }
                else {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Hall_3G_C, "OF3");
                    ((HomeActivity)getActivity()).Hall_3G_C_3="OF3";
                    btnControl9.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));
                }
            }
        });
        controlsRow.addView(view);

//3 Gang

        view = inflater.inflate(R.layout.button3g, controlsRow, false);
        btnControl11 = view.findViewById(R.id.btn3g_1);
        if(((HomeActivity)getActivity()).Office_3G_A_1.compareTo("OF1")==0) {
            btnControl11.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));
        }
        else {
            btnControl11.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));
        }
        btnControl11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((HomeActivity)getActivity()).Office_3G_A_1.compareTo("OF1")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Office_3G_A, "ON1");
                    ((HomeActivity)getActivity()).Office_3G_A_1="ON1";
                    btnControl11.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));
                }
                else {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Office_3G_A, "OF1");
                    ((HomeActivity)getActivity()).Office_3G_A_1="OF1";
                    btnControl11.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));
                }
            }
        });
        btnControl12 = view.findViewById(R.id.btn3g_2);
        if(((HomeActivity)getActivity()).Office_3G_A_2.compareTo("OF2")==0) {
            btnControl12.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));
        }
        else {
            btnControl12.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));
        }
        btnControl12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((HomeActivity)getActivity()).Office_3G_A_2.compareTo("OF2")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Office_3G_A, "ON2");
                    ((HomeActivity)getActivity()).Office_3G_A_2="ON2";
                    btnControl12.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));
                }
                else {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Office_3G_A, "OF2");
                    ((HomeActivity)getActivity()).Office_3G_A_2="OF2";
                    btnControl12.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));
                }
            }
        });
        btnControl13 = view.findViewById(R.id.btn3g_3);
        if(((HomeActivity)getActivity()).Office_3G_A_3.compareTo("OF3")==0) {
            btnControl13.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));
        }
        else {
            btnControl13.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));
        }
        btnControl13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((HomeActivity)getActivity()).Office_3G_A_3.compareTo("OF3")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Office_3G_A, "ON3");
                    ((HomeActivity)getActivity()).Office_3G_A_3="ON3";
                    btnControl13.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_blue));
                }
                else {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Office_3G_A, "OF3");
                    ((HomeActivity)getActivity()).Office_3G_A_3="OF3";
                    btnControl13.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.switchbutton_red));
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
                if(((HomeActivity)getActivity()).Office_Fan_A_Level.compareTo("FanLvl1")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Office_Fan_A, "FanLvl1");
                    ((HomeActivity)getActivity()).Office_Fan_A_Level="FanLvl1";
                    FanLvlButton.setBackgroundResource(R.drawable.fanlvl_1);
                }
                else if(((HomeActivity)getActivity()).Office_Fan_A_Level.compareTo("FanLvl2")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Office_Fan_A, "FanLvl1");
                    ((HomeActivity)getActivity()).Office_Fan_A_Level="FanLvl1";
                    FanLvlButton.setBackgroundResource(R.drawable.fanlvl_1);
                }
                else if(((HomeActivity)getActivity()).Office_Fan_A_Level.compareTo("FanLvl3")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Office_Fan_A, "FanLvl2");
                    ((HomeActivity)getActivity()).Office_Fan_A_Level="FanLvl2";
                    FanLvlButton.setBackgroundResource(R.drawable.fanlvl_2);
                }
                else if(((HomeActivity)getActivity()).Office_Fan_A_Level.compareTo("FanLvl4")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Office_Fan_A, "FanLvl3");
                    ((HomeActivity)getActivity()).Office_Fan_A_Level="FanLvl3";
                    FanLvlButton.setBackgroundResource(R.drawable.fanlvl_3);
                }
                else if(((HomeActivity)getActivity()).Office_Fan_A_Level.compareTo("FanLvl5")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Office_Fan_A, "FanLvl4");
                    ((HomeActivity)getActivity()).Office_Fan_A_Level="FanLvl4";
                    FanLvlButton.setBackgroundResource(R.drawable.fanlvl_4);
                }
            }
        });
        fanControl2 = view.findViewById(R.id.btn3g_2);
        if(((HomeActivity)getActivity()).Office_Fan_A_State.compareTo("FanOFF")==0) {
            fanControl2.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.fan_center_red));
        }
        else if(((HomeActivity)getActivity()).Office_Fan_A_State.compareTo("FanON")==0) {
            fanControl2.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.fan_center_blue));
        }
        fanControl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((HomeActivity)getActivity()).Office_Fan_A_State.compareTo("FanOFF")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Office_Fan_A, "FanON");
                    ((HomeActivity)getActivity()).Office_Fan_A_State="FanON";
                    fanControl2.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.fan_center_blue));
                }
                else {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Office_Fan_A, "FanOFF");
                    ((HomeActivity)getActivity()).Office_Fan_A_State="FanOFF";
                    fanControl2.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.fan_center_red));
                }
            }
        });
        fanControl3 = view.findViewById(R.id.btn3g_3);
        fanControl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((HomeActivity)getActivity()).Office_Fan_A_Level.compareTo("FanLvl1")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Office_Fan_A, "FanLvl2");
                    ((HomeActivity)getActivity()).Office_Fan_A_Level="FanLvl2";
                    FanLvlButton.setBackgroundResource(R.drawable.fanlvl_2);
                }
                else if(((HomeActivity)getActivity()).Office_Fan_A_Level.compareTo("FanLvl2")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Office_Fan_A, "FanLvl3");
                    ((HomeActivity)getActivity()).Office_Fan_A_Level="FanLvl3";
                    FanLvlButton.setBackgroundResource(R.drawable.fanlvl_3);
                }
                else if(((HomeActivity)getActivity()).Office_Fan_A_Level.compareTo("FanLvl3")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Office_Fan_A, "FanLvl4");
                    ((HomeActivity)getActivity()).Office_Fan_A_Level="FanLvl4";
                    FanLvlButton.setBackgroundResource(R.drawable.fanlvl_4);
                }
                else if(((HomeActivity)getActivity()).Office_Fan_A_Level.compareTo("FanLvl4")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Office_Fan_A, "FanLvl5");
                    ((HomeActivity)getActivity()).Office_Fan_A_Level="FanLvl5";
                    FanLvlButton.setBackgroundResource(R.drawable.fanlvl_5);
                }
                else if(((HomeActivity)getActivity()).Office_Fan_A_Level.compareTo("FanLvl5")==0) {
                    ((HomeActivity) getActivity()).SwitchCore(((HomeActivity) getActivity()).Office_Fan_A, "FanLvl5");
                    ((HomeActivity)getActivity()).Office_Fan_A_Level="FanLvl5";
                    FanLvlButton.setBackgroundResource(R.drawable.fanlvl_5);
                }
            }
        });
        controlsRow.addView(view);


        return _view;
    }

}
