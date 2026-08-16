package ph.edu.dlsu.lbycpob.discretemathtranslator.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import ph.edu.dlsu.lbycpob.discretemathtranslator.engine.TranslatorEngine;
import ph.edu.dlsu.lbycpob.discretemathtranslator.model.Expression;
import ph.edu.dlsu.lbycpob.discretemathtranslator.model.TranslationResult;
import ph.edu.dlsu.lbycpob.discretemathtranslator.utils.InputValidator;

import java.util.Map;

/**
 * Acts as the bridge between the JavaFX view and the
 * translation engine.
 *
 * <p>The view (FXML controller) calls {@link #handleTranslation(String)}
 * whenever the user submits an English statement, and receives
 * a {@link TranslationResult} to display.</p>
 */
public class MainController {

    /**
     * The engine responsible for performing the actual translation.
     */
    private final TranslatorEngine translatorEngine;

    /**
     * Input field where the English statement is input.
     */
    @FXML
    private TextField inputField;

    /**
     * Text area to display the translated result.
     */
    @FXML
    private TextArea translationLabel;

    /**
     * Text area displaying the variable legend for the translation.
     */
    @FXML
    private TextArea legendLabel;

    /**
     * Text area displaying the explanation for the translation.
     */
    @FXML
    private TextArea explanationLabel;

    /**
     * Label displaying the translation status.
     */
    @FXML
    private Label statusLabel;

    /**
     * Initializes the controller with a new TranslatorEngine instance.
     */
    public MainController() {
        this.translatorEngine = new TranslatorEngine();
    }

    /**
     * Handles the translation button and an Enter key pressed within input field.
     *
     * @param event the JavaFX action event
     */
    @FXML
    private void handleTranslation(ActionEvent event) {

        String rawInput = inputField.getText();

        // Input Validation
        if (rawInput == null || rawInput.trim().isEmpty()) {
            statusLabel.setText("Please enter a valid input");
            statusLabel.getStyleClass().add("error-label");

            translationLabel.setText("");
            legendLabel.clear();
            explanationLabel.setText("");
            return;
        }

        Expression expression = new Expression(rawInput);

        TranslationResult translationResult =
                translatorEngine.translate(expression);

        if (translationResult.isSuccessful()) {
            translationLabel.setText(
                    translationResult.getTranslatedNotation()
            );

            displayVariableLegend(translationResult);

            explanationLabel.setText(
                    translationResult.getExplanation()
            );

            statusLabel.setText("Successfully translated");
            statusLabel.getStyleClass().add("success-label");

        } else {
            translationLabel.setText(
                    "Cannot translate the given expression"
            );

            // Clear the previous translation's variable legend.
            legendLabel.clear();

            explanationLabel.setText(
                    translationResult.getExplanation()
            );

            statusLabel.setText("Failed translating");
            statusLabel.getStyleClass().add("error-label");
        }
    }

    /**
     * Displays the variables and their meanings.
     *
     * <p>Example:
     * A or B
     * will display:
     * p = A
     * q = B
     * </p>
     *
     * @param translationResult the result containing the legends
     */
    private void displayVariableLegend(
            TranslationResult translationResult) {

        Map<String, String> legend =
                translationResult.getVariableLegend();

        if (legend == null || legend.isEmpty()) {
            legendLabel.setText("No variables found");
            return;
        }

        StringBuilder legendText = new StringBuilder();

        for (Map.Entry<String, String> entry : legend.entrySet()) {
            legendText.append(entry.getKey())
                    .append(" = ")
                    .append(entry.getValue())
                    .append("\n");
        }

        legendLabel.setText(legendText.toString().trim());
    }

    /**
     * Handles the clear button, clears all input and results.
     *
     * @param event the JavaFX action event
     */
    @FXML
    private void handleClear(ActionEvent event) {
        inputField.clear();
        translationLabel.clear();
        legendLabel.clear();
        explanationLabel.clear();
        statusLabel.setText("");
    }

    /**
     * Handles a translation request from the view.
     *
     * @param rawInput the raw English statement typed by the user
     * @return the result of the translation
     */
    public TranslationResult handleTranslation(String rawInput) {
        Expression expression = new Expression(rawInput);
        return translatorEngine.translate(expression);
    }
}