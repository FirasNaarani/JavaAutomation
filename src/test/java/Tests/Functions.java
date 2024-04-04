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
import java.util.List;

public class Functions extends BaseTestCase {


    public Functions() {
        super(new ChromeDriver()); // Instantiate WebDriver using ChromeDriver
    }

    public Boolean areParametersEmpty(String url, String tableId, Integer searchColumn, String searchText, Integer returnColumnText) {
        // Check if any of the parameters is null or empty
        if ((url != null) &&
                (tableId != null) &&
                (searchColumn > 0) &&
                (searchText != null) &&
                (returnColumnText != searchColumn && returnColumnText != 0))
        {
            return true;
        }
        return false;
    }

    @Test
    public void Ex1() {

        // ENV Params
        String url = xmlReader.getValueByName("URL");
        String tableId = xmlReader.getValueByName("TABLE_XPATH");
        int searchColumn = Integer.parseInt(xmlReader.getValueByName("SEARCH_COLUMN"));
        String searchText = xmlReader.getValueByName("SEARCH_TEXT");
        int returnColumnText = Integer.parseInt(xmlReader.getValueByName("RETURN_COLUMN_TEXT"));

        Boolean isEmpty = areParametersEmpty(url, tableId, searchColumn, searchText, returnColumnText);
        if (!isEmpty)
        {
            System.out.println("ONE OF THE PARAMS IS NULL OR EMPTY, PLEASE CHECK 'Resources.xml'");
        } else
        {
            try
            {
                guiHandler.openBrowser(url);
                WebElement table = guiHandler.findElementByXPath(tableId);
                if (table != null)
                {
                    System.out.println("Table Found");
                    String Result = getTableCellText(table,searchColumn,searchText,returnColumnText);
                    System.out.println("RESULT =\t" + Result);
                } else
                    System.out.println("Not Found");
//        //table[@id='customers']/child::tbody/tr
//        getTableCellText()
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public String getTableCellText(WebElement table, int searchColumn, String searchText, int returnColumnText) {
        try
        {
            if (table != null)
            {
                System.out.println("Table Found");
                String tableHeaderId = xmlReader.getValueByName("TABLE_HEADER_XPATH");
                String tableCellsId = xmlReader.getValueByName("TABLE_CELLS_IN_ROWS_XPATH");

                //WebElement tableHeader = guiHandler.findElementByXPath(tableHeaderId);

                int numberOfColumns = guiHandler.countVisibleElementsByXPath(tableHeaderId); //3
                int numberOfCells = guiHandler.countVisibleElementsByXPath(tableCellsId); //18

                if (searchColumn <= numberOfColumns && returnColumnText <= numberOfColumns && numberOfCells != 0)
                {
                    List<WebElement> tableCells = guiHandler.findElementsByXPath(tableCellsId);


                    WebElement x = guiHandler.getElementByIndex(tableCells, 0);
                    System.out.println(x.getText());

                } else
                {
                    System.out.println("THE SEARCH COLUMN OUT OF THE CURRENT TABLE");
                }
            } else
                System.out.println("Not Found");
//        //table[@id='customers']/child::tbody/tr
//        getTableCellText()
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public boolean verifyTableCellText(WebElement table, int searchColumn, String searchText, int returnColumnText, String expectedText) {
        String check = getTableCellText(table, searchColumn, searchText, returnColumnText);
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
    public void toto() {
        String x = xmlReader.getValueByName("EX", "EX", "URL");
        System.out.println(x);
    }
}
