package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Controller {
    private static String path = "C:\\lab7\\src\\tmp\\database.txt";
    int i = 0;

    public void read() {
        listView.setItems(FXCollections.observableArrayList());
        go(listed);
    }

    public void readall() {
        listView.setItems(field());
    }

    public void readdebetors() {
        ObservableList<String> debetors = FXCollections.observableArrayList();
        for (int j = 0; j < sheet.size(); j++) {
            int k = 0;
            if (sheet.get(j).getSub1() < 4) {
                k++;
            }
            if (sheet.get(j).getSub2() < 4) {
                k++;
            }
            if (sheet.get(j).getSub3() < 4) {
                k++;
            }
            if (sheet.get(j).getSub4() < 4) {
                k++;
            }
            if (k != 0)
                debetors.add(sheet.get(j).toString());
        }
        listView.setItems(debetors);
    }

    public void readdeduct() {
        ObservableList<String> debetors = FXCollections.observableArrayList();
        for (int j = 0; j < sheet.size(); j++) {
            int k = 0;
            if (sheet.get(j).getSub1() < 4) {
                k++;
            }
            if (sheet.get(j).getSub2() < 4) {
                k++;
            }
            if (sheet.get(j).getSub3() < 4) {
                k++;
            }
            if (sheet.get(j).getSub4() < 4) {
                k++;
            }
            if (k >= 3)
                debetors.add(sheet.get(j).toString());
        }
        listView.setItems(debetors);
    }

    public void addStud() {

        Student stud = new Student();
        if (i != 0) {
            stud.setGroup(tranfer(group.getText()));
            stud.setName(surname.getText() + " " + name.getText() + " " + lastname.getText());
            stud.setSub1(tranfer(sub1.getText()));
            stud.setSub2(tranfer(sub2.getText()));
            stud.setSub3(tranfer(sub3.getText()));
            stud.setSub4(tranfer(sub4.getText()));
            if (isComplite(stud) && name.getText() != "" && surname.getText() != "" && lastname.getText() != "") {
                sheet.add(stud);
                btn.setText("Студент добавлен в список");
                i = 0;
            } else {
                btn.setText("Измените не верные формы");
            }
        } else {
            name.setText("");
            surname.setText("");
            lastname.setText("");
            sub1.setText("");
            sub2.setText("");
            sub3.setText("");
            sub4.setText("");

            go(add);
            i++;
        }
    }

    public void dltStud() {
        if (i == 0) {
            listdlt.setItems(field());
            go(delete);
            i++;
        } else {
            sheet.remove(listdlt.getSelectionModel().getSelectedIndex());
            listdlt.setItems(field());
        }
    }

    public void findStud() {
        txt.setText("");
        findpane.setVisible(false);
        if (i == 0) {
            go(find);
            i++;
            findfield.setText("");
        } else if (i == 1) {
            Stream<Student> sd = sheet.stream();
            String j = findfield.getText();
            j = j.trim();
            String jj[] = j.split("\\s+");
            if (jj.length == 3)
                j = jj[0] + " " + jj[1] + " " + jj[2];
            Student stud = new Student();
            stud.setName(j);
            List<Student> st = sd.filter(x -> x.toString().contains(stud.getName())).collect(Collectors.toList());
            if (st.size() != 0) {
                findpane.setVisible(true);
                Student student = st.get(0);
                fname.setText(student.getName());
                fsub1.setText(student.getSub1() + "");
                fsub2.setText(student.getSub2() + "");
                fsub3.setText(student.getSub3() + "");
                fsub4.setText(student.getSub4() + "");
                fgroup.setText(student.getGroup() + "");
                fmid.setText(student.getAmark() + "");
            } else
                txt.setText("Студента по ФИО " + findfield.getText() + " не существует,\nудостоверьтесь в правильности введенных данных");
        }
    }


    public void chngStud() {
        Student stud = new Student();
        if (i == 0) {
            listchng.setItems(field());
            go(change);
            i = 1;
        } else if (i == 1) {
            int j = listchng.getSelectionModel().getSelectedIndex();
            stud = sheet.get(j);
            String s[] = stud.getName().split("\\s+");
            name1.setText(s[1]);
            surname1.setText(s[0]);
            lastname1.setText(s[2]);
            String ss = "" + stud.getGroup();
            group1.setText(stud.getGroup() + "");
            sub11.setText(stud.getSub1() + "");
            sub21.setText(stud.getSub2() + "");
            sub31.setText(stud.getSub3() + "");
            sub41.setText(stud.getSub4() + "");
            back();
            go(setchange);
            i = 2;
        } else if (i == 2) {
            stud.setGroup(tranfer(group1.getText()));
            stud.setName(surname1.getText() + " " + name1.getText() + " " + lastname1.getText());
            stud.setSub1(tranfer(sub11.getText()));
            stud.setSub2(tranfer(sub21.getText()));
            stud.setSub3(tranfer(sub31.getText()));
            stud.setSub4(tranfer(sub41.getText()));
            if (isComplite(stud)) {
                sheet.set(listchng.getSelectionModel().getSelectedIndex(), stud);
                btn1.setText("Данные о студенте изменены");
                back();
                btn1.setText("Изменить данные о студенте");
            } else {
                btn1.setText("Измените не верные формы");
            }
        }
    }


    public void back() {
        listed.setVisible(false);
        delete.setVisible(false);
        add.setVisible(false);
        change.setVisible(false);
        find.setVisible(false);
        setchange.setVisible(false);
        findpane.setVisible(false);
        main.setVisible(true);

        i = 0;
    }

    public void exit() {
        try {
            stp();
        } catch (Exception e) {
        }
        System.exit(0);
    }

    private void go(Pane pane) {
        main.setVisible(false);
        pane.setVisible(true);
    }

    private boolean isComplite(Student stud) {
        return stud.getGroup() != 0 && stud.getName() != "" && stud.getSub1() <= 10 && stud.getSub1() > 0 && stud.getSub2() <= 10 && stud.getSub2() > 0 && stud.getSub3() <= 10 && stud.getSub3() > 0 && stud.getSub4() <= 10 && stud.getSub4() > 0;
    }

    private static LinkedList<Student> sheet = new LinkedList<>();

    public void strt() throws Exception {
        List<String> arr;
        arr = Files.readAllLines(Paths.get(path));
        for (int i = 0; i < arr.size(); i++) {
            Student stud = new Student();
            String k[] = arr.get(i).split("\\|+");
            stud.setGroup(tranfer(k[1]));
            stud.setName(k[2]);
            stud.setSub1(tranfer(k[3]));
            stud.setSub2(tranfer(k[4]));
            stud.setSub3(tranfer(k[5]));
            stud.setSub4(tranfer(k[6]));
            sheet.add(stud);
        }
    }

    public void stp() throws Exception {
        String s = "";
        Student stud;
        for (int j = 0; j < sheet.size(); j++) {
            stud = sheet.get(j);
            s += j + 1 + "|" + stud.getGroup() + "|" + stud.getName() + "|" + stud.getSub1() + "|" + stud.getSub2() + "|" + stud.getSub3() + "|" + stud.getSub4() + "|\n";
        }
        File file = new File(path);
        FileWriter writer = new FileWriter(file);
        writer.write(s);
        writer.flush();
        writer.close();
    }

    private ObservableList<String> field() {
        ObservableList<String> field = FXCollections.observableArrayList();
        for (int j = 0; j < sheet.size(); j++) {
            field.add(sheet.get(j).toString());
        }
        return field;
    }

    private static int tranfer(String s) {
        int dig = 0;
        int k = 0;
        char ch[] = s.toCharArray();
        for (int i = ch.length - 1; i != -1; i--, k++) {
            if ((int) ch[i] == 48) {
                dig += 0 * Math.pow(10, k);
            } else if ((int) ch[i] == 49) {
                dig += 1 * Math.pow(10, k);
            } else if ((int) ch[i] == 50) {
                dig += 2 * Math.pow(10, k);
            } else if ((int) ch[i] == 51) {
                dig += 3 * Math.pow(10, k);
            } else if ((int) ch[i] == 52) {
                dig += 4 * Math.pow(10, k);
            } else if ((int) ch[i] == 53) {
                dig += 5 * Math.pow(10, k);
            } else if ((int) ch[i] == 54) {
                dig += 6 * Math.pow(10, k);
            } else if ((int) ch[i] == 55) {
                dig += 7 * Math.pow(10, k);
            } else if ((int) ch[i] == 56) {
                dig += 8 * Math.pow(10, k);
            } else if ((int) ch[i] == 57) {
                dig += 9 * Math.pow(10, k);
            }
        }
        return dig;
    }

    @FXML
    private ListView<String> listView;
    @FXML
    private ListView<String> listdlt;
    @FXML
    private ListView<String> listchng;
    @FXML
    private Button btn;
    @FXML
    private TextField group;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField sub1;
    @FXML
    private TextField sub2;
    @FXML
    private TextField sub3;
    @FXML
    private TextField sub4;
    @FXML
    private TextField group1;
    @FXML
    private TextField name1;
    @FXML
    private TextField surname1;
    @FXML
    private TextField lastname1;
    @FXML
    private TextField sub11;
    @FXML
    private TextField sub21;
    @FXML
    private TextField sub31;
    @FXML
    private TextField sub41;
    @FXML
    private TextField findfield;
    @FXML
    private Pane setchange;
    @FXML
    private Pane listed;
    @FXML
    private Pane main;
    @FXML
    private Pane delete;
    @FXML
    private Pane add;
    @FXML
    private Pane change;
    @FXML
    private Pane find;
    @FXML
    private Pane findpane;
    @FXML
    private Button btn1;
    @FXML
    private Text txt;
    @FXML
    private Label fname;
    @FXML
    private Label fsub1;
    @FXML
    private Label fsub2;
    @FXML
    private Label fsub3;
    @FXML
    private Label fsub4;
    @FXML
    private Label fgroup;
    @FXML
    private Label fmid;

}
