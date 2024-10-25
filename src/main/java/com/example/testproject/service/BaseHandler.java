package com.example.testproject.service;

public abstract class BaseHandler<T> {
    private BaseHandler next;
    public void setHandler(BaseHandler next) {
        this.next = next;
    }
    public abstract void handle(T obj);
}
