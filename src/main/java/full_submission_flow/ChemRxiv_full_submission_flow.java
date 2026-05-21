package full_submission_flow;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ChemRxiv_full_submission_flow {
    private static WebElement profilemenu;

    public static void main(String[] args)  {

        // 1. Open ChemRxiv home page
        WebDriver driver = new ChromeDriver();
        driver.get("https://chemrxiv-org.authorea-stag.literatumonline.com/");
        driver.manage().window().maximize();

        // 2. Click on LOGIN | REGISTER then fill the email and password to login
        driver.findElement(By.className("sign-in-label")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailinput = wait.until(ExpectedConditions.elementToBeClickable(By.id("email-input")));
        emailinput.click();
        emailinput.sendKeys("rex-preprints+chemrxiv-author@atypon.com");
        driver.findElement(By.id("sign-in-btn")).click();  //Click on continue
        WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement emailinput1 = wait1.until(ExpectedConditions.elementToBeClickable(By.id("pass-input")));
        emailinput1.click();
        emailinput1.sendKeys("rex-preprints+chemrxiv-author+123");
        driver.findElement(By.id("password-sign-in-btn")).click();  // Click on continue to login

        // 3. Make sure the login was completed through check the user profile menu appearance
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement profileMenu = wait2.until(ExpectedConditions.elementToBeClickable(By.id("profileMenu")));
        System.out.println(profileMenu.isDisplayed());

        // 4. Click on +Document button
        driver.findElement(By.xpath("//button[span[text()='DOCUMENT']]")).click();


        // 5. Upload PDF file
        WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].style.display='block'; arguments[0].style.opacity=1;", fileInput // remove display:none و opacity:0 from JS to make file visible
        );

        fileInput.sendKeys("C:\\Users\\ashammout\\Downloads\\chemrxiv.7097960.v1.pdf");


        // 6. Wait for the extraction page then click on Submit
        WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(120));
        WebElement submitbutton = wait3.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.submit-btn")));
        submitbutton.click();

        // 7. Click on Next
        WebDriverWait wait4 = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement nextButton = wait4.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-next='next']")));
        nextButton.click();




    }
}
