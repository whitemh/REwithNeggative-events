package com.kosho.uc.chap3.regex;

import com.kosho.uc.chap3.nfa.NfaDesign;
import com.kosho.uc.chap3.regex.parser.RegexParser;

/**
 * 抽象语法树节点
 *
 * @author Kosho
 * @since 2020-04-20
 */
public abstract class Pattern {
    public abstract int precedence();

    public abstract NfaDesign toNfaDesign();

    public String bracket(int outerPrecedence) {
        if (precedence() < outerPrecedence) {
            return "(" + toString() + ")";
        }

        return toString();
    }

    public String inspect() {
        return "/" + this + "/";
    }

    public boolean matches(String str) {
        return toNfaDesign().accepts(str);
    }

    public static Pattern compile(String regex) {
        return new RegexParser(regex).parse();
    }
}
