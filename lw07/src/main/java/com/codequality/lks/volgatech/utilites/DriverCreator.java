package com.codequality.lks.volgatech.utilites;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverCreator {

    private WebDriver driver;

    public DriverCreator(String url) {
        System.setProperty("webdriver.chrome.driver", "/code-quality/lw07/chromedriver/chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.get(url);
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}
