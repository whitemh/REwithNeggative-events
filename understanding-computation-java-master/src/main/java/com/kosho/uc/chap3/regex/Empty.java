package com.kosho.uc.chap3.regex;

import com.kosho.uc.chap3.nfa.NfaDesign;
import com.kosho.uc.chap3.nfa.NfaRuleBook;
import com.kosho.uc.chap3.utils.IdGen;
import com.kosho.uc.chap3.utils.Sets;

import java.util.Collections;

/**
 * 空字符节点
 *
 * @author Kosho
 * @since 2020-04-20
 */
public class Empty extends Pattern {
    @Override
    public int precedence() {
        return 3;
    }

    @Override
    public NfaDesign toNfaDesign() {
        Integer start = IdGen.gen();
        NfaRuleBook ruleBook = new NfaRuleBook(Collections.emptyList());
        return new NfaDesign(start, Sets.create(start), ruleBook);
    }

    @Override
    public String toString() {
        return "";
    }
}
