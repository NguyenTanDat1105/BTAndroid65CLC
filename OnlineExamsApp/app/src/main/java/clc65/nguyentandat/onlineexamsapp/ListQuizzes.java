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
    private ListView listview;
    private ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_quizzes);

        operation = getIntent().getStringExtra("Operation");
        TextView title = findViewById(R.id.title);
        listview = findViewById(R.id.listview);
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ids = new ArrayList<>();
        grades = new ArrayList<>();
        data = new ArrayList<>();

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        if (operation.equals("List Solved Quizzes")) {
            showGrade = false;
            solvedQuizzes = true;
            title.setText("Solved Quizzes");
        } else if (operation.equals("List Created Quizzes")) {
            showGrade = false;
            createdQuizzes = true;
            title.setText("Your Quizzes");
        } else if (operation.equals("List Quiz Grades")) {
            quizID = getIntent().getStringExtra("Quiz ID");  // ✅ SỬA: Thêm khoảng trắng

            // ✅ Kiểm tra Quiz ID
            if (quizID == null || quizID.isEmpty()) {
                Toast.makeText(this, "Quiz ID is missing!", Toast.LENGTH_LONG).show();
                finish();
                return;
            }

            title.setText(quizID);
            quizGrades = true;
            showGrade = true;
            title.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Quiz ID", quizID);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(ListQuizzes.this, "Quiz ID copied", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }

        if (solvedQuizzes) {
            ValueEventListener listener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    // ✅ Kiểm tra node tồn tại
                    if (!snapshot.child("Users").child(uid).hasChild("Quizzes Solved")) {
                        Toast.makeText(ListQuizzes.this, "You haven't solved any quizzes yet", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    DataSnapshot ds = snapshot.child("Users").child(uid).child("Quizzes Solved");
                    for (DataSnapshot f : ds.getChildren()) {
                        String quizId = f.getKey();
                        ids.add(quizId);

                        // ✅ Kiểm tra Quiz có tồn tại
                        if (snapshot.child("Quizzes").hasChild(quizId)) {
                            if (snapshot.child("Quizzes").child(quizId).hasChild("Title")) {
                                data.add(snapshot.child("Quizzes").child(quizId).child("Title").getValue().toString());
                            } else {
                                data.add("Quiz " + quizId);
                            }
                        } else {
                            data.add("Quiz " + quizId + " (Deleted)");
                        }
                    }

                    // ✅ Cập nhật adapter
                    ListAdapter listAdapter = new ListAdapter(data);
                    listview.setAdapter(listAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ListQuizzes.this, "Can't connect", Toast.LENGTH_SHORT).show();
                    android.util.Log.e("ListQuizzes", "Firebase error: " + error.getMessage());
                }
            };
            database.addValueEventListener(listener);

        } else if (createdQuizzes) {
            ValueEventListener listener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    // ✅ Kiểm tra node tồn tại
                    if (!snapshot.child("Users").child(uid).hasChild("Quizzes Created")) {
                        Toast.makeText(ListQuizzes.this, "You haven't created any quizzes yet", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    DataSnapshot ds = snapshot.child("Users").child(uid).child("Quizzes Created");
                    for (DataSnapshot f : ds.getChildren()) {
                        String quizId = f.getKey();
                        ids.add(quizId);

                        // ✅ Kiểm tra Quiz có tồn tại
                        if (snapshot.child("Quizzes").hasChild(quizId)) {
                            if (snapshot.child("Quizzes").child(quizId).hasChild("Title")) {
                                data.add(snapshot.child("Quizzes").child(quizId).child("Title").getValue().toString());
                            } else {
                                data.add("Quiz " + quizId);
                            }
                        } else {
                            data.add("Quiz " + quizId + " (Deleted)");
                        }
                    }

                    // ✅ Cập nhật adapter
                    ListAdapter listAdapter = new ListAdapter(data);
                    listview.setAdapter(listAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ListQuizzes.this, "Can't connect", Toast.LENGTH_SHORT).show();
                    android.util.Log.e("ListQuizzes", "Firebase error: " + error.getMessage());
                }
            };
            database.addValueEventListener(listener);

        } else if (quizGrades) {
            ValueEventListener listener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    // ✅ Kiểm tra Quiz tồn tại
                    if (!snapshot.child("Quizzes").hasChild(quizID)) {
                        Toast.makeText(ListQuizzes.this, "Quiz not found", Toast.LENGTH_SHORT).show();
                        finish();
                        return;
                    }

                    // ✅ Kiểm tra có Answer node không
                    if (!snapshot.child("Quizzes").child(quizID).hasChild("Answer")) {
                        Toast.makeText(ListQuizzes.this, "No one has taken this quiz yet", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    DataSnapshot ds = snapshot.child("Quizzes").child(quizID).child("Answer");
                    for (DataSnapshot f : ds.getChildren()) {
                        String userId = f.getKey();

                        // ✅ Bỏ qua node "Points" nếu có
                        if (userId.equals("Points")) continue;

                        ids.add(userId);

                        // ✅ Kiểm tra User tồn tại
                        if (snapshot.child("Users").hasChild(userId)) {
                            String firstName = "Unknown";
                            String lastName = "User";

                            if (snapshot.child("Users").child(userId).hasChild("First Name")) {
                                firstName = snapshot.child("Users").child(userId)
                                        .child("First Name").getValue().toString();
                            }
                            if (snapshot.child("Users").child(userId).hasChild("Last Name")) {
                                lastName = snapshot.child("Users").child(userId)
                                        .child("Last Name").getValue().toString();
                            }

                            data.add(firstName + " " + lastName);

                            // ✅ Lấy điểm
                            String points = "0";
                            if (f.hasChild("Points")) {
                                points = f.child("Points").getValue().toString();
                            }

                            String total = "0";
                            if (snapshot.child("Quizzes").child(quizID).hasChild("Total Questions")) {
                                total = snapshot.child("Quizzes").child(quizID)
                                        .child("Total Questions").getValue().toString();
                            }

                            String grade = points + "/" + total;
                            grades.add(grade);
                        } else {
                            data.add("User " + userId);
                            grades.add("N/A");
                        }
                    }

                    // ✅ Cập nhật adapter
                    ListAdapter listAdapter = new ListAdapter(data);
                    listview.setAdapter(listAdapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ListQuizzes.this, "Can't connect", Toast.LENGTH_SHORT).show();
                    android.util.Log.e("ListQuizzes", "Firebase error: " + error.getMessage());
                }
            };
            database.addValueEventListener(listener);
        }
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
            View v = inflater.inflate(R.layout.quizzes_listitem, null);

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
                        intent.putExtra("Quiz ID", ids.get(position));  // ✅ Đúng key
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