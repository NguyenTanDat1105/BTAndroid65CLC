package clc65.nguyentandat.onthigiuaky;

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

public class DSMonHocActivity extends AppCompatActivity {
    ListView lv_dsMonHoc;
    ArrayList<String> dsMonHoc = new ArrayList<String>();
    ArrayAdapter <String> dsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ds_mon_hoc);
        lv_dsMonHoc = findViewById(R.id.lv_dsMonHoc);

        dsMonHoc = getData ();

        dsAdapter = new ArrayAdapter<>(this, R.layout.item_monhoc, dsMonHoc);

        lv_dsMonHoc.setAdapter(dsAdapter);

        lv_dsMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String MonDuocChon = dsAdapter.getItem(position).toString();
                Toast.makeText(DSMonHocActivity.this, "Môn được chọn:" + MonDuocChon, Toast.LENGTH_LONG).show();
            }
        });
    }
    ArrayList<String> getData() {
        ArrayList<String> dsCacMonHoc = new ArrayList<String>();
        dsCacMonHoc.add("Tin học đại cương");
        dsCacMonHoc.add("Lập trình Java");
        dsCacMonHoc.add("Phát triển Ứng dụng Web 1");
        dsCacMonHoc.add("Phát triển Ứng dụng Web 2");
        dsCacMonHoc.add("Khai phá dữ liệu lớn");
        dsCacMonHoc.add("Kinh tế chính trị");
        dsCacMonHoc.add("Lập trình thiết bị di động");
        dsCacMonHoc.add("Quản lý dự án phần mềm");
        dsCacMonHoc.add("Tư tưởng Hồ Chí Minh");
        dsCacMonHoc.add("Lập trình Python");
        dsCacMonHoc.add("Cấu trúc dữ liệu và giải thuật");
        dsCacMonHoc.add("Công nghệ phần mềm");
        dsCacMonHoc.add("Nhập môn ngành công nghệ thông tin");
        dsCacMonHoc.add("Mạn máy tính");
        dsCacMonHoc.add("Lập trình hướng đối tượng");
        dsCacMonHoc.add("Kiến trúc máy tính và hệ điều hành");
        return dsCacMonHoc;
    }
}