import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TestExecution {

    private static StartPage startPage;
    private static WebDriver driver;


    public TestExecution() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setHeadless(false);
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500L));
        startPage = new StartPage(driver);
    }
    @Test
    public void Registration(){
        String existingUserName = "Test";
        String existingUserLastName = "Test";
        String existingUserEmail = "test@test.com";
        String existingUserPassword = "1111";

        startPage.openMainPage();
        startPage.registrationProcess(existingUserName,existingUserLastName,existingUserEmail,existingUserPassword);
        registrationResult();
    }

    public void registrationResult(){
        String currentUrl = driver.getCurrentUrl();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(500L));
        wait.until(ExpectedConditions.urlToBe(currentUrl));
        Assertions.assertEquals("https://dev.kazav.com.ua/", currentUrl);

    }

    @Test
    public void Login(){

        String existingLogin = "test@test.ua";
        String existingUserPassword = "1111";

        startPage.openMainPage();
        startPage.loginProcess(existingLogin,existingUserPassword);
        loginResult();
    }

    public void loginResult(){
        String currentUrl = driver.getCurrentUrl();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1500L));
        wait.until(ExpectedConditions.urlContains(currentUrl));
        Assertions.assertEquals("https://dev.kazav.com.ua/", currentUrl);
    }

    @Test
    public void Search(){

        String text = "Елтон Джон";

        startPage.openMainPage();
        startPage.searchProcess(text);
        searchResult();
    }

    public void searchResult() {
        String expectedURL = "https://dev.kazav.com.ua/search/%D0%95%D0%BB%D1%82%D0%BE%D0%BD%20%D0%94%D0%B6%D0%BE%D0%BD";
        String currentUrl = driver.getCurrentUrl();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1500L));
        wait.until(ExpectedConditions.urlContains(currentUrl));
        Assertions.assertEquals(expectedURL, currentUrl);
    }

    @Test
    public void Quote(){

        startPage.openMainPage();
        startPage.qouteCreation();
        quoteResult();
    }

    public void quoteResult() {
        String expectedURL = "https://dev.kazav.com.ua/access-denied";
        String currentUrl = driver.getCurrentUrl();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1500L));
        wait.until(ExpectedConditions.urlContains(currentUrl));
        Assertions.assertEquals(expectedURL, currentUrl);
    }

    @Test
    public void Motivator(){

        startPage.openMainPage();
        startPage.motivatorCreation();
        motivatorResult();
    }

    public void motivatorResult() {
        String expectedURL = "https://dev.kazav.com.ua/motivator/create";
        String currentUrl = driver.getCurrentUrl();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1500L));
        wait.until(ExpectedConditions.urlContains(currentUrl));
        Assertions.assertEquals(expectedURL, currentUrl);
    }
    @Test
    public void Fresh(){

        startPage.openMainPage();
        startPage.freshClick();
        freshRedirect();
    }

    public void freshRedirect(){
        String expectedURL = "https://dev.kazav.com.ua/fresh";
        String currentUrl = driver.getCurrentUrl();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(1500L));
        wait.until(ExpectedConditions.urlContains(currentUrl));
        Assertions.assertEquals(expectedURL, currentUrl);
    }

    @Test
    public void dropDownAboutUsResult(){

        startPage.openMainPage();
        startPage.dropDownAboutUsClick();
        dropDownList();
    }

    public void dropDownList(){
        int expectedListElements = 4;
        List<WebElement> dropDownElements = driver.findElements(By.className("dropdown-item"));
        Assertions.assertEquals(expectedListElements, dropDownElements.size());
    }

    @Test
    public void redirectAfterClickAboutProject(){

        startPage.openMainPage();
        startPage.dropDownAboutUsClick();
        startPage.aboutProjectChecking();
        checkOfRedirectionAfterClickAboutProject();
    }

    public void checkOfRedirectionAfterClickAboutProject(){
        String expectedURL = "https://dev.kazav.com.ua/about/project";
        String currentUrl = driver.getCurrentUrl();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(100L));
        wait.until(ExpectedConditions.urlContains(currentUrl));
        Assertions.assertEquals(expectedURL, currentUrl);

    }

    @AfterEach
    public void CloseDriver(){
        driver.close();
        driver.quit();
    }
}

