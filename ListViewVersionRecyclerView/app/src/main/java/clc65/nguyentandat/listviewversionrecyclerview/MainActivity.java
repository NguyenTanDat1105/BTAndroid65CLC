package clc65.nguyentandat.listviewversionrecyclerview;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListViewAdapter listViewAdapter;
    ArrayList<ListView> list;
    RecyclerView recyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = getDataForRecyclerView();
        recyclerView = findViewById(R.id.RecyclerText);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        listViewAdapter = new ListViewAdapter(this,list);
        recyclerView.setAdapter(listViewAdapter);
    }

    ArrayList<ListView> getDataForRecyclerView () {
        ArrayList<ListView> dsDuLieu = new ArrayList<>();
        dsDuLieu.add(new ListView("Lập trình thiết bị di động"));
        dsDuLieu.add(new ListView("Lập trình Python"));
        dsDuLieu.add(new ListView("Quản lý dự án phần mềm"));
        dsDuLieu.add(new ListView("Phát triển ứng dụng Web 1"));
        dsDuLieu.add(new ListView("Kiến trúc & thiết kế phần mềm"));
        return dsDuLieu;
    }
}