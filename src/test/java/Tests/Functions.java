package Tests;

import Framework.BaseTestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Functions extends BaseTestCase {


    public Functions() {
        super(new ChromeDriver()); // Instantiate WebDriver using ChromeDriver
    }

    @Test
    public void Ex1() {

        String url = xmlReader.getValueByName("EX","EX","URL");
        guiHandler.openBrowser(url);
//        guiHandler.openBrowser("http://www.w3schools.com/html/html_tables.asp");
        WebElement table = guiHandler.findElementByXPath("//table[@id='customers']");
        if (table != null)
        {
            System.out.println("Table Found");
        } else
            System.out.println("Not Found");
//        //table[@id='customers']/child::tbody/tr
//        getTableCellText()
    }

    public String getTableCellText(WebElement table, int searchColumn, String searchText, int returnColumnText) {
        return null;
    }

    public boolean verifyTableCellText(WebElement table, int searchColumn, String searchText, int returnColumnText, String expectedText) {
        String check = getTableCellText(table,searchColumn,searchText,returnColumnText);
        return check.equals(expectedText);
    }

    @Test
    public void testGoogleSearch() {
        // Open Google homepage
        driver.get("https://www.google.com");

        // Perform a search
        driver.findElement(By.name("q")).sendKeys("Selenium");

        // Submit the search query
        driver.findElement(By.name("btnK")).submit();

        // Wait for search results to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("Selenium"));

        // Verify search results
        Assert.assertTrue(driver.getTitle().contains("Selenium"));
    }

    @Test
    public void toto(){
        String x = xmlReader.getValueByName("EX","EX","URL");
        System.out.println(x);
    }
}
