package com.kosho.uc.chap3.dfa;

/**
 * DFA设计器
 *
 * @author Kosho
 * @since 2020-04-20
 */
public class DfaDesign {
    private Object startState;
    private Object[] acceptStates;
    private DfaRuleBook ruleBook;

    public DfaDesign(Object startState, Object[] acceptStates, DfaRuleBook ruleBook) {
        this.startState = startState;
        this.acceptStates = acceptStates;
        this.ruleBook = ruleBook;
    }

    public Dfa toDfa() {
        return new Dfa(startState, acceptStates, ruleBook);
    }

    public boolean accepts(String str) {
        Dfa dfa = toDfa();
        dfa.readString(str);
        return dfa.accepting();
    }
}
