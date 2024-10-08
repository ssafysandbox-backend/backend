package com.ssafy.sandbox.todo.dto;

public class ResponseDTO<T> {
    private T data;

    public ResponseDTO() {
    }

    public void setTodos(T data) {
        this.data = data;
    }

    public T getTodos() {
        return data;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "data=" + data +
                '}';
    }
}
