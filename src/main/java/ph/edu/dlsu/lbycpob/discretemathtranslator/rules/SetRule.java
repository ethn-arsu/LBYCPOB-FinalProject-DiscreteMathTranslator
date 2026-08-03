package ph.edu.dlsu.lbycpob.discretemathtranslator.rules;

import ph.edu.dlsu.lbycpob.discretemathtranslator.model.Expression;
import ph.edu.dlsu.lbycpob.discretemathtranslator.model.TranslationResult;

/**
 * Handles basic set theory statements.
 *
 * <p>Examples:
 * Union
 * Intersection
 * Subset</p>
 */
public class SetRule extends TranslationRule {

    @Override
    public boolean matches(Expression expression) {

        // TODO:
        // Detect set theory statements.

        return false;
    }

    @Override
    public TranslationResult translate(Expression expression) {

        // TODO:
        // Translate set theory statement.

        return null;
    }

}