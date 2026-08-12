package ph.edu.dlsu.lbycpob.discretemathtranslator.rules;

import ph.edu.dlsu.lbycpob.discretemathtranslator.model.Expression;
import ph.edu.dlsu.lbycpob.discretemathtranslator.model.TranslationResult;

/**
 * Handles basic quantified statements and translates them into
 * predicate logic notation.
 *
 * <p>Supported quantifier patterns include:
 * <ul>
 *     <li>Every [subject] [predicate]</li>
 *     <li>All [subject] [predicate]</li>
 *     <li>Some [subject] [predicate]</li>
 *     <li>There exists a/an [predicate]</li>
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

        if (lowerInput.startsWith("every ")) {
            return translateUniversal(result, input.substring(6).trim());
        }

        if (lowerInput.startsWith("all ")) {
            return translateUniversal(result, input.substring(4).trim());
        }

        if (lowerInput.startsWith("some ")) {
            return translateExistential(result, input.substring(5).trim());
        }

        if (lowerInput.startsWith("there exists ")) {
            return translateThereExists(
                    result,
                    input.substring(13).trim()
            );
        }

        return result;
    }

    /**
     * Handles universal statements such as:
     * "Every student studies."
     */
    private TranslationResult translateUniversal(
            TranslationResult result,
            String statement) {

        String[] parts = statement.split("\\s+", 2);

        if (parts.length < 2) {
            result.setTranslatedNotation("∀x P(x)");
            result.addLegendEntry("P(x)", statement);

            result.setExplanation(
                    "The statement expresses universal quantification."
            );

            return result;
        }

        String subject = removeTrailingPunctuation(parts[0]);
        String predicate = removeTrailingPunctuation(parts[1]);

        String subjectName = capitalize(subject);
        String predicateName = capitalize(predicate);

        result.setTranslatedNotation(
                "∀x(" + subjectName + "(x) → " + predicateName + "(x))"
        );

        result.addLegendEntry(
                subjectName + "(x)",
                "x is a " + subject
        );

        result.addLegendEntry(
                predicateName + "(x)",
                "x " + predicate
        );

        result.setExplanation(
                "The statement expresses a universal quantification. "
                        + "It states that every member of the specified "
                        + "class has the given property."
        );

        return result;
    }

    /**
     * Handles existential statements beginning with "some".
     */
    private TranslationResult translateExistential(
            TranslationResult result,
            String statement) {

        String[] parts = statement.split("\\s+", 2);

        if (parts.length < 2) {
            result.setTranslatedNotation("∃x P(x)");
            result.addLegendEntry("P(x)", statement);

            result.setExplanation(
                    "The statement expresses existential quantification."
            );

            return result;
        }

        String subject = removeTrailingPunctuation(parts[0]);
        String predicate = removeTrailingPunctuation(parts[1]);

        String subjectName = capitalize(subject);
        String predicateName = capitalize(predicate);

        result.setTranslatedNotation(
                "∃x(" + subjectName + "(x) ∧ " + predicateName + "(x))"
        );

        result.addLegendEntry(
                subjectName + "(x)",
                "x is a " + subject
        );

        result.addLegendEntry(
                predicateName + "(x)",
                "x " + predicate
        );

        result.setExplanation(
                "The statement expresses existential quantification. "
                        + "It states that at least one member of the "
                        + "specified class has the given property."
        );

        return result;
    }

    /**
     * Handles statements such as:
     * "There exists a prime number."
     */
    private TranslationResult translateThereExists(
            TranslationResult result,
            String statement) {

        String cleaned = removeTrailingPunctuation(statement);

        if (cleaned.startsWith("a ")) {
            cleaned = cleaned.substring(2).trim();
        } else if (cleaned.startsWith("an ")) {
            cleaned = cleaned.substring(3).trim();
        }

        String predicateName = capitalize(cleaned);

        result.setTranslatedNotation(
                "∃x " + predicateName + "(x)"
        );

        result.addLegendEntry(
                predicateName + "(x)",
                "x is a " + cleaned
        );

        result.setExplanation(
                "The statement expresses existential quantification. "
                        + "It states that at least one element has "
                        + "the specified property."
        );

        return result;
    }

    /**
     * Removes punctuation from the end of a statement.
     */
    private String removeTrailingPunctuation(String text) {
        return text.replaceAll("[.,!?]+$", "").trim();
    }

    /**
     * Capitalizes the first letter of a predicate name.
     */
    private String capitalize(String text) {
        if (text.isEmpty()) {
            return text;
        }

        return Character.toUpperCase(text.charAt(0))
                + text.substring(1);
    }
}