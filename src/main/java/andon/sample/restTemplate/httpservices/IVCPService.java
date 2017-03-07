package andon.sample.restTemplate.httpservices;

import andon.sample.restTemplate.models.VCP;
import org.springframework.util.concurrent.ListenableFuture;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * Created by Caozheng on 2017/3/6.
 */
public interface IVCPService {
    Result<VCP> getVCP() throws IOException;
    CompletableFuture<Result<VCP>> getVCPAsync();
}
