package ph.edu.dlsu.lbycpob.discretemathtranslator.rules;

import ph.edu.dlsu.lbycpob.discretemathtranslator.model.Expression;
import ph.edu.dlsu.lbycpob.discretemathtranslator.model.TranslationResult;

/**
 * Handles negation statements and translates them into
 * propositional logic negation notation.
 *
 * <p>A negation represents the logical denial of a proposition
 * and is represented using the symbol ¬.</p>
 *
 * <p>Supported patterns include:
 * <ul>
 *     <li>Not P</li>
 *     <li>P is not ...</li>
 *     <li>P does not ...</li>
 *     <li>P never ...</li>
 * </ul>
 *
 */
public class NegationRule extends TranslationRule {

    /**
     * Determines whether the given expression contains
     * a supported negation pattern.
     *
     * @param expression the English statement to examine
     * @return true if the statement is recognized as a negation;
     *         false otherwise
     */

    @Override
    public boolean matches(Expression expression) {
        if (expression == null || expression.getEnglishStatement() == null) {
            return false;
        }

        String input = expression.getEnglishStatement().trim().toLowerCase();

        return input.startsWith("not ")
                || input.contains(" is not ")
                || input.contains(" are not ")
                || input.contains(" does not ")
                || input.contains(" do not ")
                || input.contains(" never ");
    }

    /**
     * Translates a supported negation statement into
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

        String proposition;

        if (lowerInput.startsWith("not ")) {

            proposition = input.substring(4).trim();

        } else if (lowerInput.contains(" is not ")) {

            int index = lowerInput.indexOf(" is not ");

            proposition = input.substring(0, index).trim()
                    + input.substring(index + 6).trim();

        } else if (lowerInput.contains(" are not ")) {

            int index = lowerInput.indexOf(" are not ");

            proposition = input.substring(0, index).trim()
                    + input.substring(index + 7).trim();

        } else if (lowerInput.contains(" does not ")) {

            int index = lowerInput.indexOf(" does not ");

            proposition = input.substring(0, index).trim()
                    + input.substring(index + 10).trim();

        } else if (lowerInput.contains(" do not ")) {

            int index = lowerInput.indexOf(" do not ");

            proposition = input.substring(0, index).trim()
                    + input.substring(index + 7).trim();

        } else if (lowerInput.contains(" never ")) {

            int index = lowerInput.indexOf(" never ");

            proposition = input.substring(0, index).trim()
                    + input.substring(index + 7).trim();

        } else {
            return result;
        }

        result.setTranslatedNotation("¬p");

        result.addLegendEntry("p", proposition);

        result.setExplanation(
                "The statement expresses a negation. "
                        + "The proposition \"" + proposition
                        + "\" is being denied."
        );

        return result;
    }
}