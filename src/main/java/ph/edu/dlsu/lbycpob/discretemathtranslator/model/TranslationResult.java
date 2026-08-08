package ph.edu.dlsu.lbycpob.discretemathtranslator.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Represents the output of the translation process.
 *
 * <p>Holds the translated discrete mathematics notation,
 * a legend mapping variables/symbols to their meanings,
 * and a human-readable explanation of how the translation
 * was derived.</p>
 */
public class TranslationResult {

    /**
     * The final translated statement in discrete math notation.
     */
    private String translatedNotation;

    /**
     * Maps each variable or symbol used in the notation
     * (e.g., "p", "q", "∀x") to its plain-English meaning.
     */
    private Map<String, String> variableLegend;

    /**
     * A human-readable explanation of how the translation
     * was produced (e.g., which rules were applied and why).
     */
    private String explanation;

    /**
     * Default constructor. Initializes an empty variable legend.
     */
    public TranslationResult() {
        this.variableLegend = new LinkedHashMap<>();
    }

    /**
     * Creates a TranslationResult with all fields populated.
     *
     * @param translatedNotation the translated notation
     * @param variableLegend     map of variables/symbols to meanings
     * @param explanation        explanation of the translation process
     */
    public TranslationResult(String translatedNotation, Map<String, String> variableLegend, String explanation) {
        this.translatedNotation = translatedNotation;
        this.variableLegend = (variableLegend != null) ? variableLegend : new LinkedHashMap<>();
        this.explanation = explanation;
    }

    public String getTranslatedNotation() {
        return translatedNotation;
    }

    public void setTranslatedNotation(String translatedNotation) {
        this.translatedNotation = translatedNotation;
    }

    public Map<String, String> getVariableLegend() {
        return variableLegend;
    }

    public void setVariableLegend(Map<String, String> variableLegend) {
        this.variableLegend = variableLegend;
    }

    /**
     * Adds a single variable/symbol entry to the legend.
     *
     * @param symbol  the variable or symbol (e.g., "p", "∀x")
     * @param meaning the plain-English meaning of that symbol
     */
    public void addLegendEntry(String symbol, String meaning) {
        if (this.variableLegend == null) {
            this.variableLegend = new LinkedHashMap<>();
        }
        this.variableLegend.put(symbol, meaning);
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    /**
     * Returns a formatted, readable summary of this result,
     * useful for console output or quick debugging.
     *
     * @return formatted string with notation, legend, and explanation
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Translated Notation: ").append(translatedNotation).append("\n");
        sb.append("Variable Legend:\n");
        if (variableLegend != null && !variableLegend.isEmpty()) {
            for (Map.Entry<String, String> entry : variableLegend.entrySet()) {
                sb.append("  ").append(entry.getKey()).append(" = ").append(entry.getValue()).append("\n");
            }
        } else {
            sb.append("  (none)\n");
        }
        sb.append("Explanation: ").append(explanation);
        return sb.toString();
    }
}