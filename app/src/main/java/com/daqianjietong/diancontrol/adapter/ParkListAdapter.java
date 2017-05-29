package com.daqianjietong.diancontrol.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daqianjietong.diancontrol.R;
import com.daqianjietong.diancontrol.bean.OrderInfo;
import com.daqianjietong.diancontrol.bean.ParkListInfo;
import com.daqianjietong.diancontrol.utils.TimeUtils;

import java.util.List;


/**
 * Created by Administrator on 2017/5/6.
 */

public class ParkListAdapter extends BaseAdapter {

    private Context context;
    private List<ParkListInfo.Data> entities;

    public ParkListAdapter(Context context, List<ParkListInfo.Data> entities) {
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
            convertView = View.inflate(context, R.layout.item_parklist, null);
            holder.item_partlist_name=(TextView) convertView.findViewById(R.id.item_partlist_name);
            holder.item_time=(TextView) convertView.findViewById(R.id.item_time);
            holder.tv_park_status=(TextView) convertView.findViewById(R.id.tv_park_status);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        holder.item_partlist_name.setText(entities.get(position).getPs_parknum());

        if (entities.get(position).getPs_status().equals("1")) {
            holder.item_time.setText("");
            holder.tv_park_status.setText("空");
        } else if (entities.get(position).getPs_status().equals("2")) {
            holder.item_time.setText(entities.get(position).getPs_starttime()+"至");
            holder.tv_park_status.setText("停");
        }else if (entities.get(position).getPs_status().equals("3")) {
            holder.item_time.setText(entities.get(position).getPs_starttime()+"时至"+entities.get(position).getPs_endtime()+"时");
            holder.tv_park_status.setText("预");
        }else if (entities.get(position).getPs_status().equals("4")) {
            holder.item_time.setText(entities.get(position).getPs_starttime()+"日至"+entities.get(position).getPs_endtime()+"日");
            holder.tv_park_status.setText("租");
        }

        return convertView;
    }

    static class ViewHolder {
        private TextView tv_park_status;
        private TextView item_partlist_name;
        private TextView item_time;
    }
}
