package fi.tuni.project.quantum.web.rest;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebsiteCrawl {

    private WebDriver driver;

    public WebsiteCrawl() {
        // Define the location of the chromedriver
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        // Use headless mode for the ChromeDriver
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        this.driver = new ChromeDriver(chromeOptions);
    }

    public void crawl(String url) {
        String pdfPath = "abc.pdf";

        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);

            // TODO: merge the PR and uncomment it here
            //            PDImageXObject pdImage = PDImageXObject.createFromUrl(url, doc, driver);
            PDImageXObject pdImage = PDImageXObject.createFromFile("123", doc);

            // not a big fan of nested try-catch
            try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
                contents.drawImage(pdImage, 20, 20);
            }

            doc.save(pdfPath);
            // TODO: return the doc in form of binary for downloading and delete it

        } catch (IOException e) {
            // TODO: logger
            e.printStackTrace();
        }
        //        try {
        //            // Navigate to the specified directory
        //            driver.navigate().to(url);
        //            // Sleep for 5 seconds in case the website has not fully loaded
        //            Thread.sleep(5000);
        //            // Take the screenshot and copy to the specified directory
        //            File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //            FileUtils.copyFile(src, new File("./test-image.png"));
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //        }
    }

    public void close() {
        // Close after completion
        driver.close();
    }
}
