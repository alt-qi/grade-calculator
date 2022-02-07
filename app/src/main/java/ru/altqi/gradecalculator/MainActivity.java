package ru.altqi.gradecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    class GradeAdder implements View.OnClickListener {
        Grade gradeToAdd;
        GradeAdder(Grade gradeToAdd) {
            this.gradeToAdd = gradeToAdd;
        }

        @Override
        public void onClick(View view) {
            try {
                gh.addGrade(gradeToAdd);
                averageGradeDisplay.setText(String.format(Locale.US, "%.2f", gh.getAverageGrade()));
                gradesListDisplay.setText(gh.getFormattedGradesList());
            } catch (IndexOutOfBoundsException e) {
                Toast.makeText(
                        MainActivity.this,
                        "Кол-во добавленных оценок не может превышать 50.",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }

    GradesHandler gh = new GradesHandler();
    Button addGradeA, addGradeB, addGradeC, addGradeD, undoAddingGrade, resetGrades;
    TextView averageGradeDisplay, gradesListDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Отображение оценок

        averageGradeDisplay = findViewById(R.id.average_grade);
        gradesListDisplay = findViewById(R.id.grades_list);

//        Добавление оценок

        addGradeA = findViewById(R.id.add_grade_5);
        addGradeB = findViewById(R.id.add_grade_4);
        addGradeC = findViewById(R.id.add_grade_3);
        addGradeD = findViewById(R.id.add_grade_2);

        addGradeA.setOnClickListener(new GradeAdder(Grade.A));
        addGradeB.setOnClickListener(new GradeAdder(Grade.B));
        addGradeC.setOnClickListener(new GradeAdder(Grade.C));
        addGradeD.setOnClickListener(new GradeAdder(Grade.D));

//        Удаление оценок

        undoAddingGrade = findViewById(R.id.undo);
        resetGrades = findViewById(R.id.clear_grades);

        undoAddingGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    gh.removeGrade();
                    averageGradeDisplay.setText(String.format(Locale.US, "%.2f", gh.getAverageGrade()));
                    gradesListDisplay.setText(gh.getFormattedGradesList());
                } catch (IndexOutOfBoundsException e) {
                    Toast.makeText(MainActivity.this,
                            "Все оценки уже убраны.",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

        resetGrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gh.resetGrades();
                averageGradeDisplay.setText(String.format(Locale.US, "%.2f", gh.getAverageGrade()));
                gradesListDisplay.setText(gh.getFormattedGradesList());
            }
        });
    }
}