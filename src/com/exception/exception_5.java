package com.exception;

// 日志部分根据文档学习效果并不好，日后结合视频再深入学习
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class exception_5 {
    public static void main(String[] args) {
//        // 原生 Log
//        Logger log = Logger.getGlobal();
//        log.info("info");
//        log.fine("fine");
//        log.warning("warning");
//        log.severe("severe");

        Log log = LogFactory.getLog(exception_5.class);
        log.info("start...");
        log.warn("end.");

        // Commons Log 重载方法 info(String, Throwable) 可以方便地把异常现象打印到日志
        try {
            bar();
        } catch (Exception e) {
            log.info("测试 info() 重载功能",e);
        }


    }



    static void bar(){
        throw new IndexOutOfBoundsException("DASD");
    }
}
