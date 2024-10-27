package com.example.testproject.service.handler;

public abstract class BaseHandler<T> {
    private BaseHandler next;
    public void setHandler(BaseHandler next) {
        this.next = next;
    }
    public abstract void handle(T obj);
}
