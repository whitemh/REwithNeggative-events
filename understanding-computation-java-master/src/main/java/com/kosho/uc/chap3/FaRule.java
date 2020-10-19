package com.kosho.uc.chap3;

/**
 * 自动机规则
 *
 * @author Kosho
 * @since 2020-04-20
 */
public class FaRule {
    private Object state;
    private Character character;
    private Object nextState;

    public FaRule(Object state, Character character, Object nextState) {
        this.state = state;
        this.nextState = nextState;
        this.character = character;
    }

    public boolean appliesTo(Object state, Character character) {
        return this.state.equals(state) && equals(this.character, character);
    }

    public Object follow() {
        return nextState;
    }

    public Character getCharacter() {
        return character;
    }

    @Override
    public String toString() {
        return "FaRule{" + state + "--" + character + "-->" + nextState + '}';
    }

    private boolean equals(Character a, Character b) {
        return (a == null && b == null) || (a != null && a.equals(b));
    }
}
