package com.example.testproject;

public abstract class BaseHandler<T, E> {
    private BaseHandler next;
    public void setHandler(BaseHandler next) {
        this.next = next;
    }
    public abstract E handle(T obj);
}
