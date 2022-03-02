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

public class DropDownTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        String browser = System.getProperty("browser");

        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();

    }

    @Test
    public void DropDownTestSelect() throws InterruptedException {
        driver.findElement(By.xpath("//a[text()='Dropdown']")).click();

        WebElement elementDropdown = driver.findElement(By.id("dropdown"));
        Select select = new Select(elementDropdown);
        select.selectByIndex(2);

        List<WebElement> allOption = select.getOptions();
        Assertions.assertEquals("Option 2", allOption.get(2).getText());
        Thread.sleep(3000);
    }

    @Test
    public void DropDownTestClick() throws InterruptedException {
        driver.findElement(By.xpath("//a[text()='Dropdown']")).click();
        driver.findElement(By.xpath("//*[@id='dropdown']/option[text()='Option 2']")).click();

        WebElement elementDropdown = driver.findElement(By.id("dropdown"));
        Select select = new Select(elementDropdown);

        List<WebElement> allOption = select.getOptions();
        Assertions.assertEquals("Option 2", allOption.get(2).getText());
        Thread.sleep(3000);

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
