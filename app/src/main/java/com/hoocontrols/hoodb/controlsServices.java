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
public class controlsServices extends Fragment {


    public controlsServices() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater _inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ImageView image1, image2, image3, image4, image5, image6;
        // Inflate the layout for this fragment
        View _view =  _inflater.inflate(R.layout.fragment_controls_hall, container, false);




        LinearLayout controlsRow = _view.findViewById(R.id.controlsHall);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view;
        final Button btnBase, btnControl1, btnControl2, btnControl3, btnControl4, btnControl5, btnControl6, btnControl7;

        //Reading the States Before Rendering
        ((HomeActivity)getActivity()).readBackTrackStates();


        //1st button
        view = inflater.inflate(R.layout.services, controlsRow, false);
        btnControl1 = view.findViewById(R.id.button12);
        btnControl1.setText("Live Support");
        image1 = view.findViewById(R.id.imageView2);
        image1.setImageResource(R.drawable.careem);
        btnControl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Service Called", Toast.LENGTH_LONG).show();
            }
        });
        controlsRow.addView(view);

//2nd Button
        view = inflater.inflate(R.layout.services, controlsRow, false);
        btnControl2 = view.findViewById(R.id.button12);
        btnControl2.setText("Security");
        image2 = view.findViewById(R.id.imageView2);
        image2.setImageResource(R.drawable.uber);

        btnControl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Service Called", Toast.LENGTH_LONG).show();
            }
        });
        controlsRow.addView(view);

        //2nd Button
        view = inflater.inflate(R.layout.services, controlsRow, false);
        btnControl3 = view.findViewById(R.id.button12);
        btnControl3.setText("SOS");
        image2 = view.findViewById(R.id.imageView2);
        image2.setImageResource(R.drawable.uber);

        btnControl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Service Called", Toast.LENGTH_LONG).show();
            }
        });
        controlsRow.addView(view);

        //2nd Button
        view = inflater.inflate(R.layout.services, controlsRow, false);
        btnControl4 = view.findViewById(R.id.button12);
        btnControl4.setText("Garbage");
        image3 = view.findViewById(R.id.imageView2);
        image3.setImageResource(R.drawable.uber);

        btnControl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Service Called", Toast.LENGTH_LONG).show();
            }
        });
        controlsRow.addView(view);

        //2nd Button
        view = inflater.inflate(R.layout.services, controlsRow, false);
        btnControl5 = view.findViewById(R.id.button12);
        btnControl5.setText("Plumbing");
        image4 = view.findViewById(R.id.imageView2);
        image4.setImageResource(R.drawable.uber);

        btnControl5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Service Called", Toast.LENGTH_LONG).show();
            }
        });
        controlsRow.addView(view);


        //2nd Button
        view = inflater.inflate(R.layout.services, controlsRow, false);
        btnControl6 = view.findViewById(R.id.button12);
        btnControl6.setText("Book Ride");
        image5 = view.findViewById(R.id.imageView2);
        image5.setImageResource(R.drawable.uber);

        btnControl6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Service Called", Toast.LENGTH_LONG).show();
            }
        });
        controlsRow.addView(view);


        //2nd Button
        view = inflater.inflate(R.layout.services, controlsRow, false);
        btnControl7 = view.findViewById(R.id.button12);
        btnControl7.setText("Grocery");
        image6 = view.findViewById(R.id.imageView2);
        image6.setImageResource(R.drawable.uber);

        btnControl7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Service Called", Toast.LENGTH_LONG).show();
            }
        });
        controlsRow.addView(view);

        return _view;
    }

}
