package autotests;

import entity.User;
import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import autotests.pages.HomePage;
import utils.Util;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.*;

@Log4j2
public class SampleTestNgTest extends TestNgTestBase {

  private HomePage homepage;

  @BeforeMethod
  public void initPageObjects() {
    homepage = PageFactory.initElements(driver, HomePage.class);
  }

  @Test
  @Flaky
  @Severity(SeverityLevel.NORMAL)
  @Story("New story")
  @Feature("Test feature")
  public void testHomePageHasAHeader() {
    Util.step("First step", () -> driver.get(baseUrl));
    Util.step("Verify header", () -> {
      log.debug("GET HEADER TEXT: {}", homepage.header.getText());
      log.info("GET TEXT RETURNED - {}", () -> homepage.header.getText());
      Assert.assertFalse("".equals(homepage.header.getText()));
    });
  }

  @Test
  @Severity(SeverityLevel.CRITICAL)
  @Story("New story")
  @Feature("Test feature")
  public void testHomePageHasAHeader1() throws URISyntaxException {
    Util.step("First step", () -> {
      log.info("LOG MSG");
      driver.get(baseUrl);});
    Util.step("Verify header 1", () -> Assert.assertFalse(false));

    assertThat(homepage.buttons).filteredOn( btn -> btn.getText().contains("o") )
            .containsOnly(homepage.buttons.get(0));

    assertThat(homepage.logoSubText.getText()).contains("Ukr", "aine");

    assertThat(homepage.buttons.get(0)).extracting("value")
            .contains("Search")
            .doesNotContain("Lucky");

    assertThat(homepage.logoSubText.getText()).as("Sub Logo").isEqualTo("Ukraine");

    assertThat(homepage.logoSubText.getText()).isNotEqualTo("USA");

    ZonedDateTime firstOfJanuary2000InUTC = ZonedDateTime.parse("2000-01-01T00:00:00Z");
    assertThat(firstOfJanuary2000InUTC).isBetween("1999-12-31T23:59:59Z", "2000-01-01T00:00:01Z")
            .isBetween("2000-01-01T00:00:00Z", "2000-01-01T00:00:01Z")
            .isBetween("1999-12-31T23:59:59Z", "2000-01-01T00:00:00Z")
            .isBetween("2000-01-01T00:00:00Z", "2000-01-01T00:00:00Z")
            .isStrictlyBetween("1999-12-31T23:59:59Z", "2000-01-01T00:00:01Z");

    assertThat(new URI("http://assertj.org"))
            .hasHost("assertj.org")
            .hasNoFragment()
            .hasNoPort()
            .hasPath("")
            .hasNoQuery()
            .hasNoUserInfo();

    assertThat(homepage.logoSubText.getText()).startsWith("U").endsWith("ne").hasSize(6);

    assertThat(homepage.logoSubText.getText()).containsOnlyOnce("Uk");

    assertThat(homepage.logoSubText.getText()).isEqualToIgnoringCase("ukraine").hasSameSizeAs("1234567");

    assertThat("Prius").matches("..i.s").doesNotMatch(".*d");

    assertThat(homepage.logoSubText.getText()).isNotEmpty();

    assertThat(homepage.logoSubText.getText()).doesNotContainPattern("Fr.ud")
            .doesNotContainPattern(Pattern.compile("Fr.ud"));

    User user = new User (u -> {
      u.setId((long) 1);
      u.setLogin("login");
      u.setName("Username");
      u.setPassword("qwerty12345");
    });

    List<User> userList = new ArrayList();
    userList.add(user);
    userList.add(user);
    userList.add(user);
    userList.add(user);
    userList.add(user);
 try {
   assertThat(filter(userList).with("name", "Username").get()).containsOnly(userList.get(0));
 } catch (AssertionError e){
   log.error("ERROR IN ASSERTION!! ", e.getMessage());
 }

      assertThat(user).returns("login", from(User::getLogin))
              .returns("Username", from(User::getName))
              .returns("qwerty12345", User::getPassword);
  }

  @AfterMethod(alwaysRun = true)
  public void afterMethod() {
    Util.step("Add screenshot", () -> Allure.addAttachment("Screenshot", "image/png",
            new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)), "png"));
  }

}
