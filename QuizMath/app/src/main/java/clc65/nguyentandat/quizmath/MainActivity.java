package clc65.nguyentandat.quizmath;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<CauHoiCongHaiSo> dsCauHoi;
    Button nut1, nut2, nut3, nut4;
    TextView textViewLoiHoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dsCauHoi = new ArrayList<>();
        CauHoiCongHaiSo ch1 = new CauHoiCongHaiSo (1,"1+1",2,3,4,5);
        dsCauHoi.add(ch1);
        CauHoiCongHaiSo ch2 = new CauHoiCongHaiSo (1,"10+1",11,12,13,14);
        dsCauHoi.add(ch1);
        CauHoiCongHaiSo ch3 = new CauHoiCongHaiSo (1,"10+90",100,101,102,103);
        dsCauHoi.add(ch1);

        textViewLoiHoi = findViewById(R.id.textView);
        nut1 = findViewById(R.id.da1);
        nut2 = findViewById(R.id.da2);
        nut3 = findViewById(R.id.da3);
        nut4 = findViewById(R.id.da4);

        textViewLoiHoi.setText(dsCauHoi.get(0).getLoiHoi());

        int soNN = (int)Math.random()*3;
        if (soNN == 1) {
            nut1.setText(dsCauHoi.get(0).getDaDung());
            nut2.setText(dsCauHoi.get(0).getSai1());
            nut3.setText(dsCauHoi.get(0).getSai2());
            nut4.setText(dsCauHoi.get(0).getSai3());
        }
        if (soNN == 2) {
            nut2.setText(dsCauHoi.get(0).getDaDung());
            nut1.setText(dsCauHoi.get(0).getSai1());
            nut3.setText(dsCauHoi.get(0).getSai2());
            nut4.setText(dsCauHoi.get(0).getSai3());
        }
        if (soNN == 3) {
            nut3.setText(dsCauHoi.get(0).getDaDung());
            nut2.setText(dsCauHoi.get(0).getSai1());
            nut1.setText(dsCauHoi.get(0).getSai2());
            nut4.setText(dsCauHoi.get(0).getSai3());
        }
        if (soNN == 4) {
            nut4.setText(dsCauHoi.get(0).getDaDung());
            nut2.setText(dsCauHoi.get(0).getSai1());
            nut3.setText(dsCauHoi.get(0).getSai2());
            nut1.setText(dsCauHoi.get(0).getSai3());
        }
    }
}