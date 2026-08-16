package ph.edu.dlsu.lbycpob.discretemathtranslator.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import ph.edu.dlsu.lbycpob.discretemathtranslator.engine.TranslatorEngine;
import ph.edu.dlsu.lbycpob.discretemathtranslator.model.Expression;
import ph.edu.dlsu.lbycpob.discretemathtranslator.model.TranslationResult;
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
     * Text area displaying the explanation for the translation.
     */
    @FXML
    private TextArea explanationLabel;

    /**
     * Label displaying the translation status
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