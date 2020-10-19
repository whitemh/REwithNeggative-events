package com.kosho.uc.chap3.regex;

import com.kosho.uc.chap3.FaRule;
import com.kosho.uc.chap3.nfa.NfaDesign;
import com.kosho.uc.chap3.nfa.NfaRuleBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 连接节点
 *
 * @author Kosho
 * @since 2020-04-20
 */
public class Concat extends Pattern {
    private Pattern first;
    private Pattern second;

    public Concat(Pattern first, Pattern second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return first.bracket(precedence()) + second.bracket(precedence());
    }

    @Override
    public int precedence() {
        return 1;
    }

    @Override
    public NfaDesign toNfaDesign() {
        NfaDesign firstNfaDesign = first.toNfaDesign();
        NfaDesign secondNfaDesign = second.toNfaDesign();
        Object startState = firstNfaDesign.getStartState();
        Set<Object> acceptStates = secondNfaDesign.getAcceptStates();

        List<FaRule> rules = new ArrayList<>();
        rules.addAll(firstNfaDesign.getRuleBook().getRules());
        rules.addAll(secondNfaDesign.getRuleBook().getRules());
        List<FaRule> extraRules = firstNfaDesign.getAcceptStates().stream()
                .map(state -> new FaRule(state, null, secondNfaDesign.getStartState()))
                .collect(Collectors.toList());
        rules.addAll(extraRules);

        NfaRuleBook ruleBook = new NfaRuleBook(rules);

        return new NfaDesign(startState, acceptStates, ruleBook);
    }
}
