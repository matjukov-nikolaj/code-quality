package com.codequality.lks.volgatech.pages;

import com.codequality.lks.volgatech.utilites.DriverCreator;
import com.codequality.lks.volgatech.utilites.Constants;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LoginPageTests {

    private WebDriver driver;
    private LoginPage loginPage;

    @Test
    public void user_login_with_correct_username_and_password() {
        this.createWebDriver();
        loginPage.loginUser(Constants.UserInfo.CORRECT_USERNAME, Constants.UserInfo.CORRECT_PASS);
        this.checkUser();
        this.driver.quit();
    }

    @Test
    public void user_login_with_correct_username_and_password_and_logout() {
        this.createWebDriver();
        loginPage.loginUser(Constants.UserInfo.CORRECT_USERNAME, Constants.UserInfo.CORRECT_PASS);
        this.checkUser();
        ProfilePage profile = new ProfilePage(driver);
        profile.logout();
        Assert.assertEquals(driver.getCurrentUrl(), Constants.Page.LOGIN);
        this.driver.quit();
    }

    @Test
    public void user_login_with_incorrect_username() {
        this.loginWithIncorrectData(Constants.UserInfo.INCORRECT_USERNAME, Constants.UserInfo.CORRECT_PASS);
        Assert.assertEquals(Constants.UserInfo.VALIDATION_ERROR, loginPage.getValidationError());
        this.driver.quit();
    }

    @Test
    public void user_login_with_incorrect_password() {
        this.loginWithIncorrectData(Constants.UserInfo.CORRECT_USERNAME, Constants.UserInfo.INCORRECT_PASS);
        Assert.assertEquals(Constants.UserInfo.VALIDATION_ERROR, loginPage.getValidationError());
        this.driver.quit();
    }

    @Test
    public void can_redirect_to_page_about_recover_instruction_login_and_password() {
        this.createWebDriver();
        this.loginPage.profileRecoveryInstructionsClickHandler();
        this.waitPage();
        Assert.assertEquals(Constants.Page.RECOVER_INSTRUCTIONS, this.driver.getCurrentUrl());
        this.driver.quit();
    }

    private void waitPage() {
        this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    private void loginWithIncorrectData(String username, String pass) {
        this.createWebDriver();
        loginPage.loginUser(username, pass);
        this.waitPage();
    }

    private void checkUser() {
        ProfilePage profile = new ProfilePage(driver);
        Assert.assertEquals(Constants.UserInfo.EXPECTED_USER_FULL_NAME, profile.getUserFullName());
    }

    private void createWebDriver() {
        DriverCreator driverCreator = new DriverCreator(Constants.Page.LOGIN);
        this.driver = driverCreator.getDriver();
        this.loginPage = new LoginPage(driver);
    }
}
