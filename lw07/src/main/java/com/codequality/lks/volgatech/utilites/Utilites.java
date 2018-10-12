package com.codequality.lks.volgatech.utilites;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utilites {

    public static void elementClickWhichFoundByXPath(String xpath, WebDriver driver) {
        driver.findElement(By.xpath(xpath)).click();
    }

    public static void elementClickWhichFoundByLinkText(String linkText, WebDriver driver) {
        driver.findElement(By.linkText(linkText)).click();
    }

    public static String getElementTextWhichFoundByXPath(String xpath, WebDriver driver) {
        return driver.findElement(By.xpath(xpath)).getText();
    }

    public static String getFormattedDate(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return Integer.toString(day) + "." + Integer.toString(month) + "." + Integer.toString(year);
    }

    public static Date getNextWeekFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        return calendar.getTime();
    }

}
