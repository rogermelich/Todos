package com.iesebre.dam2.roger.todos;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by roger on 27/11/15.
 */
public class CustomListAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<TodoItem> list;
    private final LayoutInflater layputInflater;

    public CustomListAdapter(Context context, ArrayList listData) {
        this.context = context;
        this.list = listData;
        layputInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = layputInflater.inflate(R.layout.todolistitem, null);
        } else {

        }

        final TextView tv = (TextView) convertView.findViewById(R.id.todolistitemtext);
        final CheckBox done = (CheckBox) convertView.findViewById(R.id.checkBoxDone);

        tv.setText(list.get(position).getName());
        tv.setPaintFlags(0);

        done.setChecked(list.get(position).isDone());

        int urgentColor = Color.parseColor("#ff0000");
        int importantColor = Color.parseColor("#ff8000");
        int notUrgentColor = Color.parseColor("#00ff00");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if(list.get(position).getPriority() == 1){done.setButtonTintList(ColorStateList.valueOf(urgentColor));}
            if(list.get(position).getPriority() == 2){done.setButtonTintList(ColorStateList.valueOf(importantColor));}
            if(list.get(position).getPriority() == 3){done.setButtonTintList(ColorStateList.valueOf(notUrgentColor));}
        } else {
            if(list.get(position).getPriority() == 1){done.setBackgroundColor(urgentColor);}
            if(list.get(position).getPriority() == 2){done.setBackgroundColor(importantColor);}
            if(list.get(position).getPriority() == 3){done.setBackgroundColor(notUrgentColor);}
        }

        showDone(tv,done, position);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDone(tv,done, position);
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).showEditTaskFrom(position);
            }
        });

        return convertView;
    }

    private void showDone(TextView tv, CheckBox done, int position)
    {
        if (!done.isChecked()) {
            done.setChecked(false);
            list.get(position).setDone(false);
            tv.setPaintFlags(0);
        } else {
            done.setChecked(true);
            list.get(position).setDone(true);
            tv.setPaintFlags(tv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}