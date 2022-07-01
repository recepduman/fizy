package FizyTest.Pages;

import FizyTest.Utils.reports.ExtentTestManager;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

public class MiniPlayerPage extends BasePage {

    By miniPlayerSongNameTxt = new By.ByXPath("//div[@class='player__media-info']//div[1]//a");
    By playListSongNameTxt = new By.ByXPath("//div[@id='play-queue-list']//play-queue-item[1]//div[@class='trackList__info']//span[@class='trackList__title ng-binding']");
    By trendingListName = new By.ByXPath("//fizy-algoritmic//div[@id='fizy-domestic-popular-list-slider']//div[@class='slick-track']//div[2]//div[@class='list-card__details']//a");
    By playListName = new By.ByXPath("//span[@class='playqueue__title-primary ng-binding']");
    By playListBtn = new By.ByCssSelector(".js-open-playlist.player__playlist-button");
    By nextSongTxt = new By.ByXPath("//div[@id='play-queue-list']//play-queue-item[2]//span[@class='trackList__title ng-binding']");
    By playIcon = new By.ByXPath("//button[@title='Oynat']");
    By forwardIcon = new By.ByXPath("//button[@class='js-next player__next-button']");
    By backIcon = new By.ByCssSelector(".js-previous.player__previous-button");
    By pauseIcon = new By.ByXPath("//button[@title='Duraklat']");
    By miniPlayerTimeTxt = new By.ByCssSelector(".player__current-time.ng-binding");
    By miniPlayerHeartIcon = new By.ByCssSelector(".js-like.player__like-button");
    By activeMiniPlayerHeartIcon = new By.ByCssSelector(".js-like.player__like-button.is-active");
    By miniPlayerRepeatBtn = new By.ByXPath("//button[@title='Tekrarla']");
    By miniPlayerTotalTimeTxt = new By.ByXPath("//span[@title='Toplam Süre']");
    By maxiPlayerBtn = new By.ByCssSelector(".js-open-now-playing.player__now-playing-open-button");
    By maxiPlayerImage = new By.ByCssSelector(".now-playing_image");
    By playListSaveBtn = new By.ByCssSelector(".action-button__save-playlist.ng-scope");
    By playListCleanBtn = new By.ByCssSelector(".js-clear-playlist.text-button.ng-scope");
    By lyricsBtn = new By.ByXPath("//button[@class='lyrics__icon']");
    By heartPopup = new By.ByXPath("//div[@class='noty_body']");

    public MiniPlayerPage(WebDriver driver) {
        super(driver);
    }

    public void setMiniPlayerSongValidation() throws InterruptedException {
        click(playListBtn);
        waitForSecond(2);
        String miniPlayerSongName = find(miniPlayerSongNameTxt).getText();
        Assert.assertEquals(miniPlayerSongName, playListSongName());
        System.out.println(miniPlayerSongName);
        ExtentTestManager.getTest().log(Status.PASS, "Right Song Opened");
    }

    public String playListSongName() {
        String miniPlayerPlayListSongName = find(playListSongNameTxt).getText();
        return miniPlayerPlayListSongName;
    }

    public void setListNameValidation() {
        String trendingListNameActual = find(trendingListName).getText();
        waitForSecond(3);
        Assert.assertEquals(trendingListNameActual, playListNameExpected());
        ExtentTestManager.getTest().log(Status.PASS, "Playing Song And İn List Song Are Matched");

    }

    public String playListNameExpected() {
        String _playListName = find(playListName).getText();
        return _playListName;
    }


    public void setForwardValidation() {
        Boolean playListIcon = isDisplay(playListBtn);
        if (playListIcon == true) {
            click(playListBtn);
        }
        Boolean playIconCheck = isDisplay(playIcon);
        if (playIconCheck == true) {
            click(playIcon);
        }
        waitForSecond(3);
        click(forwardIcon);
        ExtentTestManager.getTest().log(Status.PASS, "Forward Button Clicked");


        waitForSecond(3);
        String nextSongActual = find(miniPlayerSongNameTxt).getText();
        String nextSongExpected = find(nextSongTxt).getText();
        Assert.assertEquals(nextSongActual, nextSongExpected);
        ExtentTestManager.getTest().log(Status.PASS, "Skiped To Next Song");

    }

    public String playListPreviousSongName() {
        return find(playListSongNameTxt).getText();
    }

    public int timeCalculation() {
        String timeTxt = find(miniPlayerTimeTxt).getText().replace("00:0", "");
        return Integer.parseInt(timeTxt);
    }

