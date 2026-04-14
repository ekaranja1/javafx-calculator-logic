import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculatorApp extends Application {

    private double total = 0;

    @Override
    public void start(Stage stage) {
        // --- 1. UI Components ---
        Label screen = new Label("Total: 0.0");
        screen.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        TextField inputField = new TextField();
        inputField.setPromptText("Enter number");
        inputField.setPrefWidth(100);

        TextArea historyLog = new TextArea();
        historyLog.setEditable(false);
        historyLog.setPrefHeight(120);

        // --- 2. Operation Buttons ---
        Button btnAdd = new Button("Add (+)");
        Button btnSub = new Button("Subtract (-)");
        Button btnMul = new Button("Multiply (*)");
        Button btnDiv = new Button("Divide (/)");
        Button btnClear = new Button("Clear All");

        // Set uniform width for buttons
        btnAdd.setPrefWidth(100); btnSub.setPrefWidth(100);
        btnMul.setPrefWidth(100); btnDiv.setPrefWidth(100);

        // --- 3. Event Handling (Logic) ---
        btnAdd.setOnAction(e -> calculate("Add", inputField, historyLog, screen));
        btnSub.setOnAction(e -> calculate("Sub", inputField, historyLog, screen));
        btnMul.setOnAction(e -> calculate("Mul", inputField, historyLog, screen));
        btnDiv.setOnAction(e -> calculate("Div", inputField, historyLog, screen));

        btnClear.setOnAction(e -> {
            total = 0;
            screen.setText("Total: 0.0");
            historyLog.clear();
            inputField.clear();
        });

        // --- 4. Layout (Nested HBoxes within VBox) ---
        HBox row1 = new HBox(10, btnAdd, btnSub);
        row1.setAlignment(Pos.CENTER);

        HBox row2 = new HBox(10, btnMul, btnDiv);
        row2.setAlignment(Pos.CENTER);

        VBox root = new VBox(15, screen, inputField, row1, row2, btnClear, new Label("History:"), historyLog);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        // --- 5. Stage Setup ---
        Scene scene = new Scene(root, 350, 500);
        stage.setTitle("GGC ITEC Calculator");
        stage.setScene(scene);
        stage.show();
    }

    private void calculate(String op, TextField input, TextArea log, Label screen) {
        try {
            double val = Double.parseDouble(input.getText());
            String action = "";

            switch (op) {
                case "Add" -> { total += val; action = "Added"; }
                case "Sub" -> { total -= val; action = "Subtracted"; }
                case "Mul" -> { total *= val; action = "Multiplied by"; }
                case "Div" -> {
                    if (val == 0) {
                        screen.setText("Error: Div by 0");
                        return;
                    }
                    total /= val;
                    action = "Divided by";
                }
            }
            screen.setText("Total: " + total);
            log.appendText(action + " " + val + " | Total: " + total + "\n");
            input.clear();
        } catch (NumberFormatException ex) {
            screen.setText("Error: Use Numbers Only");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}