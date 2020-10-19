package com.kosho.uc.chap3;

import com.kosho.uc.chap3.FaRule;
import com.kosho.uc.chap3.dfa.DfaRuleBook;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DfaRuleBookTest {
    @Test
    public void nextState() {
        List<FaRule> rules = new ArrayList<>();
        rules.add(new FaRule(1, 'a', 2));
        rules.add(new FaRule(1, 'b', 1));
        rules.add(new FaRule(2, 'a', 2));
        rules.add(new FaRule(2, 'b', 3));
        rules.add(new FaRule(3, 'a', 3));
        rules.add(new FaRule(3, 'b', 3));
        DfaRuleBook ruleBook = new DfaRuleBook(rules);

        Assert.assertEquals(ruleBook.nextState(1, 'a'), 2);
        Assert.assertEquals(ruleBook.nextState(1, 'b'), 1);
        Assert.assertEquals(ruleBook.nextState(2, 'b'), 3);
    }
}