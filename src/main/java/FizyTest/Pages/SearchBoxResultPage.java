package FizyTest.Pages;

import FizyTest.Utils.reports.ExtentTestManager;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SearchBoxResultPage extends BasePage{
    By maxiPlayerBtn = new By.ByXPath("//button[@class='js-open-now-playing player__now-playing-open-button']");
    By searchBoxTxt = new By.ByCssSelector("translate[class='ng-scope']");
    By searchBn = new By.ByXPath("//input[@type='search']");
    By searchIcon = new By.ByCssSelector(".search-area__submit");
    By firstSongPlaYBtn = new By.ByXPath("//div[@id='fizy-search-results-audio-slider']//div[@class='ng-scope slick-slide slick-current slick-active']//track-list//track-list-item-album[1]//div//div[2]");
    By firstSongTitleTxt= new By.ByXPath("//section[@id='fizy-search-results-audio']//div[@class='slick-track']//div[@data-slick-index='0']//track-list-item-album[1]//span[@class='trackList__title ng-binding']");
    By miniPlayerSongNameTxt = new By.ByXPath("//div[@class='player__media-info']//div[1]//a");
    By videoPlayBtn = new By.ByXPath("//div[@id='fizy-search-results-video-slider']//div[@data-slick-index='1']//video-list-item[2]//div//div[1]");
    By videoSongNameTxt = new By.ByXPath("//section[@id='fizy-search-results-video']//div[@class='slick-track']//div[@data-slick-index='1']//video-list-item[2]//span[@class='trackList__title ng-binding']");

    public SearchBoxResultPage(WebDriver driver) {
        super(driver);
    }
    public void setSearchBoxValidation() {
        String singerName = "Tarkan";
        click(maxiPlayerBtn);
        waitForSecond(3);
        scrollPageElement(drivers().findElement(By.cssSelector(".nav-search")));
        click(searchBoxTxt);
        click(searchBn);
        type(searchBn, singerName);
        click(searchIcon);
        for (int i = 1; i < 6; i++) {
            Boolean titleControl = isDisplay(By
                    .xpath("//section[" + i + "]//h2[@class='ui-section__main-title ng-scope']"));
            Assert.assertTrue(titleControl);
        }
        for (int i = 0; i < 2; i++) {
            String trackControl = find(By
                    .xpath("//section[@id='fizy-search-results-audio']//div[@class='slick-track']//div[@data-slick-index='" + i + "']//span[@class='trackList__artistNameQuene ng-binding']"))
                    .getText();
            Assert.assertEquals(trackControl, singerName);
            ExtentTestManager.getTest().log(Status.PASS, "Artist Search and Correct Artıst Listed");
        }
    }
    public String playerSongNameExpected() {
        return find(miniPlayerSongNameTxt).getText();
    }

    public void setSearchBoxPlaySongValidation(){
        click(firstSongPlaYBtn);
        waitForSecond(3);
        String searchResultSongNameActual = find(firstSongTitleTxt).getText();
        Assert.assertEquals(searchResultSongNameActual,playerSongNameExpected());
        scrollPageElement(drivers().findElement(By.xpath("//section[5]//h2[@class='ui-section__main-title ng-scope']")));
        click(videoPlayBtn);
        waitForSecond(3);
        String videoNameActual = find(videoSongNameTxt).getText();
        Assert.assertEquals(videoNameActual,playerSongNameExpected());
        ExtentTestManager.getTest().log(Status.PASS, "Listed Song and Playing Song Same");
        click(maxiPlayerBtn);


    }
}
