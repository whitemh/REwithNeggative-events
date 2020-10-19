package com.kosho.uc.chap3.dfa;

import com.kosho.uc.chap3.FaRule;

import java.util.List;
import java.util.Optional;

/**
 * DFA规则表
 *
 * @author Kosho
 * @since 2020-04-20
 */
public class DfaRuleBook {
    private List<FaRule> rules;

    public DfaRuleBook(List<FaRule> rules) {
        this.rules = rules;
    }

    public Object nextState(Object state, char character) {
        return ruleFor(state, character)
                .map(FaRule::follow)
                .orElse(null);
    }

    public Optional<FaRule> ruleFor(Object state, char character) {
        return rules.stream()
                .filter(faRule -> faRule.appliesTo(state, character))
                .findFirst();
    }
}
