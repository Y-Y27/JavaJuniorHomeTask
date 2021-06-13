package com.yanch.javaDevHomeTask.exception;
@SuppressWarnings("serial")
public class UsernameAlreadyExistException extends Throwable {

    public UsernameAlreadyExistException(String message) {
        super(message);
    }
}
