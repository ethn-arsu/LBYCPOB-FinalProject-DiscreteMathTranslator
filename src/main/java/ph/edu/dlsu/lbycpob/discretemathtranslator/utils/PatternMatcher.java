package ph.edu.dlsu.lbycpob.discretemathtranslator.utils;

/**
 * Provides helper methods for identifying supported
 * discrete mathematics sentence patterns.
 *
 * <p>This class assists the application in detecting
 * supported logical and mathematical sentence structures.</p>
 */
public class PatternMatcher {

    /**
     * Checks whether the input contains a biconditional pattern.
     *
     * @param input the user's input
     * @return true if a biconditional pattern is detected
     */
    public boolean containsBiconditional(String input) {
        if (input == null) {
            return false;
        }

        String normal = input.trim().toLowerCase();

        return normal.contains(" if and only if ");
    }

    /**
     * Checks whether the input contains a conditional pattern.
     *
     * <p>Biconditional statements are excluded because they
     * are handled separately.</p>
     *
     * @param input the user's input
     * @return true if a supported conditional pattern is detected
     */
    public boolean containsConditional(String input) {
        if (input == null) {
            return false;
        }

        String normal = input.trim().toLowerCase();

        if (containsBiconditional(normal)) {
            return false;
        }

        return (normal.startsWith("if ") && normal.contains(" then "))
                || (normal.startsWith("if ") && normal.contains(","))
                || normal.contains(" implies ");
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

        String normal = input.trim().toLowerCase();

        return normal.contains(" and ");
    }

    /**
     * Checks whether the input contains a disjunction pattern.
     *
     * @param input the user's input
     * @return true if a disjunction pattern is detected
     */
    public boolean containsDisjunction(String input) {
        if (input == null) {
            return false;
        }

        String normal = input.trim().toLowerCase();

        return normal.contains(" or ");
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

        String normal = input.trim().toLowerCase();

        return normal.startsWith("not ")
                || normal.contains(" is not ")
                || normal.contains(" are not ")
                || normal.contains(" does not ")
                || normal.contains(" do not ")
                || normal.contains(" never ");
    }

    /**
     * Checks whether the input contains a quantifier pattern.
     *
     * @param input the user's input
     * @return true if a quantifier pattern is detected
     */
    public boolean containsQuantifier(String input) {
        if (input == null) {
            return false;
        }

        String normal = input.trim().toLowerCase();

        return normal.startsWith("every ")
                || normal.startsWith("all ")
                || normal.startsWith("some ")
                || normal.startsWith("there exists ");
    }

    /**
     * Checks whether the input contains a set theory pattern.
     *
     * @param input the user's input
     * @return true if a set pattern is detected
     */
    public boolean containsSet(String input) {
        if (input == null) {
            return false;
        }

        String normal = input.trim().toLowerCase();

        return normal.contains(" union ")
                || normal.contains(" intersect ")
                || normal.contains(" subset of ")
                || normal.contains(" element of ");
    }
}