    public void setBackButtonValidation() {

        if (timeCalculation() >= 3) {
            Actions actions = new Actions(drivers());
            WebElement elementLocator = drivers().findElement(By.cssSelector(".js-previous.player__previous-button"));
            actions.doubleClick(elementLocator).perform();
            click(backIcon);
            ExtentTestManager.getTest().log(Status.PASS, "Previous Button Clicked");
            waitForSecond(5);
            String miniPlayerPreviousSongActual = find(miniPlayerSongNameTxt).getText();
            Assert.assertEquals(miniPlayerPreviousSongActual, playListPreviousSongName());
            ExtentTestManager.getTest().log(Status.PASS, "Skiped Previous Song");


        } else {
            click(backIcon);
            ExtentTestManager.getTest().log(Status.PASS, "Previous Button Clicked");
            waitForSecond(5);
            String _miniPlayerPreviousSongActual = find(miniPlayerSongNameTxt).getText();
            Assert.assertEquals(_miniPlayerPreviousSongActual, playListPreviousSongName());
            ExtentTestManager.getTest().log(Status.PASS, "Skiped Previous Song");
        }

    }

    public void setMiniPlayerPauseValidation() {
        click(pauseIcon);
        Boolean playIconDisplay = isDisplay(playIcon);
        if (playIconDisplay == true) {
            String timeActual = find(miniPlayerTimeTxt).getText().replace("00:0", "");
            int timeNumbActual = Integer.parseInt(timeActual);
            waitForSecond(5);
            Assert.assertEquals(timeNumbActual, timeCalculation());
            ExtentTestManager.getTest().log(Status.PASS, "Pause Icon Clicked and Song Is Stoped");
        } else {
            Assert.fail("Şarkı durmadı");
        }

    }

    public void setMiniPlayerPlayValidation() {
        String timeActual = find(miniPlayerTimeTxt).getText();
        click(playIcon);
        String timeExpected = find(miniPlayerTimeTxt).getText();
        Assert.assertEquals(timeActual, timeExpected);
        Boolean pauseIconDisplay = isDisplay(pauseIcon);
        Assert.assertTrue(pauseIconDisplay);
        ExtentTestManager.getTest().log(Status.PASS, "Song Played");
        waitForSecond(3);

    }

    public boolean heartBtnSuccess() {
        boolean heartControl = getElementLocatedControl(By.xpath("//button[@class='js-like player__like-button is-active']"));
        return heartControl;
    }

    public void setHeartIconValidation() {
        if (heartBtnSuccess()==true){
            click(activeMiniPlayerHeartIcon);
            waitForSecond(5);
        }
        click(miniPlayerHeartIcon);
        waitForSecond(3);
        Assert.assertTrue(heartBtnSuccess());
        Boolean popUpControl = isDisplay(heartPopup);
        Assert.assertTrue(popUpControl);
        ExtentTestManager.getTest().log(Status.PASS, "Heart Icon Clicked And Song Added List");


    }

    public String playerSongNameExpected() {
        return find(miniPlayerSongNameTxt).getText();
    }

    public void setRepeatButtonValidation() {
        Boolean playIconCheck = isDisplay(playIcon);
        if (playIconCheck == true) {
            click(playIcon);
        }
        String songNameActual = find(miniPlayerSongNameTxt).getText();
        Actions actions = new Actions(drivers());
        WebElement repeatButtonLocator = drivers().findElement(By.xpath("//button[@title='Tekrarla']"));
        actions.doubleClick(repeatButtonLocator).perform();
        WebElement e = drivers().findElement(By.cssSelector(".player__progress-bar-bg.slider"));
        actions
                .moveToElement(e)
                .clickAndHold()
                .moveByOffset(570, 0)
                .release()
                .perform();

        waitForSecond(7);
        Assert.assertEquals(songNameActual, playerSongNameExpected());
        ExtentTestManager.getTest().log(Status.PASS, "Repeat Button Double Cliked And Song Repeated");
        waitForSecond(3);
        click(playListBtn);

    }

    public void setMaxiPlayerLaunchValidation() {
        click(maxiPlayerBtn);
        Boolean maxiPlayerImageControl = isDisplay(maxiPlayerImage);
        Assert.assertTrue(maxiPlayerImageControl);
        ExtentTestManager.getTest().log(Status.PASS, "Maxi Player Opened");

    }

    public int playListGetSize() {
        List<WebElement> playListCount = drivers().findElements(By.xpath("//div[@id='play-queue-list']//play-queue-item"));
        int listCount = playListCount.size();
        return listCount;
    }

    public void setMaxiPlayerPlayListValidation() {
        waitForSecond(5);
        click(playListBtn);
        Boolean playListControl = isDisplay(playListSaveBtn);
        Assert.assertTrue(playListControl);
        click(playListCleanBtn);
        int playListExpectedCount = 1;
        Assert.assertEquals(playListGetSize(), playListExpectedCount);
        ExtentTestManager.getTest().log(Status.PASS, "Listed Songs Cleaned And Playing Song Exist");
        click(maxiPlayerBtn);

    }


}
