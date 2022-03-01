package ru.academits;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumLocalDriverTests {

    @Test
    public void openPageInChromeTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.quit();
    }
    @Test
    public void openPageInFirefoxTest() throws InterruptedException {
        System.setProperty("webdriver.gecko.driver","C:\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.quit();
    }
    @Test
    public void openPageWithParameterTest() throws InterruptedException {
        WebDriver driver = null;

        String browser = System.getProperty("browser");
        if (browser.equals("chrome")){
            System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
           driver = new ChromeDriver();
        } else if (browser.equals("firefox")){
            System.setProperty("webdriver.gecko.driver","C:\\geckodriver.exe");
            driver = new FirefoxDriver();
        }


        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        driver.quit();

    }
}
