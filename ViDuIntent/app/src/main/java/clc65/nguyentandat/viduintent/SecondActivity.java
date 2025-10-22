package clc65.nguyentandat.viduintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        // 1. Nhận về Intent
        Intent iNhanDuoc = getIntent();
        // 2. Bóc ra
        String intNhanDuoc = iNhanDuoc.getStringExtra("ht");
        // 3.Xử lý
            // Set lên TextView
        TextView htTen = findViewById(R.id.tvHienThiTen);
        htTen.setText(intNhanDuoc);

        // Nút BACK
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iQuayBack = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(iQuayBack);
            }
        });
    }
}