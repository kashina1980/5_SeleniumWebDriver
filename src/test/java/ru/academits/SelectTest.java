package ru.academits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp(){
        String browser = System.getProperty("browser");

        if (browser.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equals("edge")){
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.get("http://htmlbook.ru/html/select");
        driver.manage().window().maximize();

    }


    @Test
    public void selectTest() throws InterruptedException {

        WebElement elementDropdown = driver.findElement(By.name("select2"));
        Select select = new Select(elementDropdown);

        select.selectByIndex(3);
        Assertions.assertEquals("Шапокляк", elementDropdown.getAttribute("value"));

        Thread.sleep(2000);
    }

    @Test
    public void selectTestOption() throws InterruptedException {

        WebElement elementDropdown = driver.findElement(By.name("select2"));
        Select select = new Select(elementDropdown);

        List<WebElement> allOption = select.getOptions();
        Assertions.assertEquals("Шапокляк", allOption.get(3).getText());

        Thread.sleep(2000);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
