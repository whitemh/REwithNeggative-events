package com.kosho.uc.chap3.regex.parser;

import java.util.NoSuchElementException;

/**
 * 正则表达式词法解析器
 *
 * @author Kosho
 * @since 2020-04-20
 */
public class Tokenizer {
    private String regex;

    private Token cur = null;

    private int index = 0;

    public Tokenizer(String regex) {
        this.regex = regex;
        if (regex != null && regex.length() > 0) {
            cur = toToken(regex.charAt(0));
        }
    }

    public Token getToken() {
        return cur;
    }

    public void consumeToken() {
        index++;
        if (index < regex.length()) {
            cur = toToken(regex.charAt(index));
        } else if (index == regex.length()) {
            cur = null;
        } else {
            throw new NoSuchElementException();
        }
    }

    private Token toToken(char ch) {
        switch (ch) {
            case '*':
                return Token.REPEAT;

            case '|':
                return Token.CHOOSE;

            case '(':
                return Token.LPAR;

            case ')':
                return Token.RPAR;

            case '-':
                return Token.NEGATIVE;

            default:
                return new Token(Token.TokenType.CHAR, ch);
        }
    }
}
