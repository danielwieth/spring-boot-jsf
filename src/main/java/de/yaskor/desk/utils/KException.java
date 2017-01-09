package de.yaskor.desk.utils;

import lombok.Getter;

/**
 *
 * @author saka
 */
public class KException extends Exception {

    @Getter private final String messageKey;

    public KException(String messageKey) {
        super();
        this.messageKey = messageKey;
    }
    
    public KException(String messageKey, Throwable cause) {
        super(cause.getMessage(), cause);
        this.messageKey = messageKey;
    }
}
