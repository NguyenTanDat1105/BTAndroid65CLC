package clc65.nguyentandat.bailamthemvdreadjson;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1. Đọc file JSON từ assets
        String jsonFile = Utils.getJsonFromAsset(this,"user.json");
        Log.i(TAG, "JSON from assets: " + jsonFile);
        if (jsonFile == null){
            Log.i(TAG,"Can't read JSON");
            return;
        }
        //2. Khơi tạo Gson
        Gson gson = new Gson();
        //3. Xác định kiểu dữ liệu List<User>
        Type listUserType = new TypeToken<List<User>>(){}.getType();
        //4. Chuyển JSON thành List<User>
        List<User> users = gson.fromJson(jsonFile, listUserType);
        //5. Xử lý dữ liệu
        TextView textView = findViewById(R.id.textViewResult);

            // Tạo chuỗi để hiển thị
        StringBuilder result = new StringBuilder();
        for (User user : users) {
            result.append("Tên: ").append(user.getName()).append("\n");
            result.append("Tuổi: ").append(user.getAge()).append("\n");
            result.append("Tin nhắn: ").append(user.getMessages()).append("\n\n");
        }

            // Gán chuỗi ra TextView
        textView.setText(result.toString());
    }
}