package thigk1.NguyenTanDat.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnTinhDT, btnVatLieu, btnCongTrinh, btnGioiThieu;
    void TimDk () {
        btnTinhDT = findViewById(R.id.btnTinhDienTich);
        btnVatLieu = findViewById(R.id.btnVatLieu);
        btnCongTrinh = findViewById(R.id.btnCongTrinh);
        btnGioiThieu = findViewById(R.id.btnGioiThieu);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimDk();
        btnTinhDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iTinhDT = new Intent(MainActivity.this, TinhDTichActivity.class);
                startActivity(iTinhDT);
            }
        });
        btnVatLieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iVatLieu = new Intent(MainActivity.this, DSVatLieuActivity.class);
                startActivity(iVatLieu);
            }
        });
        btnCongTrinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iCongTrinh = new Intent(MainActivity.this, CongTrinhActivity.class);
                startActivity(iCongTrinh);
            }
        });
        btnGioiThieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioiThieu = new Intent(MainActivity.this, GioiThieuActivity.class);
                startActivity(iGioiThieu);
            }
        });
    }
}