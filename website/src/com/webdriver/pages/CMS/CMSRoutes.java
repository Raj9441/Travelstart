package pages.CMS;

import com.webdriver.helper.DriverManager;
import com.webdriver.pages.CMS.CMSDestroyPage;
import com.webdriver.pages.CMS.CMSLogout;
import com.webdriver.utils.*;
import com.webdriver.utils.AssertionUtil;
import com.webdriver.utils.CMS_Object;
import com.webdriver.utils.MarketingCMSDetails;
import com.webdriver.utils.PropertyFileReader;
import com.webdriver.utils.TSParameterObject;
import com.webdriver.utils.WaitFor;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;

import java.util.*;
import java.util.List;

import java.util.Date;

public class CMSRoutes {

    public WebDriver driver = DriverManager.getDriver();
    private WaitFor wait = new WaitFor();
    public final String random = "" + (new Date().getTime());
    public MarketingCMSDetails cmsDetails = new MarketingCMSDetails();
    //public CMSVerfyLandingPage vLandingPage;
    PropertyFileReader prop;
    private CMS_Object cms_object;
    private AssertionUtil assertionUtil;
    private TSParameterObject tsParameterObject;


    //By navRouteLInk=null;
    By navRoutePages = null;
    By navRoutes = null;
    By viewBtnRoutespage = null;
    By viewDealBtn = null;
    By txtOrigion = null;
    By txtDestination1 = null;
    By departureDate = null;
    By departureMonthYear = null;
    By departureDay = null;
    By returnDate = null;
    By returnMonthYear = null;
    By retrunDay = null;
    By noOfTravellers = null;
    By window = null;
    By travelStartLogo = null;
    By txtOriginCheck = null;
    By txtDestination1Check = null;
    By txtDepartureMonthYearCheck = null;
    By txtReturnMonthYearCheck = null;
    By noOfTravellersCheck = null;

    //By navRoute=null;


    public CMSRoutes(CMS_Object cms_object) {
        prop = new PropertyFileReader();
        this.cms_object = cms_object;
        tsParameterObject = new TSParameterObject.TSParameterBuilder().setsTestCaseID(cms_object.getsTestCaseID()).setsTestCase(cms_object.getsTestCase()).tsParameterObject();
        assertionUtil = new AssertionUtil(tsParameterObject);
        //navRoute = By.cssSelector(prop.returnPropVal("navRoute"));
        navRoutes = By.xpath(prop.returnPropVal("navRoutes"));
        navRoutePages = By.xpath(prop.returnPropVal("navRoutePages"));
        viewBtnRoutespage = By.xpath(prop.returnPropVal("viewBtnRoutespage"));
        viewDealBtn = By.xpath(prop.returnPropVal("viewDealBtn"));
        txtOrigion = By.xpath(prop.returnPropVal("txtOrigion"));
        txtDestination1 = By.xpath(prop.returnPropVal("txtDestination1"));
        departureDate = By.xpath(prop.returnPropVal("departureDate"));
        departureMonthYear = By.xpath(prop.returnPropVal("departureMonthYear"));
        departureDay = By.xpath(prop.returnPropVal("departureDay"));
        returnDate = By.xpath(prop.returnPropVal("returnDate"));
        returnMonthYear = By.xpath(prop.returnPropVal("returnMonthYear"));
        retrunDay = By.xpath(prop.returnPropVal("retrunDay"));
        noOfTravellers = By.xpath(prop.returnPropVal("noOfTravellers"));
        travelStartLogo = By.xpath(prop.returnPropVal("travelStartLogo"));
        txtOriginCheck = By.xpath(prop.returnPropVal("txtOriginCheck"));
        txtDestination1Check = By.xpath(prop.returnPropVal("txtDestination1Check"));
        txtDepartureMonthYearCheck = By.xpath(prop.returnPropVal("txtDepartureMonthYearCheck"));
        txtReturnMonthYearCheck = By.xpath(prop.returnPropVal("txtReturnMonthYearCheck"));
        noOfTravellersCheck = By.xpath(prop.returnPropVal("noOfTravellersCheck"));


        window = By.tagName("body");
    }

