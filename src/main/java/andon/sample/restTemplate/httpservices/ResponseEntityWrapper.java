package andon.sample.restTemplate.httpservices;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * Created by Caozheng on 2017/3/7.
 */
@Component
public class ResponseEntityWrapper {

    @Autowired
    private ObjectMapper mapper;

    public <T> CompletableFuture<Result<T>>
        toCompletableFuture(ListenableFuture<ResponseEntity<String>> futureResponseEntity, Class<T> tClass) {
        CompletableFuture<Result<T>> cf = new CompletableFuture<>();

        futureResponseEntity.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                cf.completeExceptionally(throwable);
            }

            @Override
            public void onSuccess(ResponseEntity<String> stringResponseEntity)  {
                HttpStatus status = stringResponseEntity.getStatusCode();
                if(status == HttpStatus.OK){
                    try {
                        T t = mapper.readValue(stringResponseEntity.getBody(), tClass);
                        cf.complete(Result.success(t));
                    }
                    catch (Exception ex){
                        cf.completeExceptionally(ex);
                    }
                }
                else{
                    try{
                        ErrorResult errorResult = mapper.readValue(stringResponseEntity.getBody(), ErrorResult.class);
                        cf.complete(Result.fail(errorResult.getErrorCode(), errorResult.getErrorMessage(), status));
                    }
                    catch (Exception ex){
                        cf.completeExceptionally(ex);
                    }
                }
            }
        });

        return cf;

    }

    public <T> Result<T> toResult(ResponseEntity<String> responseEntity, Class<T> tClass) throws IOException{
        HttpStatus status = responseEntity.getStatusCode();
        if(status == HttpStatus.OK){
            T t = mapper.readValue(responseEntity.getBody(), tClass);

            return Result.success(t);
        }
        else{
            ErrorResult errorResult = mapper.readValue(responseEntity.getBody(), ErrorResult.class);

            return Result.fail(errorResult.getErrorCode(), errorResult.getErrorMessage(), status);
        }
    }
}
