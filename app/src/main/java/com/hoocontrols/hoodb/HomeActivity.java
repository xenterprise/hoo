package com.hoocontrols.hoodb;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.os.Environment;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.wifi.WifiConfiguration;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import io.moquette.BrokerConstants;
import io.moquette.server.config.MemoryConfig;

import org.json.JSONArray;
import org.json.JSONException;

import org.json.JSONObject;


public class HomeActivity extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

//    SharedPreferences.Editor editor;

// Hello Test
// hello test 2

    MqttAndroidClient client;
    MqttConnectOptions options;


    int sdf = 8;
    int id = 1;
    public static FragmentManager fragmentManager;
    public static boolean isAddedRemoved = false;
    public MemoryConfig memoryConfig = new MemoryConfig(new Properties());
    String _message;
    String brokerState = "DEAD";
    String[] parts;

    static String MQTTHOST = "tcp://192.168.1.148:1883";
//    static String MQTTHOST = "tcp://192.168.1.191:1883";
//    static String MQTTHOST = "tcp://192.168.1.177:1883";
//    static String MQTTHOST = "tcp://192.168.0.132:1883";
//    static String MQTTHOST = "tcp://192.168.43.218:1883";
    static String USERNAME = "vqjftqpa";
    static String PASSWORD = "Lgng8k4diV28";
//    static String DEFAULT_SSID = "EVO-Charji-488B";
//    static String DEFAULT_SSID = "LTE3_3993";
    static String DEFAULT_SSID = "hoocontrols";
//    static String DEFAULT_SSID = "hoocontrols1";
//        static String DEFAULT_SSID = "ICE;
//    static String DEFAULT_SSID = "hosterlink.com Web Hosting";
//    String topicStrL = "painlessMesh/to/586821073";
//    String topicStrK = "painlessMesh/to/756816693";

    //Topics:

    String gatewayRegister  = "HooPDM/from/gatewayReg";
    String Ack              = "HooPDM/from/StateACK";
    String backTrack        = "HooPDMbackTrack";
    String Hall_3G_A        = "HooPDM/to/756816707";    //Conf_3G_A============
    String Hall_3G_B        = "HooPDM/to/756816936";
    String Hall_3G_C        = "HooPDM/to/759587720";
    String Hall_2G_A        = "HooPDM/to/756848281";    //Office_2G_A==========
    String Breaker_4G_A     = "HooPDM/to/2491745745";
    String Conf_3G_A        = "HooPDM/to/756816688";    //Hall_3G_A============
    String Office_Fan_A     = "HooPDM/to/3156690853";   //Hall_Fan_A===========
    String Hall_Fan_A       = "HooPDM/to/839050764";    //Office_Fan_A===========

    String WashRoom1_2G_A   = "HooPDM/to/756848243";
    String WashRoom1_1G_A   = "HooPDM/to/987573640";
    String WashRoom2_2G_A   = "HooPDM/to/756848278";
    String WashRoom2_1G_A   = "HooPDM/to/987571783";
    String Office_3G_A      = "HooPDM/to/756816775";    //Hall_3G_D============
    String Breaker_AllOnOff = "HooPDM/to/broadcast";


//987571783
    // State of Apps:

    String Hall_3G_A_1        = "OF1";
    String Hall_3G_A_2        = "OF2";
    String Hall_3G_A_3        = "OF3";

    String Hall_3G_B_1        = "OF1";
    String Hall_3G_B_2        = "OF2";
    String Hall_3G_B_3        = "OF3";

    String Hall_3G_C_1        = "OF1";
    String Hall_3G_C_2        = "OF2";
    String Hall_3G_C_3        = "OF3";

    String Hall_2G_A_1        = "OF1";
    String Hall_2G_A_2        = "OF3";


    String Conf_3G_A_1        = "OF1";
    String Conf_3G_A_2        = "OF2";
    String Conf_3G_A_3        = "OF3";




    String Breaker_4G_A_1     = "OF1";
    String Breaker_4G_A_2     = "OF2";
    String Breaker_4G_A_3     = "OF3";
    String Breaker_4G_A_4     = "OF4";

    String WashRoom1_2G_A_1   = "OF1";
    String WashRoom1_2G_A_2   = "OF3";
    String WashRoom1_1G_A_1   = "OF2";


    String WashRoom2_2G_A_1   = "OF1";
    String WashRoom2_2G_A_2   = "OF3";
    String WashRoom2_1G_A_1   = "OF2";


    String Office_3G_A_1      = "OF1";
    String Office_3G_A_2      = "OF2";
    String Office_3G_A_3      = "OF3";

    String Office_Fan_A_State        = "OF2";
    String Office_Fan_A_Level        = "FanLvl1";

    String Hall_Fan_A_State        = "OF2";
    String Hall_Fan_A_Level        = "FanLvl1";



// BackTrack States

    String BackTrack_Hall_3G_A_1        = "OF1";
    String BackTrack_Hall_3G_A_2        = "OF2";
    String BackTrack_Hall_3G_A_3        = "OF3";

    String BackTrack_Hall_3G_B_1        = "OF1";
    String BackTrack_Hall_3G_B_2        = "OF2";
    String BackTrack_Hall_3G_B_3        = "OF3";

    String BackTrack_Hall_3G_C_1        = "OF1";
    String BackTrack_Hall_3G_C_2        = "OF2";
    String BackTrack_Hall_3G_C_3        = "OF3";

    String BackTrack_Hall_2G_A_1        = "OF1";
    String BackTrack_Hall_2G_A_2        = "OF3";


    String BackTrack_Conf_3G_A_1        = "OF1";
    String BackTrack_Conf_3G_A_2        = "OF2";
    String BackTrack_Conf_3G_A_3        = "OF3";



    String BackTrack_Breaker_4G_A_1     = "OF1";
    String BackTrack_Breaker_4G_A_2     = "OF2";
    String BackTrack_Breaker_4G_A_3     = "OF3";
    String BackTrack_Breaker_4G_A_4     = "OF4";

    String BackTrack_WashRoom1_2G_A_1   = "OF1";
    String BackTrack_WashRoom1_2G_A_2   = "OF3";
    String BackTrack_WashRoom1_1G_A_1   = "OF2";


    String BackTrack_WashRoom2_2G_A_1   = "OF1";
    String BackTrack_WashRoom2_2G_A_2   = "OF3";
    String BackTrack_WashRoom2_1G_A_1   = "OF2";


    String BackTrack_Office_3G_A_1      = "OF1";
    String BackTrack_Office_3G_A_2      = "OF2";
    String BackTrack_Office_3G_A_3      = "OF3";

    String BackTrack_Office_Fan_A_State        = "FanOFF";
    String BackTrack_Office_Fan_A_Level        = "FanLvl1";
    String BackTrack_Hall_Fan_A_State        = "FanOFF";
    String BackTrack_Hall_Fan_A_Level        = "FanLvl1";


    io.moquette.server.Server server = new io.moquette.server.Server();

    final Handler handler = new Handler();

    TextView log;

    IMqttToken token;
    Button logoButton, homeButton, scenesButton;
    public Button temperatureHolder, humidityHolder;

    String ssid;

    String JSON_STRING = "{\"employee\":{\"name\":\"Abhishek Saini\",\"salary\":65000}}";

    // Below edittext and button are all exist in the popup dialog view.
    private View addRoomDV = null;
    // Contains user name data.
    private EditText roomNameTextBox = null;
    // Click this button in popup dialog to save user input data in above three edittext.
    private Button addRoomButton = null;
    // Click this button to cancel edit user data.
    private Button cancelAddRoomButton = null;





    private static final int LOCATION = 1;
    protected void onStart() {
        super.onStart();
        //Assume you want to read the SSID when the activity is started
        tryToReadSSID();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED && requestCode == LOCATION){
            //User allowed the location and you can read it now
            tryToReadSSID();
        }
    }

    private void tryToReadSSID() {
        //If requested permission isn't Granted yet
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Request permission from user
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION);
        }else{//Permission already granted
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            if(wifiInfo.getSupplicantState() == SupplicantState.COMPLETED){
                ssid = wifiInfo.getSSID().replace("\"", ""); //Here you can access your SSID
//                Toast.makeText(HomeActivity.this, ssid, Toast.LENGTH_LONG).show();
//                log.setText(ssid);
            }
            else{
                ssid = "Unknown SSID x";
//                log.setText(ssid);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Shared Preferences:
        pref = getApplicationContext().getSharedPreferences("HooPref", MODE_PRIVATE);
        editor = pref.edit();


        //No Notification bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



        setContentView(R.layout.activity_home);

//        log =findViewById(R.id.textView4);
//        log.setText("Hello test String");

        //This should be called inside onCreate
        LinearLayout accessPanel = (LinearLayout) findViewById(R.id.accessPanel);
        LinearLayout sololoads = (LinearLayout) findViewById(R.id.soloLoadsPanel);


        temperatureHolder = findViewById(R.id.button10);
        humidityHolder = findViewById(R.id.button9);



        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


//        ConstraintLayout constraintLayout = findViewById(R.id.layout);
//        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
//        animationDrawable.setEnterFadeDuration(2000);
//        animationDrawable.setExitFadeDuration(4000);
//        animationDrawable.start();
        logoButton = findViewById(R.id.button15);
//        homeButton = findViewById(R.id.button12);
//        scenesButton = findViewById(R.id.button14);


        View view;
        Button button1;

        logoButton.setBackgroundResource(R.drawable.sun);


        view = View.inflate(HomeActivity.this, R.layout.menux, null);
        button1 = view.findViewById(R.id.button13);
        button1.setBackgroundResource(R.drawable.tile_red);
        button1.setCompoundDrawablesWithIntrinsicBounds( 0, R.drawable.home_36_w, 0, 0);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new RoomsFragment(), null).commit();
            }
        });
        accessPanel.addView(view);

