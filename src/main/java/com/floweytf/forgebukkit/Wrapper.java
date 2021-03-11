package com.floweytf.forgebukkit;

public abstract class Wrapper<T> {
    private T handle;

    protected Wrapper(T handle) {
        this.handle = handle;
    }

    protected void setHandle(T handle) {
        this.handle = handle;
    }

    public T getHandle() {
        return handle;
    }
}
