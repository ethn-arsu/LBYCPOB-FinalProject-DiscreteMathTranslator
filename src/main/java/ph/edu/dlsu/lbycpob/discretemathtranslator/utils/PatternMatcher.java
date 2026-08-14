package ph.edu.dlsu.lbycpob.discretemathtranslator.utils;

/**
 * Provides helper methods for identifying supported
 * discrete mathematics sentence patterns.
 *
 * <p>This class assists the translation engine by
 * detecting keywords and logical connectors
 * within the user's input.</p>
 */
public class PatternMatcher {

    /**
     * Checks whether the input contains a conditional pattern.
     *
     * @param input the user's input
     * @return true if a biconditional pattern is detected
     */
    public boolean containsBiconditional(String input) {

        if (input == null) {
            return false;
        }
        String normal = input.toLowerCase();

        return normal.contains(" if and only if ");
    }

    /**
     * Checks whether the input contains a conditional pattern.
     *
     * @param input the user's input
     * @return true if a conditional pattern is detected
     */
    public boolean containsConditional(String input) {

        if (input == null) {
            return false;
        }
        String normal = input.toLowerCase();

        // Biconditional, handled by containsBiconditional
        if (normal.contains(" if and only if ")){
            return false;
        }

        return (normal.startsWith("if ") && normal.contains(" then ")) ||
                normal.contains(" if ") || normal.contains(" whenever ") ||
                normal.contains(" once ");
    }

    /**
     * Checks whether the input contains a conjunction pattern.
     *
     * @param input the user's input
     * @return true if a conjunction pattern is detected
     */
    public boolean containsConjunction(String input) {

        if (input == null) {
            return false;
        }
        String normal = input.toLowerCase();

        return normal.contains(" and ") ||
                normal.contains("as well as") ||
                normal.contains("but");
    }

    /**
     * Checks whether the input contains a negation pattern.
     *
     * @param input the user's input
     * @return true if a negation pattern is detected
     */
    public boolean containsNegation(String input) {

        if (input == null) {
            return false;
        }
        String normal = input.toLowerCase();

        return normal.contains("not") || normal.contains("is not") ||
                normal.contains("are not") || normal.contains("does not") ||
                normal.contains("do not") || normal.contains("never");

    }
}