//        view = View.inflate(HomeActivity.this, R.layout.menux, null);
//        button1 = view.findViewById(R.id.button13);
//        button1.setBackgroundResource(R.drawable.tile_red);
//        button1.setCompoundDrawablesWithIntrinsicBounds( 0, R.drawable.grain_36_w, 0, 0);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new RoomsFragment(), null).commit();
//            }
//        });
//        accessPanel.addView(view);

//        view = View.inflate(HomeActivity.this, R.layout.menux, null);
//        button1 = view.findViewById(R.id.button13);
//        button1.setBackgroundResource(R.drawable.tile_red);
//        button1.setCompoundDrawablesWithIntrinsicBounds( 0, R.drawable.wifi_36_w, 0, 0);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new RoomsFragment(), null).commit();
//            }
//        });
//        accessPanel.addView(view);

        view = View.inflate(HomeActivity.this, R.layout.menux, null);
        button1 = view.findViewById(R.id.button13);
        button1.setBackgroundResource(R.drawable.tile_red);
        button1.setCompoundDrawablesWithIntrinsicBounds( 0, R.drawable.scenes_36_w, 0, 0);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new controlsScenes(), null).commit();
            }
        });
        accessPanel.addView(view);

        view = View.inflate(HomeActivity.this, R.layout.menux, null);
        button1 = view.findViewById(R.id.button13);
        button1.setBackgroundResource(R.drawable.tile_red);
        button1.setCompoundDrawablesWithIntrinsicBounds( 0, R.drawable.rooms_36_w, 0, 0);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new controlsServices(), null).commit();
            }
        });
        accessPanel.addView(view);



//        view = View.inflate(HomeActivity.this, R.layout.menux, null);
//        button1 = view.findViewById(R.id.button13);
//        button1.setBackgroundResource(R.drawable.tile_red);
//        button1.setCompoundDrawablesWithIntrinsicBounds( 0, R.drawable.contact_36_w, 0, 0);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new RoomsFragment(), null).commit();
//            }
//        });
//        accessPanel.addView(view);

//        view = View.inflate(HomeActivity.this, R.layout.menux, null);
//        button1 = view.findViewById(R.id.button13);
//        button1.setBackgroundResource(R.drawable.tile_red);
//        button1.setCompoundDrawablesWithIntrinsicBounds( 0, R.drawable.sos_36_w, 0, 0);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new RoomsFragment(), null).commit();
//            }
//        });
//        accessPanel.addView(view);



        view = View.inflate(HomeActivity.this, R.layout.menux, null);
        button1 = view.findViewById(R.id.button13);
        button1.setBackgroundResource(R.drawable.tile_red);
        button1.setCompoundDrawablesWithIntrinsicBounds( 0, R.drawable.settings_36_w, 0, 0);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new RoomsFragment(), null).commit();
            }
        });
        accessPanel.addView(view);

//        view = View.inflate(HomeActivity.this, R.layout.menux, null);
//        button1 = view.findViewById(R.id.button13);
//        button1.setBackgroundResource(R.drawable.tile_red);
//        button1.setCompoundDrawablesWithIntrinsicBounds( 0, R.drawable.user_36_w, 0, 0);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new RoomsFragment(), null).commit();
//            }
//        });
//        accessPanel.addView(view);

        view = View.inflate(HomeActivity.this, R.layout.menux, null);
        button1 = view.findViewById(R.id.button13);
        button1.setBackgroundResource(R.drawable.tile_red);
        button1.setCompoundDrawablesWithIntrinsicBounds( 0, R.drawable.add_36_w, 0, 0);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new RoomsFragment(), null).commit();
            }
        });
        accessPanel.addView(view);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        for(int k =0; k<=5; k++){
            view = View.inflate(HomeActivity.this, R.layout.loadx, null);
            button1 = view.findViewById(R.id.button13);
            button1.setBackgroundResource(R.drawable.tile_plt_5);
            button1.setText("Light");
            button1.setCompoundDrawablesWithIntrinsicBounds( 0, R.drawable.bulb_36_w, 0, 0);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new RoomsFragment(), null).commit();
                }
            });
            sololoads.addView(view);
        }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//MQTT Broker
        try {
            String root = getFilesDir().getAbsolutePath()+ File.separator + BrokerConstants.DEFAULT_MOQUETTE_STORE_MAP_DB_FILENAME;
            File file = new File(root);
            if(file.exists()){
                file.delete();
//                Toast.makeText(HomeActivity.this, "OLD FILE DELETED", Toast.LENGTH_LONG).show();
            }
//            Toast.makeText(HomeActivity.this, "ME HERE", Toast.LENGTH_LONG).show();

            //getFilesDir().getAbsolutePath()
            memoryConfig.setProperty(BrokerConstants.PERSISTENT_STORE_PROPERTY_NAME, getFilesDir().getAbsolutePath()+ File.separator + BrokerConstants.DEFAULT_MOQUETTE_STORE_MAP_DB_FILENAME);
            Toast.makeText(HomeActivity.this, "5: "+ memoryConfig.toString(), Toast.LENGTH_LONG).show();
//            log.setText("5: "+ memoryConfig.toString());

            server.startServer(memoryConfig);
            brokerState = "ALIVE";
//            Toast.makeText(HomeActivity.this, "Server Started", Toast.LENGTH_LONG).show();
        }
        catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(HomeActivity.this, "3: "+ e.toString(), Toast.LENGTH_LONG).show();
            log.setText("3: "+ e.toString());
        }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(HomeActivity.this, "4: "+ e.toString(), Toast.LENGTH_LONG).show();
            log.setText("4: "+ e.toString());
        }


//        editor.putString("BackTrack_Hall_3G_A_1", "ON1");
//        editor.putString("BackTrack_Hall_3G_A_2", "ON2");
//        editor.putString("BackTrack_Hall_3G_A_3", "ON3");


        readBackTrackStates();

        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), MQTTHOST, clientId);

        options = new MqttConnectOptions();
        options.setUserName(USERNAME);
        options.setPassword(PASSWORD.toCharArray());


        // Wifi Name Check
//        WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        final WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//        String ssid = wifiInfo.getSSID();



