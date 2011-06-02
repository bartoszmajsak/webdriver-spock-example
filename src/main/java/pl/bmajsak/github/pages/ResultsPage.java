package pl.bmajsak.github.pages;

import static org.fest.assertions.Assertions.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public class ResultsPage {

    private static final String PROJECT_RESULTS = "//div[@id='code_search_results']//div[@class='result']/*[@class='title']/a";
    
    private final WebDriver driver;
    
    public ResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void containsProject(String projectName) {
        List<WebElement> foundProjects = driver.findElements(By.xpath(PROJECT_RESULTS));
        List<String> projectsTitles = Lists.transform(foundProjects, new ExtractProjectName());
        assertThat(projectsTitles).contains(projectName);
    }
    
    private final class ExtractProjectName implements Function<WebElement, String> {
        public String apply(WebElement from) {
            String text = from.getText().trim();
            return text.substring(text.lastIndexOf('/') + 1).trim();
        }
    }
    
}
