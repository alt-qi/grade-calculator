package ru.altqi.gradecalculator;

public enum Grade {
    A(5), B(4), C(3), D(2), E(1);
    public int value;
    Grade(int grade) {
        this.value = grade;
    }
}
