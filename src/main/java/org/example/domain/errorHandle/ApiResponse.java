package org.example.domain.errorHandle;

public class ApiResponse<T> {
    private T data;
    private ErrorClass error;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ErrorClass getError() {
        return error;
    }

    public void setError(ErrorClass error) {
        this.error = error;
    }
}
