package FizyTest.Pages;

import FizyTest.Utils.reports.ExtentTestManager;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

public class ExplorePage extends BasePage {


    By trendingTxt = new By.ByCssSelector("fizy-algoritmic[id='fizy-algoritmic'] h2[class='ui-section__main-title ng-scope']");
    By fizyIcon = new By.ByCssSelector("a[title='Fizy']");
    By playAlgoritmicBtn = new By.ByXPath("//fizy-algoritmic[@id='fizy-algoritmic']//div[@id='fizy-domestic-popular-list-slider']//div[2]//playlist-card[1]//list-card[1]//div[1]//div[1]//div[1]//div[2]//div[1]//button[1]");
    By themeImage = new By.ByXPath("//div[@class='slider__item ng-scope slick-slide slick-current slick-active']//a[@class='theme-card__link']");
    By exploreBtn = new By.ByXPath("//ul[@class='nav-menu']//a[@ui-sref='explore']");
    By threeDotBtn = new By.ByXPath("//list-for-you//div[@class='slick-track']//div[3]//div[@class='list-card__header']//button[@class='js-list-context-menu interaction-button__menu']");
    By threeDotActionMenu = new By.ByXPath("//ul[@class='context-menu-list context-menu-root']");
    By addToQueueBtn = new By.ByXPath("//ul[@class='context-menu-list context-menu-root']//li[1]");
    By watchVideoBtn = new By.ByCssSelector(".nav-menu__link.ng-binding[ui-sref='video']");
    By popularVideoTxt = new By.ByXPath("//video-popular//h2");
    By playWatchIcon = new By.ByXPath("//div[@id='fizy-videos-popular-of-month-slider']//video-card[2]//button[@class='interaction-button__play']");
    By videoImage = new By.ByCssSelector("#maxi-video-player_Viblast_api");
    By fullScreenIcon = new By.ByCssSelector(".js-full-screen-button.now-playing__full-screen-button");
    By videoPlayerTimeTxt = new By.ByCssSelector(".player__current-time.ng-binding");
    By maxiPlayerBtn = new By.ByXPath("//button[@title='Büyük Ekran']");
    By searchBoxTxt = new By.ByCssSelector("translate[class='ng-scope']");
    By searchBn = new By.ByXPath("//input[@type='search']");
    By titleTxt = new By.ByXPath("//section[1]//h2[@class='ui-section__main-title ng-scope']");
    By searchIcon = new By.ByCssSelector(".search-area__submit");
    By profilIcon = new By.ByCssSelector(".js-user-context-menu.user-info__user-menu.ng-scope");
    By logOutBtn = new By.ByCssSelector(".context-menu-item.test-id-logout");
    By packageBtn = new By.ByCssSelector("#viewPackages");


    //Actions a = new Actions(driver);
    Actions a = new Actions(drivers());

    public ExplorePage(WebDriver driver) {
        super(driver);
    }

    public void setThemeSelection() throws InterruptedException {
        boolean fizyLogo = isDisplay(fizyIcon);
        Assert.assertTrue(fizyLogo,"Giriş Başarılı");

        ExtentTestManager.getTest().log(Status.PASS, "Loged in success");
        scrollPageElement(find(trendingTxt));
        waitForSecond(2);
        //Actions a = new Actions(driver);
        a.moveToElement(find(By.xpath("//fizy-algoritmic//div[@id='fizy-domestic-popular-list-slider']//div[@class='slick-track']//div[2]//div[@class='list-card__details']//a")))
                .perform();
        waitForSecond(3);
        click(playAlgoritmicBtn);



    }

    public void setListsByThemes() {
        waitForSecond(2);
        scrollPageElement(find(By.cssSelector("theme-list[id='fizy-theme-list'] h2[class='ui-section__main-title ng-scope']")));
        click(themeImage);
        waitForSecond(2);
        // List<WebElement> themeListAmount = driver.findElements(By.xpath("//div[@class='list-card__details']//a"));
        List<WebElement> themeListAmount = drivers().findElements(By.xpath("//div[@class='list-card__details']//a"));
        ExtentTestManager.getTest().log(Status.PASS, "Themes Opened by List");


        int themeCount = themeListAmount.size();
        System.out.println(themeCount);
        if (themeCount == 0) {
            Assert.fail("Temalara Göre Listeler Sayfası Boş");
            ExtentTestManager.getTest().log(Status.PASS, "Opened List Not Empty");

        }
    }

    public void setActionMenuValidation() {

        Boolean fizyIconControl = isDisplay(fizyIcon);
        if (fizyIconControl == false) {
            drivers().navigate().refresh();
            waitForSecond(4);
            click(exploreBtn);
        }
        waitForSecond(5);
        //Actions a = new Actions(driver);
        a.moveToElement(find(By.xpath("//list-for-you//div[@class='slick-track']//div[3]//div[@class='list-card__header']")))
                .perform();
        click(threeDotBtn);
        ExtentTestManager.getTest().log(Status.PASS, "Action Menu Is Opened");
        Boolean actionMenu = isDisplay(threeDotActionMenu);
        Assert.assertTrue(actionMenu);
        click(addToQueueBtn);
        ExtentTestManager.getTest().log(Status.PASS, "Queue Button Clicked");

    }

    public int videoPlayerTimeCount() {
        String getTime = find(videoPlayerTimeTxt).getText().replace("00:0", "");
        return Integer.parseInt(getTime);
    }

    public void setVideoStartValidation() {
        click(watchVideoBtn);
        Boolean popularVideoControl = isDisplay(popularVideoTxt);
        Assert.assertTrue(popularVideoControl);
        scrollPageElement(drivers().findElement(By.xpath("//video-popular//h2")));
        a.moveToElement(find(By.xpath("//div[@id='fizy-videos-popular-of-month-slider']//video-card[2]//div[@class='video-card__info']//a[1]")))
                .perform();
        click(playWatchIcon);
        waitForSecond(8);
        Boolean videoControl = isDisplay(videoImage);
        Assert.assertTrue(videoControl);
        Boolean fullScreenIconControl = isDisplay(fullScreenIcon);
        Assert.assertTrue(fullScreenIconControl);
        int expextedTime = 1;
        if (videoPlayerTimeCount() >= expextedTime) {
            ExtentTestManager.getTest().log(Status.PASS, "Popular Videos Clicked And Video Played ");

        } else {
            Assert.fail("Video Başlatılamadı!");
        }




    }
    public void setAccountLogOutValidation(){
        click(profilIcon);
        waitForSecond(2);
        click(logOutBtn);
        Boolean successLogOutControl = isDisplay(packageBtn);
        Assert.assertTrue(successLogOutControl);
        ExtentTestManager.getTest().log(Status.PASS, "Succesfully Log Out");

    }





}
