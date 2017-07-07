package autotests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

/**
 * Sample page
 */
public class HomePage extends Page {

  @FindBy(how = How.TAG_NAME, using = "h1")
  @CacheLookup
  public WebElement header;

  @FindBy(id = "hplogo")
  public WebElement logoText;

  @FindBy(css = ".logo-subtext")
  public WebElement logoSubText;

  @FindBy(css = "div > center > input")
  public List<WebElement> buttons;

  public HomePage(WebDriver webDriver) {
    super(webDriver);
  }
}
