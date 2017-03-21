package com.liuting.editexample.adapter;

import android.content.Context;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liuting.editexample.R;
import com.liuting.editexample.utils.DipUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Package:com.liuting.editexample.adapter
 * author:liuting
 * Date:2017/3/21
 * Desc:发送内容列表Adapter
 */

public class MsgListAdapter extends BaseAdapter {
    private  List<Editable> listMsg=new ArrayList<>();
    private LayoutInflater inflater;//布局
    private Context context;//上下文

    public interface onItemClickListener{
        void onItemClick(View view, int position);
    }

    public MsgListAdapter(Context context,List<Editable> list) {
        this.context = context;
        this.listMsg=list;
        inflater= LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listMsg.size();
    }

    @Override
    public Object getItem(int i) {
        return listMsg.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            view = inflater.inflate(R.layout.list_item_msg_layout,null);
            holder = new ViewHolder();
            holder.tvMsg=(TextView) view.findViewById(R.id.msg_tv_send);
            holder.layoutMain=(LinearLayout)view.findViewById(R.id.msg_layout_main);
            view.setTag(holder);
        }else{
            holder=(ViewHolder)view.getTag();
        }
        if(position%2==0){
            holder.layoutMain.setGravity(Gravity.RIGHT);
            holder.tvMsg.setBackgroundResource(R.mipmap.icon_right);
            holder.layoutMain.setPadding(DipUtils.px2dip(20),DipUtils.px2dip(10), 0,DipUtils.px2dip(10));
        }else{
            holder.layoutMain.setGravity(Gravity.LEFT);
            holder.tvMsg.setBackgroundResource(R.mipmap.icon_left);
            holder.layoutMain.setPadding(0,DipUtils.px2dip(10), DipUtils.px2dip(20),DipUtils.px2dip(10));
        }
        holder.tvMsg.setText(listMsg.get(position));
        Log.e("TAG","position："+position+listMsg.get(position).toString());
        return view;
    }

    static class ViewHolder{
        TextView tvMsg;//发送的内容
        LinearLayout layoutMain;//主布局
    }
}
