package clc65.nguyentandat.chuongtrinhtracnghiempheptoancong;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvQuestion, tvScore;
    private Button btnOption1, btnOption2, btnOption3, btnOption4, btnNext;

    private int num1, num2, correctAnswer;
    private int score = 0;
    private int questionCount = 0;
    private final int MAX_QUESTIONS = 10;
    private Random random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvScore = findViewById(R.id.tvScore);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);
        btnNext = findViewById(R.id.btnNext);

        random = new Random();

        generateQuestion();

        View.OnClickListener answerClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer((Button) v);
            }
        };

        btnOption1.setOnClickListener(answerClickListener);
        btnOption2.setOnClickListener(answerClickListener);
        btnOption3.setOnClickListener(answerClickListener);
        btnOption4.setOnClickListener(answerClickListener);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionCount < MAX_QUESTIONS) {
                    generateQuestion();
                    enableAllButtons();
                    btnNext.setVisibility(View.GONE);
                } else {
                    showFinalScore();
                }
            }
        });

        btnNext.setVisibility(View.GONE);
    }

    private void generateQuestion() {
        num1 = random.nextInt(50) + 1;
        num2 = random.nextInt(50) + 1;
        correctAnswer = num1 + num2;


        tvQuestion.setText(num1 + " + " + num2 + " = ?");
        questionCount++;
        tvScore.setText("Câu hỏi: " + questionCount + "/" + MAX_QUESTIONS + " | Điểm: " + score);

        ArrayList<Integer> answers = new ArrayList<>();
        answers.add(correctAnswer);

        while (answers.size() < 4) {
            int wrongAnswer = correctAnswer + random.nextInt(21) - 10; // Sai số từ -10 đến +10
            if (wrongAnswer > 0 && wrongAnswer != correctAnswer && !answers.contains(wrongAnswer)) {
                answers.add(wrongAnswer);
            }
        }

        Collections.shuffle(answers);

        btnOption1.setText(String.valueOf(answers.get(0)));
        btnOption2.setText(String.valueOf(answers.get(1)));
        btnOption3.setText(String.valueOf(answers.get(2)));
        btnOption4.setText(String.valueOf(answers.get(3)));

        resetButtonColors();
    }

    private void checkAnswer(Button selectedButton) {
        int selectedAnswer = Integer.parseInt(selectedButton.getText().toString());

        disableAllButtons();

        if (selectedAnswer == correctAnswer) {
            selectedButton.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
            score += 10;
            Toast.makeText(this, "Chính xác! +10 điểm", Toast.LENGTH_SHORT).show();
        } else {
            selectedButton.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            highlightCorrectAnswer();
            Toast.makeText(this, "Sai rồi! Đáp án đúng là: " + correctAnswer, Toast.LENGTH_SHORT).show();
        }

        tvScore.setText("Câu hỏi: " + questionCount + "/" + MAX_QUESTIONS + " | Điểm: " + score);

        if (questionCount < MAX_QUESTIONS) {
            btnNext.setVisibility(View.VISIBLE);
        } else {
            btnNext.setText("Xem kết quả");
            btnNext.setVisibility(View.VISIBLE);
        }
    }

    private void highlightCorrectAnswer() {
        Button[] buttons = {btnOption1, btnOption2, btnOption3, btnOption4};
        for (Button btn : buttons) {
            if (Integer.parseInt(btn.getText().toString()) == correctAnswer) {
                btn.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                break;
            }
        }
    }

    private void disableAllButtons() {
        btnOption1.setEnabled(false);
        btnOption2.setEnabled(false);
        btnOption3.setEnabled(false);
        btnOption4.setEnabled(false);
    }

    private void enableAllButtons() {
        btnOption1.setEnabled(true);
        btnOption2.setEnabled(true);
        btnOption3.setEnabled(true);
        btnOption4.setEnabled(true);
    }

    private void resetButtonColors() {
        int defaultColor = getResources().getColor(android.R.color.holo_blue_light);
        btnOption1.setBackgroundColor(defaultColor);
        btnOption2.setBackgroundColor(defaultColor);
        btnOption3.setBackgroundColor(defaultColor);
        btnOption4.setBackgroundColor(defaultColor);
    }

    private void showFinalScore() {
        tvQuestion.setText("Hoàn thành!");
        tvScore.setText("Điểm cuối cùng: " + score + "/" + (MAX_QUESTIONS * 10));

        String message;
        if (score >= 80) {
            message = "Xuất sắc! 🌟";
        } else if (score >= 60) {
            message = "Tốt lắm! 👍";
        } else if (score >= 40) {
            message = "Khá đấy! 😊";
        } else {
            message = "Cố gắng thêm nhé! 💪";
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        btnOption1.setVisibility(View.GONE);
        btnOption2.setVisibility(View.GONE);
        btnOption3.setVisibility(View.GONE);
        btnOption4.setVisibility(View.GONE);

        btnNext.setText("Chơi lại");
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
            }
        });
    }

    private void restartGame() {
        score = 0;
        questionCount = 0;

        btnOption1.setVisibility(View.VISIBLE);
        btnOption2.setVisibility(View.VISIBLE);
        btnOption3.setVisibility(View.VISIBLE);
        btnOption4.setVisibility(View.VISIBLE);

        generateQuestion();
        enableAllButtons();
        btnNext.setVisibility(View.GONE);
        btnNext.setText("Câu tiếp theo");
    }
}