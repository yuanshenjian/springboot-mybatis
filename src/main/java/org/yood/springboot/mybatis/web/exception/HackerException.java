package org.yood.springboot.mybatis.web.exception;

public class HackerException extends Exception {

    private HackerException(){

    }

    public static HackerException newInstance(){
        return new HackerException();
    }

}
