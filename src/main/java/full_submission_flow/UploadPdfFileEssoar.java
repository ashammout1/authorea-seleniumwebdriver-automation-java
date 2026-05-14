package full_submission_flow;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

        WebElement emailInput = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("email-input")));

        // 3.Click and type email then click on continue
        emailInput.click();
        emailInput.sendKeys("rex-preprints+essoar-author@atypon.com");
        driver.findElement(By.id("sign-in-btn")).click();  //Click on continue

        // Add wait before click on password field
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailInput1 = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("pass-input")));
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
        WebElement infopanel = wait10.until(ExpectedConditions.visibilityOfElementLocated(By.className("info-panel__wrapper")));
        System.out.println(infopanel.isDisplayed());


        driver.quit();
        driver = new ChromeDriver();
        driver.get("https://essopenarchive-org.authorea-stag.literatumonline.com/");
        driver.manage().window().maximize();

        //14. Login using a moderator role













    }
}






