package webappdev;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Login {

    private static final String ROOT_URL = "http://localhost:8080/LoginPage";
    private final WebDriver driver = new ChromeDriver();

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        driver.get(ROOT_URL);
    }

    @When("I enter the username {string}")
    public void iEnterTheUsername(String username) {
        WebElement usernameInput = driver.findElement(By.xpath("//*[@id=\"username\"]"));
        usernameInput.sendKeys(username);
    }

    @And("I enter the password {string}")
    public void iEnterThePassword(String password) {
        WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"password\"]"));
        passwordInput.sendKeys(password);
    }

    @And("I press the Login button")
    public void iPressTheLoginButton() {
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/button"));
        loginButton.click();
    }

    @Then("I should get a {string} message")
    public void iShouldGetAMessage(String expectedResult) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement loginResponse = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/p")));
        assertEquals(expectedResult, loginResponse.getText());
    }

    @And("I have tried unsuccessfully to log in the two previous attempts")
    public void iHaveTriedUnsuccessfullyToLogInTheTwoPreviousAttempts() {
        // attempt 1
        iPressTheLoginButton();

        // attempt 2
        iPressTheLoginButton();
    }

    @Then("I should be redirected to the Account Blocked page")
    public void iShouldBeRedirectedToTheAccountBlockedPage() {
        String url = driver.getCurrentUrl();
        assertEquals(url, "http://localhost:8080/AccountBlockedPage");
    }

    @And("I have tried unsuccessfully to log in on the previous attempt")
    public void iHaveTriedUnsuccessfullyToLogInOnThePreviousAttempt() {
        // attempt 1
        iPressTheLoginButton();
    }

    @After
    public void after() {
        driver.quit();
    }
}
