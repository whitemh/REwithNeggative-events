package com.kosho.uc.chap3.nfa;

import com.kosho.uc.chap3.FaRule;
import com.kosho.uc.chap3.dfa.DfaDesign;
import com.kosho.uc.chap3.dfa.DfaRuleBook;
import com.kosho.uc.chap3.utils.Pair;
import com.kosho.uc.chap3.utils.Sets;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * NFA模拟器
 *
 * @author Kosho
 * @since 2020-04-20
 */
public class NfaSimulation {
    private NfaDesign nfaDesign;

    public NfaSimulation(NfaDesign nfaDesign) {
        this.nfaDesign = nfaDesign;
    }

    public Set<Object> nextState(Set<Object> state, char character) {
        Nfa nfa = nfaDesign.toNfa(state);
        nfa.readCharacter(character);
        return nfa.getCurrentStates();
    }

    public List<FaRule> rulesFor(Set<Object> state) {
        return nfaDesign.getRuleBook().alphabet().stream()
                .map(ch -> new FaRule(state, ch, nextState(state, ch))).collect(Collectors.toList());
    }

    public Pair<Set<Set<Object>>, List<FaRule>> discoverStatesAndRules(Set<Set<Object>> states) {
        List<FaRule> rules = states.stream()
                .map(this::rulesFor)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        Set<Object> moreStates = rules.stream().map(FaRule::follow).collect(Collectors.toSet());
        if (states.containsAll(moreStates)) {
            return new Pair<>(states, rules);
        } else {
            Set<Set<Object>> newStates = new HashSet<>(states);
            moreStates.forEach(state -> newStates.add((Set<Object>) state));
            return discoverStatesAndRules(newStates);
        }
    }

    public DfaDesign toDfaDesign() {
        Set<Object> startStates = nfaDesign.toNfa().getCurrentStates();
        Pair<Set<Set<Object>>, List<FaRule>> statesAndRules = discoverStatesAndRules(Sets.create(startStates));
        Set<Set<Object>> states = statesAndRules.getLeft();
        List<FaRule> rules = statesAndRules.getRight();
        Set<Set<Object>> acceptStates = states.stream().filter(state -> nfaDesign.toNfa(state).accepting()).collect(Collectors.toSet());
        return new DfaDesign(startStates, acceptStates.toArray(), new DfaRuleBook(rules));
    }
}
