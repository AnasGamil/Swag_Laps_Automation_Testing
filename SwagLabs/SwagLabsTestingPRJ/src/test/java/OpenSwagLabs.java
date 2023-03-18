import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class OpenSwagLabs {

    WebDriver driver = null;
    login log;

    String site_url = "https://www.saucedemo.com/" ;

    @BeforeTest
    public void OpenSL() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*") ;

        String ChromePath = System.getProperty("user.dir") + "\\src\\main\\resources\\chromedriver.exe";
        System.out.println(ChromePath);
        System.setProperty("webdriver.chrome.driver", ChromePath);

        //2 new object
        driver = new ChromeDriver(options);

        //3 - Navigate to google.com and maxmize then sleep 3 sec
        driver.manage().window().maximize();
        Thread.sleep(3000);

        log = new login() ;


    }

    @Test(priority = 1)
    public void ValidLogin() throws InterruptedException {
        //navigation to the website
        driver.navigate().to(site_url);

        //login steps
        log.loginsteps(driver, "standard_user", "secret_sauce");

        //assert that the url after login is correct
        String url = "https://www.saucedemo.com/inventory.html" ;
        Assert.assertEquals(driver.getCurrentUrl(),url);

        //sleep the quit
        Thread.sleep(3000);
    }

    @Test(priority = 2)
    public void logout() throws InterruptedException {
        //click on menu then click logout
        log.logoutsteps(driver);

        //assert that login button is correctly disabled
        Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed(), "Login button didn't display");
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    public void invalidLogin() throws InterruptedException {
        //navigation to the website
        driver.navigate().to(site_url);

        //login steps
        log.loginsteps(driver, "wrong_user", "secret");

        //create strings for expect and actual
        String expected = "Epic sadface: Username and password do not match any user in this service";
        String actual = driver.findElement(log.errorlogin()).getText();

        //assert that login failed message displayed
        Assert.assertTrue(actual.contains(expected),"Error Measage : Text is wrong");
        Thread.sleep(2000);
    }

    @Test(priority = 4)
    public void AddToCart() throws InterruptedException {
        //navigation to the website
        driver.navigate().to(site_url);

        //login steps
        log.loginsteps(driver, "standard_user", "secret_sauce");

        //adding product to cart
        log.addtocart(driver).click();
        Thread.sleep(2000);

        //open my cart
        driver.findElement(log.open_my_cart()).click();

        //product name's string
        String productname = driver.findElement(log.ProductName()).getText();

        //assert that the product is added successfully
        Assert.assertEquals(productname, "Sauce Labs Backpack");

        Thread.sleep(3000);
    }

    @Test(priority = 5)
    public void PriceL2H() throws InterruptedException {
        //navigation to the website
        driver.navigate().to(site_url);

        //login steps
        log.loginsteps(driver, "standard_user", "secret_sauce");

        Thread.sleep(3000);

        //choose price low to high option
        driver.findElement(log.low2high()).click();

        //low2high string
        String expexted = driver.findElement(log.low2high()).getText();

        //assert that the filter is done
        Assert.assertEquals(expexted, "Price (low to high)");


        Thread.sleep(3000);
    }

    @Test(priority = 6)
    public void PDP() throws InterruptedException {
        //navigation to the website
        driver.navigate().to(site_url);

        //login steps
        log.loginsteps(driver, "standard_user", "secret_sauce");

        Thread.sleep(2000);

        driver.findElement(log.clickonproduct()).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(log.productimg()).isDisplayed());

    }

    @AfterTest
    public void CloseBrowser()
    {
        driver.quit();
    }

}
