package com.hoocontrols.hoodb;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import io.moquette.BrokerConstants;
import io.moquette.server.config.MemoryConfig;


public class BrokerActivity extends AppCompatActivity {


    io.moquette.server.Server server = new io.moquette.server.Server();

    TextView log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broker);

        log =findViewById(R.id.textView5);

        //MQTT Broker
        try {
            String root = getFilesDir().getAbsolutePath()+ File.separator + BrokerConstants.DEFAULT_MOQUETTE_STORE_MAP_DB_FILENAME;
            File file = new File(root);
            if(file.exists()){
                file.delete();
            }

            MemoryConfig memoryConfig = new MemoryConfig(new Properties());//.getAbsolutePath()
            memoryConfig.setProperty(BrokerConstants.PERSISTENT_STORE_PROPERTY_NAME, getFilesDir().getAbsolutePath()+ File.separator + BrokerConstants.DEFAULT_MOQUETTE_STORE_MAP_DB_FILENAME);
            Toast.makeText(BrokerActivity.this, memoryConfig.toString(), Toast.LENGTH_LONG).show();

            server.startServer(memoryConfig);
////             server.startServer();//is not working due to DEFAULT_MOQUETTE_STORE_MAP_DB_FILENAME;
            Toast.makeText(BrokerActivity.this, "Server Started", Toast.LENGTH_LONG).show();
        }
        catch (IOException e) { e.printStackTrace();Toast.makeText(BrokerActivity.this, e.toString(), Toast.LENGTH_LONG).show(); }
        catch (Exception e){
            e.printStackTrace();
            Toast.makeText(BrokerActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            log.setText(e.toString());
        }
////        server.startServer();
    }
}
