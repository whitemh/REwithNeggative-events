package com.kosho.uc.chap3;

import com.kosho.uc.chap3.FaRule;
import com.kosho.uc.chap3.nfa.NfaDesign;
import com.kosho.uc.chap3.nfa.NfaRuleBook;
import com.kosho.uc.chap3.utils.Sets;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


public class NfaRuleBookTest {
    @Test
    public void nextState() {
        NfaRuleBook ruleBook = createNfaRuleBook();
        System.out.println(ruleBook.nextStates(Arrays.asList(1), 'b'));
        System.out.println(ruleBook.nextStates(Arrays.asList(1, 2), 'a'));
        System.out.println(ruleBook.nextStates(Arrays.asList(1, 3), 'b'));
    }

    @Test
    public void freeMove() {
        List<FaRule> rules = new ArrayList<>();
        rules.add(new FaRule(1, null, 2));
        rules.add(new FaRule(1, null, 4));
        rules.add(new FaRule(2, 'a', 3));
        rules.add(new FaRule(3, 'a', 2));
        rules.add(new FaRule(4, 'a', 5));
        rules.add(new FaRule(5, 'a', 6));
        rules.add(new FaRule(6, 'a', 4));
        NfaRuleBook ruleBook = new NfaRuleBook(rules);
        Set<Object> integers = ruleBook.nextStates(Sets.create(1), null);
        Assert.assertArrayEquals(integers.toArray(new Object[0]), new Object[]{2, 4});

        NfaDesign nfaDesign = new NfaDesign(1, Sets.create(2, 4), ruleBook);
        Assert.assertTrue(nfaDesign.accepts("aa"));
        Assert.assertTrue(nfaDesign.accepts("aaa"));
        Assert.assertFalse(nfaDesign.accepts("aaaaa"));
        Assert.assertTrue(nfaDesign.accepts("aaaaaa"));
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