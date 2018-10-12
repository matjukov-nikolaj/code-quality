package com.codequality.lks.volgatech.pages;

import com.codequality.lks.volgatech.utilites.Utilites;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.codequality.lks.volgatech.utilites.Constants;

public class LoginPage {

    //xpath
    private static final String VALIDATION_ERROR = "/html/body/div/div/form/div[1]/ul/li";
    private static final String PROFILE_RECOVERY_INSTRUCTIONS = "/html/body/div/div/form/p/a";
    private static final String SIGN_IN_BUTTON = "/html/body/div/div/form/input[2]";

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getValidationError() {
        return Utilites.getElementTextWhichFoundByXPath(VALIDATION_ERROR, this.driver);
    }

    public void profileRecoveryInstructionsClickHandler() {
        Utilites.elementClickWhichFoundByXPath(PROFILE_RECOVERY_INSTRUCTIONS, this.driver);
    }

    public void loginUser(String username, String pass) {
        this.setUserNameAndPassWordField(username, pass);
        this.signInButtonClickHandler();
    }

    private void setUserNameAndPassWordField(String username, String pass) {
        WebElement usernameField = this.driver.findElement(By.id(Constants.UserInfo.USERNAME_ID));
        usernameField.sendKeys(username);
        WebElement passwordField = this.driver.findElement(By.id(Constants.UserInfo.PASS_ID));
        passwordField.sendKeys(pass);
    }

    private void signInButtonClickHandler() {
        WebElement signInButton = this.driver.findElement(By.xpath(SIGN_IN_BUTTON));
        signInButton.click();
    }

}