package com.companyname.pages;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.KeyboardRobot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.time.ZoneId;



public class HomePage extends TestBase {

    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    WebElement cookieAcceptButton;

    @FindBy(xpath="//a[@data-qa=\"home-page.common-header-flight-status\"]")
    WebElement flightStatusBtn;

    @FindBy(xpath="//input[@id='flight-OriginStationCode']")
    WebElement flightDepartureInput;

    @FindBy(xpath = "//input[@id='flight-DestinationStationCode']")
    WebElement flightArrivalInput;

    @FindBy(xpath = "//div[@data-qa='calendar-selection']")
    WebElement calendarDiv;

    @FindBy(xpath = "//div[@role=\"application\"]")
    WebElement calendarDisplay;

    @FindBy(xpath = "//button[@data-qa='home-search-widget-btn-desktop']")
    WebElement startBookingBtn;

    
    //Initializing the Page Objects:
    public HomePage(){
        PageFactory.initElements(driver, this);
    }

    //Actions:
    public String validateHomePageTitle(){
        return driver.getTitle();
    }

    public void setDepartureFlight(String ap){

        flightDepartureInput.sendKeys(ap);
    }

    public boolean checkDepartureFlight(String ap){

        boolean isOk = flightDepartureInput.getText().contains(ap);
        return isOk;
    }

    public void setArrivalFlight(String ap){

        flightArrivalInput.sendKeys(ap);
    }

    public boolean checkArrivalFlightIsOk(String ap){

        boolean isOk = flightArrivalInput.getText().contains(ap);
        return isOk;
    }

    public void validateCookieAcceptBtn(){
        Actions action = new Actions(driver);
        if(cookieAcceptButton.isDisplayed()){

            /* <= java 8!!!

            WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
            try {
                wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));
                JavascriptExecutor jse = (JavascriptExecutor) driver;
                cookieAcceptButton.click();
            } catch (Exception e) {
                e.printStackTrace();
            }
            */

            try {
                Thread.sleep(8000);

                cookieAcceptButton.click();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }


    }

    public void clickOnCalendar(){
        Actions action = new Actions(driver);
        action.moveToElement(calendarDiv).build().perform();
        calendarDiv.click();
    }

    public void setDepartureReturnSevenPlusOne() throws AWTException {
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_A);
        r.keyRelease(KeyEvent.VK_A);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_DELETE);
        r.keyRelease(KeyEvent.VK_DELETE);

        ZoneId zoneId = ZoneId.of( "America/Montreal" );
        LocalDate today = LocalDate.now( zoneId );
        LocalDate weekLater = today.plusWeeks( 1 );

        KeyboardRobot key = new KeyboardRobot();
        key.type(weekLater.toString());
        LocalDate weekLaterPlusOneDay = weekLater.plusDays(1);
        key.type(" - ");
        key.type(weekLaterPlusOneDay.toString());

        r.keyPress(KeyEvent.VK_ENTER);

    }

    public boolean calendarIsDisplay(){
        return calendarDisplay.isDisplayed();
    }

    public void clickOnFlightStatusTab(){
        Actions action = new Actions(driver);
        action.moveToElement(flightStatusBtn).build().perform();
        flightStatusBtn.click();
    }

    public FlightPage startBooking(String departureFlight,String arrivalFlight){

        Actions action = new Actions(driver);

        validateCookieAcceptBtn();
        setDepartureFlight(departureFlight);
        setArrivalFlight(arrivalFlight);
        clickOnCalendar();
        try {
            setDepartureReturnSevenPlusOne();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        action.moveToElement(startBookingBtn).build().perform();
        startBookingBtn.click();

        return new FlightPage();

    }



}
