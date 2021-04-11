package fi.tuni.project.quantum.web.rest;

import org.apache.commons.lang3.StringUtils;

public class ScreenshotUrl {

    private String url;

    public ScreenshotUrl() {
        this.url = StringUtils.EMPTY;
    }

    public ScreenshotUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
