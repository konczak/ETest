package pl.konczak.etest.error;

import java.util.HashMap;
import java.util.Map;

public class SystemException
        extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private ErrorCode errorCode;
    private Map<String, Object> properties;

    public SystemException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.properties = new HashMap<String, Object>();
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public SystemException add(String property, Object value) {
        this.properties.put(property, value);
        return this;
    }
}
