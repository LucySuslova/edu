package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;

import java.io.ByteArrayInputStream;

/**
 * Created by lsu on 6/27/17.
 */
public class Util {

    @Step("{title}")
    public static void step(String title, Runnable code) {
        code.run();
    }

    public static void addAttachment(String name, String mime, ByteArrayInputStream content, String extension) {
        Allure.addAttachment(name, mime, content, extension);
    }

}
