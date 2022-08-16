import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StartPage {

    private final WebDriver driver;
    WebDriverWait wait;
    By buttonLogin = By.xpath("//a[@href='/user/login']");
    By buttonRegistration = By.xpath("//a[@href='/user/registration']");
    By buttonQuote = By.xpath("//a[@href='/quote/create']");
    By buttonMotivator = By.xpath("//a[@href='/motivator/create']");
    By buttonFacebook = By.xpath("//i[@class='bi-facebook']");
    By buttonInstagram = By.xpath("//i[@class='bi-instagram']");
    By buttonTwitter = By.xpath("//i[@class='bi-twitter']");
    By fieldFirstNameInRegistration= By.id("first_name");
    By fieldLastNameInRegistration= By.id("last_name");
    By fieldEmailInRegistration= By.id("email");
    By fieldPasswordInRegistration= By.id("password");
    By buttonRegistrationInRegistration = By.xpath("//button[@class='btn btn-primary me-2']");
    By fieldLogin = By.id("login");
    By fieldPassword = By.id("password");
    By searchField = By.xpath("//input[@class='form-control me-2 ng-touched ng-dirty ng-invalid']");
    By searchButton = By.xpath("//button[@class='btn btn-outline-success']");
    By bookmarkFresh = By.xpath("//a[@href='/fresh' and text() ='Почекальня']");
    By dropDownAboutUs = By.id("navbarDropdown");
    //By aboutProjectLink = By.xpath("//a[@href='/about/project' and text() ='Про проєкт']");
    By aboutProjectLink = By.xpath("//ul[@class='dropdown-menu show']//descendant::a[text()='Про проєкт']");

    public StartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofMillis(1500L));
    }

    public void openMainPage() {
        driver.get("https://dev.kazav.com.ua/");
    }

    public void registrationProcess(String existingUserName, String existingUserLastName, String existingUserEmail, String existingUserPassword){

        wait.until(ExpectedConditions.presenceOfElementLocated(buttonRegistration)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(fieldFirstNameInRegistration)).sendKeys(existingUserName);
        wait.until(ExpectedConditions.presenceOfElementLocated(fieldLastNameInRegistration)).sendKeys(existingUserLastName);
        wait.until(ExpectedConditions.presenceOfElementLocated(fieldEmailInRegistration)).sendKeys(existingUserEmail);
        wait.until(ExpectedConditions.presenceOfElementLocated(fieldPasswordInRegistration)).sendKeys(existingUserPassword);
        wait.until(ExpectedConditions.elementToBeClickable(buttonRegistrationInRegistration)).click();
    }

    public void loginProcess(String existingLogin,String existingPassword){
        wait.until(ExpectedConditions.presenceOfElementLocated(buttonLogin)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(fieldLogin)).sendKeys(existingLogin);
        wait.until(ExpectedConditions.presenceOfElementLocated(fieldPassword)).sendKeys(existingPassword);
        wait.until(ExpectedConditions.elementToBeClickable(buttonRegistrationInRegistration)).click();
    }

    public void searchProcess(String text){
        wait.until(ExpectedConditions.presenceOfElementLocated(searchField)).clear();
        wait.until(ExpectedConditions.presenceOfElementLocated(searchField)).sendKeys(text);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void qouteCreation() {

        wait.until(ExpectedConditions.presenceOfElementLocated(buttonQuote)).click();

    }

    public void motivatorCreation() {

        wait.until(ExpectedConditions.presenceOfElementLocated(buttonMotivator)).click();

    }
    public void freshClick() {
        wait.until(ExpectedConditions.presenceOfElementLocated(bookmarkFresh)).click();

    }
    public void dropDownAboutUsClick() {
        wait.until(ExpectedConditions.presenceOfElementLocated(dropDownAboutUs)).click();

    }
    public void aboutProjectChecking() {
        wait.until(ExpectedConditions.elementToBeClickable(dropDownAboutUs)).click();
        //WebElement aboutProjectElement = driver.findElement(aboutProjectLink);
        //Select selectElementAboutProject = new Select(aboutProjectElement);
        //selectElementAboutProject.selectByVisibleText("Про проєкт");
        //selectElementAboutProject.selectByIndex(1);
        // selectElementAboutProject.getFirstSelectedOption().click();
        driver.switchTo().frame(driver.findElement(By.xpath(("//*[@class = 'dropdown-menu show']"))));
        wait.until(ExpectedConditions.elementToBeClickable(aboutProjectLink)).click();
        //List<WebElement> dropDownElements = driver.findElements(By.className("dropdown-item"));
        //dropDownElements.get(0).click();
    }

}

