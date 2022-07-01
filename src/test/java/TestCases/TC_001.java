package TestCases;

import Drivers.BaseTest;
import FizyTest.Pages.*;
import net.sourceforge.tess4j.TesseractException;
import org.testng.annotations.Test;

import java.io.IOException;

public class TC_001 extends BaseTest {


    @Test
    public void fizyProcessValidationTest() throws InterruptedException, TesseractException, IOException {
        FastLoginPage fastLoginPage = new FastLoginPage(driver);
        ExplorePage explorePage = new ExplorePage(driver);
        MiniPlayerPage miniPlayerPage = new MiniPlayerPage(driver);
        SearchBoxResultPage searchBoxResultPage = new SearchBoxResultPage(driver);
        MyMusicPage myMusicPage = new MyMusicPage(driver);

        fastLoginPage.captchaTest();
        explorePage.setThemeSelection();


        miniPlayerPage.setMiniPlayerSongValidation();
        miniPlayerPage.setListNameValidation();

        explorePage.setListsByThemes();
        explorePage.setActionMenuValidation();

        miniPlayerPage.setForwardValidation();
        miniPlayerPage.setBackButtonValidation();
        miniPlayerPage.setMiniPlayerPauseValidation();
        miniPlayerPage.setMiniPlayerPlayValidation();
        miniPlayerPage.setHeartIconValidation();
        miniPlayerPage.setRepeatButtonValidation();
        miniPlayerPage.setMaxiPlayerLaunchValidation();
        miniPlayerPage.setMaxiPlayerPlayListValidation();
        explorePage.setVideoStartValidation();
        searchBoxResultPage.setSearchBoxValidation();
        searchBoxResultPage.setSearchBoxPlaySongValidation();
        myMusicPage.setCreateNewList();
        myMusicPage.setDeleteNewListValidation();
        explorePage.setAccountLogOutValidation();


    }
}
