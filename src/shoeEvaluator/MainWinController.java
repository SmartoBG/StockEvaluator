package shoeEvaluator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import shoeEvaluator.dataModel.StockItem;
import shoeEvaluator.dataModel.StockItemData;

import java.io.IOException;
import java.util.Optional;

public class MainWinController {

    @FXML
    private BorderPane mainWindow;

    @FXML
    private TableView<StockItem> stockItemTable;

    private StockItemData data;

    public void initialize(){
        data = new StockItemData();
        data.loadStockItems();
        stockItemTable.setItems(data.getStockItems());
    }

    @FXML
    public void showAddItemDialog(){

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainWindow.getScene().getWindow());
        dialog.setTitle("Добавяне на артикул");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("stockItemDialog.fxml"));
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e){
            System.out.println("Couldn't load dialog.");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            StockItemController stockItemController = fxmlLoader.getController();
            StockItem newItem = stockItemController.getNewStockItem();
            data.addStockItem(newItem);
            data.saveStockItems();
        }
    }

    @FXML
    public void showEditItemDialog(){
        StockItem selectedItem = stockItemTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Не е избран артикул за редакция!");
            alert.setHeaderText(null);
            alert.setContentText("Моля изберете артикул от списъка.");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Редакция на артикул");
        dialog.initOwner(mainWindow.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("stockItemDialog.fxml"));

        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Couldn't load dialog.");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);

        StockItemController stockItemController = fxmlLoader.getController();
        stockItemController.editItem(selectedItem);

        Optional<ButtonType> result =  dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            stockItemController.updateItem(selectedItem);
            data.saveStockItems();
        }

    }

    @FXML
    public void deleteItem() {
        StockItem selectedItem = stockItemTable.getSelectionModel().getSelectedItem();

        if (selectedItem == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Не е избран артикул за изтриване!");
            alert.setHeaderText(null);
            alert.setContentText("Моля изберете артикул от списъка.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Изтриване на артикул");
        alert.setHeaderText(null);
        alert.setContentText("Сигурни ли сте, че искате да изтриете избрания артикул!");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            data.deleteStockItem(selectedItem);
            data.saveStockItems();
        }

    }
}
