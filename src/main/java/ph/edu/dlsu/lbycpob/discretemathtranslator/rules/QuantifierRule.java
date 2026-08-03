package ph.edu.dlsu.lbycpob.discretemathtranslator.rules;

import ph.edu.dlsu.lbycpob.discretemathtranslator.model.Expression;
import ph.edu.dlsu.lbycpob.discretemathtranslator.model.TranslationResult;

/**
 * Handles quantified statements.
 *
 * <p>Examples:
 * "Every..."
 * "There exists..."</p>
 */
public class QuantifierRule extends TranslationRule {

    @Override
    public boolean matches(Expression expression) {

        // TODO:
        // Detect quantified statements.

        return false;
    }

    @Override
    public TranslationResult translate(Expression expression) {

        // TODO:
        // Translate quantified statement.

        return null;
    }

}