import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    private String currentPlayer = "X";
    private Label statusLabel = new Label("Player X's Turn");
    private Button[] buttons = new Button[9];

    @Override
    public void start(Stage stage) {
        // 1. Requirement: Label for Status
        statusLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        // 2. Requirement: Layout (VBox for rows)
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new javafx.geometry.Insets(20));

        // Create the 3x3 Grid using HBoxes
        for (int i = 0; i < 3; i++) {
            HBox row = new HBox(10);
            row.setAlignment(Pos.CENTER);
            for (int j = 0; j < 3; j++) {
                int index = i * 3 + j;
                buttons[index] = new Button("");
                buttons[index].setPrefSize(60, 60);

                // 3. Requirement: setOnAction (Game Logic)
                buttons[index].setOnAction(e -> handleMove(index));

                row.getChildren().add(buttons[index]);
            }
            root.getChildren().add(row);
        }

        // 4. Requirement: Extra Button (Reset)
        Button resetBtn = new Button("Reset Game");
        resetBtn.setOnAction(e -> resetGame());

        root.getChildren().addAll(statusLabel, resetBtn);

        Scene scene = new Scene(root, 300, 350);
        stage.setTitle("ITEC 3860 Tic-Tac-Toe");
        stage.setScene(scene);
        stage.show();
    }

    private void handleMove(int index) {
        if (buttons[index].getText().equals("") && !statusLabel.getText().contains("Wins")) {
            buttons[index].setText(currentPlayer);
            if (checkWin()) {
                statusLabel.setText("Player " + currentPlayer + " Wins!");
            } else {
                currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                statusLabel.setText("Player " + currentPlayer + "'s Turn");
            }
        }
    }

    private boolean checkWin() {
        // 1. Check Rows (Horizontal)
        for (int i = 0; i < 9; i += 3) {
            if (!buttons[i].getText().equals("") &&
                    buttons[i].getText().equals(buttons[i+1].getText()) &&
                    buttons[i].getText().equals(buttons[i+2].getText())) {
                return true;
            }
        }

        // 2. Check Columns (Vertical)
        for (int i = 0; i < 3; i++) {
            if (!buttons[i].getText().equals("") &&
                    buttons[i].getText().equals(buttons[i+3].getText()) &&
                    buttons[i].getText().equals(buttons[i+6].getText())) {
                return true;
            }
        }

        // 3. Check Diagonals
        if (!buttons[0].getText().equals("") &&
                buttons[0].getText().equals(buttons[4].getText()) &&
                buttons[0].getText().equals(buttons[8].getText())) {
            return true;
        }

        if (!buttons[2].getText().equals("") &&
                buttons[2].getText().equals(buttons[4].getText()) &&
                buttons[2].getText().equals(buttons[6].getText())) {
            return true;
        }

        return false;
    }

    private void resetGame() {
        for (Button btn : buttons) btn.setText("");
        currentPlayer = "X";
        statusLabel.setText("Player X's Turn");
    }

    public static void main(String[] args) {
        launch(args);
    }
}