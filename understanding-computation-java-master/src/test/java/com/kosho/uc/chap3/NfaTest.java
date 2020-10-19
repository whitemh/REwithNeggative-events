package com.kosho.uc.chap3;

import com.kosho.uc.chap3.FaRule;
import com.kosho.uc.chap3.nfa.NfaDesign;
import com.kosho.uc.chap3.nfa.NfaRuleBook;
import com.kosho.uc.chap3.utils.Sets;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NfaTest {
    @Test
    public void read1() {
        NfaRuleBook ruleBook = createNfaRuleBook();
        NfaDesign nfaDesign = new NfaDesign(1, Sets.create(4), ruleBook);
        System.out.println(nfaDesign.accepts("bab"));
        System.out.println(nfaDesign.accepts("bbbbb"));
        System.out.println(nfaDesign.accepts("bbabb"));
        System.out.println(nfaDesign.accepts("bbabbw"));
    }

    private NfaRuleBook createNfaRuleBook() {
        List<FaRule> rules = new ArrayList<>();
        rules.add(new FaRule(1, 'a', 1));
        rules.add(new FaRule(1, 'b', 1));
        rules.add(new FaRule(1, 'b', 2));
        rules.add(new FaRule(2, 'a', 3));
        rules.add(new FaRule(2, 'b', 3));
        rules.add(new FaRule(3, 'a', 4));
        rules.add(new FaRule(3, 'b', 4));
        return new NfaRuleBook(rules);
    }
}