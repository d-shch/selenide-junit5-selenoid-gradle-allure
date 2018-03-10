package ru.dshcherbakov.web.environment;

import io.qameta.allure.model.Link;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.addLinks;

public class TestBase {

    private static DriverSetup driver = new DriverSetup();
    private static long testStartTime;
    private static long startSuiteTime;
    private Link mylink = new Link();


    @BeforeAll
    static void init() throws MalformedURLException {
        driver.startDriver();
        System.out.println("Video name:" + driver.getSessionId() + ".mp4");
        startSuiteTime = System.currentTimeMillis();
        open("https://www.google.ru/");
    }

    @AfterAll
    static  void tearDown() {
        driver.stopDriver();
    }

    @BeforeEach
    public void failTimeOnVideo() {
        testStartTime = System.currentTimeMillis();
        addLinks(addVideoLink());
    }

    private static String getStartTestTime() {
        long failedTime = testStartTime - getStartSuiteTime();
        return (new SimpleDateFormat("mm:ss:SSS")).format(new Date(failedTime));
    }

    private static long getStartSuiteTime() {
        return startSuiteTime;
    }

    private Link addVideoLink() {
        mylink.setName("Test start time: " + getStartTestTime() + ". CLICK_TO_WATCH_THE_VIDEO");
        mylink.setUrl("http://___SELENOID_VIDEO_CONTAINER____:4444/video/" + driver.getSessionId() + ".mp4");
        mylink.setType("video");
        return mylink;
    }

}
