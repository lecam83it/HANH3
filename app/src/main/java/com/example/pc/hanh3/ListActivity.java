package com.example.pc.hanh3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList<Song> list;
    ListView lvList;
    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        setControl();
    }

    private void setControl() {
        list = new ArrayList<>();
        list.add(new Song("Cô Gái M52",R.raw.co_gai_m52, R.drawable.co_gai_m52));
        list.add(new Song("Đừng Như Thói Quen",R.raw.dung_nhu_thoi_quen, R.drawable.dung_nhu_thoi_quen));
        list.add(new Song("Người Âm Phủ",R.raw.nguoi_am_phu, R.drawable.nguoi_am_phu));

        adapter = new Adapter(ListActivity.this, R.layout.items, list);
        lvList = (ListView) findViewById(R.id.listSong);
        lvList.setAdapter(adapter);
        lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                intent.putExtra("KEY", i);
                intent.putExtra("LIST_SONG", list);
                startActivity(intent);
            }
        });
    }
}
