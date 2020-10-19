package com.kosho.uc.chap3;

import com.kosho.uc.chap3.FaRule;
import com.kosho.uc.chap3.dfa.DfaDesign;
import com.kosho.uc.chap3.nfa.NfaDesign;
import com.kosho.uc.chap3.nfa.NfaRuleBook;
import com.kosho.uc.chap3.nfa.NfaSimulation;
import com.kosho.uc.chap3.regex.Pattern;
import com.kosho.uc.chap3.utils.Pair;
import com.kosho.uc.chap3.utils.Sets;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class NfaSimulationTest {
    @Test
    public void nextState() {
        NfaDesign nfaDesign = createNfaDesign();
        NfaSimulation nfaSimulation = new NfaSimulation(nfaDesign);
        Set<Object> currentStates = nfaDesign.toNfa().getCurrentStates();
        Pair<Set<Set<Object>>, List<FaRule>> statesAndRules = nfaSimulation.discoverStatesAndRules(Sets.create(currentStates));
        System.out.println(statesAndRules);
    }

    @Test
    public void nfa2Dfa() {
        NfaDesign nfaDesign = createNfaDesign();
        NfaSimulation nfaSimulation = new NfaSimulation(nfaDesign);
        DfaDesign dfaDesign = nfaSimulation.toDfaDesign();
        Assert.assertFalse(dfaDesign.accepts("aaa"));
        Assert.assertTrue(dfaDesign.accepts("aab"));
        Assert.assertTrue(dfaDesign.accepts("bbbabb"));
    }

    @Test
    public void regex2Dfa() {
        Pattern pattern = Pattern.compile("a(b|c)*d");
        Assert.assertTrue(pattern.matches("abcbccbccd"));
        Assert.assertFalse(pattern.matches("abcbccca"));

        NfaDesign nfaDesign = pattern.toNfaDesign();
        DfaDesign dfaDesign = new NfaSimulation(nfaDesign).toDfaDesign();
        Assert.assertTrue(dfaDesign.accepts("abcbccbccd"));
        Assert.assertFalse(dfaDesign.accepts("abcbcccae"));
    }

    private NfaDesign createNfaDesign() {
        List<FaRule> rules = new ArrayList<>();
        rules.add(new FaRule(1, 'a', 1));
        rules.add(new FaRule(1, 'a', 2));
        rules.add(new FaRule(1, null, 2));
        rules.add(new FaRule(2, 'b', 3));
        rules.add(new FaRule(3, 'b', 1));
        rules.add(new FaRule(3, null, 2));
        NfaRuleBook ruleBook = new NfaRuleBook(rules);
        return new NfaDesign(1, Sets.create(3), ruleBook);
    }
}