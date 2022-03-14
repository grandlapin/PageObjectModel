package com.companyname.pages;

import com.crm.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class FlightPage extends TestBase {

    @FindAll( {
            @FindBy(xpath = ""),
            @FindBy(xpath = "")
    } )
    private List<WebElement> listOfDepartureFlights;


}
