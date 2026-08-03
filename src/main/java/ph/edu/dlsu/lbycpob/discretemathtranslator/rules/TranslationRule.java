package ph.edu.dlsu.lbycpob.discretemathtranslator.rules;

import ph.edu.dlsu.lbycpob.discretemathtranslator.model.Expression;
import ph.edu.dlsu.lbycpob.discretemathtranslator.model.TranslationResult;

/**
 * Abstract blueprint for all translation rules.
 *
 * <p>Each rule determines whether it can translate a given
 * English statement and produces the corresponding
 * discrete mathematics notation.</p>
 */
public abstract class TranslationRule {

    /**
     * Determines whether this rule applies to the given expression.
     *
     * @param expression the user's English statement
     * @return true if the rule can translate the statement;
     *         false otherwise
     */
    public abstract boolean matches(Expression expression);

    /**
     * Translates the given expression.
     *
     * @param expression the user's English statement
     * @return the translation result
     */
    public abstract TranslationResult translate(Expression expression);

}