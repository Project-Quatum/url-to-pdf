package fi.tuni.project.quantum.service;

import java.io.File;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
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
public class DriverServiceImpl implements DriverService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverServiceImpl.class);
    private static String OS = System.getProperty("os.name").toLowerCase();

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public String getDriverLocation() {
        if (resourceLoader == null) {
            LOGGER.error("Can not create a path to the driver because the resource-loader is null");
            return StringUtils.EMPTY;
        }

        String driverPath = StringUtils.EMPTY;
        if (isWindow()) {
            driverPath = "webdriver/chromedriver_win32.exe";
        } else if (isMac()) {
            driverPath = "webdriver/chromedriver_mac64";
        } else if (isUnix()) {
            driverPath = "webdriver/chromedriver_linux64";
        }

        try {
            File chromeDriver = new ClassPathResource(driverPath).getFile();
            return chromeDriver.getPath();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            return StringUtils.EMPTY;
        }
    }

    private boolean isWindow() {
        return OS.contains("win");
    }

    private boolean isMac() {
        return OS.contains("mac");
    }

    private boolean isUnix() {
        return OS.contains("nix") || OS.contains("nux") || OS.contains("aix");
    }
}
