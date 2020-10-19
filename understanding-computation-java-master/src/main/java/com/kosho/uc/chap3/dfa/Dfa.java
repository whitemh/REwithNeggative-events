package com.kosho.uc.chap3.dfa;

/**
 * DFA
 *
 * @author Kosho
 * @since 2020-04-20
 */
public class Dfa {
    private Object currentState;
    private Object[] acceptStates;
    private DfaRuleBook ruleBook;

    public Dfa(Object currentState, Object[] acceptStates, DfaRuleBook ruleBook) {
        this.currentState = currentState;
        this.acceptStates = acceptStates;
        this.ruleBook = ruleBook;
    }

    public boolean accepting() {
        for (Object acceptState : acceptStates) {
            if (acceptState.equals(currentState)) {
                return true;
            }
        }

        return false;
    }

    public void readCharacter(char character) {
        currentState = ruleBook.nextState(currentState, character);
    }

    public void readString(String str) {
        for (int i = 0; i < str.length(); i++) {
            readCharacter(str.charAt(i));
        }
    }
}
