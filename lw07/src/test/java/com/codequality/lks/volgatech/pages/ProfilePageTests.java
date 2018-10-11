package com.codequality.lks.volgatech.pages;

import com.codequality.lks.volgatech.utilites.Constants;
import com.codequality.lks.volgatech.utilites.DriverCreator;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class ProfilePageTests {

    private WebDriver driver;
    private ProfilePage profilePage;

    @Test
    public void can_redirect_to_official_volgatech_web_site() {
        this.createProfilePage();
        this.profilePage.officalVolgatechPageClickHandler();
        this.waitForRedirect();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        this.driver.switchTo().window(tabs.get(1));
        Assert.assertEquals(Constants.VOLGATECH_MAIN_PAGE, this.driver.getCurrentUrl());
        this.driver.quit();
    }

    @Test
    public void can_redirect_to_profile_usage_instructions() {
        this.createProfilePage();
        this.profilePage.profileUsageInstructionsClickHandler();
        this.waitForRedirect();
        Assert.assertEquals(Constants.PROFILE_INSTRUCTION_PAGE, this.driver.getCurrentUrl());
        this.driver.quit();
    }

    @Test
    public void can_redirect_to_time_table_page() {
        this.redirectToTimeTablePage();
        this.driver.quit();
    }

    @Test
    public void can_select_day_on_my_timetable_page() {
        this.redirectToTimeTablePage();
        Date firstDate = this.getNextWeekFromDate(new Date(System.currentTimeMillis()));
        this.profilePage.calendarClickHandler(this.getFormattedDate(firstDate));
        this.waitEvent();
        String firstWeekName = this.profilePage.getWeekColorName();
        Date secondDate = this.getNextWeekFromDate(firstDate);
        this.profilePage.calendarClickHandler(this.getFormattedDate(secondDate));
        this.waitEvent();
        String secondWeekName = this.profilePage.getWeekColorName();
        Assert.assertFalse(firstWeekName.equals(secondWeekName));
        this.driver.quit();
    }

    private String getFormattedDate(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return Integer.toString(day) + "." + Integer.toString(month) + "." + Integer.toString(year);
    }

    private Date getNextWeekFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        Date increasedDate = calendar.getTime();
        return increasedDate;
    }

    private void redirectToTimeTablePage() {
        this.createProfilePage();
        this.profilePage.myTimeTableClickHandler();
        Assert.assertEquals(Constants.MY_TIME_TABLE_PAGE, this.driver.getCurrentUrl());
    }

    private void waitEvent() {
        this.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    private void waitForRedirect() {
        this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    private void createProfilePage() {
        DriverCreator driverCreator = new DriverCreator(Constants.LOGIN_URL);
        this.driver = driverCreator.getDriver();
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.loginUser(Constants.CORRECT_USERNAME, Constants.CORRECT_PASS);
        this.profilePage = new ProfilePage(driver);
    }

}
