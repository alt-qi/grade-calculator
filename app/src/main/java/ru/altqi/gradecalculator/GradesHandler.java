package ru.altqi.gradecalculator;

import java.util.Arrays;

public class GradesHandler {
    private int[] grades = new int[50];

    private int getNumberOfGrades() {
        int num = 0;
        for (int grade: grades) if (grade != 0) num++;
        return num;
    }

    public void addGrade(Grade grade) throws IndexOutOfBoundsException {
        int num = getNumberOfGrades();
        grades[num] = grade.value;
    }

    public void removeGrade() throws IndexOutOfBoundsException {
        int num = getNumberOfGrades();
        grades[num-1] = 0;
    }

    public void resetGrades() {
        Arrays.fill(grades, 0);
    }

    public String getFormattedGradesList() {
        String str = "";
        for (int grade: grades) if (grade != 0) str += String.format("%s, ", Integer.toString(grade));
        return str.isEmpty() ? str : str.substring(0, str.length() - 2);
    }

    private int getGradesSum() {
        int sum = 0;
        for (int grade: grades) sum += grade;
        return sum;
    }

    public float getAverageGrade() {
        return getNumberOfGrades() > 0 ? (float) getGradesSum() / getNumberOfGrades() : 0;
    }
}
