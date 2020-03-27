package com.kevin.usc.core.exception;

/**
 * 验证码校验异常 msgid = 600x | 60xx
 * @author jianglz
 * @since 2018/8/2.
 */
public class ValidateCodeException extends BaseException {
  public ValidateCodeException(String message) {
    super(6000,message);
  }

  public ValidateCodeException(long msgid,String message) {
    super(msgid,message);
  }
}
