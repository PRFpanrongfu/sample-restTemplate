package andon.sample.restTemplate.controllers;

import andon.sample.restTemplate.httpservices.IVCPService;
import andon.sample.restTemplate.httpservices.Result;
import andon.sample.restTemplate.models.VCP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Created by Caozheng on 2017/3/6.
 */
@RestController
public class VCPController extends BaseController{


    @Autowired
    private IVCPService vcpService;

    @GetMapping("/vcp/demo1")
    public ResponseEntity<?> getVCP1() throws Exception{
        CompletableFuture<Result<VCP>> future = vcpService.getVCPAsync();
        Result<VCP> result = future.get();

        if(result.getError().isPresent()){
            return fail(result.getError().get(), result.getStatus());
        }
        else{
            return success(result.getValue().get());
        }
    }

    @GetMapping("/vcp/demo2")
    public CompletableFuture<ResponseEntity<?>> getVCP2() throws Exception{
        return vcpService.getVCPAsync().thenApply(result->{
            Optional<ResponseEntity<?>> errorResult =
                    result.getError().map(err->fail(err, result.getStatus()));

            Optional<ResponseEntity<?>> valueResult =
                    result.getValue().map(value->success(value));

            return errorResult.isPresent()?errorResult.get():valueResult.get();

        });
    }

}
