package org.financetool.gui.rich.main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.financetool.domain.Statement;
import org.financetool.domain.Tag;
import org.financetool.file.input.InputDirectoryReader;
import org.financetool.file.input.InputFileReader;
import org.financetool.file.input.InputTabFileReader;
import org.financetool.file.input.TagFileReader;
import org.financetool.task.OperationTagger;
import org.financetool.util.TableUtils;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainView extends Application implements Initializable {

    @FXML
    private TableView<OperationTableRow> statementTable;
    @FXML
    private TableColumn<OperationTableRow, String> dateCol;
    @FXML
    private TableColumn<OperationTableRow, String> valueCol;
    @FXML
    private TableColumn<OperationTableRow, String> descriptionCol;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("Home Finance Tool");
        primaryStage.setScene(new Scene(root, 1000, 875));
        primaryStage.show();

    }

    private Statement loadStatement() {
        final String inputDirName = "./resources/data/input";
        System.out.println("Importing data from directory " + inputDirName);
        final InputDirectoryReader inputDirectoryReader = new InputDirectoryReader();
        Statement taggedStatement = null;
        try {
            final Statement statement = inputDirectoryReader.readAllFilesInDir(inputDirName);

            final String tagFileName = "./resources/data/tags/category-bills.txt";
            final TagFileReader tagFileReader = new TagFileReader();
            final Tag billTag = tagFileReader.readTagInputFile("Bill", tagFileName);

            final OperationTagger tagger = new OperationTagger();
            taggedStatement = tagger.tagStatement(statement, billTag);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taggedStatement;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        valueCol.setCellValueFactory(new PropertyValueFactory<>("value"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        statementTable.setItems(operationsList());

        // enable table multi-selection
        statementTable.getSelectionModel().setCellSelectionEnabled(true);
        statementTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        // enable table copy/paste
        TableUtils.installCopyPasteHandler(statementTable);

    }

    private ObservableList<OperationTableRow> operationsList() {
        final Statement statement = loadStatement();
        List<OperationTableRow> operationRows =
                statement.getOperationList().stream()
                        .map(OperationTableRow::new).collect(Collectors.toList());
        return FXCollections.observableArrayList(operationRows);
    }
}
