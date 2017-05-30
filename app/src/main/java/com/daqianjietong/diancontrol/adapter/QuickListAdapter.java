package com.daqianjietong.diancontrol.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daqianjietong.diancontrol.R;
import com.daqianjietong.diancontrol.bean.OrderInfo;
import com.daqianjietong.diancontrol.bean.QuickListBean;
import com.daqianjietong.diancontrol.utils.TimeUtils;

import java.util.List;


/**
 * Created by Administrator on 2017/5/6.
 */

public class QuickListAdapter extends BaseAdapter {

    private Context context;
    private List<QuickListBean.Data> entities;

    public QuickListAdapter(Context context, List<QuickListBean.Data> entities) {
        this.context = context;
        this.entities=entities;
    }

    @Override
    public int getCount() {
        return entities.size();
    }

    @Override
    public Object getItem(int position) {
        return entities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView = View.inflate(context, R.layout.item_quicklist, null);
            holder.item_partlist_name=(TextView) convertView.findViewById(R.id.item_partlist_name);
            holder.item_start_time=(TextView) convertView.findViewById(R.id.item_start_time);
            holder.item_quicklist_leave=(TextView) convertView.findViewById(R.id.item_quicklist_leave);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        holder.item_partlist_name.setText(entities.get(position).getR_parknum());
        holder.item_start_time.setText(TimeUtils.getShowTime3(entities.get(position).getR_starttime()));

        holder.item_quicklist_leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        return convertView;
    }

    static class ViewHolder {
        private TextView item_partlist_name;
        private TextView item_start_time;
        private TextView item_quicklist_leave;


    }
}
