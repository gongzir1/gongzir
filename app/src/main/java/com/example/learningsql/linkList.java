package com.example.learningsql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.learningsql.data.DatabaseHelper;
import com.example.learningsql.model.YoutubeLink;

import java.util.List;

public class linkList extends AppCompatActivity implements YoutubelinkAdapter.OnRowClickListener{
    RecyclerView listRv;
    List<YoutubeLink> linkList;
    DatabaseHelper db;
    YoutubelinkAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_list);
        db=new DatabaseHelper(this);
        linkList=db.fetchAllLink(MainActivity.username);
        listRv=findViewById(R.id.rv_list);
        adapter=new YoutubelinkAdapter(linkList, linkList.this,this);
        RecyclerView.LayoutManager vertical_manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        listRv.setAdapter(adapter);
        listRv.setLayoutManager(vertical_manager);
    }

    @Override
    public void onItemClick(int id) {

    }
}