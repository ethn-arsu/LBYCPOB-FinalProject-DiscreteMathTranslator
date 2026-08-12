package ph.edu.dlsu.lbycpob.discretemathtranslator.rules;

import ph.edu.dlsu.lbycpob.discretemathtranslator.model.Expression;
import ph.edu.dlsu.lbycpob.discretemathtranslator.model.TranslationResult;

/**
 * Handles basic quantified statements and translates them into
 * predicate logic notation.
 *
 * <p>Supported quantifier patterns include:
 * <ul>
 *     <li>Every ...</li>
 *     <li>All ...</li>
 *     <li>Some ...</li>
 *     <li>There exists ...</li>
 * </ul>
 *
 */
public class QuantifierRule extends TranslationRule {

    /**
     * Determines whether the given expression contains
     * a supported quantifier pattern.
     *
     * @param expression the English statement to examine
     * @return true if the statement contains a supported
     *         quantifier; false otherwise
     */

    @Override
    public boolean matches(Expression expression) {
        if (expression == null || expression.getEnglishStatement() == null) {
            return false;
        }

        String input = expression.getEnglishStatement().trim().toLowerCase();

        return input.startsWith("every ")
                || input.startsWith("all ")
                || input.startsWith("some ")
                || input.startsWith("there exists ");
    }

    /**
     * Translates a supported quantified statement into
     * predicate logic notation.
     *
     * @param expression the English statement to translate
     * @return the resulting translation
     */

    @Override
    public TranslationResult translate(Expression expression) {
        TranslationResult result = new TranslationResult();

        String input = expression.getEnglishStatement().trim();
        String lowerInput = input.toLowerCase();

        String quantifier;
        String statement;

        if (lowerInput.startsWith("every ")) {

            quantifier = "∀";
            statement = input.substring(6).trim();

        } else if (lowerInput.startsWith("all ")) {

            quantifier = "∀";
            statement = input.substring(4).trim();

        } else if (lowerInput.startsWith("some ")) {

            quantifier = "∃";
            statement = input.substring(5).trim();

        } else if (lowerInput.startsWith("there exists ")) {

            quantifier = "∃";
            statement = input.substring(13).trim();

        } else {
            return result;
        }

        result.setTranslatedNotation(
                quantifier + "x P(x)"
        );

        result.addLegendEntry(
                "P(x)",
                statement
        );

        if (quantifier.equals("∀")) {
            result.setExplanation(
                    "The statement expresses a universal quantification. "
                            + "The statement applies to every element in the domain."
            );
        } else {
            result.setExplanation(
                    "The statement expresses an existential quantification. "
                            + "The statement applies to at least one element in the domain."
            );
        }

        return result;
    }
}