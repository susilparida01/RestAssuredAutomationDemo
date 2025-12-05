package com.sl.api.framework.reporting;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportListener implements ITestListener {
	
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	
	@Override
	public void onStart(ITestContext context) {
		//Initialize Extent Report
		String reportPath = System.getProperty("user.dir") + "/target/ExtentReport.html";
		ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
		spark.config().setReportName("CISCO API Automation Report");
		spark.config().setDocumentTitle("API Test Report");
		  
		extent = new ExtentReports();
		extent.attachReporter(spark);
		extent.setSystemInfo("Project", "API");
		extent.setSystemInfo("Tester", "Susil P");
		  
	}
	
	@Override
	public void onFinish(ITestContext context) {
		// Write everything to the report
		if (extent != null) {
			extent.flush();
		}

	}
	
	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent
				.createTest(result.getMethod().getMethodName())
				.assignCategory(result.getMethod().getGroups());
		test.set(extentTest);
	  
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
	    test.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().log(Status.SKIP, "Test Skipped");
	    
	}	
	
}
