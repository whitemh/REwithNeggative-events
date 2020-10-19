package com.kosho.uc.chap3.regex.parser;

/**
 * 正则表达式Token
 *
 * @author Kosho
 * @since 2020-04-20
 */
public class Token {
    public static final Token REPEAT = new Token(TokenType.REPEAT, null);

    public static final Token CHOOSE = new Token(TokenType.CHOOSE, null);

    public static final Token LPAR = new Token(TokenType.LPAR, null);

    public static final Token RPAR = new Token(TokenType.RPAR, null);

    public enum TokenType {
        CHAR, LPAR, RPAR, CHOOSE, REPEAT, NEGATIVE
    }

    public TokenType tokenType;

    public Object value;

    public Token(TokenType tokenType, Object value) {
        this.tokenType = tokenType;
        this.value = value;
    }
}
