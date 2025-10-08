package clc65.nguyentandat.unitconverter;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUSD, editTextVND;
    private Button btnConvert;
    private final double RATE = 26358.5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các View
        editTextUSD = findViewById(R.id.editTextUSD);
        editTextVND = findViewById(R.id.editTextVND);
        btnConvert = findViewById(R.id.btn_convert);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usdText = editTextUSD.getText().toString().trim();
                String vndText = editTextVND.getText().toString().trim();

                if (!TextUtils.isEmpty(usdText) && TextUtils.isEmpty(vndText)) {
                    try {
                        double usd = Double.parseDouble(usdText);
                        double vnd = usd * RATE;
                        editTextVND.setText(String.format("%.1f", vnd));
                        Toast.makeText(MainActivity.this, "Đã chuyển từ USD sang VND", Toast.LENGTH_SHORT).show();
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Giá trị USD không hợp lệ", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (TextUtils.isEmpty(usdText) && !TextUtils.isEmpty(vndText)) {
                    try {
                        double vnd = Double.parseDouble(vndText);
                        double usd = vnd / RATE;
                        editTextUSD.setText(String.format("%.2f", usd));
                        Toast.makeText(MainActivity.this, "Đã chuyển từ VND sang USD", Toast.LENGTH_SHORT).show();
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Giá trị VND không hợp lệ", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Hãy nhập vào một trong hai ô!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}