package fi.tuni.project.quantum.web.rest;

import fi.tuni.project.quantum.service.CreatePdfFromUrl;
import fi.tuni.project.quantum.web.rest.errors.ApiError;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScreenshotController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScreenshotController.class);

    private static final String ERROR_MESSAGE = "Cannot create a pdf file because of an invalid url";

    @Autowired
    private CreatePdfFromUrl createPdfFromUrl;

    @PostMapping(value = "/screenshots")
    public ResponseEntity<Object> createScreenshotPDF(@RequestBody String url) {
        if (!validUrl(url)) {
            ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ERROR_MESSAGE);
            return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
        }

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=screenshot.pdf");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        ByteArrayOutputStream outputStream = createPdfFromUrl.createPdf(url);
        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        return ResponseEntity
            .ok()
            .headers(header)
            .contentLength(outputStream.size())
            .contentType(MediaType.parseMediaType("application/octet-stream"))
            .body(resource);
    }

    private boolean validUrl(String url) {
        if (StringUtils.isBlank(url)) {
            return false;
        }

        try {
            new URL(url).toURI();
            return true;
        } catch (Exception e) {
            LOGGER.error("Invalid URL {}", url);
            return false;
        }
    }
}
