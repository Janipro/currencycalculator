module javafxapp {
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires javafx.controls;

    exports currencycalculator;

	opens currencycalculator to javafx.fxml;
}