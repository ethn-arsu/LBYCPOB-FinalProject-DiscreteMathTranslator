package ph.edu.dlsu.lbycpob.discretemathtranslator.rules;

import ph.edu.dlsu.lbycpob.discretemathtranslator.model.Expression;
import ph.edu.dlsu.lbycpob.discretemathtranslator.model.TranslationResult;

/**
 * Handles basic set theory statements and translates them into
 * mathematical set notation.
 *
 * <p>Supported patterns include:
 * <ul>
 *     <li>An union B</li>
 *     <li>An intersect B</li>
 *     <li>A is a subset of B</li>
 *     <li>x is an element of A</li>
 * </ul>
 */
public class SetRule extends TranslationRule {

    /**
     * Determines whether the given expression contains
     * a supported set theory pattern.
     *
     * @param expression the English statement to examine
     * @return true if the statement contains a supported
     *         set theory pattern; false otherwise
     */

    @Override
    public boolean matches(Expression expression) {
        if (expression == null || expression.getEnglishStatement() == null) {
            return false;
        }

        String input = expression.getEnglishStatement().trim().toLowerCase();

        return input.contains(" union ")
                || input.contains(" intersect ")
                || input.contains(" subset of ")
                || input.contains(" element of ");
    }

    /**
     * Translates a supported set theory statement into
     * mathematical set notation.
     *
     * @param expression the English statement to translate
     * @return the resulting translation
     */

    @Override
    public TranslationResult translate(Expression expression) {
        TranslationResult result = new TranslationResult();

        String input = expression.getEnglishStatement().trim();
        String lowerInput = input.toLowerCase();

        if (lowerInput.contains(" union ")) {

            int index = lowerInput.indexOf(" union ");

            String firstSet = input.substring(0, index).trim();
            String secondSet = input.substring(index + 7).trim();

            result.setTranslatedNotation("A ∪ B");

            result.addLegendEntry("A", firstSet);
            result.addLegendEntry("B", secondSet);

            result.setExplanation(
                    "The statement expresses the union of two sets. "
                            + "The union combines the elements belonging "
                            + "to either set."
            );

        } else if (lowerInput.contains(" intersect ")) {

            int index = lowerInput.indexOf(" intersect ");

            String firstSet = input.substring(0, index).trim();
            String secondSet = input.substring(index + 10).trim();

            result.setTranslatedNotation("A ∩ B");

            result.addLegendEntry("A", firstSet);
            result.addLegendEntry("B", secondSet);

            result.setExplanation(
                    "The statement expresses the intersection of two sets. "
                            + "The intersection contains the elements "
                            + "common to both sets."
            );

        } else if (lowerInput.contains(" subset of ")) {

            int index = lowerInput.indexOf(" subset of ");

            String firstSet = input.substring(0, index).trim();
            String secondSet = input.substring(index + 11).trim();

            result.setTranslatedNotation("A ⊆ B");

            result.addLegendEntry("A", firstSet);
            result.addLegendEntry("B", secondSet);

            result.setExplanation(
                    "The statement expresses a subset relationship. "
                            + "Every element of the first set is also "
                            + "an element of the second set."
            );

        } else if (lowerInput.contains(" element of ")) {

            int index = lowerInput.indexOf(" element of ");

            String element = input.substring(0, index).trim();
            String set = input.substring(index + 12).trim();

            result.setTranslatedNotation("x ∈ A");

            result.addLegendEntry("x", element);
            result.addLegendEntry("A", set);

            result.setExplanation(
                    "The statement expresses set membership. "
                            + "The specified element belongs to the set."
            );

        }

        return result;
    }
}