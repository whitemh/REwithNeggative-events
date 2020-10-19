package com.kosho.uc.chap3.regex;

import com.kosho.uc.chap3.FaRule;
import com.kosho.uc.chap3.nfa.NfaDesign;
import com.kosho.uc.chap3.nfa.NfaRuleBook;
import com.kosho.uc.chap3.utils.IdGen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 选择节点
 *
 * @author Kosho
 * @since 2020-04-20
 */
public class Choose extends Pattern {
    private Pattern first;
    private Pattern second;

    public Choose(Pattern first, Pattern second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return first.bracket(precedence()) + "|" + second.bracket(precedence());
    }

    @Override
    public int precedence() {
        return 0;
    }

    @Override
    public NfaDesign toNfaDesign() {
        NfaDesign firstNfaDesign = first.toNfaDesign();
        NfaDesign secondNfaDesign = second.toNfaDesign();
        Integer startState = IdGen.gen();
        Set<Object> acceptStates = new HashSet<>();
        acceptStates.addAll(firstNfaDesign.getAcceptStates());
        acceptStates.addAll(secondNfaDesign.getAcceptStates());

        List<FaRule> rules = new ArrayList<>();
        rules.addAll(firstNfaDesign.getRuleBook().getRules());
        rules.addAll(secondNfaDesign.getRuleBook().getRules());
        // extra rule
        rules.add(new FaRule(startState, null, firstNfaDesign.getStartState()));
        rules.add(new FaRule(startState, null, secondNfaDesign.getStartState()));

        NfaRuleBook ruleBook = new NfaRuleBook(rules);
        return new NfaDesign(startState, acceptStates, ruleBook);
    }
}
