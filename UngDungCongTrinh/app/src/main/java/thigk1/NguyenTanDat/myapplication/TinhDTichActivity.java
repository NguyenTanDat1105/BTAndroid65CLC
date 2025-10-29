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

import com.google.android.material.textfield.TextInputLayout;

public class TinhDTichActivity extends AppCompatActivity {

    Button btnTinh, btnBack;
    TextInputLayout input_cdai, input_crong, output_kqua;
    void TimDK ()
    {
        btnTinh = findViewById(R.id.bt_tinh);
        btnBack = findViewById(R.id.bt_back);
        input_cdai = findViewById(R.id.input_cdai);
        input_crong = findViewById(R.id.input_crong);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinh_dtich_activity);
        TimDK();
        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Cdai = input_cdai.getEditText().getText().toString();
                String Crong = input_crong.getEditText().getText().toString();

                Double ChieuDai = Double.parseDouble(Cdai);
                Double ChieuRong = Double.parseDouble(Crong);

                Double ketqua = ChieuDai * ChieuRong;

                String strKQ = String.format("%.1f", ketqua);

                output_kqua.getEditText().setText(strKQ);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iBack = new Intent(TinhDTichActivity.this, MainActivity.class);
                startActivity(iBack);
            }
        });

    }
}