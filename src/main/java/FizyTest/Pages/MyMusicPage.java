package FizyTest.Pages;

import FizyTest.Utils.reports.ExtentTestManager;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

public class MyMusicPage extends BasePage{
    By myMusicBtn = new By.ByCssSelector(".nav-menu__link.ng-binding[ui-sref='my_music']");
    By createNewListBtn = new By.ByCssSelector(".ui-create-new-block");
    By enterListNameTxt = new By.ByXPath("//body/div[2]/div[2]/div[1]/input[1]");
    By okeyBtn = new By.ByXPath("//button[@title='TAMAM']");
    By newListName = new By.ByXPath("//my-playlists//div[@class='ui-tab-content']//div[1]//div[@class='list-card__details']//a[1]");
    By newListNameTxt = new By.ByXPath("//a[contains(text(),'Yeni Liste Deneme')]");
    By threeDotForNewListBtn = new By.ByXPath("//my-music[1]/div[1]/div[3]/my-playlists[1]/div[1]/div[1]/playlist-card[1]/list-card[1]/div[1]/div[1]/div[1]/div[2]/div[1]/button[2]");
    By threeDotDeleteBtn = new By.ByCssSelector(".context-menu-item.test-id-delete");
    public MyMusicPage(WebDriver driver) {
        super(driver);
    }

    public void setCreateNewList(){
        String createNewListName = "Yeni Liste Deneme";
        click(myMusicBtn);
        scrollPageElement(drivers().findElement(By.cssSelector(".ui-create-new-block")));
        waitForSecond(3);
        click(createNewListBtn);
        ExtentTestManager.getTest().log(Status.PASS, "Create New List Button Clicked");
        Boolean listNameEnterControl = isDisplay(okeyBtn);
        if (listNameEnterControl == true) {
            click(enterListNameTxt);
            type(enterListNameTxt,createNewListName);
            click(okeyBtn);
        }
        waitForSecond(3);
        scrollPageElement(drivers().findElement(By.cssSelector(".nav-search")));
        waitForSecond(3);
        String listNameControl = find(newListName).getText();
        Assert.assertEquals(createNewListName,listNameControl);
        Boolean listNameDisplay = isDisplay(newListNameTxt);
        Assert.assertTrue(listNameDisplay);
        ExtentTestManager.getTest().log(Status.PASS, "List Created");
    }

    public void setDeleteNewListValidation(){
        waitForSecond(5);
        List<WebElement> myListCountBefore = drivers().findElements(By.xpath("//div[@class='list-card__details']"));
        int countBefore = myListCountBefore.size();
        System.out.println(countBefore);
        Actions a = new Actions(drivers());
        a.moveToElement(find(By.xpath("//my-playlists//div[@class='ui-tab-content']//div[1]//div[@class='list-card__details']//a[1]")))
                .perform();
        click(threeDotForNewListBtn);
        waitForSecond(2);
        click(threeDotDeleteBtn);
        click(By.cssSelector("button[title='Sil']"));
        waitForSecond(6);
        List<WebElement> myListCount = drivers().findElements(By.xpath("//div[@class='list-card__details']"));
        int myListCountActual = myListCount.size();
        System.out.println(countBefore);
        int leftCount = myListCountActual - countBefore;
        if (leftCount!=1){
            ExtentTestManager.getTest().log(Status.PASS, "List Deleted");
        }else {
            Assert.fail("List Not Deleted");
        }
        /*
        Boolean listNameDisplay = isDisplay(newListNameTxt);
        if (listNameDisplay==true){
            Assert.fail("Liste Silinmedi");
        }
       // Assert.assertFalse(listNameDisplay);
        ExtentTestManager.getTest().log(Status.PASS, "List Deleted");

         */


    }
}