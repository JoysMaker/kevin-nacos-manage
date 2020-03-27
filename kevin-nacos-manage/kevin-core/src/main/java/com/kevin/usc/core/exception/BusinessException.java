package com.kevin.usc.core.exception;

/**
 * 通用业务异常 msgid = 900x |  90xx
 * @author jianglz
 * @since 2018/3/5.
 */
public class BusinessException extends BaseException {

  public BusinessException(String message) {
    super(9000, message);
  }
  public BusinessException(long msgid, String message) {
    super(msgid, message);
  }

}
