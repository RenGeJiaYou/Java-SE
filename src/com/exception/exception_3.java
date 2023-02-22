package com.exception;

// 除了 Java 定义的异常类型，还可以自定义
// 自定义的异常，要保证一个合理的继承体系，就需要创建一个共同的 BaseException.
// BaseException 推荐继承自 Java 标准库里的 RuntimeException
// BaseException 推荐继承RuntimeException的多个重载的构造函数，以供不同的超类使用
public class exception_3 {

}

class BaseException extends RuntimeException{
    // 多个构造函数
    public BaseException(){
        super();
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message){
        super(message);
    }

    public BaseException(String message,Throwable cause){
        super(message, cause);
    }
}

class UserNotFound extends BaseException{

}
class LoginFailedException extends BaseException {
}
