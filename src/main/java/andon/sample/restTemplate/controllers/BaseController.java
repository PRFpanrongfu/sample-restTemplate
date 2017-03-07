package andon.sample.restTemplate.controllers;

import andon.sample.restTemplate.httpservices.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Caozheng on 2017/3/7.
 */
public class BaseController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllError(HttpServletRequest request, Throwable ex){

        return fail(500d, ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<?> fail(double code, String message, HttpStatus status){
        ResponseEntity.BodyBuilder builder = ResponseEntity.status(status);

        return builder.body(new ErrorResult(code, message));

    }

    public ResponseEntity<?> fail(ErrorResult errorResult, HttpStatus status){
        ResponseEntity.BodyBuilder builder = ResponseEntity.status(status);

        return builder.body(errorResult);
    }

    public ResponseEntity<?> success(){
        return new ResponseEntity(null, HttpStatus.OK);
    }

    public <T> ResponseEntity<T> success(T entity){
        return new ResponseEntity(entity, HttpStatus.OK);
    }
}
