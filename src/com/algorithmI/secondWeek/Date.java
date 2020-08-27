package com.algorithmI.secondWeek;

/**
 * 实现了Comparable接口的数据类型：Integer、String、Double都实现了Comparable接口。创建自己的数据类型时，
 * 我们也要实现Comparable接口就能使用该模板进行排序。必须要实现Comparable接口中的compareTo方法。
 * <p>
 * compareTo必须实现一个全序关系：
 * ①自反性：对所有的v=v
 * ②反对称性：对所有v<w都有v>w，且v=w时w=v
 * ③传递性
 */
public class Date implements Comparable<Date> {
    private final int day;
    private final int month;
    private final int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }


    @Override
    public int compareTo(Date o) {
        if (this.year > o.year) {
            return 1;
        }
        if (this.year < o.year) {
            return -1;
        }
        if (this.month > o.month) {
            return 1;
        }
        if (this.month < o.month) {
            return -1;
        }
        if (this.day > o.day) {
            return 1;
        }
        if (this.day < o.day) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Date{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
}
