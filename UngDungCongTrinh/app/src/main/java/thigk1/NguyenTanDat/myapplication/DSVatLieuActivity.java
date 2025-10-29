package thigk1.NguyenTanDat.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class DSVatLieuActivity extends AppCompatActivity {
    ListView lv_dsVatLieu;

    ArrayList<String> dsVatLieu = new ArrayList<String>();
    ArrayAdapter<String> dsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_vat_lieu);
        lv_dsVatLieu = findViewById(R.id.lv_dsVatLieu);

        dsVatLieu = getData ();

        dsAdapter = new ArrayAdapter<>(this, R.layout.item_vat_lieu, dsVatLieu);

        lv_dsVatLieu.setAdapter(dsAdapter);

        lv_dsVatLieu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String VatLieuDuocChon = dsAdapter.getItem(position).toString();
                Toast.makeText(DSVatLieuActivity.this, "Vật liệu được chọn" + VatLieuDuocChon, Toast.LENGTH_LONG).show();
            }
        });
    }
    ArrayList<String> getData() {
        ArrayList<String> dsCacVatLieu= new ArrayList<String>();
        dsCacVatLieu.add("Xi măng");
        dsCacVatLieu.add("Gạch");
        dsCacVatLieu.add("Đá ốp lát");
        dsCacVatLieu.add("Ống nhựa");
        dsCacVatLieu.add("Sơn chống thấm");
        dsCacVatLieu.add("...");
        return dsCacVatLieu;
    }
}