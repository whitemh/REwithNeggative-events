package com.kosho.uc.chap3.regex.parser;

import com.kosho.uc.chap3.regex.*;

import static com.kosho.uc.chap3.regex.parser.Token.TokenType.*;

/**
 * 正则表达式语法解析器
 *
 * @author Kosho
 * @since 2020-04-20
 */
public class RegexParser {
    private final Tokenizer tokenizer;

    public RegexParser(String regex) {
        this.tokenizer = new Tokenizer(regex);
    }

    public Pattern parse() {
        return choose();
    }

    private Pattern choose() {
        Pattern left = concatOrEmpty();
        Token cur = tokenizer.getToken();
        if (cur != null && cur.tokenType == CHOOSE) {
            tokenizer.consumeToken();
            return new Choose(left, choose());
        }

        return left;
    }

    private Pattern concatOrEmpty() {
        Token cur = tokenizer.getToken();
        if (cur == null) {
            return new Empty();
        }

        Pattern concat = concat();
        if (concat == null) {
            return new Empty();
        }

        return concat;
    }

    private Pattern concat() {
        Pattern left = repeat();
        if (left == null) {
            return null;
        }

        Token cur = tokenizer.getToken();
        if (cur == null) {
            return left;
        }

        Pattern concat = concat();
        if (concat != null) {
            return new Concat(left, concat);
        }

        return left;
    }

    private Pattern repeat() {
        Pattern left = brackets();
        Token cur = tokenizer.getToken();
        if (cur != null && cur.tokenType == REPEAT) {
            tokenizer.consumeToken();
            return new Repeat(left);
        }
        return left;
    }

    private Pattern brackets() {
        Token cur = tokenizer.getToken();
        if (cur.tokenType == LPAR) {
            tokenizer.consumeToken();
            Pattern choose = choose();
            match(tokenizer.getToken(), RPAR);
            return choose;
        }

        return literal();
    }

    private Pattern literal() {
        Token cur = tokenizer.getToken();
        if (cur.tokenType == CHAR) {
            tokenizer.consumeToken();
            return new Literal((Character) cur.value);
        }

        return null;
    }

    private void match(Token token, Token.TokenType tokenType) {
        if (token.tokenType != tokenType) {
            throw new IllegalStateException();
        }

        tokenizer.consumeToken();
    }
}
