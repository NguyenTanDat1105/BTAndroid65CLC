package clc65.nguyentandat.onthigiuaky;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HoatDongTruongActivity extends AppCompatActivity {
    HoatDongAdapter hoatdongAdapter;
    ArrayList<HoatDongModel> dsHoatDong;
    RecyclerView RecyclerViewHoatDong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoat_dong_truong);
        dsHoatDong = getData();
        RecyclerViewHoatDong = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutLinear = new LinearLayoutManager(this);
        RecyclerViewHoatDong.setLayoutManager(layoutLinear);
        hoatdongAdapter = new HoatDongAdapter(this, dsHoatDong);
        RecyclerViewHoatDong.setAdapter(hoatdongAdapter);
    }

    ArrayList<HoatDongModel> getData(){
        ArrayList<HoatDongModel> dsDuLieu = new ArrayList<>();
        HoatDongModel hd1 = new HoatDongModel("Hoạt động 1", "logo", "Intents - Fragments - RecylerView-Viewpager-Tablayout");
        HoatDongModel hd2 = new HoatDongModel("Hoạt động 2", "logo", "Intents - Fragments - RecylerView-Viewpager-Tablayout");
        HoatDongModel hd3 = new HoatDongModel("Hoạt động 3", "logo", "Intents - Fragments - RecylerView-Viewpager-Tablayout");
        HoatDongModel hd4 = new HoatDongModel("Hoạt động 4", "logo", "Intents - Fragments - RecylerView-Viewpager-Tablayout");
        HoatDongModel hd5 = new HoatDongModel("Hoạt động 5", "logo", "Intents - Fragments - RecylerView-Viewpager-Tablayout");
        dsDuLieu.add(hd1);
        dsDuLieu.add(hd2);
        dsDuLieu.add(hd3);
        dsDuLieu.add(hd4);
        dsDuLieu.add(hd5);
        return dsDuLieu;
    }
}