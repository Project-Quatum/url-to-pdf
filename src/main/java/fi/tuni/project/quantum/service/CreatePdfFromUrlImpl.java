package fi.tuni.project.quantum.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.annotation.PostConstruct;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CreatePdfFromUrlImpl implements CreatePdfFromUrl {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreatePdfFromUrlImpl.class);

    private ChromeOptions chromeOptions;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void init() throws IOException {
        File chromeDriver = new ClassPathResource("webdriver/chromedriver_mac64").getFile();
        System.setProperty("webdriver.chrome.driver", chromeDriver.getPath());
        // Use headless mode for the ChromeDriver
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
    }

    @Override
    public ByteArrayOutputStream createPdf() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);

            PDImageXObject pdImage = PDImageXObject.createFromFile("test.png", doc);
            try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
                contents.drawImage(pdImage, 20, 20);
            }

            doc.save(outputStream);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return outputStream;
    }

    @Override
    public ByteArrayOutputStream createPdf(String url) {
        //        if (StringUtils.isBlank(url) || Objects.isNull(chromeOptions)) {
        //            return new PDDocument();
        //        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PDDocument doc = null;
        WebDriver driver = null;
        try {
            doc = new PDDocument();
            driver = new ChromeDriver(chromeOptions);
            PDPage page = new PDPage();
            doc.addPage(page);

            PDImageXObject pdImage = PDImageXObject.createFromUrl(url, doc, driver);
            try (PDPageContentStream contents = new PDPageContentStream(doc, page)) {
                contents.drawImage(pdImage, 20, 20);
            }

            doc.save(outputStream);
            doc.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (!Objects.isNull(driver)) {
                driver.close();
            }
        }
        return outputStream;
    }
}
