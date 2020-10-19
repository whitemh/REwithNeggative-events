package com.kosho.uc.chap3.nfa;

import com.kosho.uc.chap3.FaRule;

import java.util.*;
import java.util.stream.Collectors;

/**
 * NFA规则表
 *
 * @author Kosho
 * @since 2020-04-20
 */
public class NfaRuleBook {
    private List<FaRule> rules;

    public NfaRuleBook(List<FaRule> rules) {
        this.rules = rules;
    }

    public Set<Object> nextStates(Collection<Object> states, Character character) {
        return states.stream()
                .map(state -> followRulesFor(state, character))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    public Set<Object> followRulesFor(Object state, Character character) {
        return rulesFor(state, character).stream().map(FaRule::follow).collect(Collectors.toSet());
    }

    public Set<FaRule> rulesFor(Object state, Character character) {
        return rules.stream()
                .filter(faRule -> faRule.appliesTo(state, character))
                .collect(Collectors.toSet());
    }

    public Set<Object> followFreeMoves(Collection<Object> states) {
        Set<Object> moreStates = nextStates(states, null);
        if (states.containsAll(moreStates)) {
            return new HashSet<>(states);
        } else {
            states.addAll(moreStates);
            return followFreeMoves(states);
        }
    }

    public List<Character> alphabet() {
        return rules.stream()
                .map(FaRule::getCharacter)
                .distinct()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<FaRule> getRules() {
        return rules;
    }
}
