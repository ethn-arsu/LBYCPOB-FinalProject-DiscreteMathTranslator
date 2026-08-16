package ph.edu.dlsu.lbycpob.discretemathtranslator.rules;

import ph.edu.dlsu.lbycpob.discretemathtranslator.model.Expression;
import ph.edu.dlsu.lbycpob.discretemathtranslator.model.TranslationResult;

/**
 * Handles conditional statements and translates them into
 * propositional logic implication notation.
 *
 * <p>A conditional statement expresses that one proposition
 * implies another and is represented by the symbol →.</p>
 *
 * <p>Supported patterns include:
 * <ul>
 *     <li>If P, then Q</li>
 *     <li>If P, Q</li>
 *     <li>P implies Q</li>
 * </ul>
 *
 * <p>Example:
 * "If Alice studies, then Alice passes the exam."
 * becomes:
 * p → q</p>
 *
 */
public class ConditionalRule extends TranslationRule {

    @Override
    public boolean matches(Expression expression) {
        if (expression == null
                || expression.getEnglishStatement() == null) {
            return false;
        }

        String input = expression.getEnglishStatement()
                .trim()
                .toLowerCase();

        // This prevents it from claiming the statement as biconditional
        if (input.contains(" if and only if ")) {
            return false;
        }

        return (input.startsWith("if ") && input.contains(" then "))
                || (input.startsWith("if ") && input.contains(","))
                || input.contains(" implies ");
    }

    /**
     * Translates a supported conditional statement into
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

        String leftSide;
        String rightSide;

        if (lowerInput.startsWith("if ")
                && lowerInput.contains(" then ")) {

            int thenIndex = lowerInput.indexOf(" then ");

            leftSide = input.substring(3, thenIndex).trim();
            rightSide = input.substring(
                    thenIndex + 6
            ).trim();

        } else if (lowerInput.startsWith("if ")
                && lowerInput.contains(",")) {

            int commaIndex = input.indexOf(",");

            leftSide = input.substring(3, commaIndex).trim();
            rightSide = input.substring(
                    commaIndex + 1
            ).trim();

        } else {

            int impliesIndex = lowerInput.indexOf(" implies ");

            if (impliesIndex == -1) {
                return result;
            }

            leftSide = input.substring(0, impliesIndex).trim();
            rightSide = input.substring(
                    impliesIndex + 9
            ).trim();
        }

        leftSide = removeTrailingPunctuation(leftSide);
        rightSide = removeTrailingPunctuation(rightSide);

        if (leftSide.isEmpty() || rightSide.isEmpty()) {
            return result;
        }

        result.setTranslatedNotation("p → q");

        result.addLegendEntry("p", leftSide);
        result.addLegendEntry("q", rightSide);

        result.setExplanation(
                "The statement expresses a conditional relationship. "
                        + "\"" + leftSide + "\" is the condition, while "
                        + "\"" + rightSide + "\" is the consequence. "
                        + "The conditional relationship is represented "
                        + "by implication (→)."
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