
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Set;

public class WindowHandle {
    WebDriver driver;
    @BeforeSuite
    public void setUpBrowser(){
        System.setProperty("Webdriver.chrome.driver","E:\\Mandeep Learnings\\chromedriver_win32\\chromedriver.exe");
        driver= new ChromeDriver();
        driver.get("https://zoom.us/");
    }
    @Test
    public void testWindowHandle()throws InterruptedException{
        System.out.println("Before"+driver.getCurrentUrl());
        WebElement element = driver.findElement(By.cssSelector("#navbar > ul:nth-child(1) > li:nth-child(11) > a"));
        new Actions(driver).keyDown(Keys.CONTROL).click(element).build().perform();
        String currentWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        String newWindow="";
        Thread.sleep(5000);
        for(String window:windowHandles){
            if(!window.equals(currentWindow)){
                newWindow= window;
                driver.switchTo().window(window);
            }
        }
        driver.findElement(By.id("email")).sendKeys("Mandeep@gmail");
        driver.findElement(By.id("company")).sendKeys("Amazon");
        Thread.sleep(5000);
        driver.switchTo().window(currentWindow);
        Thread.sleep(5000);
        driver.switchTo().window(newWindow);
    }
    @AfterSuite
    public void teardown ()throws InterruptedException{
        Thread.sleep(5000);
        driver.quit();
    }

    }