    public void navigateToRoutePages() throws Exception {

        assertionUtil.clickElement(navRoutes, "Routes");
        assertionUtil.clickElement(navRoutePages, "Route Page");
        //click on view buttton on routes page
        assertionUtil.clickElement(viewBtnRoutespage, "Click on View button");
        //assertionUtil.switchToTab();
        assertionUtil.switchToWindow();
        // assertionUtil.wait(3000);
        assertionUtil.scrollDown();
        assertionUtil.clickElement(viewDealBtn, "Click on View Deal");
        // assertionUtil.getTitle(window);


    }

    public void travelInformation() throws Exception {
        String fromPlace = assertionUtil.getString(txtOrigion, "FromOrigion value");
        System.out.println("fromPlace" + fromPlace);
        String destination = assertionUtil.getString(txtDestination1, "Destination Origion value");
        System.out.println("destination" + destination);
        String departureDate1 = assertionUtil.getString(departureDate, "departure date value");
        System.out.println("departureDate1" + departureDate1);
        String departureMonthYear1 = assertionUtil.getString(departureMonthYear, "departureMonthYear value");
        System.out.println("departureMonthYear1" + departureMonthYear1);

        String departureDay1 = assertionUtil.getString(departureDay, "departureDay value");
        System.out.println("departureDay1" + departureDay1);
        String returnDate1 = assertionUtil.getString(returnDate, "returnDate value");
        System.out.println("returnDate1" + returnDate1);
        String returnMonthYear1 = assertionUtil.getString(returnMonthYear, "returnMonthYear value");
        System.out.println("returnMonthYear1" + returnMonthYear1);
        String retrunDay1 = assertionUtil.getString(retrunDay, "retrunDay value");
        System.out.println("retrunDay1" + retrunDay1);


        String noOfTravellers1 = assertionUtil.getString(noOfTravellers, "noOfTravellers value");
        System.out.println("noOfTravellers1" + noOfTravellers1);

        Thread.sleep(10000);
        // assertionUtil.wait(5000,2);
        assertionUtil.clickElement(travelStartLogo, "Click on Travel start logo");


        String fromPlaceCheck = assertionUtil.getStringOfButton(txtOriginCheck, "FromOrigioncheck value");
        System.out.println("fromPlaceCheck" + fromPlaceCheck);

        assertionUtil.assertStringEquals(fromPlace, fromPlaceCheck);

        String txtDestinationCheck = assertionUtil.getStringOfButton(txtDestination1Check, "txtDestination1Check value");
        System.out.println("txtDestinationCheck" + txtDestinationCheck);

        assertionUtil.assertStringEquals(destination, txtDestinationCheck);

        //dep month year

        String txtDepartureMonthYearCheck1 = assertionUtil.getString(txtDepartureMonthYearCheck, "txtDepartureMonthYearCheck value");
        System.out.println("txtDepartureMonthYearCheck1" + txtDepartureMonthYearCheck1);// wed 24 jul
        String departureMonth = departureMonthYear1.substring(0, 3); //jul
        String depDay = departureDay1.substring(0, 3);
        String actualDepText = depDay + " " + departureDate1 + " " + departureMonth;


        assertionUtil.assertStringEquals(actualDepText, txtDepartureMonthYearCheck1);


        String txtReturnMonthYearCheck1 = assertionUtil.getString(txtReturnMonthYearCheck, "txtReturnMonthYearCheck value");
        System.out.println("txtReturnMonthYearCheck1" + txtReturnMonthYearCheck1);// wed 24 jul
        String returnMonth = returnMonthYear1.substring(0, 3); //jul
        String retDay = retrunDay1.substring(0, 3);
        //String actualRetuText = retDay + " " + departureDate1 + " " + returnMonth;
        String actualRetText1 = retDay + " " + returnDate1 + " " + returnMonth;

        assertionUtil.assertStringEquals(actualRetText1, txtReturnMonthYearCheck1);
        String noOfTravellerscheck = assertionUtil.getString(noOfTravellersCheck, "noOfTravellerscheck value");
        String noOfTravellerscheck1 = noOfTravellerscheck.substring(0, 1);
        System.out.println("noOfTravellerscheck" + noOfTravellerscheck);

        assertionUtil.assertStringEquals(noOfTravellers1, noOfTravellerscheck1);


    }

}