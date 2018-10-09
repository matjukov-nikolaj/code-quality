package com.codequality.moodle.volgatech.pages;

import com.codequality.moodle.volgatech.utilites.DriverCreator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MoodleVolgatechPageTests {
    private WebDriver driver;

    private WebDriver getWebDriver() {
        DriverCreator driverCreator = new DriverCreator("https://moodle.volgatech.net/login/index.php?authldap_skipntlmsso=1");
        return driverCreator.getDriver();
    }


    private void loginWithCorrectUserName() {
        this.driver = this.getWebDriver();
//        this.loginUser("s1160501065", "Testpass1234/", "loginbtn");
    }


}
