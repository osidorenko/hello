package sample;

public class Student {
    private int group;
    private String name;
    private int sub1;
    private int sub2;
    private int sub3;
    private int sub4;

    public Student() {
    }

    public Student(int group, String name, int sub1, int sub2, int sub3, int sub4) {
        this.group = group;
        this.name = name;
        this.sub1 = sub1;
        this.sub2 = sub2;
        this.sub3 = sub3;
        this.sub4 = sub4;
    }

    private double amark() {
        double amark;
        amark = (double) (getSub1() + getSub2() + getSub3() + getSub4()) / 4;
        return amark;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSub1() {
        return sub1;
    }

    public void setSub1(int sub1) {
        this.sub1 = sub1;
    }

    public int getSub2() {
        return sub2;
    }

    public void setSub2(int sub2) {
        this.sub2 = sub2;
    }

    public int getSub3() {
        return sub3;
    }

    public void setSub3(int sub3) {
        this.sub3 = sub3;
    }

    public int getSub4() {
        return sub4;
    }

    public void setSub4(int sub4) {
        this.sub4 = sub4;
    }

    public double getAmark() {
        return amark();
    }

    @Override
    public String toString() {
        return "Группа " + group +
                " ФИО студента " + name +
                " Математика " + sub1 +
                " ОАиП " + sub2 +
                " Философия " + sub3 +
                " ОКТех " + sub4 + " Средний балл " + amark() +
                ' ';
    }
}
