package com.hoocontrols.hoodb;

//import android.arch.lifecycle.ViewModelProvider;
//import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

//import androidx.appcompat.app.AppCompatActivity;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

public class Mqtt extends AppCompatActivity {

    //    static String MQTTHOST = "tcp://m23.cloudmqtt.com:17983";
    static String MQTTHOST = "tcp://192.168.0.150:1883";
    static String USERNAME = "vqjftqpa";
    static String PASSWORD = "Lgng8k4diV28";
    String topicStrL = "painlessMesh/to/586821073";
    String topicStrK = "painlessMesh/to/756816693";
    String hall3G = "HooPDM/to/756816707";
    MqttAndroidClient client;
//    TextView subText;
    MqttConnectOptions options;
    private WifiManager wifiManager;
    WifiConfiguration wifiConfiguration;

    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mqtt);

        //Here is the Wifi Manager code

        wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.SSID = "xptest";




//        subText = (TextView)findViewById(R.id.subText);

        String clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), MQTTHOST, clientId);

        options = new MqttConnectOptions();
        options.setUserName(USERNAME);
        options.setPassword(PASSWORD.toCharArray());

        try {
            IMqttToken token = client.connect(options);

            token.setActionCallback(new IMqttActionListener() {

                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(Mqtt.this, "connected", Toast.LENGTH_LONG).show();

                    //Here I am subscribing to a topic of choice after success of connection to brocker
                    setSubscription();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(Mqtt.this, "connection failed", Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
//                subText.setText(new String(message.getPayload()));

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });



    }

    public void ap_on(View v) {
        Method method;
        try {
            method = wifiManager.getClass().getDeclaredMethod("setWifiApEnabled", WifiConfiguration.class, Boolean.TYPE);
            method.invoke(wifiManager, wifiConfiguration, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ap_off(View v) {
        Method method;
        try {
            method = wifiManager.getClass().getDeclaredMethod("setWifiApEnabled", WifiConfiguration.class, Boolean.TYPE);
            method.invoke(wifiManager, wifiConfiguration, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void enwifi(View v){
        wifiManager.setWifiEnabled(true);
    }

    public void dnwifi(View v){
        wifiManager.setWifiEnabled(false);
    }

    public void pubON1(View v){
        String topic = topicStrK;
        String message = "ON1";
        try {
            client.publish(topic, message.getBytes(), 0, false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    public void pubOFF1(View v){
        String topic = topicStrK;
        String message = "OFF1";
        try {
            client.publish(topic, message.getBytes(), 0, false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    public void pubON(View v){
        String topic = hall3G;
        String message = "ON1";
        try {
            client.publish(topic, message.getBytes(), 0, false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    public void pubOFF(View v){
        String topic = hall3G;
        String message = "OFF1";
        try {
            client.publish(topic, message.getBytes(), 0, false);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void setSubscription(){

        try {
            client.subscribe(topicStrL,0);
            client.subscribe(topicStrK,0);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void conn(View v){
        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(Mqtt.this, "connected", Toast.LENGTH_LONG).show();

                    //Here I am subscribing to a topic of choice after success of connection to brocker
                    setSubscription();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(Mqtt.this, "connection failed", Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void disconn(View v){
        try {
            IMqttToken token = client.disconnect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(Mqtt.this, "Disconnected", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(Mqtt.this, "could not disconnect", Toast.LENGTH_LONG).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
