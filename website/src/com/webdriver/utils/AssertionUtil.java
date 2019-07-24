package com.webdriver.utils;

import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.webdriver.helper.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.JavascriptExecutor;

/**
 *
 * @author emmanuel
 */
public class AssertionUtil extends DriverManager {

    TakeScreenshot takeScreenshot = new TakeScreenshot();
    public WaitFor wait = new WaitFor();
    private String TCName;
    private String TCDescript;
    public WebDriver driver = DriverManager.getDriver();

    public AssertionUtil(TSParameterObject param) {
        TCName = param.getsTestCaseID();
        TCDescript = param.getsTestCase();
    }

    public void highlightElement(WebElement element) {

        for (int i = 0; i < 3; i++) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].setAttribute('style',arguments[1]);", element, "color: green; border: 5px solid yellow;");
            js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");

        }

    }

    public void clickElement(By waitBy, String description) throws Exception {

        try {

            WebElement elem = wait.waitUntilElementDisplays(waitBy);
            highlightElement(elem);
            elem.click();
            //log info
            System.out.println("Clicked '" + description + "' successfully");

        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot
            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.fail("Error, element '" + description + "' not found after 90 sec", e);
        }

    }

    public void clickElementOnArray1(By waitBy, int index, String description) throws Exception {

        try {

            List<WebElement> elem = wait.waitUntilAllElementsDisplay(waitBy);
            highlightElement(elem.get(index));
            elem.get(index).click();
            //log info
            System.out.println("Clicked '" + description + "' successfully");
        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot

            takeScreenshot.takeScreenshot(TCName, TCDescript);
        }

    }

    public String getString(By locator, String description) throws Exception {

        WebElement element = null;
        String str = null;

        try {

            element = (WebElement) wait.waitUntilElementDisplays(locator);
            str = element.getText();
            //log info
            System.out.println("Clicked '" + description + "' successfully");

        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot
            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.fail("Error, element '" + description + "' not found after 90 sec", e);

        }

        return str;

    }

    public String getString2(WebElement element, String description) throws Exception {

        String str = null;

        try {

            str = element.getText();
            //log info
            System.out.println("Clicked '" + description + "' successfully");

        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot
            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.fail("Error, element '" + description + "' not found after 90 sec", e);

        }

        return str;

    }

    public String getStringonArray2(By locator, String value, String description) throws Exception {

        List<WebElement> elements = wait.waitUntilAllElementsDisplay(locator);
        String str = null;

        try {

            for (WebElement cell : elements) {
                if (cell.getText().equalsIgnoreCase(value)) {
                    System.out.println(cell.getText());
                    str = cell.getText();
                    break;

                }

            }
            //log info
            System.out.println("Clicked '" + description + "' successfully");

        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot
            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.fail("Error, element '" + description + "' not found after 90 sec", e);

        }

        return str;

    }

    public WebElement clickElement2(WebElement element, String description) throws Exception {

        try {

            element.click();
            //log info
            System.out.println("Clicked '" + description + "' successfully");

        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot
            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.fail("Error, element '" + description + "' not found after 90 sec", e);

        }

        return element;

    }

    public WebElement waitElement(By locator, String description) throws Exception {

        WebElement element = null;

        try {

            element = (WebElement) wait.waitUntilElementDisplays(locator);

        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot
            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.fail("Error, element '" + description + "' not found after 90 sec", e);

        }

        return element;

    }

    public WebElement clearElement(By locator, String description) throws Exception {

        WebElement element = null;

        try {

            element = (WebElement) wait.waitUntilElementDisplays(locator);
            element.clear();

        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot
            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.fail("Error, element '" + description + "' not found after 90 sec", e);

        }

        return element;

    }

    public void clickElementOnArray(By locator, String description, String value) throws Exception {

        try {

            Thread.sleep(1000);
            List<WebElement> elements = driver.findElements(locator);

            for (WebElement cell : elements) {
                if (cell.getText().equalsIgnoreCase(value)) {
                    System.out.println(cell.getText() + " found");
                    Thread.sleep(1000);
                    cell.click();
                    break;

                }

            }
            //log info
            System.out.println("Clicked '" + description + "' successfully");

        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot
            Assert.fail("Error, element '" + description + "' not found after 90 sec", e);
            takeScreenshot.takeScreenshot(TCName, TCDescript);
        }

    }

    public void clickElementOnArray2(By locator, String description, String value) throws Exception {

        try {

            List<WebElement> elements = driver.findElements(locator);

            int n = 0;
            boolean dialog = false;

            loop:
            {
                while (dialog == false) {
                    for (WebElement cell : elements) {
                        if (cell.getText().contains(value)) {
                            System.out.println(cell.getText() + " found");
                            cell.click();
                            dialog = true;
                            break;
                        } else {
                            System.out.println(description + dialog + " " + n + " " + cell.getText());
                            Thread.sleep(1000);
                            if (n == 150) {
                                break loop;

                            }
                            n++;
                        }

                    }

                }

            }

            //log info
            System.out.println("Clicked '" + description + "' successfully");

        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot
            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.fail("Error, element '" + description + "' not found after 90 sec", e);
        }

    }

    public void clickElementOnArray3(By locator, String description, String value) throws Exception {

        try {

            List<WebElement> elements = wait.waitUntilAllElementsDisplay(locator);

            for (WebElement cell : elements) {
                if (cell.getText().contains(value)) {
                    System.out.println(cell.getText());
                    Thread.sleep(1000);
                    cell.click();
                    break;

                }

            }

            //log info
            System.out.println("Clicked '" + description + "' successfully");

        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot
            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.fail("Error, element '" + description + "' not found after 90 sec", e);
        }

    }

    public boolean getBooleanOnArray(By locator, String description, String value) throws Exception {

        boolean available = false;

        try {

            List<WebElement> rows = driver.findElements(locator);

            for (WebElement cell : rows) {
                if (cell.getText().equalsIgnoreCase(value)) {
                    System.out.println(cell.getText() + " Found-");
                    available = true;
                    break;

                }

            }
            //log info
            System.out.println("Boolean found '" + description + "' successfully");

        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");

        }

        return available;

    }

    public boolean getBoolean(By locator, String description) throws Exception {

        boolean available = false;

        try {

            available = wait.isElementPresent(locator);

            //log info
            System.out.println("Boolean '" + description + "' found successfully");

        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");

        }

        return available;

    }

    public WebElement getElement(By locator, String description, String value) throws Exception {

        WebElement element = null;

        try {

            List<WebElement> elements = driver.findElements(locator);

            for (int i = 0; i < elements.size(); i++) {
                if (elements.get(i).getText().equalsIgnoreCase(value)) {
                    element = elements.get(i);
                    System.out.println(element.getText() + " " + i);

                }

            }
            //log info
            System.out.println("Clicked '" + description + "' successfully");

        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot
            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.fail("Error, element '" + description + "' not found after 90 sec", e);
        }

        return element;

    }

    public List<WebElement> getElements(By locator, String description) throws Exception {

        List<WebElement> elements = null;

        try {

            //elements = driver.findElements(locator);
            elements = wait.waitUntilAllElementsDisplay(locator);

            //log info
            System.out.println("Got '" + description + "' successfully");

        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot

            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.fail("Error, element '" + description + "' not found after 90 sec", e);
        }

        return elements;

    }

    public List<WebElement> getElements2(By locator, String value, String description) throws Exception {

        List<WebElement> list = new ArrayList<WebElement>();

        try {

            List<WebElement> elements = wait.waitUntilAllElementsDisplay(locator);
            WebElement element = null;

            for (int i = 0; i < elements.size(); i++) {
                if (elements.get(i).getText().contains(value)) {
                    element = elements.get(i);
                    list.add(element);

                }

            }

            //log info
            System.out.println("Got '" + description + "' successfully");

        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot

            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.fail("Error, element '" + description + "' not found after 90 sec", e);
        }

        return list;

    }

    public List<WebElement> getElements3(By locator, String description) throws Exception {

        List<WebElement> elements = null;

        try {

            elements = driver.findElements(locator);

            //log info
            System.out.println("Got '" + description + "' successfully");

        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot

            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.fail("Error, element '" + description + "' not found after 90 sec", e);
        }

        return elements;

    }

    public List<WebElement> getElements4(By locator, By locator2, String description) throws Exception {

        List<WebElement> elements = null;

        try {

            WebElement element = wait.waitUntilElementDisplays(locator);

            //elements = driver.findElements(locator);
            elements = element.findElements(locator2);

            //log info
            System.out.println("Got '" + description + "' successfully");

        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.fail("Error, element '" + description + "' not found after 90 sec", e);
            //move to element then take snapshot

        }

        return elements;

    }

    public int getIndex(By locator, String description, String value) throws Exception {

        WebElement element = null;
        int index = 0;

        try {

            List<WebElement> elements = driver.findElements(locator);

            for (int i = 0; i < elements.size(); i++) {
                if (elements.get(i).getText().equalsIgnoreCase(value)) {
                    element = elements.get(i);
                    index = i;
                    System.out.println(element.getText() + " " + i);

                }

            }
            //log info
            System.out.println("Clicked '" + description + "' successfully");

        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot

            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.fail("Error, element '" + description + "' not found after 90 sec", e);
        }

        return index;

    }

    public WebElement getElementOnArray(By locator, String description, String value) throws Exception {

        WebElement element = null;

        try {

            List<WebElement> elements = driver.findElements(locator);

            for (int i = 0; i < elements.size(); i++) {
                if (elements.get(i).getText().equalsIgnoreCase(value)) {
                    element = elements.get(i);
                    System.out.println(element.getText() + " " + i);

                }

            }
            //log info
            System.out.println("Clicked '" + description + "' successfully");

        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot

            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.fail("Error, element '" + description + "' not found after 90 sec", e);
        }

        return element;

    }

    public WebElement getElementOnArray2(By locator, String description, String value) throws Exception {

        WebElement element = null;
        int count = 0;

        try {

            List<WebElement> elements = driver.findElements(locator);

            for (WebElement cell : elements) {
                if (cell.getText().equals(value)) {
                    System.out.println(cell.getText() + " Found");
                    break;

                }
                count++;

            }

            element = elements.get(count);
            System.out.println(count);
            //log info
            System.out.println("Clicked '" + description + "' successfully");

        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot

            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.fail("Error, element '" + description + "' not found after 90 sec", e);
        }

        return element;

    }

    public Object[] findValues(String value, final By locator, String description) throws InterruptedException, Exception {

        WebElement element = null;
        boolean available = false;
        int count = 0;
        List<WebElement> elementList = new ArrayList<WebElement>();
        List<Boolean> availableList = new ArrayList<Boolean>();

        try {

            List<WebElement> elements = driver.findElements(locator);
            System.out.println("element count: " + elements.size());

            for (WebElement cell : elements) {
                if (cell.getText().equalsIgnoreCase(value)) {
                    System.out.println(cell.getText() + " Found-");
                    available = true;
                    break;

                }
                count++;
            }

            element = elements.get(count);

            elementList.add(element);
            availableList.add(available);
            System.out.println("boolean is: " + available);

        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot
            takeScreenshot.takeScreenshot(TCName, TCDescript);
        }

        return new Object[]{elementList, availableList};

    }

    public Object[] findValues2(String value, final By locator, String description) throws InterruptedException, Exception {

        int count = 0;
        boolean available = false;
        List<Integer> countList = new ArrayList<Integer>();
        List<Boolean> availableList = new ArrayList<Boolean>();

        try {

            List<WebElement> elements = driver.findElements(locator);

            for (WebElement cell : elements) {
                if (cell.getText().equalsIgnoreCase(value)) {
                    System.out.println(cell.getText() + " Found -");
                    available = true;
                    break;

                }
                count++;
            }

            countList.add(count);
            availableList.add(available);

        } catch (Exception e) {
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            takeScreenshot.takeScreenshot(TCName, TCDescript);
        }

        return new Object[]{countList, availableList};

    }

    public void clickToLaunch(By waitBy, By toBeLaunched, String description, String description_launch) throws Exception {

        try {

            WebElement elem = wait.waitUntilElementDisplays(waitBy);
            highlightElement(elem);
            elem.click();

            try {
                WebElement elem2 = wait.waitUntilElementDisplays(toBeLaunched);
            } catch (Exception e) {
                System.out.println("Error, when clicked '" + description + "' it launched unexpected view, Expected condition '" + description_launch + "' not found after 90 sec");
                //move to element then take snapshot
                takeScreenshot.takeScreenshotFocused(TCName, TCDescript, elem);
                Assert.assertTrue(false);

            }

            //log info
            System.out.println("Clicked '" + description + "' successfully");
        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot

            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.assertTrue(false);
        }

    }

    public void enterText(By waitBy, String description, String input) throws Exception {

        try {

            WebElement elem = wait.waitUntilElementDisplays(waitBy);
            highlightElement(elem);
            elem.sendKeys(input);

            //log info
            System.out.println("Entered '" + description + "' with value '" + input + "' successfully");

        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec, elapsed");
            //move to element then take snapshot
            takeScreenshot.takeScreenshot(TCName, TCDescript);
            //Assert.assertTrue(false);
            Assert.fail("Error, element '" + description + "' not found after 90 sec", e);

        }

    }

    public void enterTextOnArray(By locator, String description, String value, String str) throws Exception {


        try {

            List<WebElement> rows = driver.findElements(locator);

            for (WebElement cell : rows) {
                if (cell.getText().equalsIgnoreCase(value)) {
                    System.out.println(cell.getText() + " Found-");
                    cell.sendKeys(str);
                    break;

                }

            }
            //log info
            System.out.println("Sent '" + description + "' successfully");

        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot

            takeScreenshot.takeScreenshot(TCName, TCDescript);
        }

    }

    public void clearTextOnArray(By locator, String description, String value) throws Exception {


        try {

            List<WebElement> rows = driver.findElements(locator);

            for (WebElement cell : rows) {
                if (cell.getText().equalsIgnoreCase(value)) {
                    System.out.println(cell.getText() + " Found-");
                    cell.clear();
                    break;

                }

            }
            //log info
            System.out.println("Sent '" + description + "' successfully");

        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot

            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.fail("Error, element '" + description + "' not found after 90 sec", e);
        }

    }

    public void enterTextWitouValidating(By waitBy, String description, String input) throws Exception {

        try {

            WebElement elem = wait.waitUntilElementDisplays(waitBy);
            highlightElement(elem);
            String entered = "";
            try {
                elem.sendKeys(input);
                Thread.sleep(1000);
                entered = elem.getText();
                // Assert.assertEquals(entered, input);
            } catch (AssertionError e) {

                System.out.println("Error, Unable to enter text '" + input + "' at '" + description + "' section!, Expected ['" + input + "'], but found ['" + entered + "']");
                //move to element then take snapshot
                takeScreenshot.takeScreenshotFocused(TCName, TCDescript, elem);
                Assert.assertEquals(entered, input);
            }

            //log info
            System.out.println("Entered '" + description + "' with value '" + input + "' successfully");
        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec, elapsed");
            //move to element then take snapshot
            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.assertTrue(false);
        }

    }

    public void enterTextOnDropDown(By waitBy, String description, String input) throws Exception {

        try {

            WebElement elem = wait.waitUntilElementDisplays(waitBy);
            highlightElement(elem);
            String entered = "";
            try {
                elem.sendKeys(input);
                Thread.sleep(1000);
                entered = elem.getText();
                //Assert.assertEquals(entered, input);
            } catch (AssertionError e) {

                System.out.println("Error, Unable to enter text '" + input + "' at '" + description + "' section!, Expected ['" + input + "'], but found ['" + entered + "']");
                //move to element then take snapshot
                takeScreenshot.takeScreenshotFocused(TCName, TCDescript, elem);
                // Assert.assertEquals(entered, input);
            }

            //log info
            System.out.println("Entered '" + description + "' with value '" + input + "' successfully");
        } catch (Exception e) {
            //log error and take snapshot
            System.out.println("Error, element '" + description + "' not found after 90 sec, elapsed");
            //move to element then take snapshot
            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.assertTrue(false);
        }
    }

    public void assertStringEqualsByText(By waitBy, String description, String expectedVal) throws Exception {

        try {

            WebElement elem = wait.waitUntilElementDisplays(waitBy);
            highlightElement(elem);
            String strActual = elem.getText();

            //get only numberic
            Pattern pattern = Pattern.compile("[^a-z A-Z]");
            Matcher matcher = pattern.matcher(strActual);
            strActual = matcher.replaceAll("");

            try {
                Assert.assertEquals(strActual, expectedVal);
            } catch (AssertionError e) {

                System.out.println("Error, Comparison does not match, Actual '" + strActual + "' does not equals to Expected  '" + expectedVal + "' at '" + description + "' section!");
                //move to element then take snapshot
                takeScreenshot.takeScreenshotFocused(TCName, TCDescript, elem);
                Assert.assertEquals(strActual, expectedVal);
            }
            //log info
            System.out.println("Compared '" + description + "' successfully, such that Actual '" + description + "' '" + strActual + "' equals Expected '" + description + "' of '" + expectedVal + "' ");

        } catch (Exception e) {
            //log error and take snapshot
            String log = "Error, element '" + description + "' not found after 90 sec";
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot

            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.assertTrue(false, log);
        }

    }

    public void assertStringEqualsByText2(WebElement element, String description, String expectedVal) throws Exception {

        try {

            highlightElement(element);
            String strActual = element.getText();

            //get only numberic
            Pattern pattern = Pattern.compile("[^a-z A-Z]");
            Matcher matcher = pattern.matcher(strActual);
            strActual = matcher.replaceAll("");

            try {
                Assert.assertEquals(strActual, expectedVal);
            } catch (AssertionError e) {

                System.out.println("Error, Comparison does not match, Actual '" + strActual + "' does not equals to Expected  '" + expectedVal + "' at '" + description + "' section!");
                //move to element then take snapshot
                takeScreenshot.takeScreenshotFocused(TCName, TCDescript, element);
                Assert.assertEquals(strActual, expectedVal);
            }
            //log info
            System.out.println("Compared '" + description + "' successfully, such that Actual '" + description + "' '" + strActual + "' equals Expected '" + description + "' of '" + expectedVal + "' ");

        } catch (Exception e) {
            //log error and take snapshot
            String log = "Error, element '" + description + "' not found after 90 sec";
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot

            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.assertTrue(false, log);
        }

    }

    public void assertStringEqualsByPlainText(By waitBy, String description, String expectedVal) throws Exception {

        try {

            WebElement elem = wait.waitUntilElementDisplays(waitBy);
            highlightElement(elem);
            String strActual = elem.getText();

            try {
                Assert.assertEquals(strActual, expectedVal);
            } catch (AssertionError e) {

                System.out.println("Error, Comparison does not match, Actual '" + strActual + "' does not equals to Expected  '" + expectedVal + "' at '" + description + "' section!");
                //move to element then take snapshot
                takeScreenshot.takeScreenshotFocused(TCName, TCDescript, elem);
                Assert.assertEquals(strActual, expectedVal);
            }
            //log info
            System.out.println("Compared '" + description + "' successfully, such that Actual '" + description + "' '" + strActual + "' equals Expected '" + description + "' of '" + expectedVal + "' ");
        } catch (Exception e) {
            //log error and take snapshot
            String log = "Error, element '" + description + "' not found after 90 sec";
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot

            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.assertTrue(false, log);
        }

    }

    public void assertStringEquals(String actual, String expectedVal) throws Exception {

        try {

            Assert.assertEquals(actual, expectedVal);
        } catch (AssertionError e) {

            System.out.println("Error, Comparison does not match, Actual '" + actual + "' does not equals to Expected  '" + expectedVal);
            //move to element then take snapshot
            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.assertEquals(actual, expectedVal);
        }

    }

    public void assertStringEqualsByAttribute(By waitBy, String attribute, String description, String expectedVal) throws Exception {

        try {

            WebElement elem = wait.waitUntilElementDisplays(waitBy);
            highlightElement(elem);
            String strActual = elem.getAttribute(attribute);

            //get only non-numuric
            Pattern pattern = Pattern.compile("[^a-z A-Z]");
            Matcher matcher = pattern.matcher(strActual);
            strActual = matcher.replaceAll("");

            try {
                Assert.assertEquals(strActual, expectedVal);
            } catch (AssertionError e) {

                System.out.println("Error, Comparison does not match, Actual '" + strActual + "' does not equals to Expected  '" + expectedVal + "' at '" + description + "' section!");
                //move to element then take snapshot
                takeScreenshot.takeScreenshotFocused(TCName, TCDescript, elem);
                Assert.assertEquals(strActual, expectedVal);
            }
            //log info
            System.out.println("Compared '" + description + "' successfully, such that Actual '" + description + "' '" + strActual + "' equals Expected '" + description + "' of '" + expectedVal + "' ");
        } catch (Exception e) {
            //log error and take snapshot
            String log = "Error, element '" + description + "' not found after 90 sec";
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot
            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.assertTrue(false, log);
        }

    }

    public void assertNumericEqualsByText(By waitBy, String description, String expectedVal) throws Exception {

        try {

            WebElement elem = wait.waitUntilElementDisplays(waitBy);
            highlightElement(elem);
            String strActual = elem.getText();

            //get only numberic
            Pattern pattern = Pattern.compile("[^0-9]");
            Matcher matcher = pattern.matcher(strActual);
            strActual = matcher.replaceAll("");

            try {
                Assert.assertEquals(strActual, expectedVal);
            } catch (AssertionError e) {

                System.out.println("Error, Comparison does not match, Actual '" + strActual + "' does not equals to Expected  '" + expectedVal + "' at '" + description + "' section!");
                //move to element then take snapshot
                takeScreenshot.takeScreenshotFocused(TCName, TCDescript, elem);
                Assert.assertEquals(strActual, expectedVal);
            }
            //log info
            System.out.println("Compared '" + description + "' successfully, such that Actual '" + description + "' '" + strActual + "' equals Expected '" + description + "' of '" + expectedVal + "' ");
        } catch (Exception e) {
            //log error and take snapshot
            String log = "Error, element '" + description + "' not found after 90 sec";
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot

            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.assertTrue(false, log);

        }

    }

    public void assertNumericEqualsByAttribute(By waitBy, String attribute, String description, String expectedVal) throws Exception {

        try {

            WebElement elem = wait.waitUntilElementDisplays(waitBy);
            highlightElement(elem);
            String strActual = elem.getAttribute(attribute);

            //get only numberic
            Pattern pattern = Pattern.compile("[^0-9]");
            Matcher matcher = pattern.matcher(strActual);
            strActual = matcher.replaceAll("");

            try {
                Assert.assertEquals(strActual, expectedVal);
            } catch (AssertionError e) {

                System.out.println("Error, Comparison does not match, Actual '" + strActual + "' does not equal to Expected  '" + expectedVal + "' at '" + description + "' section!");
                //move to element then take snapshot
                takeScreenshot.takeScreenshotFocused(TCName, TCDescript, elem);
                Assert.assertEquals(strActual, expectedVal);
            }
            //log info
            System.out.println("Compared '" + description + "' successfully, such that Actual '" + description + "' '" + strActual + "' equal Expected '" + description + "' of '" + expectedVal + "' ");
        } catch (Exception e) {
            //log error and take snapshot
            String log = "Error, element '" + description + "' not found after 90 sec";
            System.out.println("Error, element '" + description + "' not found after 90 sec");
            //move to element then take snapshot
            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.assertTrue(false, log);

        }

    }

    public void assertTrueContains(String description, String actualVal, String expectedVal) throws Exception {

        try {
            Assert.assertTrue(actualVal.contains(expectedVal), "'" + description + "' ");
        } catch (AssertionError e) {

            System.out.println("Error, Comparison does not match, Actual '" + actualVal + "' does not conatian Expected  '" + expectedVal + "' at '" + description + "' section!");
            //move to element then take snapshot
            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.assertTrue(actualVal.contains(expectedVal), "'" + description + "' ");
        }

    }

    public void assertTrueMethod(boolean condition, String description) throws Exception {

        try {
            Assert.assertTrue(condition, description);
        } catch (AssertionError e) {
            System.out.println(description);
            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.assertTrue(condition, description);
        }

    }

    public void assertFalseMethod(boolean condition, String description) throws Exception {

        try {
            Assert.assertFalse(condition, description);
        } catch (AssertionError e) {
            System.out.println(description);
            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.assertFalse(condition, description);
        }
    }

    public void assertObjectNotEqual(Object actual, Object expected, String description) throws Exception {

        try {
            Assert.assertNotEquals(actual, expected, description);
        } catch (AssertionError e) {
            System.out.println(description);
            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.assertNotEquals(actual, expected, description);
        }

    }

    public void assertObjectEquals(Object actual, Object expected, String description) throws Exception {

        try {
            Assert.assertEquals(actual, expected, description);
        } catch (AssertionError e) {
            System.out.println(description);
            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.assertEquals(actual, expected, description);
        }

    }

    public void selectByVisibleTextDropdown(By waitBy, String visibleText) throws Exception {

        String sActual = "";

        try {
            WebElement elem = wait.waitUntilElementDisplays(waitBy);
            highlightElement(elem);
            Select dropdown = new Select(driver.findElement(waitBy));
            dropdown.selectByVisibleText(visibleText);

            Thread.sleep(1000);
            WebElement actual = dropdown.getFirstSelectedOption();
            sActual = actual.getText().trim();

            Assert.assertEquals(sActual, visibleText);

        } catch (AssertionError e) {
            System.out.println("Failed to validate that the selected is as expected: " + sActual + " == " + visibleText);
            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.assertEquals(sActual, visibleText);
        }

    }


    public void selectByVisibleTextDropdown2(WebElement element, String visibleText) throws Exception {

        String sActual = "";

        try {
            highlightElement(element);
            Select dropdown = new Select(element);
            dropdown.selectByVisibleText(visibleText);

            Thread.sleep(1000);
            WebElement actual = dropdown.getFirstSelectedOption();
            sActual = actual.getText().trim();

            Assert.assertEquals(sActual, visibleText);

        } catch (AssertionError e) {
            System.out.println("Failed to validate that the selected is as expected: " + sActual + " == " + visibleText);
            takeScreenshot.takeScreenshot(TCName, TCDescript);
            Assert.assertEquals(sActual, visibleText);
        }

    }


    public void selectByIndexDropdown(By waitBy, int index) throws Exception {

        try {
            WebElement elem = wait.waitUntilElementDisplays(waitBy);
            highlightElement(elem);
            Select dropdown = new Select(driver.findElement(waitBy));
            dropdown.selectByIndex(index);

        } catch (Exception e) {
            System.out.println("Failed to select by index");
            takeScreenshot.takeScreenshot(TCName, TCDescript);

        }

    }

    public void switchToWindow() throws Exception {

        try {

            String parentWindow = driver.getWindowHandle();
            Set<String> handles2 = driver.getWindowHandles();
            for (String windowHandle : handles2) {
                if (!windowHandle.equals(parentWindow)) {
                    driver.switchTo().window(windowHandle);

          /*for(String d:driver.getWindowHandles())
          {
              driver.switchTo().window(d);
              if(driver.getCurrentUrl().contains("cheap-flights"));
              break;

          }*/
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to select by index");
            takeScreenshot.takeScreenshot(TCName, TCDescript);

        }


    }


    public void scrollDown() throws Exception {

        try {

            JavascriptExecutor jse = (JavascriptExecutor) driver;
            //jse.executeScript("window.scrollBy(0,250)");
            jse.executeScript("scroll(0,250)");

        } catch (Exception e) {
            System.out.println("Failed to select by index");
            takeScreenshot.takeScreenshot(TCName, TCDescript);

        }
    }

    public void getTitle() throws Exception {

        try {
            // new Actions(driver).sendKeys(driver.findElement(By.tagName("html")), Keys.CONTROL).sendKeys(driver.findElement(By.tagName("html")), Keys.NUMPAD2).build().perform();
            String pagetitle = driver.getTitle();
            System.out.println("page tittle" + pagetitle);
        } catch (Exception e) {
            System.out.println("Failed to select by index");
            takeScreenshot.takeScreenshot(TCName, TCDescript);

        }
    }

        public String getStringOfButton(By locator, String description) throws Exception {

            WebElement element = null;
            String str = null;

            try {

                element = (WebElement) wait.waitUntilElementDisplays(locator);
                str = element.getAttribute("value");
                //log info
                System.out.println("Clicked '" + description + "' successfully");

            } catch (Exception e) {
                //log error and take snapshot
                System.out.println("Error, element '" + description + "' not found after 90 sec");
                //move to element then take snapshot
                takeScreenshot.takeScreenshot(TCName, TCDescript);
                Assert.fail("Error, element '" + description + "' not found after 90 sec", e);

            }

            return str;

        }

   }
