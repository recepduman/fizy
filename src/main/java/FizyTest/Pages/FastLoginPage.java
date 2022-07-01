package FizyTest.Pages;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class FastLoginPage extends BasePage {

    By fastLoginBtn = new By.ById("webPlayerHeader");
    By phoneNumberTtx = new By.ByCssSelector("#phoneNo");
    By passwordTxt = new By.ByCssSelector("#password");
    By loginWithFastPasswordEnterCheckBox = new By.ByXPath("//label[@for='loginWithPassword']");
    By userAnotherAccountButton = new By.ById("use-another-account-button");
    By rememberMeCheckBox = new By.ByXPath("//label[@for='rememberMe']");
    By loginBtn = new By.ById("webLogin-button");
    By okeyBtn = new By.ByCssSelector("#password-login-forward-button");
    By captchaCodeTxt = new By.ById("captcha-code");
    By genericPopupOkBtn = new By.ById("generic-popup-ok-button");
    By returnBtn = new By.ByXPath("//a[@class='m-captcha__return']");
    By passControl = new By.ById("forgotPasswordLink");
    By captchaControl = new By.ById("captchaSrc");
    String username = "5315464799";
    String password = "Deneme01";



    public FastLoginPage(WebDriver driver) {
        super(driver);
    }

    public void setFastLogin() throws InterruptedException, IOException, TesseractException {


        String originalWindow = drivers().getWindowHandle();
        //assert drivers().getWindowHandles().size() == 1;
        click(fastLoginBtn);

        waitForSecond(3);


        for (String windowHandle : drivers().getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                drivers().switchTo().window(windowHandle);
                type(phoneNumberTtx, "5315465100");
                click(loginWithFastPasswordEnterCheckBox);
                click(rememberMeCheckBox);
                click(loginBtn);
               waitForSecond(10);
                Boolean captcha = drivers().findElement(By.id("captchaSrc")).isDisplayed();
                if (captcha==true){
                    WebElement image = drivers().findElement(By.id("captchaSrc"));
                    File src = image.getScreenshotAs(OutputType.FILE);
                    String path = "C:\\Users\\siram\\Desktop\\Fizy\\captchaImage\\captcha.png";
                    String tesPath = "C:\\Users\\siram\\Desktop\\Fizy\\tessdata";
                   FileHandler.copy(src,new File(path));
                    waitForSecond(2);
                    ITesseract tes = new Tesseract();
                    tes.setDatapath(tesPath);
                    String str = tes.doOCR(new File(path));

                    System.out.println(str);
                    break;


                }
            }

        }
        drivers().switchTo().window(originalWindow);









    }
    public void setFail(){
        drivers().findElement(By.id("webLogin-button")).click();
    }
    By errorControl = new By.ById("generic-popup-ok-button");
    public boolean captchaBoolControl(){
       // boolean errorMessage = drivers().findElement(By.id("generic-popup-ok-button")).isDisplayed();
        boolean errorMessage =isDisplayTime(errorControl);
        return errorMessage;
    }
    public boolean successControl(){
            boolean codeOne = drivers().findElement(By.id("forgotPasswordLink")).isDisplayed();
            return codeOne;
    }
    By errorTxt = new By.ByXPath("//input[@class='parsley-error']");
    public boolean missingCodePass(){
      //  boolean missingCode= drivers().findElement(By.xpath("//a[@class='input-text__error-icon']")).isDisplayed();
        boolean missingCode= isDisplayTime(errorTxt);
          return missingCode;
    }
    public String imageRead() throws IOException, TesseractException {
        waitForSecond(2);
        WebElement imageC = drivers().findElement(By.id("captchaSrc"));
        File src = imageC.getScreenshotAs(OutputType.FILE);
        String path = "C:\\Users\\siram\\Desktop\\Fizy\\captchaImage\\captcha.png";
        String tesPath = "C:\\Users\\siram\\Desktop\\Fizy\\tessdata";
        FileHandler.copy(src,new File(path));
        waitForSecond(2);
        ITesseract tes = new Tesseract();
        tes.setDatapath(tesPath);
        String str = tes.doOCR(new File(path));
        String newCode = str.replaceAll("[^\\d.]","");
       // int a = newCode.length();
        //return a;
        return newCode;
    }
    public String imageTxt() throws IOException, TesseractException {
        waitForSecond(2);
        WebElement imageC = drivers().findElement(By.id("captchaSrc"));
        File src = imageC.getScreenshotAs(OutputType.FILE);
        String path = "C:\\Users\\siram\\Desktop\\Fizy\\captchaImage\\captcha.png";
        String tesPath = "C:\\Users\\siram\\Desktop\\Fizy\\tessdata";
        FileHandler.copy(src,new File(path));
        waitForSecond(2);
        ITesseract tes = new Tesseract();
        tes.setDatapath(tesPath);
        String str1 = tes.doOCR(new File(path));
        return str1;

    }


    public void captchaTest() throws IOException, TesseractException {

        String originalWindow = drivers().getWindowHandle();
        click(fastLoginBtn);
        waitForSecond(5);
        for (String windowHandle : drivers().getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                drivers().switchTo().window(windowHandle);
                click(userAnotherAccountButton);
                waitForSecond(5);
                type(phoneNumberTtx, "5315464799");
                click(loginWithFastPasswordEnterCheckBox);
                click(rememberMeCheckBox);
                click(loginBtn);
                waitForSecond(3);
                Boolean captcha = getElementLocatedControl(captchaControl);
                if (captcha==true){
                    while (captchaBoolControl()==false){

                      String captchaText = imageRead();

                        while (captchaText.length()!=6){
                                click(returnBtn);
                                waitForSecond(3);
                            captchaText = imageRead();


                            }
                            drivers().findElement(By.id("captcha-code")).sendKeys(captchaText);
                            click(loginBtn);

                        waitForSecond(2);
                        if (captchaBoolControl()==true){
                            click(genericPopupOkBtn);
                            click(returnBtn);

                        }else {
                            break;
                        }
                    }
                    type(passwordTxt,"Deneme01");
                    click(okeyBtn);

                }else {
                    type(passwordTxt,"Deneme01");
                    click(okeyBtn);
                }
                break;
            }
        }
        drivers().switchTo().window(originalWindow);
    }

}
