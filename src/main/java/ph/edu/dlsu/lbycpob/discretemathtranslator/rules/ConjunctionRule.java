package ph.edu.dlsu.lbycpob.discretemathtranslator.rules;

import ph.edu.dlsu.lbycpob.discretemathtranslator.model.Expression;
import ph.edu.dlsu.lbycpob.discretemathtranslator.model.TranslationResult;

/**
 * Handles conjunction statements and translates them into
 * propositional logic conjunction notation.
 *
 * <p>A conjunction connects two propositions using "and"
 * and is represented by the symbol ∧.</p>
 *
 * <p>Example:
 * "Alice studies and Bob studies."
 * becomes:
 * p ∧ q</p>
 *
 */
public class ConjunctionRule extends TranslationRule {

    /**
     * Determines whether the given expression contains
     * a supported conjunction pattern.
     *
     * @param expression the English statement to examine
     * @return true if the statement is recognized as a conjunction;
     *         false otherwise
     */

    @Override
    public boolean matches(Expression expression) {
        if (expression == null || expression.getEnglishStatement() == null) {
            return false;
        }

        String input = expression.getEnglishStatement().trim().toLowerCase();

        return input.contains(" and ")
                || input.contains(" as well as ")
                || input.contains(" but ");
    }

    /**
     * Translates a supported conjunction statement into
     * propositional logic notation.
     *
     * @param expression the English statement to translate
     * @return the resulting translation
     */

    @Override
    public TranslationResult translate(Expression expression) {
        TranslationResult result = new TranslationResult();

        String input = expression.getEnglishStatement().trim();
        String lowerInput = input.toLowerCase();

        String leftSide;
        String rightSide;

        String connector;

        if (lowerInput.contains(" as well as ")) {
            connector = " as well as ";
        } else if (lowerInput.contains(" and ")) {
            connector = " and ";
        } else if (lowerInput.contains(" but ")) {
            connector = " but ";
        } else {
            return result;
        }

        int connectorIndex = lowerInput.indexOf(connector);

        leftSide = input.substring(0, connectorIndex).trim();
        rightSide = input.substring(
                connectorIndex + connector.length()
        ).trim();

        result.setTranslatedNotation("p ∧ q");

        result.addLegendEntry("p", leftSide);
        result.addLegendEntry("q", rightSide);

        result.setExplanation(
                "The statement expresses a conjunction. "
                        + "\"" + leftSide + "\" and "
                        + "\"" + rightSide + "\" are connected by a logical AND."
        );

        return result;
    }
}