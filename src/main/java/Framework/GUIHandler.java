package Framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GUIHandler {
    private final WebDriver driver;

    public GUIHandler(WebDriver driver) {
        this.driver = driver;
    }

    public void openBrowser(String url) {
        driver.get(url);
        waitForPageLoad();
    }

    private void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((org.openqa.selenium.JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    // Method to find WebElement by XPath with wait
    public WebElement findElementByXPath(String xpath) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to find WebElement by ID with wait
    public WebElement findElementById(String id) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to find WebElement by CSS selector with wait
    public WebElement findElementByCssSelector(String cssSelector) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to set text to a WebElement
    public void setText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    // Method to click on a WebElement
    public void clickElement(WebElement element) {
        element.click();
    }
}
