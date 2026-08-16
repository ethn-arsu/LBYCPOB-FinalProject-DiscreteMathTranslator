package ph.edu.dlsu.lbycpob.discretemathtranslator.engine;

import ph.edu.dlsu.lbycpob.discretemathtranslator.model.Expression;
import ph.edu.dlsu.lbycpob.discretemathtranslator.model.TranslationResult;
import ph.edu.dlsu.lbycpob.discretemathtranslator.rules.BiconditionalRule;
import ph.edu.dlsu.lbycpob.discretemathtranslator.rules.ConditionalRule;
import ph.edu.dlsu.lbycpob.discretemathtranslator.rules.ConjunctionRule;
import ph.edu.dlsu.lbycpob.discretemathtranslator.rules.DisjunctionRule;
import ph.edu.dlsu.lbycpob.discretemathtranslator.rules.NegationRule;
import ph.edu.dlsu.lbycpob.discretemathtranslator.rules.QuantifierRule;
import ph.edu.dlsu.lbycpob.discretemathtranslator.rules.SetRule;
import ph.edu.dlsu.lbycpob.discretemathtranslator.rules.TranslationRule;

import java.util.ArrayList;
import java.util.List;

/**
 * Coordinates the translation process.
 *
 * <p>The TranslatorEngine receives an English statement,
 * determines the appropriate translation rule,
 * and returns the translated mathematical notation.</p>
 */
public class TranslatorEngine {

    /**
     * The set of translation rules this engine can apply,
     * checked in order until a matching rule is found.
     *
     * <p>Order matters: more structurally specific rules
     * (biconditional, conditional, quantifier, set) are checked
     * before broad substring-based connector rules (conjunction,
     * disjunction), since statements using those structures can
     * also incidentally contain words like "and" or "or".</p>
     */
    private final List<TranslationRule> rules;

    /**
     * Initializes the engine with all available translation rules,
     * ordered from most specific to most general.
     */
    public TranslatorEngine() {
        this.rules = new ArrayList<>();
        rules.add(new BiconditionalRule()); // must precede ConditionalRule
        rules.add(new ConditionalRule());
        rules.add(new QuantifierRule());    // structural — check before generic connectors
        rules.add(new SetRule());           // structural — check before generic connectors
        rules.add(new NegationRule());
        rules.add(new ConjunctionRule());   // broad substring match — check last
        rules.add(new DisjunctionRule());   // broad substring match — check last
    }

    /**
     * Translates an English expression into discrete mathematics notation.
     *
     * @param expression the user's English statement
     * @return the translation result
     */
    public TranslationResult translate(Expression expression) {

        for (TranslationRule rule : rules) {
            if (rule.matches(expression)) {
                return rule.translate(expression);
            }
        }

        // No rule matched — return a result indicating failure to translate.
        TranslationResult result = new TranslationResult();
        result.setTranslatedNotation(null);
        result.setExplanation("No matching translation rule found for: \""
                + expression.getEnglishStatement() + "\"");
        return result;
    }

}