package pl.bmajsak.github.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

    private static final String URL = "https://www.github.com";

    private final WebDriver driver;
    
    @FindBy(xpath = "//form[@id='top_search_form']/p/input[@class='search' and @name='q']")
    private WebElement searchBox;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get(URL);
        PageFactory.initElements(driver, this);
    }
    
    public ResultsPage searchForProject(String projectName) {
        searchBox.sendKeys(projectName);
        searchBox.submit();
        return PageFactory.initElements(driver, ResultsPage.class);
    }
    
    
}
