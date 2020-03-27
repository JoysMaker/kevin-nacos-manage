package com.kevin.usc.core.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kevin.usc.core.bean.WError;
import lombok.extern.slf4j.Slf4j;


/**
 *
 * @author 小K
 * @desc 异常捕获类
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler{


  @ExceptionHandler(Exception.class)
  @ResponseBody
  public WError exceptionHandler(Exception ex) {

    String exceptionName = ex.getClass().getSimpleName();
//    ex.printStackTrace();
    log.error("error:>>{}",ex);
    WError wError = new WError();
    switch (exceptionName) {
      case "AuthException":
      case "InsufficientAuthenticationException":
      case "InvalidTokenException":
      case "OAuth2Exception":
        wError.setCode(401);
        wError.setMsg(ex.getMessage());
        break;
      case "RequestLimitException":
        wError.setCode(403);
        wError.setMsg(ex.getMessage());
        break;
      case "BusinessException":
        wError.setCode(((BusinessException) ex).getMsgid());
        wError.setMsg(ex.getMessage());
        break;
      case "BaseException":
        wError.setCode(400);
        break;
      case "DataAccessException":
        wError.setCode(400);
        wError.setMsg("数据库访问异常");
        break;
      case "SQLException":
        wError.setCode(400);
        break;
      case "MissingServletRequestParameterException":
      case "ServletRequestBindingException":
      case "ConversionNotSupportedException":
      case "TypeMismatchException":
      case "HttpMessageNotReadableException":
      case "HttpMessageNotWritableException":
      case "MethodArgumentNotValidException":
      case "MissingServletRequestPartException":
      case "BindException":
        wError.setCode(400);
        break;
      case "PageNotFoundException":
      case "NoHandlerFoundException":
      case "NoSuchRequestHandlingMethodException":
        wError.setCode(400);
        break;
      case "HttpRequestMethodNotSupportedException":
        wError.setCode(405);
        break;
      case "HttpMediaTypeNotAcceptableException":
        wError.setCode(405);
        break;
      case "HttpMediaTypeNotSupportedException":
        wError.setCode(415);
        break;
      case "MissingPathVariableException":
        wError.setCode(500);
        break;
      default:
        wError.setCode(500);
        wError.setMsg("服务器繁忙");
        break;
    }

    return wError;
  }


}