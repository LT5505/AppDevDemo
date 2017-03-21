package com.liuting.editexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.liuting.editexample.Bean.FaceInfo;
import com.liuting.editexample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Package:com.liuting.editexample.adapter
 * author:liuting
 * Date:2017/3/21
 * Desc:表情网格列表Adapter
 */

public class FaceGvAdapter extends BaseAdapter {
    private  List<FaceInfo> listFace=new ArrayList<>();
    private LayoutInflater inflater;//布局
    private Context context;//上下文
    private onItemClickListener listener;

    public interface onItemClickListener{
        void onItemClick(View view,int position);
    }

    public FaceGvAdapter(List<FaceInfo> listFace, Context context,onItemClickListener listener) {
        this.listFace = listFace;
        this.context = context;
        this.listener = listener;
        inflater= LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listFace.size();
    }

    @Override
    public Object getItem(int i) {
        return listFace.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            view = inflater.inflate(R.layout.grid_item_face_layout,null);
            holder = new ViewHolder();
            holder.imgIcon=(ImageView)view.findViewById(R.id.face_img_icon);
            view.setTag(holder);
        }else{
            holder=(ViewHolder)view.getTag();
        }
        holder.imgIcon.setImageResource(listFace.get(position).getResourceId());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.onItemClick(view,position);
                }
            }
        });
        return view;
    }

    static class ViewHolder{
        ImageView imgIcon;//表情图片
    }
}
