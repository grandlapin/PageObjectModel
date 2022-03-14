package com.companyname.testcases;

import com.companyname.pages.HomePage;
import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightPageTest  extends TestBase {
    HomePage homePage;
    TestUtil testUtil;

    @BeforeMethod
    @Parameters({"browser", "url","departureFlight", "arrivalFlight"})
    public void setUp(String browser, String url,String departureFlight,String arrivalFlight) {
        initialization(browser, url);
        testUtil = new TestUtil();
        homePage = new HomePage();
        homePage.startBooking(departureFlight, arrivalFlight);
    }

    @Test
    public void test(){
        Assert.assertTrue(true);
    }


    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
