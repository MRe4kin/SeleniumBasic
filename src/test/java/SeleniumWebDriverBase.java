
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SeleniumWebDriverBase {
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {

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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    @Order(1)
    public void firstNameTest() {
        WebElement titleElement = driver.findElement(By.xpath("//*[@id=\"firstName\"]"));
        titleElement.sendKeys("TestFirstName");
    }

    @Test
    @Order(2)
    public void lastNameTest() {
        WebElement titleElement = driver.findElement(By.xpath("//*[@id=\"lastName\"]"));
        titleElement.sendKeys("TestLastName");
    }

    @Test
    @Order(3)
    public void emailTest() {
        WebElement titleElement = driver.findElement(By.xpath("//*[@id=\"userEmail\"]"));
        titleElement.sendKeys("testmail@test.com");
    }

    @Test
    @Order(4)
    public void genderTest() {
        WebElement titleElement = driver.findElement(By.xpath("//label[contains(@class,'custom-control-label')]"));
        titleElement.click();
    }

    @Test
    @Order(5)
    public void telephoneNumberTest() {
        WebElement titleElement = driver.findElement(By.xpath("//*[@id=\"userNumber\"]"));
        titleElement.sendKeys("0123456789");
    }

    @Test
    @Order(6)
    public void birthDateTest() {
        WebElement openCalendar = driver.findElement(By.xpath("//*[@id=\"dateOfBirthInput\"]"));
        openCalendar.click();
        WebElement year = driver.findElement(By.xpath("//select[@class='react-datepicker__year-select']"));
        Select selectYear = new Select(year);
        selectYear.selectByValue("1990");
        WebElement month = driver.findElement(By.xpath("//select[@class='react-datepicker__month-select']"));
        Select selectMonth = new Select(month);
        selectMonth.selectByValue("1");
        WebElement day = driver.findElement(By.xpath("//div[text()='10']"));
        day.click();
    }

    @Test
    @Order(7)
    public void subjectsTest() {
        WebElement titleElement = driver.findElement(By.xpath("//*[@id=\"subjectsInput\"]"));
        titleElement.sendKeys("Computer");
        titleElement.sendKeys(Keys.RETURN);
    }

    @Test
    @Order(8)
    public void hobbiesTest() {
        WebElement titleElement = driver.findElement(By.xpath("//div[@class='custom-control custom-checkbox custom-control-inline']/label[1]"));
        titleElement.click();
    }

    @Test
    @Order(9)
    public void pictureTest() {
        File f = new File("src/test/altai.jpg");
        WebElement fileInput = driver.findElement(By.xpath("//*[@id=\"uploadPicture\"]"));
        fileInput.sendKeys(f.getAbsolutePath());
    }

    @Test
    @Order(10)
    public void currentAdressTest() {
        WebElement titleElement = driver.findElement(By.xpath("//*[@id=\"currentAddress\"]"));
        titleElement.sendKeys("Current Location");
    }

    @Test
    @Order(11)
    public void selectStateTest() {
        WebElement openState = driver.findElement(By.xpath("//*[@id=\"state\"]"));
        openState.click();
        WebElement selectState = driver.findElement(By.xpath("//div[text()='NCR']"));
        selectState.click();

        WebElement openCity = driver.findElement(By.xpath("//*[@id=\"city\"]"));
        openCity.click();
        WebElement selectCity = driver.findElement(By.xpath("//div[text()='Gurgaon']"));
        selectCity.click();
    }

    @Test
    @Order(12)
    public void submitTest() {
        WebElement titleElement = driver.findElement(By.xpath("//*[@id=\"submit\"]"));
        titleElement.click();
    }


    @Test
    @Order(13)
    public void assertsCheckTest() {

        WebDriverWait wait = new WebDriverWait(driver, 30, 500);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']")));

        String checkFirstName = driver.findElement(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']//tr[1]/td[2]")).getText();
        Assertions.assertTrue(checkFirstName.contains("TestFirstName"));

        String checkLastName = driver.findElement(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']//tr[1]/td[2]")).getText();
        Assertions.assertTrue(checkLastName.contains("TestLastName"));

        String checkEmail = driver.findElement(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']//tr[2]/td[2]")).getText();
        Assertions.assertTrue(checkEmail.contains("testmail@test.com"));

        String checkGender = driver.findElement(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']//tr[3]/td[2]")).getText();
        Assertions.assertTrue(checkGender.contains("Male"));

        String checkTelephoneNumber = driver.findElement(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']//tr[4]/td[2]")).getText();
        Assertions.assertTrue(checkTelephoneNumber.contains("0123456789"));

        String checkBirthDate = driver.findElement(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']//tr[5]/td[2]")).getText();
        Assertions.assertTrue(checkBirthDate.contains("10 February,1990"));

        String checkSubject = driver.findElement(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']//tr[6]/td[2]")).getText();
        Assertions.assertTrue(checkSubject.contains("Computer"));

        String checkHobbies = driver.findElement(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']//tr[7]/td[2]")).getText();
        Assertions.assertTrue(checkHobbies.contains("Sports"));

        String checkPicture = driver.findElement(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']//tr[8]/td[2]")).getText();
        Assertions.assertTrue(checkPicture.contains("altai.jpg"));

        String checkAdress = driver.findElement(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']//tr[9]/td[2]")).getText();
        Assertions.assertTrue(checkAdress.contains("Current Location"));

        String checkState = driver.findElement(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']//tr[10]/td[2]")).getText();
        Assertions.assertTrue(checkState.contains("NCR"));

        String checkCity = driver.findElement(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']//tr[10]/td[2]")).getText();
        Assertions.assertTrue(checkCity.contains("Gurgaon"));
    }

    @AfterAll
    public static void tearDown() {

        driver.quit();
    }


}
