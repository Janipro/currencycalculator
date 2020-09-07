package currencycalculator;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AppController {

    @FXML
    TextField currencyInput, currencyOutput;

    @FXML
    ComboBox<String> fromCurrency, toCurrency;

    @FXML
    void initialize(){
        var currencies = FXCollections.observableArrayList("USD", "NOK", "EUR");
        fromCurrency.setItems(currencies);
        toCurrency.setItems(currencies);
        fromCurrency.getSelectionModel().select("USD");
        toCurrency.getSelectionModel().select("USD");
        currencyInput.textProperty().addListener((observable, oldValue, newValue) -> {
            handleCurrencyInput(newValue);
        });
    }


    void handleCurrencyInput(String value){
        var value1 = fromCurrency.getValue();
        var value2 = toCurrency.getValue();
        var ratio = calculateRatio(value1, value2);


        try {
            var currentCurrencyOutput = Double.parseDouble(currencyInput.getText()) * ratio;
            currentCurrencyOutput = Math.abs(Math.round(currentCurrencyOutput*100.0))/100.0; //Instead of not allowing a negative input value (which can happen accidentally), this workaround takes care of the problem by using the absolute value.
            currencyOutput.setText(Double.toString(currentCurrencyOutput));
        } catch (NumberFormatException e) {
            currencyOutput.setText("Please use numbers");
        }
    }

    double calculateRatio(String currency1, String currency2){
        var currency1Value = 0.0;
        var currency2Value = 0.0;

        if (currency1.equals("USD")){
            currency1Value = 8.85;
        } else if (currency1.equals("NOK")){
            currency1Value = 1;
        } else if (currency1.equals("EUR")){
            currency1Value = 10.53;
        }

        if (currency2.equals("USD")){
            currency2Value = 8.85;
        } else if (currency2.equals("NOK")){
            currency2Value = 1;
        } else if (currency2.equals("EUR")){
            currency2Value = 10.53;
        }

        return currency1Value / currency2Value;
    }
}
