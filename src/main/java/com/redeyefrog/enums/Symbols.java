package com.redeyefrog.enums;

/**
 * 符號
 */
public enum Symbols {

    /** 和 */
    AMPERSAND("&"),

    /** 單引號 */
    APOSTROPHE("'"),

    /** 小老鼠 */
    AT_SIGN("@"),

    /** 右小括弧 */
    CLOSE_PARENTHESIS(")"),

    /** 右中括弧 */
    CLOSE_BRACKET("]"),

    /** 右大括弧 */
    CLOSE_BRACE("}"),

    /** 冒號 */
    COLON(":"),

    /** 逗號 */
    COMMA(","),

    /** 連字號 */
    DASH("-"),

    /** 省略號 */
    ELLIPSIS("..."),

    /** 驚嘆號 */
    EXCLAMATION_MARK("!"),

    /** 等號 */
    EQUAL("="),

    /** 全型逗號 */
    FULL_COMMA("，"),

    /** 全型句號 */
    FULL_PERIOD("。"),

    /** 井字號 */
    NUMBER_SIGN("#"),

    /** 左小括弧 */
    OPEN_PARENTHESIS("("),

    /** 左中括弧 */
    OPEN_BRACKET("["),

    /** 左大括弧 */
    OPEN_BRACE("{"),

    /** 句號 */
    PERIOD("."),

    /** 豎線 */
    PIPE("|"),

    /** 問號 */
    QUESTION_MARK("?"),

    /** 雙引號 */
    QUOTATION_MARK("\""),

    /** 分號 */
    SEMICOLON(";"),

    /** 斜線 */
    SLASH("/"),

    /** 波浪號 */
    TILDE("~"),

    /** 下劃線 */
    UNDERSCORE("_"),

    UNKNOWN("");

    Symbols(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

}
