package com.codequality.moodle.volgatech.pages;

import com.codequality.moodle.volgatech.utilites.Constants;
import com.codequality.moodle.volgatech.utilites.DriverCreator;
import com.codequality.moodle.volgatech.utilites.SignIn;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageTests {
    public static final String instanceUrl = "https://moodle.volgatech.net/login/index.php?authldap_skipntlmsso=1";

    private WebDriver driver;
    private static final SignIn signIn = new SignIn();
    private static final Constants config = new Constants();

    @Test
    public void user_login_with_correct_data_to_moodle_volagtech_website() {
        this.loginWithCorrectUserName();
        this.driver.quit();
    }

    @Test
    public void user_login_with_correct_data_to_moodle_volagtech_website_and_logout_from_website() {
        this.loginWithCorrectUserName();
        this.logoutFromProfile();
        this.checkForUserAbsence(this.config.NOT_LOGGED_IN);
        this.driver.quit();
    }

    @Test
    public void try_to_user_login_with_incorrect_data_to_moodle_volgatech_website() {
        this.driver = this.getWebDriver();
        this.loginWithIncorrectData(this.config.INCORRECT_USERNAME, this.config.INCORRECT_PASSWORD);
        this.driver.quit();
    }

    @Test
    public void try_to_user_login_with_correct_username_and_incorrect_password_to_moodle_volgatech_website() {
        this.driver = this.getWebDriver();
        this.loginWithIncorrectData(this.config.CORRECT_USERNAME, this.config.INCORRECT_PASSWORD);
        this.driver.quit();
    }

    @Test
    public void can_redirect_to_refactor_password_url_if_entered_correct_password() {
        this.driver = this.getWebDriver();
        this.loginWithIncorrectData(this.config.CORRECT_USERNAME, this.config.INCORRECT_PASSWORD);
        this.driver.findElement(By.id("chpwd")).click();
        this.signIn.loginUser(this.config.CORRECT_USERNAME, this.config.CORRECT_PASSWORD, this.config.SIGN_IN_BUTTON, this.driver);
        WebElement oldPass = this.driver.findElement(By.xpath("//*[@id=\"tblMid\"]/tbody/tr[8]/td/table/tbody/tr[1]/td[1]/label"));
        WebElement newPass = this.driver.findElement(By.xpath("//*[@id=\"tblMid\"]/tbody/tr[8]/td/table/tbody/tr[2]/td[1]/label"));
        Assert.assertEquals("Старый пароль:", oldPass.getText());
        Assert.assertEquals("Новый пароль:", newPass.getText());
        this.driver.quit();
    }

    private void checkForUserAbsence(String expectedText) {
        WebElement signInButton = this.driver.findElement(By.className("btn-login"));
        String text = signInButton.getText();
        Assert.assertEquals(expectedText, text);
    }

    private void logoutFromProfile() {
        driver.findElement(By.className("usermendrop")).click();
        WebElement logoutButton = this.driver.findElement(By.linkText("Выход"));
        logoutButton.click();
    }

    private void loginWithCorrectUserName() {
        this.driver = this.getWebDriver();
        signIn.loginUser(this.config.CORRECT_USERNAME, this.config.CORRECT_PASSWORD, this.config.LOGIN_BUTTON, this.driver);
        this.checkUserName("Николай");
    }

    private WebDriver getWebDriver() {
        DriverCreator driverCreator = new DriverCreator(this.instanceUrl);
        return driverCreator.getDriver();
    }

    private void loginWithIncorrectData(String username, String pass) {
        signIn.loginUser(username, pass, this.config.LOGIN_BUTTON, this.driver);
        signIn.loginUser(username, pass, this.config.SIGN_IN_BUTTON, this.driver);
        WebElement errorBlock = this.driver.findElement(By.className("wrng"));
        String errorText = errorBlock.getText();
        Assert.assertEquals("“Нет необходимых разрешений для доступа на этот веб-узел. Обратитесь к администратору веб-узла.”", errorText);
    }

    private void checkUserName(String expectedName) {
        WebElement profileUser = this.driver.findElement(By.xpath("//*[text()='Николай\t\t\t\t\t\t\t\t\t']"));
        String name = profileUser.getText();
        Assert.assertEquals(expectedName, name);
    }

}
