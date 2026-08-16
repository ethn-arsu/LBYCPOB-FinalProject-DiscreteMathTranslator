package ph.edu.dlsu.lbycpob.discretemathtranslator.rules;

import ph.edu.dlsu.lbycpob.discretemathtranslator.model.Expression;
import ph.edu.dlsu.lbycpob.discretemathtranslator.model.TranslationResult;

/**
 * Handles basic quantified statements and translates them into
 * predicate logic notation.
 *
 * <p>Supported patterns include:
 * <ul>
 *     <li>Every [subject] [predicate]</li>
 *     <li>All [subject] [predicate]</li>
 *     <li>Some [subject] [predicate]</li>
 *     <li>There exists a/an [predicate]</li>
 * </ul>
 *
 * <p>Examples:
 * <ul>
 *     <li>"Every student studies." → ∀x(Student(x) → Studies(x))</li>
 *     <li>"There exists a prime number." → ∃x PrimeNumber(x)</li>
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
        if (expression == null
                || expression.getEnglishStatement() == null) {
            return false;
        }

        String input = expression.getEnglishStatement()
                .trim()
                .toLowerCase();

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

    // This is very involved due to predicate logic
    @Override
    public TranslationResult translate(Expression expression) {
        TranslationResult result = new TranslationResult();

        if (!matches(expression)) {
            return result;
        }

        String input = expression.getEnglishStatement().trim();
        String lowerInput = input.toLowerCase();

        if (lowerInput.startsWith("every ")) {
            return translateUniversal(
                    result,
                    input.substring(6).trim()
            );
        }

        if (lowerInput.startsWith("all ")) {
            return translateUniversal(
                    result,
                    input.substring(4).trim()
            );
        }

        if (lowerInput.startsWith("some ")) {
            return translateExistential(
                    result,
                    input.substring(5).trim()
            );
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
     * Translates a universal statement such as:
     * "Every student studies."
     */
    private TranslationResult translateUniversal(
            TranslationResult result,
            String statement) {

        String cleanedStatement = removeTrailingPunctuation(statement);

        String[] parts = cleanedStatement.split("\\s+", 2);

        if (parts.length < 2) {
            return result;
        }

        String subject = parts[0];
        String predicate = parts[1];

        String subjectName = toPredicateName(subject);
        String predicateName = toPredicateName(predicate);

        result.setTranslatedNotation(
                "∀x(" + subjectName + "(x) → "
                        + predicateName + "(x))"
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
                "The statement expresses universal quantification. "
                        + "It states that every " + subject
                        + " has the specified property."
        );

        return result;
    }

    /**
     * Translates an existential statement such as:
     * "Some student studies."
     */
    private TranslationResult translateExistential(
            TranslationResult result,
            String statement) {

        String cleanedStatement = removeTrailingPunctuation(statement);

        String[] parts = cleanedStatement.split("\\s+", 2);

        if (parts.length < 2) {
            return result;
        }

        String subject = parts[0];
        String predicate = parts[1];

        String subjectName = toPredicateName(subject);
        String predicateName = toPredicateName(predicate);

        result.setTranslatedNotation(
                "∃x(" + subjectName + "(x) ∧ "
                        + predicateName + "(x))"
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
                        + "It states that at least one " + subject
                        + " has the specified property."
        );

        return result;
    }

    /**
     * Translates a statement beginning with:
     * "There exists a/an ..."
     */
    private TranslationResult translateThereExists(
            TranslationResult result,
            String statement) {

        String cleaned = removeTrailingPunctuation(statement);

        if (cleaned.toLowerCase().startsWith("a ")) {
            cleaned = cleaned.substring(2).trim();
        } else if (cleaned.toLowerCase().startsWith("an ")) {
            cleaned = cleaned.substring(3).trim();
        }

        String predicateName = toPredicateName(cleaned);

        result.setTranslatedNotation(
                "∃x " + predicateName + "(x)"
        );

        result.addLegendEntry(
                predicateName + "(x)",
                "x is a " + cleaned
        );

        result.setExplanation(
                "The statement expresses existential quantification. "
                        + "It states that at least one element satisfies "
                        + "the specified property."
        );

        return result;
    }

    /**
     * Converts a phrase into a simple predicate-style name.
     *
     * Example:
     * "student" → "Student"
     * "studies" → "Studies"
     * "prime number" → "PrimeNumber"
     */
    private String toPredicateName(String text) {
        String[] words = text.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }

            result.append(
                    Character.toUpperCase(word.charAt(0))
            );

            if (word.length() > 1) {
                result.append(word.substring(1));
            }
        }

        return result.toString();
    }

    /**
     * Removes punctuation from the end of a statement.
     */
    private String removeTrailingPunctuation(String text) {
        return text.replaceAll("[.,!?]+$", "").trim();
    }
}