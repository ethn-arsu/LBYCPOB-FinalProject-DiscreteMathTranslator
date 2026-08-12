package ph.edu.dlsu.lbycpob.discretemathtranslator.rules;

import ph.edu.dlsu.lbycpob.discretemathtranslator.model.Expression;
import ph.edu.dlsu.lbycpob.discretemathtranslator.model.TranslationResult;

/**
 * Handles biconditional statements and translates them into
 * propositional logic biconditional notation.
 *
 * <p>A biconditional states that two propositions are true
 * under the same conditions and is represented by the symbol ↔.</p>
 *
 * <p>Supported pattern:
 * "P if and only if Q"</p>
 *
 */
public class BiconditionalRule extends TranslationRule {

    /**
     * Determines whether the given expression contains
     * a supported biconditional pattern.
     *
     * @param expression the English statement to examine
     * @return true if the statement is recognized as a
     *         biconditional; false otherwise
     */

    @Override
    public boolean matches(Expression expression) {
        if (expression == null || expression.getEnglishStatement() == null) {
            return false;
        }

        String input = expression.getEnglishStatement()
                .trim()
                .toLowerCase();

        return input.contains(" if and only if ");
    }

    /**
     * Translates a supported biconditional statement into
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

        String connector = " if and only if ";
        int connectorIndex = lowerInput.indexOf(connector);

        if (connectorIndex == -1) {
            return result;
        }

        String leftSide = input.substring(0, connectorIndex).trim();
        String rightSide = input.substring(
                connectorIndex + connector.length()
        ).trim();

        result.setTranslatedNotation("p ↔ q");

        result.addLegendEntry("p", leftSide);
        result.addLegendEntry("q", rightSide);

        result.setExplanation(
                "The statement expresses a biconditional relationship. "
                        + "\"" + leftSide + "\" and "
                        + "\"" + rightSide + "\" are connected by "
                        + "a biconditional (↔)."
        );

        return result;
    }
}