package view.lyd.com.sectionrecyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import adapter.HotelEntityAdapter;
import adapter.SectionedSpanSizeLookup;
import entity.HotelEntity;
import utils.JsonUtils;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private HotelEntityAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new HotelEntityAdapter(this);
        GridLayoutManager manager = new GridLayoutManager(this,4);
        //设置header
        manager.setSpanSizeLookup(new SectionedSpanSizeLookup(mAdapter,manager));
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
        final HotelEntity entity = JsonUtils.analysisJsonFile(this,"json");
        mAdapter.setData(entity.allTagsList);
        mAdapter.setOnItemClickListener(new HotelEntityAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int group, int childPosition, HotelEntity.TagsEntity.TagInfo tagInfo) {
//                int position = mRecyclerView.getChildAdapterPosition(view);
                Log.d("hyh", "name = " + tagInfo.tagName + " --group= " + group +  "--childPosition= " + childPosition);
                Toast.makeText(MainActivity.this, "taginfo name = " + tagInfo.tagName, Toast.LENGTH_LONG).show();
            }
        });
    }
}
