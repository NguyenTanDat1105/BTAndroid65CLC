package clc65.nguyentandat.onthigiuaky;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    MaterialCardView btnCN1, btnCN2, btnCN3, btnAboutMe;
    void TimDK () {
        btnCN1 = findViewById(R.id.btnCN1);
        btnCN2 = findViewById(R.id.btnCN2);
        btnCN3 = findViewById(R.id.btnCN3);
        btnAboutMe = findViewById(R.id.btnAboutMe);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimDK();
        btnCN1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iCN1 = new Intent(MainActivity.this, AverageScoreOfTheSubjectActivity.class);
                startActivity(iCN1);
            }
        });
        btnAboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iAboutMe = new Intent(MainActivity.this, AboutMeActivity.class);
                startActivity(iAboutMe);
            }
        });
    }
}