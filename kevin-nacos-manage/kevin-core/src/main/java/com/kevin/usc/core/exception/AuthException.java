package com.kevin.usc.core.exception;

/**
 * 401 ex
 *
 * @author jianglz
 * @since 2018/3/5.
 */
public class AuthException extends BaseException {

  public AuthException(String message) {
    super(401, message);
  }

  public AuthException(long msgid, String message) {
    super(msgid, message);
  }

}
