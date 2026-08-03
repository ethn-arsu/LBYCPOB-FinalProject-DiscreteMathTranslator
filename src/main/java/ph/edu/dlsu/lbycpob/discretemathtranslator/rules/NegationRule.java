package ph.edu.dlsu.lbycpob.discretemathtranslator.rules;

import ph.edu.dlsu.lbycpob.discretemathtranslator.model.Expression;
import ph.edu.dlsu.lbycpob.discretemathtranslator.model.TranslationResult;

/**
 * Handles negation statements.
 *
 * <p>Example:
 * "Not P"</p>
 */
public class NegationRule extends TranslationRule {

    @Override
    public boolean matches(Expression expression) {

        // TODO:
        // Detect negation statements.

        return false;
    }

    @Override
    public TranslationResult translate(Expression expression) {

        // TODO:
        // Translate negation statement.

        return null;
    }

}