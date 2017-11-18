package edu.brandeis.cs.nonikers.nonikers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by ruchen on 11/18/2017.
 */

public class MonikersAdapter extends BaseAdapter {

    String[] cards = new String[10];
    LayoutInflater inflater;

    public MonikersAdapter(String[] s, LayoutInflater inflater){
        System.arraycopy(s,0,cards,0,10);
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return cards[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v1 = view;
        if (view == null){
            v1 = inflater.inflate(R.layout.list_item, null,false);
        }
        CheckBox checkBox = v1.findViewById(R.id.checkBox);
        checkBox.setText(cards[i]);
        return v1;
    }
}
