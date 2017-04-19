package com.liuting.swiperefreshlayout;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.liuting.swiperefreshlayout.adapter.ListItemAdapter;
import com.liuting.swiperefreshlayout.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SwipeRefreshLayout layoutRefresh;//刷新控件
    private RecyclerView recyclerView;//RecyclerView
    private List<String> datas = new ArrayList<>();
    private ListItemAdapter mAdatper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 初始化控件
     */
    public void initView(){
        layoutRefresh = (SwipeRefreshLayout) findViewById(R.id.main_layout_refresh);
        recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        initDatas();
        initRefreshLayout();
        initRecyclerView();
    }

    /**
     *初始化 RecyclerView
     */
    private void initRecyclerView() {
        mAdatper = new ListItemAdapter(datas);
        mAdatper.setOnItemClickListener(new ListItemAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position, String city) {

                Toast.makeText(MainActivity.this, "city:" + city + ",position:" + position, Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(mAdatper);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    /**
     * 初始化数据
     */
    private  void initDatas(){
        datas.add("New York");
        datas.add("Boston");
        datas.add("Washington");
        datas.add("San Francisco");
        datas.add("California");
        datas.add("Chicago");
        datas.add("Houston");
        datas.add("Phoenix");
        datas.add("Philadelphia");
        datas.add("Pennsylvania");
        datas.add("San Antonio");
        datas.add("Austin");
        datas.add("Milwaukee");
        datas.add("Las Vegas");
        datas.add("Oklahoma");
        datas.add("Portland");
        datas.add("Mexico");
    }

    /**
     *初始化 SwipeRefreshLayout
     */
    private void  initRefreshLayout(){

        layoutRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        layoutRefresh.setDistanceToTriggerSync(100);
//        mlayout.setProgressBackgroundColorSchemeColor(getResources().getColor(R.color.item_press));

        layoutRefresh.setSize(SwipeRefreshLayout.LARGE);

        layoutRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=0; i<=10;i++){
                            mAdatper.addData(i,"new City:"+i);
                        }
                        mAdatper.notifyItemRangeChanged(0,10);
                        recyclerView.scrollToPosition(0);
                        layoutRefresh.setRefreshing(false);
//                        mlayout.isRefreshing()//isRefreshing
                    }
                },3000);
            }
        });
    }

}
