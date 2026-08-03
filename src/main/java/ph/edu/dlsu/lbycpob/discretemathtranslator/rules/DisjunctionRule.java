package ph.edu.dlsu.lbycpob.discretemathtranslator.rules;

import ph.edu.dlsu.lbycpob.discretemathtranslator.model.Expression;
import ph.edu.dlsu.lbycpob.discretemathtranslator.model.TranslationResult;

/**
 * Handles disjunction statements.
 *
 * <p>Example:
 * "P or Q"</p>
 */
public class DisjunctionRule extends TranslationRule {

    @Override
    public boolean matches(Expression expression) {

        // TODO:
        // Detect disjunction statements.

        return false;
    }

    @Override
    public TranslationResult translate(Expression expression) {

        // TODO:
        // Translate disjunction statement.

        return null;
    }

}