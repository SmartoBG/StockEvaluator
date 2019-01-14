module MpulseEvaluator {
    requires javafx.fxml;
    requires javafx.controls;
    requires java.xml;

    opens shoeEvaluator;
    opens shoeEvaluator.dataModel;
}