package andon.sample.restTemplate.httpservices;

import andon.sample.restTemplate.models.VCP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * Created by Caozheng on 2017/3/6.
 */
@Component
public class VCPService implements IVCPService {

    @Autowired
    private AsyncRestTemplate asyncRestTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ResponseEntityWrapper responseEntityWrapper;

    @Override
    public Result<VCP> getVCP() throws IOException{

        //更复杂情况使用exchange
        return responseEntityWrapper.toResult(
                restTemplate.getForEntity("http://139.217.3.141:8080/v0/vcp/vcp/瓜皮程序/modelKey",String.class), VCP.class
        );
    }

    @Override
    public CompletableFuture<Result<VCP>> getVCPAsync(){

        //更复杂情况使用exchange
        return responseEntityWrapper.toCompletableFuture(
                asyncRestTemplate.getForEntity("http://139.217.3.141:8080/v0/vcp/vcp/瓜皮程序/modelKey",String.class), VCP.class
        );

    }
}
