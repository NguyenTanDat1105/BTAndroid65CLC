package clc65.nguyentandat.th_bai1_basicgui_simplemath;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    EditText edtA, edtB;
    RadioGroup radioGroup;
    Button btnCalc;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        radioGroup = findViewById(R.id.radioGroup);
        btnCalc = findViewById(R.id.btnCalc);
        txtResult = findViewById(R.id.txtResult);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double a = Double.parseDouble(edtA.getText().toString());
                    double b = Double.parseDouble(edtB.getText().toString());
                    double result = 0;

                    int checkedId = radioGroup.getCheckedRadioButtonId();
                    if (checkedId == R.id.rbAdd) {
                        result = a + b;
                    } else if (checkedId == R.id.rbSub) {
                        result = a - b;
                    } else if (checkedId == R.id.rbMul) {
                        result = a * b;
                    } else if (checkedId == R.id.rbDiv) {
                        if (b == 0) {
                            txtResult.setText("Không thể chia cho 0");
                            return;
                        }
                        result = a / b;
                    } else {
                        txtResult.setText("Vui lòng chọn phép toán");
                        return;
                    }

                    txtResult.setText("Kết quả: " + result);
                } catch (NumberFormatException e) {
                    txtResult.setText("Vui lòng nhập số hợp lệ");
                }
            }
        });
    }
}