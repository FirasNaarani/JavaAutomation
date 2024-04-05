package Framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GUIHandler {
    private final WebDriver driver;
    private Duration timeOut;

    public GUIHandler(WebDriver driver) {
        this.driver = driver;
        this.timeOut = Duration.ofSeconds(30);
    }

    public void openBrowser(String url) {
        driver.get(url);
        waitForPageLoad();
    }

    private void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((org.openqa.selenium.JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    // Method to find List<WebElement> by XPath with wait
    public List<WebElement> findElementsByXPath(String xpath) {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            return wait.until((ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath))));
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public WebElement getElementByIndex(List<WebElement> list, int index) {
        return list.get(index);
    }

    // Method to find WebElement by XPath with wait
    public WebElement findElementByXPath(String xpath) {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    // Method to find WebElement by ID with wait
    public WebElement findElementById(String id) {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    // Method to find WebElement by CSS selector with wait
    public WebElement findElementByCssSelector(String cssSelector) {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    // Method to count of elements
    public int countVisibleElementsByXPath(String xpath) {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
            return elements.size();
        } catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    // Method to click on a WebElement
    public void clickElementByXpath(String xpath) {
        try
        {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(xpath)));

            element.click();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void ClickItem(WebElement element, String item){
        try{
            String[] ListItems = item.split(";");
            if(ListItems.length > 1){
                clickElementByXpath(ListItems[0]);

                for(int i =1;i<ListItems.length;i++){
                    clickElementByXpath(ListItems[i]);
                }
            }
            else {
                clickElementByXpath(item);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
