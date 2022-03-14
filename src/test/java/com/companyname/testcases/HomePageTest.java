package com.companyname.testcases;

import com.crm.qa.base.TestBase;
import com.companyname.pages.HomePage;
import com.crm.qa.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.*;

import java.awt.*;

public class HomePageTest extends TestBase {
    HomePage homePage;
    TestUtil testUtil;

    /*
    '*************************************************************************
    'Name: setUp
    'Description : Prepare driver for Tcs in HomePage
    'Input       :  1.browser, a string to select browser in BaseTest
                    2.url, a string to got to the new HomePage for training web 03
    'Output      : NA
    'Return      : NA
    'Created By  : Geoffrey Le Coz
    'Created On  : 14 March 2022
    '*************************************************************************
    */
    @Parameters({"browser", "url"})
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser,
                      @Optional("https://www.spirit.com/?homeRedesign=true") String url)
    {

        initialization(browser, url);
        testUtil = new TestUtil();
        homePage = new HomePage();
        homePage.validateCookieAcceptBtn();
    }

    /*
    '*************************************************************************
    'Name: verifyHomePageTitleTest_homePageNew
    'Description : Check that HomePage started loading
    'Output      : NA
    'Return      : NA
    'Created By  : Geoffrey Le Coz
    'Created On  : 14 March 2022
    '*************************************************************************
    */    
    @Test(priority = 1)
    public void verifyHomePageTitleTest_homePageNew() {
        String homePageTitle = homePage.validateHomePageTitle();
        Assert.assertEquals(homePageTitle, "Book flight tickets online with low-fare airline | Spirit Airlines", "Home page title not matched");
    }
    
    /*
    '*************************************************************************
    'Name: verifyGuestCanSetDepartureFlightByTyping
    'Description : Input AP and check AP departure 1st leg
    'Input       : Upcoming 1.DataProvider
    'Output      : NA
    'Return      : NA
    'Created By  : Geoffrey Le Coz
    'Created On  : 14 March 2022
    '*************************************************************************
    */
    //TODO dataprovider
    @Test
    public void verifyGuestCanSetDepartureFlightByTyping() {
        homePage.setDepartureFlight("DFW");
        boolean isTrue = homePage.checkDepartureFlight("DFW");
        Assert.assertTrue(isTrue);

    }

    /*
    '*************************************************************************
    'Name: verifyGuestCanSetArrivalFlightByTyping
    'Description : Input AP and check AP departure 2d leg
    'Input       : Upcoming 1.DataProvider
    'Output      : NA
    'Return      : NA
    'Created By  : Geoffrey Le Coz
    'Created On  : 14 March 2022
    '*************************************************************************
    */
    //TODO dataprovider
    @Test
    public void verifyGuestCanSetArrivalFlightByTyping() {
        homePage.setArrivalFlight("TPA");
        Assert.assertTrue(homePage.checkArrivalFlightIsOk("TPA"));
    }

    /*
    '*************************************************************************
    'Name: calendar
    'Description : Interact with calendar and input currentdate plus 7 days
                   and current date plus 8 days
    'Output      : NA
    'Return      : NA
    'Created By  : Geoffrey Le Coz
    'Created On  : 14 March 2022
    '*************************************************************************
    */
    @Test
    public void calendar() throws AWTException {

        homePage.clickOnCalendar();
        homePage.setDepartureReturnSevenPlusOne();
        Assert.assertTrue(homePage.calendarIsDisplay());
    }

    /*
    '*************************************************************************
    'Name: startBooking
    'Description : Do all action needed for training web 03 TC
    'Input       : 1.String, departure Flight
    '              2.String: arrival Flight
    'Output      : NA
    'Return      : NA
    'Created By  : Geoffrey Le Coz
    'Created On  : 14 March 2022
    '*************************************************************************
    */
    @Parameters({"departureFlight", "arrivalFlight"})
    @Test
    public void startBooking(@Optional("DFW") String departureFlight,
                             @Optional("TPA") String arrivalFlight)
    {

        String oldUrl = driver.getCurrentUrl();
        homePage.startBooking(departureFlight,arrivalFlight);
        Assert.assertTrue(!oldUrl.equals(driver.getCurrentUrl()));

    }

    /*
    '*************************************************************************
    'Name: tearDown
    'Description : Close and quite driver
    'Output      : NA
    'Return      : NA
    'Created By  : Geoffrey Le Coz
    'Created On  : 14 March 2022
    '*************************************************************************
    */
    @AfterMethod
    public void tearDown() {
        //driver.close();
        //driver.quit();
    }
}
