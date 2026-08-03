package ph.edu.dlsu.lbycpob.discretemathtranslator.model;

/**
 * Represents an English statement entered by the user.
 *
 * <p>This class stores the original input that will later
 * be analyzed and translated into discrete mathematics notation.</p>
 */
public class Expression {

    /**
     * The English statement entered by the user.
     */
    private String englishStatement;

    /**
     * Default constructor.
     */
    public Expression() {

    }

    /**
     * Creates an Expression object with the given statement.
     *
     * @param englishStatement the user's input
     */
    public Expression(String englishStatement) {
        this.englishStatement = englishStatement;
    }

    // TODO:
    // Add getters and setters if needed.

}