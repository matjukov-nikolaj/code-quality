package com.codequality.lks.volgatech.pages;

import com.codequality.lks.volgatech.utilites.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProfilePage {

    private WebDriver driver;
    private Actions builder;

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
        WebElement timeTablePageLink = this.driver.findElement(By.linkText("Мое расписание занятий"));
        timeTablePageLink.click();
    }

    public void myExamListClickHandler() {
        WebElement examListPageLink = this.driver.findElement(By.linkText("Мое расписание экзаменов"));
        examListPageLink.click();
    }

    public String getCurrentSemester() {
        WebElement semesterList = this.driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/span/span/span[1]"));
        return semesterList.getText();
    }

    public void semesterListClickHandler() {
        WebElement semesterList = this.driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/span"));
        Actions builder = new Actions(this.driver);
        builder.moveToElement(semesterList).build().perform();
        semesterList.click();
    }

    public void previousSemesterClickHandler() {
        (new WebDriverWait(this.driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Semestr_listbox\"]/li[2]"))).click();

    }

    public void myEducationClickHandler() {
        WebElement education = this.driver.findElement(By.linkText("Мое образование"));
        education.click();
    }

    public void myGrantsClickHandler() {
        WebElement education = this.driver.findElement(By.linkText("Мои стипендии"));
        education.click();
    }

    public void mySocialDocsClickHandler() {
        WebElement education = this.driver.findElement(By.linkText("Мои социальные документы"));
        education.click();
    }

    public void curriculumButtonClickHandler() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement curriculumButton = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div.wrapper > div.body-content > div > h1:nth-child(3) > a")));
        this.builder = new Actions(this.driver);
        this.builder.moveToElement(curriculumButton).click().build().perform();
    }

    public void studySheduleButtonClickHandler() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement studySheduleButton = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div.wrapper > div.body-content > div > h1:nth-child(4) > a")));
        this.builder = new Actions(this.driver);
        this.builder.moveToElement(studySheduleButton).click().build().perform();
    }


    public boolean isCurriculumTableOpen() {
        try {
            WebElement webElement = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("PlanContent")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStudySheduleTablesOpen() {
        try {
            WebElement webElement = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("GraphContent")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void myGrantWorksClickHandler() {
        WebElement education = this.driver.findElement(By.linkText("Мои достижения"));
        education.click();
    }

    public void myAcademicPerformanceClickHandler() {
        WebElement academicPerformance = this.driver.findElement(By.linkText("Моя успеваемость"));
        academicPerformance.click();
    }

    public void showPastAchievementsClickHandler() {
        WebElement onOffButton = this.driver.findElement(By.xpath("//*[@id=\"studentWorks_togglableOnOffSwitch_direction_2\"]/span/label"));
        Actions builder = new Actions(this.driver);
        builder.moveToElement(onOffButton).build().perform();
        onOffButton.click();
    }

    public String getTableWithPostAchievementsDisplayStyle() {
        WebElement table = this.driver.findElement(By.xpath("//*[@id=\"studentWorks_wideTable_direction_2\"]/table/tbody/tr[2]"));
        return table.getCssValue("display");
    }

    public void logout() {
        WebElement logoutButton = this.driver.findElement(By.linkText("Выйти"));
        logoutButton.click();
    }

    public void calendarClickHandler(String date) {
        WebElement calendar = (new WebDriverWait(this.driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("SelectedDate")));
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
