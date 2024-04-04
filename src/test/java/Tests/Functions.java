package Tests;

import Framework.BaseTestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.List;

public class Functions extends BaseTestCase {

    public Functions() {
        super(getChromeDriverWithMaximizedWindow());
    }

    private static WebDriver getChromeDriverWithMaximizedWindow() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        return new ChromeDriver(options);
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
                    String Result = getTableCellText(table, searchColumn, searchText, returnColumnText);
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

    public String getTableCellText(WebElement table, String searchColumn, String searchText, int returnColumnText) {
        int sc;
        switch (searchColumn)
        {
            case "Company":
                sc = 1;
                break;
            case "Contact":
                sc = 2;
                break;
            case "Country":
                sc = 3;
                break;
            default:
                return null;
        }
        if(sc == returnColumnText){
            Assert.fail("Can't Be 'Search Column' and 'Return Column' Same Value");
        }
        return getTableCellText(table, sc, searchText, returnColumnText);
    }

    public String getTableCellText(WebElement table, int searchColumn, String searchText, int returnColumnText) {
        try
        {
            if (table != null)
            {
                System.out.println("Table Found");
                String tableHeaderId = xmlReader.getValueByName("TABLE_HEADER_XPATH");
                String tableCellsId = xmlReader.getValueByName("TABLE_CELLS_IN_ROWS_XPATH");

                int numberOfColumns = guiHandler.countVisibleElementsByXPath(tableHeaderId); //3
                int numberOfCells = guiHandler.countVisibleElementsByXPath(tableCellsId); //18
                //TODO check the count

                if (searchColumn <= numberOfColumns && returnColumnText <= numberOfColumns && numberOfCells != 0)
                {
                    String searchTextId = xmlReader.getValueByName("SEARCH_TEXT_XPATH");
                    String xPathString = String.format(searchTextId, searchText);
                    System.out.println(xPathString);

                    WebElement cell = guiHandler.findElementByXPath(xPathString);
                    if (cell != null)
                    {
                        Assert.fail(String.format("Search Text = '%s', Wasn't Found in the Table", searchText));
                    } else
                    {
                        List<WebElement> tableCells = guiHandler.findElementsByXPath(tableCellsId);

                        int index = tableCells.indexOf(cell);
                        int num_row = (index / numberOfColumns) + 1;
                        System.out.println(index);

//                        WebElement x = guiHandler.getElementByIndex(tableCells, 0);
//                        System.out.println(x.getText());

                    }
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
    public void toto() {
        String searchTextId = xmlReader.getValueByName("SEARCH_TEXT_XPATH");
        String searchText = xmlReader.getValueByName("SEARCH_TEXT");

        String xPathString = String.format(searchTextId, searchText);
        System.out.println(xPathString);
    }
}
