package ph.edu.dlsu.lbycpob.discretemathtranslator.rules;

import ph.edu.dlsu.lbycpob.discretemathtranslator.model.Expression;
import ph.edu.dlsu.lbycpob.discretemathtranslator.model.TranslationResult;

/**
 * Handles conjunction statements.
 *
 * <p>Example:
 * "P and Q"</p>
 */
public class ConjunctionRule extends TranslationRule {

    @Override
    public boolean matches(Expression expression) {

        // TODO:
        // Detect conjunction statements.

        return false;
    }

    @Override
    public TranslationResult translate(Expression expression) {

        // TODO:
        // Translate conjunction statement.

        return null;
    }

}