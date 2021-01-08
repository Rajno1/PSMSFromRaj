package com.issi.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.issi.constants.FrameworkConstants;

import java.io.File;
import java.io.IOException;

public class ExtentReport  {
	  public static ExtentReports extent;
	    public static ExtentSparkReporter spark;
	    public static ExtentSparkReporter sparkFailed;
	    public static ExtentTest test;
	    
	    
    public static ExtentReports initReport() {
        try {
            extent = new ExtentReports();
            spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/Extent Report/"+"Reoprt.html").viewConfigurer()
                    .viewOrder()
                    .as(new ViewName[]{
                            ViewName.TEST,
                            ViewName.LOG,
                            ViewName.EXCEPTION,
                            ViewName.DEVICE,
                            ViewName.AUTHOR,
                            ViewName.DASHBOARD
                    })
                    .apply();
            sparkFailed = new ExtentSparkReporter("target/failed-test-index.html").filter().statusFilter().as(new Status[]{Status.FAIL, Status.SKIP}).apply();

            sparkFailed.config().setDocumentTitle("Failed Tests");
            spark.loadXMLConfig(new File("ExtentResources\\spark-config.xml")); // configured 'spark-config.xml' file
            extent.setSystemInfo("os", "Window10");
            extent.attachReporter(spark, sparkFailed);


            return extent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
