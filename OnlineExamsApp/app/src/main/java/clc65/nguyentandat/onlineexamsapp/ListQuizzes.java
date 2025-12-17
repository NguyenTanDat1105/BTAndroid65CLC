package clc65.nguyentandat.onlineexamsapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListQuizzes extends AppCompatActivity {

    private String operation;
    private boolean showGrade;
    private boolean solvedQuizzes;
    private boolean createdQuizzes;
    private boolean quizGrades;
    private String uid;
    private ArrayList<String> ids;
    private ArrayList<String> grades;
    private String quizID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_quizzes);

        operation = getIntent().getStringExtra("Operation");
        TextView title = findViewById(R.id.title);
        ListView listview = findViewById(R.id.listview);
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ids = new ArrayList<>();
        grades = new ArrayList<>();
        ArrayList<String> data = new ArrayList<>();

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        if (operation.equals("List Solved Quizzes")) {
            showGrade = false;
            solvedQuizzes = true;
        } else if (operation.equals("List Created Quizzes")) {
            showGrade = false;
            createdQuizzes = true;
        } else if (operation.equals("List Quiz Grades")) {
            quizID = getIntent().getStringExtra("QuizID");
            title.setText(quizID);
            quizGrades = true;
            showGrade = true;
            title.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Quiz ID", quizID);
                    clipboard.setPrimaryClip(clip);
                    return true;
                }
            });
        }

        if (solvedQuizzes) {
            ValueEventListener listener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    DataSnapshot ds = snapshot.child("Users").child(uid).child("Quizzes Solved");
                    for (DataSnapshot f : ds.getChildren()) {
                        ids.add(f.getKey());
                        data.add(snapshot.child("Quizzes").child(f.getKey()).child("Title").getValue().toString());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ListQuizzes.this, "Can't connect", Toast.LENGTH_SHORT).show();
                }
            };
            database.addValueEventListener(listener);
        } else if (createdQuizzes) {
            ValueEventListener listener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    DataSnapshot ds = snapshot.child("Users").child(uid).child("Quizzes Created");
                    for (DataSnapshot f : ds.getChildren()) {
                        ids.add(f.getKey());
                        data.add(snapshot.child("Quizzes").child(f.getKey()).child("Title").getValue().toString());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ListQuizzes.this, "Can't connect", Toast.LENGTH_SHORT).show();
                }
            };
            database.addValueEventListener(listener);
        } else if (quizGrades) {
            ValueEventListener listener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    DataSnapshot ds = snapshot.child("Quizzes").child(quizID).child("Answers");
                    for (DataSnapshot f : ds.getChildren()) {
                        ids.add(f.getKey());
                        String firstName = snapshot.child("Users").child(f.getKey())
                                .child("First Name").getValue().toString();
                        String lastName = snapshot.child("Users").child(f.getKey())
                                .child("Last Name").getValue().toString();
                        data.add(firstName + " " + lastName);
                        String points = snapshot.child("Quizzes").child(quizID)
                                .child("Answer").child(f.getKey()).child("Points")
                                .getValue().toString();
                        String total = snapshot.child("Quizzes").child(quizID)
                                .child("Total Questions").getValue().toString();
                        String grade = points + "/" + total;
                        grades.add(grade);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ListQuizzes.this, "Can't connect", Toast.LENGTH_SHORT).show();
                }
            };
            database.addValueEventListener(listener);
        }
        ListAdapter listAdapter = new ListAdapter(data);
        listview.setAdapter(listAdapter);
    }

    public class ListAdapter extends BaseAdapter {
        ArrayList<String> arr;

        ListAdapter(ArrayList<String> arr2) {
            arr = arr2;
        }

        @Override
        public int getCount() {
            return arr.size();
        }

        @Override
        public Object getItem(int position) {
            return arr.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            LayoutInflater inflater = getLayoutInflater();
            View v = inflater.inflate(R.layout.quizzes_listitem,null);

            TextView grade = v.findViewById(R.id.grade);
            TextView quiz = v.findViewById(R.id.quiz);
            RelativeLayout item = v.findViewById(R.id.item);

            quiz.setText(arr.get(position));

            if (showGrade) {
                grade.setVisibility(View.VISIBLE);
            } else {
                grade.setVisibility(View.GONE);
            }

            if (solvedQuizzes) {
                item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view1) {
                        Intent intent = new Intent(ListQuizzes.this, Result.class);
                        intent.putExtra("Quiz ID", ids.get(position));
                        startActivity(intent);
                    }
                });
            } else if (createdQuizzes) {
                item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view1) {
                        Intent intent = new Intent(ListQuizzes.this, ListQuizzes.class);
                        intent.putExtra("Operation", "List Quiz Grades");
                        intent.putExtra("Quiz ID", ids.get(position));
                        intent.putExtra("Quiz Title", arr.get(position));
                        startActivity(intent);
                    }
                });
            } else if (quizGrades) {
                grade.setText(grades.get(position));
                item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view1) {
                        Intent intent = new Intent(ListQuizzes.this, Result.class);
                        intent.putExtra("Quiz ID", quizID);
                        intent.putExtra("User UID", ids.get(position));
                        startActivity(intent);
                    }
                });
            }

            return v;
        }
    }


}