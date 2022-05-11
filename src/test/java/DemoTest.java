import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class DemoTest {
    WebDriver driver;
    @BeforeSuite
    public void setUpBrowser(){
        System.setProperty("WebDriver.chrome.driver","E:\\Mandeep Learnings\\chromedriver_win32\\chromedriver.exe");
        driver= new ChromeDriver();
        driver.get("https://facebook.com");

    }

    @Test
    public void setUpTest(){

        String title = driver.getTitle();
        System.out.println("title");
        if(title.equals("Facebook")){
            System.out.println("correct ");
        }
        else{
            System.out.println("wrong title");
        }
        System.out.println(driver.getCurrentUrl());
        driver.quit();
    }
    @AfterSuite
    public void teardown() throws InterruptedException{
        Thread.sleep(5000);
    }
}
