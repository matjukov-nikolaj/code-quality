package com.codequality.lks.volgatech.pages;

import com.codequality.lks.volgatech.utilites.Constants;
import com.codequality.lks.volgatech.utilites.DriverCreator;
import com.codequality.lks.volgatech.utilites.Utilites;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ProfilePageTests {

    private WebDriver driver;
    private ProfilePage profilePage;

    @Test
    public void can_redirect_to_official_volgatech_web_site() {
        this.createProfilePage();
        this.profilePage.officalVolgatechPageClickHandler();
        this.waitPage();
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        this.driver.switchTo().window(tabs.get(1));
        Assert.assertEquals(Constants.Page.VOLGATECH_MAIN, this.driver.getCurrentUrl());
        this.driver.quit();
    }

    @Test
    public void can_redirect_to_profile_usage_instructions() {
        this.createProfilePage();
        this.profilePage.profileUsageInstructionsClickHandler();
        this.waitPage();
        Assert.assertEquals(Constants.Page.PROFILE_INSTRUCTION, this.driver.getCurrentUrl());
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
        Date firstDate = Utilites.getNextWeekFromDate(new Date(System.currentTimeMillis()));
        this.profilePage.calendarClickHandler(Utilites.getFormattedDate(firstDate));
        String firstWeekName = this.profilePage.getWeekColorName();
        Date secondDate = Utilites.getNextWeekFromDate(firstDate);
        this.profilePage.calendarClickHandler(Utilites.getFormattedDate(secondDate));
        String secondWeekName = this.profilePage.getWeekColorName();
        Assert.assertFalse(firstWeekName.equals(secondWeekName));
        this.driver.quit();
    }

    @Test
    public void can_choose_semester_exam_on_exam_list_page() {
        this.redirectToExamListPage();
        this.сompareСurrentSemesterChoices();
        this.driver.quit();
    }

    @Test
    public void can_watch_my_equcation_page() {
        this.redirectToEducationPage();
        this.profilePage.studySheduleButtonClickHandler();
        Assert.assertTrue(this.profilePage.isStudySheduleTablesOpen());
        this.profilePage.curriculumButtonClickHandler();
        Assert.assertTrue(this.profilePage.isCurriculumTableOpen());
        this.driver.quit();
    }

    @Test
    public void can_watch_my_grant_works() {
        this.redirectToGrantWorksPage();
        String displayStyle = this.profilePage.getTableWithPostAchievementsDisplayStyle();
        this.profilePage.showPastAchievementsClickHandler();
        String currDisplayStyle = this.profilePage.getTableWithPostAchievementsDisplayStyle();
        Assert.assertFalse(currDisplayStyle.equals(displayStyle));
        this.driver.quit();
    }

    @Test
    public void can_watch_my_academic_performance() {
        this.redirectToAcademicPerformancePage();
        this.сompareСurrentSemesterChoices();
        this.driver.quit();
    }

    @Test
    public void can_watch_my_grants_page() {
        this.createProfilePage();
        this.profilePage.myGrantsClickHandler();
        Assert.assertEquals(Constants.Page.MY_GRANTS, this.driver.getCurrentUrl());
        this.driver.quit();
    }

    @Test
    public void can_watch_my_social_docs_page() {
        this.createProfilePage();
        this.profilePage.mySocialDocsClickHandler();
        Assert.assertEquals(Constants.Page.MY_SOCIAL_DOCS, this.driver.getCurrentUrl());
        this.driver.quit();
    }


    private void сompareСurrentSemesterChoices() {
        String currentSemester = this.profilePage.getCurrentSemester();
        this.profilePage.semesterListClickHandler();
        this.profilePage.previousSemesterClickHandler();
        String previousSemester = this.profilePage.getCurrentSemester();
        Assert.assertFalse(currentSemester.equals(previousSemester));
    }

    private void redirectToAcademicPerformancePage() {
        this.createProfilePage();
        this.profilePage.myAcademicPerformanceClickHandler();
        Assert.assertEquals(Constants.Page.MY_ACADEMIC_PERFORMANCE, this.driver.getCurrentUrl());
    }

    private void redirectToGrantWorksPage() {
        this.createProfilePage();
        this.profilePage.myGrantWorksClickHandler();
        Assert.assertEquals(Constants.Page.MY_WORKS, this.driver.getCurrentUrl());
    }

    private void redirectToEducationPage() {
        this.createProfilePage();
        this.profilePage.myEducationClickHandler();
        Assert.assertEquals(Constants.Page.MY_EDUCATION, this.driver.getCurrentUrl());
    }

    private void redirectToTimeTablePage() {
        this.createProfilePage();
        this.profilePage.myTimeTableClickHandler();
        Assert.assertEquals(Constants.Page.MY_TIME_TABLE, this.driver.getCurrentUrl());
    }

    private void redirectToExamListPage() {
        this.createProfilePage();
        this.profilePage.myExamListClickHandler();
        Assert.assertEquals(Constants.Page.MY_EXAM_LIST, this.driver.getCurrentUrl());
    }

    private void waitPage() {
        this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    private void createProfilePage() {
        DriverCreator driverCreator = new DriverCreator(Constants.Page.LOGIN);
        this.driver = driverCreator.getDriver();
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.loginUser(Constants.UserInfo.CORRECT_USERNAME, Constants.UserInfo.CORRECT_PASS);
        this.profilePage = new ProfilePage(driver);
    }

}