//        Toast.makeText(HomeActivity.this, wifiInfo.toString(), Toast.LENGTH_LONG).show();
//        log.setText(wifiInfo.toString());

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                tryToReadSSID();
//                log.setText(ssid);
//                Toast.makeText(HomeActivity.this, "HereOut  "+ssid +"  "+ DEFAULT_SSID + "  " + brokerState.toString(), Toast.LENGTH_LONG).show();
                if(ssid.compareTo(DEFAULT_SSID)==0 && brokerState.compareTo("DEAD")==0){
                    Toast.makeText(HomeActivity.this, "IN", Toast.LENGTH_LONG).show();
//                    log.setText(ssid+"  IN");
                    server.stopServer();
                    try {
                        String root = getFilesDir().getAbsolutePath()+ File.separator + BrokerConstants.DEFAULT_MOQUETTE_STORE_MAP_DB_FILENAME;
                        File file = new File(root);
                        if(file.exists()){
                            file.delete();
//                Toast.makeText(HomeActivity.this, "OLD FILE DELETED", Toast.LENGTH_LONG).show();
                        }
//            Toast.makeText(HomeActivity.this, "ME HERE", Toast.LENGTH_LONG).show();

                        memoryConfig = new MemoryConfig(new Properties());//getFilesDir().getAbsolutePath()
                        memoryConfig.setProperty(BrokerConstants.PERSISTENT_STORE_PROPERTY_NAME, getFilesDir().getAbsolutePath()+ File.separator + BrokerConstants.DEFAULT_MOQUETTE_STORE_MAP_DB_FILENAME);
//                        Toast.makeText(HomeActivity.this, memoryConfig.toString(), Toast.LENGTH_LONG).show();

                        server.startServer(memoryConfig);
                        brokerState = "ALIVE";
                        Toast.makeText(HomeActivity.this, "ALIVE",  Toast.LENGTH_LONG).show();
                        Toast.makeText(HomeActivity.this, "Server Started", Toast.LENGTH_LONG).show();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(HomeActivity.this, "1: "+ e.toString(), Toast.LENGTH_LONG).show();
                        log.setText("1: "+ e.toString());
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(HomeActivity.this, "2: "+ e.toString(), Toast.LENGTH_LONG).show();
                        log.setText("2: "+ e.toString());
//            log.setText(e.toString());
                    }
                }

                //Starting the client again
                try {
                    token = client.connect(options);

                    token.setActionCallback(new IMqttActionListener() {

                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
//                            log.setText("Connection Established:");
//                            Toast.makeText(HomeActivity.this, "connected", Toast.LENGTH_LONG).show();

                            //Here I am subscribing to a topic of choice after success of connection to brocker
                            setSubscription();
                        }
                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//                            Toast.makeText(HomeActivity.this, "connection failed", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (MqttException e) {
                    e.printStackTrace();
                }


                handler.postDelayed(this, 10000);


            }
        }, 10000);  //the time is in milliseconds


        try {
            token = client.connect(options);

            token.setActionCallback(new IMqttActionListener() {

                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
//                    Toast.makeText(HomeActivity.this, "connected", Toast.LENGTH_LONG).show();

                    //Here I am subscribing to a topic of choice after success of connection to brocker
                    setSubscription();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(HomeActivity.this, "connection failed", Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
//                log.setText("Connection Lost:" + cause.toString());
                try {
                    token = client.connect(options);

                    token.setActionCallback(new IMqttActionListener() {

                        @Override
                        public void onSuccess(IMqttToken asyncActionToken) {
//                            log.setText("Connection Established:");
//                            Toast.makeText(HomeActivity.this, "connected", Toast.LENGTH_LONG).show();

                            //Here I am subscribing to a topic of choice after success of connection to brocker
                            setSubscription();
                        }
                        @Override
                        public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
//                            Toast.makeText(HomeActivity.this, "connection failed", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (MqttException e) {
                    e.printStackTrace();
                }


                tryToReadSSID();
                if(ssid.compareTo(DEFAULT_SSID)==0){
//                    log.setText("SSID YES" + cause.toString());
                }
                else{
                    brokerState="DEAD";
                }
//                Toast.makeText(HomeActivity.this, cause.toString(),  Toast.LENGTH_LONG).show();
//                if(cause.toString().contains("Software caused connection abort")){
//
//
//                   brokerState="DEAD";
//                    Toast.makeText(HomeActivity.this, "DEAD",  Toast.LENGTH_LONG).show();
//                }
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Toast.makeText(HomeActivity.this, "AAAAA" + ";;;;;" + "AAAAA", Toast.LENGTH_LONG).show();
//                subText.setText(new String(message.getPayload()));
//                log.setText(topic);
                _message = new String(message.getPayload());


//                Toast.makeText(HomeActivity.this, _message, Toast.LENGTH_LONG).show();

                if(_message.contains("@")){
                    Toast.makeText(HomeActivity.this, "BBBB" + ";;;;;" + "", Toast.LENGTH_LONG).show();
                    setBackTrack(topic, _message);
                }else if(_message.contains("&")){
                    Toast.makeText(HomeActivity.this, "CCCC" + ";;;;;" + "", Toast.LENGTH_LONG).show();
                    setBackTrack_FromAck(topic, _message);
                }else if(topic.contains("HooPDM/from/gatewayTemp_Hum")){
//                    Toast.makeText(HomeActivity.this, "DDDD" + ";;;;;" + "", Toast.LENGTH_LONG).show();
                    parts = _message.split(",");
                    temperatureHolder.setText(parts[0] + " \u2103 Temperature");

                    humidityHolder.setText(parts[0] + " % Humidity");
                }else if(topic.contains("HooPDM/from/gatewayReg")) {
//                    Toast.makeText(HomeActivity.this, "EEEE" + ";;;;;" + "", Toast.LENGTH_LONG).show();
                    parts = _message.split(":");
                    String deviceID = parts[0].replace("$", "");
                    String deviceType = parts[1];
                    ReceiveUnassignedHoolet(deviceID, deviceType);
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
            }
        });



        fragmentManager = getSupportFragmentManager();
        if(findViewById(R.id.fragment_container)!=null){
            if(savedInstanceState!=null){
                return;
            }
        }
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        final RoomsFragment roomsFragment = new RoomsFragment();
        fragmentTransaction.add(R.id.fragment_container, roomsFragment, null );
        fragmentTransaction.commit();

        getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.HomeFragment));
//        ImageView iconView1 = findViewById(R.id.imageView2);
//        ImageView iconView2 = findViewById(R.id.imageView3);
//        ImageView iconView3 = findViewById(R.id.imageView4);



//        ImageView iconHome = findViewById(R.id.homeImage);

//        iconView1.setImageResource(R.drawable.cloud);
//        iconView2.setImageResource(R.drawable.sun);
//        iconView3.setImageResource(R.drawable.humidw);


//        settingsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new RoomsFragment(), null).commit();
//            }
//        });
//        homeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new RoomsFragment(), null).commit();
//
//            }
//        });
//        scenesButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new RoomsFragment(), null).commit();
////                fragmentTransaction.hide(roomsFragment);
//                HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new controlsScenes(), null).commit();
//            }
//        });

//        Toast.makeText(HomeActivity.this, "End Stage", Toast.LENGTH_LONG).show();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                // Create a AlertDialog Builder.
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HomeActivity.this);
                // Set title, icon, can not cancel properties.
                alertDialogBuilder.setTitle("Add Room");
                alertDialogBuilder.setIcon(R.drawable.ic_launcher_background);
                alertDialogBuilder.setCancelable(false);
                LayoutInflater layoutInflater = LayoutInflater.from(HomeActivity.this);
                // Inflate the popup dialog from a layout xml file.
                addRoomDV = layoutInflater.inflate(R.layout.add_rooms_dialog, null);
                roomNameTextBox = addRoomDV.findViewById(R.id.roomName);
                addRoomButton = addRoomDV.findViewById(R.id.btn_add_room);
                cancelAddRoomButton = addRoomDV.findViewById(R.id.btn_cancel_room);
                // Set the inflated layout view object to the AlertDialog builder.
                alertDialogBuilder.setView(addRoomDV);
                // Create AlertDialog and show.
                final AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                // When user click the save user data button in the popup dialog.
                addRoomButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String newRoomName = roomNameTextBox.getText().toString();
                        if(newRoomName.compareTo("")!=0) {
                            String newRoomKey = Registration.mDatabase.child("sites").child(Registration.siteKey).child("rooms").push().getKey();
                            Registration.mDatabase.child("sites").child(Registration.siteKey).child("rooms").child(newRoomKey).child("name").setValue(newRoomName);
                            alertDialog.cancel();
                        }
                        else{
                            alertDialog.cancel();
                        }
                    }
                });

                cancelAddRoomButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.cancel();
                    }
                });
            }
        });
    }

//    public void MYS1(){
//        String message = "ON1";
//        try {
//            client.publish(hall3G1, message.getBytes(), 0, false);
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//    }

