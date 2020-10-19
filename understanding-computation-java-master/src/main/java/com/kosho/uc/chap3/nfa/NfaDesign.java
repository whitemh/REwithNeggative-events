package com.kosho.uc.chap3.nfa;

import com.kosho.uc.chap3.utils.Sets;

import java.util.Set;

/**
 * NFA设计器
 *
 * @author Kosho
 * @since 2020-04-20
 */
public class NfaDesign {
    private Object startState;
    private Set<Object> acceptStates;
    private NfaRuleBook ruleBook;

    public NfaDesign(Object startState, Set<Object> acceptStates, NfaRuleBook ruleBook) {
        this.startState = startState;
        this.acceptStates = acceptStates;
        this.ruleBook = ruleBook;
    }

    public Nfa toNfa() {
        return new Nfa(Sets.create(startState), acceptStates, ruleBook);
    }

    public Nfa toNfa(Set<Object> currentStates) {
        return new Nfa(currentStates, acceptStates, ruleBook);
    }

    public boolean accepts(String str) {
        Nfa nfa = toNfa();
        nfa.readString(str);
        return nfa.accepting();
    }

    public Object getStartState() {
        return startState;
    }

    public Set<Object> getAcceptStates() {
        return acceptStates;
    }

    public NfaRuleBook getRuleBook() {
        return ruleBook;
    }
}
