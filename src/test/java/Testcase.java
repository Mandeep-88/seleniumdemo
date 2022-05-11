import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.openqa.selenium.StaleElementReferenceException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class Testcase {
    WebDriver driver;
    @BeforeSuite
    public void setupBrowser(){
        System.setProperty("webdriver.chrome.driver","E:\\Mandeep Learnings\\chromedriver_win32\\chromedriver.exe");
        driver= new ChromeDriver();
        //driver.get("https://pragra.io");

    }
    @Test(enabled = false)
    public void testComplexActions() throws InterruptedException {
        driver.get("https://cn.ca/en");

        WebElement service = driver.findElement(By.cssSelector("div>a[href='/en/our-services/'][class='topLevelLink'][id='ctl06__401d136693096_repMainNav_topLevelLink_1']"));

        //trucking
        WebElement trucking = driver.findElement(By.cssSelector("li>a[href='/en/our-services/trucking/'][id='ctl06__401d136693096_repMainNav_repSecondLevel_1_secondLevelLink_2']"));
        //express
        WebElement express = driver.findElement(By.cssSelector("li>a[href='/en/our-services/trucking/cn-express-pass/'][id='ctl06__401d136693096_repMainNav_repSecondLevel_1_repThirdLevel_2_thirdLevelLink_1'][class='thirdlevellink']"));
        //jobaid
        WebElement jobAid = driver.findElement(By.cssSelector("li>[id='ctl06__401d136693096_repMainNav_repSecondLevel_1_repThirdLevel_2_repFourthLevel_1_fourthLevelLink_0'][class='fourthlevellink']"));

        Actions actions = new Actions(driver);
//      actions.moveToElement(service).pause(2000).moveToElement(trucking).pause(2000)                                  //move cursor trucking to express to jobaid
//               .moveToElement(express).pause(2000).moveToElement(jobAid).pause(2000).click().build().perform();
        //actions.moveToElement(service,100,0).build().perform();
       //actions.moveToElement(service).clickAndHold().build().perform();
       actions.moveToElement(service).click().keyDown(Keys.CONTROL).build().perform();
//        WebElement search = driver.findElement(By.name("search"));
//        actions.moveToElement(search).click().keyDown(Keys.SHIFT).sendKeys("acfbhf").build().perform();
        Thread.sleep(5000);
    }
    @Test(enabled = false)
    public void testJquery() throws InterruptedException {
        driver.get("https://jqueryui.com/droppable/");
        driver.switchTo().frame(0);
        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("droppable"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(draggable,droppable).build().perform();
        Thread.sleep(5000);
        actions.doubleClick();
    }
    @Test
    public void testDoubleClick() throws InterruptedException {
        driver.get("https://developer.mozilla.org/en-US/docs/Web/API/Element/dblclick_event");
        driver.switchTo().frame("frame_examples");
        WebElement doubleClick = driver.findElement(By.tagName("aside"));
        Actions actions = new Actions(driver);
        actions.doubleClick(doubleClick).build().perform();
    }

    @Test(enabled=false)
    public void testCaseAlert(){
        ((JavascriptExecutor) driver).executeScript("alert('Hello Guys')");
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        try{
            Thread.sleep(5000);
        }
        catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        alert.dismiss();

    }

    @Test(enabled = false)
    public void testSelect() throws InterruptedException {
        driver.navigate().to("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select_multiple");
        WebElement iframeResult = driver.findElement(By.id("iframeResult"));
        driver.switchTo().frame(iframeResult);
        WebElement cars = driver.findElement(By.id("cars"));
        Select select = new Select(cars);
        if(select.isMultiple()){
            System.out.println("Yes Multiple");
        }
        select.selectByIndex(1);
        Thread.sleep(2000);
        select.selectByIndex(2 );
        Thread.sleep(2000);
        select.selectByVisibleText("Audi");
        Thread.sleep(2000);
        select.deselectAll();
    }

    @Test(enabled = false)
    public void elementDisableEmail(){
        driver.get("https://explore.zoom.us/en/contactsales/");
        WebElement form = driver.findElement(By.tagName("form"));
        WebElement email = driver.findElement(By.id("email"));
        System.out.println("position = " +email.getLocation());
        System.out.println("size is ="+email.getSize());
        System.out.println("Rect = " +email.getRect());
        WebElement signupfree = driver.findElement(By.cssSelector("#signupfree>a"));
        System.out.println("Background-color ="+signupfree.getCssValue("background-color"));     //background-color check by getcssvalue
        System.out.println("Height = " + signupfree.getCssValue("height"));
        File screenshot = signupfree.getScreenshotAs(OutputType.FILE);           //screenshot of an element
        try {
            Files.copy(screenshot.toPath(),Paths.get("button.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        ((JavascriptExecutor)driver).executeScript("$(\"#email\").attr(\"disabled\",\"disabled\")");
//        if(email.isEnabled()){
//            System.out.println("Enabled");
//        }
//        else{
//            System.out.println("Not Enabled");
//        }


    }
    @Test(enabled = false)
    public void tagNameTc() throws InterruptedException {
        driver.get("https://explore.zoom.us/en/contactsales/");
        WebElement form = driver.findElement(By.tagName("form"));
        //form.submit();
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("mandeep@gmail.com");
        Thread.sleep(2000);
        email.clear();
        System.out.println(email.getTagName());
        System.out.println(email.getAttribute("placeholder"));
    }

    @Test(enabled = false)
    public void testFrames(){
        driver.get("https://freecrm.com/");
        driver.switchTo().frame("user icon");
        WebElement element = driver.findElement(By.name("email"));
        element.sendKeys("mandeepquiet@gmail.com");
//        WebElement password = driver.findElement(By.name("password"));
//        element.sendKeys("13@Freecrm");
//        WebElement element1 = driver.findElement(By.xpath("//div[@class='ui fluid large blue submit button']"));
//        element.click();
    }
    @Test(enabled = false)
    public void screenShotTc() throws InterruptedException {
        driver.get("https://pragra.io");
        WebElement element = driver.findElement(By.xpath("//a[.='Apply']"));
        element.click();
        File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(screenshotAs.toPath(), Paths.get("screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test(enabled = false)
    public void windowTc() throws InterruptedException, MalformedURLException {
        driver.get("https://pragra.io");
        WebElement element = driver.findElement(By.xpath("//a[.='Apply']"));
        element.click();
        driver.navigate().to(new URL("https://zoom.us/"));
        WebDriver.Window window = driver.manage().window();
        window.fullscreen();
        Thread.sleep(2000);
        window.setSize(new Dimension(900,800));
        Thread.sleep(5000);
        Point position = window.getPosition();
        window.setPosition(new Point(0,0));
    }

    @Test(enabled = false)
    public void cookiesTc() throws InterruptedException, MalformedURLException {

        driver.get("https://zoom.us/pricing");


//        Set<Cookie> cookies = driver.manage().getCookies();
//        cookies.forEach(System.out::println);
        driver.manage().deleteCookieNamed("_zm_currency");
        Thread.sleep(2000 );
        driver.manage().addCookie(new Cookie("_zm_currency","USD"));
        Thread.sleep(5000);
        driver.navigate().refresh();
    }
    @Test(enabled = false)
    public void navigateTc() throws InterruptedException, MalformedURLException {
        driver.get("https://pragra.io");

       WebElement element = driver.findElement(By.xpath("//a[.='Apply']"));
        element.click();
        Thread.sleep(2000);
        driver.navigate().back();
        Thread.sleep(2000);
        driver.navigate().forward();
        Thread.sleep(2000);
        driver.navigate().refresh();
        Thread.sleep(2000);
        driver.navigate().to(new URL("https://zoom.us/"));
        driver.quit();

//        WebElement element = driver.findElement(By.cssSelector("#black-topbar ul[role=menubar]>li:nth-child(2)"));

//       System.out.println("before"+driver.getCurrentUrl());
//        driver.findElement(By.cssSelector("#btnJoinMeeting")).click();
//        System.out.println("After"+ driver.getCurrentUrl());
//        driver.findElement(By.cssSelector("#join-form label+input")).sendKeys("4656415364");
    }
    @Test(enabled = false)

    public void tc(){
//        WebElement element = driver.findElement(By.cssSelector("label[for=email]+div>div>input"));
//        element.sendKeys("helloselenium.dev");
//        WebElement element = driver.findElement(By.cssSelector("#black-topbar ul[role=menubar]>li:nth-child(2)"));
//        element.click();
        System.out.println("before"+driver.getCurrentUrl());
        driver.findElement(By.cssSelector("#btnJoinMeeting")).click();
        System.out.println("After"+ driver.getCurrentUrl());
      driver.findElement(By.cssSelector("#join-form label+input")).sendKeys("4656415364");

//        driver.findElement(By.xpath("//*[@id='navbar']//ul//li//a[contains (text(),'Sales') ]")).click();
//        driver.findElement(By.xpath("//form//input[@type='email']")).sendKeys("mandeep@gmail.com");
//        driver.findElement(By.xpath("//form//input[@name='company']")).sendKeys("Amazon");
//        driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys("Mandeep");

//        driver.findElement(By.cssSelector("#btnJoinMeeting")).click();
//        driver.findElement(By.cssSelector("join-form label+input")).sendKeys("456468678");
        //driver.findElement(By.xpath("//input[@value='Contact Sales']/*[@id='email'])).sendKeys("mandeep@gmail.com");
//        driver.findElement(By.xpath("//form//input[@name='company']")).sendKeys("Amazon");
//        driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys("Mandeep");

    }
    @Test(enabled = false)
    public void testTitle(){
        Assert.assertTrue(driver.getTitle().contains("Mandeep Join Meeting"));
    }
    @AfterSuite
    public void teardown() throws InterruptedException{
        Thread.sleep(10000);
        driver.quit();
    }

}
