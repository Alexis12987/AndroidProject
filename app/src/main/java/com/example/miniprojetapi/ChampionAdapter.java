package com.example.miniprojetapi;

import android.content.Context;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;




public class ChampionAdapter extends ArrayAdapter<Champion> implements Filterable {

    private List<Champion> origChampList;
    private Context mContext;
    private List<Champion> champList;
    private Filter champFilter;

    public ChampionAdapter(@NonNull Context context, List<Champion> list) {
        super(context, 0 , list);
        mContext = context;
        champList = list;
        origChampList=list;
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
    public void resetData() {
        champList = origChampList;
    }


    @Override
    public Filter getFilter() {
        if (champFilter == null)
            champFilter = new ChampionFilter();
        return champFilter;
    }
    private class ChampionFilter extends Filter {

        @Override
        protected Filter.FilterResults performFiltering(CharSequence constraint) {
            Filter.FilterResults results = new Filter.FilterResults();

            if (constraint == null || constraint.length() == 0) {

                results.values = champList;
                results.count = champList.size();
            }
            else {
                List<Champion> nChampList = new ArrayList<Champion>();

                for (Champion p : champList) {
                    if (p.getName().toUpperCase()
                            .startsWith(constraint.toString().toUpperCase()))
                        nChampList.add(p);
                }

                results.values = nChampList;
                results.count = nChampList.size();
            }
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint,
                                      Filter.FilterResults results) {


            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                champList = (List<Champion>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
