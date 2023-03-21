
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
    public void demoQATest() {
        String nameFirst = "TestFirstName";
        String nameLast = "TestLastName";
        String mail = "testmail@test.com";
        String phoneMobile = "0123456789";
        String subjectSelect = "Computer";
        String adressCurrent = "Current Location";
        String state = "NCR";
        String city = "Gurgaon";

        WebElement firstName = driver.findElement(By.xpath("//*[@id=\"firstName\"]"));
        firstName.sendKeys(nameFirst);

        WebElement lastName = driver.findElement(By.xpath("//*[@id=\"lastName\"]"));
        lastName.sendKeys(nameLast);

        WebElement eMail = driver.findElement(By.xpath("//*[@id=\"userEmail\"]"));
        eMail.sendKeys(mail);

        WebElement gender = driver.findElement(By.xpath("//label[contains(@class,'custom-control-label')]"));
        gender.click();

        WebElement mobilePhone = driver.findElement(By.xpath("//*[@id=\"userNumber\"]"));
        mobilePhone.sendKeys(phoneMobile);

        WebElement openCalendar = driver.findElement(By.xpath("//*[@id=\"dateOfBirthInput\"]"));
        openCalendar.click();
        WebElement openMonth = driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]//select[@class='react-datepicker__month-select']"));
        openMonth.click();
        WebElement selectMonth = driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]//select[@class='react-datepicker__month-select']/option[2]"));
        selectMonth.click();
        WebElement openYear = driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]//select[@class='react-datepicker__year-select']"));
        openYear.click();
        WebElement selectYear = driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]//select[@class='react-datepicker__year-select']/option[91]"));
        selectYear.click();
        WebElement day = driver.findElement(By.xpath("//div[text()='10']"));
        day.click();

        WebElement subject = driver.findElement(By.xpath("//*[@id=\"subjectsInput\"]"));
        subject.sendKeys(subjectSelect);
        subject.sendKeys(Keys.RETURN);

        WebElement hobbies = driver.findElement(By.xpath("//div[@class='custom-control custom-checkbox custom-control-inline']/label[1]"));
        hobbies.click();

        File picture = new File("src/test/altai.jpg");
        WebElement fileInput = driver.findElement(By.xpath("//*[@id=\"uploadPicture\"]"));
        fileInput.sendKeys(picture.getAbsolutePath());

        WebElement currentAdress = driver.findElement(By.xpath("//*[@id=\"currentAddress\"]"));
        currentAdress.sendKeys(adressCurrent);


        WebElement selectState = driver.findElement(By.xpath("//*[@id=\"react-select-3-input\"]"));
        selectState.sendKeys(state);
        selectState.sendKeys(Keys.ENTER);

        WebElement selectCity = driver.findElement(By.xpath("//*[@id=\"react-select-4-input\"]"));
        selectCity.sendKeys(city);
        selectCity.sendKeys(Keys.ENTER);

        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"submit\"]"));
        submitButton.click();


        WebDriverWait wait = new WebDriverWait(driver, 30, 500);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']")));

        String checkFirstName = driver.findElement(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']//tr[1]/td[2]")).getText();
        Assertions.assertTrue(checkFirstName.contains(nameFirst));

        String checkLastName = driver.findElement(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']//tr[1]/td[2]")).getText();
        Assertions.assertTrue(checkLastName.contains(nameLast));

        String checkEmail = driver.findElement(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']//tr[2]/td[2]")).getText();
        Assertions.assertTrue(checkEmail.contains(mail));

        String checkGender = driver.findElement(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']//tr[3]/td[2]")).getText();
        Assertions.assertTrue(checkGender.contains("Male"));

        String checkTelephoneNumber = driver.findElement(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']//tr[4]/td[2]")).getText();
        Assertions.assertTrue(checkTelephoneNumber.contains(phoneMobile));

        String checkBirthDate = driver.findElement(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']//tr[5]/td[2]")).getText();
        Assertions.assertTrue(checkBirthDate.contains("10 February,1990"));

        String checkSubject = driver.findElement(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']//tr[6]/td[2]")).getText();
        Assertions.assertTrue(checkSubject.contains(subjectSelect));

        String checkHobbies = driver.findElement(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']//tr[7]/td[2]")).getText();
        Assertions.assertTrue(checkHobbies.contains("Sports"));

        String checkPicture = driver.findElement(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']//tr[8]/td[2]")).getText();
        Assertions.assertTrue(checkPicture.contains("altai.jpg"));

        String checkAdress = driver.findElement(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']//tr[9]/td[2]")).getText();
        Assertions.assertTrue(checkAdress.contains(adressCurrent));

        String checkState = driver.findElement(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']//tr[10]/td[2]")).getText();
        Assertions.assertTrue(checkState.contains(state));

        String checkCity = driver.findElement(By.xpath("//table[@class='table table-dark table-striped table-bordered table-hover']//tr[10]/td[2]")).getText();
        Assertions.assertTrue(checkCity.contains(city));
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}
