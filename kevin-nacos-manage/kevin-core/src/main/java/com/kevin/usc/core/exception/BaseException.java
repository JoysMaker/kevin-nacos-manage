package com.kevin.usc.core.exception;

/**
 * @author jianglz
 * @since 2018/3/5.
 */
public class BaseException extends RuntimeException{

    // 异常信息编码
    private long msgid;

    private String type;

    /**
     * Method getMsgid.
     *
     * @return long
     */
    public long getMsgid() {
        return msgid;
    }

    /**
     * Method setMsgid.
     *
     * @param msgid long
     */
    public void setMsgid(long msgid) {
        this.msgid = msgid;
    }

    public BaseException() {
        super();
    }

    /**
     * Constructor for BaseException.
     *
     * @param cause BaseException
     */
    public BaseException(BaseException cause) {
        super(cause);
        // this.sendException(cause);
    }

    /**
     * Constructor for BaseException.
     *
     * @param message String
     */
    public BaseException(String message) {
        super(message);
        this.sendException(message);
    }

    /**
     * Constructor for BaseException.
     *
     * @param message String
     * @param cause   Throwable
     */
    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.sendException(message, cause);
    }

    /**
     * Constructor for BaseException.
     *
     * @param msgid   long
     * @param message String
     */
    public BaseException(long msgid, String message) {
        super(message);
        this.msgid = msgid;
    }

    /**
     * Constructor for BaseException.
     *
     * @param msgid   long
     * @param message String
     * @param cause   Throwable
     */
    public BaseException(long msgid, String message, Throwable cause) {
        super(message, cause);
        this.msgid = msgid;
    }

    /**
     * Constructor for BaseException.
     *
     * @param msgid   long
     * @param message String
     * @param type    String
     */
    public BaseException(long msgid, String message, String type) {
        super(message);
        this.msgid = msgid;
        this.sendException(msgid, message, type);
    }

    /**
     * Method sendException.
     *
     * @param cause Throwable
     */
    public void sendException(Throwable cause) {
        // ExceptionProcess.senderException(cause.getMessage(),
        // BaseException.class, cause.getMessage(), "-99999");
    }

    /**
     * Method sendException.
     *
     * @param message String
     */
    public void sendException(String message) {
        // ExceptionProcess.senderException("BaseException", BaseException.class,
        // message, "-99999");
    }

    /**
     * Method sendException.
     *
     * @param message String
     * @param cause   Throwable
     */
    public void sendException(String message, Throwable cause) {
        // ExceptionProcess.senderException(cause.getMessage(),
        // BaseException.class, message, "-99999");
    }

    /**
     * Method sendException.
     *
     * @param msgid   long
     * @param message String
     * @param type    String
     */
    public void sendException(long msgid, String message, String type) {
        // ExceptionProcess.senderException(type, BaseException.class, message,
        // String.valueOf(msgid));
    }

    /**
     * Method sendException.
     *
     * @param msgid   long
     * @param message String
     * @param cause   Throwable
     */
    public void sendException(long msgid, String message, Throwable cause) {
        // ExceptionProcess.senderException(cause.getMessage(),
        // BaseException.class, message, String.valueOf(msgid));
    }

    /**
     * Method setType.
     *
     * @param type String
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Method getType.
     *
     * @return String
     */
    public String getType() {
        return type;
    }
}
