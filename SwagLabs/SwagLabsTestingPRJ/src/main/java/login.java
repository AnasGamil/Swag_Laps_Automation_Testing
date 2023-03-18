import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class login {

    public WebElement user(WebDriver driver) {
        return driver.findElement(By.id("user-name")) ;
    }

    public WebElement pass(WebDriver driver) {
        return driver.findElement(By.id("password")) ;
    }

    public void loginsteps(WebDriver driver, String username, String pass){
        user(driver).clear();
        user(driver).sendKeys(username);

        pass(driver).clear();
        pass(driver).sendKeys(pass);
        pass(driver).sendKeys(Keys.ENTER);
    }

    public By low2high(){
        return By.cssSelector("select[class=\"product_sort_container\"] > [value=\"lohi\"]");
    }

    public WebElement addtocart(WebDriver driver){
        return driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
    }

    public By productimg(){
        return By.cssSelector("img[class=\"inventory_details_img\"]");
    }

    public By clickonproduct(){
        return By.id("item_4_img_link");
    }

    public By errorlogin(){
        return By.cssSelector("div[class=\"error-message-container error\"]>h3[data-test=\"error\"]");
    }

    public WebElement menu(WebDriver driver){
        return driver.findElement(By.id("react-burger-menu-btn"));
    }

    public WebElement logoutbutton(WebDriver driver){
        return driver.findElement(By.id("logout_sidebar_link"));
    }

    public void logoutsteps(WebDriver driver) throws InterruptedException {
        menu(driver).click();
        Thread.sleep(3000);
        logoutbutton(driver).click();
    }

    public By open_my_cart(){
        return By.className("shopping_cart_link");
    }

    public By ProductName(){
        return By.className("inventory_item_name");
    }
}
