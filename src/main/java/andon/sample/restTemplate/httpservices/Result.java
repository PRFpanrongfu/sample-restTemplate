package andon.sample.restTemplate.httpservices;

import org.springframework.http.HttpStatus;

import java.util.Optional;

/**
 * Created by Caozheng on 2017/3/7.
 */
public class Result<T> {
    private Optional<T> value;
    private HttpStatus status;
    private Optional<ErrorResult> error;

    private Result(Optional<T> value, HttpStatus status, Optional<ErrorResult> error){
        this.value = value;
        this.status = status;
        this.error = error;
    }

    public Optional<T> getValue() {
        return value;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Optional<ErrorResult> getError() {
        return error;
    }

    public static <T> Result<T> success(T value){
        return new Result(Optional.ofNullable(value), HttpStatus.OK, Optional.empty());
    }

    public static <T> Result<T> success(T value, HttpStatus status){
        return new Result(Optional.ofNullable(value), status, Optional.empty());
    }

    public static <T> Result<T> fail(double code, String msg, HttpStatus status){
        return new Result(Optional.empty(), status, Optional.of(new ErrorResult(code, msg)));
    }

}
