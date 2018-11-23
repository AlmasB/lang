package com.almasb.lang;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class LangApp extends Application {

    private TextArea area = new TextArea();

    private List<Expression> expressions = new ArrayList<>();

    private Env environment = new Env(s -> area.appendText(s + "\n"));

    private Parent createContent() {

        expressions.add(new MemCommand());
        expressions.add(new AssignmentExpression());
        expressions.add(new AddExpression());

        VBox root = new VBox(50);

        root.setPrefSize(800, 600);

        area.setPrefSize(800 - 30, 600 - 50);
        area.setEditable(false);
        area.setFont(Font.font(48));

        TextField input = new TextField();
        input.setFont(Font.font(48));
        input.setOnAction(e -> {
            onCommand(input.getText());
            input.clear();
        });

        root.setPadding(new Insets(15));
        root.getChildren().addAll(area, input);

        return root;
    }

    private void onCommand(String line) {
        for (Expression e : expressions) {
            if (e.matches(line)) {
                e.eval(line, environment);
                return;
            }
        }

        area.appendText("Cannot eval: " + line + "\n");
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
