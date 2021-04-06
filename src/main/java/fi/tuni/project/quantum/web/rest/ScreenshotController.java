package fi.tuni.project.quantum.web.rest;

import fi.tuni.project.quantum.service.CreatePdfFromUrl;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScreenshotController {

    @Autowired
    private CreatePdfFromUrl createPdfFromUrl;

    @RequestMapping("/screenshots")
    //    public @ResponseBody byte[] createScreenshotPDF(@RequestParam("url") String url) throws IOException {
    public @ResponseBody byte[] createScreenshotPDF() throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PDStream pdStream = null;
        try (PDDocument pdDoc = createPdfFromUrl.createPdf("https://google.com")) {
            pdStream = new PDStream(pdDoc);
        }
        Objects.requireNonNull(pdStream);
        return pdStream.toByteArray();
    }
}
