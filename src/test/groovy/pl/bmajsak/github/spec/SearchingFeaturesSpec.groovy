package pl.bmajsak.github.spec

import org.openqa.selenium.firefox.FirefoxDriver;
import pl.bmajsak.github.pages.MainPage
import pl.bmajsak.github.pages.ProjectPage
import pl.bmajsak.webdriver.tooltip.TooltipPresenter;

import spock.lang.*


class SearchingFeaturesSpec extends Specification {

    @Shared def driver = new FirefoxDriver();
    
    def projectName = "webdriver-spock";

    def fullProjectName = "bartoszmajsak/" + projectName;
    
    def "Searching for project by it's name"() {
        given: "User is on the main page"
            def mainPage =  new MainPage(driver)
            def tooltipPresenter = new TooltipPresenter(driver)
        
        when: "Enters project's name ${projectName} "
            tooltipPresenter.show("Global search", "Type project name in the search box in the upper right corner of the website");
            def resultsPage = mainPage.searchForProject(projectName)
        
        then: "Link to project site should be listed"
            tooltipPresenter.show("As result", "You should see the project in the repositories section of the result page.");
            resultsPage.containsProject projectName
    }
    
    def "Search for a file using tree finder"() {
        given: "User enters project page"
            def fileName = "pom.xml"
            def tooltipPresenter = new TooltipPresenter(driver)
            def projectPage = new ProjectPage(driver, fullProjectName)
            tooltipPresenter.show("Tree finder", "How to search for file patterns in whole project tree. Similar to Eclipse Open Type/Resource ");
            
        when: "Hits keyboard shortcut to enable quick finder"
            tooltipPresenter.show("Keyboard shortcut", "Hit T to enable interactive search");
            def treeFinder = projectPage.enableTreeFinder()
        
        and: "searches for ${fileName}"
            tooltipPresenter.show("Type what you are looking for", "In our case - ${fileName}");
            treeFinder.type fileName
            
        then: "File should be listed in tree viewer"
            tooltipPresenter.show("As a result", "you should see files matching pattern highlighted");
            treeFinder.contains fileName
    }
    
    def "Close browser"() {
        setup:
            driver.quit()
    }
    

}
