package com.kosho.uc.chap3.regex;

import com.kosho.uc.chap3.FaRule;
import com.kosho.uc.chap3.nfa.NfaDesign;
import com.kosho.uc.chap3.nfa.NfaRuleBook;
import com.kosho.uc.chap3.utils.IdGen;
import com.kosho.uc.chap3.utils.Sets;

import java.util.Collections;

/**
 * 字符节点
 *
 * @author Kosho
 * @since 2020-04-20
 */
public class Literal extends Pattern {
    private Character character;

    public Literal(Character character) {
        this.character = character;
    }

    @Override
    public int precedence() {
        return 3;
    }

    @Override
    public NfaDesign toNfaDesign() {
        Integer start = IdGen.gen();
        Integer accept = IdGen.gen();
        FaRule rule = new FaRule(start, character, accept);
        NfaRuleBook ruleBook = new NfaRuleBook(Collections.singletonList(rule));
        return new NfaDesign(start, Sets.create(accept), ruleBook);
    }

    @Override
    public String toString() {
        return String.valueOf(character);
    }
}
