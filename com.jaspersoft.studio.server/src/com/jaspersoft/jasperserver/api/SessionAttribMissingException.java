package com.jaspersoft.jasperserver.api;

/**
 * Created by nthapa on 7/12/13.
 */

import java.lang.RuntimeException;

/**
 * Created by nthapa on 7/11/13.
 */
public class SessionAttribMissingException extends JSShowOnlyErrorMessage {

    public SessionAttribMissingException(String message) {
        super(message);
    }

    public SessionAttribMissingException(String message, Object[] args) {
        super(message, args);
    }

}
