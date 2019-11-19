package com.redeyefrog.enums;

/**
 * Line Request Source
 */
public enum Sources {

    GROUP("G"),
    ROOM("R"),
    USER("U"),

    UNKNOWN("");

    private String code;

    Sources(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Sources find(String code) {
        if(code != null && !"".equals(code)) {
            for(Sources type : values()) {
                if(code.equals(type.getCode())) {
                    return type;
                }
            }
        }

        return UNKNOWN;
    }

    public boolean isUnknown() {

        return equals(UNKNOWN);
    }

}
