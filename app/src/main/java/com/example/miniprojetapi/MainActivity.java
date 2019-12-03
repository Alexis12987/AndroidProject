package com.example.miniprojetapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONObject;

import java.io.Console;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listChampView = (ListView) findViewById(R.id.listchamp);
        ArrayList< Champion > listChamp = new ArrayList< Champion >();
        /*final ArrayAdapter< Champion > monAdapteur
                = new ArrayAdapter < Champion >( MainActivity . this ,
                android . R . layout . simple_list_item_1 ,
                listChamp );*/
        final ChampionAdapter mAdapter = new ChampionAdapter(this,listChamp);

        listChampView.setAdapter(mAdapter);


        Ion.with(getBaseContext())
                .load("http://ddragon.leagueoflegends.com/cdn/9.23.1/data/en_US/champion.json")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        if(result!=null)
                        Log.d("test",result.get("version").getAsString());
                        JsonObject data = result.get("data").getAsJsonObject();


                        //for(int i=0;i<result.get("data").getAsJsonObject().size();i++) {
                        for (Map.Entry<String, JsonElement> entry : data.entrySet()){
                            /*JsonObject champjson = data.get("Aatrox").getAsJsonObject();
                            String key = champjson . getAsJsonPrimitive("key"). getAsString ();
                            String name = champjson . getAsJsonPrimitive ("name"). getAsString ();
                            String title = champjson . getAsJsonPrimitive ("title").getAsString();*/

                            String key = entry.getValue().getAsJsonObject().get("key").getAsString();
                            String name = entry.getValue().getAsJsonObject().get("name").getAsString();
                            String title = entry.getValue().getAsJsonObject().get("title").getAsString();
                            Champion champ = new Champion ( key, name,title);

                            Log.println(Log.DEBUG,"tag",champ.getName());
                            mAdapter . add ( champ );

                        }
                        // do stuff with the result or error
                    }
                });
    }
}
