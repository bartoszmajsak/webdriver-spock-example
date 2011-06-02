package pl.bmajsak.github.pages;

import org.openqa.selenium.Keyboard;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class ProjectPage {

    private static final String BASE_URL = "https://github.com/";
    
    private final EventFiringWebDriver driver;

    public ProjectPage(WebDriver driver, String project) {
        this.driver = new EventFiringWebDriver(driver);
        this.driver.get(BASE_URL + project);
    }
    
    public TreeFinder enableTreeFinder() {
        Keyboard keyboard = driver.getKeyboard();
        keyboard.sendKeys("t");
        return new TreeFinder(driver);
    }
    
}