//    public void MYS0(){
//        String message = "OFF1";
//        try {
//            client.publish(hall3G1, message.getBytes(), 0, false);
//        } catch (MqttException e) {
//            e.printStackTrace();
//        }
//    }

    public void ReceiveUnassignedHoolet(String deviceId, String devicetype){

        Registration.uaSwitchRef.child("d"+deviceId).setValue(true);

        Registration.switchPropsRef.child("d"+deviceId).child("type").setValue(devicetype);
        Registration.switchPropsRef.child("d"+deviceId).child("name").setValue("Lights");

        Registration.switchStatesRef.child("d"+deviceId).child("g1").setValue("OF1");
        Registration.switchStatesRef.child("d"+deviceId).child("g2").setValue("OF2");
        Registration.switchStatesRef.child("d"+deviceId).child("g3").setValue("OF3");
        Registration.switchStatesRef.child("d"+deviceId).child("g4").setValue("OF4");
        Registration.switchStatesRef.child("d"+deviceId).child("g5").setValue("OF5");
        Registration.switchStatesRef.child("d"+deviceId).child("g6").setValue("OF6");
        Registration.switchStatesRef.child("d"+deviceId).child("g7").setValue("OF7");
        Registration.switchStatesRef.child("d"+deviceId).child("g8").setValue("OF8");
        Registration.switchStatesRef.child("d"+deviceId).child("s1").setValue("OF1");
        Registration.switchStatesRef.child("d"+deviceId).child("s2").setValue("OF2");
        Registration.switchStatesRef.child("d"+deviceId).child("u1").setValue("OF1");
        Registration.switchStatesRef.child("d"+deviceId).child("u2").setValue("OF2");

        isAddedRemoved = true;
    }


    public void setBackTrack(String backTrack_topic, String backTrack_message) {

        String SwitchNumber= "T1";
        backTrack_message = backTrack_message.replace("@", "");
        String[] messageParts = backTrack_message.split(",");

//        messageParts[0] == "756816707"
//        messageParts[1] == "T1"
//        messageParts[2] == "0" "1"

//        Toast.makeText(HomeActivity.this, "BT Enter "+ messageParts[0], Toast.LENGTH_LONG).show();




        if(messageParts[1].contains("T1")){
            SwitchNumber = "T1";
        }else if(messageParts[1].contains("T2")){
            SwitchNumber = "T2";
        }else if(messageParts[1].contains("T3")){
            SwitchNumber = "T3";
        }

        if (messageParts[0].compareTo("756816688")==0) {
            if (SwitchNumber.compareTo("T1")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_Conf_3G_A_1 = "OF1";
                    editor.putString("BackTrack_Conf_3G_A_1", "OF1");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B1-0", Toast.LENGTH_LONG).show();

                } else if (messageParts[2].compareTo("1")==0) {
                    BackTrack_Conf_3G_A_1 = "ON1";
                    editor.putString("BackTrack_Conf_3G_A_1", "ON1");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B1-1", Toast.LENGTH_LONG).show();
                }
            }else  if (SwitchNumber.compareTo("T2")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_Conf_3G_A_2 = "OF2";
                    editor.putString("BackTrack_Conf_3G_A_2", "OF2");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B2-0", Toast.LENGTH_LONG).show();

                } else  if (messageParts[2].compareTo("1")==0) {
                    BackTrack_Conf_3G_A_2 = "ON2";
                    editor.putString("BackTrack_Conf_3G_A_2", "ON2");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B2-1", Toast.LENGTH_LONG).show();

                }
            }else if (SwitchNumber.compareTo("T3")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_Conf_3G_A_3 = "OF3";
                    editor.putString("BackTrack_Conf_3G_A_3", "OF3");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B3-0", Toast.LENGTH_LONG).show();

                } else if (messageParts[2].compareTo("1")==0) {
                    BackTrack_Conf_3G_A_3 = "ON3";
                    editor.putString("BackTrack_Conf_3G_A_3", "ON3");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B3-1", Toast.LENGTH_LONG).show();

                }
            }
            //Opening The Main Rooms Fragment when change occurs from hardware
            HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new RoomsFragment(), null).commit();
        }else if (messageParts[0].compareTo("756816707")==0) {
            if (SwitchNumber.compareTo("T1")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_Hall_3G_A_1 = "OF1";
                    editor.putString("BackTrack_Hall_3G_A_1", "OF1");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "Hall B1-0", Toast.LENGTH_LONG).show();

                } else if (messageParts[2].compareTo("1")==0) {
                    BackTrack_Hall_3G_A_1 = "ON1";
                    editor.putString("BackTrack_Hall_3G_A_1", "ON1");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "Hall B1-1", Toast.LENGTH_LONG).show();
                }
            }else  if (SwitchNumber.compareTo("T2")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_Hall_3G_A_2 = "OF2";
                    editor.putString("BackTrack_Hall_3G_A_2", "OF2");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "Hall B2-0", Toast.LENGTH_LONG).show();

                } else  if (messageParts[2].compareTo("1")==0) {
                    BackTrack_Hall_3G_A_2 = "ON2";
                    editor.putString("BackTrack_Hall_3G_A_2", "ON2");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "Hall B2-1", Toast.LENGTH_LONG).show();

                }
            }else if (SwitchNumber.compareTo("T3")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_Hall_3G_A_3 = "OF3";
                    editor.putString("BackTrack_Hall_3G_A_3", "OF3");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "Hall B3-0", Toast.LENGTH_LONG).show();

                } else if (messageParts[2].compareTo("1")==0) {
                    BackTrack_Hall_3G_A_3 = "ON3";
                    editor.putString("BackTrack_Hall_3G_A_3", "ON3");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "Hall B3-1", Toast.LENGTH_LONG).show();

                }
            }
            //Opening The Main Rooms Fragment when change occurs from hardware
            HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new RoomsFragment(), null).commit();
        }else if (messageParts[0].compareTo("756816936")==0) {
            if (SwitchNumber.compareTo("T1")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_Hall_3G_B_1 = "OF1";
                    editor.putString("BackTrack_Hall_3G_B_1", "OF1");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B1-0", Toast.LENGTH_LONG).show();

                } else if (messageParts[2].compareTo("1")==0) {
                    BackTrack_Hall_3G_B_1 = "ON1";
                    editor.putString("BackTrack_Hall_3G_B_1", "ON1");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B1-1", Toast.LENGTH_LONG).show();
                }
            }else  if (SwitchNumber.compareTo("T2")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_Hall_3G_B_2 = "OF2";
                    editor.putString("BackTrack_Hall_3G_B_2", "OF2");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B2-0", Toast.LENGTH_LONG).show();

                } else  if (messageParts[2].compareTo("1")==0) {
                    BackTrack_Hall_3G_B_2 = "ON2";
                    editor.putString("BackTrack_Hall_3G_B_2", "ON2");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B2-1", Toast.LENGTH_LONG).show();

                }
            }else if (SwitchNumber.compareTo("T3")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_Hall_3G_B_3 = "OF3";
                    editor.putString("BackTrack_Hall_3G_B_3", "OF3");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B3-0", Toast.LENGTH_LONG).show();

                } else if (messageParts[2].compareTo("1")==0) {
                    BackTrack_Hall_3G_B_3 = "ON3";
                    editor.putString("BackTrack_Hall_3G_B_3", "ON3");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B3-1", Toast.LENGTH_LONG).show();

                }
            }
            //Opening The Main Rooms Fragment when change occurs from hardware
            HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new RoomsFragment(), null).commit();
        }else if (messageParts[0].compareTo("2491745745")==0) {
            if (SwitchNumber.compareTo("T1")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_Hall_3G_B_1 = "OF1";
                    editor.putString("BackTrack_Breaker_4G_A_1", "OF1");
                    editor.apply();

                } else if (messageParts[2].compareTo("1")==0) {
                    BackTrack_Hall_3G_B_1 = "ON1";
                    editor.putString("BackTrack_Breaker_4G_A_1", "ON1");
                    editor.apply();
                }
            }else  if (SwitchNumber.compareTo("T2")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_Hall_3G_B_2 = "OF2";
                    editor.putString("BackTrack_Breaker_4G_A_2", "OF2");
                    editor.apply();

                } else  if (messageParts[2].compareTo("1")==0) {
                    BackTrack_Hall_3G_B_2 = "ON2";
                    editor.putString("BackTrack_Breaker_4G_A_2", "ON2");
                    editor.apply();
                }
            }else if (SwitchNumber.compareTo("T3")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_Hall_3G_B_3 = "OF3";
                    editor.putString("BackTrack_Breaker_4G_A_3", "OF3");
                    editor.apply();

                } else if (messageParts[2].compareTo("1")==0) {
                    BackTrack_Hall_3G_B_3 = "ON3";
                    editor.putString("BackTrack_Breaker_4G_A_3", "ON3");
                    editor.apply();
                }
            }else if (SwitchNumber.compareTo("T4")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_Hall_3G_B_3 = "OF4";
                    editor.putString("BackTrack_Breaker_4G_A_4", "OF4");
                    editor.apply();

                } else if (messageParts[2].compareTo("1")==0) {
                    BackTrack_Hall_3G_B_3 = "ON3";
                    editor.putString("BackTrack_Breaker_4G_A_4", "ON4");
                    editor.apply();
                }
            }
            //Opening The Main Rooms Fragment when change occurs from hardware
            HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new RoomsFragment(), null).commit();
        }
        else if (messageParts[0].compareTo("839050764")==0) {                     // Hall Fan A
            if (SwitchNumber.compareTo("DIM")==0) {
                if (messageParts[2].compareTo("FanOFF")==0) {
                    BackTrack_Hall_Fan_A_State = "FanOFF";
                    editor.putString("BackTrack_Hall_Fan_A_State", "FanOFF");
                    editor.apply();
                } else if (messageParts[2].compareTo("FanON")==0) {
                    BackTrack_Hall_Fan_A_State = "FanON";
                    editor.putString("BackTrack_Hall_Fan_A_State", "FanON");
                    editor.apply();
                } else if (messageParts[2].compareTo("FanLvl1")==0) {
                    BackTrack_Hall_Fan_A_State = "FanLvl1";
                    editor.putString("BackTrack_Hall_Fan_A_State", "FanLvl1");
                    editor.apply();
                } else if (messageParts[2].compareTo("FanLvl2")==0) {
                    BackTrack_Hall_Fan_A_State = "FanLvl2";
                    editor.putString("BackTrack_Hall_Fan_A_State", "FanLvl2");
                    editor.apply();
                } else if (messageParts[2].compareTo("FanLvl3")==0) {
                    BackTrack_Hall_Fan_A_State = "FanLvl3";
                    editor.putString("BackTrack_Hall_Fan_A_State", "FanLvl3");
                    editor.apply();
                } else if (messageParts[2].compareTo("FanLvl4")==0) {
                    BackTrack_Hall_Fan_A_State = "FanLvl4";
                    editor.putString("BackTrack_Hall_Fan_A_State", "FanLvl4");
                    editor.apply();
                } else if (messageParts[2].compareTo("FanLvl5")==0) {
                    BackTrack_Hall_Fan_A_State = "FanLvl5";
                    editor.putString("BackTrack_Hall_Fan_A_State", "FanLvl5");
                    editor.apply();
                }
            }

        }
        else if (messageParts[0].compareTo("3156690853")==0) {                     // Office Fan A
            if (SwitchNumber.compareTo("DIM")==0) {
                if (messageParts[2].compareTo("FanOFF")==0) {
                    BackTrack_Office_Fan_A_State = "FanOFF";
                    editor.putString("BackTrack_Office_Fan_A_State", "FanOFF");
                    editor.apply();
                } else if (messageParts[2].compareTo("FanON")==0) {
                    BackTrack_Office_Fan_A_State = "FanON";
                    editor.putString("BackTrack_Office_Fan_A_State", "FanON");
                    editor.apply();
                } else if (messageParts[2].compareTo("FanLvl1")==0) {
                    BackTrack_Office_Fan_A_Level = "FanLvl1";
                    editor.putString("BackTrack_Office_Fan_A_Level", "FanLvl1");
                    editor.apply();
                } else if (messageParts[2].compareTo("FanLvl2")==0) {
                    BackTrack_Office_Fan_A_Level = "FanLvl2";
                    editor.putString("BackTrack_Office_Fan_A_Level", "FanLvl2");
                    editor.apply();
                } else if (messageParts[2].compareTo("FanLvl3")==0) {
                    BackTrack_Office_Fan_A_Level = "FanLvl3";
                    editor.putString("BackTrack_Office_Fan_A_Level", "FanLvl3");
                    editor.apply();
                } else if (messageParts[2].compareTo("FanLvl4")==0) {
                    BackTrack_Office_Fan_A_Level = "FanLvl4";
                    editor.putString("BackTrack_Office_Fan_A_Level", "FanLvl4");
                    editor.apply();
                } else if (messageParts[2].compareTo("FanLvl5")==0) {
                    BackTrack_Office_Fan_A_Level = "FanLvl5";
                    editor.putString("BackTrack_Office_Fan_A_Level", "FanLvl5");
                    editor.apply();
                }
            }
            
        }else if (messageParts[0].compareTo("759587720")==0) {
            if (SwitchNumber.compareTo("T1")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_Hall_3G_C_1 = "OF1";
                    editor.putString("BackTrack_Hall_3G_C_1", "OF1");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B1-0", Toast.LENGTH_LONG).show();

                } else if (messageParts[2].compareTo("1")==0) {
                    BackTrack_Hall_3G_C_1 = "ON1";
                    editor.putString("BackTrack_Hall_3G_C_1", "ON1");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B1-1", Toast.LENGTH_LONG).show();
                }
            }else  if (SwitchNumber.compareTo("T2")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_Hall_3G_C_2 = "OF2";
                    editor.putString("BackTrack_Hall_3G_C_2", "OF2");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B2-0", Toast.LENGTH_LONG).show();

                } else  if (messageParts[2].compareTo("1")==0) {
                    BackTrack_Hall_3G_C_2 = "ON2";
                    editor.putString("BackTrack_Hall_3G_C_2", "ON2");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B2-1", Toast.LENGTH_LONG).show();

                }
            }else if (SwitchNumber.compareTo("T3")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_Hall_3G_C_3 = "OF3";
                    editor.putString("BackTrack_Hall_3G_C_3", "OF3");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B3-0", Toast.LENGTH_LONG).show();

                } else if (messageParts[2].compareTo("1")==0) {
                    BackTrack_Hall_3G_C_3 = "ON3";
                    editor.putString("BackTrack_Hall_3G_C_3", "ON3");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B3-1", Toast.LENGTH_LONG).show();

                }
            }
            //Opening The Main Rooms Fragment when change occurs from hardware
            HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new RoomsFragment(), null).commit();
        }else if (messageParts[0].compareTo("756848281")==0) {
            if (SwitchNumber.compareTo("T1")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_Hall_2G_A_1 = "OF1";
                    editor.putString("BackTrack_Hall_2G_A_1", "OF1");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B1-0", Toast.LENGTH_LONG).show();

                } else if (messageParts[2].compareTo("1")==0) {
                    BackTrack_Hall_2G_A_1 = "ON1";
                    editor.putString("BackTrack_Hall_2G_A_1", "ON1");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B1-1", Toast.LENGTH_LONG).show();
                }
            }else  if (SwitchNumber.compareTo("T2")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_Hall_2G_A_2 = "OF3";
                    editor.putString("BackTrack_Hall_2G_A_2", "OF3");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B2-0", Toast.LENGTH_LONG).show();

                } else  if (messageParts[2].compareTo("1")==0) {
                    BackTrack_Hall_2G_A_2 = "ON3";
                    editor.putString("BackTrack_Hall_2G_A_2", "ON3");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B2-1", Toast.LENGTH_LONG).show();

                }
            }
            //Opening The Main Rooms Fragment when change occurs from hardware
            HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new RoomsFragment(), null).commit();
        }else if (messageParts[0].compareTo("756848243")==0) {
            if (SwitchNumber.compareTo("T1")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_WashRoom1_2G_A_1 = "OF1";
                    editor.putString("BackTrack_WashRoom1_2G_A_1", "OF1");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B1-0", Toast.LENGTH_LONG).show();

                } else if (messageParts[2].compareTo("1")==0) {
                    BackTrack_WashRoom1_2G_A_1 = "ON1";
                    editor.putString("BackTrack_WashRoom1_2G_A_1", "ON1");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B1-1", Toast.LENGTH_LONG).show();
                }
            }else  if (SwitchNumber.compareTo("T2")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_WashRoom1_2G_A_2 = "OF3";
                    editor.putString("BackTrack_WashRoom1_2G_A_2", "OF3");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B2-0", Toast.LENGTH_LONG).show();

                } else  if (messageParts[2].compareTo("1")==0) {
                    BackTrack_WashRoom1_2G_A_2 = "ON3";
                    editor.putString("BackTrack_WashRoom1_2G_A_2", "ON3");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B2-1", Toast.LENGTH_LONG).show();

                }
            }
            //Opening The Main Rooms Fragment when change occurs from hardware
            HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new RoomsFragment(), null).commit();
        }else if (messageParts[0].compareTo("987573640")==0) {
            if (SwitchNumber.compareTo("T1")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_WashRoom1_1G_A_1 = "OF2";
                    editor.putString("BackTrack_WashRoom1_1G_A_1", "OF2");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B1-0", Toast.LENGTH_LONG).show();

                } else if (messageParts[2].compareTo("1")==0) {
                    BackTrack_WashRoom1_1G_A_1 = "ON2";
                    editor.putString("BackTrack_WashRoom1_1G_A_1", "ON2");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B1-1", Toast.LENGTH_LONG).show();
                }
            }
            //Opening The Main Rooms Fragment when change occurs from hardware
            HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new RoomsFragment(), null).commit();
        }else if (messageParts[0].compareTo("756848278")==0) {
            if (SwitchNumber.compareTo("T1")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_WashRoom2_2G_A_1 = "OF1";
                    editor.putString("BackTrack_WashRoom2_2G_A_1", "OF1");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B1-0", Toast.LENGTH_LONG).show();

                } else if (messageParts[2].compareTo("1")==0) {
                    BackTrack_WashRoom2_2G_A_1 = "ON1";
                    editor.putString("BackTrack_WashRoom2_2G_A_1", "ON1");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B1-1", Toast.LENGTH_LONG).show();
                }
            }else  if (SwitchNumber.compareTo("T2")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_WashRoom2_2G_A_2 = "OF3";
                    editor.putString("BackTrack_WashRoom2_2G_A_2", "OF3");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B2-0", Toast.LENGTH_LONG).show();

                } else  if (messageParts[2].compareTo("1")==0) {
                    BackTrack_WashRoom2_2G_A_2 = "ON3";
                    editor.putString("BackTrack_WashRoom2_2G_A_2", "ON3");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B2-1", Toast.LENGTH_LONG).show();

                }
            }
            //Opening The Main Rooms Fragment when change occurs from hardware
            HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new RoomsFragment(), null).commit();
        }
        else if (messageParts[0].compareTo("987571783")==0) {
            if (SwitchNumber.compareTo("T1")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_WashRoom2_1G_A_1 = "OF2";
                    editor.putString("BackTrack_WashRoom2_1G_A_1", "OF2");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B1-0", Toast.LENGTH_LONG).show();

                } else if (messageParts[2].compareTo("1")==0) {
                    BackTrack_WashRoom2_1G_A_1 = "ON2";
                    editor.putString("BackTrack_WashRoom2_1G_A_1", "ON2");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B1-1", Toast.LENGTH_LONG).show();
                }
            }
            //Opening The Main Rooms Fragment when change occurs from hardware
            HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new RoomsFragment(), null).commit();
        }else if (messageParts[0].compareTo("756816775")==0) {
            if (SwitchNumber.compareTo("T1")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_Office_3G_A_1 = "OF1";
                    editor.putString("BackTrack_Office_3G_A_1", "OF1");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B1-0", Toast.LENGTH_LONG).show();

                } else if (messageParts[2].compareTo("1")==0) {
                    BackTrack_Office_3G_A_1 = "ON1";
                    editor.putString("BackTrack_Office_3G_A_1", "ON1");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B1-1", Toast.LENGTH_LONG).show();
                }
            }else  if (SwitchNumber.compareTo("T2")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_Office_3G_A_2 = "OF2";
                    editor.putString("BackTrack_Office_3G_A_2", "OF2");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B2-0", Toast.LENGTH_LONG).show();

                } else  if (messageParts[2].compareTo("1")==0) {
                    BackTrack_Office_3G_A_2 = "ON2";
                    editor.putString("BackTrack_Office_3G_A_2", "ON2");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B2-1", Toast.LENGTH_LONG).show();

                }
            }else if (SwitchNumber.compareTo("T3")==0) {
                if (messageParts[2].compareTo("0")==0) {
                    BackTrack_Office_3G_A_3 = "OF3";
                    editor.putString("BackTrack_Office_3G_A_3", "OF3");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B3-0", Toast.LENGTH_LONG).show();

                } else if (messageParts[2].compareTo("1")==0) {
                    BackTrack_Office_3G_A_3 = "ON3";
                    editor.putString("BackTrack_Office_3G_A_3", "ON3");
                    editor.apply();
                    Toast.makeText(HomeActivity.this, "B3-1", Toast.LENGTH_LONG).show();

                }
            }
            //Opening The Main Rooms Fragment when change occurs from hardware
            HomeActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container, new RoomsFragment(), null).commit();
        } else {
            Toast.makeText(HomeActivity.this, "ELSE", Toast.LENGTH_LONG).show();
        }

        //***************//
    }

