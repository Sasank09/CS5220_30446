//Controller class for BillCalculator Application - to handle logic
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

    // On click event of Calculate button to find total amount due.
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
                amountValue.setText(String.format("$%.2f",total));
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

    //Alert popup message for exception handling
    @FXML
    void displayErrorMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Occurred");
        alert.setHeaderText("Please check your input and enter only decimals");
        alert.showAndWait();
    }


}
