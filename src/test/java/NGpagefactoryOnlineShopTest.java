import onlineShopPage.OnlineShopPage;
import onlineShopPage.OnlineShopPagepageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NGpagefactoryOnlineShopTest extends BaseTest {

    OnlineShopPagepageFactory page;

    @Test
    public void verifyAddItemToCartWithActins() {
        final String expectedTotalPrice = "$29.00";

        page = new OnlineShopPagepageFactory(driver);
        page.searchItem("Bloose").switchOnListView().clickButtonAddToCart();
        page.clickProccedToCheckoutButton();
        String actualTotalPrice = driver.findElement(By.id("total_price")).getText();

        org.junit.Assert.assertEquals("Two elements NOT equal", expectedTotalPrice, actualTotalPrice);
    }

    @Test
    public void workWithMenu() {
        page = new OnlineShopPagepageFactory(driver);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//a[@title='Women']"))).build().perform();

        driver.findElement(By.xpath("//a[@title='T-shirts']")).click();
    }

    @Test(dataProvider = "create account", dataProviderClass = CreateAccountDataProvider.class)
    public void verifyCreateAccount(String email, String massage) {
        page = new OnlineShopPagepageFactory(driver);

        page.clickSignIn();
        page.waitEmailField();
        page.enterIntoEmailField(email);
        page.clickButtonCreateAccount();
        page.waitErrorMessage();
        Assert.assertEquals(page.getErrorMessage(), massage);
    }
}
