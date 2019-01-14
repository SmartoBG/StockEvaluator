package shoeEvaluator.dataModel;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StockItem {

    private SimpleStringProperty itemId = new SimpleStringProperty();
    private SimpleStringProperty category = new SimpleStringProperty();
    private SimpleStringProperty season = new SimpleStringProperty();
    private SimpleStringProperty brand = new SimpleStringProperty();
    private SimpleStringProperty model = new SimpleStringProperty();
    private SimpleIntegerProperty size = new SimpleIntegerProperty();
    private SimpleStringProperty condition = new SimpleStringProperty();
    private SimpleStringProperty sex = new SimpleStringProperty();
    private SimpleDoubleProperty price = new SimpleDoubleProperty();
    private SimpleStringProperty quality = new SimpleStringProperty();

    public StockItem(String itemId, String category, String season, String brand, String model, Integer size, String condition, String sex, Double price, String quality) {
        this.itemId.set(itemId);
        this.category.set(category);
        this.season.set(season);
        this.brand.set(brand);
        this.model.set(model);
        this.size.set(size);
        this.condition.set(condition);
        this.sex.set(sex);
        this.price.set(price);
        this.quality.set(quality);
    }

    public StockItem() {
    }

    public String getItemId() {
        return itemId.get();
    }

    public SimpleStringProperty itemIdProperty() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId.set(itemId);
    }

    public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getSeason() {
        return season.get();
    }

    public SimpleStringProperty seasonProperty() {
        return season;
    }

    public void setSeason(String season) {
        this.season.set(season);
    }

    public String getBrand() {
        return brand.get();
    }

    public SimpleStringProperty brandProperty() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public String getModel() {
        return model.get();
    }

    public SimpleStringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public int getSize() {
        return size.get();
    }

    public SimpleIntegerProperty sizeProperty() {
        return size;
    }

    public void setSize(int size) {
        this.size.set(size);
    }

    public String getCondition() {
        return condition.get();
    }

    public SimpleStringProperty conditionProperty() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition.set(condition);
    }

    public String getSex() {
        return sex.get();
    }

    public SimpleStringProperty sexProperty() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex.set(sex);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getQuality() {
        return quality.get();
    }

    public SimpleStringProperty qualityProperty() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality.set(quality);
    }

    @Override
    public String toString() {
        return "StockItem{" +
                "itemId=" + itemId +
                ", category=" + category +
                ", season=" + season +
                ", brand=" + brand +
                ", model=" + model +
                ", size=" + size +
                ", condition=" + condition +
                ", sex=" + sex +
                ", price=" + price +
                ", quality=" + quality +
                '}';
    }
}
