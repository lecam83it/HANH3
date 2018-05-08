package com.example.pc.hanh3;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 5/8/2018.
 */

public class Adapter extends BaseAdapter {

    Activity context ;
    int res;
    ArrayList<Song> list;

    public Adapter(Activity context , int res, ArrayList<Song> list){
        this.context = context;
        this.res = res;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ViewHolder{
        TextView txtName;
        ImageView imgAvatar;
        public ViewHolder(){}
    }
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = this.context.getLayoutInflater().inflate(this.res, null);

            holder.txtName = (TextView) convertView.findViewById(R.id.nameOfSong);
            holder.imgAvatar = (ImageView) convertView.findViewById(R.id.img_logo);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Song song = (Song) getItem(i);
        holder.imgAvatar.setImageResource(song.getImage());
        holder.txtName.setText(song.getTitle());
        return  convertView;
    }
}
