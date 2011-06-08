package pl.bmajsak.github.spec

import org.openqa.selenium.firefox.FirefoxDriver;
import pl.bmajsak.github.pages.MainPage
import pl.bmajsak.github.pages.ProjectPage
import pl.bmajsak.webdriver.Browser
import pl.bmajsak.webdriver.tooltip.Tooltip;

import spock.lang.*


class SearchingFeaturesScreencastSpec extends Specification {

    @Shared driver = new FirefoxDriver();
    
    def projectName = "webdriver-spock";

    def fullProjectName = "bartoszmajsak/" + projectName;
    
    def setupSpec() {
        def browser = new Browser(driver);
        browser.fullscreen();
    }
    
    def cleanupSpec() {
        driver.quit()
    }
    
    def "Searching for project by it's name"() {
        given: "User is on the main page"
            def mainPage =  new MainPage(driver)
            def tooltip = new Tooltip(driver)
        
        when: "Enters project's name ${projectName} "
            tooltip.show("Global search", "Type project name in the search box in the upper right corner of the website");
            def resultsPage = mainPage.searchForProject(projectName)
        
        then: "Link to project site should be listed"
            tooltip.show("As result", "You should see the project in the repositories section of the result page.");
            resultsPage.containsProject projectName
    }
    
    def "Search for a file using tree finder"() {
        given: "User enters project page"
            def fileName = "pom.xml"
            def tooltip = new Tooltip(driver)
            def projectPage = new ProjectPage(driver, fullProjectName)
            tooltip.show("Tree finder", "How to search for file patterns in whole project tree. Similar to Eclipse Open Type/Resource ");
            
        when: "Hits keyboard shortcut to enable quick finder"
            tooltip.show("Keyboard shortcut", "Hit T to enable interactive search");
            def treeFinder = projectPage.enableTreeFinder()
        
        and: "searches for ${fileName}"
            tooltip.show("Type what you are looking for", "In our case - ${fileName}");
            treeFinder.type fileName
            
        then: "File should be listed in tree viewer"
            tooltip.show("As a result", "you should see files matching pattern highlighted");
            treeFinder.contains fileName
    }

}
