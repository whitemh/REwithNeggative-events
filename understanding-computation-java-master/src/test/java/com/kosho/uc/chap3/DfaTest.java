package com.kosho.uc.chap3;

import com.kosho.uc.chap3.FaRule;
import com.kosho.uc.chap3.dfa.DfaDesign;
import com.kosho.uc.chap3.dfa.DfaRuleBook;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DfaTest {
    @Test
    public void testDfa1() {
        DfaRuleBook ruleBook = createDfaRuleBook();
        DfaDesign dfaDesign = new DfaDesign(1, new Object[]{3}, ruleBook);
        Assert.assertTrue(dfaDesign.accepts("baaab"));
    }

    @Test
    public void testDfa2() {
        List<FaRule> rules = new ArrayList<>();
        rules.add(new FaRule(1, 'a', 2));
        rules.add(new FaRule(1, 'b', 1));
        rules.add(new FaRule(2, 'a', 2));
        rules.add(new FaRule(2, 'b', 3));
        rules.add(new FaRule(3, 'a', 3));
        rules.add(new FaRule(3, 'b', 3));
        DfaRuleBook ruleBook = new DfaRuleBook(rules);
        DfaDesign dfaDesign = new DfaDesign(1, new Object[]{3}, ruleBook);
        Assert.assertTrue(dfaDesign.accepts("baaab"));
    }

    private DfaRuleBook createDfaRuleBook() {
        List<FaRule> rules = new ArrayList<>();
        rules.add(new FaRule(1, 'a', 2));
        rules.add(new FaRule(1, 'b', 1));
        rules.add(new FaRule(2, 'a', 2));
        rules.add(new FaRule(2, 'b', 3));
        rules.add(new FaRule(3, 'a', 3));
        rules.add(new FaRule(3, 'b', 3));
        return new DfaRuleBook(rules);
    }
}