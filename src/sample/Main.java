package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    /*Все данные в linkedList загружаются из с:\lab7\src\tmp\database.txt с помощью метода strt() в классе Controller
    Далее по ходу работы программы данные обрабатываются(просмотр,добавление,удаление,изменение,нахождение по ФИО) и беруться из linkedlist
    Сформирование списков студентов задложников и списков для отчисления формируются исходя из linkedlist в методах readdebetors() и readdeduct() класса Controller
    После обработки данных данные загружаются обратно в C:\lab7\src\tmp\database.txt с помощью метода stp() класса Controller

    */

    Controller cr = new Controller();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Student list app");
        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.show();

        cr.strt();
    }

    public void stop() throws Exception {
        cr.stp();
    }

    public static void main(String[] args) {

        launch(args);
    }

}
