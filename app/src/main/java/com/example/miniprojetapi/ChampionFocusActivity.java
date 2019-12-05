package com.example.miniprojetapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChampionFocusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion_focus);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Champion champ = (Champion) extras.getSerializable("champion");
            Log.d("intent", "Your choice : " + champ.getName());

            ImageView image = this.findViewById(R.id.imageView_face);
            Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/9.23.1/img/champion/"+champ.getImg()).into(image);

            TextView name = this.findViewById(R.id.textView_name);
            name.setText(champ.getName());

            TextView title = this.findViewById(R.id.textView_title);
            title.setText(champ.getTitle());

            TextView categories = this.findViewById(R.id.textView_categories);
            categories.setText("");
            Ion.with(getBaseContext())
                    .load("http://leagueoflegends.fandom.com/api.php?action=query&prop=revisions&rvprop=content&format=json&titles="+ champ.getName().replace(" ", "%20"))
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            if (result != null) {
                                JsonObject ctg = (JsonObject) result.get("query").getAsJsonObject().get("pages");

                                Pattern pattern = Pattern.compile("Category:\\w+");
                                Matcher m = pattern.matcher(ctg.toString());
                                while (m.find()){
                                    categories.append(m.group() + "\n");
                                }
                            }
                        }
                    });
        }
    }
}
