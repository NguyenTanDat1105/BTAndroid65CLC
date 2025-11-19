package clc65.nguyentandat.vidulamviecsqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getBookData();
        ArrayList<String> dsTenSach = getBookName();
        // Hiện lên ListView
        ListView listView = findViewById(R.id.lvDanhSachTenSach);
        ArrayAdapter<String> adapterTenSach = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, dsTenSach);
        listView.setAdapter(adapterTenSach);

        Button bThem = findViewById(R.id.btnThemSach);
        bThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu
                EditText edtTenSach = findViewById(R.id.edtTenSach);
                String tenSach = edtTenSach.getText().toString();

                EditText edtGiaBan = findViewById(R.id.edtGiaBan);
                float giaBan = Float.parseFloat(edtGiaBan.getText().toString());

                // Thêm vào DB
                ContentValues row = new ContentValues();
                row.put("BookName", tenSach);
                row.put("Price", giaBan);
                db.insert("BOOKS", null, row);

                // Làm tươi lại cái ListView
                adapterTenSach.notifyDataSetChanged();
            }
        });





    }

    ArrayList<BOOKS> getBookData () {
        // Tạo CSDL
        db = openOrCreateDatabase("books.db", MODE_PRIVATE, null);
        // Tạo bảng BOOKS
//        String sqlTaoBang = "CREATE TABLE BOOKS(BookID integer PRIMARY KEY, BookName text, Page integer, Price Float, Description text)";
//        String sqlXoaBang = "DROP TABLE IF EXISTS BOOKS";
//        db.execSQL(sqlXoaBang);
//        db.execSQL(sqlTaoBang);
//        // Thêm vào 1 số dòng dữ liệu
//        String sqlThem1 = "INSERT INTO BOOKS VALUES(1, 'Java', 100, 9.99, 'sách về java')";
//        String sqlThem2 = "INSERT INTO BOOKS VALUES(2, 'Android', 320, 19.00, 'Android cơ bản');";
//        String sqlThem3 = "INSERT INTO BOOKS VALUES(3, 'Học làm giàu', 120, 0.99, 'sách đọc cho vui')";
//        String sqlThem4 = "INSERT INTO BOOKS VALUES(4, 'Tử điển Anh-Việt', 1000, 29.50, 'Từ điển 100.000 từ')";
//        String sqlThem5 = "INSERT INTO BOOKS VALUES(5, 'CNXH', 1, 1, 'chuyện cổ tích')";
//        db.execSQL(sqlThem1);
//        db.execSQL(sqlThem2);
//        db.execSQL(sqlThem3);
//        db.execSQL(sqlThem4);
//        db.execSQL(sqlThem5);
        // Test

        // Truy vấn dữ liệu
        String sqlSelectAll = "SELECT * FROM BOOKS";
        Cursor resultSet = db.rawQuery(sqlSelectAll, null);
        ArrayList<BOOKS> dsSach = new ArrayList<BOOKS>();
        resultSet.moveToFirst();
        while (true){
            // Lấy dữ liệu của dòng/bản ghi hiện tại, trả bởi resultSet
            int maSach = resultSet.getInt(0);
            String tenSach = resultSet.getString(1);
            int soTrang = resultSet.getInt(2);
            float giaBan = resultSet.getFloat(3);
            String moTa = resultSet.getString(4);
            // Gói vào 1 đối tượng ==> tạo 1 thực thể/lớp có cấu trúc tương đương
            BOOKS book = new BOOKS(maSach, tenSach, soTrang, giaBan, moTa);
            // Ở bài demo này, ta chỉ hiện ra tên sách lên ListView
            // Thêm vào 1 biến danh sách
            // Dùng 1 ArrayList<String> để chứa tên sách
            dsSach.add(book);
            // Di chuyển đến bản ghi tiếp theo => nếu đã hết thì thoát khỏi vòng lặp
            if (resultSet.moveToNext() == false)
                break;
        }
        db.close();
        return dsSach;
    }

    ArrayList<String> getBookName (){
        // Create CSDL
        db = openOrCreateDatabase("books",MODE_PRIVATE,null);
        // Truy van
        String sqlSelectAll = "SELECT * FROM BOOKS";
        Cursor resultSet = db.rawQuery(sqlSelectAll,null);
        ArrayList<String> dsTenSach = new ArrayList<>();
        resultSet.moveToFirst();
        while (true){
            // Get data
            int maSach = resultSet.getInt(0);
            String nameBook = resultSet.getString(1);
            // Add to list
            dsTenSach.add(nameBook);
            // Move to next
            resultSet.moveToNext();
            if (resultSet.isAfterLast()){
                break;
            }
        }
        db.close();
        return dsTenSach;
    }
}