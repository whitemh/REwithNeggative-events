package com.kosho.uc.chap3.regex;

import com.kosho.uc.chap3.FaRule;
import com.kosho.uc.chap3.nfa.NfaDesign;
import com.kosho.uc.chap3.nfa.NfaRuleBook;
import com.kosho.uc.chap3.utils.IdGen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 重复节点
 *
 * @author Kosho
 * @since 2020-04-20
 */
public class Repeat extends Pattern {
    private Pattern pattern;

    public Repeat(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return pattern.bracket(precedence()) + "*";
    }

    @Override
    public int precedence() {
        return 2;
    }

    @Override
    public NfaDesign toNfaDesign() {
        NfaDesign patternNfaDesign = pattern.toNfaDesign();
        Integer startState = IdGen.gen();
        Set<Object> acceptStates = new HashSet<>();
        acceptStates.add(startState);
        acceptStates.addAll(patternNfaDesign.getAcceptStates());

        ArrayList<FaRule> rules = new ArrayList<>(patternNfaDesign.getRuleBook().getRules());

        patternNfaDesign.getAcceptStates().stream()
                .map(acceptState -> new FaRule(acceptState, null, patternNfaDesign.getStartState()))
                .forEach(rules::add);
        rules.add(new FaRule(startState, null, patternNfaDesign.getStartState()));

        NfaRuleBook ruleBook = new NfaRuleBook(rules);
        return new NfaDesign(startState, acceptStates, ruleBook);
    }
}
