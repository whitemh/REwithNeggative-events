package com.kosho.uc.chap3.nfa;

import java.util.Set;

/**
 * NFA
 *
 * @author Kosho
 * @since 2020-04-20
 */
public class Nfa {
    private Set<Object> currentStates;
    private Set<Object> acceptStates;
    private NfaRuleBook ruleBook;

    public Nfa(Set<Object> currentStates, Set<Object> acceptStates, NfaRuleBook ruleBook) {
        this.currentStates = currentStates;
        this.acceptStates = acceptStates;
        this.ruleBook = ruleBook;
    }

    public boolean accepting() {
        for (Object currentState : getCurrentStates()) {
            if (acceptStates.contains(currentState)) {
                return true;
            }
        }

        return false;
    }

    public void readCharacter(Character character) {
        currentStates = ruleBook.nextStates(getCurrentStates(), character);
    }

    public void readString(String str) {
        for (int i = 0; i < str.length(); i++) {
            readCharacter(str.charAt(i));
        }
    }

    public Set<Object> getCurrentStates() {
        return ruleBook.followFreeMoves(currentStates);
    }
}
