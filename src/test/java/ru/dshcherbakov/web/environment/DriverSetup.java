package ru.dshcherbakov.web.environment;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import java.net.MalformedURLException;
import java.net.URI;


class DriverSetup {

    private RemoteWebDriver driver;

     void startDriver() throws MalformedURLException {
        SelenideLogger.addListener("allure", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        capabilities.setVersion("64.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        driver = new RemoteWebDriver(
                URI.create("http://0.0.0.0:4444/wd/hub").toURL(), capabilities);
        WebDriverRunner.setWebDriver(driver);
    }

    SessionId getSessionId() {
        return  driver.getSessionId();
    }

    void stopDriver() {
        WebDriverRunner.closeWebDriver();
    }

}
