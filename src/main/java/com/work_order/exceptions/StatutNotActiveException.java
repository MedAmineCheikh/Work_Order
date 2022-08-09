package com.work_order.exceptions;

public class StatutNotActiveException extends RuntimeException  {
    public StatutNotActiveException(String message) {
        super(message);
    }
}
