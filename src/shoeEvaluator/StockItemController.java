package shoeEvaluator;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import shoeEvaluator.dataModel.StockItem;

public class StockItemController {

    @FXML
    private TextField itemIdField;

    @FXML
    private TextField categoryField;

    @FXML
    private TextField sexField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField conditionField;

    @FXML
    private TextField sizeField;

    @FXML
    private TextField modelField;

    @FXML
    private TextField brandField;

    @FXML
    private TextField seasonField;

    @FXML
    private TextField qualityField;

    public StockItem getNewStockItem(){
        String itemId = itemIdField.getText();
        String category = categoryField.getText();
        String sex = sexField.getText();
        Double price = Double.parseDouble(priceField.getText());
        String condition = conditionField.getText();
        Integer size = Integer.parseInt(sizeField.getText());
        String model = modelField.getText();
        String brand = brandField.getText();
        String season = seasonField.getText();
        String quality = qualityField.getText();

        StockItem item = new StockItem(itemId,category,season,brand,model,size, condition,sex,price,quality);
        return item;
    }


    public void editItem(StockItem stockItem) {
        itemIdField.setText(stockItem.getItemId());
        categoryField.setText(stockItem.getCategory());
        sexField.setText(stockItem.getSex());
        priceField.setText(Double.toString(stockItem.getPrice()));
        conditionField.setText(stockItem.getCondition());
        sizeField.setText(Integer.toString(stockItem.getSize()));
        modelField.setText(stockItem.getModel());
        brandField.setText(stockItem.getBrand());
        seasonField.setText(stockItem.getSeason());
        qualityField.setText(stockItem.getQuality());
    }

    public void updateItem(StockItem stockItem) {
        stockItem.setItemId(itemIdField.getText());
        stockItem.setCategory(categoryField.getText());
        stockItem.setSex(sexField.getText());
        stockItem.setPrice(Double.valueOf(priceField.getText()));
        stockItem.setCondition(conditionField.getText());
        stockItem.setSize(Integer.valueOf(sizeField.getText()));
        stockItem.setModel(modelField.getText());
        stockItem.setBrand(brandField.getText());
        stockItem.setSeason(seasonField.getText());
        stockItem.setQuality(qualityField.getText());
    }
}