//    String Breaker_4G_A     = "HooPDM/to/2491745745";
//    String WashRoom1_2G_A   = "HooPDM/to/987537730";
//    String WashRoom1_1G_A   = "HooPDM/to/987573640";
//    String WashRoom2_2G_A   = "HooPDM/to/756848278";
//    String WashRoom2_1G_A   = "HooPDM/to/987571783";
//    String Office_3G_A      = "HooPDM/to/756816775";


    public void setBackTrack_FromAck(String backTrack_topic, String backTrack_message) {



        String SwitchNumber= "T1";
        backTrack_message = backTrack_message.replace("&", "");
        backTrack_message = backTrack_message.replace("OK", "");
        String[] messageParts = backTrack_message.split(":");

        if(messageParts[1].contains("1")){
            SwitchNumber = "T1";
        }else if(messageParts[1].contains("2")){
            SwitchNumber = "T2";
        }else if(messageParts[1].contains("3")){
            SwitchNumber = "T3";
        }else if(messageParts[1].contains("4")){
            SwitchNumber = "T4";
        }

//        Toast.makeText(HomeActivity.this, "", Toast.LENGTH_LONG).show();

        if (messageParts[0].compareTo("756816688")==0) {        // Conf 3G A
            if (SwitchNumber.compareTo("T1")==0) {
                if (messageParts[1].compareTo("OF1")==0) {
                    BackTrack_Conf_3G_A_1 = "OF1";
                    editor.putString("BackTrack_Conf_3G_A_1", "OF1");
                    editor.apply();

                } else if (messageParts[1].compareTo("ON1")==0) {
                    BackTrack_Conf_3G_A_1 = "ON1";
                    editor.putString("BackTrack_Conf_3G_A_1", "ON1");
                    editor.apply();

                }
            }else  if (SwitchNumber.compareTo("T2")==0) {
                if (messageParts[1].compareTo("OF2")==0) {
                    BackTrack_Conf_3G_A_2 = "OF2";
                    editor.putString("BackTrack_Conf_3G_A_2", "OF2");
                    editor.apply();

                } else  if (messageParts[1].compareTo("ON2")==0) {
                    BackTrack_Conf_3G_A_2 = "ON2";
                    editor.putString("BackTrack_Conf_3G_A_2", "ON2");
                    editor.apply();

                }
            }else if (SwitchNumber.compareTo("T3")==0) {
                if (messageParts[1].compareTo("OF3")==0) {
                    BackTrack_Conf_3G_A_3 = "OF3";
                    editor.putString("BackTrack_Conf_3G_A_3", "OF3");
                    editor.apply();

                } else if (messageParts[1].compareTo("ON3")==0) {
                    BackTrack_Conf_3G_A_3 = "ON3";
                    editor.putString("BackTrack_Conf_3G_A_3", "ON3");
                    editor.apply();

                }
            }
        }else if (messageParts[0].compareTo("756816707")==0) {          // Hall 3G A
            if (SwitchNumber.compareTo("T1")==0) {
                if (messageParts[1].compareTo("OF1")==0) {
                    BackTrack_Hall_3G_A_1 = "OF1";
                    editor.putString("BackTrack_Hall_3G_A_1", "OF1");
                    editor.apply();

                } else if (messageParts[1].compareTo("ON1")==0) {
                    BackTrack_Hall_3G_A_1 = "ON1";
                    editor.putString("BackTrack_Hall_3G_A_1", "ON1");
                    editor.apply();

                }
            }else  if (SwitchNumber.compareTo("T2")==0) {
                if (messageParts[1].compareTo("OF2")==0) {
                    BackTrack_Hall_3G_A_2 = "OF2";
                    editor.putString("BackTrack_Hall_3G_A_2", "OF2");
                    editor.apply();

                } else  if (messageParts[1].compareTo("ON2")==0) {
                    BackTrack_Hall_3G_A_2 = "ON2";
                    editor.putString("BackTrack_Hall_3G_A_2", "ON2");
                    editor.apply();

                }
            }else if (SwitchNumber.compareTo("T3")==0) {
                if (messageParts[1].compareTo("OF3")==0) {
                    BackTrack_Hall_3G_A_3 = "OF3";
                    editor.putString("BackTrack_Hall_3G_A_3", "OF3");
                    editor.apply();

                } else if (messageParts[1].compareTo("ON3")==0) {
                    BackTrack_Hall_3G_A_3 = "ON3";
                    editor.putString("BackTrack_Hall_3G_A_3", "ON3");
                    editor.apply();

                }
            }
        }else if (messageParts[0].compareTo("756816936")==0) {          // Hall 3G B
            if (SwitchNumber.compareTo("T1")==0) {
                if (messageParts[1].compareTo("OF1")==0) {
                    BackTrack_Hall_3G_B_1 = "OF1";
                    editor.putString("BackTrack_Hall_3G_B_1", "OF1");
                    editor.apply();

                } else if (messageParts[1].compareTo("ON1")==0) {
                    BackTrack_Hall_3G_B_1 = "ON1";
                    editor.putString("BackTrack_Hall_3G_B_1", "ON1");
                    editor.apply();

                }
            }else  if (SwitchNumber.compareTo("T2")==0) {
                if (messageParts[1].compareTo("OF2")==0) {
                    BackTrack_Hall_3G_B_2 = "OF2";
                    editor.putString("BackTrack_Hall_3G_B_2", "OF2");
                    editor.apply();

                } else  if (messageParts[1].compareTo("ON2")==0) {
                    BackTrack_Hall_3G_B_2 = "ON2";
                    editor.putString("BackTrack_Hall_3G_B_2", "ON2");
                    editor.apply();

                }
            }else if (SwitchNumber.compareTo("T3")==0) {
                if (messageParts[1].compareTo("OF3")==0) {
                    BackTrack_Hall_3G_B_3 = "OF3";
                    editor.putString("BackTrack_Hall_3G_B_3", "OF3");
                    editor.apply();

                } else if (messageParts[1].compareTo("ON3")==0) {
                    BackTrack_Hall_3G_B_3 = "ON3";
                    editor.putString("BackTrack_Hall_3G_B_3", "ON3");
                    editor.apply();
                }
            }
        }else if (messageParts[0].compareTo("759587720")==0) {          // Hall 3G C
            if (SwitchNumber.compareTo("T1")==0) {
                if (messageParts[1].compareTo("OF1")==0) {
                    BackTrack_Hall_3G_C_1 = "OF1";
                    editor.putString("BackTrack_Hall_3G_C_1", "OF1");
                    editor.apply();

                } else if (messageParts[1].compareTo("ON1")==0) {
                    BackTrack_Hall_3G_C_1 = "ON1";
                    editor.putString("BackTrack_Hall_3G_C_1", "ON1");
                    editor.apply();

                }
            }else  if (SwitchNumber.compareTo("T2")==0) {
                if (messageParts[1].compareTo("OF2")==0) {
                    BackTrack_Hall_3G_C_2 = "OF2";
                    editor.putString("BackTrack_Hall_3G_C_2", "OF2");
                    editor.apply();

                } else  if (messageParts[1].compareTo("ON2")==0) {
                    BackTrack_Hall_3G_C_2 = "ON2";
                    editor.putString("BackTrack_Hall_3G_C_2", "ON2");
                    editor.apply();

                }
            }else if (SwitchNumber.compareTo("T3")==0) {
                if (messageParts[1].compareTo("OF3")==0) {
                    BackTrack_Hall_3G_C_3 = "OF3";
                    editor.putString("BackTrack_Hall_3G_C_3", "OF3");
                    editor.apply();

                } else if (messageParts[1].compareTo("ON3")==0) {
                    BackTrack_Hall_3G_C_3 = "ON3";
                    editor.putString("BackTrack_Hall_3G_C_3", "ON3");
                    editor.apply();
                }
            }
        }else if (messageParts[0].compareTo("756848281")==0) {          // Hall 2G A
            if (SwitchNumber.compareTo("T1")==0) {
                if (messageParts[1].compareTo("OF1")==0) {
                    BackTrack_Hall_2G_A_1 = "OF1";
                    editor.putString("BackTrack_Hall_2G_A_1", "OF1");
                    editor.apply();

                } else if (messageParts[1].compareTo("ON1")==0) {
                    BackTrack_Hall_2G_A_1 = "ON1";
                    editor.putString("BackTrack_Hall_2G_A_1", "ON1");
                    editor.apply();

                }
            }else  if (SwitchNumber.compareTo("T2")==0) {
                if (messageParts[1].compareTo("OF3")==0) {
                    BackTrack_Hall_2G_A_2 = "OF3";
                    editor.putString("BackTrack_Hall_2G_A_2", "OF3");
                    editor.apply();

                } else  if (messageParts[1].compareTo("ON3")==0) {
                    BackTrack_Hall_2G_A_2 = "ON3";
                    editor.putString("BackTrack_Hall_2G_A_2", "ON3");
                    editor.apply();

                }
            }
        }else if (messageParts[0].compareTo("2491745745")==0) {          // Main Power 4G A
            if (SwitchNumber.compareTo("T1")==0) {
                if (messageParts[1].compareTo("OF1")==0) {
                    BackTrack_Breaker_4G_A_1 = "OF1";
                    editor.putString("BackTrack_Breaker_4G_A_1", "OF1");
                    editor.apply();

                } else if (messageParts[1].compareTo("ON1")==0) {
                    BackTrack_Breaker_4G_A_1 = "ON1";
                    editor.putString("BackTrack_Breaker_4G_A_1", "ON1");
                    editor.apply();

                }
            }else  if (SwitchNumber.compareTo("T2")==0) {
                if (messageParts[1].compareTo("OF2")==0) {
                    BackTrack_Breaker_4G_A_2 = "OF2";
                    editor.putString("BackTrack_Breaker_4G_A_2", "OF2");
                    editor.apply();

                } else  if (messageParts[1].compareTo("ON2")==0) {
                    BackTrack_Breaker_4G_A_2 = "ON2";
                    editor.putString("BackTrack_Breaker_4G_A_2", "ON2");
                    editor.apply();

                }
            }else if (SwitchNumber.compareTo("T3")==0) {
                if (messageParts[1].compareTo("OF3")==0) {
                    BackTrack_Breaker_4G_A_3 = "OF3";
                    editor.putString("BackTrack_Breaker_4G_A_3", "OF3");
                    editor.apply();

                } else if (messageParts[1].compareTo("ON3")==0) {
                    BackTrack_Breaker_4G_A_3 = "ON3";
                    editor.putString("BackTrack_Breaker_4G_A_3", "ON3");
                    editor.apply();
                }
            }else if (SwitchNumber.compareTo("T4")==0) {
                if (messageParts[1].compareTo("OF4")==0) {
                    BackTrack_Breaker_4G_A_4 = "OF4";
                    editor.putString("BackTrack_Breaker_4G_A_4", "OF4");
                    editor.apply();

                } else if (messageParts[1].compareTo("ON4")==0) {
                    BackTrack_Breaker_4G_A_4 = "ON4";
                    editor.putString("BackTrack_Breaker_4G_A_4", "ON4");
                    editor.apply();
                }
            }
        }else if (messageParts[0].compareTo("756816775")==0) {          // Office 3G A
            if (SwitchNumber.compareTo("T1")==0) {
                if (messageParts[1].compareTo("OF1")==0) {
                    BackTrack_Office_3G_A_1 = "OF1";
                    editor.putString("BackTrack_Office_3G_A_1", "OF1");
                    editor.apply();

                } else if (messageParts[1].compareTo("ON1")==0) {
                    BackTrack_Office_3G_A_1 = "ON1";
                    editor.putString("BackTrack_Office_3G_A_1", "ON1");
                    editor.apply();
                }
            }else  if (SwitchNumber.compareTo("T2")==0) {
                if (messageParts[1].compareTo("OF2")==0) {
                    BackTrack_Office_3G_A_2 = "OF2";
                    editor.putString("BackTrack_Office_3G_A_2", "OF2");
                    editor.apply();

                } else  if (messageParts[1].compareTo("ON2")==0) {
                    BackTrack_Office_3G_A_2 = "ON2";
                    editor.putString("BackTrack_Office_3G_A_2", "ON2");
                    editor.apply();
                }
            }else if (SwitchNumber.compareTo("T3")==0) {
                if (messageParts[1].compareTo("OF3")==0) {
                    BackTrack_Office_3G_A_3 = "OF3";
                    editor.putString("BackTrack_Office_3G_A_3", "OF3");
                    editor.apply();

                } else if (messageParts[1].compareTo("ON3")==0) {
                    BackTrack_Office_3G_A_3 = "ON3";
                    editor.putString("BackTrack_Office_3G_A_3", "ON3");
                    editor.apply();
                }
            }
        }else if (messageParts[0].compareTo("3156690853")==0) {          // Hall Fan A
            if (SwitchNumber.compareTo("DIM")==0) {
                if (messageParts[1].compareTo("FanON")==0) {
                    BackTrack_Hall_Fan_A_State = "FanON";
                    editor.putString("BackTrack_Hall_Fan_A_State", "FanON");
                    editor.apply();
                } else if (messageParts[1].compareTo("FanOFF")==0) {
                    BackTrack_Hall_Fan_A_State = "FanOFF";
                    editor.putString("BackTrack_Hall_Fan_A_State", "FanOFF");
                    editor.apply();
                }else if (messageParts[1].compareTo("FanLvl1")==0) {
                    BackTrack_Hall_Fan_A_State = "FanLvl1";
                    editor.putString("BackTrack_Hall_Fan_A_State", "FanLvl1");
                    editor.apply();
                }else if (messageParts[1].compareTo("FanLvl2")==0) {
                    BackTrack_Hall_Fan_A_State = "FanLvl2";
                    editor.putString("BackTrack_Hall_Fan_A_State", "FanLvl2");
                    editor.apply();
                }else if (messageParts[1].compareTo("FanLvl3")==0) {
                    BackTrack_Hall_Fan_A_State = "FanLvl3";
                    editor.putString("BackTrack_Hall_Fan_A_State", "FanLvl3");
                    editor.apply();
                }else if (messageParts[1].compareTo("FanLvl4")==0) {
                    BackTrack_Hall_Fan_A_State = "FanLvl4";
                    editor.putString("BackTrack_Hall_Fan_A_State", "FanLvl4");
                    editor.apply();
                }else if (messageParts[1].compareTo("FanLvl5")==0) {
                    BackTrack_Hall_Fan_A_State = "FanLvl5";
                    editor.putString("BackTrack_Hall_Fan_A_State", "FanLvl5");
                    editor.apply();
                }
            }
        }else if (messageParts[0].compareTo("3156690853")==0) {          // Office Fan A
            if (SwitchNumber.compareTo("DIM")==0) {
                if (messageParts[1].compareTo("FanON")==0) {
                    BackTrack_Office_Fan_A_State = "FanON";
                    editor.putString("BackTrack_Office_Fan_A_State", "FanON");
                    editor.apply();
                } else if (messageParts[1].compareTo("FanOFF")==0) {
                    BackTrack_Office_Fan_A_State = "FanOFF";
                    editor.putString("BackTrack_Office_Fan_A_State", "FanOFF");
                    editor.apply();
                }else if (messageParts[1].compareTo("FanLvl1")==0) {
                    BackTrack_Office_Fan_A_Level = "FanLvl1";
                    editor.putString("BackTrack_Office_Fan_A_Level", "FanLvl1");
                    editor.apply();
                }else if (messageParts[1].compareTo("FanLvl2")==0) {
                    BackTrack_Office_Fan_A_Level = "FanLvl2";
                    editor.putString("BackTrack_Office_Fan_A_Level", "FanLvl2");
                    editor.apply();
                }else if (messageParts[1].compareTo("FanLvl3")==0) {
                    BackTrack_Office_Fan_A_Level = "FanLvl3";
                    editor.putString("BackTrack_Office_Fan_A_Level", "FanLvl3");
                    editor.apply();
                }else if (messageParts[1].compareTo("FanLvl4")==0) {
                    BackTrack_Office_Fan_A_Level = "FanLvl4";
                    editor.putString("BackTrack_Office_Fan_A_Level", "FanLvl4");
                    editor.apply();
                }else if (messageParts[1].compareTo("FanLvl5")==0) {
                    BackTrack_Office_Fan_A_Level = "FanLvl5";
                    editor.putString("BackTrack_Office_Fan_A_Level", "FanLvl5");
                    editor.apply();
                }
            }
        }else if (messageParts[0].compareTo("756848243")==0) {          // WashRoom1 2G A
            if (SwitchNumber.compareTo("T1")==0) {
                if (messageParts[1].compareTo("OF1")==0) {
                    BackTrack_WashRoom1_2G_A_1 = "OF1";
                    editor.putString("BackTrack_WashRoom1_2G_A_1", "OF1");
                    editor.apply();

                } else if (messageParts[1].compareTo("ON1")==0) {
                    BackTrack_WashRoom1_2G_A_1 = "ON1";
                    editor.putString("BackTrack_WashRoom1_2G_A_1", "ON1");
                    editor.apply();

                }
            }else  if (SwitchNumber.compareTo("T2")==0) {
                if (messageParts[1].compareTo("OF3")==0) {
                    BackTrack_WashRoom1_2G_A_2 = "OF3";
                    editor.putString("BackTrack_WashRoom1_2G_A_2", "OF3");
                    editor.apply();

                } else  if (messageParts[1].compareTo("ON3")==0) {
                    BackTrack_WashRoom1_2G_A_2 = "ON3";
                    editor.putString("BackTrack_WashRoom1_2G_A_2", "ON3");
                    editor.apply();

                }
            }
        }else if (messageParts[0].compareTo("756848278")==0) {          // WashRoom2 2G A
            if (SwitchNumber.compareTo("T1")==0) {
                if (messageParts[1].compareTo("OF1")==0) {
                    BackTrack_WashRoom2_2G_A_1 = "OF1";
                    editor.putString("BackTrack_WashRoom2_2G_A_1", "OF1");
                    editor.apply();

                } else if (messageParts[1].compareTo("ON1")==0) {
                    BackTrack_WashRoom2_2G_A_1 = "ON1";
                    editor.putString("BackTrack_WashRoom2_2G_A_1", "ON1");
                    editor.apply();

                }
            }else  if (SwitchNumber.compareTo("T2")==0) {
                if (messageParts[1].compareTo("OF3")==0) {
                    BackTrack_WashRoom2_2G_A_2 = "OF3";
                    editor.putString("BackTrack_WashRoom2_2G_A_2", "OF3");
                    editor.apply();

                } else  if (messageParts[1].compareTo("ON3")==0) {
                    BackTrack_WashRoom2_2G_A_2 = "ON3";
                    editor.putString("BackTrack_WashRoom2_2G_A_2", "ON3");
                    editor.apply();

                }
            }
        }else if (messageParts[0].compareTo("987573640")==0) {          // WashRoom1 1G A
            if (SwitchNumber.compareTo("T1")==0) {
                if (messageParts[1].compareTo("OF2")==0) {
                    BackTrack_WashRoom1_1G_A_1 = "OF2";
                    editor.putString("BackTrack_WashRoom1_1G_A_1", "OF2");
                    editor.apply();

                } else if (messageParts[1].compareTo("ON2")==0) {
                    BackTrack_WashRoom1_1G_A_1 = "ON2";
                    editor.putString("BackTrack_WashRoom1_1G_A_1", "ON2");
                    editor.apply();

                }
            }
        }else if (messageParts[0].compareTo("987571783")==0) {          // WashRoom2 1G A
            if (SwitchNumber.compareTo("T1")==0) {
                if (messageParts[1].compareTo("OF2")==0) {
                    BackTrack_WashRoom2_1G_A_1 = "OF2";
                    editor.putString("BackTrack_WashRoom2_1G_A_1", "OF2");
                    editor.apply();

                } else if (messageParts[1].compareTo("ON2")==0) {
                    BackTrack_WashRoom2_1G_A_1 = "ON2";
                    editor.putString("BackTrack_WashRoom2_1G_A_1", "ON2");
                    editor.apply();

                }
            }
        } else {
//            Toast.makeText(HomeActivity.this, "ELSE", Toast.LENGTH_LONG).show();
        }





        //***************//
    }



    public void readBackTrackStates(){
        String state = "OFF1";

        state = pref.getString("BackTrack_Conf_3G_A_1", "OF1");
        Conf_3G_A_1 = state;

        state = pref.getString("BackTrack_Conf_3G_A_2", "OF2");
        Conf_3G_A_2 = state;

        state = pref.getString("BackTrack_Conf_3G_A_3", "OF3");
        Conf_3G_A_3 = state;

        // Hall 3G A

        state = pref.getString("BackTrack_Hall_3G_A_1", "OF1");
        Hall_3G_A_1 = state;

        state = pref.getString("BackTrack_Hall_3G_A_2", "OF2");
        Hall_3G_A_2 = state;

        state = pref.getString("BackTrack_Hall_3G_A_3", "OF3");
        Hall_3G_A_3 = state;

//        Toast.makeText(HomeActivity.this, Hall_3G_A_1 + " "+ Hall_3G_A_2 + " "+ Hall_3G_A_3 , Toast.LENGTH_LONG).show();

        // Hall 3G B

        state = pref.getString("BackTrack_Hall_3G_B_1", "OF1");
        Hall_3G_B_1 = state;

        state = pref.getString("BackTrack_Hall_3G_B_2", "OF2");
        Hall_3G_B_2 = state;

        state = pref.getString("BackTrack_Hall_3G_B_3", "OF3");
        Hall_3G_B_3 = state;

        // Hall 3G C

        state = pref.getString("BackTrack_Hall_3G_C_1", "OF1");
        Hall_3G_C_1 = state;

        state = pref.getString("BackTrack_Hall_3G_C_2", "OF2");
        Hall_3G_C_2 = state;

        state = pref.getString("BackTrack_Hall_3G_C_3", "OF2");
        Hall_3G_C_3 = state;

        // Hall 2G A

        state = pref.getString("BackTrack_Hall_2G_A_1", "OF1");
        Hall_2G_A_1 = state;

        state = pref.getString("BackTrack_Hall_2G_A_2", "OF3");
        Hall_2G_A_2 = state;


        // Breaker 4G A
        state = pref.getString("BackTrack_Breaker_4G_A_1", "OF1");
        Breaker_4G_A_1 = state;

        state = pref.getString("BackTrack_Breaker_4G_A_2", "OF2");
        Breaker_4G_A_2 = state;

        state = pref.getString("BackTrack_Breaker_4G_A_3", "OF3");
        Breaker_4G_A_3 = state;

        state = pref.getString("BackTrack_Breaker_4G_A_4", "OF4");
        Breaker_4G_A_4 = state;



        // WashRoom 1 2G A
        state = pref.getString("BackTrack_WashRoom1_2G_A_1", "OF1");
        WashRoom1_2G_A_1 = state;

        state = pref.getString("BackTrack_WashRoom1_2G_A_2", "OF3");
        WashRoom1_2G_A_2 = state;

        // WashRoom 1 1G A

        state = pref.getString("BackTrack_WashRoom1_1G_A_1", "OF2");
        WashRoom1_1G_A_1 = state;

        // WashRoom 2 2G A

        state = pref.getString("BackTrack_WashRoom2_2G_A_1", "OF1");
        WashRoom2_2G_A_1 = state;

        state = pref.getString("BackTrack_WashRoom2_2G_A_2", "OF3");
        WashRoom2_2G_A_2 = state;

        // WashRoom 2 1G A

        state = pref.getString("BackTrack_WashRoom2_1G_A_1", "OF2");
        WashRoom2_1G_A_1 = state;

        // Office 3G A

        state = pref.getString("BackTrack_Office_3G_A_1", "OF1");
        Office_3G_A_1 = state;

        state = pref.getString("BackTrack_Office_3G_A_2", "OF2");
        Office_3G_A_2 = state;

        state = pref.getString("BackTrack_Office_3G_A_3", "OF3");
        Office_3G_A_3 = state;


        // Office Fan ******************************** Working Here
        state = pref.getString("BackTrack_Office_Fan_A_State", "FanOFF");
        Office_Fan_A_State = state;

        state = pref.getString("BackTrack_Office_Fan_A_Level", "FanLvl1");
        Office_Fan_A_Level = state;

        // Office Fan ******************************** Working Here
        state = pref.getString("BackTrack_Hall_Fan_A_State", "FanOFF");
        Hall_Fan_A_State = state;

        state = pref.getString("BackTrack_Hall_Fan_A_Level", "FanLvl1");
        Hall_Fan_A_Level = state;


//        Toast.makeText(HomeActivity.this, Conf_3G_A_1 + " " + Conf_3G_A_2 +"  "+ Conf_3G_A_3, Toast.LENGTH_LONG).show();
    }

    public void SwitchCore( String core_topic, String core_message){
        String message = core_message;
        try {
            client.publish(core_topic, message.getBytes(), 0, false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }



    public void setSubscription(){
        try {
            client.subscribe(Ack,0);
            client.subscribe(backTrack,0);
            client.subscribe(Hall_3G_A,0);
            client.subscribe(gatewayRegister, 0);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
