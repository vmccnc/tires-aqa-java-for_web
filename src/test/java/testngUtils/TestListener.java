package testngUtils;

import lombok.extern.log4j.Log4j2;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;

@Log4j2
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("======================================== STARTING TEST {} ========================================", iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info("======================================== FINISHED TEST {} Duration: {}s ========================================", iTestResult.getName(), getExecutionTime(iTestResult));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.error("======================================== FAILED TEST {} Duration: {}s ========================================", iTestResult.getName(), getExecutionTime(iTestResult));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.warn("======================================== SKIPPING TEST {} ========================================", iTestResult.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.warn("======================================== TEST FAILED BUT WITHIN SUCCESS PERCENTAGE {} ========================================", iTestResult.getName());
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        log.info("======================================== STARTING TEST CONTEXT {} ========================================", iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        log.info("======================================== FINISHING TEST CONTEXT {} ========================================", iTestContext.getName());
    }

    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }
}
