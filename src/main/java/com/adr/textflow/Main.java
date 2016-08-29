//    TextFlow Builder is a JavaFX library create styled labels
//    Copyright (C) 2016 Adrián Romero Corchado.
//
//    This file is part of TextFlow Builder
//
//     Licensed under the Apache License, Version 2.0 (the "License");
//     you may not use this file except in compliance with the License.
//     You may obtain a copy of the License at
//     
//         http://www.apache.org/licenses/LICENSE-2.0
//     
//     Unless required by applicable law or agreed to in writing, software
//     distributed under the License is distributed on an "AS IS" BASIS,
//     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//     See the License for the specific language governing permissions and
//     limitations under the License.
package com.adr.textflow;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        try {
            primaryStage.setTitle("TextFlow Builder samples");

            VBox root = new VBox();
            root.setPadding(new Insets(5.0));
            root.setSpacing(5);

            root.getChildren().addAll(
                    CreateCardTextFlow("{-fx-fill:red; -fx-font-weight: bold;}Simple styled text."),
                    CreateCardTextFlow("{-fx-fill:red; -fx-font-weight: bold;}More{-fx-fill: blue; -fx-font-size: 30;} than{} one style."),
                    CreateCardTextFlow("And if you do not want style, do not add style tags..."),
                    CreateCardTextFlow("Remember to {-fx-font-weight: bold;}\\{escape\\}{} brackets if you want to use it."),
                    CreateCardFlowPane("With FlowPanel you can {-fx-background-color: lightgray; -fx-background-radius: 5;} style {} the background.")
            );

            ScrollPane scroll = new ScrollPane(root);
            scroll.setFitToWidth(true);
            scroll.setFocusTraversable(false);
            primaryStage.setScene(new Scene(scroll, 800, 400));
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Node CreateCardTextFlow(String value) throws IOException {
        return createCard("Pane text = TextContainer.createTextFlow(\"" + simpleEscape(value) + "\");", TextContainer.createTextFlow(value));
    }

    private Node CreateCardFlowPane(String value) throws IOException {
        return createCard("Pane text = TextContainer.createFlowPane(\"" + simpleEscape(value) + "\");", TextContainer.createFlowPane(value));
    }

    private Node createCard(String value, Node n) {
        VBox parent = new VBox();
        parent.setSpacing(5);
        parent.setStyle("-fx-background-color: white; -fx-background-radius: 5; -fx-padding: 5;");

        TextField t = new TextField(value);
        t.setEditable(false);
        t.setFocusTraversable(false);
        t.setStyle("-fx-background-color: transparent; -fx-font-family: monospace; -fx-font-weight: bold; -fx-text-fill: gray;");

        Button b = new Button("Copy");
        b.setFocusTraversable(false);
        b.setOnAction(e -> {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString(value);
            clipboard.setContent(content);
        });

        BorderPane border = new BorderPane();
        border.setCenter(t);
        border.setRight(b);

        parent.getChildren().addAll(border, n);

        return parent;
    }

    private String simpleEscape(String value) {
        return value.replaceAll("\\\\", "\\\\\\\\");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
