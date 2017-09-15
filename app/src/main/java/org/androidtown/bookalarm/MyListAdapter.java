package org.androidtown.bookalarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by wlals on 2017-08-30.
 */

public class MyListAdapter extends BaseAdapter {
    Button button;
    Context context;
    ArrayList<list_item> list_itemArrayList;
    ViewHolder viewholder;
    class ViewHolder{
        TextView title_textView;
        TextView date_textView;
        TextView au_textView;
        ImageView profile_imageView;
    }


    public MyListAdapter(Context context, ArrayList<list_item> list_itemArrayList) {
        this.context = context;
        this.list_itemArrayList = list_itemArrayList;
    }
    @Override
    public int getCount() {
        return this.list_itemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list_itemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item,null);
            viewholder = new ViewHolder();
            viewholder.profile_imageView = (ImageView)convertView.findViewById(R.id.profile_imageView);
            viewholder.date_textView = (TextView)convertView.findViewById(R.id.date_textview);
            viewholder.title_textView  =(TextView)convertView.findViewById(R.id.title_textview);
            viewholder.au_textView  =(TextView)convertView.findViewById(R.id.au_textview);
            convertView.setTag(viewholder);
        }else{
            viewholder = (ViewHolder)convertView.getTag();
        }

        viewholder.title_textView.setText(list_itemArrayList.get(position).getTitle() );
        viewholder.date_textView.setText(list_itemArrayList.get(position).getLink().toString());
        viewholder.au_textView.setText(list_itemArrayList.get(position).getAuthor() );
        Glide.with(context).load(list_itemArrayList.get(position).getCover()).into(viewholder.profile_imageView);

        final TextView au_textView = (TextView) convertView.findViewById(R.id.au_textview) ;
        button = (Button) convertView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, au_textView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
    }




