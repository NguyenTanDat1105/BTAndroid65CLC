package clc65.nguyentandat.bailamthem_th_bai2_basicgui_bmi;

import android.os.Bundle;
import android.widget.Toast;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout tilHeight, tilWeight;
    private TextInputEditText edtHeight, edtWeight;
    private MaterialCheckBox chkAsian;
    private MaterialButton btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // giữ lại insets như code cũ
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ
        tilHeight = findViewById(R.id.tilHeight);
        tilWeight = findViewById(R.id.tilWeight);
        edtHeight = findViewById(R.id.edtHeight);
        edtWeight = findViewById(R.id.edtWeight);
        chkAsian = findViewById(R.id.chkAsian);
        btnCalculate = findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        String heightStr = edtHeight.getText() != null ? edtHeight.getText().toString().trim() : "";
        String weightStr = edtWeight.getText() != null ? edtWeight.getText().toString().trim() : "";

        if (heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập chiều cao và cân nặng", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double height = Double.parseDouble(heightStr) / 100.0;
            double weight = Double.parseDouble(weightStr);

            double bmi = weight / (height * height);

            String result;
            if (chkAsian.isChecked()) {
                if (bmi < 18.5) result = "Thiếu cân";
                else if (bmi < 23) result = "Bình thường";
                else if (bmi < 25) result = "Thừa cân";
                else result = "Béo phì";
            } else {
                if (bmi < 18.5) result = "Thiếu cân";
                else if (bmi < 25) result = "Bình thường";
                else if (bmi < 30) result = "Thừa cân";
                else result = "Béo phì";
            }

            String msg = "BMI: " + String.format("%.1f", bmi) + "\nĐánh giá: " + result;
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Sai định dạng số!", Toast.LENGTH_SHORT).show();
        }
    }
}
