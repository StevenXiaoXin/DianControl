package com.daqianjietong.diancontrol.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daqianjietong.diancontrol.R;
import com.daqianjietong.diancontrol.bean.LeaveBean;
import com.daqianjietong.diancontrol.bean.OrderInfo;
import com.daqianjietong.diancontrol.utils.Api;
import com.daqianjietong.diancontrol.utils.HttpUtil;
import com.daqianjietong.diancontrol.utils.TimeUtils;

import java.util.List;


/**
 * Created by Administrator on 2017/5/6.
 */

public class OrderListAdapter extends BaseAdapter {

    private Context context;
    private List<OrderInfo.Data> entities;

    public OrderListAdapter(Context context, List<OrderInfo.Data> entities) {
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
            convertView = View.inflate(context, R.layout.item_orderlist, null);
            holder.item_partlist_name=(TextView) convertView.findViewById(R.id.item_partlist_name);
            holder.item_start_time=(TextView) convertView.findViewById(R.id.item_start_time);
            holder.item_end_time=(TextView) convertView.findViewById(R.id.item_end_time);
            holder.item_orderlist_continiue=(TextView) convertView.findViewById(R.id.item_orderlist_continiue);
            holder.item_orderlist_leave=(TextView) convertView.findViewById(R.id.item_orderlist_leave);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        holder.item_partlist_name.setText(entities.get(position).getR_parknum());
        holder.item_start_time.setText(TimeUtils.getShowTime3(entities.get(position).getR_starttime()));
        holder.item_end_time.setText(TimeUtils.getShowTime3(entities.get(position).getR_endtime()));
        holder.item_orderlist_continiue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.item_orderlist_leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLeave(entities.get(position).getR_id());
            }
        });

        return convertView;
    }

    static class ViewHolder {
        private TextView item_partlist_name;
        private TextView item_start_time;
        private TextView item_end_time;
        private TextView item_orderlist_continiue;
        private TextView item_orderlist_leave;


    }

    public void doLeave(String txt_reserveid){
        Api.getInstance().leave(txt_reserveid,new HttpUtil.URLListenter<LeaveBean>(){

            @Override
            public void onsucess(LeaveBean leaveBean) throws Exception {
                if (leaveBean.getData().get(0).equals("1")) {
                    Toast.makeText(context, "离位成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "离位失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onfaild(String error) {
                Toast.makeText(context,error,Toast.LENGTH_SHORT).show();
            }
        },context);

    }

}
