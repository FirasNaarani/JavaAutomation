package Tests;

import Framework.BaseTestCase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EX2 extends BaseTestCase {
    String url;
    String navBarId = xmlReader.getValueByName("URL");

    WebElement navBar;

    public EX2() {
        super(getChromeDriverWithMaximizedWindow());
    }

    private static WebDriver getChromeDriverWithMaximizedWindow() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        return new ChromeDriver(options);
    }

    private Boolean areParametersEmpty(String url, String navBar) {
        // Check if any of the parameters is null or empty
        return (url != null) && (navBar != null);
    }

    @BeforeClass
    public void SetUp() {
        this.url = xmlReader.getValueByName("EX2", "URL2");
        this.navBarId = xmlReader.getValueByName("EX2", "NAVBAR_XPATH");
        //Check ENV Params
        Boolean isEmpty = areParametersEmpty(url, navBarId);
        if (!isEmpty)
        {
            Assert.fail("ONE OF THE PARAMS IS NULL OR EMPTY, PLEASE CHECK 'Resources.xml'");
        } else
        {
            try
            {
                //Open BROWSER
                guiHandler.openBrowser(url);
                this.navBar = guiHandler.findElementByXPath(navBarId);
                if (this.navBar == null)
                {
                    Assert.fail("NAVBAR NOT FOUND");
                }
            } catch (Exception e)
            {
                System.out.println("ERROR WILL OPENING BROWSER");
                e.printStackTrace();
            }
        }
    }

    @Test
    //Using FullPath
    public void EX2_v1_1() {
        try
        {
//            ClickElements("Selenium;Table Demo");
            ClickElements("Selenium");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    //Using MainItem & SubItem
    public void EX2_v1_2() {
        try
        {
            ClickElements("Selenium", "Table Demo");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void ClickElements(String fullPath) {
        try
        {
            String[] ListItems = fullPath.split(";");

            if (ListItems.length > 1)
            {
                String mainItem = String.format(xmlReader.getValueByName("EX2","NAVBAR_MAIN_ITEM_XPATH"),ListItems[0]);
                guiHandler.clickElementByXpath(mainItem);

                for (int i = 1; i < ListItems.length; i++)
                {
                    guiHandler.clickElementByXpath(ListItems[i]);
                }
            } else
            {
                guiHandler.clickElementByXpath(fullPath);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void ClickElements(String MainItem, String SubItem) {

    }
}
