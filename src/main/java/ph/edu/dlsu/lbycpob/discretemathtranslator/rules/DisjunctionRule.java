package ph.edu.dlsu.lbycpob.discretemathtranslator.rules;

import ph.edu.dlsu.lbycpob.discretemathtranslator.model.Expression;
import ph.edu.dlsu.lbycpob.discretemathtranslator.model.TranslationResult;

/**
 * Handles disjunction statements and translates them into
 * propositional logic disjunction notation.
 *
 * <p>A disjunction represents two propositions connected
 * by a logical OR.</p>
 *
 * <p>Supported patterns include:
 * <ul>
 *     <li>P or Q</li>
 *     <li>Either P or Q</li>
 *     <li>P otherwise Q</li>
 * </ul>
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
        if (expression == null || expression.getEnglishStatement() == null) {
            return false;
        }

        String input = expression.getEnglishStatement().trim().toLowerCase();

        return input.contains(" or ")
                || (input.startsWith("either ") && input.contains(" or "))
                || input.contains(" otherwise ");
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

        String input = expression.getEnglishStatement().trim();
        String lowerInput = input.toLowerCase();

        String leftSide;
        String rightSide;
        String connector;

        if (lowerInput.startsWith("either ")
                && lowerInput.contains(" or ")) {

            connector = " or ";

            int orIndex = lowerInput.indexOf(connector);

            leftSide = input.substring(7, orIndex).trim();
            rightSide = input.substring(orIndex + connector.length()).trim();

        } else if (lowerInput.contains(" otherwise ")) {

            connector = " otherwise ";

            int connectorIndex = lowerInput.indexOf(connector);

            leftSide = input.substring(0, connectorIndex).trim();
            rightSide = input.substring(
                    connectorIndex + connector.length()
            ).trim();

        } else if (lowerInput.contains(" or ")) {

            connector = " or ";

            int connectorIndex = lowerInput.indexOf(connector);

            leftSide = input.substring(0, connectorIndex).trim();
            rightSide = input.substring(
                    connectorIndex + connector.length()
            ).trim();

        } else {
            return result;
        }

        result.setTranslatedNotation("p ∨ q");

        result.addLegendEntry("p", leftSide);
        result.addLegendEntry("q", rightSide);

        result.setExplanation(
                "The statement expresses a disjunction. "
                        + "\"" + leftSide + "\" and "
                        + "\"" + rightSide + "\" are connected by a logical OR."
        );

        return result;
    }
}