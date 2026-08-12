package ph.edu.dlsu.lbycpob.discretemathtranslator.rules;

import ph.edu.dlsu.lbycpob.discretemathtranslator.model.Expression;
import ph.edu.dlsu.lbycpob.discretemathtranslator.model.TranslationResult;

/**
 * Handles conditional statements and translates them into
 * propositional logic implication notation.
 *
 * <p>Supported patterns include:
 * <ul>
 *     <li>If P, then Q</li>
 *     <li>If P then Q</li>
 *     <li>Q if P</li>
 *     <li>Q whenever P</li>
 *     <li>Q once P</li>
 * </ul>
 *
 */
public class ConditionalRule extends TranslationRule {

    @Override
    public boolean matches(Expression expression) {
        if (expression == null || expression.getEnglishStatement() == null) {
        return false;
    }

        String input = expression.getEnglishStatement().trim().toLowerCase();

        return input.startsWith("if ")
                && input.contains(" then ")
                || input.contains(" if ")
                || input.contains(" whenever ")
                || input.contains(" once ");
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

        String input = expression.getEnglishStatement().trim();

        String antecedent;
        String consequent;

        String lowerInput = input.toLowerCase();

        if (lowerInput.startsWith("if ") && lowerInput.contains(" then ")) {

            int thenIndex = lowerInput.indexOf(" then ");

            antecedent = input.substring(3, thenIndex).trim();
            consequent = input.substring(thenIndex + 6).trim();

        } else if (lowerInput.contains(" whenever ")) {

            int wheneverIndex = lowerInput.toLowerCase().indexOf(" whenever ");

            consequent = input.substring(0, wheneverIndex).trim();
            antecedent = input.substring(wheneverIndex + 10).trim();

        } else if (lowerInput.contains(" once ")) {

            int onceIndex = lowerInput.toLowerCase().indexOf(" once ");

            consequent = input.substring(0, onceIndex).trim();
            antecedent = input.substring(onceIndex + 6).trim();

        } else if (lowerInput.contains(" if ")) {

            int ifIndex = lowerInput.indexOf(" if ");

            consequent = input.substring(0, ifIndex).trim();
            antecedent = input.substring(ifIndex + 4).trim();

        } else {
            return result;
        }

        result.setTranslatedNotation("p → q");

        result.addLegendEntry("p", antecedent);
        result.addLegendEntry("q", consequent);

        result.setExplanation(
                "The statement expresses a conditional relationship. "
                        + "\"" + antecedent + "\" is the antecedent, while "
                        + "\"" + consequent + "\" is the consequent."
        );

        return result;
    }
}