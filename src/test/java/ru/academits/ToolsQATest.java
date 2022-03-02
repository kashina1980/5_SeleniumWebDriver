package ru.academits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class ToolsQATest {
    private WebDriver driver;
    private File file;

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
        driver.get("https://demoqa.com/automation-practice-form");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void АuthenticationFormsTest() throws InterruptedException {

        String firstName = "Name";
        String lastName = "LastName";
        String email = "name@mail.ru";
        String gender = "Male";
        String mobile = "1234567890";
        String dateBirthday = "21 January,2000";
        String currentAdress = "Russia Moscow";
        String state = "Haryana";
        String city = "Karnal";
        String subject = "Maths";
        file = new File("src/img.jpg");

        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("userEmail")).sendKeys(email);

        WebElement maleRadioButton = driver.findElement(By.cssSelector("div .custom-control.custom-radio.custom-control-inline:nth-child(1)"));
        WebElement famaleRadioButton = driver.findElement(By.cssSelector("div .custom-control.custom-radio.custom-control-inline:nth-child(2)"));
        WebElement otherRadioButton = driver.findElement(By.cssSelector("div .custom-control.custom-radio.custom-control-inline:nth-child(3)"));
        maleRadioButton.click();
        Assertions.assertFalse(famaleRadioButton.isSelected());
        Assertions.assertFalse(otherRadioButton.isSelected());

        driver.findElement(By.id("userNumber")).sendKeys(mobile);

        driver.findElement(By.id("dateOfBirthInput")).click();
        driver.findElement(By.cssSelector(".react-datepicker__month-select [value='0']")).click();
        driver.findElement(By.cssSelector(".react-datepicker__year-select [value='2000']")).click();
        driver.findElement(By.cssSelector("div[aria-label='Choose Friday, January 21st, 2000']")).click();

        WebElement subjectAutocomplete = driver.findElement(By.id("subjectsInput"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", subjectAutocomplete);
        subjectAutocomplete.sendKeys(subject);
        subjectAutocomplete.sendKeys(Keys.ENTER);

        WebElement sportCheckBox = driver.findElement(By.cssSelector("div.custom-control.custom-checkbox.custom-control-inline:nth-child(1)"));
        WebElement readingCheckBox = driver.findElement(By.cssSelector("div.custom-control.custom-checkbox.custom-control-inline:nth-child(2)"));
        WebElement musicCheckBox = driver.findElement(By.cssSelector("div.custom-control.custom-checkbox.custom-control-inline:nth-child(3)"));
        sportCheckBox.click();
        readingCheckBox.click();
        Assertions.assertFalse(musicCheckBox.isSelected());

        WebElement selectPictureButton = driver.findElement(By.id("uploadPicture"));
        selectPictureButton.sendKeys(file.getAbsolutePath());

        driver.findElement(By.cssSelector("div textarea")).sendKeys(currentAdress);

        WebElement stateAutocomplete = driver.findElement(By.id("react-select-3-input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", stateAutocomplete);
        stateAutocomplete.sendKeys(state);
        stateAutocomplete.sendKeys(Keys.ENTER);

        WebElement cityAutocomplete = driver.findElement(By.id("react-select-4-input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cityAutocomplete);
        cityAutocomplete.sendKeys(city);
        cityAutocomplete.sendKeys(Keys.ENTER);

        WebElement button = driver.findElement(By.id("submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        button.sendKeys(Keys.ENTER);

        String successfulMessage = driver.findElement(By.id("example-modal-sizes-title-lg")).getText();
        Assertions.assertEquals("Thanks for submitting the form", successfulMessage);

        WebElement StudentName = driver.findElement(By.xpath("//div[@class='modal-body']//table/tbody/tr[1]/td[2]"));
        WebElement StudentEmail = driver.findElement(By.xpath("//div[@class='modal-body']//table/tbody/tr[2]/td[2]"));
        WebElement StudentGender = driver.findElement(By.xpath("//div[@class='modal-body']//table/tbody/tr[3]/td[2]"));
        WebElement StudentMobile = driver.findElement(By.xpath("//div[@class='modal-body']//table/tbody/tr[4]/td[2]"));
        WebElement StudentDateBirth = driver.findElement(By.xpath("//div[@class='modal-body']//table/tbody/tr[5]/td[2]"));
        WebElement StudentSubjects = driver.findElement(By.xpath("//div[@class='modal-body']//table/tbody/tr[6]/td[2]"));
        WebElement StudentHobbies = driver.findElement(By.xpath("//div[@class='modal-body']//table/tbody/tr[7]/td[2]"));
        WebElement StudentPicture = driver.findElement(By.xpath("//div[@class='modal-body']//table/tbody/tr[8]/td[2]"));
        WebElement StudentAdress = driver.findElement(By.xpath("//div[@class='modal-body']//table/tbody/tr[9]/td[2]"));
        WebElement StudentStateAndCity = driver.findElement(By.xpath("//div[@class='modal-body']//table/tbody/tr[10]/td[2]"));

        Assertions.assertEquals(firstName + " " + lastName, StudentName.getText());
        Assertions.assertEquals(email, StudentEmail.getText());
        Assertions.assertEquals(gender, StudentGender.getText());
        Assertions.assertEquals(mobile, StudentMobile.getText());
        Assertions.assertEquals(dateBirthday, StudentDateBirth.getText());
        Assertions.assertEquals(subject, StudentSubjects.getText());
        Assertions.assertEquals("Sports, Reading", StudentHobbies.getText());
        Assertions.assertEquals("img.jpg", StudentPicture.getText());
        Assertions.assertEquals(state + " " + city, StudentStateAndCity.getText());

        Thread.sleep(5000);
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
