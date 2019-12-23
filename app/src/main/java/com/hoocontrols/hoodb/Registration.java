package com.hoocontrols.hoodb;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Registration extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;




    // This button is placed in main activity layout.
    private Button openConsole = null;
    private Button openInputPopupDialogButton = null;
    // This listview is just under above button.
    private ListView userDataListView = null;
    private TextView data = null;

    // Below edittext and button are all exist in the popup dialog view.
    private View popupInputDialogView = null;
    // Contains user name data.
    private EditText userNameEditText = null;
    // Contains password data.
    private EditText passwordEditText = null;
    // Contains email data.
    private EditText emailEditText = null;
    // Click this button in popup dialog to save user input data in above three edittext.
    private Button saveUserDataButton = null;
    // Click this button to cancel edit user data.
    private Button cancelUserDataButton = null;

    public static DatabaseReference mDatabase, roomsRef, uaSwitchRef, siteRef, switchStatesRef, switchPropsRef;
    public static String siteKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Shared Preferences:
        pref = getApplicationContext().getSharedPreferences("HooPref", MODE_PRIVATE);
        editor = pref.edit();

        data = findViewById(R.id.textView4);
        setTitle("Hoo Controls Test Bench");

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        siteKey = pref.getString("siteKey", "NON");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        siteRef = mDatabase.child("sites").child(siteKey);
        roomsRef = siteRef.child("rooms");
        switchStatesRef = siteRef.child("switchStates");
        switchPropsRef = siteRef.child("switchProps");


        if(siteKey.compareTo("NON")==0){
            siteKey = mDatabase.push().getKey();
            uaSwitchRef = mDatabase.child("sites").child(siteKey).child("rooms").child("UASR").child("switches");

            mDatabase.child("sites").child(siteKey).child("age").setValue("FRESH");
            uaSwitchRef.child("name").setValue("Unassigned Switches");
            editor.putString("siteKey", siteKey);
            editor.apply();

        }else{
            uaSwitchRef = mDatabase.child("sites").child(siteKey).child("rooms").child("UASR").child("switches");

            mDatabase.child("sites").child(siteKey).child("age").setValue("OLD");
            Intent homeActivity = new Intent(Registration.this, HomeActivity.class);
            startActivity(homeActivity);
        }








        openInputPopupDialogButton = (Button)findViewById(R.id.button_popup_overlay_input_dialog);
        openConsole = (Button)findViewById(R.id.button_add_room);
        userDataListView = (ListView)findViewById(R.id.listview_user_data);

        openConsole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeActivity = new Intent(Registration.this, HomeActivity.class);
                startActivity(homeActivity);
            }

        });

        // When click the open input popup dialog button.
        openInputPopupDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create a AlertDialog Builder.
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Registration.this);
                // Set title, icon, can not cancel properties.
                alertDialogBuilder.setTitle("Add Room Info");
                alertDialogBuilder.setIcon(R.drawable.ic_launcher_background);
                alertDialogBuilder.setCancelable(false);

                // Init popup dialog view and it's ui controls.
                initPopupViewControls();

                // Set the inflated layout view object to the AlertDialog builder.
                alertDialogBuilder.setView(popupInputDialogView);

                // Create AlertDialog and show.
                final AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                // When user click the save user data button in the popup dialog.
                saveUserDataButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // Get user data from popup dialog editeext.
                        String userName = userNameEditText.getText().toString();
                        String password = passwordEditText.getText().toString();
                        String email = emailEditText.getText().toString();

                        // Create data for the listview.
                        String[] titleArr = { "User Name", "Password", "Email"};
                        String[] dataArr = {userName, password, email};

                        ArrayList<Map<String,Object>> itemDataList;
                        itemDataList = new ArrayList<Map<String,Object>>();

                        int titleLen = titleArr.length;
                        for(int i =0; i < titleLen; i++) {
                            Map<String,Object> listItemMap = new HashMap<String,Object>();
                            listItemMap.put("title", titleArr[i]);
                            listItemMap.put("data", dataArr[i]);
                            itemDataList.add(listItemMap);
                        }

                        SimpleAdapter simpleAdapter = new SimpleAdapter(Registration.this,itemDataList,android.R.layout.simple_list_item_2,
                                new String[]{"title","data"},new int[]{android.R.id.text1,android.R.id.text2});

                        userDataListView.setAdapter(simpleAdapter);

                        alertDialog.cancel();
                    }
                });

                cancelUserDataButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                    }
                });
            }
        });

        String x, y;
        x = "y2000";
        y = "Hello";
        //Write data to file
        JSONObject student1 = new JSONObject();
        try {
            student1.put("id", "3");
            student1.put("name", "NAME OF STUDENT");
            student1.put("year", "4th");
            student1.put("curriculum", "Arts");
            student1.put("birthday", "5/5/1993");
            student1.put(x,y);



        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        writeToFile(student1.toString());
        // Read JSON Data from file
        data.setText(readFromFile());
    }

    private void writeToFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile() {
        String ret = "";
        try {
            InputStream inputStream = openFileInput("config.txt");
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return ret;
    }

    /* Initialize popup dialog view and ui controls in the popup dialog. */
    private void initPopupViewControls()
    {
        // Get layout inflater object.
        LayoutInflater layoutInflater = LayoutInflater.from(Registration.this);

        // Inflate the popup dialog from a layout xml file.
        popupInputDialogView = layoutInflater.inflate(R.layout.rooms_dialog, null);

        // Get user input edittext and button ui controls in the popup dialog.
        userNameEditText = (EditText) popupInputDialogView.findViewById(R.id.userName);
        passwordEditText = (EditText) popupInputDialogView.findViewById(R.id.password);
        emailEditText = (EditText) popupInputDialogView.findViewById(R.id.email);
        saveUserDataButton = popupInputDialogView.findViewById(R.id.button_save_user_data);
        cancelUserDataButton = popupInputDialogView.findViewById(R.id.button_cancel_user_data);

        // Display values from the main activity list view in user input edittext.
        initEditTextUserDataInPopupDialog();
    }


    /* Get current user data from listview and set them in the popup dialog edittext controls. */
    private void initEditTextUserDataInPopupDialog()
    {
        List<String> userDataList = getExistUserDataInListView(userDataListView);

        if(userDataList.size() == 3)
        {
            String userName = userDataList.get(0);

            String password = userDataList.get(1);

            String email = userDataList.get(2);

            if(userNameEditText != null)
            {
                userNameEditText.setText(userName);
            }

            if(passwordEditText != null)
            {
                passwordEditText.setText(password);
            }

            if(emailEditText != null)
            {
                emailEditText.setText(email);
            }
        }
    }

    /* If user data exist in the listview then retrieve them to a string list. */
    private List<String> getExistUserDataInListView(ListView listView)
    {
        List<String> ret = new ArrayList<String>();

        if(listView != null)
        {
            ListAdapter listAdapter = listView.getAdapter();

            if(listAdapter != null) {

                int itemCount = listAdapter.getCount();

                for (int i = 0; i < itemCount; i++) {
                    Object itemObject = listAdapter.getItem(i);
                    HashMap<String, String> itemMap = (HashMap<String, String>)itemObject;

                    Set<String> keySet = itemMap.keySet();

                    Iterator<String> iterator = keySet.iterator();

                    String key = iterator.next();

                    String value = itemMap.get(key);

                    ret.add(value);
                }
            }
        }

        return ret;
    }
}

