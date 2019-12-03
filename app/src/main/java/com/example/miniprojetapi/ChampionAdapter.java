package com.example.miniprojetapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ChampionAdapter extends ArrayAdapter<Champion> {

    private Context mContext;
    private List<Champion> champList = new ArrayList<>();

    public ChampionAdapter(@NonNull Context context, ArrayList<Champion> list) {
        super(context, 0 , list);
        mContext = context;
        champList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Champion currentChamp = champList.get(position);

        ImageView image = (ImageView)listItem.findViewById(R.id.imageView_poster);
        Picasso.get().load("http://ddragon.leagueoflegends.com/cdn/9.23.1/img/champion/"+currentChamp.getImg()).into(image);


        TextView name = (TextView) listItem.findViewById(R.id.textView_name);
        name.setText(currentChamp.getName());

        TextView title = (TextView) listItem.findViewById(R.id.textView_title);
        title.setText(currentChamp.getTitle());

        return listItem;
    }
}