package ui.tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static utils.EnvProperties.BASE_URL;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        Configuration.baseUrl = BASE_URL;
    }

    @AfterMethod(alwaysRun = true)
    public void cleanup() {
        closeWebDriver();
    }
}