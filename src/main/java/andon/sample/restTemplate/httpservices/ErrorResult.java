package andon.sample.restTemplate.httpservices;

/**
 * Created by Caozheng on 2017/3/7.
 */
public class ErrorResult {
    private double errorCode;
    private String errorMessage;

    public ErrorResult(){

    }

    public ErrorResult(double errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public double getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
