package ph.edu.dlsu.lbycpob.discretemathtranslator.rules;

import ph.edu.dlsu.lbycpob.discretemathtranslator.model.Expression;
import ph.edu.dlsu.lbycpob.discretemathtranslator.model.TranslationResult;

/**
 * Handles disjunction statements and translates them into
 * propositional logic disjunction notation.
 *
 * <p>A disjunction connects two propositions using "or"
 * and is represented by the symbol ∨.</p>
 *
 * <p>Example:
 * "Alice studies or Bob studies."
 * becomes:
 * p ∨ q</p>
 *
 */
public class DisjunctionRule extends TranslationRule {

    /**
     * Determines whether the given expression contains
     * a supported disjunction pattern.
     *
     * @param expression the English statement to examine
     * @return true if the statement is recognized as a disjunction;
     *         false otherwise
     */

    @Override
    public boolean matches(Expression expression) {
        if (expression == null
                || expression.getEnglishStatement() == null) {
            return false;
        }

        String input = expression.getEnglishStatement()
                .trim()
                .toLowerCase();

        return input.contains(" or ");
    }


    /**
     * Translates a supported disjunction statement into
     * propositional logic notation.
     *
     * @param expression the English statement to translate
     * @return the resulting translation
     */

    @Override
    public TranslationResult translate(Expression expression) {
        TranslationResult result = new TranslationResult();

        if (!matches(expression)) {
            return result;
        }

        String input = expression.getEnglishStatement().trim();
        String lowerInput = input.toLowerCase();

        String connector = " or ";
        int connectorIndex = lowerInput.indexOf(connector);

        if (connectorIndex == -1) {
            return result;
        }

        String leftSide = input.substring(0, connectorIndex).trim();
        String rightSide = input.substring(
                connectorIndex + connector.length()
        ).trim();

        leftSide = removeTrailingPunctuation(leftSide);
        rightSide = removeTrailingPunctuation(rightSide);

        if (leftSide.isEmpty() || rightSide.isEmpty()) {
            return result;
        }

        result.setTranslatedNotation("p ∨ q");

        result.addLegendEntry("p", leftSide);
        result.addLegendEntry("q", rightSide);

        result.setExplanation(
                "The statement expresses a disjunction. "
                        + "\"" + leftSide + "\" and "
                        + "\"" + rightSide + "\" are connected by "
                        + "disjunction (∨)."
        );

        return result;
    }

    /**
     * Removes punctuation from the end of a proposition.
     */
    private String removeTrailingPunctuation(String text) {
        return text.replaceAll("[.,!?]+$", "").trim();
    }
}