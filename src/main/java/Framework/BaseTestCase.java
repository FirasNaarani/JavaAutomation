package Framework;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTestCase {
    protected WebDriver driver;
    protected GUIHandler guiHandler;
    protected XMLReader xmlReader;

    String xmlFilePath = "Resources.xml";

    // Constructor
    public BaseTestCase(WebDriver driver) {
        this.driver = driver;
        this.guiHandler = new GUIHandler(driver);
        this.xmlReader = new XMLReader(xmlFilePath);
    }

    @BeforeClass
    public void setupClass() {
        System.out.println(this.getClass().getSimpleName() + "\t|\tStart");
    }

    @AfterClass
    public void tearDown() {
        // Close the browser window
        if (driver != null)
        {
            driver.quit();
            driver = null;
        }
        System.out.println(this.getClass().getSimpleName() + "\t|\tDone");
    }

}