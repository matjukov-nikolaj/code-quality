package com.codequality.lks.volgatech.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.codequality.lks.volgatech.utilites.Constants;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getValidationError() {
        WebElement validationError = this.driver.findElement(By.xpath("/html/body/div/div/form/div[1]/ul/li"));
        return validationError.getText();
    }

    public void profileRecoveryInstructionsClickHandler() {
        WebElement profileRecoveryInstructions = this.driver.findElement(By.xpath("/html/body/div/div/form/p/a"));
        profileRecoveryInstructions.click();
    }

    public void loginUser(String username, String pass) {
        this.setUserNameAndPassWordField(username, pass);
        this.signInButtonClickHandler();
    }

    private void setUserNameAndPassWordField(String username, String pass) {
        WebElement usernameField = this.driver.findElement(By.id(Constants.USERNAME_ID));
        usernameField.sendKeys(username);
        WebElement passwordField = this.driver.findElement(By.id(Constants.PASS_ID));
        passwordField.sendKeys(pass);
    }

    private void signInButtonClickHandler() {
        WebElement signInButton = this.driver.findElement(By.xpath("/html/body/div/div/form/input[2]"));
        signInButton.click();
    }

}