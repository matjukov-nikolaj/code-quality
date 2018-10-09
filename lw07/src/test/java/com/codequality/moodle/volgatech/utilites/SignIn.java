package com.codequality.moodle.volgatech.utilites;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignIn {

    public void loginUser(String username, String pass, String signInButtonId, WebDriver driver) {
        this.setUserNameAndPassWordField(username, pass, driver);
        this.signInButtonClickHandler(signInButtonId, driver);
    }

    private void setUserNameAndPassWordField(String username, String pass, WebDriver driver) {
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(username);
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(pass);
    }

    private void signInButtonClickHandler(String buttonId, WebDriver driver) {
        WebElement submitButton = driver.findElement(By.id(buttonId));
        submitButton.click();
    }

}
