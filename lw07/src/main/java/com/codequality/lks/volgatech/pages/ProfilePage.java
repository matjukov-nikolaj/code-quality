package com.codequality.lks.volgatech.pages;

import com.codequality.lks.volgatech.utilites.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePage {

    private WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUserFullName() {
        WebElement userFullName = this.driver.findElement(By.xpath("//*[@id=\"leftMenu\"]/div[2]"));
        return userFullName.getText();
    }

    public void officalVolgatechPageClickHandler() {
        WebElement volgatechPageLink = this.driver.findElement(By.xpath("/html/body/footer/p[1]/a[1]"));
        volgatechPageLink.click();
    }

    public void profileUsageInstructionsClickHandler() {
        WebElement profileUsageInstructionsLink = this.driver.findElement(By.xpath("/html/body/footer/p[1]/a[2]"));
        profileUsageInstructionsLink.click();
    }

    public void myTimeTableClickHandler() {
        WebElement timeTablePage = this.driver.findElement(By.linkText("Мое расписание занятий"));
        timeTablePage.click();
    }

    public void myExamListClickHandler() {

    }

    public void logout() {
        WebElement logoutButton = this.driver.findElement(By.linkText("Выйти"));
        logoutButton.click();
    }

    public void calendarClickHandler(String date) {
        WebElement calendar = this.driver.findElement(By.id("SelectedDate"));
        calendar.click();
        calendar.clear();
        calendar.sendKeys(date);
        calendar.sendKeys(Keys.ENTER);
    }

    public String getWeekColorName() {
        WebElement weekColorNameElement = this.driver.findElement(By.id("WeekColorName"));
        return weekColorNameElement.getText();
    }

}
