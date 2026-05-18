package full_submission_flow;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.sql.SQLOutput;
import java.time.Duration;


public class UploadPdfFileEssoar {
    private static Object driver;
    private static Object submitButton;
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        // 1. Open Essoar homepage then maximize the screen
        driver.get("https://essopenarchive-org.authorea-stag.literatumonline.com/");
        driver.manage().window().maximize();

        // 2. Click on LOG IN | REGISTER
        driver.findElement(By.className("sign-in-label")).click();

        //Adding wait.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement emailInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("email-input")));

        // 3.Click and type email then click on continue
        emailInput.click();
        emailInput.sendKeys("rex-preprints+essoar-author@atypon.com");
        driver.findElement(By.id("sign-in-btn")).click();  //Click on continue

        // Add wait before click on password field
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailInput1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("pass-input")));
        emailInput1.sendKeys("rex-preprints+essoar-author+123");
        driver.findElement(By.id("password-sign-in-btn")).click();  // Click on continue to login

        // 4. Make sure the login was completed through check the user profile menu appearance
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement profileMenu = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("profileMenu")));
        WebElement profilemenu = driver.findElement(By.id("profileMenu"));
        System.out.println(profilemenu.isDisplayed());

        // 5. Click on +Document button
        driver.findElement(By.xpath("//button[span[text()='DOCUMENT']]")).click();

        // 6. Upload PDF file
        WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].style.display='block'; arguments[0].style.opacity=1;", fileInput // remove display:none و opacity:0 from JS to make file visible
        );
        fileInput.sendKeys("C:\\Users\\ashammout\\Downloads\\essoar.15002645_v1.pdf");

        // 7. Wait for the extraction page then click on Submit
        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(90));
        WebElement submitButton = wait3.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button.submit-btn")));
        submitButton.click();

        // 8. Click on Next
        WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement nextButton = wait4.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-next='next']")));

        nextButton.click();

        // 9. Select Subject area
        WebDriverWait wait5 = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement subjectarea = wait5.until(ExpectedConditions.elementToBeClickable(By.className("pill-checkbox")));

        subjectarea.click();

        // 10. Click on Next
        WebDriverWait wait6 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nextButton2 = wait6.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-next='next']")));
        nextButton2.click();

        // 11. Click on Next
        WebDriverWait wait7 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nextButton3 = wait7.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-next='next']")));
        nextButton3.click();
        // 12. Click on Submit
        WebDriverWait wait8 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement Submitbutton = wait8.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-submit='submit']")));
        Submitbutton.click();
        // 12. Click on Yes,Continue
        WebDriverWait wait9 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement yescontinue = wait9.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-yes-continue='Yes, continue']")));
        yescontinue.click();

        // 13. Verify the In Moderation Info Panel appearance
        WebDriverWait wait10 = new WebDriverWait(driver, Duration.ofSeconds(10));
        Boolean isVisible = wait10.until(ExpectedConditions.visibilityOfElementLocated(By.className("info-panel__wrapper"))).isDisplayed();
        Assert.assertTrue(isVisible);

        WebDriverWait wait11 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement submittedby = wait11.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='m-0']")));
        Assert.assertEquals(submittedby.getText(), "Submitted by: author essoar");

        WebDriverWait wait12 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement license = wait12.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'License:')]")));
        Assert.assertEquals(license.getText(),"License: Non-exclusive, no reuse license - Default");



        driver.quit();
        driver = new ChromeDriver();
        driver.get("https://essopenarchive-org.authorea-stag.literatumonline.com/");
        driver.manage().window().maximize();

        //14. Login using a moderator role
        driver.findElement(By.className("sign-in-label")).click();
        WebDriverWait wait13 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailInput2 = wait13.until(ExpectedConditions.elementToBeClickable(By.id("email-input")));
        emailInput2.click();
        emailInput2.sendKeys("rex-preprints+essoar-moderator@atypon.com");
        driver.findElement(By.id("sign-in-btn")).click();

        WebDriverWait wait14 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailinput3 = wait14.until(ExpectedConditions.elementToBeClickable(By.id("pass-input")));
        emailinput3.sendKeys("rex-preprints+essoar-moderator+123");
        driver.findElement(By.id("password-sign-in-btn")).click();

        //15. Open In Moderation page
        driver.get("https://essopenarchive-org.authorea-stag.literatumonline.com/in-moderation");

       // 16. Click on the article inside the In Moderation page
        WebElement articleLink = driver.findElement(By.xpath("//a[contains(text(),'Does the conversion of biogenic organic nitrates')]"));
        articleLink.click();

        // 17. Click on "ACCEPT" button then on ACCEPT FOR PUBLICATION
        WebDriverWait wait15 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement acceptButton = wait15.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.//span[normalize-space()='Accept']]")));
        acceptButton.click();

        driver.findElement(By.xpath("//button[normalize-space()='YES, ACCEPT FOR PUBLICATION']")).click();

        // 18. assert that the In Moderation page opened automatically after Accept the article

        WebDriverWait wait16 = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement InModeration = wait16.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='In moderation']")));
        assert(InModeration.isDisplayed());
        driver.get("https://essopenarchive-org.authorea-stag.literatumonline.com/action/doLogout");
       // 19. Check the article under PUBLISHED tab

        driver.get("https://essopenarchive-org.authorea-stag.literatumonline.com/");
        WebDriverWait wait17 = new WebDriverWait(driver,Duration.ofSeconds(20));
        WebElement loginbutton = wait17.until(ExpectedConditions.elementToBeClickable(By.className("sign-in-label")));
        loginbutton.click();

        WebDriverWait wait18 = new WebDriverWait(driver,Duration.ofSeconds(20));
        WebElement emailinput4 = wait18.until(ExpectedConditions.elementToBeClickable(By.id("email-input")));
        emailinput4.click();
        emailinput4.sendKeys("rex-preprints+essoar-author@atypon.com");
        driver.findElement(By.id("sign-in-btn")).click();  //Click on continue

        WebDriverWait wait19 = new WebDriverWait(driver,Duration.ofSeconds(20));
        WebElement emailinput5 = wait19.until(ExpectedConditions.elementToBeClickable(By.id("pass-input")));
        emailinput5.click();

        emailinput5.sendKeys("rex-preprints+essoar-author+123");
        driver.findElement(By.id("password-sign-in-btn")).click();  // Click on continue to login

        driver.get("https://essopenarchive-org.authorea-stag.literatumonline.com/my-documents"); // Open My Documents page

        WebDriverWait wait20 = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement publishedtab = wait20.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,'nav-link') and contains(.,'Published')]")));
        publishedtab.click();

        WebDriverWait wait21 = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement article = wait21.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(@href,'/doi/full/') and contains(@aria-label,'Does the conversion')]")));

        Assert.assertTrue(article.isDisplayed());


    }
}






