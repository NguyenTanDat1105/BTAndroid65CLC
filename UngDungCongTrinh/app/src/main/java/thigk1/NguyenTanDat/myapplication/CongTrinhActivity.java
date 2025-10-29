package thigk1.NguyenTanDat.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CongTrinhActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CongTrinhAdapter adapter;
    private List<CongTrinhModel> congTrinhList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cong_trinh);

        recyclerView = findViewById(R.id.recyclerViewCongTrinh);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Chuẩn bị dữ liệu
        khoiTaoDuLieu();

        // Khởi tạo Adapter và xử lý click
        adapter = new CongTrinhAdapter(this, congTrinhList, new CongTrinhAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CongTrinhModel congTrinh) {
                chuyenSangDetailActivity(congTrinh);
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void chuyenSangDetailActivity(CongTrinhModel congTrinh) {
        Intent intent = new Intent(CongTrinhActivity.this, CongTrinhActivity.class);
        // Đóng gói đối tượng CongTrinh và gửi đi
        intent.putExtra("CONG_TRINH_DATA", congTrinh);
        startActivity(intent);
    }

    private void khoiTaoDuLieu() {
        // Tạm thời tạo dữ liệu cứng
        // Thay vì phần này, bạn sẽ đọc từ file JSON
        congTrinhList = new ArrayList<>();
        congTrinhList.add(new CongTrinhModel("Công trình 1", "Thời gian đăng: 28/10/2025", R.mipmap.image1));
        congTrinhList.add(new CongTrinhModel("Công trình 2", "Thời gian đăng: 27/10/2025", R.mipmap.image2));
        congTrinhList.add(new CongTrinhModel("Công trình 3", "Thời gian đăng: 26/10/2025", R.mipmap.image3));
    }
}