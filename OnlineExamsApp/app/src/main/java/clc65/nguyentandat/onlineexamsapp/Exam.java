package clc65.nguyentandat.onlineexamsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Exam extends AppCompatActivity {

    private Question[] data;
    private String quizID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
    }

    public class ListAdapter extends BaseAdapter {
        Question[] arr;

        ListAdapter(Question[] arr2) {
            arr = arr2;
        }

        @Override
        public int getCount() {
            return arr.length;
        }

        @Override
        public Object getItem(int position) {
            return arr[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            View v = inflater.inflate(R.layout.question);

            return null;
        }
    }
}