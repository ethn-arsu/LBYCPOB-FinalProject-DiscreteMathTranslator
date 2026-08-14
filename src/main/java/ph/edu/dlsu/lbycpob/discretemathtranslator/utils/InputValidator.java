package ph.edu.dlsu.lbycpob.discretemathtranslator.utils;

/**
 * Provides utility methods for validating user input.
 *
 * <p>This class checks whether an input statement
 * is suitable for processing by the translation engine.</p>
 */
public class InputValidator {

    /**
     * Determines whether the given input is valid.
     *
     * @param input the user's input
     * @return true if the input is valid; false otherwise
     */
    public boolean isValid(String input) {
        return !isEmpty(input);
    }

    /**
     * Determines whether the given input is empty.
     *
     * @param input the user's input
     * @return true if empty; false otherwise
     */
    public boolean isEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }
}