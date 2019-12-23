package com.hoocontrols.hoodb;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;

//import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class RoomsFragment extends Fragment {


    int RoomCount = 20;
    int RoomIndex=0;
    public Button button, button1, button2, button3, button4, button5, button6, button7;
    public Button[] roomsButton = new Button[RoomCount];
    public String[] roomNames;

    public String rawNames = "Conference Room,Main Hall,Main Power,Office,Rest Room,Rest Room";

    public Button btnBase;
    public ImageView imageView;


//    private controlsConf controlsFragment;
//    private Bundle controlsFragmentBundle;


    public RoomsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view =  _inflater.inflate(R.layout.fragment_rooms, container, false);

        final LinearLayout controlsRow = _view.findViewById(R.id.roomControls);
        final LayoutInflater inflater = LayoutInflater.from(getActivity());
        final controlsConf controlsFragment = new controlsConf();
        final Bundle controlsFragmentBundle = new Bundle();


        Registration.roomsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                controlsRow.removeAllViews();

//                Log.e("HOODE" ,snapshot.toString());
                for (final DataSnapshot postSnapshot: snapshot.getChildren()) {
                    RoomInformation room = postSnapshot.getValue(RoomInformation.class);
//                    Log.e("HOODE1", postSnapshot.getKey());
//                    Log.e("HOODE2", room.getName());




                    View view = inflater.inflate(R.layout.roomx, controlsRow, false);
                    button1 = view.findViewById(R.id.button13);
                    button1.setText(room.getName());
                    button1.setBackgroundResource(R.drawable.tile_green);
                    button1.setCompoundDrawablesWithIntrinsicBounds( 0, R.drawable.mainicons_conf, 0, 0);
                    // button1.setImageResource(R.drawable.conf);
                    final int fRoomIndex = RoomIndex;
                    button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            Log.e("HOODE6", "RoomIndex "+RoomIndex);
//                            Log.e("HOODE7", "fRoomIndex "+fRoomIndex);

                            controlsFragmentBundle.putString("roomId", postSnapshot.getKey());
                            controlsFragment.setArguments(controlsFragmentBundle);

                            HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, controlsFragment,  "ggg").commit();
                        }
                    });
//                    Log.e("HOODE5", "Index "+RoomIndex);
                    RoomIndex++;

                    controlsRow.addView(view);
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("The read failed: " ,databaseError.getMessage());
            }
        });


        return _view;
    }


}
