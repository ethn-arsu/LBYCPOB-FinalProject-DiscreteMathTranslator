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
        if (expression == null
                || expression.getEnglishStatement() == null) {
            return false;
        }

        String input = expression.getEnglishStatement()
                .trim()
                .toLowerCase();

        // "and" is part of the biconditional phrase
        // "if and only if", so it must not be treated
        // as a conjunction.
        if (input.contains("if and only if")) {
            return false;
        }

        return input.contains(" and ");
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

        if (!matches(expression)) {
            return result;
        }

        String input = expression.getEnglishStatement().trim();
        String lowerInput = input.toLowerCase();

        String connector = " and ";
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

        result.setTranslatedNotation("p ∧ q");

        result.addLegendEntry("p", leftSide);
        result.addLegendEntry("q", rightSide);

        result.setExplanation(
                "The statement expresses a conjunction. "
                        + "\"" + leftSide + "\" and "
                        + "\"" + rightSide + "\" are connected by "
                        + "conjunction (∧)."
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