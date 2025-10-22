package clc65.nguyentandat.viduintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nutSend =  findViewById(R.id.btnSend);
        nutSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code chuyển màn hình
                // 1. Tạo Intent
                Intent iChuyen = new Intent(MainActivity.this,SecondActivity.class);
                // 2. Lấy dữ liệu vào iSend
                    // 2.1. Lấy dữ liệu
                    EditText edtHoTen =  findViewById(R.id.edtName);
                    String data = edtHoTen.getText().toString();
                    // 2.2. Gửi vào iSend, dùng putExtra(key/name, value)
                    iChuyen.putExtra("ht", data);
                    iChuyen.putExtra("copyright", "MCT");
                // 3. Kích hoạt màn hình 2
                startActivity(iChuyen);
            }
        });
    }
}