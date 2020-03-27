package com.kevin.usc.base.bean;

import lombok.Data;

/**
 * 异常返回对象
 * @author jianglz
 * @since 2018/7/27.
 */
@Data
public class WError {
  long code = 500;

  String msg;

  public WError() {
    this.msg = "fail";
  }
  public WError(long code,String msg){
      this.code = code;
      this.msg = msg;
  }
  public WError(String msg) {
    this.msg = msg;
  }

  public static WError success(){
    return new WError(200,"success");
  }
  public static WError fail(){
    return new WError();
  }

  public static WError success(String msg){
    return new WError(200,msg);
  }
  public static WError fail(String msg){
    return new WError(msg);
  }

 
}
