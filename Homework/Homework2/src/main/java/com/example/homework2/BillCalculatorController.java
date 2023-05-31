package com.example.homework2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class BillCalculatorController {

    @FXML
    private TextField itemPriceValue;


    @FXML
    private  TextField quantityValue;


    @FXML
    private TextField amountValue;


    @FXML
    void onClickCalculate(ActionEvent event) {
        try {
            double itemPrice = Double.parseDouble(itemPriceValue.getText());
            double quantity = Double.parseDouble(quantityValue.getText());
            if(itemPrice>=0 && quantity>=0) {
                double total = itemPrice*quantity;
                if(amountValue.getText()!="") {
                    amountValue.clear();
                }
                amountValue.setText("$"+String.valueOf(total));
            }
            else {
                amountValue.clear();
                displayErrorMessage();
            }

        }
        catch (Exception ex){
            amountValue.clear();
            displayErrorMessage();
        }
    }

    @FXML
    void displayErrorMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Occurred");
        alert.setHeaderText("Please check your input and enter only decimals");
        alert.showAndWait();
    }


}
