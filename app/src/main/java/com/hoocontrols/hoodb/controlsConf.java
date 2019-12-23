package com.hoocontrols.hoodb;


import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

//import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class controlsConf extends Fragment {

    //    private String hooletId = "";
    private String roomId = "";     // current room id
    private Button btnBase, btnControl1, btnControl2, btnControl3, btnContext;
    int count = 0;
    //    private SwitchState swStateObj = null;
    private String[] roomIds = new String[30]; //array of room ids for context menu
    private int RoomContextIndex = 0;

    private String contextHoolet = null;
    private View contextView = null;

    LinearLayout controlsRow = null;

    private switchInformation swInfo;

    public controlsConf() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View _view = _inflater.inflate(R.layout.fragment_controls_hall, container, false);

        controlsRow = _view.findViewById(R.id.controlsHall);
        final LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view;


        //Reading the States Before Rendering
        ((HomeActivity) getActivity()).readBackTrackStates();

        Bundle controlsFragmentBundle = getArguments();
        roomId = controlsFragmentBundle.getString("roomId");


//        Log.e("HOODE4", roomId);

        //Query for All Switches in a specific Room(Q1)
        Registration.roomsRef.child(roomId).child("switches").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                if(HomeActivity.isAddedRemoved == true){
                    // Doing an IF check to see if this is Ad delete scenario
                    controlsRow.removeAllViews();
                    HomeActivity.isAddedRemoved = true;
                }


                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    final String hooletId = postSnapshot.getKey();

                    // Query for getting the Switch Properties(Q2)
                    Registration.switchPropsRef.child(hooletId).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot swSnapshot) {
                            swInfo = swSnapshot.getValue(switchInformation.class);

                            if (swInfo.getType().compareTo("3G") == 0) {

                                final View view = inflater.inflate(R.layout.button3g, controlsRow, false);

                                //Query for Switch States (Q3)
                                Registration.switchStatesRef.child(hooletId).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot swSnapshot) {
                                        final SwitchState swStateObj = swSnapshot.getValue(SwitchState.class);

                                        btnContext = view.findViewById(R.id.button1);
                                        btnContext.setText(hooletId);
//                                        btnContext.setTextSize(0);
                                        registerForContextMenu(btnContext);
                                        btnContext.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                contextHoolet = hooletId;
                                                Log.e("HOODE1", contextHoolet);

                                            }
                                        });


                                        btnControl1 = view.findViewById(R.id.btn3g_1);
                                        if (swStateObj.getG1().compareTo("OF1") == 0) {
                                            btnControl1.setBackgroundResource(R.drawable.switchbutton_red);
                                        } else {
                                            btnControl1.setBackgroundResource(R.drawable.switchbutton_blue);
                                        }
                                        btnControl1.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                if (swStateObj.getG1().compareTo("OF1") == 0) {
                                                    Registration.switchStatesRef.child(hooletId).child("g1").setValue("ON1");
                                                } else {
                                                    Registration.switchStatesRef.child(hooletId).child("g1").setValue("OF1");
                                                }
                                            }
                                        });


                                        btnControl2 = view.findViewById(R.id.btn3g_2);
                                        if (swStateObj.getG2().compareTo("OF2") == 0) {
                                            btnControl2.setBackgroundResource(R.drawable.switchbutton_red);
                                        } else {
                                            btnControl2.setBackgroundResource(R.drawable.switchbutton_blue);
                                        }
                                        btnControl2.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                if (swStateObj.getG2().compareTo("OF2") == 0) {
                                                    Registration.switchStatesRef.child(hooletId).child("g2").setValue("ON2");
                                                } else {
                                                    Registration.switchStatesRef.child(hooletId).child("g2").setValue("OF2");
                                                }
                                            }
                                        });

                                        btnControl3 = view.findViewById(R.id.btn3g_3);
                                        if (swStateObj.getG3().compareTo("OF3") == 0) {
                                            btnControl3.setBackgroundResource(R.drawable.switchbutton_red);
                                        } else {
                                            btnControl3.setBackgroundResource(R.drawable.switchbutton_blue);
                                        }
                                        btnControl3.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                if (swStateObj.getG3().compareTo("OF3") == 0) {
                                                    Registration.switchStatesRef.child(hooletId).child("g3").setValue("ON3");
                                                } else {
                                                    Registration.switchStatesRef.child(hooletId).child("g3").setValue("OF3");
                                                }
                                            }
                                        });
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        Log.e("The read failed: ", databaseError.getMessage());
                                    }
                                });
                                controlsRow.addView(view);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.e("The read failed: ", databaseError.getMessage());
                        }
                    });



                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("The read failed: ", databaseError.getMessage());
            }
        });

        return _view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, final View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        v.callOnClick();
        Log.e("HOODE2", contextHoolet);


//        switch (v.getId()) {
//
//            case R.id.button2:
//                Log.e("HOODEW", "B2");
//                break;
//            case R.id.button1:
//                Log.e("HOODEW", "B1");
//                break;
//
//
//        }


//        menu.setHeaderTitle("Context Menu");

//        Log.e("HOODEW", menuInfo.toString());

        menu.add(0, v.getId(), 0, "Rename");
        final SubMenu subm = menu.addSubMenu(0, 1, 0, "Move To");
//        subm.add(0, v.getId(), Menu.NONE, "Room1");

        RoomContextIndex = 0;
        Registration.roomsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot swSnapshot) {
                for (final DataSnapshot postSnapshot : swSnapshot.getChildren()) {
                    RoomInformation room = postSnapshot.getValue(RoomInformation.class);
                    subm.add(0, RoomContextIndex, Menu.NONE, room.getName());

                    roomIds[RoomContextIndex] = postSnapshot.getKey();
//                    Log.e("HOODER: ", postSnapshot.getKey());
                    RoomContextIndex++;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("The read failed: ", databaseError.getMessage());
            }
        });

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String destinationRoom = roomIds[item.getItemId()];
        String sourceRoom = roomId;
        String targetHoolet = contextHoolet;
//        Log.e("HOODEA: ", roomId);
        if (item.getTitle() == "Rename") {
//            Log.e("HOODEC: ", "Rename");
        } else if (item.getTitle() == "Move To") {
//            Log.e("HOODEC: ", "Rename");
        } else {
//            View cview = info.targetView;
//            Log.e("HOODEC", "Text:  "+ btnContext.getText());
            Log.e("HOODEX", " Source Room: " + roomId + " Destinaiton Room: " + roomIds[item.getItemId()] + " Hoolet Id: " + contextHoolet);
            Registration.roomsRef.child(destinationRoom).child("switches").child(targetHoolet).setValue(true);
            Registration.roomsRef.child(sourceRoom).child("switches").child(targetHoolet).removeValue();
            controlsRow.removeAllViews();
            return false;
        }
        return true;
    }
}
