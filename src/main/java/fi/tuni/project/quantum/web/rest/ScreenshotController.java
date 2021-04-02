package fi.tuni.project.quantum.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScreenshotController {

    @RequestMapping("/screenshots")
    public String index(@RequestParam("url") String url) {
        return url;
    }
}
