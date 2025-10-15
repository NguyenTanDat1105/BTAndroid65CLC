package clc65.nguyentandat.vidulistview;

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

public class MainActivity extends AppCompatActivity {

    ListView lvhocphan;
    // (1) Chuẩn bị nguồn dữ liệu hiển thị
    // -- Khai báo --
    ArrayList<String> listHocPhan= new ArrayList<String>();
    // (2) Tạo Adapter
    ArrayAdapter<String> HPAdapter = new ArrayAdapter<String>(
            this, android.R.layout.simple_list_item_1,listHocPhan
        );
    void TimDK () {
        lvhocphan = findViewById(R.id.dshocphan);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimDK();


        // -- Lấy dữ liệu đưa vào listHocPhan
        // -- LẤY Ở ĐÂU ??? = file, database, internet(Cloud)...
        listHocPhan = getData(); // 1.2

        // (3) Gắn Adapter
        lvhocphan.setAdapter(HPAdapter);
        // (4) Thiết lập sự kiện
        ArrayList<String> finalListHocPhan = listHocPhan;
        lvhocphan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Xử lý việc chon Item ở đây
                // Biến position chứa vị trí của Item được chọn
                // (4.1) Lấy giá trị của phần tử được chọn
                    // Cách 1: Lấy gián tiếp qua Adapter
                        String HPduocChon =  HPAdapter.getItem(position).toString();
                    // Cách 2: Lấy trực tiếp từ biến chứa danh sách
                        // String HPduocChon1 = listHocPhan.get(position);
                // (4.2) Làm gì với nó thì làm
                String thongbao = " Bạn đã chọn học lại học phần " + HPduocChon;
                Toast.makeText(MainActivity.this, thongbao, Toast.LENGTH_LONG).show();
            }

        });
    }
    // 1.1
    ArrayList<String> getData() {
        // Code đọc dữ liệu và cất vào biến tạm, trước khi return cho hàm
        ArrayList<String> dsTenHP = new ArrayList<String>();
        // Code ở đây
        // Bài này, ta hard-code, để fake dữ liệu tại đây cho nhanh
        dsTenHP.add("Kiến trúc và thiết kế phần mềm");
        dsTenHP.add("Quản lí dự án phần mềm");
        dsTenHP.add("Lập trình thiết bị di động");
        dsTenHP.add("Lập trình Python");
        dsTenHP.add("Phát triển ứng dụng Web 1");
        dsTenHP.add("Tư tưởng Hồ Chí Minh");
        return dsTenHP;
    }
}