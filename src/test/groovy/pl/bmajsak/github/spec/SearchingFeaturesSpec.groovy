package pl.bmajsak.github.spec

import org.openqa.selenium.firefox.FirefoxDriver;
import pl.bmajsak.github.pages.MainPage
import pl.bmajsak.github.pages.ProjectPage

import spock.lang.*


class SearchingFeaturesSpec extends Specification {

    @Shared def driver = new FirefoxDriver();
    
    def projectName = "webdriver-spock";

    def fullProjectName = "bartoszmajsak/" + projectName;
    
    def "Searching for project by it's name"() {
        given: "User is on the main page"
            def mainPage =  new MainPage(driver)
        
        when: "Enters project's name ${projectName} "
            def resultsPage = mainPage.searchForProject(projectName)
        
        then: "Link to project site should be listed"
            resultsPage.containsProject projectName
    }
    
    def "Search for a file using tree finder"() {
        given: "User enters project page"
            def fileName = "pom.xml"
            def projectPage = new ProjectPage(driver, fullProjectName)
            
        when: "Hits keyboard shortcut to enable quick finder"
            def treeFinder = projectPage.enableTreeFinder()
        
        and: "searches for ${fileName}"
            treeFinder.type fileName
            
        then: "File should be listed in tree viewer"
            treeFinder.contains fileName
    }
    
    def "Close browser"() {
        setup:
            driver.quit()
    }
    

}
