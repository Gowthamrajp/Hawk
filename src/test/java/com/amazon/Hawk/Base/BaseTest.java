package com.amazon.Hawk.Base;

import java.util.logging.Logger;
import com.amazon.Hawk.Pages.BuyPO;
import com.amazon.Hawk.Pages.LoginPO;
import com.amazon.Hawk.Pages.SearchPO;
import com.amazon.Hawk.Utils.DriverFactory;
import com.amazon.Hawk.Utils.TestLogger;

public class BaseTest {
	protected DriverFactory driverFactory = DriverFactory.getInstance();
	protected Logger logger = TestLogger.getInstance().getLogger();
	
	public static String getMethodName() {
        // Get the current stack trace
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        // The method name is at index 2 in the stack trace
        // Index 0 is getStackTrace, Index 1 is the current method (getMethodName), Index 2 is the calling method
        String methodName = stackTrace[2].getMethodName();
        return methodName;
    }
//	@BeforeMethod
//	public void setUp(WebDriver driver) {
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//        logger.info("Test environment setup successful for "+getMethodName());
//    }
}