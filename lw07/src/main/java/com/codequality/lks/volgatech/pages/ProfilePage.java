package com.codequality.lks.volgatech.pages;

import com.codequality.lks.volgatech.utilites.Utilites;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ProfilePage {
    //linkText
    private static final String MY_TIME_TABLE = "Мое расписание занятий";
    private static final String MY_EXAM_LIST = "Мое расписание экзаменов";
    private static final String MY_EDUCATION = "Мое образование";
    private static final String MY_GRANTS = "Мои стипендии";
    private static final String MY_SOCIAL_DOCS = "Мои социальные документы";
    private static final String MY_ACHIEVEMENTS = "Мои достижения";
    private static final String MY_ACADEMIC_PERFORMANCE = "Моя успеваемость";
    private static final String LOGOUT = "Выйти";

    //xpath
    private static final String USER_NAME = "//*[@id=\"leftMenu\"]/div[2]";
    private static final String VOLGATECH_LINK = "/html/body/footer/p[1]/a[1]";
    private static final String PROFILE_INSTRUCTIONS_LINK = "/html/body/footer/p[1]/a[2]";
    private static final String SEMESTER = "/html/body/div[1]/div[2]/div/span/span/span[1]";
    private static final String SEMESTER_LIST = "/html/body/div[1]/div[2]/div/span";
    private static final String PREVIOUS_SEMESTER = "//*[@id=\"Semestr_listbox\"]/li[2]";
    private static final String SHOW_HIDE_TABLE_BUTTON = "//*[@id=\"studentWorks_togglableOnOffSwitch_direction_2\"]/span/label";
    private static final String ACHIEVEMENTS_TABLE = "//*[@id=\"studentWorks_wideTable_direction_2\"]/table/tbody/tr[2]";

    //cssSelector
    private static final String CURRICULUM = "body > div.wrapper > div.body-content > div > h1:nth-child(3) > a";
    private static final String STUDY_SHEDULE = "body > div.wrapper > div.body-content > div > h1:nth-child(4) > a";

    //id
    private static final String CURRICULUM_TABLE="PlanContent";
    private static final String STUDY_SHEDULE_TABLE = "GraphContent";
    private static final String SELECTED_DATE = "SelectedDate";
    private static final String WEEK_COLOR_NAME = "WeekColorName";

    private static final String STYLE_DISPLAY = "display";


    private WebDriver driver;
    private Actions builder;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUserFullName() {
        return Utilites.getElementTextWhichFoundByXPath(USER_NAME, this.driver);
    }

    public void officalVolgatechPageClickHandler() {
        Utilites.elementClickWhichFoundByXPath(VOLGATECH_LINK, this.driver);
    }

    public void profileUsageInstructionsClickHandler() {
        Utilites.elementClickWhichFoundByXPath(PROFILE_INSTRUCTIONS_LINK, this.driver);
    }

    public void myTimeTableClickHandler() {
        Utilites.elementClickWhichFoundByLinkText(MY_TIME_TABLE, this.driver);
    }

    public void myExamListClickHandler() {
        Utilites.elementClickWhichFoundByLinkText(MY_EXAM_LIST, this.driver);
    }

    public String getCurrentSemester() {
        return Utilites.getElementTextWhichFoundByXPath(SEMESTER, this.driver);
    }

    public void semesterListClickHandler() {
        WebElement semesterList = this.driver.findElement(By.xpath(SEMESTER_LIST));
        Actions builder = new Actions(this.driver);
        builder.moveToElement(semesterList).build().perform();
        semesterList.click();
    }

    public void previousSemesterClickHandler() {
        (new WebDriverWait(this.driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PREVIOUS_SEMESTER))).click();

    }

    public void myEducationClickHandler() {
        Utilites.elementClickWhichFoundByLinkText(MY_EDUCATION, this.driver);
    }

    public void myGrantsClickHandler() {
        Utilites.elementClickWhichFoundByLinkText(MY_GRANTS, this.driver);
    }

    public void mySocialDocsClickHandler() {
        Utilites.elementClickWhichFoundByLinkText(MY_SOCIAL_DOCS, this.driver);
    }

    public void curriculumButtonClickHandler() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement curriculumButton = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(CURRICULUM)));
        this.builder = new Actions(this.driver);
        this.builder.moveToElement(curriculumButton).click().build().perform();
    }

    public void studySheduleButtonClickHandler() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement studySheduleButton = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(STUDY_SHEDULE)));
        this.builder = new Actions(this.driver);
        this.builder.moveToElement(studySheduleButton).click().build().perform();
    }


    public boolean isCurriculumTableOpen() {
        try {
            WebElement webElement = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id(CURRICULUM_TABLE)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStudySheduleTablesOpen() {
        try {
            WebElement webElement = (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id(STUDY_SHEDULE_TABLE)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void myGrantWorksClickHandler() {
        Utilites.elementClickWhichFoundByLinkText(MY_ACHIEVEMENTS, this.driver);
    }

    public void myAcademicPerformanceClickHandler() {
        Utilites.elementClickWhichFoundByLinkText(MY_ACADEMIC_PERFORMANCE, this.driver);
    }

    public void showPastAchievementsClickHandler() {
        WebElement onOffButton = this.driver.findElement(By.xpath(SHOW_HIDE_TABLE_BUTTON));
        Actions builder = new Actions(this.driver);
        builder.moveToElement(onOffButton).build().perform();
        onOffButton.click();
    }

    public String getTableWithPostAchievementsDisplayStyle() {
        WebElement table = this.driver.findElement(By.xpath(ACHIEVEMENTS_TABLE));
        return table.getCssValue(STYLE_DISPLAY);
    }

    public void logout() {
        Utilites.elementClickWhichFoundByLinkText(LOGOUT, this.driver);
    }

    public void calendarClickHandler(String date) {
        WebElement calendar = (new WebDriverWait(this.driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(SELECTED_DATE)));
        calendar.click();
        calendar.clear();
        calendar.sendKeys(date);
        calendar.sendKeys(Keys.ENTER);
    }

    public String getWeekColorName() {
        return  this.driver.findElement(By.id(WEEK_COLOR_NAME)).getText();
    }

}
