package com.webdriver.tests;

import com.webdriver.helper.DriverManager;
import com.webdriver.modules.B2B.B2B_ConfirmedBookingModule;
import com.webdriver.modules.B2B.B2B_DashboardModule;
import com.webdriver.modules.B2B.B2B_LoginModule;
import com.webdriver.modules.B2B.B2B_PaymentInformationModule;
import com.webdriver.modules.B2B.B2B_SearchFlightModule;
import com.webdriver.modules.B2B.B2B_SearchResultsModule;
import com.webdriver.modules.MyAccount.ProfileModule;
import com.webdriver.modules.MyAccount.TravellersModule;
import com.webdriver.modules.TCC.TccModule;
import com.webdriver.pages.CMS.CMSAirportPage;
import com.webdriver.pages.CMS.CMSFlightRoutes;
import com.webdriver.pages.CMS.CMSProductsPage;
import com.webdriver.pages.TCC.QueuesPage;
import com.webdriver.pages.TCC.TCCMyAccount;
import com.webdriver.utils.AssertionUtil;
import com.webdriver.utils.CMS_Object;
import com.webdriver.utils.ExcelReader;
import com.webdriver.utils.FlightDetail;
import com.webdriver.utils.GLOBAL_CONSTANTS;
import com.webdriver.utils.GetURL;
import com.webdriver.utils.Log;
import com.webdriver.utils.GetSessionID;
import com.webdriver.utils.MarkasTest;
import com.webdriver.utils.SaveReference;
import com.webdriver.utils.TSParameterObject;
import com.webdriver.utils.TakeScreenshot;
import com.webdriver.utils.VoucherUtil;
import com.webdriver.utils.WaitFor;
import components.BookFlight;
import modules.BusinessAccount.BusinessPaymentModule;
import modules.BusinessAccount.BusinessProfileModule;
import modules.BusinessAccount.CompanyTravellerModule;
import modules.BusinessAccount.SignInAndSignUpModule;
import modules.Hotels.GuestModule;
import modules.Hotels.HotelsPaymentModule;
import modules.Hotels.HotelsSearchModule;
import modules.Hotels.HotelsSearchResultsModule;
import modules.MyAccount.AccountHomeModule;
import modules.MyAccount.PendingFlightsModule;
import modules.TSWEB.*;
import modules.TSWEB.PaymentsModule;
import modules.TSWEB.ResultsModule;
import modules.TSWEB.SearchModule;
import modules.TSWEB.HomeModule;
import org.sikuli.script.FindFailed;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import pages.Affiliate.CreateSource;
import pages.Affiliate.SignupAffiliate;
import pages.BusinessAccount.CompanyTravellersPage;
import pages.BusinessAccount.PaymentPage;
import pages.BusinessAccount.ProfilePage;
import pages.BusinessAccount.SignUpAndSignInPage;
import pages.CMS.*;
import pages.CMS.CMSAirlines;
import pages.Hotels.GuestDetailsPage;
import pages.Hotels.HotelsPaymentPage;
import pages.Hotels.SearchPage;
import pages.Hotels.SearchResultsPage;
import pages.MyAccount.BookingsPage;
import pages.MyAccount.LoginPage;
import pages.MyAccount.PendingFlightsPage;
import pages.MyAccount.SignupPage;
import pages.TCC.MonitLogs;
import pages.TSWEB.NewReviewPage;
import pages.Notifications.VerifyNotifications;
import pages.Rules.DownloadExcelSheets;
import pages.Rules.UploadExcelSheets;
import pages.TCC.Activities;
import pages.TCC.Comments;
import pages.TCC.Customer;
import pages.TCC.Documents;
import pages.TCC.Invoices;
import pages.TCC.PassengersAndFlights;
import pages.TCC.SelectMarket;
import pages.TCC.TCCLogin;
import pages.TCC.TCCLogout;
import pages.TCC.Vouchers;
import pages.TSWEB.NewConfirmPage;
import pages.TSWEB.NewHomePage;
import pages.TSWEB.NewPassengersPage;
import pages.TSWEB.NewPaymentsPage;
import pages.TSWEB.NewProductsPage;
import pages.TSWEB.NewResultsPage;
import pages.TSWEB.NewSearchPage;
import pages.TSWEB.PaymentsLinkPage;
import pages.TSWEB.UpdateApisLink;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import utils.GetLinks;
import utils.HotelsParamObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.webdriver.modules.Notifications.NotificationModule;
import utils.XmlManipulation;

public class TravelStartTestCases extends DriverManager {

    @Features("Allow user to search for flights")
    @Stories({"As a user I should be able to search www.travelstart.co.za for flights"})

    @Test(groups = {"ALL"}, dataProvider = "Scenarios", dataProviderClass = dataProvider.class)
    public void testAllTests(String sTestCaseID, String sTestCase, String sDescription, String sBrowser, String sPlatform, String sVersion, String sFlightType, String sTripType, String sDepartureCity, String sArrivalCity, String sDepartureDate, String sReturnDate, String sPreferredAirline, String sPreferredCabinClass, String sCalenderSearch, String sFlexiTicket, String sSortByDeparture, String sSortByReturn, String sFilterBy, String sBookmarkFlight, String sAdultTraveller, String sTeenTraveller, String sChildTraveller, String sInfantTraveller, String sSelectFlexiTicket, String sNewsletter, String sAccountType, String sSMS, String sAddBaggage, String sUpgradeFlexi, String sCellNumber, String sSameAsFirstPassenger, String sProducts, String sAternativeProducts, String sPaymentMethod, String sCardType, String sCardNumber, String sCVV, String sExpiryMonth, String sExpiryYear, String sBank, String sVATInvoice, String sTccVATInvoice, String sVoucher, String sMakeBooking, String sFrequentFlyer, String sMeal, String sSeat, String sReprice, String sPaymentLink, String sActivityLine, String sRefreshItinerary, String sRefreshPNR, String sApisEmail, String sEmailNotification, String sSignup, String sAddTravellers, String sAddCards, String sLogin, String sUsername, String sPassword, String sLang, String sRegion, String sReference, String sTBReference, String sSessionID) throws InterruptedException, IOException, FindFailed, Exception {

        TSParameterObject paramObject = new TSParameterObject.TSParameterBuilder().setsTestCaseID(sTestCaseID).setsTestCase(sTestCase).setsDescription(sDescription)
                .setsBrowser(sBrowser).setsPlatform(sPlatform).setsVersion(sVersion).setsFlightType(sFlightType).setsTripType(sTripType)
                .setsDepartureCity(sDepartureCity).setsArrivalCity(sArrivalCity).setsDepartureDate(sDepartureDate).setsReturnDate(sReturnDate)
                .setsPreferredAirline(sPreferredAirline).setsPreferredCabinClass(sPreferredCabinClass).setsCalenderSearch(sCalenderSearch)
                .setsFlexiTicket(sFlexiTicket).setsSortByDeparture(sSortByDeparture).setsSortByReturn(sSortByReturn).setsFilterBy(sFilterBy)
                .setsBookmarkFlight(sBookmarkFlight).setsAdultTraveller(sAdultTraveller).setsTeenTraveller(sTeenTraveller).setsChildTraveller(sChildTraveller)
                .setsInfantTraveller(sInfantTraveller).setsSelectFlexiTicket(sSelectFlexiTicket).setsNewsletter(sNewsletter).setsAccountType(sAccountType)
                .setsSMS(sSMS).setsAddBaggage(sAddBaggage).setsUpgradeFlexi(sUpgradeFlexi).setsCellNumber(sCellNumber).setsSameAsFirstPassenger(sSameAsFirstPassenger).setsProducts(sProducts).setsAternativeProducts(sAternativeProducts).setsPaymentMethod(sPaymentMethod)
                .setsCardType(sCardType).setsCardNumber(sCardNumber).setsCVV(sCVV).setsExpiryMonth(sExpiryMonth).setsExpiryYear(sExpiryYear).setsBank(sBank).setsVATInvoice(sVATInvoice)
                .setsTccVATInvoice(sTccVATInvoice).setsVoucher(sVoucher).setsMakeBooking(sMakeBooking).setsFrequentFlyer(sFrequentFlyer).setsMeal(sMeal).setsSeat(sSeat).setsReprice(sReprice).setsPaymentLink(sPaymentLink).setsActivityLine(sActivityLine)
                .setsRefreshItinerary(sRefreshItinerary).setsRefreshPNR(sRefreshPNR).setsApisEmail(sApisEmail).setsEmailNotification(sEmailNotification).setsSignUp(sSignup)
                .setsAddTravellers(sAddTravellers).setsAddCards(sAddCards).setsLogin(sLogin).setsUsername(sUsername).setsPassword(sPassword).setsLang(sLang).setsRegion(sRegion).tsParameterObject();

        String sauceTestCase = paramObject.getsTestCaseID() + paramObject.getsTestCase();
        setupWebDriver(paramObject.getsBrowser(), paramObject.getsPlatform(), paramObject.getsVersion(), sauceTestCase);

        if (paramObject.getsTestCase().equalsIgnoreCase("BookFlights") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("BookFlights") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equalsIgnoreCase(MARKET)) {

            testOne(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("PurchaseVoucher") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("PurchaseVoucher") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equalsIgnoreCase(MARKET)) {

            testOne(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("PaymentLinkVoucher") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("PaymentLinkVoucher") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equalsIgnoreCase(MARKET)) {

            testOne(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("VoucherSingle") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("VoucherSingle") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equalsIgnoreCase(MARKET)) {

            testOne(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("VoucherAffiliate") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("VoucherAffiliate") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equalsIgnoreCase(MARKET)) {

            testOne(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("VoucherPool") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("VoucherPool") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equalsIgnoreCase(MARKET)) {

            testOne(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ChangeLangOnCheckout") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ChangeLangOnCheckout") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equalsIgnoreCase(MARKET)) {

            testOne(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("BookFlightWithVoucher") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("BookFlightWithVoucher") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testTwo(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("TakePayment") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("TakePayment") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testThirtyThree(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("RefreshPNRPaymentLinkDomestic") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("RefreshPNRPaymentLinkDomestic") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFour(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("RefreshPNRPaymentLinkInternational") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("RefreshPNRPaymentLinkInternational") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFour(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("RepriceBooking") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("RepriceBooking") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFive(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("CreateTravelBarBooking") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("CreateTravelBarBooking") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testNine(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("CreateTBBookingURL") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("CreateTBBookingURL") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testOneHundredAndSix(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("SendTBQuote") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("SendTBQuote") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testOneHundredAndSeven(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("AffiliateTest") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("AffiliateTest") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testTen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("AffiliateTestAuto") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("AffiliateTestAuto") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testEleven(paramObject);

            System.out.println("Test is successful");


        } else if (paramObject.getsTestCase().equalsIgnoreCase("SearchFlights") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("SearchFlights") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testTwelve(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("SearchFlightsErrorValidation") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("SearchFlightsErrorValidation") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testOneHundredAndThree(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("SearchFlightsSupplier") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("SearchFlightsSupplier") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testThirteen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("NegativetestInfantPassengers") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("NegativetestInfantPassengers") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFourteen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("NegativetestTotalPassengers") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("NegativetestTotalPassengers") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFourteen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("NegativetestCCInvalidCVV") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("NegativetestCCInvalidCVV") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFifteen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("NegativetestCCMissingCVV") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("NegativetestCCMissingCVV") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFifteen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("NegativetestCCMissingExpMonth") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("NegativetestCCMissingExpMonth") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFifteen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("NegativetestCCMissingExpYear") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("NegativetestCCMissingExpYear") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFifteen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("NegativetestCCNumber") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("NegativetestCCNumber") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFifteen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("SubmitComment") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("SubmitComment") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testTwentySeven(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ChangePaymentMethod") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ChangePaymentMethod") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testThirty(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("DuplicateBookingError") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("DuplicateBookingError") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testThirtyOne(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("NegativeTestArabicChars") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("NegativeTestArabicChars") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testTwentyNine(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("FlightSearchFilters") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("FlightSearchFilters") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testThirtyFour(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ValidateCustomerTCC") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ValidateCustomerTCC") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testThirtyFive(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ValidateRefundOnTCC") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ValidateRefundOnTCC") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testThirtySix(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("Refund") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("Refund") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testThirtySix(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("PartialRefund") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("PartialRefund") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testThirtySix(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ValidateInvoicesBlockTCC") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ValidateInvoicesBlockTCC") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testThirtyNine(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ValidateActivityLines") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ValidateActivityLines") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testThirtyEight(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("PassengerNameValidation") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("PassengerNameValidation") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFourthy(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("PassengerNameEraseSpecialChars") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("PassengerNameEraseSpecialChars") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFourthyOne(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("PassengerNameConcatenation") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("PassengerNameConcatenation") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFourthyTwo(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("MangoCreditCardWarning") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("MangoCreditCardWarning") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFortyNine(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ValidateBaggageSelected") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ValidateBaggageSelected") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFiftyTwo(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ValidatePaymentMethods") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ValidatePaymentMethods") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFiftyThree(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("GenerateVoucherinTCC") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("GenerateVoucherinTCC") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFiftySix(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("EmailNotification") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("EmailNotification") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFiftySeven(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ValidateBaggageSelectedMultiPax") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ValidateBaggageSelectedMultiPax") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFiftyTwo(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ValidateAirportDialog") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ValidateAirportDialog") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFiftyEight(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("PassengerNameLength") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("PassengerNameLength") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSixtyOne(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("UploadAndDeleteDocsOnTCC") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("UploadAndDeleteDocsOnTCC") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSixty(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ValidateLanguageToggle") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ValidateLanguageToggle") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSixtyThree(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ValidateCellNumber") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ValidateCellNumber") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSixtyFour(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ValidateEmailFormat") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ValidateCellNumber") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSixtyFour(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ValidatePassengerDetails") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ValidatePassengerDetails") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFiftyThree(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ValidateBookingStatus") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ValidateBookingStatus") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFiftyThree(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("BookingFlow1") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("BookingFlow1") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSixtySix(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("BookingFlow2") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("BookingFlow2") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSixtySix(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("BookingFlow3") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("BookingFlow3") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSixtySix(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("BookingFlow4") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("BookingFlow4") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSixtySix(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("BookingFlow5") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("BookingFlow5") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSixtySix(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("BookingFlow6") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("BookingFlow6") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSixtySix(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("BookingFlow7") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("BookingFlow7") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSixtySix(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("BookingFlow8") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("BookingFlow8") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSixtySix(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ValidateFlexiOnResultsPage") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ValidateFlexiOnResultsPage") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testThirtyFour(paramObject);

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ValidateSameAsFirstAdult") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ValidateSameAsFirstAdult") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testTwentyNine(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("CancelItinerary") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("CancelItinerary") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSixtySeven(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("SearchByRefEmailUsername") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("SearchByRefEmailUsername") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSixtyNine(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ValidatePassengerAge") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ValidatePassengerAge") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSeventy(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("validateQueues") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("validateQueues") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSeventyOne(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ManualBookingLandOnly") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ManualBookingLandOnly") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSeventyTwo(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("FeaturesInTicketBlock") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("FeaturesInTicketBlock") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testEightyThree(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("IteneraryFlightsFields") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("IteneraryFlightsFields") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testEightyFour(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("SendFareAlerts") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("SendFareAlerts") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testThirtyFour(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("PriceStep") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("PriceStep") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSeventy(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ValidateHomePageLinks") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ValidateHomePageLinks") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testEightySix(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("CompareFlights") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("CompareFlights") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testEightySeven(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("StoreSearches") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("StoreSearches") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testEightyEight(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("AmendmentInvoice") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("AmendmentInvoice") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testNinetyTwo(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("RefundValidation") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("RefundValidation") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testNinetyThree(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("SendInvoice") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("SendInvoice") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testNinetyFour(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ContactForm") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ContactForm") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testNinetyFive(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("SimilityFraud") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("SimilityFraud") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testOneHundredAndFour(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ValidateBookingLogs") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ValidateBookingLogs") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testOneHundredAndEight(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("PayAndTicket") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("PayAndTicket") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testOneHundredAndNine(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("AutoTicket") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("AutoTicket") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testOneHundredAndTen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("HomepageLinksHttpRequest") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("HomepageLinksHttpRequest") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testOneHundredAndTwelve(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("HomepageLinksUI") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("HomepageLinksUI") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testOneHundredAndThirteen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ValidateCurrencies") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ValidateCurrencies") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testOneHundredAndFourteen(paramObject);

            System.out.println("Test is successful");

        } else {

            throw new SkipException("Skip test");
        }

    }

    @Features("Allow user to search for flights")
    @Stories({"As a user I should be able to search www.travelstart.co.za for flights"})

    @Test(groups = {"BookingTests"}, dataProvider = "Scenarios", dataProviderClass = dataProvider.class)
    public void testBookingTests(String sTestCaseID, String sTestCase, String sDescription, String sBrowser, String sPlatform, String sVersion, String sFlightType, String sTripType, String sDepartureCity, String sArrivalCity, String sDepartureDate, String sReturnDate, String sPreferredAirline, String sPreferredCabinClass, String sCalenderSearch, String sFlexiTicket, String sSortByDeparture, String sSortByReturn, String sFilterBy, String sBookmarkFlight, String sAdultTraveller, String sTeenTraveller, String sChildTraveller, String sInfantTraveller, String sSelectFlexiTicket, String sNewsletter, String sAccountType, String sSMS, String sAddBaggage, String sUpgradeFlexi, String sCellNumber, String sSameAsFirstPassenger, String sProducts, String sAternativeProducts, String sPaymentMethod, String sCardType, String sCardNumber, String sCVV, String sExpiryMonth, String sExpiryYear, String sBank, String sVATInvoice, String sTccVATInvoice, String sVoucher, String sMakeBooking, String sFrequentFlyer, String sMeal, String sSeat, String sReprice, String sPaymentLink, String sActivityLine, String sRefreshItinerary, String sRefreshPNR, String sApisEmail, String sEmailNotification, String sSignup, String sAddTravellers, String sAddCards, String sLogin, String sUsername, String sPassword, String sLang, String sRegion, String sReference, String sTBReference, String sSessionID) throws InterruptedException, IOException, FindFailed, Exception {

        TSParameterObject paramObject = new TSParameterObject.TSParameterBuilder().setsTestCaseID(sTestCaseID).setsTestCase(sTestCase).setsDescription(sDescription)
                .setsBrowser(sBrowser).setsPlatform(sPlatform).setsVersion(sVersion).setsFlightType(sFlightType).setsTripType(sTripType)
                .setsDepartureCity(sDepartureCity).setsArrivalCity(sArrivalCity).setsDepartureDate(sDepartureDate).setsReturnDate(sReturnDate)
                .setsPreferredAirline(sPreferredAirline).setsPreferredCabinClass(sPreferredCabinClass).setsCalenderSearch(sCalenderSearch)
                .setsFlexiTicket(sFlexiTicket).setsSortByDeparture(sSortByDeparture).setsSortByReturn(sSortByReturn).setsFilterBy(sFilterBy)
                .setsBookmarkFlight(sBookmarkFlight).setsAdultTraveller(sAdultTraveller).setsTeenTraveller(sTeenTraveller).setsChildTraveller(sChildTraveller)
                .setsInfantTraveller(sInfantTraveller).setsSelectFlexiTicket(sSelectFlexiTicket).setsNewsletter(sNewsletter).setsAccountType(sAccountType)
                .setsSMS(sSMS).setsAddBaggage(sAddBaggage).setsUpgradeFlexi(sUpgradeFlexi).setsCellNumber(sCellNumber).setsSameAsFirstPassenger(sSameAsFirstPassenger).setsProducts(sProducts).setsAternativeProducts(sAternativeProducts).setsPaymentMethod(sPaymentMethod)
                .setsCardType(sCardType).setsCardNumber(sCardNumber).setsCVV(sCVV).setsExpiryMonth(sExpiryMonth).setsExpiryYear(sExpiryYear).setsBank(sBank).setsVATInvoice(sVATInvoice)
                .setsTccVATInvoice(sTccVATInvoice).setsVoucher(sVoucher).setsMakeBooking(sMakeBooking).setsFrequentFlyer(sFrequentFlyer).setsMeal(sMeal).setsSeat(sSeat).setsReprice(sReprice).setsPaymentLink(sPaymentLink).setsActivityLine(sActivityLine)
                .setsRefreshItinerary(sRefreshItinerary).setsRefreshPNR(sRefreshPNR).setsApisEmail(sApisEmail).setsEmailNotification(sEmailNotification).setsSignUp(sSignup)
                .setsAddTravellers(sAddTravellers).setsAddCards(sAddCards).setsLogin(sLogin).setsUsername(sUsername).setsPassword(sPassword).setsLang(sLang).setsRegion(sRegion).tsParameterObject();

        String sauceTestCase = paramObject.getsTestCaseID() + paramObject.getsTestCase();
        setupWebDriver(paramObject.getsBrowser(), paramObject.getsPlatform(), paramObject.getsVersion(), sauceTestCase);

        if (paramObject.getsTestCase().equalsIgnoreCase("BookFlights") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("BookFlights") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsTestCase().equalsIgnoreCase(MARKET)) {

            testOne(paramObject);

            System.out.println("Test is successful");

        } else {

            throw new SkipException("Skip test");

        }

    }

    @Test(groups = {"TCCTests"}, dataProvider = "Scenarios", dataProviderClass = dataProvider.class)
    public void testTCCTests(String sTestCaseID, String sTestCase, String sDescription, String sBrowser, String sPlatform, String sVersion, String sFlightType, String sTripType, String sDepartureCity, String sArrivalCity, String sDepartureDate, String sReturnDate, String sPreferredAirline, String sPreferredCabinClass, String sCalenderSearch, String sFlexiTicket, String sSortByDeparture, String sSortByReturn, String sFilterBy, String sBookmarkFlight, String sAdultTraveller, String sTeenTraveller, String sChildTraveller, String sInfantTraveller, String sSelectFlexiTicket, String sNewsletter, String sAccountType, String sSMS, String sAddBaggage, String sUpgradeFlexi, String sCellNumber, String sSameAsFirstPassenger, String sProducts, String sAternativeProducts, String sPaymentMethod, String sCardType, String sCardNumber, String sCVV, String sExpiryMonth, String sExpiryYear, String sBank, String sVATInvoice, String sTccVATInvoice, String sVoucher, String sMakeBooking, String sFrequentFlyer, String sMeal, String sSeat, String sReprice, String sPaymentLink, String sActivityLine, String sRefreshItinerary, String sRefreshPNR, String sApisEmail, String sEmailNotification, String sSignup, String sAddTravellers, String sAddCards, String sLogin, String sUsername, String sPassword, String sLang, String sRegion, String sReference, String sTBReference, String sSessionID) throws InterruptedException, IOException, FindFailed, Exception {

        TSParameterObject paramObject = new TSParameterObject.TSParameterBuilder().setsTestCaseID(sTestCaseID).setsTestCase(sTestCase).setsDescription(sDescription)
                .setsBrowser(sBrowser).setsPlatform(sPlatform).setsVersion(sVersion).setsFlightType(sFlightType).setsTripType(sTripType)
                .setsDepartureCity(sDepartureCity).setsArrivalCity(sArrivalCity).setsDepartureDate(sDepartureDate).setsReturnDate(sReturnDate)
                .setsPreferredAirline(sPreferredAirline).setsPreferredCabinClass(sPreferredCabinClass).setsCalenderSearch(sCalenderSearch)
                .setsFlexiTicket(sFlexiTicket).setsSortByDeparture(sSortByDeparture).setsSortByReturn(sSortByReturn).setsFilterBy(sFilterBy)
                .setsBookmarkFlight(sBookmarkFlight).setsAdultTraveller(sAdultTraveller).setsTeenTraveller(sTeenTraveller).setsChildTraveller(sChildTraveller)
                .setsInfantTraveller(sInfantTraveller).setsSelectFlexiTicket(sSelectFlexiTicket).setsNewsletter(sNewsletter).setsAccountType(sAccountType)
                .setsSMS(sSMS).setsAddBaggage(sAddBaggage).setsUpgradeFlexi(sUpgradeFlexi).setsCellNumber(sCellNumber).setsSameAsFirstPassenger(sSameAsFirstPassenger).setsProducts(sProducts).setsAternativeProducts(sAternativeProducts).setsPaymentMethod(sPaymentMethod)
                .setsCardType(sCardType).setsCardNumber(sCardNumber).setsCVV(sCVV).setsExpiryMonth(sExpiryMonth).setsExpiryYear(sExpiryYear).setsBank(sBank).setsVATInvoice(sVATInvoice)
                .setsTccVATInvoice(sTccVATInvoice).setsVoucher(sVoucher).setsMakeBooking(sMakeBooking).setsFrequentFlyer(sFrequentFlyer).setsMeal(sMeal).setsSeat(sSeat).setsReprice(sReprice).setsPaymentLink(sPaymentLink).setsActivityLine(sActivityLine)
                .setsRefreshItinerary(sRefreshItinerary).setsRefreshPNR(sRefreshPNR).setsApisEmail(sApisEmail).setsEmailNotification(sEmailNotification).setsSignUp(sSignup)
                .setsAddTravellers(sAddTravellers).setsAddCards(sAddCards).setsLogin(sLogin).setsUsername(sUsername).setsPassword(sPassword).setsLang(sLang).setsRegion(sRegion).tsParameterObject();

        String sauceTestCase = paramObject.getsTestCaseID() + paramObject.getsTestCase();
        setupWebDriver(paramObject.getsBrowser(), paramObject.getsPlatform(), paramObject.getsVersion(), sauceTestCase);

        if (paramObject.getsTestCase().equalsIgnoreCase("BookFlightWithVoucher") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("BookFlightWithVoucher") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testTwo(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("RefreshPNRPaymentLinkDomestic") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("RefreshPNRPaymentLinkDomestic") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFour(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("RefreshPNRPaymentLinkInternational") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("RefreshPNRPaymentLinkInternational") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFour(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("RepriceBooking") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("RepriceBooking") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFive(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("SubmitComment") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("SubmitComment") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testTwentySeven(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("TakePayment") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("TakePayment") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testThirtyThree(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ValidateCustomerTCC") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ValidateCustomerTCC") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testThirtyFive(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ValidateRefundOnTCC") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ValidateRefundOnTCC") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testThirtySix(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ValidateInvoicesBlockTCC") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ValidateInvoicesBlockTCC") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testThirtyNine(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ValidateActivityLines") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ValidateActivityLines") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testThirtyEight(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("CancelItinerary") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("CancelItinerary") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSixtySeven(paramObject);

            System.out.println("Test is successful");

        } else {

            throw new SkipException("Skip test");

        }

    }

    @Test(groups = {"TravelbarTests"}, dataProvider = "Scenarios", dataProviderClass = dataProvider.class)
    public void testTravelBarTests(String sTestCaseID, String sTestCase, String sDescription, String sBrowser, String sPlatform, String sVersion, String sFlightType, String sTripType, String sDepartureCity, String sArrivalCity, String sDepartureDate, String sReturnDate, String sPreferredAirline, String sPreferredCabinClass, String sCalenderSearch, String sFlexiTicket, String sSortByDeparture, String sSortByReturn, String sFilterBy, String sBookmarkFlight, String sAdultTraveller, String sTeenTraveller, String sChildTraveller, String sInfantTraveller, String sSelectFlexiTicket, String sNewsletter, String sAccountType, String sSMS, String sAddBaggage, String sUpgradeFlexi, String sCellNumber, String sSameAsFirstPassenger, String sProducts, String sAternativeProducts, String sPaymentMethod, String sCardType, String sCardNumber, String sCVV, String sExpiryMonth, String sExpiryYear, String sBank, String sVATInvoice, String sTccVATInvoice, String sVoucher, String sMakeBooking, String sFrequentFlyer, String sMeal, String sSeat, String sReprice, String sPaymentLink, String sActivityLine, String sRefreshItinerary, String sRefreshPNR, String sApisEmail, String sEmailNotification, String sSignup, String sAddTravellers, String sAddCards, String sLogin, String sUsername, String sPassword, String sLang, String sRegion, String sReference, String sTBReference, String sSessionID) throws InterruptedException, IOException, FindFailed, Exception {

        TSParameterObject paramObject = new TSParameterObject.TSParameterBuilder().setsTestCaseID(sTestCaseID).setsTestCase(sTestCase).setsDescription(sDescription)
                .setsBrowser(sBrowser).setsPlatform(sPlatform).setsVersion(sVersion).setsFlightType(sFlightType).setsTripType(sTripType)
                .setsDepartureCity(sDepartureCity).setsArrivalCity(sArrivalCity).setsDepartureDate(sDepartureDate).setsReturnDate(sReturnDate)
                .setsPreferredAirline(sPreferredAirline).setsPreferredCabinClass(sPreferredCabinClass).setsCalenderSearch(sCalenderSearch)
                .setsFlexiTicket(sFlexiTicket).setsSortByDeparture(sSortByDeparture).setsSortByReturn(sSortByReturn).setsFilterBy(sFilterBy)
                .setsBookmarkFlight(sBookmarkFlight).setsAdultTraveller(sAdultTraveller).setsTeenTraveller(sTeenTraveller).setsChildTraveller(sChildTraveller)
                .setsInfantTraveller(sInfantTraveller).setsSelectFlexiTicket(sSelectFlexiTicket).setsNewsletter(sNewsletter).setsAccountType(sAccountType)
                .setsSMS(sSMS).setsAddBaggage(sAddBaggage).setsUpgradeFlexi(sUpgradeFlexi).setsCellNumber(sCellNumber).setsSameAsFirstPassenger(sSameAsFirstPassenger).setsProducts(sProducts).setsAternativeProducts(sAternativeProducts).setsPaymentMethod(sPaymentMethod)
                .setsCardType(sCardType).setsCardNumber(sCardNumber).setsCVV(sCVV).setsExpiryMonth(sExpiryMonth).setsExpiryYear(sExpiryYear).setsBank(sBank).setsVATInvoice(sVATInvoice)
                .setsTccVATInvoice(sTccVATInvoice).setsVoucher(sVoucher).setsMakeBooking(sMakeBooking).setsFrequentFlyer(sFrequentFlyer).setsMeal(sMeal).setsSeat(sSeat).setsReprice(sReprice).setsPaymentLink(sPaymentLink).setsActivityLine(sActivityLine)
                .setsRefreshItinerary(sRefreshItinerary).setsRefreshPNR(sRefreshPNR).setsApisEmail(sApisEmail).setsEmailNotification(sEmailNotification).setsSignUp(sSignup)
                .setsAddTravellers(sAddTravellers).setsAddCards(sAddCards).setsLogin(sLogin).setsUsername(sUsername).setsPassword(sPassword).setsLang(sLang).setsRegion(sRegion).tsParameterObject();

        String sauceTestCase = paramObject.getsTestCaseID() + paramObject.getsTestCase();
        setupWebDriver(paramObject.getsBrowser(), paramObject.getsPlatform(), paramObject.getsVersion(), sauceTestCase);

        if (paramObject.getsTestCase().equalsIgnoreCase("CreateTravelBarBooking") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("CreateTravelBarBooking") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testNine(paramObject);

            System.out.println("Test is successful");

        } else {

            throw new SkipException("Skip test");

        }

    }

    @Test(groups = {"NotificationTests"}, dataProvider = "Scenarios", dataProviderClass = dataProvider.class)
    public void testNotificationTestCases(String sTestCaseID, String sTestCase, String sDescription, String sBrowser, String sPlatform, String sVersion, String sFlightType, String sTripType, String sDepartureCity, String sArrivalCity, String sDepartureDate, String sReturnDate, String sPreferredAirline, String sPreferredCabinClass, String sCalenderSearch, String sFlexiTicket, String sSortByDeparture, String sSortByReturn, String sFilterBy, String sBookmarkFlight, String sAdultTraveller, String sTeenTraveller, String sChildTraveller, String sInfantTraveller, String sSelectFlexiTicket, String sNewsletter, String sAccountType, String sSMS, String sAddBaggage, String sUpgradeFlexi, String sCellNumber, String sSameAsFirstPassenger, String sProducts, String sAternativeProducts, String sPaymentMethod, String sCardType, String sCardNumber, String sCVV, String sExpiryMonth, String sExpiryYear, String sBank, String sVATInvoice, String sTccVATInvoice, String sVoucher, String sMakeBooking, String sFrequentFlyer, String sMeal, String sSeat, String sReprice, String sPaymentLink, String sActivityLine, String sRefreshItinerary, String sRefreshPNR, String sApisEmail, String sEmailNotification, String sSignup, String sAddTravellers, String sAddCards, String sLogin, String sUsername, String sPassword, String sLang, String sRegion, String sReference, String sTBReference, String sSessionID) throws InterruptedException, IOException, FindFailed, Exception {

        TSParameterObject paramObject = new TSParameterObject.TSParameterBuilder().setsTestCaseID(sTestCaseID).setsTestCase(sTestCase).setsDescription(sDescription)
                .setsBrowser(sBrowser).setsPlatform(sPlatform).setsVersion(sVersion).setsFlightType(sFlightType).setsTripType(sTripType)
                .setsDepartureCity(sDepartureCity).setsArrivalCity(sArrivalCity).setsDepartureDate(sDepartureDate).setsReturnDate(sReturnDate)
                .setsPreferredAirline(sPreferredAirline).setsPreferredCabinClass(sPreferredCabinClass).setsCalenderSearch(sCalenderSearch)
                .setsFlexiTicket(sFlexiTicket).setsSortByDeparture(sSortByDeparture).setsSortByReturn(sSortByReturn).setsFilterBy(sFilterBy)
                .setsBookmarkFlight(sBookmarkFlight).setsAdultTraveller(sAdultTraveller).setsTeenTraveller(sTeenTraveller).setsChildTraveller(sChildTraveller)
                .setsInfantTraveller(sInfantTraveller).setsSelectFlexiTicket(sSelectFlexiTicket).setsNewsletter(sNewsletter).setsAccountType(sAccountType)
                .setsSMS(sSMS).setsAddBaggage(sAddBaggage).setsUpgradeFlexi(sUpgradeFlexi).setsCellNumber(sCellNumber).setsSameAsFirstPassenger(sSameAsFirstPassenger).setsProducts(sProducts).setsAternativeProducts(sAternativeProducts).setsPaymentMethod(sPaymentMethod)
                .setsCardType(sCardType).setsCardNumber(sCardNumber).setsCVV(sCVV).setsExpiryMonth(sExpiryMonth).setsExpiryYear(sExpiryYear).setsBank(sBank).setsVATInvoice(sVATInvoice)
                .setsTccVATInvoice(sTccVATInvoice).setsVoucher(sVoucher).setsMakeBooking(sMakeBooking).setsFrequentFlyer(sFrequentFlyer).setsMeal(sMeal).setsSeat(sSeat).setsReprice(sReprice).setsPaymentLink(sPaymentLink).setsActivityLine(sActivityLine)
                .setsRefreshItinerary(sRefreshItinerary).setsRefreshPNR(sRefreshPNR).setsApisEmail(sApisEmail).setsEmailNotification(sEmailNotification).setsSignUp(sSignup)
                .setsAddTravellers(sAddTravellers).setsAddCards(sAddCards).setsLogin(sLogin).setsUsername(sUsername).setsPassword(sPassword).setsLang(sLang).setsRegion(sRegion).tsParameterObject();

        String sauceTestCase = paramObject.getsTestCaseID() + paramObject.getsTestCase();
        setupWebDriver(paramObject.getsBrowser(), paramObject.getsPlatform(), paramObject.getsVersion(), sauceTestCase);

        if (paramObject.getsTestCase().equalsIgnoreCase("NotificationsEFT") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("NotificationsEFT") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testEight(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("NotificationsEFTPaymentLink") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("NotificationsEFTPaymentLink") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testNine(paramObject);

            System.out.println("Test is successful");

        } else {

            throw new SkipException("Skip test");

        }

    }

    @Test(groups = {"AffiliateTests"}, dataProvider = "Scenarios", dataProviderClass = dataProvider.class)
    public void testAffiliateTestCases(String sTestCaseID, String sTestCase, String sDescription, String sBrowser, String sPlatform, String sVersion, String sFlightType, String sTripType, String sDepartureCity, String sArrivalCity, String sDepartureDate, String sReturnDate, String sPreferredAirline, String sPreferredCabinClass, String sCalenderSearch, String sFlexiTicket, String sSortByDeparture, String sSortByReturn, String sFilterBy, String sBookmarkFlight, String sAdultTraveller, String sTeenTraveller, String sChildTraveller, String sInfantTraveller, String sSelectFlexiTicket, String sNewsletter, String sAccountType, String sSMS, String sAddBaggage, String sUpgradeFlexi, String sCellNumber, String sSameAsFirstPassenger, String sProducts, String sAternativeProducts, String sPaymentMethod, String sCardType, String sCardNumber, String sCVV, String sExpiryMonth, String sExpiryYear, String sBank, String sVATInvoice, String sTccVATInvoice, String sVoucher, String sMakeBooking, String sFrequentFlyer, String sMeal, String sSeat, String sReprice, String sPaymentLink, String sActivityLine, String sRefreshItinerary, String sRefreshPNR, String sApisEmail, String sEmailNotification, String sSignup, String sAddTravellers, String sAddCards, String sLogin, String sUsername, String sPassword, String sLang, String sRegion, String sReference, String sTBReference, String sSessionID) throws InterruptedException, IOException, FindFailed, Exception {

        TSParameterObject paramObject = new TSParameterObject.TSParameterBuilder().setsTestCaseID(sTestCaseID).setsTestCase(sTestCase).setsDescription(sDescription)
                .setsBrowser(sBrowser).setsPlatform(sPlatform).setsVersion(sVersion).setsFlightType(sFlightType).setsTripType(sTripType)
                .setsDepartureCity(sDepartureCity).setsArrivalCity(sArrivalCity).setsDepartureDate(sDepartureDate).setsReturnDate(sReturnDate)
                .setsPreferredAirline(sPreferredAirline).setsPreferredCabinClass(sPreferredCabinClass).setsCalenderSearch(sCalenderSearch)
                .setsFlexiTicket(sFlexiTicket).setsSortByDeparture(sSortByDeparture).setsSortByReturn(sSortByReturn).setsFilterBy(sFilterBy)
                .setsBookmarkFlight(sBookmarkFlight).setsAdultTraveller(sAdultTraveller).setsTeenTraveller(sTeenTraveller).setsChildTraveller(sChildTraveller)
                .setsInfantTraveller(sInfantTraveller).setsSelectFlexiTicket(sSelectFlexiTicket).setsNewsletter(sNewsletter).setsAccountType(sAccountType)
                .setsSMS(sSMS).setsAddBaggage(sAddBaggage).setsUpgradeFlexi(sUpgradeFlexi).setsCellNumber(sCellNumber).setsSameAsFirstPassenger(sSameAsFirstPassenger).setsProducts(sProducts).setsAternativeProducts(sAternativeProducts).setsPaymentMethod(sPaymentMethod)
                .setsCardType(sCardType).setsCardNumber(sCardNumber).setsCVV(sCVV).setsExpiryMonth(sExpiryMonth).setsExpiryYear(sExpiryYear).setsBank(sBank).setsVATInvoice(sVATInvoice)
                .setsTccVATInvoice(sTccVATInvoice).setsVoucher(sVoucher).setsMakeBooking(sMakeBooking).setsFrequentFlyer(sFrequentFlyer).setsMeal(sMeal).setsSeat(sSeat).setsReprice(sReprice).setsPaymentLink(sPaymentLink).setsActivityLine(sActivityLine)
                .setsRefreshItinerary(sRefreshItinerary).setsRefreshPNR(sRefreshPNR).setsApisEmail(sApisEmail).setsEmailNotification(sEmailNotification).setsSignUp(sSignup)
                .setsAddTravellers(sAddTravellers).setsAddCards(sAddCards).setsLogin(sLogin).setsUsername(sUsername).setsPassword(sPassword).setsLang(sLang).setsRegion(sRegion).tsParameterObject();

        String sauceTestCase = paramObject.getsTestCaseID() + paramObject.getsTestCase();
        setupWebDriver(paramObject.getsBrowser(), paramObject.getsPlatform(), paramObject.getsVersion(), sauceTestCase);

        if (paramObject.getsTestCase().equalsIgnoreCase("AffiliateTest") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("AffiliateTest") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testTen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("AffiliateTestAuto") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("AffiliateTestAuto") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testEleven(paramObject);

            System.out.println("Test is successful");

        } else {

            throw new SkipException("Skip test");

        }

    }

    @Test(groups = {"NegativeTests"}, dataProvider = "Scenarios", dataProviderClass = dataProvider.class)
    public void testNegativeTests(String sTestCaseID, String sTestCase, String sDescription, String sBrowser, String sPlatform, String sVersion, String sFlightType, String sTripType, String sDepartureCity, String sArrivalCity, String sDepartureDate, String sReturnDate, String sPreferredAirline, String sPreferredCabinClass, String sCalenderSearch, String sFlexiTicket, String sSortByDeparture, String sSortByReturn, String sFilterBy, String sBookmarkFlight, String sAdultTraveller, String sTeenTraveller, String sChildTraveller, String sInfantTraveller, String sSelectFlexiTicket, String sNewsletter, String sAccountType, String sSMS, String sAddBaggage, String sUpgradeFlexi, String sCellNumber, String sSameAsFirstPassenger, String sProducts, String sAternativeProducts, String sPaymentMethod, String sCardType, String sCardNumber, String sCVV, String sExpiryMonth, String sExpiryYear, String sBank, String sVATInvoice, String sTccVATInvoice, String sVoucher, String sMakeBooking, String sFrequentFlyer, String sMeal, String sSeat, String sReprice, String sPaymentLink, String sActivityLine, String sRefreshItinerary, String sRefreshPNR, String sApisEmail, String sEmailNotification, String sSignup, String sAddTravellers, String sAddCards, String sLogin, String sUsername, String sPassword, String sLang, String sRegion, String sReference, String sTBReference, String sSessionID) throws InterruptedException, IOException, FindFailed, Exception {

        TSParameterObject paramObject = new TSParameterObject.TSParameterBuilder().setsTestCaseID(sTestCaseID).setsTestCase(sTestCase).setsDescription(sDescription)
                .setsBrowser(sBrowser).setsPlatform(sPlatform).setsVersion(sVersion).setsFlightType(sFlightType).setsTripType(sTripType)
                .setsDepartureCity(sDepartureCity).setsArrivalCity(sArrivalCity).setsDepartureDate(sDepartureDate).setsReturnDate(sReturnDate)
                .setsPreferredAirline(sPreferredAirline).setsPreferredCabinClass(sPreferredCabinClass).setsCalenderSearch(sCalenderSearch)
                .setsFlexiTicket(sFlexiTicket).setsSortByDeparture(sSortByDeparture).setsSortByReturn(sSortByReturn).setsFilterBy(sFilterBy)
                .setsBookmarkFlight(sBookmarkFlight).setsAdultTraveller(sAdultTraveller).setsTeenTraveller(sTeenTraveller).setsChildTraveller(sChildTraveller)
                .setsInfantTraveller(sInfantTraveller).setsSelectFlexiTicket(sSelectFlexiTicket).setsNewsletter(sNewsletter).setsAccountType(sAccountType)
                .setsSMS(sSMS).setsAddBaggage(sAddBaggage).setsUpgradeFlexi(sUpgradeFlexi).setsCellNumber(sCellNumber).setsSameAsFirstPassenger(sSameAsFirstPassenger).setsProducts(sProducts).setsAternativeProducts(sAternativeProducts).setsPaymentMethod(sPaymentMethod)
                .setsCardType(sCardType).setsCardNumber(sCardNumber).setsCVV(sCVV).setsExpiryMonth(sExpiryMonth).setsExpiryYear(sExpiryYear).setsBank(sBank).setsVATInvoice(sVATInvoice)
                .setsTccVATInvoice(sTccVATInvoice).setsVoucher(sVoucher).setsMakeBooking(sMakeBooking).setsFrequentFlyer(sFrequentFlyer).setsMeal(sMeal).setsSeat(sSeat).setsReprice(sReprice).setsPaymentLink(sPaymentLink).setsActivityLine(sActivityLine)
                .setsRefreshItinerary(sRefreshItinerary).setsRefreshPNR(sRefreshPNR).setsApisEmail(sApisEmail).setsEmailNotification(sEmailNotification).setsSignUp(sSignup)
                .setsAddTravellers(sAddTravellers).setsAddCards(sAddCards).setsLogin(sLogin).setsUsername(sUsername).setsPassword(sPassword).setsLang(sLang).setsRegion(sRegion).tsParameterObject();

        String sauceTestCase = paramObject.getsTestCaseID() + paramObject.getsTestCase();
        setupWebDriver(paramObject.getsBrowser(), paramObject.getsPlatform(), paramObject.getsVersion(), sauceTestCase);

        if (paramObject.getsTestCase().equalsIgnoreCase("NegativetestInfantPassengers") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("NegativetestInfantPassengers") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFourteen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("NegativetestTotalPassengers") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("NegativetestTotalPassengers") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFourteen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("NegativetestCCInvalidCVV") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("NegativetestCCInvalidCVV") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFifteen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("NegativetestCCMissingCVV") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("NegativetestCCMissingCVV") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFifteen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("NegativetestCCMissingExpMonth") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("NegativetestCCMissingExpMonth") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFifteen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("NegativetestCCMissingExpYear") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("NegativetestCCMissingExpYear") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFifteen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("NegativetestCCNumber") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("NegativetestCCNumber") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFifteen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("NegativeSameCities") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("NegativeSameCities") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testTwentyEight(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("NegativeTestArabicChars") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("NegativeTestArabicChars") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testTwentyNine(paramObject);

            System.out.println("Test is successful");

        } else {

            throw new SkipException("Skip test");

        }

    }

    @Features("Allow user to merge rules files")
    @Stories({"As a user I should be able to merge North and South rules"})

    @Test(groups = {"Rules"}, dataProvider = "Scenarios", dataProviderClass = dataProvider.class)
    public void testImportRules(String sID, String sSpreadsheet, String sCashOverRide, String sSource1, String sSource2) throws InterruptedException, IOException, FindFailed, Exception {

        String sauceTestCase = "Importing " + sSpreadsheet;
        setupWebDriver("chrome", "macOS 10.12", "56", sauceTestCase);

        if (sSpreadsheet.equalsIgnoreCase("AmadeusDataManagement") || sSpreadsheet.equalsIgnoreCase("AmadeusDefaultTicketOffice") || sSpreadsheet.equalsIgnoreCase("AmadeusFareRule") || sSpreadsheet.equalsIgnoreCase("AmadeusOffice") || sSpreadsheet.equalsIgnoreCase("AmadeusSearch") || sSpreadsheet.equalsIgnoreCase("CrossBorderMarkup") || sSpreadsheet.equalsIgnoreCase("SupplierSearch") || sSpreadsheet.equalsIgnoreCase("Markup")) {
            testFiftyOne(sID, sSpreadsheet, sCashOverRide, sSource1, sSource2);

        }

        if (sSpreadsheet.equalsIgnoreCase("PaymentMethods")) {
            testOneHundredAndEleven(sID, sSpreadsheet, sCashOverRide, sSource1, sSource2);

        }

    }

    @Features("Allow user to mark orders as test")
    @Stories({"As a user I should be able to mark an order as test"})

    @Test(groups = {"MarkasTest"}, dataProvider = "Scenarios", dataProviderClass = dataProvider.class)
    public void testMarkasTest(String sOrder, String sTestCaseID, String sTestCase, String sDescription) throws InterruptedException, IOException, FindFailed, Exception {

        String sauceTestCase = "MarkasTest";
        setupWebDriver("chrome", "macOS 10.12", "56", sauceTestCase);

        testNinetySix(sOrder, sTestCaseID, sTestCase, sDescription);

        System.out.println("Import is successful");

    }

    @Test(groups = {"SearchTests"}, dataProvider = "Scenarios", dataProviderClass = dataProvider.class)
    public void testSearchTests(String sTestCaseID, String sTestCase, String sDescription, String sBrowser, String sPlatform, String sVersion, String sFlightType, String sTripType, String sDepartureCity, String sArrivalCity, String sDepartureDate, String sReturnDate, String sPreferredAirline, String sPreferredCabinClass, String sCalenderSearch, String sFlexiTicket, String sSortByDeparture, String sSortByReturn, String sFilterBy, String sBookmarkFlight, String sAdultTraveller, String sTeenTraveller, String sChildTraveller, String sInfantTraveller, String sSelectFlexiTicket, String sNewsletter, String sAccountType, String sSMS, String sAddBaggage, String sUpgradeFlexi, String sCellNumber, String sSameAsFirstPassenger, String sProducts, String sAternativeProducts, String sPaymentMethod, String sCardType, String sCardNumber, String sCVV, String sExpiryMonth, String sExpiryYear, String sBank, String sVATInvoice, String sTccVATInvoice, String sVoucher, String sMakeBooking, String sFrequentFlyer, String sMeal, String sSeat, String sReprice, String sPaymentLink, String sActivityLine, String sRefreshItinerary, String sRefreshPNR, String sApisEmail, String sEmailNotification, String sSignup, String sAddTravellers, String sAddCards, String sLogin, String sUsername, String sPassword, String sLang, String sRegion, String sReference, String sTBReference, String sSessionID) throws InterruptedException, IOException, FindFailed, Exception {

        TSParameterObject paramObject = new TSParameterObject.TSParameterBuilder().setsTestCaseID(sTestCaseID).setsTestCase(sTestCase).setsDescription(sDescription)
                .setsBrowser(sBrowser).setsPlatform(sPlatform).setsVersion(sVersion).setsFlightType(sFlightType).setsTripType(sTripType)
                .setsDepartureCity(sDepartureCity).setsArrivalCity(sArrivalCity).setsDepartureDate(sDepartureDate).setsReturnDate(sReturnDate)
                .setsPreferredAirline(sPreferredAirline).setsPreferredCabinClass(sPreferredCabinClass).setsCalenderSearch(sCalenderSearch)
                .setsFlexiTicket(sFlexiTicket).setsSortByDeparture(sSortByDeparture).setsSortByReturn(sSortByReturn).setsFilterBy(sFilterBy)
                .setsBookmarkFlight(sBookmarkFlight).setsAdultTraveller(sAdultTraveller).setsTeenTraveller(sTeenTraveller).setsChildTraveller(sChildTraveller)
                .setsInfantTraveller(sInfantTraveller).setsSelectFlexiTicket(sSelectFlexiTicket).setsNewsletter(sNewsletter).setsAccountType(sAccountType)
                .setsSMS(sSMS).setsAddBaggage(sAddBaggage).setsUpgradeFlexi(sUpgradeFlexi).setsCellNumber(sCellNumber).setsSameAsFirstPassenger(sSameAsFirstPassenger).setsProducts(sProducts).setsAternativeProducts(sAternativeProducts).setsPaymentMethod(sPaymentMethod)
                .setsCardType(sCardType).setsCardNumber(sCardNumber).setsCVV(sCVV).setsExpiryMonth(sExpiryMonth).setsExpiryYear(sExpiryYear).setsBank(sBank).setsVATInvoice(sVATInvoice)
                .setsTccVATInvoice(sTccVATInvoice).setsVoucher(sVoucher).setsMakeBooking(sMakeBooking).setsFrequentFlyer(sFrequentFlyer).setsMeal(sMeal).setsSeat(sSeat).setsReprice(sReprice).setsPaymentLink(sPaymentLink).setsActivityLine(sActivityLine)
                .setsRefreshItinerary(sRefreshItinerary).setsRefreshPNR(sRefreshPNR).setsApisEmail(sApisEmail).setsEmailNotification(sEmailNotification).setsSignUp(sSignup)
                .setsAddTravellers(sAddTravellers).setsAddCards(sAddCards).setsLogin(sLogin).setsUsername(sUsername).setsPassword(sPassword).setsLang(sLang).setsRegion(sRegion).tsParameterObject();

        String sauceTestCase = paramObject.getsTestCaseID() + paramObject.getsTestCase();
        setupWebDriver(paramObject.getsBrowser(), paramObject.getsPlatform(), paramObject.getsVersion(), sauceTestCase);

        if (paramObject.getsTestCase().equalsIgnoreCase("SearchFlights") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("SearchFlights") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testTwelve(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("SearchFlightsSupplier") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("SearchFlightsSupplier") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testThirteen(paramObject);

            System.out.println("Test is successful");

        } else {

            throw new SkipException("Skip test");

        }

    }

    @Test(groups = {"MyAccountTests"}, dataProvider = "Scenarios", dataProviderClass = dataProvider.class)
    public void testMyAccountTests(String sTestCaseID, String sTestCase, String sDescription, String sBrowser, String sPlatform, String sVersion, String sFlightType, String sTripType, String sDepartureCity, String sArrivalCity, String sDepartureDate, String sReturnDate, String sPreferredAirline, String sPreferredCabinClass, String sCalenderSearch, String sFlexiTicket, String sSortByDeparture, String sSortByReturn, String sFilterBy, String sBookmarkFlight, String sAdultTraveller, String sTeenTraveller, String sChildTraveller, String sInfantTraveller, String sSelectFlexiTicket, String sNewsletter, String sAccountType, String sSMS, String sAddBaggage, String sUpgradeFlexi, String sCellNumber, String sSameAsFirstPassenger, String sProducts, String sAternativeProducts, String sPaymentMethod, String sCardType, String sCardNumber, String sCVV, String sExpiryMonth, String sExpiryYear, String sBank, String sVATInvoice, String sTccVATInvoice, String sVoucher, String sMakeBooking, String sFrequentFlyer, String sMeal, String sSeat, String sReprice, String sPaymentLink, String sActivityLine, String sRefreshItinerary, String sRefreshPNR, String sApisEmail, String sEmailNotification, String sSignup, String sAddTravellers, String sAddCards, String sLogin, String sUsername, String sPassword, String sLang, String sRegion, String sReference, String sTBReference, String sSessionID) throws InterruptedException, IOException, FindFailed, Exception {

        TSParameterObject paramObject = new TSParameterObject.TSParameterBuilder().setsTestCaseID(sTestCaseID).setsTestCase(sTestCase).setsDescription(sDescription)
                .setsBrowser(sBrowser).setsPlatform(sPlatform).setsVersion(sVersion).setsFlightType(sFlightType).setsTripType(sTripType)
                .setsDepartureCity(sDepartureCity).setsArrivalCity(sArrivalCity).setsDepartureDate(sDepartureDate).setsReturnDate(sReturnDate)
                .setsPreferredAirline(sPreferredAirline).setsPreferredCabinClass(sPreferredCabinClass).setsCalenderSearch(sCalenderSearch)
                .setsFlexiTicket(sFlexiTicket).setsSortByDeparture(sSortByDeparture).setsSortByReturn(sSortByReturn).setsFilterBy(sFilterBy)
                .setsBookmarkFlight(sBookmarkFlight).setsAdultTraveller(sAdultTraveller).setsTeenTraveller(sTeenTraveller).setsChildTraveller(sChildTraveller)
                .setsInfantTraveller(sInfantTraveller).setsSelectFlexiTicket(sSelectFlexiTicket).setsNewsletter(sNewsletter).setsAccountType(sAccountType)
                .setsSMS(sSMS).setsAddBaggage(sAddBaggage).setsUpgradeFlexi(sUpgradeFlexi).setsCellNumber(sCellNumber).setsSameAsFirstPassenger(sSameAsFirstPassenger).setsProducts(sProducts).setsAternativeProducts(sAternativeProducts).setsPaymentMethod(sPaymentMethod)
                .setsCardType(sCardType).setsCardNumber(sCardNumber).setsCVV(sCVV).setsExpiryMonth(sExpiryMonth).setsExpiryYear(sExpiryYear).setsBank(sBank).setsVATInvoice(sVATInvoice)
                .setsTccVATInvoice(sTccVATInvoice).setsVoucher(sVoucher).setsMakeBooking(sMakeBooking).setsFrequentFlyer(sFrequentFlyer).setsMeal(sMeal).setsSeat(sSeat).setsReprice(sReprice).setsPaymentLink(sPaymentLink).setsActivityLine(sActivityLine)
                .setsRefreshItinerary(sRefreshItinerary).setsRefreshPNR(sRefreshPNR).setsApisEmail(sApisEmail).setsEmailNotification(sEmailNotification).setsSignUp(sSignup)
                .setsAddTravellers(sAddTravellers).setsAddCards(sAddCards).setsLogin(sLogin).setsUsername(sUsername).setsPassword(sPassword).setsLang(sLang).setsRegion(sRegion).tsParameterObject();

        String sauceTestCase = paramObject.getsTestCaseID() + paramObject.getsTestCase();
        setupWebDriver(paramObject.getsBrowser(), paramObject.getsPlatform(), paramObject.getsVersion(), sauceTestCase);

        if (paramObject.getsTestCase().equalsIgnoreCase("SignOut") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("SignOut") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSixteen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("UpdatePassword") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("UpdatePassword") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSeventeen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ActivateAccountFromBooking") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ActivateAccountFromBooking") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testEighteen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("AddTraveller") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("AddTraveller") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {


            testNineteen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("AddCardDetails") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("AddCardDetails") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testTwenty(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("BookFlightWithSavedTraveller") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("BookFlightWithSavedTraveller") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testTwentyOne(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("DeleteCardDetails") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("DeleteCardDetails") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testTwentyTwo(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("CreateAccount") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("CreateAccount") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testTwentyThree(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("DeleteTraveller") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("DeleteTraveller") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testTwentyFour(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("EditPersonalDetails") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("EditPersonalDetails") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testTwentyFive(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("Notifications") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("Notifications") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testTwentySix(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("Login") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("Login") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFortySeven(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("LoginExistingAccount") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("LoginExistingAccount") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFiftyFour(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("BookFlightWithSavedCardDetails") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("BookFlightWithSavedCardDetails") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFiftyFive(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("SignUpAndBookFlight") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("SignUpAndBookFlight") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testOneHundredAndFive(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("SignInAndBookFlight") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("SignInAndBookFlight") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testOneHundredAndFive(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("EditTraveller") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("EditTraveller") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSixtyFive(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("SignUpWithActiveEmail") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("SignUpWithActiveEmail") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSixtyEight(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("SignInInvalidPassword") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("SignInInvalidPassword") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSeventyThree(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("SignInInactiveAccount") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("SignInInactiveAccount") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSeventyFour(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ForgotPasswordOnSignIn") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ForgotPasswordOnSignIn") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSeventyFive(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ResetPasswordFromTcc") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ResetPasswordFromTcc") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSeventySix(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("StoreSearches") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("StoreSearches") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testEightyEight(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("TrackMyBooking") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("TrackMyBooking") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testOneHundredAndFive(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("FooterLinks") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("FooterLinks") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testOneHundredAndFifteen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("TravelstartRedirect") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("TravelstartRedirect") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testOneHundredAndTwentyOne(paramObject);

            System.out.println("Test is successful");

        } else {

            throw new SkipException("Skip test");

        }

    }

    @Test(groups = {"CMS"}, dataProvider = "Scenarios", dataProviderClass = dataProvider.class)
    public void testMarketingCMS(String sTestCaseID, String sTestCase, String sDescription, String sBrowser, String sPlatform, String sVersion, String clonePage, String sRegion, String sReference, String sTBReference, String sSessionID) throws InterruptedException, IOException, FindFailed, Exception {

        CMS_Object paramObject = new CMS_Object.CMS_Builder().setsTestCaseID(sTestCaseID).setsTestCase(sTestCase).setsDescription(sDescription).setsBrowser(sBrowser)
                .setsPlatform(sPlatform).setsVersion(sVersion).setsClonePage(clonePage).setsRegion(sRegion).cmsObject();

        String sauceTestCase = paramObject.getsTestCaseID() + paramObject.getsTestCase();
        setupWebDriver(paramObject.getsBrowser(), paramObject.getsPlatform(), paramObject.getsVersion(), sauceTestCase);

        if (paramObject.getsTestCase().equalsIgnoreCase("CMSPages") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("CMSPages") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFourthyThree(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("CMSAirlines") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("CMSAirlines") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFourthyFour(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("CMSContinent") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("CMSContinent") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFourthyFive(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("CMSCity") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("CMSCity") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFourthySix(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("CMSCountry") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("CMSCountry") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFourthyEight(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("CMSEditPages") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("CMSEditPages") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFourthyThree(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("CMSEditAirlines") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("CMSEditAirlines") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFourthyFour(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("CMSEditContinent") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("CMSEditContinent") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFourthyFive(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("CMSEditCity") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("CMSEditCity") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFourthySix(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("CMSEditCountry") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("CMSEditCountry") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testFourthyEight(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("CMSFlightRoutes") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("CMSFlightRoutes") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testEightyNine(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("CMSProductsPage") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("CMSProductsPage") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testNinety(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("CMSAirportPage") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("CMSAirportPage") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testNinetyOne(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("CMSRoutes") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("CMSRoutes") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testOneHundredAndTwentytwo(paramObject);

            System.out.println("Test is successful");


        } else {

            throw new SkipException("Skip test");

        }
    }

    @Test(groups = {"B2B"}, dataProvider = "Scenarios", dataProviderClass = dataProvider.class)
    public void testB2BTests(String sTestCaseID, String sTestCase, String sDescription, String sBrowser, String sPlatform, String sVersion, String sFlightType, String sTripType, String sDepartureCity, String sArrivalCity, String sDepartureDate, String sReturnDate, String sPreferredAirline, String sPreferredCabinClass, String sCalenderSearch, String sFlexiTicket, String sSortByDeparture, String sSortByReturn, String sFilterBy, String sBookmarkFlight, String sAdultTraveller, String sTeenTraveller, String sChildTraveller, String sInfantTraveller, String sSelectFlexiTicket, String sNewsletter, String sAccountType, String sSMS, String sAddBaggage, String sUpgradeFlexi, String sCellNumber, String sSameAsFirstPassenger, String sProducts, String sAternativeProducts, String sPaymentMethod, String sCardType, String sCardNumber, String sCVV, String sExpiryMonth, String sExpiryYear, String sBank, String sVATInvoice, String sTccVATInvoice, String sVoucher, String sMakeBooking, String sFrequentFlyer, String sMeal, String sSeat, String sReprice, String sPaymentLink, String sActivityLine, String sRefreshItinerary, String sRefreshPNR, String sApisEmail, String sEmailNotification, String sSignup, String sAddTravellers, String sAddCards, String sLogin, String sUsername, String sPassword, String sLang, String sRegion, String sReference, String sTBReference, String sSessionID) throws InterruptedException, IOException, FindFailed, Exception {

        TSParameterObject paramObject = new TSParameterObject.TSParameterBuilder().setsTestCaseID(sTestCaseID).setsTestCase(sTestCase).setsDescription(sDescription)
                .setsBrowser(sBrowser).setsPlatform(sPlatform).setsVersion(sVersion).setsFlightType(sFlightType).setsTripType(sTripType)
                .setsDepartureCity(sDepartureCity).setsArrivalCity(sArrivalCity).setsDepartureDate(sDepartureDate).setsReturnDate(sReturnDate)
                .setsPreferredAirline(sPreferredAirline).setsPreferredCabinClass(sPreferredCabinClass).setsCalenderSearch(sCalenderSearch)
                .setsFlexiTicket(sFlexiTicket).setsSortByDeparture(sSortByDeparture).setsSortByReturn(sSortByReturn).setsFilterBy(sFilterBy)
                .setsBookmarkFlight(sBookmarkFlight).setsAdultTraveller(sAdultTraveller).setsTeenTraveller(sTeenTraveller).setsChildTraveller(sChildTraveller)
                .setsInfantTraveller(sInfantTraveller).setsSelectFlexiTicket(sSelectFlexiTicket).setsNewsletter(sNewsletter).setsAccountType(sAccountType)
                .setsSMS(sSMS).setsAddBaggage(sAddBaggage).setsUpgradeFlexi(sUpgradeFlexi).setsCellNumber(sCellNumber).setsSameAsFirstPassenger(sSameAsFirstPassenger).setsProducts(sProducts).setsAternativeProducts(sAternativeProducts).setsPaymentMethod(sPaymentMethod)
                .setsCardType(sCardType).setsCardNumber(sCardNumber).setsCVV(sCVV).setsExpiryMonth(sExpiryMonth).setsExpiryYear(sExpiryYear).setsBank(sBank).setsVATInvoice(sVATInvoice)
                .setsTccVATInvoice(sTccVATInvoice).setsVoucher(sVoucher).setsMakeBooking(sMakeBooking).setsFrequentFlyer(sFrequentFlyer).setsMeal(sMeal).setsSeat(sSeat).setsReprice(sReprice).setsPaymentLink(sPaymentLink).setsActivityLine(sActivityLine)
                .setsRefreshItinerary(sRefreshItinerary).setsRefreshPNR(sRefreshPNR).setsApisEmail(sApisEmail).setsEmailNotification(sEmailNotification).setsSignUp(sSignup)
                .setsAddTravellers(sAddTravellers).setsAddCards(sAddCards).setsLogin(sLogin).setsUsername(sUsername).setsPassword(sPassword).setsLang(sLang).setsRegion(sRegion).tsParameterObject();

        System.out.println("Display initial region: " + paramObject.getsRegion());
        System.out.println("Set by builder: " + paramObject.getsRegion());

        //String sauceTestCase = sTestCaseID + sTestCase;
        String sauceTestCase = paramObject.getsTestCaseID() + paramObject.getsTestCase();
        setupWebDriver(paramObject.getsBrowser(), paramObject.getsPlatform(), paramObject.getsVersion(), sauceTestCase);

        if (paramObject.getsTestCase().equalsIgnoreCase("B2BExistingAccountLogin") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("B2BExistingAccountLogin") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSeventySeven(paramObject);

            System.out.println("Test is successful");
        } else if (paramObject.getsTestCase().equalsIgnoreCase("B2BValidateDomesticBooking") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("B2BValidateDomesticBooking") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSeventyEight(paramObject);

            System.out.println("Test is successful");
        } else if (paramObject.getsTestCase().equalsIgnoreCase("B2BBookingReflectsOnDashboard") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("B2BBookingReflectsOnDashboard") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testSeventyNine(paramObject);

            System.out.println("Test is successful");
        } else if (paramObject.getsTestCase().equalsIgnoreCase("B2B_BookingDetailsTCC") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("B2B_BookingDetailsTCC") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testEighty(paramObject);

            System.out.println("Test is successful");
        } else if (paramObject.getsTestCase().equalsIgnoreCase("B2B_ValidatePaymentLink") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("B2B_ValidatePaymentLink") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testEightyOne(paramObject);

            System.out.println("Test is successful");
        } else if (paramObject.getsTestCase().equalsIgnoreCase("B2BPaymentlinkChangeFOP") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("B2BPaymentlinkChangeFOP") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testEightyTwo(paramObject);

            System.out.println("Test is successful");
        } else if (paramObject.getsTestCase().equalsIgnoreCase("B2B_ValidateCancelledBookingOnDashboard") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("B2B_ValidateCancelledBookingOnDashboard") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testEightyFive(paramObject);

            System.out.println("Test is successful");
        } else {

            throw new SkipException("Skip test");
        }
    }

    /*
    @Test(groups={"Hotels"}, dataProvider = "hotelsProvider", dataProviderClass = dataProvider.class)
    public void hotelsTests(HotelsParamObject hotelsParamObject) throws Exception {
        System.out.println(hotelsParamObject.getTestCase());
        String sauceTestCase = hotelsParamObject.getTestCaseId()+ hotelsParamObject.getTestCase();
        setupWebDriver(hotelsParamObject.getBrowser(), hotelsParamObject.getPlatform(), hotelsParamObject.getVersion(), sauceTestCase);
        if (hotelsParamObject.getTestCase().equalsIgnoreCase("ValidHotelSearch")) {
            testNinetySeven(hotelsParamObject);
            System.out.println("Test is successful");
        } else if (hotelsParamObject.getTestCase().equalsIgnoreCase("ValidHotelPaymentPage")) {
            testNinetyEight(hotelsParamObject);
            System.out.println("Test is successful");
        } else if (hotelsParamObject.getTestCase().equalsIgnoreCase("ValidHotelSortByFields")) {
            testNinetyNine(hotelsParamObject);
            System.out.println("Test is successful");
        } else if (hotelsParamObject.getTestCase().equalsIgnoreCase("ValidHotelPersonalDetails")) {
            testOneHundred(hotelsParamObject);
            System.out.println("Test is successful");
        } else if (hotelsParamObject.getTestCase().equalsIgnoreCase("ValidatePrice")) {
            testOneHundredAndOne(hotelsParamObject);
            System.out.println("Test is successful");
        } else {
            throw new SkipException("Skip test");
        }
    } */

    @Test(groups = {"Hotels"}, dataProvider = "Scenarios", dataProviderClass = dataProvider.class)
    public void hotelsTests(String sTestCaseId, String sTestCase, String sDescription, String sBrowser, String sPlatform, String sVersion, String sDestination, String sCheckinDate, String sCheckoutDate, String sRoom, String sAdult, String sChild, String sFilterByStarRating, String sFilterByProperty, String sFilterByPriceRange, String sResults) throws Exception {
        HotelsParamObject hotelsParamObject = new HotelsParamObject(sTestCaseId, sTestCase, sDescription, sBrowser, sPlatform, sVersion, sDestination, sCheckinDate, sCheckoutDate, sRoom, sAdult, sChild, sFilterByStarRating, sFilterByProperty, sFilterByPriceRange, sResults);
        System.out.println(hotelsParamObject.getTestCase());

        String sauceTestCase = hotelsParamObject.getTestCaseId() + hotelsParamObject.getTestCase();
        setupWebDriver(hotelsParamObject.getBrowser(), hotelsParamObject.getPlatform(), hotelsParamObject.getVersion(), sauceTestCase);

        if (hotelsParamObject.getTestCase().equalsIgnoreCase("ValidHotelSearch")) {
            testNinetySeven(hotelsParamObject);

            System.out.println("Test is successful");
        } else if (hotelsParamObject.getTestCase().equalsIgnoreCase("ValidHotelPaymentPage")) {
            testNinetyEight(hotelsParamObject);

            System.out.println("Test is successful");
        } else if (hotelsParamObject.getTestCase().equalsIgnoreCase("ValidHotelSortByFields")) {
            testNinetyNine(hotelsParamObject);

            System.out.println("Test is successful");
        } else if (hotelsParamObject.getTestCase().equalsIgnoreCase("ValidHotelPersonalDetails")) {
            testOneHundred(hotelsParamObject);

            System.out.println("Test is successful");
        } else if (hotelsParamObject.getTestCase().equalsIgnoreCase("ValidatePrice")) {
            testOneHundredAndOne(hotelsParamObject);

            System.out.println("Test is successful");
        } else if (hotelsParamObject.getTestCase().equalsIgnoreCase("validateLinks")) {
            testOneHundredAndTwo(hotelsParamObject);

            System.out.println("Test is successful");
        } else {
            throw new SkipException("Skip test");
        }
    }

    @Test(groups = {"BusinessAccountTests"}, dataProvider = "Scenarios", dataProviderClass = dataProvider.class)
    public void testBusinessAccountTests(String sTestCaseID, String sTestCase, String sDescription, String sBrowser, String sPlatform, String sVersion, String sFlightType, String sTripType, String sDepartureCity, String sArrivalCity, String sDepartureDate, String sReturnDate, String sPreferredAirline, String sPreferredCabinClass, String sCalenderSearch, String sFlexiTicket, String sSortByDeparture, String sSortByReturn, String sFilterBy, String sBookmarkFlight, String sAdultTraveller, String sTeenTraveller, String sChildTraveller, String sInfantTraveller, String sSelectFlexiTicket, String sNewsletter, String sAccountType, String sSMS, String sAddBaggage, String sUpgradeFlexi, String sCellNumber, String sSameAsFirstPassenger, String sProducts, String sAternativeProducts, String sPaymentMethod, String sCardType, String sCardNumber, String sCVV, String sExpiryMonth, String sExpiryYear, String sBank, String sVATInvoice, String sTccVATInvoice, String sVoucher, String sMakeBooking, String sFrequentFlyer, String sMeal, String sSeat, String sReprice, String sPaymentLink, String sActivityLine, String sRefreshItinerary, String sRefreshPNR, String sApisEmail, String sEmailNotification, String sSignup, String sAddTravellers, String sAddCards, String sLogin, String sUsername, String sPassword, String sLang, String sRegion, String sReference, String sTBReference, String sSessionID) throws InterruptedException, IOException, FindFailed, Exception {

        TSParameterObject paramObject = new TSParameterObject.TSParameterBuilder().setsTestCaseID(sTestCaseID).setsTestCase(sTestCase).setsDescription(sDescription)
                .setsBrowser(sBrowser).setsPlatform(sPlatform).setsVersion(sVersion).setsFlightType(sFlightType).setsTripType(sTripType)
                .setsDepartureCity(sDepartureCity).setsArrivalCity(sArrivalCity).setsDepartureDate(sDepartureDate).setsReturnDate(sReturnDate)
                .setsPreferredAirline(sPreferredAirline).setsPreferredCabinClass(sPreferredCabinClass).setsCalenderSearch(sCalenderSearch)
                .setsFlexiTicket(sFlexiTicket).setsSortByDeparture(sSortByDeparture).setsSortByReturn(sSortByReturn).setsFilterBy(sFilterBy)
                .setsBookmarkFlight(sBookmarkFlight).setsAdultTraveller(sAdultTraveller).setsTeenTraveller(sTeenTraveller).setsChildTraveller(sChildTraveller)
                .setsInfantTraveller(sInfantTraveller).setsSelectFlexiTicket(sSelectFlexiTicket).setsNewsletter(sNewsletter).setsAccountType(sAccountType)
                .setsSMS(sSMS).setsAddBaggage(sAddBaggage).setsUpgradeFlexi(sUpgradeFlexi).setsCellNumber(sCellNumber).setsSameAsFirstPassenger(sSameAsFirstPassenger).setsProducts(sProducts).setsAternativeProducts(sAternativeProducts).setsPaymentMethod(sPaymentMethod)
                .setsCardType(sCardType).setsCardNumber(sCardNumber).setsCVV(sCVV).setsExpiryMonth(sExpiryMonth).setsExpiryYear(sExpiryYear).setsBank(sBank).setsVATInvoice(sVATInvoice)
                .setsTccVATInvoice(sTccVATInvoice).setsVoucher(sVoucher).setsMakeBooking(sMakeBooking).setsFrequentFlyer(sFrequentFlyer).setsMeal(sMeal).setsSeat(sSeat).setsReprice(sReprice).setsPaymentLink(sPaymentLink).setsActivityLine(sActivityLine)
                .setsRefreshItinerary(sRefreshItinerary).setsRefreshPNR(sRefreshPNR).setsApisEmail(sApisEmail).setsEmailNotification(sEmailNotification).setsSignUp(sSignup)
                .setsAddTravellers(sAddTravellers).setsAddCards(sAddCards).setsLogin(sLogin).setsUsername(sUsername).setsPassword(sPassword).setsLang(sLang).setsRegion(sRegion).tsParameterObject();

        String sauceTestCase = paramObject.getsTestCaseID() + paramObject.getsTestCase();
        setupWebDriver(paramObject.getsBrowser(), paramObject.getsPlatform(), paramObject.getsVersion(), sauceTestCase);

        if (paramObject.getsTestCase().equalsIgnoreCase("SignUpBusiness") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("SignUpBusiness") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testOneHundredAndSixteen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("EditProfileDetails") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("EditProfileDetails") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testOneHundredAndSeventeen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("AddAndRemoveTraveller") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("AddAndRemoveTraveller") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testOneHundredAndEighteen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("AddAndRemoveCard") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("AddAndRemoveCard") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testOneHundredAndNineteen(paramObject);

            System.out.println("Test is successful");

        } else if (paramObject.getsTestCase().equalsIgnoreCase("ForgotPassword") && MARKET.equalsIgnoreCase("ALL") || paramObject.getsTestCase().equalsIgnoreCase("ForgotPassword") && !MARKET.equalsIgnoreCase("ALL") && paramObject.getsRegion().equals(MARKET)) {

            testOneHundredAndTwenty(paramObject);

            System.out.println("Test is successful");

        } else {

            throw new SkipException("Skip test");

        }
    }

    @Test(enabled = true)
    public void testOne(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            VoucherUtil voucherUtil = new VoucherUtil(paramObject);
            //String sVoucherCode= voucherUtil.createVoucher(paramObject.getsVoucher(), paramObject.getsRegion());

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            boolean flgBookingNotCompleted = false;

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            NewPaymentsPage paymentPage = new NewPaymentsPage(paramObject);

            flgBookingNotCompleted = paymentPage.availableBookingNotCompleted();
            System.out.println("Booking no longer available status:" + flgBookingNotCompleted);

            if (flgBookingNotCompleted) {
                //payment method its BudgetCC
                //log in to TCC, to search for reference as budgetCC booking does not accept 4242424242424242 as test card, but booking its collected along the wway
                //validate that it appears on TCC

                TCCLogin login = new TCCLogin(paramObject);
                login.logintoTCC(paramObject.getsRegion());

                Customer customer = new Customer();

                //Search by Email
                login.searchByEmail(makeBooking.getPassengerDetails().get(0));
                login.validateSearchByEmail(makeBooking.getPassengerDetails().get(0));
                String refNo = customer.getTravelBarReference();
                System.out.println("Reference number is :" + refNo);

                //validte witFh customer details
                FlightDetail passAdult = makeBooking.getPassengerDetails().get(0);
                String fname = passAdult.getLstAdultFname().get(0);
                String lname = passAdult.getLstAdultLname().get(0);
                String email = passAdult.getEmailAddress();
                String cellNo = "27" + paramObject.getsCellNumber();
                String phoneNo = "27 21 4684380";

                customer.verifyCustomer(fname, lname, phoneNo, cellNo, email);

            } else {//finish making booking for other payment method

                SaveReference savereference = new SaveReference();
                savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

                FlightDetail test = makeBooking.getVoucherDetail();
                String voucher = test.getVoucherCode();
                System.out.println("voucher: " + voucher);

                TccModule tccModule = new TccModule(makeBooking, paramObject);
                tccModule.tccModule(paramObject, confirmDetail, voucher);
                tccModule.validateFrequentFlyer(confirmDetail);
                tccModule.validateMeal(confirmDetail);
                tccModule.validateSeat(confirmDetail);
                tccModule.validateVoucher();
            }

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testTwo(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            FlightDetail flightDetail = new FlightDetail();
            BookFlight makeBooking = new BookFlight();

            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            FlightDetail test = makeBooking.getVoucherDetail();
            String voucher = test.getVoucherCode();
            System.out.println("voucher: " + voucher);

            TccModule tccModule = new TccModule(makeBooking, paramObject);
            tccModule.tccModule(paramObject, confirmDetail, voucher);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testFour(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            FlightDetail test = makeBooking.getVoucherDetail();
            String voucher = test.getVoucherCode();
            System.out.println("voucher " + voucher);

            TccModule tccModule = new TccModule(makeBooking, paramObject);
            tccModule.tccModule(paramObject, confirmDetail, voucher);

            PassengersAndFlights flight = new PassengersAndFlights();
            flight.refreshPNR();

            Invoices invoices = new Invoices();
            invoices.sendPaymentLink();

            TCCLogout logout = new TCCLogout();
            logout.logoutofTCC();

            VerifyNotifications verify = new VerifyNotifications(paramObject);
            verify.verifyPaymentLink(paramObject.getsRegion(), confirmDetail);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testFive(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            FlightDetail test = makeBooking.getVoucherDetail();
            String voucher = test.getVoucherCode();
            System.out.println("voucher " + voucher);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            TccModule tccModule = new TccModule(makeBooking, paramObject);
            tccModule.tccModule(paramObject, confirmDetail, voucher);

            //PassengersAndFlights flight = new PassengersAndFlights();
            //flight.reprice();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testEight(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            FlightDetail test = makeBooking.getVoucherDetail();
            String voucher = test.getVoucherCode();
            System.out.println("voucher " + voucher);

            TccModule tccModule = new TccModule(makeBooking, paramObject);
            tccModule.tccModule(paramObject, confirmDetail, voucher);

            Invoices invoices = new Invoices();
            invoices.sendPaymentLink();

            VerifyNotifications verify = new VerifyNotifications(paramObject);
            verify.verifyBookingConfirmation(confirmDetail);
            verify.verifyPaymentLink(paramObject.getsRegion(), confirmDetail);

            //CancellationModule cancelationmodule = new CancellationModule();
            //cancelationmodule.CancellationModule(confirmDetail, sRegion);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testNine(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();

            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            TCCLogin login = new TCCLogin(paramObject);
            login.logintoTCC(paramObject.getsRegion());
            login.searchFlight2(confirmDetail);

            PassengersAndFlights flight = new PassengersAndFlights();
            //flight.getPNR();
            flight.getPNRdynamic();

            SelectMarket market = new SelectMarket();
            market.selectTravelBar();

            login.search();
            login.createBooking();

            Customer customer = new Customer();
            customer.markasTest();
            customer.skipAutoTicket();
            customer.enterCustomerDetails();

            //flight.createPNR();
            flight.createPNRDynamic();

            Activities activity = new Activities(paramObject);
            activity.checkallActivities();

            Invoices invoices = new Invoices();
            invoices.sendNewBookingPaymentLink();

            String tbreference = customer.getTravelBarReference();
            confirmDetail.setTBReference(tbreference);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            market.selectAll();
            login.search();
            customer.markasTest();
            customer.skipAutoTicket();

            String price = invoices.sendPaymentLink();
            //price = invoices.subtractPaymentMethodAmount(price);

            Thread.sleep(5000);
            TCCLogout logout = new TCCLogout();
            logout.logoutofTCC();

            VerifyNotifications verify = new VerifyNotifications(paramObject);
            verify.verifyBookingConfirmation(confirmDetail);

            PaymentsLinkPage paymentlink = new PaymentsLinkPage(paramObject);

            paymentlink.gotoPaymentsLink(paramObject.getsRegion(), confirmDetail);
            paymentlink.uncheckProductsAndgetTotalPrice(price);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testTen(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            TCCLogin login = new TCCLogin(paramObject);
            login.logintoTCC(paramObject.getsRegion());

            CreateSource source = new CreateSource();
            source.setupAffiliate(paramObject.getsRegion());

            TCCLogout logout = new TCCLogout();
            logout.logoutofTCC();

            source.sendfiletoServer();
            source.getAffiliateURL();

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            login.logintoTCC(paramObject.getsRegion());
            login.searchFlight2(confirmDetail);

            Customer customer = new Customer();
            customer.verifyAffiliateType();

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            customer.markasTest();
            customer.skipAutoTicket();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            //session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testEleven(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            GetURL url = new GetURL();
            url.getURL(paramObject.getsRegion());

            NewHomePage newhome = new NewHomePage(paramObject);
            newhome.waitforAffiliateLink();

            SignupAffiliate signup = new SignupAffiliate();
            signup.signupAffiliate();

            TCCLogin login = new TCCLogin(paramObject);
            login.logintoTCC(paramObject.getsRegion());
            login.searchAffiliate();

            CreateSource source = new CreateSource();
            source.setupAffiliateAuto(paramObject.getsRegion());

            TCCLogout logout = new TCCLogout();
            logout.logoutofTCC();

            source.sendfiletoServer();
            source.getAffiliateURL();

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            login.logintoTCC(paramObject.getsRegion());
            login.searchFlight2(confirmDetail);

            Customer customer = new Customer();
            customer.verifyAffiliateType();

            customer.markasTest();
            customer.skipAutoTicket();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testTwelve(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);
            SearchModule searchmodule = new SearchModule();
            List<FlightDetail> searchDetail = searchmodule.SearchModule(paramObject, homeDetail);

            NewResultsPage results = new NewResultsPage(paramObject);
            results.verifyCalenderSearch(paramObject.getsTripType(), paramObject.getsFlightType(), paramObject.getsDepartureDate(),
                    paramObject.getsReturnDate(), paramObject.getsCalenderSearch(), paramObject.getsLang());
            results.verifySearchResults(paramObject.getsTestCase(), paramObject.getsTestCaseID(), paramObject.getsRegion());
            results.verifyPreferredCabinClass(paramObject.getsTripType(), paramObject.getsPreferredCabinClass(), paramObject.getsFlightType());
            results.verifyPrefererredAirline(paramObject.getsTripType(), paramObject.getsPreferredAirline(), paramObject.getsFlightType(), paramObject.getsRegion());

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);

        }

    }

    @Test(enabled = true)
    public void testThirteen(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            FlightDetail flightDetail = new FlightDetail();

            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);
            SearchModule searchmodule = new SearchModule();
            List<FlightDetail> searchDetail = searchmodule.SearchModule(paramObject, homeDetail);

            NewResultsPage results = new NewResultsPage(paramObject);
            results.verifyPrefererredAirline(paramObject.getsTripType(), paramObject.getsPreferredAirline(), paramObject.getsFlightType(), paramObject.getsRegion());

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);

        }

    }

    @Test(enabled = true)
    public void testFourteen(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            FlightDetail flightDetail = new FlightDetail();

            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);
            SearchModule searchmodule = new SearchModule();
            List<FlightDetail> searchDetail = searchmodule.SearchModule(paramObject, homeDetail);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testFifteen(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            VoucherUtil voucherUtil = new VoucherUtil(paramObject);
            String sVoucherCode = voucherUtil.createVoucher(paramObject.getsVoucher(), paramObject.getsRegion());

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            FlightDetail flightDetail = new FlightDetail();

            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);
            SearchModule searchmodule = new SearchModule();
            List<FlightDetail> searchDetails = searchmodule.SearchModule(paramObject, homeDetail);
            ResultsModule resultsmodule = new ResultsModule();
            List<FlightDetail> flightDetails = resultsmodule.ResultsModule(paramObject, searchDetails);
            ReviewModule reviewmodule = new ReviewModule();
            FlightDetail reviewDetail = reviewmodule.ReviewModule(paramObject, searchDetails, flightDetails);
            PassengerModule passengersmodule = new PassengerModule();
            List<FlightDetail> passengersDetail = passengersmodule.PassengerModule(paramObject, searchDetails, flightDetails);
            ProductsModule productsmodule = new ProductsModule();
            List<FlightDetail> productsDetail = productsmodule.ProductsModule(paramObject, homeDetail, passengersDetail);
            PaymentsModule paymentsmodule = new PaymentsModule(new NewPaymentsPage(paramObject));
            FlightDetail paymentsDetail = paymentsmodule.PaymentsModule(paramObject, sVoucherCode, homeDetail, passengersDetail);
            NewPaymentsPage newpayments = new NewPaymentsPage(paramObject);
            newpayments.assertCreditCardErrors(paramObject.getsCardNumber(), paramObject.getsCVV(), paramObject.getsExpiryMonth(), paramObject.getsExpiryYear());

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testSixteen(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            FlightDetail flightDetail = new FlightDetail();

            AccountHomeModule homemodule = new AccountHomeModule();
            List<FlightDetail> homeDetail = homemodule.AccountHomeModule(paramObject);

            SignupPage signup = new SignupPage(paramObject);
            signup.signOut();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testSeventeen(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            FlightDetail flightDetail = null;

            AccountHomeModule homemodule = new AccountHomeModule();
            List<FlightDetail> homeDetail = homemodule.AccountHomeModule(paramObject);

            ProfileModule profileModule = new ProfileModule(paramObject);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testEighteen(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            FlightDetail test = makeBooking.getVoucherDetail();
            String voucher = test.getVoucherCode();
            System.out.println("voucher " + voucher);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            TccModule tccModule = new TccModule(makeBooking, paramObject);
            tccModule.tccModule(paramObject, confirmDetail, voucher);

            SignupPage signup = new SignupPage(paramObject);
            signup.activateAccount(paramObject.getsTestCase(), makeBooking.getPassengerDetails(), paramObject.getsRegion());

            BookingsPage book = new BookingsPage(paramObject);
            book.verifyBookingDetails(makeBooking.getflightDetails(), makeBooking.getPassengerDetails(), confirmDetail, paramObject.getsRegion());

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testNineteen(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            FlightDetail flightDetail = new FlightDetail();

            AccountHomeModule homemodule = new AccountHomeModule();
            List<FlightDetail> homeDetail = homemodule.AccountHomeModule(paramObject);

            TravellersModule travellersModule = new TravellersModule(paramObject, homeDetail);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testTwenty(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            FlightDetail flightDetail = new FlightDetail();

            AccountHomeModule homemodule = new AccountHomeModule();
            List<FlightDetail> homeDetail = homemodule.AccountHomeModule(paramObject);

            ProfileModule profileModule = new ProfileModule(paramObject);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testTwentyOne(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            AccountHomeModule accounthomemodule = new AccountHomeModule();
            List<FlightDetail> homeDetail = accounthomemodule.AccountHomeModule(paramObject);

            TravellersModule travellersModule = new TravellersModule(paramObject, homeDetail);

            BookingsPage book = new BookingsPage(paramObject);
            book.bookFlight();

            NewHomePage home = new NewHomePage(paramObject);
            home.waitforLoggedinState(paramObject.getsRegion(), homeDetail);

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testTwentyTwo(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            FlightDetail flightDetail = new FlightDetail();


            AccountHomeModule homemodule = new AccountHomeModule();
            List<FlightDetail> homeDetail = homemodule.AccountHomeModule(paramObject);

            ProfileModule profileModule = new ProfileModule(paramObject);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testTwentyThree(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            AccountHomeModule homemodule = new AccountHomeModule();
            List<FlightDetail> homeDetail = homemodule.AccountHomeModule(paramObject);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testTwentyFour(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            FlightDetail flightDetail = new FlightDetail();

            AccountHomeModule homemodule = new AccountHomeModule();
            List<FlightDetail> homeDetail = homemodule.AccountHomeModule(paramObject);

            TravellersModule travellersModule = new TravellersModule(paramObject, homeDetail);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testTwentyFive(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            FlightDetail flightDetail = new FlightDetail();

            AccountHomeModule homemodule = new AccountHomeModule();
            List<FlightDetail> homeDetail = homemodule.AccountHomeModule(paramObject);

            ProfileModule profileModule = new ProfileModule(paramObject);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testTwentySix(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            FlightDetail flightDetail = new FlightDetail();

            AccountHomeModule homemodule = new AccountHomeModule();
            List<FlightDetail> homeDetail = homemodule.AccountHomeModule(paramObject);

            ProfileModule profileModule = new ProfileModule(paramObject);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testTwentySeven(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            TCCLogin sign_in = new TCCLogin(paramObject);
            sign_in.logintoTCC(paramObject.getsRegion());
            Thread.sleep(3000);

            System.out.println("Selecting market passed");

            //Selecting a market
            SelectMarket tb_market = new SelectMarket();
            tb_market.selectTravelBar();

            sign_in.createBooking();

            System.out.println("Creating a customer in TCC now");
            Customer newCustomer = new Customer();
            newCustomer.enterCustomerDetails();

            System.out.println("submitting a comment in TCC now");

            Thread.sleep(3000);
            Comments newComment = new Comments();
            newComment.writeComment();

            System.out.println("Test is Finished");

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testTwentyEight(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);
            NewSearchPage search = new NewSearchPage(paramObject);
            search.selectTripType(paramObject.getsTripType(), paramObject.getsTestCase());
            search.enterTrip(paramObject.getsBrowser(), paramObject.getsFlightType(), paramObject.getsTripType(), paramObject.getsDepartureCity(), paramObject.getsArrivalCity(),
                    paramObject.getsDepartureDate(), paramObject.getsReturnDate(), paramObject.getsLang(), paramObject.getsRegion(), paramObject.getsTestCase(), homeDetail, paramObject);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testTwentyNine(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        FlightDetail flightD_set = new FlightDetail();
        try {


            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);

            SearchModule searchmodule = new SearchModule();
            List<FlightDetail> searchDetails = searchmodule.SearchModule(paramObject, homeDetail);

            ResultsModule resultsmodule = new ResultsModule();
            List<FlightDetail> flightDetails = resultsmodule.ResultsModule(paramObject, searchDetails);
            ReviewModule reviewmodule = new ReviewModule();
            FlightDetail reviewDetail = reviewmodule.ReviewModule(paramObject, searchDetails, flightDetails);
            PassengerModule passengersmodule = new PassengerModule();
            List<FlightDetail> passengersDetail = passengersmodule.PassengerModule(paramObject, searchDetails, flightDetails);


            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testThirty(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            FlightDetail test = makeBooking.getVoucherDetail();
            String voucher = test.getVoucherCode();
            System.out.println("voucher " + voucher);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            TccModule tccModule = new TccModule(makeBooking, paramObject);
            tccModule.tccModule(paramObject, confirmDetail, voucher);

            Invoices invoices = new Invoices();
            String price = invoices.sendPaymentLink();
            String newPrice = "";

            TCCLogout logout = new TCCLogout();
            logout.logoutofTCC();

            VerifyNotifications verify = new VerifyNotifications(paramObject);
            verify.verifyBookingConfirmation(confirmDetail);

            PaymentsLinkPage paymentlink = new PaymentsLinkPage(paramObject);
            paymentlink.gotoPaymentsLink(paramObject.getsRegion(), confirmDetail);

            String strChangePaymentM = "CC";
            List<FlightDetail> lstFlightD = new ArrayList<FlightDetail>();

            NewProductsPage extras = new NewProductsPage(paramObject);
            NewPaymentsPage paymentsPage = new NewPaymentsPage(paramObject);
            NewConfirmPage confirmPage = new NewConfirmPage(paramObject);
            UpdateApisLink updatelink = new UpdateApisLink(paramObject);
            NewPassengersPage passenger = new NewPassengersPage(paramObject);

            boolean isThereTravellers = updatelink.isTravellersPage();

            if (isThereTravellers) {
                passenger.enterAdultPassportForNewLink(paramObject.getsRegion(), Integer.parseInt(paramObject.getsAdultTraveller()), confirmDetail);
                System.out.println("Clicking continue page on travellers -> Extras");
                extras.continueButton();

            }

            paymentlink.uncheckProductsAndgetTotalPrice(price);

            //verify products chosen
            FlightDetail checkProd = makeBooking.getProductDetails().get(0);
            extras.verifyProductChosen(paramObject.getsProducts(), checkProd);
            extras.continueButton();

            //change payment from EFT to CC
            //clicking credit card
            paymentsPage.selectPaymentMethod(strChangePaymentM, paramObject.getsRegion());
            //enter cc details
            paymentsPage.selectCardType(paramObject.getsFlightType(), strChangePaymentM, paramObject.getsCardType(), "Emmanuel",
                    paramObject.getsCardNumber(), paramObject.getsCVV(), paramObject.getsExpiryMonth(), paramObject.getsExpiryYear(), paramObject.getsRegion());
            //clicking continue
            float totPrice = paymentsPage.getTotalPrice();
            paymentsPage.reserveButton(false, paramObject.getsRegion(), paramObject.getsMakeBooking());
            //confirm details
            String newTicket = confirmPage.getTicketReference();
            System.out.println(newTicket + " = " + confirmDetail.getReference());

            //log in again to TCC to cofirm that booking payment was changed
            TCCLogin login = new TCCLogin(paramObject);
            login.logintoTCC(paramObject.getsRegion());
            login.searchFlight2(confirmDetail);
            invoices.validateExtraPaymentMethod();
            //add total price with R49 as VISA was selected as new payment method
            invoices.validateInvoiceTotal(Float.toString(totPrice));

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testThirtyOne(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        FlightDetail flightDet = new FlightDetail();
        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            //create a booking, and it store passangers details, and use it along the rest of the loop
            //1st scan: creates the normal booking, aim to store passangers details
            //2nd scan: use passangers details based on 1st scan, and create duplicate booking, while Booking status is PENDING
            //3rd scan: create booking while its status is CANCELLED (popup shouldn't appear)
            //4th scan: created a booking while its status is PAID(Duplicate popup should appear)
            //5th scan: cancel all Ref made

            for (int k = 0; k < 5; k++) {

                //local variable, dynamical object
                FlightDetail flightD = new FlightDetail();
                Customer customer = new Customer();
                PassengersAndFlights passenger = new PassengersAndFlights();
                TCCLogin login = new TCCLogin(paramObject);
                NewHomePage homp = new NewHomePage(paramObject);
                BookFlight objBookF = new BookFlight();
                CancellationModule cancelMode = new CancellationModule();

                if (k == 0) {

                    System.out.printf("%n%s%n", "Making a normal booking");
                    FlightDetail confirmDetail = objBookF.sharedBookFlight(paramObject);
                    //set duplicate as true
                    flightD.setDuplicateBooking(true);
                    //set origin flight details
                    flightD.setOriginFlightD(confirmDetail);

                }
                //popup should appear under "Pending"
                else if (k == 1) {

                    login.logintoTCC(paramObject.getsRegion());
                    login.searchFlight2(flightD.getOriginalFlightD().get(0));
                    customer.markasTest();
                    customer.skipAutoTicket();
                    flightD.setStatus(customer.getStatus());

                    System.out.printf("%n%s%n", "Making a duplicate booking");
                    homp.closeBrowser();

                    //start another browse
                    setupWebDriver(paramObject.getsBrowser(), paramObject.getsPlatform(), paramObject.getsVersion(), paramObject.getsTestCase());

                    FlightDetail confirmDetail = objBookF.sharedBookFlight(paramObject);
                    flightD.setOriginFlightD(confirmDetail);


                }
                //there should be no popups under "Cancelled"
                else if (k == 2) {


                    //login to TCC and cancel original booking, and attempt to book a ticket with previous details
                    System.out.printf("%n%s%n", "Making a normal booking while the previous booking status is cancelled ");

                    //cancelling the two ref
                    cancelMode.CancellationModule(paramObject, flightD.getOriginalFlightD().get(0), paramObject.getsRegion());
                    cancelMode.CancellationModule(paramObject, flightD.getOriginalFlightD().get(1), paramObject.getsRegion());

                    //identify status is cancelled, through TCC
                    login.logintoTCC(paramObject.getsRegion());
                    login.searchFlight2(flightD.getOriginalFlightD().get(0));//cancel original booking 2nd
                    flightD.setStatus(customer.getStatus());
                    login.searchFlight2(flightD.getOriginalFlightD().get(1));//cancel original booking 2nd
                    flightD.setStatus(customer.getStatus());
                    homp.closeBrowser();

                    //start another browse
                    setupWebDriver(paramObject.getsBrowser(), paramObject.getsPlatform(), paramObject.getsVersion(), paramObject.getsTestCase());

                    //make a booking with same/original passangers details to proof that popup does'nt show
                    FlightDetail confirmDetail = objBookF.sharedBookFlight(paramObject);
                    flightD.setOriginFlightD(confirmDetail);

                }
                //popup should appear under "Paid"
                else if (k == 3) {

                    //login to TCC and booking status to paid
                    login.logintoTCC(paramObject.getsRegion());
                    login.searchFlight2(flightD.getOriginalFlightD().get(2));
                    customer.markasTest();
                    customer.skipAutoTicket();
                    Invoices invoices = new Invoices();
                    invoices.changeStatusToPaid();
                    flightD.setStatus(customer.getStatus());
                    homp.closeBrowser();

                    //start another browse
                    setupWebDriver(paramObject.getsBrowser(), paramObject.getsPlatform(), paramObject.getsVersion(), paramObject.getsTestCase());
                    //make another booking while it is in paid mode
                    System.out.println("Making a booking while its status is Paid");
                    FlightDetail confirmDetail2 = objBookF.sharedBookFlight(paramObject);
                    flightD.setOriginFlightD(confirmDetail2);
                    homp.closeBrowser();

                }
                //cancel created Ref
                else if (k == 4) {
                    //if the test pass cancell all the ref
                    for (int i = 0; i < flightD.getOriginalFlightD().size(); i++) {
                        cancelMode.CancellationModule(paramObject, flightD.getOriginalFlightD().get(i), paramObject.getsRegion());
                    }

                }
            }

            flightDet.setDuplicateBooking(false);//make names to be picked randomly for passengers details
            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {
            //if the test fails cancell all the ref
            FlightDetail flightD = new FlightDetail();
            CancellationModule cancelMode = new CancellationModule();

            flightDet.setDuplicateBooking(false);//make names to be picked randomly for passengers details

            for (int i = 0; i < flightD.getOriginalFlightD().size(); i++) {
                cancelMode.CancellationModule(paramObject, flightD.getOriginalFlightD().get(i), paramObject.getsRegion());
            }

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testThirtyTwo(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        String name = null, surname = null;
        try {

            for (int i = 0; i < 4; i++) {
                //local variable, dynamical object
                FlightDetail flightD = new FlightDetail();
                NewHomePage homp = new NewHomePage(paramObject);
                CancellationModule cancelMode = new CancellationModule();

                if (i == 0) {//PassengerNameValidation

                    //set test case
                    String sTestCase = "PassengerNameValidation";

                    System.out.println("Test case started");
                    System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());
                    FlightDetail flightDetail = new FlightDetail();
                    HomeModule homemodule = new HomeModule();
                    FlightDetail homeDetail = homemodule.HomeModule(paramObject);
                    SearchModule searchmodule = new SearchModule();
                    List<FlightDetail> searchDetails = searchmodule.SearchModule(paramObject, homeDetail);
                    ResultsModule resultsmodule = new ResultsModule();
                    List<FlightDetail> flightDetails = resultsmodule.ResultsModule(paramObject, searchDetails);
                    ReviewModule reviewmodule = new ReviewModule();
                    FlightDetail reviewDtail = reviewmodule.ReviewModule(paramObject, searchDetails, flightDetails);
                    PassengerModule passengersmodule = new PassengerModule();
                    List<FlightDetail> passengersDetail = passengersmodule.PassengerModule(paramObject, searchDetails, flightDetails);
                    System.out.println("Quiting browser");
                    homp.closeBrowser();

                    if (bResult == true) {
                        System.out.println("Test is successful");
                        System.out.println();
                        ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

                    } else {
                        throw new Exception("Test Case Failed, please check logs");
                    }

                } else if (i == 1) {//PassengerNameEraseSpecialChars
                    //set test case
                    String sTestCase = "PassengerNameEraseSpecialChars";

                    //start another browse
                    setupWebDriver(paramObject.getsBrowser(), paramObject.getsPlatform(), paramObject.getsVersion(), sTestCase);

                    System.out.println("Test case started");
                    System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

                    BookFlight makeBooking = new BookFlight();

                    FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);
                    FlightDetail contactDetails = makeBooking.getPassengerDetails().get(0);

                    name = contactDetails.getLstAdultFname().get(0);
                    surname = contactDetails.getLstAdultLname().get(0);

                    System.out.println("------------------------------------------WAIT-------------------------------------");
                    Thread.sleep(10000);
                    System.out.println(name);
                    System.out.println(surname);

                    NewConfirmPage confirm = new NewConfirmPage(paramObject);
                    confirm.verifyTravellersDetails(sTestCase, name, surname);

                    SaveReference savereference = new SaveReference();
                    savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());
                    System.out.println("Quiting browser");
                    homp.closeBrowser();
                    if (bResult == true) {
                        System.out.println("Test is successful");
                        System.out.println();
                        ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

                    } else {
                        throw new Exception("Test Case Failed, please check logs");
                    }


                } else if (i == 2) {//PassengerNameConcatenation
                    //set test case
                    String sTestCase = "PassengerNameConcatenation";
                    //start another browse
                    setupWebDriver(paramObject.getsBrowser(), paramObject.getsPlatform(), paramObject.getsVersion(), sTestCase);

                    System.out.println("Test case started");
                    System.out.println(paramObject.getsTestCaseID() + ": " + sTestCase + ": " + paramObject.getsDescription());

                    BookFlight makeBooking = new BookFlight();
                    FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

                    SaveReference savereference = new SaveReference();
                    savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

                    if (bResult == true) {
                        System.out.println("Test is successful");
                        System.out.println();
                        ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

                    } else {
                        throw new Exception("Test Case Failed, please check logs");
                    }

                } else if (i == 3) {
                    //if the test pass cancell all the ref
                    for (int k = 0; k < flightD.getOriginalFlightD().size(); k++) {
                        cancelMode.CancellationModule(paramObject, flightD.getOriginalFlightD().get(k), paramObject.getsRegion());
                    }

                }
            }


        } catch (Exception e) {

            //if the test fails cancell all the ref
            FlightDetail flightD = new FlightDetail();
            CancellationModule cancelMode = new CancellationModule();

            for (int i = 0; i < flightD.getOriginalFlightD().size(); i++) {
                cancelMode.CancellationModule(paramObject, flightD.getOriginalFlightD().get(i), paramObject.getsRegion());
            }
            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);

        }

    }

    @Test(enabled = true)
    public void testThirtyThree(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            FlightDetail test = makeBooking.getVoucherDetail();
            String voucher = test.getVoucherCode();
            System.out.println("voucher " + voucher);

            TccModule tccModule = new TccModule(makeBooking, paramObject);
            tccModule.tccModule(paramObject, confirmDetail, voucher);

            Invoices invoices = new Invoices();
            invoices.takePayment();
            invoices.changeStatusToPaid();

            Customer customer = new Customer();
            String status = customer.getStatus();

            AssertionUtil asserts = new AssertionUtil(paramObject);
            asserts.assertStringEquals(status, "Paid");

            System.out.println("Validating text 'Ticketed'");
            customer.validateTicketed();

            System.out.println("Validating VoidTickets button...");
            invoices.validateVoidTicketsButton();

            PassengersAndFlights flight = new PassengersAndFlights();
            flight.refreshItinerary();
            flight.cancelBooking();
            flight.supplierInteractions();

            System.out.println("Validating booking ticket...");
            NotificationModule notify = new NotificationModule(new VerifyNotifications(paramObject));
            notify.verifyBookingTicketEmail(confirmDetail, makeBooking, paramObject.getsRegion());

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }
    }

    @Test(enabled = true)
    public void testThirtyFour(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());


            FlightDetail flightDetail = new FlightDetail();

            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);
            SearchModule searchmodule = new SearchModule();
            List<FlightDetail> searchDetail = searchmodule.SearchModule(paramObject, homeDetail);
            ResultsModule resultsmodule = new ResultsModule();
            List<FlightDetail> flightDetails = resultsmodule.ResultsModule(paramObject, searchDetail);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testThirtyFive(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        String fname = null, lname = null, email = null, phoneNo = null, cellNo = null, strEmail = null;

        try {
            VoucherUtil voucherUtil = new VoucherUtil(paramObject);
            String sVoucherCode = voucherUtil.createVoucher(paramObject.getsVoucher(), paramObject.getsRegion());

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());
            FlightDetail flightD = new FlightDetail();
            BookFlight makeBooking = new BookFlight();
            //-------------------------------------------------------------------------
            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);
            SearchModule searchmodule = new SearchModule();
            List<FlightDetail> searchDetails = searchmodule.SearchModule(paramObject, homeDetail);
            ResultsModule resultsmodule = new ResultsModule();
            List<FlightDetail> flightDetails = resultsmodule.ResultsModule(paramObject, searchDetails);
            //set flight details

            ReviewModule reviewmodule = new ReviewModule();
            FlightDetail reviewDetail = reviewmodule.ReviewModule(paramObject, searchDetails, flightDetails);
            PassengerModule passengersmodule = new PassengerModule();
            flightD.setFlgTCC(true);
            List<FlightDetail> passengersDetail = passengersmodule.PassengerModule(paramObject, searchDetails, flightDetails);
            //set passenger details
            //strEmail = passengersDetail.getEmailAddress();
            FlightDetail contactDetails = passengersDetail.get(0);
            FlightDetail contactDetails2 = passengersDetail.get(1);
            System.out.println("**************" + contactDetails.getLstAdultLname().size());
            fname = contactDetails.getLstAdultFname().get(0);
            lname = contactDetails.getLstAdultLname().get(0);
            email = contactDetails.getEmailAddress();

            ProductsModule productsmodule = new ProductsModule();
            List<FlightDetail> productsDetails = productsmodule.ProductsModule(paramObject, homeDetail, passengersDetail);

            //verifies breakdown and price on payment
            BookFlight bookFlight = new BookFlight();
            bookFlight.verifyPriceAndBreakdownOnPayment(paramObject, paramObject.getsProducts(), paramObject.getsAternativeProducts(), productsDetails);

            PaymentsModule paymentsmodule = new PaymentsModule(new NewPaymentsPage(paramObject));
            FlightDetail paymentsDetail = paymentsmodule.PaymentsModule(paramObject, sVoucherCode, homeDetail, passengersDetail);
            ConfirmModule confirmmodule = new ConfirmModule();
            FlightDetail confirmDetail = confirmmodule.ConfirmModule(paramObject, sVoucherCode, searchDetails, productsDetails, paymentsDetail, passengersDetail, reviewDetail, flightDetails);

            //-------------------------------------------------------------------------
            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            TccModule tccModule = new TccModule(makeBooking, paramObject);
            tccModule.tccModule(paramObject, confirmDetail, sVoucherCode);

            phoneNo = "27 21 4684380";
            cellNo = "27" + paramObject.getsCellNumber();

            Customer customer = new Customer();
            customer.verifyCustomer(fname, lname, phoneNo, cellNo, email);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }


    }

    @Test(enabled = true)
    public void testThirtySix(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            FlightDetail test = makeBooking.getVoucherDetail();
            String voucher = test.getVoucherCode();
            System.out.println("voucher " + voucher);

            TccModule tccModule = new TccModule(makeBooking, paramObject);
            tccModule.tccModule(paramObject, confirmDetail, voucher);

            Invoices invoices = new Invoices();

            if (paramObject.getsTestCase().equalsIgnoreCase("PartialRefund")) {
                System.out.println("Validating Partial Refund...");
                invoices.takePayment();
                System.out.println("Finished taking payment...");
                invoices.btnRefund_click();
                System.out.println("Finished clicking Refund button...");
                invoices.selectPaymentLine();
                System.out.println("Finished selecting payment line checkbox...");
                invoices.btnDeletePaymentLine();
                System.out.println("Finished deleting selected payment line...");
                invoices.refundsEntry(paramObject.getsRegion());

            } else {

                invoices.refundAndValidation(paramObject.getsTestCase(), paramObject.getsRegion());
                double refundAmount = invoices.getRefundedAmount();
                String transactionId = invoices.getTransactionId();
                MonitLogs monitLogs = new MonitLogs();
                String paymentMethod = null;

                if (paramObject.getsCardType().equalsIgnoreCase("Visa")) {
                    paymentMethod = "CC:VC";

                } else if (paramObject.getsCardType().equalsIgnoreCase("MasterCard")) {
                    paymentMethod = "CC:MC";

                } else {
                    paymentMethod = "CC:VC";

                }

                monitLogs.validateTS_RefundRQ(refundAmount, transactionId, paymentMethod);
                monitLogs.validateRefundRS(transactionId, refundAmount);
            }

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    /*@Test(enabled=true)
    public void testThirtySeven(String sBrowser, String sTestCaseID, String sTestCase, String sDescription, String sFlightType, String sTripType, String sDepartureCity, String sArrivalCity, String sDepartureDate, String sReturnDate, String sPreferredAirline, String sPreferredCabinClass, String sCalenderSearch, String sFlexiTicket, String sAdultTraveller, String sTeenTraveller, String sChildTraveller, String sInfantTraveller, String sSelectFlexiTicket, String sNewsletter, String sAccountType, String sSMS, String sUpgradeFlexi, String sCellNumber, String sSameAsFirstPassenger,String sProducts, String sAternativeProducts , String  sPaymentMethod, String sCardType, String sCardNumber, String sCVV, String sExpiryMonth, String sExpiryYear, String sBank, String sVATInvoice, String sVoucher,  String sMakeBooking, String sReprice, String sPaymentLink, String sActivityLine, String sRefreshItinerary, String sSignup, String sAddTravellers, String sAddCards, String sLogin, String sUsername, String sPassword, String sLang, String sRegion, String sFilterBy,  String sSortByDeparture, String sSortByReturn) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail= makeBooking.sharedBookFlight(paramObject);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            TccModule tccModule = new TccModule(makeBooking, paramObject);
            tccModule.tccModule(paramObject, confirmDetail);

            Activities activity = new Activities();
            activity.addActivity();

            Invoices invoices = new Invoices();
            String price = invoices.activityLinePaymentLink();

            VerifyNotifications verify = new VerifyNotifications();
            verify.verifyBookingConfirmation(confirmDetail);

            PaymentsLinkPage paymentlink = new PaymentsLinkPage();
            paymentlink.gotoPaymentsLink(sRegion, confirmDetail);
            paymentlink.getTotalPrice(price, sRegion);

            if(bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    } */

    @Test(enabled = true)
    public void testThirtyNine(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        String strName = "", strSurname = "";

        try {
            VoucherUtil voucherUtil = new VoucherUtil(paramObject);
            String sVoucherCode = voucherUtil.createVoucher(paramObject.getsVoucher(), paramObject.getsRegion());

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());
            FlightDetail flightD = new FlightDetail();
            BookFlight makeBooking = new BookFlight();

            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);
            SearchModule searchmodule = new SearchModule();
            List<FlightDetail> searchDetails = searchmodule.SearchModule(paramObject, homeDetail);
            ResultsModule resultsmodule = new ResultsModule();
            List<FlightDetail> flightDetails = resultsmodule.ResultsModule(paramObject, searchDetails);
            //set flight details

            ReviewModule reviewmodule = new ReviewModule();
            FlightDetail reviewDetail = reviewmodule.ReviewModule(paramObject, searchDetails, flightDetails);
            PassengerModule passengersmodule = new PassengerModule();
            List<FlightDetail> passengersDetail = passengersmodule.PassengerModule(paramObject, searchDetails, flightDetails);
            //set passenger details
            FlightDetail contactDetails = passengersDetail.get(0);
            strName = contactDetails.getLstAdultFname().get(0);
            strSurname = contactDetails.getLstAdultLname().get(0);

            ProductsModule productsmodule = new ProductsModule();
            List<FlightDetail> productsDetails = productsmodule.ProductsModule(paramObject, homeDetail, passengersDetail);

            //verifies breakdown and price on payment
            BookFlight bookFlight = new BookFlight();
            bookFlight.verifyPriceAndBreakdownOnPayment(paramObject, paramObject.getsProducts(), paramObject.getsAternativeProducts(), productsDetails);

            PaymentsModule paymentsmodule = new PaymentsModule(new NewPaymentsPage(paramObject));
            FlightDetail paymentsDetail = paymentsmodule.PaymentsModule(paramObject, sVoucherCode, homeDetail, passengersDetail);
            ConfirmModule confirmmodule = new ConfirmModule();
            FlightDetail confirmDetail = confirmmodule.ConfirmModule(paramObject, sVoucherCode, searchDetails, productsDetails, paymentsDetail, passengersDetail, reviewDetail, flightDetails);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            TccModule tccModule = new TccModule(makeBooking, paramObject);
            tccModule.tccModule(paramObject, confirmDetail, sVoucherCode);

            Invoices invoices = new Invoices();
            invoices.verifyInvoiceBlock(paramObject.getsTestCase(), confirmDetail, paramObject.getsProducts(), paramObject.getsAternativeProducts(), paramObject.getsPaymentMethod(), paramObject.getsRegion(),
                    paramObject.getsCellNumber(), strName, strSurname, productsDetails.get(0));

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testThirtyEight(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            FlightDetail test = makeBooking.getVoucherDetail();
            String voucher = test.getVoucherCode();
            System.out.println("voucher " + voucher);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            TccModule tccModule = new TccModule(makeBooking, paramObject);
            tccModule.tccModule(paramObject, confirmDetail, voucher);

            Activities activity = new Activities(paramObject);
            activity.validateActivities(makeBooking.getProductDetails(), makeBooking.getPassengerDetails());

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testFourthy(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            //local variable, dynamical object
            FlightDetail flightD = new FlightDetail();
            NewHomePage homp = new NewHomePage(paramObject);
            CancellationModule cancelMode = new CancellationModule();

            //set test case
            String sTestCase = "PassengerNameValidation";

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());
            FlightDetail flightDetail = new FlightDetail();
            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);
            SearchModule searchmodule = new SearchModule();
            List<FlightDetail> searchDetails = searchmodule.SearchModule(paramObject, homeDetail);
            ResultsModule resultsmodule = new ResultsModule();
            List<FlightDetail> flightDetails = resultsmodule.ResultsModule(paramObject, searchDetails);
            ReviewModule reviewmodule = new ReviewModule();
            FlightDetail reviewDtail = reviewmodule.ReviewModule(paramObject, searchDetails, flightDetails);
            PassengerModule passengersmodule = new PassengerModule();

            flightDetail.setFlgValidate(true);
            List<FlightDetail> passengersDetail = passengersmodule.PassengerModule(paramObject, searchDetails, flightDetails);


            if (bResult == true) {
                System.out.println("Test is successful");
                System.out.println();
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);

        }

    }

    @Test(enabled = true)
    public void testFourthyOne(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {
            VoucherUtil voucherUtil = new VoucherUtil(paramObject);
            String sVoucherCode = voucherUtil.createVoucher(paramObject.getsVoucher(), paramObject.getsRegion());

            //local variable, dynamical object
            FlightDetail flightD = new FlightDetail();
            NewHomePage homp = new NewHomePage(paramObject);
            CancellationModule cancelMode = new CancellationModule();

            //PassengerNameEraseSpecialChars
            //set test case
            String sTestCase = "PassengerNameEraseSpecialChars";


            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            String name = "", surname = "";

            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);
            SearchModule searchmodule = new SearchModule();
            List<FlightDetail> searchDetails = searchmodule.SearchModule(paramObject, homeDetail);
            ResultsModule resultsmodule = new ResultsModule();
            List<FlightDetail> flightDetails = resultsmodule.ResultsModule(paramObject, searchDetails);
            //set flight details

            ReviewModule reviewmodule = new ReviewModule();
            FlightDetail reviewDetail = reviewmodule.ReviewModule(paramObject, searchDetails, flightDetails);
            PassengerModule passengersmodule = new PassengerModule();

            //set flight d
            flightD.setFlgEraseChars(true);
            List<FlightDetail> passengersDetail = passengersmodule.PassengerModule(paramObject, searchDetails, flightDetails);

            //set passenger details
            FlightDetail contactDetails = passengersDetail.get(0);
            name = contactDetails.getLstAdultFname().get(0);
            surname = contactDetails.getLstAdultLname().get(0);

            ProductsModule productsmodule = new ProductsModule();
            List<FlightDetail> productsDetails = productsmodule.ProductsModule(paramObject, homeDetail, passengersDetail);

            //verifies breakdown and price on payment
            BookFlight bookFlight = new BookFlight();
            bookFlight.verifyPriceAndBreakdownOnPayment(paramObject, paramObject.getsProducts(), paramObject.getsAternativeProducts(), productsDetails);

            PaymentsModule paymentsmodule = new PaymentsModule(new NewPaymentsPage(paramObject));
            FlightDetail paymentsDetail = paymentsmodule.PaymentsModule(paramObject, sVoucherCode, homeDetail, passengersDetail);
            ConfirmModule confirmmodule = new ConfirmModule();
            FlightDetail confirmDetail = confirmmodule.ConfirmModule(paramObject, sVoucherCode, searchDetails, productsDetails, paymentsDetail, passengersDetail, reviewDetail, flightDetails);


            NewConfirmPage confirm = new NewConfirmPage(paramObject);
            confirm.verifyTravellersDetails(paramObject.getsTestCase(), name, surname);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            if (bResult == true) {
                System.out.println("Test is successful");
                System.out.println();
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);

        }

    }

    @Test(enabled = true)
    public void testFourthyTwo(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {
            VoucherUtil voucherUtil = new VoucherUtil(paramObject);
            String sVoucherCode = voucherUtil.createVoucher(paramObject.getsVoucher(), paramObject.getsRegion());
            //set test case

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsTestCase() + ": " + paramObject.getsDescription());

            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);
            SearchModule searchmodule = new SearchModule();
            List<FlightDetail> searchDetails = searchmodule.SearchModule(paramObject, homeDetail);
            ResultsModule resultsmodule = new ResultsModule();
            List<FlightDetail> flightDetails = resultsmodule.ResultsModule(paramObject, searchDetails);
            //set flight details

            ReviewModule reviewmodule = new ReviewModule();
            FlightDetail reviewDetail = reviewmodule.ReviewModule(paramObject, searchDetails, flightDetails);

            PassengerModule passengersmodule = new PassengerModule();
            List<FlightDetail> passengersDetail = passengersmodule.PassengerModule(paramObject, searchDetails, flightDetails);

            ProductsModule productsmodule = new ProductsModule();
            List<FlightDetail> productsDetails = productsmodule.ProductsModule(paramObject, homeDetail, passengersDetail);

            //verifies breakdown and price on payment
            BookFlight bookFlight = new BookFlight();
            bookFlight.verifyPriceAndBreakdownOnPayment(paramObject, paramObject.getsProducts(), paramObject.getsAternativeProducts(), productsDetails);

            PaymentsModule paymentsmodule = new PaymentsModule(new NewPaymentsPage(paramObject));
            FlightDetail paymentsDetail = paymentsmodule.PaymentsModule(paramObject, sVoucherCode, homeDetail, passengersDetail);

            ConfirmModule confirmmodule = new ConfirmModule();
            FlightDetail confirmDetail = confirmmodule.ConfirmModule(paramObject, sVoucherCode, searchDetails, productsDetails, paymentsDetail, passengersDetail, reviewDetail, flightDetails);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            if (bResult == true) {

                System.out.println("Test is successful");
                System.out.println();
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }


        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);

        }

    }

    @Test(enabled = true)
    public void testFourthyThree(CMS_Object paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            CMSLogin cmsLoging = new CMSLogin();
            cmsLoging.logintoCMS(paramObject.getsRegion());

            CMSPages cmsPages = new CMSPages();

            if (paramObject.getsTestCase().equals("CMSPages")) {
                cmsPages.moduleCreate(paramObject.getsClonePage());

            }

            if (bResult == true) {
                System.out.println("Test is successful");
                System.out.println();
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }


        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);

        }

    }

    @Test(enabled = true)
    public void testFourthyFour(CMS_Object paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            CMSLogin cmsLoging = new CMSLogin();
            cmsLoging.logintoCMS(paramObject.getsRegion());
            CMSAirlines objArilinePage = new CMSAirlines();

            if (paramObject.getsTestCase().equals("CMSAirlines")) {
                objArilinePage.moduleCreate(paramObject.getsClonePage());

            }

            if (bResult == true) {
                System.out.println("Test is successful");
                System.out.println();
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }


        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);

        }

    }

    @Test(enabled = true)
    public void testFourthyFive(CMS_Object paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            CMSLogin cmsLoging = new CMSLogin();
            cmsLoging.logintoCMS(paramObject.getsRegion());
            CMSContinent continent = new CMSContinent();

            if (paramObject.getsTestCase().equals("CMSContinent")) {
                continent.moduleCreate(paramObject.getsClonePage());

            }

            if (bResult == true) {
                System.out.println("Test is successful");
                System.out.println();
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }


        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);

        }

    }

    @Test(enabled = true)
    public void testFourthySix(CMS_Object paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            CMSLogin cmsLoging = new CMSLogin();
            cmsLoging.logintoCMS(paramObject.getsRegion());
            CMSCity cmsCity = new CMSCity();

            if (paramObject.getsTestCase().equals("CMSCity")) {
                cmsCity.moduleCreate(paramObject.getsClonePage());

            }

            if (bResult == true) {
                System.out.println("Test is successful");
                System.out.println();
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }


        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);

        }

    }

    @Test(enabled = true)
    public void testFortySeven(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            FlightDetail flightDetail = new FlightDetail();

            AccountHomeModule homemodule = new AccountHomeModule();
            List<FlightDetail> homeDetail = homemodule.AccountHomeModule(paramObject);

            SignupPage signup = new SignupPage(paramObject);
            signup.signOut();

            LoginPage login = new LoginPage(paramObject);
            login.loginIn(homeDetail);
            login.validateLoginMessage(homeDetail);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testFourthyEight(CMS_Object paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            CMSLogin cmsLoging = new CMSLogin();
            cmsLoging.logintoCMS(paramObject.getsRegion());
            CMSCountryPages cmsCountry = new CMSCountryPages();

            if (paramObject.getsTestCase().equals("CMSCountry")) {
                cmsCountry.moduleCreate(paramObject.getsClonePage());
            } else if (paramObject.getsTestCase().equals("CMSEditCountry")) {
                System.out.println("------------------" + paramObject.getsTestCase());
                cmsCountry.moduleEdit(paramObject.getsClonePage());
            }

            if (bResult == true) {
                System.out.println("Test is successful");
                System.out.println();
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);

        }

    }

    @Test(enabled = true)
    public void testFortyNine(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            FlightDetail flightDetail = new FlightDetail();

            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);
            SearchModule searchmodule = new SearchModule();
            List<FlightDetail> searchDetail = searchmodule.SearchModule(paramObject, homeDetail);
            ResultsModule resultsmodule = new ResultsModule();
            List<FlightDetail> flightDetails = resultsmodule.ResultsModule(paramObject, searchDetail);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testFiftyOne(String sID, String sSpreadsheet, String sCashOverRide, String sSource1, String sSource2) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Rules import started");

            DownloadExcelSheets download = new DownloadExcelSheets();
            download.download(sSpreadsheet, sSource1, sSource2);
            UploadExcelSheets upload = new UploadExcelSheets();
            String env = upload.upload(sSpreadsheet);
            upload.commitChanges(sSpreadsheet, env);

            if (bResult == true) {
                System.out.println("Import is successful");
                System.out.println();
                ExcelReader.setCellData("Passed", sID, GLOBAL_CONSTANTS.Col_Rules_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Import Failed, please check logs");

            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", sID, GLOBAL_CONSTANTS.Col_Rules_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            screenshot.takeScreenshot(sID, sSpreadsheet);
            Log.error(e.getMessage());
            throw (e);

        }

    }

    @Test(enabled = true)
    public void testOneHundredAndEleven(String sID, String sSpreadsheet, String sCashOverRide, String sSource1, String sSource2) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Payment Rules import started");

            UploadExcelSheets upload = new UploadExcelSheets();
            upload.uploadPaymentsExcel(sCashOverRide);

            if (bResult == true) {
                System.out.println("Import is successful");
                System.out.println();
                ExcelReader.setCellData("Passed", sID, GLOBAL_CONSTANTS.Col_Rules_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Import Failed, please check logs");

            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", sID, GLOBAL_CONSTANTS.Col_Rules_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            screenshot.takeScreenshot(sID, sSpreadsheet);
            Log.error(e.getMessage());
            throw (e);

        }

    }

    @Test(enabled = true)
    public void testFiftyTwo(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");

            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());
            FlightDetail flightDetail = new FlightDetail();
            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);
            SearchModule searchmodule = new SearchModule();
            List<FlightDetail> searchDetails = searchmodule.SearchModule(paramObject, homeDetail);
            ResultsModule resultsmodule = new ResultsModule();

            List<FlightDetail> flightDetails = resultsmodule.ResultsModule(paramObject, searchDetails);
            float price = flightDetails.get(0).getTotalPrice();

            ReviewModule reviewmodule = new ReviewModule();
            FlightDetail reviewDetail = reviewmodule.ReviewModule(paramObject, searchDetails, flightDetails);
            PassengerModule passengersmodule = new PassengerModule();

            //new Passangers page
            NewPassengersPage newPassenger = new NewPassengersPage(paramObject);
            System.out.println("Verifying Baggage is selected...");

            float bagPrice = newPassenger.verifyBaggage(paramObject.getsTripType(), paramObject.getsAdultTraveller(), paramObject.getsChildTraveller(),
                    price, paramObject.getsPreferredAirline(), paramObject.getsRegion(), paramObject.getsAddBaggage());

            System.out.println("Bag price = " + bagPrice);

            if (paramObject.getsMakeBooking().equalsIgnoreCase("Yes")) {

                List<FlightDetail> passengersDetail = passengersmodule.PassengerModule(paramObject, searchDetails, flightDetails);

                ProductsModule productsmodule = new ProductsModule();
                List<FlightDetail> productsDetail = productsmodule.ProductsModule(paramObject, homeDetail, passengersDetail);

                //verifies breakdown and price on payment
                BookFlight bookFlight = new BookFlight();
                bookFlight.verifyPriceAndBreakdownOnPayment(paramObject, paramObject.getsProducts(), paramObject.getsAternativeProducts(), productsDetail);

                PaymentsModule paymentsmodule = new PaymentsModule(new NewPaymentsPage(paramObject));
                FlightDetail paymentsDetail = paymentsmodule.PaymentsModule(paramObject, paramObject.getsVoucher(), homeDetail, passengersDetail);
                ConfirmModule confirmmodule = new ConfirmModule();
                FlightDetail confirmDetail = confirmmodule.ConfirmModule(paramObject, paramObject.getsVoucher(), searchDetails, productsDetail, paymentsDetail, passengersDetail, reviewDetail, flightDetails);

                SaveReference savereference = new SaveReference();
                savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

                TCCLogin login = new TCCLogin(paramObject);
                login.logintoTCC(paramObject.getsRegion());

                //Search by REF
                login.searchFlight2(confirmDetail);

                Customer customer = new Customer();
                customer.markasTest();
                customer.skipAutoTicket();

                Invoices invoices = new Invoices();
                invoices.extractBaggageLine(bagPrice, paramObject.getsPreferredAirline());

            }

            if (bResult == true) {
                System.out.println("Test is successful");
                System.out.println();
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);

        }

    }

    @Test(enabled = true)
    public void testFiftyThree(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());
            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            if (paramObject.getsTestCase().equalsIgnoreCase("ValidatePaymentMethods")) {
                String email = makeBooking.getPassengerDetails().get(0).getEmailAddress();
                ExcelReader.setCellData(email, paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Username, GLOBAL_CONSTANTS.Col_TestCaseID);

            }

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testFiftyFour(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            FlightDetail flightDetail = new FlightDetail();

            AccountHomeModule homemodule = new AccountHomeModule();
            List<FlightDetail> homeDetail = homemodule.AccountHomeModule(paramObject);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testFiftyFive(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            FlightDetail flightDetail = new FlightDetail();

            AccountHomeModule homeaccmodule = new AccountHomeModule();
            List<FlightDetail> homeaccDetail = homeaccmodule.AccountHomeModule(paramObject);

            ProfileModule profileModule = new ProfileModule(paramObject);

            BookingsPage book = new BookingsPage(paramObject);
            book.bookFlight();

            NewHomePage home = new NewHomePage(paramObject);
            home.waitforLoggedinState(paramObject.getsRegion(), homeaccDetail);

            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);
            SearchModule searchmodule = new SearchModule();
            List<FlightDetail> searchDetails = searchmodule.SearchModule(paramObject, homeDetail);
            ResultsModule resultsmodule = new ResultsModule();
            List<FlightDetail> flightDetails = resultsmodule.ResultsModule(paramObject, searchDetails);
            //set flight details

            ReviewModule reviewmodule = null;
            FlightDetail reviewDetail = null;

            if (flightDetails != null) {
                System.out.println("FlightDetails is SET: " + flightDetails);
                reviewmodule = new ReviewModule();
                reviewDetail = reviewmodule.ReviewModule(paramObject, searchDetails, flightDetails);

            } else {
                System.out.println("FlightDetails is NULL: " + flightDetails);
                reviewmodule = new ReviewModule(new NewReviewPage(paramObject), paramObject);
                flightDetails = reviewmodule.getSearchFlightDetails(searchDetails);

            }

            PassengerModule passengersmodule = new PassengerModule();
            List<FlightDetail> passengersDetail = passengersmodule.PassengerModule(paramObject, searchDetails, flightDetails);

            ProductsModule productsmodule = new ProductsModule();
            List<FlightDetail> productsDetails = productsmodule.ProductsModule(paramObject, homeDetail, passengersDetail);

            //verifies breakdown and price on payment
            BookFlight bookFlight = new BookFlight();
            bookFlight.verifyPriceAndBreakdownOnPayment(paramObject, paramObject.getsProducts(), paramObject.getsAternativeProducts(), productsDetails);

            NewPaymentsPage payments = new NewPaymentsPage(paramObject);
            boolean cc = payments.availableCCPayment(paramObject.getsRegion());

            if (cc) {
                payments.selectPaymentMethod(paramObject.getsPaymentMethod(), paramObject.getsRegion());

            } else {
                payments.selectPaymentMethod("3DCC", paramObject.getsRegion());

            }

            payments.savedCardDetails();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testFiftySix(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            FlightDetail flightDetail = new FlightDetail();

            TCCLogin login = new TCCLogin(paramObject);
            login.logintoTCC(paramObject.getsRegion());

            Vouchers voucher = new Vouchers();
            String vouchercode = voucher.createVoucher();

            TCCLogout logout = new TCCLogout();
            logout.logoutofTCC();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testFiftySeven(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            TccModule tccModule = new TccModule(makeBooking, paramObject);
            tccModule.emailNotifications(paramObject, confirmDetail, paramObject.getsRegion(), paramObject.getsEmailNotification());

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testFiftyEight(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            FlightDetail flightDetail = new FlightDetail();

            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);
            SearchModule searchmodule = new SearchModule();
            List<FlightDetail> searchDetails = searchmodule.SearchModule(paramObject, homeDetail);
            ResultsModule resultsmodule = new ResultsModule();
            List<FlightDetail> flightDetails = resultsmodule.ResultsModule(paramObject, searchDetails);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    /*@Test(enabled=true)
    public void testFiftyNine(String sBrowser, String sTestCaseID, String sTestCase, String sDescription, String sFlightType, String sTripType, String sDepartureCity, String sArrivalCity, String sDepartureDate, String sReturnDate, String sPreferredAirline, String sPreferredCabinClass, String sCalenderSearch, String sFlexiTicket, String sAdultTraveller, String sTeenTraveller, String sChildTraveller, String sInfantTraveller, String sSelectFlexiTicket, String sNewsletter, String sAccountType, String sSMS, String sUpgradeFlexi, String sCellNumber, String sSameAsFirstPassenger,String sProducts, String sAternativeProducts , String  sPaymentMethod, String sCardType, String sCardNumber, String sCVV, String sExpiryMonth, String sExpiryYear, String sBank, String sVATInvoice, String sVoucher,  String sMakeBooking, String sReprice, String sPaymentLink, String sActivityLine, String sRefreshItinerary, String sSignup, String sAddTravellers, String sAddCards, String sLogin, String sUsername, String sPassword, String sLang, String sRegion, String sFilterBy,  String sSortByDeparture, String sSortByReturn) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail= makeBooking.sharedBookFlight(paramObject);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            TccModule tccModule = new TccModule(makeBooking);
            tccModule.tccModule(paramObject, confirmDetail);

            Invoices invoices = new Invoices();
            String price = invoices.sendPaymentLink();
            System.out.println("---------------------Price is "+price);

            TCCLogout logout = new TCCLogout();
            logout.logoutofTCC();

            VerifyNotifications verify = new VerifyNotifications();
            verify.verifyBookingConfirmation(confirmDetail);

            PaymentsLinkPage paymentlink = new PaymentsLinkPage();
            paymentlink.gotoPaymentsLink(sRegion, confirmDetail);


            String strChangePaymentM = "CC";
            List<FlightDetail> lstFlightD = new ArrayList<FlightDetail>();

            NewProductsPage extras = new NewProductsPage();
            NewPassengersPage passenger = new NewPassengersPage();
            NewPaymentsPage paymentsPage = new NewPaymentsPage();
            NewConfirmPage confirmPage = new NewConfirmPage();

            //products
            if(paramObject.getsRegion("ZA") && sFlightType.equalsIgnoreCase("Inter")) {
                UpdateApisLink updatelink = new UpdateApisLink();
                List<String> listCrucialTraveller  = updatelink.updateTraveller();
                //passenger.enterAdultPassportForNewLink(sRegion, Integer.parseInt(sAdultTraveller), makeBooking.getflightDetails());
                //extras.continueButton();
            }else{
                //other markets no need to fill in info on Travellers page
                //extras.continueButton();
            }
            paymentlink.getTotalPrice(price, sRegion);

            //verify products chosen
            FlightDetail checkProd = makeBooking.getProductDetails().get(0);

            extras.verifyProductChosen(sProducts,checkProd);
            extras.continueButton();

            if(paramObject.getsRegion("AE")) {
                //clicking credit card
                paymentsPage.selectPaymentMethod(strChangePaymentM, sRegion);
                paymentsPage.selectCardType(sFlightType, strChangePaymentM, sCardType, "Emmanuel", "4000000000000002", sCVV, sExpiryMonth, sExpiryYear, sRegion);

            } else if(paramObject.getsRegion("ZA")) {
                //clicking credit card
                paymentsPage.selectPaymentMethod(strChangePaymentM, sRegion);
                paymentsPage.selectCardType(sFlightType, strChangePaymentM, sCardType, "Emmanuel", sCardNumber, sCVV, sExpiryMonth, sExpiryYear, sRegion);

            } else if(paramObject.getsRegion("NG")) {
                //clicking credit card
                paymentsPage.selectPaymentMethod("CashPayment", sRegion);
                paymentsPage.selectBank("FirstBank");
            }

            float totPrice = paymentsPage.getTotalPrice();
            //clicking continue
            paymentsPage.reserveButton(true, sRegion, sMakeBooking);


            //confirm details
            String newTicket = confirmPage.getTicketReference();
            System.out.println(newTicket + " = " + confirmDetail.getReference());

            //log in again to TCC to cofirm that booking payment was changed
            TCCLogin login = new TCCLogin();
            login.logintoTCC(paramObject.getsRegion());
            login.searchFlight2(confirmDetail);
            invoices.validateExtraPaymentMethod();
            //add total price with R49 as VISA was selected as new payment method
            invoices.validateInvoiceTotal(Float.toString(totPrice));

            if(bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    } */

    @Test(enabled = true)
    public void testSixty(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            FlightDetail test = makeBooking.getVoucherDetail();
            String voucher = test.getVoucherCode();
            System.out.println("voucher " + voucher);

            TccModule tccModule = new TccModule(makeBooking, paramObject);
            tccModule.tccModule(paramObject, confirmDetail, voucher);

            Documents documents = new Documents();
            documents.performUpload();
            String[] strFileToDel = {"TccTest2.pdf", "TccTest1.pdf", "TccTest3.pdf", "TccTest4.pdf"};
            documents.deleteFile(strFileToDel);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }


    @Test(enabled = true)
    public void testSixtyOne(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {
            VoucherUtil voucherUtil = new VoucherUtil(paramObject);
            String sVoucherCode = voucherUtil.createVoucher(paramObject.getsVoucher(), paramObject.getsRegion());

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsTestCase() + ": " + paramObject.getsDescription());

            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);
            SearchModule searchmodule = new SearchModule();
            List<FlightDetail> searchDetails = searchmodule.SearchModule(paramObject, homeDetail);
            ResultsModule resultsmodule = new ResultsModule();
            List<FlightDetail> flightDetails = resultsmodule.ResultsModule(paramObject, searchDetails);
            //set flight details

            ReviewModule reviewmodule = new ReviewModule();
            FlightDetail reviewDetail = reviewmodule.ReviewModule(paramObject, searchDetails, flightDetails);

            PassengerModule passengersmodule = new PassengerModule();
            List<FlightDetail> passengersDetail = passengersmodule.PassengerModule(paramObject, searchDetails, flightDetails);

            ProductsModule productsmodule = new ProductsModule();
            List<FlightDetail> productsDetails = productsmodule.ProductsModule(paramObject, homeDetail, passengersDetail);

            //check for error popups for length
            NewPassengersPage passengersPage = new NewPassengersPage(paramObject);
            boolean flgLengthErr = passengersPage.availableLengthErr();
            passengersPage.handleLengthErr(flgLengthErr);

            if (bResult == true) {

                System.out.println("Test is successful");
                System.out.println();
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);

        }

    }

    @Test(enabled = true)
    public void testSixtyThree(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsTestCase() + ": " + paramObject.getsDescription());

            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testSixtyFour(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {
            //set test case
            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsTestCase() + ": " + paramObject.getsDescription());

            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);

            SearchModule searchmodule = new SearchModule();
            List<FlightDetail> searchDetails = searchmodule.SearchModule(paramObject, homeDetail);

            ResultsModule resultsmodule = new ResultsModule();
            List<FlightDetail> flightDetails = resultsmodule.ResultsModule(paramObject, searchDetails);

            PassengerModule passengersmodule = new PassengerModule();
            passengersmodule.PassengerModule(paramObject, searchDetails, flightDetails);

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }
    }

    @Test(enabled = true)
    public void testSixtyFive(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            FlightDetail flightDetail = new FlightDetail();

            AccountHomeModule homemodule = new AccountHomeModule();
            List<FlightDetail> homeDetail = homemodule.AccountHomeModule(paramObject);

            TravellersModule travellersModule = new TravellersModule(paramObject, homeDetail);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testSixtySix(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            FlightDetail test = makeBooking.getVoucherDetail();
            String voucher = test.getVoucherCode();
            System.out.println("voucher " + voucher);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            TccModule tccModule = new TccModule(makeBooking, paramObject);
            tccModule.tccModule(paramObject, confirmDetail, voucher);
            Customer customer = new Customer();

            //Validate customer details with the confirmation page
            String fname = makeBooking.getPassengerDetails().get(0).getLstAdultFname().get(0);
            String lname = makeBooking.getPassengerDetails().get(0).getLstAdultLname().get(0);
            String email = makeBooking.getPassengerDetails().get(0).getEmailAddress();
            String phoneNo = "27 21 4684380";
            String cellNo = "27" + paramObject.getsCellNumber();
            customer.verifyCustomer(fname, lname, phoneNo, cellNo, email);

            //Validate Passenger & Flights details  with the confirmation page
            PassengersAndFlights passengersAndFlights = new PassengersAndFlights();
            List<FlightDetail> passangerDet = makeBooking.getPassengerDetails();
            FlightDetail flightDetailReview = makeBooking.getflightDetails().get(0);

            //validate passangers and flights details
            //List<FlightDetail> searchDetailsMultiCity = makeBooking.getSearchDetails();
            FlightDetail searchDetailsMultiCity = makeBooking.getConfirmDetail();
            passengersAndFlights.validatePassangersAndFlightDetails(passangerDet, flightDetailReview, searchDetailsMultiCity,
                    paramObject.getsTripType(), paramObject.getsAdultTraveller(), paramObject.getsTeenTraveller(), paramObject.getsChildTraveller(), paramObject.getsInfantTraveller());

            //Validate Invoice details with the confirmation page
            Invoices invoices = new Invoices();
            invoices.verifyInvoiceBlock(paramObject.getsTestCase(), confirmDetail, paramObject.getsProducts(), paramObject.getsAternativeProducts(),
                    paramObject.getsPaymentMethod(), paramObject.getsRegion(), paramObject.getsCellNumber(), fname, lname, makeBooking.getProductDetails().get(0));
            WaitFor wait = new WaitFor();

            if (paramObject.getsTestCase().equalsIgnoreCase("BookingFlow1") || paramObject.getsTestCase().equalsIgnoreCase("BookingFlow3") ||
                    paramObject.getsTestCase().equals("BookingFlow4") || paramObject.getsTestCase().equalsIgnoreCase("BookingFlow5") ||
                    paramObject.getsTestCase().equals("BookingFlow6") || paramObject.getsTestCase().equalsIgnoreCase("BookingFlow8")) {
                ///User able to click Refresh and expand list make sure its success
                System.out.printf("%n%s%n", "<<REFRESHING PNR>>");
                passengersAndFlights.refreshPNR();

                //User able to click Update SSR and expand list make sure its success
                System.out.printf("%n%s%n", "<<Updating SSR>>");
                wait.tccIsLoading();
                passengersAndFlights.updateSSR();

                //User able to click Reprice and expand list make sure its success
                System.out.printf("%n%s%n", "<<REPRICING>>");
                wait.tccIsLoading();
                passengersAndFlights.reprice();

            } else if (paramObject.getsTestCase().equals("BookingFlow2") || paramObject.getsTestCase().equalsIgnoreCase("BookingFlow7")) {
                //PNR 1 User able to click Refresh and expand list make sure its success
                System.out.printf("%n%s%n", "<<REFRESHING ITINERARY 1>>");
                passengersAndFlights.refreshPNR();

                //PNR 1 User able to click Update SSR and expand list make sure its success
                System.out.printf("%n%s%n", "<<Updating SSR 1>>");
                wait.tccIsLoading();
                passengersAndFlights.updateSSR();

                //PNR 1 User able to click Repriceand expand list make sure its success
                System.out.printf("%n%s%n", "<<REPRICING 1>>");
                wait.tccIsLoading();
                passengersAndFlights.reprice();

                //PNR 2 User able to click Refresh and expand list make sure its success
                System.out.printf("%n%s%n", "<<REFRESHING 2>>");
                wait.tccIsLoading();
                passengersAndFlights.refreshPNR2();

                //PNR 2 User able to click Update SSR and expand list make sure its success
                System.out.printf("%n%s%n", "<<Updating SSR 2>>");
                wait.tccIsLoading();
                passengersAndFlights.updateSSR2();

                //PNR 2 User able to click Repriceand expand list make sure its success
                System.out.printf("%n%s%n", "<<REPRICING 2>>");
                wait.tccIsLoading();
                passengersAndFlights.reprice2();

            }
            // Add Payment Line in the invoice and save
            System.out.printf("%n%s%n", "<<ADDING ACTIVITY LINE, PAYMENT LINE>>");
            wait.tccIsLoading();
            Activities activity = new Activities(paramObject);
            activity.addActivity();
            String price = invoices.activityLinePaymentLink();

            //Delete the added payment line and save
            System.out.printf("%n%s%n", "<<DELETING PAYMENT LINE>>");
            wait.tccIsLoading();
            invoices.deleteCreatedPaymentLine();

            //Click on View invoice
            System.out.printf("%n%s%n", "<<VIEW INVOICES>>");
            wait.tccIsLoading();
            invoices.viewInvoiceAndValidate();

            //Validate confirmation queued/sent
            System.out.printf("%n%s%n", "<<VALIDATE CONFIRMATION SENT>>");
            wait.tccIsLoading();
            invoices.sendPaymentLink();

            VerifyNotifications verify = new VerifyNotifications(paramObject);
            verify.verifyBookingConfirmation(confirmDetail);
            verify.verifyPaymentLink(paramObject.getsRegion(), confirmDetail);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }
    }

    @Test(enabled = true)
    public void testSixtySeven(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();

            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);
            List<FlightDetail> passangerDetails = makeBooking.getPassengerDetails();

            FlightDetail test = makeBooking.getVoucherDetail();
            String voucher = test.getVoucherCode();
            System.out.println("voucher " + voucher);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            TccModule tccModule = new TccModule(makeBooking, paramObject);
            tccModule.tccModule(paramObject, confirmDetail, voucher);

            PassengersAndFlights passengersAndFlights = new PassengersAndFlights();
            passengersAndFlights.cancelBooking();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testSixtyEight(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            AccountHomeModule homemodule = new AccountHomeModule();
            List<FlightDetail> homeDetail = homemodule.AccountHomeModule(paramObject);

            SignupPage signup = new SignupPage(paramObject);
            //FlightDetail flightDetail = signup.signUp(sRegion);

            signup.signUpWithActiveEmail(homeDetail);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }
        System.out.println("Finished signing up...");
        Thread.sleep(20000);
    }

    @Test(enabled = true)
    public void testSixtyNine(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            TCCLogin login = new TCCLogin(paramObject);
            login.logintoTCC(paramObject.getsRegion());

            //Search by REF
            login.searchFlight2(confirmDetail);

            Customer customer = new Customer();
            customer.markasTest();
            customer.skipAutoTicket();

            login.validateSearchByReference(confirmDetail);

            //Search by Email
            login.searchByEmail(confirmDetail);
            login.validateSearchByEmail(confirmDetail);

            //Search by username
            login.searchByUsername(makeBooking.getPassengerDetails().get(0));
            login.validateSearchByUsername(makeBooking.getPassengerDetails().get(0));

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testSeventy(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsTestCase() + ": " + paramObject.getsDescription());

            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);
            SearchModule searchmodule = new SearchModule();
            List<FlightDetail> searchDetails = searchmodule.SearchModule(paramObject, homeDetail);
            ResultsModule resultsmodule = new ResultsModule();
            List<FlightDetail> flightDetails = resultsmodule.ResultsModule(paramObject, searchDetails);

            ReviewModule reviewmodule = new ReviewModule();
            reviewmodule.ReviewModule(paramObject, searchDetails, flightDetails);

            PassengerModule passengersmodule = new PassengerModule();
            passengersmodule.PassengerModule(paramObject, searchDetails, flightDetails);

            if (bResult == true) {

                System.out.println("Test is successful");
                System.out.println();
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }
    }

    @Test(enabled = true)
    public void testSeventyOne(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            TCCLogin login = new TCCLogin(paramObject);
            login.logintoTCC(paramObject.getsRegion());

            //Search by REF
            login.searchFlight2(confirmDetail);

            Customer customer = new Customer();
            customer.markasTest();
            customer.skipAutoTicket();

            login.validateSearchByReference(confirmDetail);

            QueuesPage queues = new QueuesPage();
            queues.queueFields();
            queues.addQueue();
            queues.validateQueues(confirmDetail);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }
    }

    @Test(enabled = true)
    public void testSeventyTwo(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            FlightDetail confirmDetail = new FlightDetail();

            TCCLogin login = new TCCLogin(paramObject);
            login.logintoTCC(paramObject.getsRegion());

            SelectMarket market = new SelectMarket();
            market.selectTravelBar();

            login.createBooking();

            Thread.sleep(3000);

            Customer customer = new Customer();
            customer.markasTest();
            customer.manualOverride();

            Thread.sleep(3000);
            customer.enterCustomerDetails();

            Activities activity = new Activities(paramObject);
            activity.addHotelsActivity();
            String tbRef = activity.retrieveRef();
            confirmDetail.setReference(tbRef);

            Invoices invoices = new Invoices();
            String price = invoices.activityLinePaymentLink();
            invoices.validateDescription();

            PaymentsLinkPage paymentlink = new PaymentsLinkPage(paramObject);
            paymentlink.gotoPaymentsLink(paramObject.getsRegion(), confirmDetail);

            paymentlink.uncheckProductsAndgetTotalPrice(price);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testSeventyThree(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            FlightDetail flightDetail = new FlightDetail();

            AccountHomeModule homemodule = new AccountHomeModule();
            List<FlightDetail> homeDetail = homemodule.AccountHomeModule(paramObject);


            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testSeventyFour(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started--------");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            AccountHomeModule homemodule = new AccountHomeModule();
            List<FlightDetail> homeDetail = homemodule.AccountHomeModule(paramObject);


            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testSeventyFive(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            AccountHomeModule homemodule = new AccountHomeModule();
            List<FlightDetail> homeDetail = homemodule.AccountHomeModule(paramObject);


            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testSeventySix(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {
            AssertionUtil assertionUtil = new AssertionUtil(paramObject);

            int count = 0;

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            String email = "autotravelstart+1503062063436@gmail.com";

            TCCLogin tccLogin = new TCCLogin(paramObject);
            tccLogin.logintoTCC(paramObject.getsRegion());

            TCCMyAccount tccMyAccount = new TCCMyAccount();
            tccMyAccount.btnMyAccountMenu_Click();
            tccMyAccount.peformSearch(email);
            tccMyAccount.resetPassword(email);

            FlightDetail userCredentials = new FlightDetail();

            LoginPage login = new LoginPage(paramObject);
            login.forgotPasswordEmail(email, paramObject.getsRegion());
            String password = "!P" + (new Date().getTime());
            userCredentials.setPassword(password);
            while (login.resetPassword(password) && count < 5) {
                System.out.println(count + ". Attempting to find relevant reset link on email...");
                login.forgotPasswordEmail(paramObject.getsUsername(), paramObject.getsRegion());
                count++;
            }
            if (count >= 5) {
                assertionUtil.assertFalseMethod(true, "Was unable to reset password, after multiple attempts to retrieve right reset link");
            }


            SignupPage signUpPage = new SignupPage(paramObject);
            signUpPage.signOut();

            userCredentials.setSignupEmail(email);
            login.performLogin(userCredentials);
            login.validateLogin("Luvo Mgulwa");

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }
    }

    @Test(enabled = true)
    public void testSeventySeven(TSParameterObject b2bObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(b2bObject.getsTestCaseID() + ": " + b2bObject.getsDescription());

            B2B_LoginModule login = new B2B_LoginModule();
            login.successfulLogin(b2bObject.getsUsername(), b2bObject.getsPassword(), b2bObject.getsRegion());

            if (bResult == true) {
                ExcelReader.setCellData("Passed", b2bObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", b2bObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(b2bObject.getsTestCaseID());
            screenshot.takeScreenshot(b2bObject.getsTestCaseID(), b2bObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }
    }

    @Test(enabled = true)
    public void testSeventyEight(TSParameterObject b2bParameters) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(b2bParameters.getsTestCaseID() + ": " + b2bParameters.getsDescription());

            B2B_LoginModule login = new B2B_LoginModule();
            login.successfulLogin(b2bParameters.getsUsername(), b2bParameters.getsPassword(), b2bParameters.getsRegion());

            B2B_SearchFlightModule searchFlight = new B2B_SearchFlightModule();
            FlightDetail searchDetail = searchFlight.searchFlight(b2bParameters);

            B2B_SearchResultsModule searchResults = new B2B_SearchResultsModule();
            searchResults.searchResults(b2bParameters, searchDetail);

            B2B_PaymentInformationModule paymentInformation = new B2B_PaymentInformationModule();
            paymentInformation.confirmBooingPayment(b2bParameters);

            B2B_ConfirmedBookingModule confirmedBooking = new B2B_ConfirmedBookingModule();
            FlightDetail confirmDetail = confirmedBooking.confirmedBooking(b2bParameters);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, b2bParameters.getsTestCaseID(), b2bParameters.getsTestCase(), b2bParameters.getsMakeBooking());

            TCCLogin tccLogin = new TCCLogin(b2bParameters);
            tccLogin.logintoTCC(b2bParameters.getsRegion());

            tccLogin.searchFlight2(confirmDetail);

            Customer customer = new Customer();
            customer.markasTest();
            customer.skipAutoTicket();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", b2bParameters.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", b2bParameters.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(b2bParameters.getsTestCaseID());
            screenshot.takeScreenshot(b2bParameters.getsTestCaseID(), b2bParameters.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }
    }

    @Test(enabled = true)
    public void testSeventyNine(TSParameterObject b2bParameters) throws InterruptedException, IOException, FindFailed, Exception {

        try {
            System.out.println("Test case started");
            System.out.println(b2bParameters.getsTestCaseID() + ": " + b2bParameters.getsDescription());

            B2B_LoginModule login = new B2B_LoginModule();
            login.successfulLogin(b2bParameters.getsUsername(), b2bParameters.getsPassword(), b2bParameters.getsRegion());

            B2B_SearchFlightModule searchFlight = new B2B_SearchFlightModule();
            FlightDetail searchDetail = searchFlight.searchFlight(b2bParameters);

            B2B_SearchResultsModule searchResults = new B2B_SearchResultsModule();
            searchResults.searchResults(b2bParameters, searchDetail);

            B2B_PaymentInformationModule paymentInformation = new B2B_PaymentInformationModule();
            paymentInformation.confirmBooingPayment(b2bParameters);

            B2B_ConfirmedBookingModule confirmedBooking = new B2B_ConfirmedBookingModule();
            FlightDetail confirmDetail = confirmedBooking.confirmedBooking(b2bParameters);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, b2bParameters.getsTestCaseID(), b2bParameters.getsTestCase(), b2bParameters.getsMakeBooking());

            B2B_DashboardModule dashboardModule = new B2B_DashboardModule();
            dashboardModule.validateBooking(confirmDetail);

            TCCLogin tccLogin = new TCCLogin(b2bParameters);
            tccLogin.logintoTCC(b2bParameters.getsRegion());

            tccLogin.searchFlight2(confirmDetail);

            Customer customer = new Customer();
            customer.markasTest();
            customer.skipAutoTicket();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", b2bParameters.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", b2bParameters.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(b2bParameters.getsTestCaseID());
            screenshot.takeScreenshot(b2bParameters.getsTestCaseID(), b2bParameters.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }
    }

    @Test(enabled = true)
    public void testEighty(TSParameterObject b2bParameters) throws InterruptedException, IOException, FindFailed, Exception {

        try {
            System.out.println("Test case started");
            System.out.println(b2bParameters.getsTestCaseID() + ": " + b2bParameters.getsDescription());

            B2B_LoginModule login = new B2B_LoginModule();
            login.successfulLogin(b2bParameters.getsUsername(), b2bParameters.getsPassword(), b2bParameters.getsRegion());

            B2B_SearchFlightModule searchFlight = new B2B_SearchFlightModule();
            FlightDetail searchDetail = searchFlight.searchFlight(b2bParameters);

            B2B_SearchResultsModule searchResults = new B2B_SearchResultsModule();
            searchResults.searchResults(b2bParameters, searchDetail);

            B2B_PaymentInformationModule paymentInformation = new B2B_PaymentInformationModule();
            paymentInformation.confirmBooingPayment(b2bParameters);

            B2B_ConfirmedBookingModule confirmedBooking = new B2B_ConfirmedBookingModule();
            FlightDetail confirmDetail = confirmedBooking.confirmedBooking(b2bParameters);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, b2bParameters.getsTestCaseID(), b2bParameters.getsTestCase(), b2bParameters.getsMakeBooking());

            TCCLogin tccLogin = new TCCLogin(b2bParameters);
            tccLogin.logintoTCC(b2bParameters.getsRegion());

            tccLogin.searchFlight2(confirmDetail);

            Customer customer = new Customer();
            customer.markasTest();
            customer.skipAutoTicket();

            customer.verifyCustomer(GLOBAL_CONSTANTS.B2B_FIRSTNAME, GLOBAL_CONSTANTS.B2B_SURNAME, GLOBAL_CONSTANTS.B2B_PHONE, GLOBAL_CONSTANTS.B2B_CELL, b2bParameters.getsUsername());

            PassengersAndFlights passengersAndFlights = new PassengersAndFlights();
            confirmedBooking.validatePassengerDetails(passengersAndFlights.getPassengerFullName(), searchDetail);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", b2bParameters.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", b2bParameters.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(b2bParameters.getsTestCaseID());
            screenshot.takeScreenshot(b2bParameters.getsTestCaseID(), b2bParameters.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }
    }

    @Test(enabled = true)
    public void testEightyOne(TSParameterObject b2bParameters) throws InterruptedException, IOException, FindFailed, Exception {

        try {
            System.out.println("Test case started");
            System.out.println(b2bParameters.getsTestCaseID() + ": " + b2bParameters.getsDescription());

            B2B_LoginModule login = new B2B_LoginModule();
            login.successfulLogin(b2bParameters.getsUsername(), b2bParameters.getsPassword(), b2bParameters.getsRegion());

            B2B_SearchFlightModule searchFlight = new B2B_SearchFlightModule();
            FlightDetail searchDetail = searchFlight.searchFlight(b2bParameters);

            B2B_SearchResultsModule searchResults = new B2B_SearchResultsModule();
            searchResults.searchResults(b2bParameters, searchDetail);

            B2B_PaymentInformationModule paymentInformation = new B2B_PaymentInformationModule();
            paymentInformation.confirmBooingPayment(b2bParameters);

            B2B_ConfirmedBookingModule confirmedBooking = new B2B_ConfirmedBookingModule();
            FlightDetail confirmDetail = confirmedBooking.confirmedBooking(b2bParameters);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, b2bParameters.getsTestCaseID(), b2bParameters.getsTestCase(), b2bParameters.getsMakeBooking());

            TCCLogin tccLogin = new TCCLogin(b2bParameters);
            tccLogin.logintoTCC(b2bParameters.getsRegion());

            tccLogin.searchFlight2(confirmDetail);

            Customer customer = new Customer();
            customer.markasTest();
            customer.skipAutoTicket();

            Invoices invoices = new Invoices();
            invoices.sendPaymentLink();

            VerifyNotifications verify = new VerifyNotifications(b2bParameters);
            verify.verifyPaymentLink(b2bParameters.getsRegion(), confirmDetail, b2bParameters.getsUsername(), GLOBAL_CONSTANTS.GMAIL_PASSWORD);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", b2bParameters.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", b2bParameters.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(b2bParameters.getsTestCaseID());
            screenshot.takeScreenshot(b2bParameters.getsTestCaseID(), b2bParameters.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }
    }

    @Test(enabled = true)
    public void testEightyTwo(TSParameterObject b2bParameters) throws InterruptedException, IOException, FindFailed, Exception {

        try {
            System.out.println("Test case started");
            System.out.println(b2bParameters.getsTestCaseID() + ": " + b2bParameters.getsDescription());

            B2B_LoginModule login = new B2B_LoginModule();
            login.successfulLogin(b2bParameters.getsUsername(), b2bParameters.getsPassword(), b2bParameters.getsRegion());

            B2B_SearchFlightModule searchFlight = new B2B_SearchFlightModule();
            FlightDetail searchDetail = searchFlight.searchFlight(b2bParameters);

            B2B_SearchResultsModule searchResults = new B2B_SearchResultsModule();
            searchResults.searchResults(b2bParameters, searchDetail);

            B2B_PaymentInformationModule paymentInformation = new B2B_PaymentInformationModule();
            paymentInformation.confirmBooingPayment(b2bParameters);

            B2B_ConfirmedBookingModule confirmedBooking = new B2B_ConfirmedBookingModule();
            FlightDetail confirmDetail = confirmedBooking.confirmedBooking(b2bParameters);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, b2bParameters.getsTestCaseID(), b2bParameters.getsTestCase(), b2bParameters.getsMakeBooking());

            TCCLogin tccLogin = new TCCLogin(b2bParameters);
            tccLogin.logintoTCC(b2bParameters.getsRegion());

            tccLogin.searchFlight2(confirmDetail);

            Customer customer = new Customer();
            customer.markasTest();
            customer.skipAutoTicket();

            Invoices invoices = new Invoices();
            String price = invoices.sendPaymentLink();

            if (b2bParameters.getsRegion().equals("EG")) {
                Double dPrice = Double.parseDouble(price);
                price = String.valueOf(Math.round(dPrice));
            }

            B2B_PaymentInformationModule payment = new B2B_PaymentInformationModule();
            payment.changeFOP(confirmDetail, b2bParameters, price);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", b2bParameters.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", b2bParameters.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(b2bParameters.getsTestCaseID());
            screenshot.takeScreenshot(b2bParameters.getsTestCaseID(), b2bParameters.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }
    }

    @Test(enabled = true)
    public void testEightyThree(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            TCCLogin login = new TCCLogin(paramObject);
            login.logintoTCC(paramObject.getsRegion());
            login.searchFlight2(confirmDetail);

            Customer customer = new Customer();
            customer.markasTest();
            customer.skipAutoTicket();

            PassengersAndFlights flight = new PassengersAndFlights();
            FlightDetail pnr = flight.getPNRdynamic();

            List<Object> initTicketFeatures = flight.getTicketFeatures();

            login.btnHome_click();
            login.searchByPNR(pnr);

            List<Object> postTicketFeatures = flight.getTicketFeatures();

            flight.validateDetails(initTicketFeatures, postTicketFeatures);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testEightyFour(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            TCCLogin login = new TCCLogin(paramObject);
            login.logintoTCC(paramObject.getsRegion());
            login.searchFlight2(confirmDetail);

            Customer customer = new Customer();
            customer.markasTest();
            customer.skipAutoTicket();

            PassengersAndFlights flight = new PassengersAndFlights();
            FlightDetail pnr = flight.getPNRdynamic();
            flight.changeFirstName();

            List<Object> initItineraryFlightDetails = flight.getPassengers();

            login.btnHome_click();
            login.searchByPNR(pnr);

            List<Object> postItineraryFlightDetails = flight.getPassengers();

            flight.validateDetails(initItineraryFlightDetails, postItineraryFlightDetails);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testEightyFive(TSParameterObject b2bParameters) throws InterruptedException, IOException, FindFailed, Exception {

        try {
            System.out.println("Test case started");
            System.out.println(b2bParameters.getsTestCaseID() + ": " + b2bParameters.getsDescription());

            B2B_LoginModule login = new B2B_LoginModule();
            login.successfulLogin(b2bParameters.getsUsername(), b2bParameters.getsPassword(), b2bParameters.getsRegion());

            B2B_SearchFlightModule searchFlight = new B2B_SearchFlightModule();
            FlightDetail searchDetail = searchFlight.searchFlight(b2bParameters);

            B2B_SearchResultsModule searchResults = new B2B_SearchResultsModule();
            searchResults.searchResults(b2bParameters, searchDetail);

            B2B_PaymentInformationModule paymentInformation = new B2B_PaymentInformationModule();
            paymentInformation.confirmBooingPayment(b2bParameters);

            B2B_ConfirmedBookingModule confirmedBooking = new B2B_ConfirmedBookingModule();
            FlightDetail confirmDetail = confirmedBooking.confirmedBooking(b2bParameters);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, b2bParameters.getsTestCaseID(), b2bParameters.getsTestCase(), b2bParameters.getsMakeBooking());

            TCCLogin tccLogin = new TCCLogin(b2bParameters);
            tccLogin.logintoTCC(b2bParameters.getsRegion());

            tccLogin.searchFlight2(confirmDetail);

            Customer customer = new Customer();
            customer.markasTest();
            customer.skipAutoTicket();

            PassengersAndFlights passengerAndFlight = new PassengersAndFlights();
            passengerAndFlight.cancelBooking();

            B2B_DashboardModule dashboardModule = new B2B_DashboardModule();
            dashboardModule.cancelledBooking(confirmDetail, b2bParameters.getsRegion());

            if (bResult == true) {
                ExcelReader.setCellData("Passed", b2bParameters.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", b2bParameters.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(b2bParameters.getsTestCaseID());
            screenshot.takeScreenshot(b2bParameters.getsTestCaseID(), b2bParameters.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }
    }

    @Test(enabled = true)
    public void testEightySix(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            VoucherUtil voucherUtil = new VoucherUtil(paramObject);
            String sVoucherCode = voucherUtil.createVoucher(paramObject.getsVoucher(), paramObject.getsRegion());

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);
            GetLinks brokenLinks = new GetLinks();
            brokenLinks.validatelinks();
            SearchModule searchmodule = new SearchModule();
            List<FlightDetail> searchDetails = searchmodule.SearchModule(paramObject, homeDetail);
            brokenLinks.validatelinks();
            ResultsModule resultsmodule = new ResultsModule();
            List<FlightDetail> flightDetails = resultsmodule.ResultsModule(paramObject, searchDetails);
            brokenLinks.validatelinks();
            ReviewModule reviewmodule = new ReviewModule();
            FlightDetail reviewDetail = reviewmodule.ReviewModule(paramObject, searchDetails, flightDetails);
            PassengerModule passengersmodule = new PassengerModule();
            List<FlightDetail> passengersDetail = passengersmodule.PassengerModule(paramObject, searchDetails, flightDetails);
            brokenLinks.validatelinks();
            ProductsModule productsmodule = new ProductsModule();
            List<FlightDetail> productsDetail = productsmodule.ProductsModule(paramObject, homeDetail, passengersDetail);
            brokenLinks.validatelinks();
            PaymentsModule paymentsmodule = new PaymentsModule(new NewPaymentsPage(paramObject));
            FlightDetail paymentsDetail = paymentsmodule.PaymentsModule(paramObject, sVoucherCode, homeDetail, passengersDetail);
            brokenLinks.validatelinks();
            ConfirmModule confirmmodule = new ConfirmModule();
            FlightDetail confirmDetail = confirmmodule.ConfirmModule(paramObject, sVoucherCode, searchDetails, productsDetail, paymentsDetail, passengersDetail, reviewDetail, flightDetails);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());
            brokenLinks.validatelinks();

            TCCLogin login = new TCCLogin(paramObject);
            login.logintoTCC(paramObject.getsRegion());

            brokenLinks.validatelinks();

            login.searchFlight2(confirmDetail);

            Customer customer = new Customer();
            customer.markasTest();
            customer.skipAutoTicket();

            brokenLinks.validatelinks();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testEightySeven(TSParameterObject paramObject) throws Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);
            SearchModule searchmodule = new SearchModule();
            List<FlightDetail> searchDetail = searchmodule.SearchModule(paramObject, homeDetail);

            NewResultsPage results = new NewResultsPage(paramObject);
            results.verifyCalenderSearch(paramObject.getsTripType(), paramObject.getsFlightType(), paramObject.getsDepartureDate(),
                    paramObject.getsReturnDate(), paramObject.getsCalenderSearch(), paramObject.getsLang());
            results.verifySearchResults(paramObject.getsTestCase(), paramObject.getsTestCaseID(), paramObject.getsRegion());
            results.verifyPreferredCabinClass(paramObject.getsTripType(), paramObject.getsPreferredCabinClass(), paramObject.getsFlightType());
            results.verifyPrefererredAirline(paramObject.getsTripType(), paramObject.getsPreferredAirline(), paramObject.getsFlightType(), paramObject.getsRegion());

            ResultsModule resultsModule = new ResultsModule(results);
            resultsModule.compareFlights(paramObject);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testEightyEight(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            AccountHomeModule homemodule = new AccountHomeModule(new NewHomePage(paramObject), new NewSearchPage(paramObject), new NewResultsPage(paramObject), paramObject);
            homemodule.AccountHomeModule(paramObject);
            homemodule.recentSearches();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testEightyNine(CMS_Object paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {
            CMSLogin cmsLoging = new CMSLogin();
            cmsLoging.logintoCMS(paramObject.getsRegion());
            CMSFlightRoutes cmsFlightRoutes = new CMSFlightRoutes(paramObject);

            if (paramObject.getsTestCase().equals("CMSFlightRoutes")) {
                cmsFlightRoutes.createPageFlightRoutese("Cape Town", "Johannesburg");
            }

            if (bResult == true) {
                System.out.println("Test is successful");
                System.out.println();
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);

        }

    }

    @Test(enabled = true)
    public void testNinety(CMS_Object paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {
            CMSLogin cmsLoging = new CMSLogin();
            cmsLoging.logintoCMS(paramObject.getsRegion());
            CMSProductsPage cmsProductsPage = new CMSProductsPage(paramObject);

            if (paramObject.getsTestCase().equals("CMSProductsPage")) {
                cmsProductsPage.createProductPage();
            }

            if (bResult == true) {
                System.out.println("Test is successful");
                System.out.println();
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);

        }
    }

    @Test(enabled = true)
    public void testNinetyOne(CMS_Object paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {
            CMSLogin cmsLoging = new CMSLogin();
            cmsLoging.logintoCMS(paramObject.getsRegion());
            CMSAirportPage cmsAirportPage = new CMSAirportPage(paramObject);

            if (paramObject.getsTestCase().equals("CMSAirportPage")) {
                cmsAirportPage.createAirportPage();
            }

            if (bResult == true) {
                System.out.println("Test is successful");
                System.out.println();
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);

        }
    }

    @Test(enabled = true)
    public void testNinetyTwo(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            VoucherUtil voucherUtil = new VoucherUtil(paramObject);
            String sVoucherCode = voucherUtil.createVoucher(paramObject.getsVoucher(), paramObject.getsRegion());

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            TccModule tccModule = new TccModule(makeBooking, paramObject);
            tccModule.markAsTestAndSkipAutoTicket(confirmDetail, paramObject.getsRegion());

            tccModule.amendments(confirmDetail);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }
    }

    public void testNinetyThree(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            VoucherUtil voucherUtil = new VoucherUtil(paramObject);
            String sVoucherCode = voucherUtil.createVoucher(paramObject.getsVoucher(), paramObject.getsRegion());

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            TccModule tccModule = new TccModule(makeBooking, paramObject);
            tccModule.markAsTestAndSkipAutoTicket(confirmDetail, paramObject.getsRegion());

            tccModule.validateRefund();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }
        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }
    }

    public void testNinetyFour(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            VoucherUtil voucherUtil = new VoucherUtil(paramObject);
            String sVoucherCode = voucherUtil.createVoucher(paramObject.getsVoucher(), paramObject.getsRegion());

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            TccModule tccModule = new TccModule(makeBooking, paramObject);
            tccModule.markAsTestAndSkipAutoTicket(confirmDetail, paramObject.getsRegion());

            tccModule.validateSendInvoice();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }
        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }
    }

    @Test(enabled = true)
    public void testNinetyFive(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            HomeModule homeModule = new HomeModule(new NewHomePage(paramObject), paramObject);
            homeModule.ContactForm();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }
        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testNinetySix(String sOrder, String sTestCaseID, String sTestCase, String sDescription) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Mark as Test started");

            MarkasTest mark = new MarkasTest();
            mark.markasTest(sOrder, sTestCaseID, sTestCase);

            if (bResult == true) {
                System.out.println("Test is successful");
                System.out.println();
                ExcelReader.setCellData("Passed", sTestCaseID, GLOBAL_CONSTANTS.Col_Rules_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Import Failed, please check logs");

            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", sTestCaseID, GLOBAL_CONSTANTS.Col_Rules_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            screenshot.takeScreenshot(sTestCaseID, sTestCase);
            Log.error(e.getMessage());
            throw (e);

        }

    }

    @Test
    public void testNinetySeven(HotelsParamObject hotelsParamObject) throws Exception {

        try {

            System.out.println("Test case started");
            System.out.println(hotelsParamObject.getTestCaseId() + ": " + hotelsParamObject.getDescription());

            HotelsSearchModule searchModule = new HotelsSearchModule(new SearchPage(), hotelsParamObject);
            searchModule.validHotelSearch();

            HotelsSearchResultsModule hotelsSearchResultsModule = new HotelsSearchResultsModule(new SearchResultsPage(), hotelsParamObject);
            hotelsSearchResultsModule.validateResultsFields();
            hotelsSearchResultsModule.filter();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", hotelsParamObject.getTestCaseId(), GLOBAL_CONSTANTS.Col_Hotels_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }
        } catch (Exception e) {

            ExcelReader.setCellData("Failed", hotelsParamObject.getTestCaseId(), GLOBAL_CONSTANTS.Col_Hotels_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(hotelsParamObject.getTestCaseId());
            screenshot.takeScreenshot(hotelsParamObject.getTestCaseId(), hotelsParamObject.getTestCase());
            Log.error(e.getMessage());
            throw (e);
        }
    }

    @Test
    public void testNinetyEight(HotelsParamObject hotelsParamObject) throws Exception {

        try {

            System.out.println("Test case started");
            System.out.println(hotelsParamObject.getTestCaseId() + ": " + hotelsParamObject.getDescription());

            HotelsSearchModule searchModule = new HotelsSearchModule(new SearchPage(), hotelsParamObject);
            searchModule.validHotelSearch();

            HotelsSearchResultsModule hotelsSearchResultsModule = new HotelsSearchResultsModule(new SearchResultsPage(), hotelsParamObject);
            hotelsSearchResultsModule.filter();
            hotelsSearchResultsModule.validateHotel();

            GuestModule guestModule = new GuestModule(new GuestDetailsPage(), hotelsParamObject);
            guestModule.validateGuestDetails();

            HotelsPaymentModule hotelsPaymentModule = new HotelsPaymentModule(new HotelsPaymentPage(), hotelsParamObject);
            hotelsPaymentModule.validatePaymentFields();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", hotelsParamObject.getTestCaseId(), GLOBAL_CONSTANTS.Col_Hotels_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }
        } catch (Exception e) {

            ExcelReader.setCellData("Failed", hotelsParamObject.getTestCaseId(), GLOBAL_CONSTANTS.Col_Hotels_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(hotelsParamObject.getTestCaseId());
            screenshot.takeScreenshot(hotelsParamObject.getTestCaseId(), hotelsParamObject.getTestCase());
            Log.error(e.getMessage());
            throw (e);
        }
    }

    @Test
    public void testNinetyNine(HotelsParamObject hotelsParamObject) throws Exception {

        try {

            System.out.println("Test case started");
            System.out.println(hotelsParamObject.getTestCaseId() + ": " + hotelsParamObject.getDescription());

            HotelsSearchModule searchModule = new HotelsSearchModule(new SearchPage(), hotelsParamObject);
            searchModule.validHotelSearch();

            HotelsSearchResultsModule hotelsSearchResultsModule = new HotelsSearchResultsModule(new SearchResultsPage(), hotelsParamObject);
            hotelsSearchResultsModule.validateCheapest();
            hotelsSearchResultsModule.validateBestRating();
            hotelsSearchResultsModule.validateRecommened();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", hotelsParamObject.getTestCaseId(), GLOBAL_CONSTANTS.Col_Hotels_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }
        } catch (Exception e) {

            ExcelReader.setCellData("Failed", hotelsParamObject.getTestCaseId(), GLOBAL_CONSTANTS.Col_Hotels_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(hotelsParamObject.getTestCaseId());
            screenshot.takeScreenshot(hotelsParamObject.getTestCaseId(), hotelsParamObject.getTestCase());
            Log.error(e.getMessage());
            throw (e);
        }
    }

    @Test
    public void testOneHundred(HotelsParamObject hotelsParamObject) throws Exception {

        try {

            System.out.println("Test case started");
            System.out.println(hotelsParamObject.getTestCaseId() + ": " + hotelsParamObject.getDescription());

            HotelsSearchModule searchModule = new HotelsSearchModule(new SearchPage(), hotelsParamObject);
            searchModule.validHotelSearch();

            HotelsSearchResultsModule hotelsSearchResultsModule = new HotelsSearchResultsModule(new SearchResultsPage(), hotelsParamObject);
            hotelsSearchResultsModule.filter();
            hotelsSearchResultsModule.validateHotel();

            GuestModule guestModule = new GuestModule(new GuestDetailsPage(), hotelsParamObject);
            guestModule.InvalidGuestEmailAndPhone();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", hotelsParamObject.getTestCaseId(), GLOBAL_CONSTANTS.Col_Hotels_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", hotelsParamObject.getTestCaseId(), GLOBAL_CONSTANTS.Col_Hotels_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(hotelsParamObject.getTestCaseId());
            screenshot.takeScreenshot(hotelsParamObject.getTestCaseId(), hotelsParamObject.getTestCase());
            Log.error(e.getMessage());
            throw (e);
        }
    }

    @Test
    public void testOneHundredAndOne(HotelsParamObject hotelsParamObject) throws Exception {

        try {

            System.out.println("Test case started");
            System.out.println(hotelsParamObject.getTestCaseId() + ": " + hotelsParamObject.getDescription());

            HotelsSearchModule searchModule = new HotelsSearchModule(new SearchPage(), hotelsParamObject);
            searchModule.validHotelSearch();

            HotelsSearchResultsModule hotelsSearchResultsModule = new HotelsSearchResultsModule(new SearchResultsPage(), hotelsParamObject);
            hotelsSearchResultsModule.filter();
            hotelsSearchResultsModule.validatePrice();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", hotelsParamObject.getTestCaseId(), GLOBAL_CONSTANTS.Col_Hotels_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", hotelsParamObject.getTestCaseId(), GLOBAL_CONSTANTS.Col_Hotels_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(hotelsParamObject.getTestCaseId());
            screenshot.takeScreenshot(hotelsParamObject.getTestCaseId(), hotelsParamObject.getTestCase());
            Log.error(e.getMessage());
            throw (e);
        }
    }

    @Test
    public void testOneHundredAndTwo(HotelsParamObject hotelsParamObject) throws Exception {

        try {

            System.out.println("Test case started");
            System.out.println(hotelsParamObject.getTestCaseId() + ": " + hotelsParamObject.getDescription());

            HotelsSearchModule searchModule = new HotelsSearchModule(new SearchPage(), hotelsParamObject);
            searchModule.validateLinks();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", hotelsParamObject.getTestCaseId(), GLOBAL_CONSTANTS.Col_Hotels_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }
        } catch (Exception e) {

            ExcelReader.setCellData("Failed", hotelsParamObject.getTestCaseId(), GLOBAL_CONSTANTS.Col_Hotels_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(hotelsParamObject.getTestCaseId());
            screenshot.takeScreenshot(hotelsParamObject.getTestCaseId(), hotelsParamObject.getTestCase());
            Log.error(e.getMessage());
            throw (e);
        }
    }

    @Test(enabled = true)
    public void testOneHundredAndThree(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);
            SearchModule searchmodule = new SearchModule();

            searchmodule.searchPanelErrorMessages(paramObject);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);

        }

    }

    @Test(enabled = true)
    public void testOneHundredAndFour(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            VoucherUtil voucherUtil = new VoucherUtil(paramObject);
            String sVoucherCode = voucherUtil.createVoucher(paramObject.getsVoucher(), paramObject.getsRegion());

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            NewResultsPage resultsPage = new NewResultsPage(paramObject);
            resultsPage.bookingError();

            TccModule tccModule = new TccModule(makeBooking, paramObject);
            tccModule.markAsTestByEmailAndSkipAutoTicket(paramObject.getsRegion());
            tccModule.validateFraudBooking();

            System.out.println("Finished validating fraud booking...");

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testOneHundredAndFive(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            FlightDetail flightDetail = new FlightDetail();

            AccountHomeModule homeaccmodule = new AccountHomeModule();
            List<FlightDetail> homeaccDetail = homeaccmodule.AccountHomeModule(paramObject);

            ProfileModule profileModule = new ProfileModule(paramObject);

            BookingsPage book = new BookingsPage(paramObject);
            book.bookFlight();

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            FlightDetail test = makeBooking.getVoucherDetail();
            String voucher = test.getVoucherCode();
            System.out.println("voucher " + voucher);

            TccModule tccModule = new TccModule(makeBooking, paramObject);
            tccModule.validateFrequentFlyer(confirmDetail);
            tccModule.validateMeal(confirmDetail);
            tccModule.validateSeat(confirmDetail);
            tccModule.tccModule(paramObject, confirmDetail, voucher);

            if (paramObject.getsTestCase().equalsIgnoreCase("TrackMyBooking")) {
                PendingFlightsModule pendingFlightsModule = new PendingFlightsModule(new PendingFlightsPage(), new LoginPage(paramObject), paramObject);
                pendingFlightsModule.validateWhereIsMyTicket(confirmDetail.getReference());
            }

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testOneHundredAndSix(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();

            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            TCCLogin login = new TCCLogin(paramObject);
            login.logintoTCC(paramObject.getsRegion());
            login.searchFlight2(confirmDetail);

            SelectMarket market = new SelectMarket();

            Customer customer = new Customer();
            customer.markasTest();
            customer.skipAutoTicket();

            String tbreference = customer.getTravelBarReference();
            confirmDetail.setTBReference(tbreference);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            Activities activity = new Activities(paramObject);
            activity.checkallActivities();

            Invoices invoices = new Invoices();
            invoices.sendNewBookingPaymentLink();

            String price = invoices.sendPaymentLink();

            TCCLogout logout = new TCCLogout();
            logout.logoutofTCC();

            VerifyNotifications verify = new VerifyNotifications(paramObject);
            verify.verifyBookingConfirmation(confirmDetail);

            PaymentsLinkPage paymentlink = new PaymentsLinkPage(paramObject);

            paymentlink.gotoPaymentsLink(paramObject.getsRegion(), confirmDetail);
            paymentlink.uncheckProductsAndgetTotalPrice(price);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testOneHundredAndSeven(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsTestCase() + ": " + paramObject.getsDescription());

            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);
            SearchModule searchmodule = new SearchModule();
            List<FlightDetail> searchDetail = searchmodule.SearchModule(paramObject, homeDetail);

            NewResultsPage results = new NewResultsPage(paramObject);
            results.verifySearchResults(paramObject.getsTestCase(), paramObject.getsTestCaseID(), paramObject.getsRegion());

            results.startQuote(paramObject.getsTestCase(), paramObject.getsDepartureCity(), paramObject.getsArrivalCity(), paramObject.getsTripType(), paramObject.getsPreferredAirline(), paramObject.getsFilterBy(), paramObject.getsRegion());
            results.mailQuote();

            if (bResult == true) {

                System.out.println("Test is successful");
                System.out.println();
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);

        }

    }

    @Test(enabled = true)
    public void testOneHundredAndEight(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            FlightDetail test = makeBooking.getVoucherDetail();
            String voucher = test.getVoucherCode();
            System.out.println("voucher " + voucher);

            TccModule tccModule = new TccModule(makeBooking, paramObject);
            tccModule.tccModule(paramObject, confirmDetail, voucher);

            Invoices invoices = new Invoices();
            invoices.takePayment();
            invoices.changeStatusToPaid();

            Customer customer = new Customer();
            String status = customer.getStatus();

            AssertionUtil asserts = new AssertionUtil(paramObject);
            asserts.assertStringEquals(status, "Paid");

            System.out.println("Validating text 'Ticketed'");
            customer.validateTicketed();

            System.out.println("Validating VoidTickets button...");
            invoices.validateVoidTicketsButton();

            PassengersAndFlights flight = new PassengersAndFlights();
            flight.refreshItinerary();
            flight.cancelBooking();
            flight.supplierInteractions();

            System.out.println("Validating booking ticket...");
            NotificationModule notify = new NotificationModule(new VerifyNotifications(paramObject));
            notify.verifyBookingTicketEmail(confirmDetail, makeBooking, paramObject.getsRegion());

            String amount = invoices.getPlatformCurrencyAmount();

            MonitLogs monitLogs = new MonitLogs();
            monitLogs.validateDecisionResponse();
            monitLogs.validatePreAuthRQ(amount);
            monitLogs.validatePreAuthRS();
            monitLogs.validateDebitRQ();
            monitLogs.validateDebitRS();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testOneHundredAndNine(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            FlightDetail test = makeBooking.getVoucherDetail();
            String voucher = test.getVoucherCode();
            System.out.println("voucher " + voucher);

            TccModule tccModule = new TccModule(makeBooking, paramObject);
            tccModule.tccModule(paramObject, confirmDetail, voucher);

            Invoices invoices = new Invoices();
            invoices.changePaymentLineToPaid("Paid");

            PassengersAndFlights flight = new PassengersAndFlights();

            System.out.println("About to click Pay and Ticket...");
            flight.payAndTicket();

            QueuesPage queuesPage = new QueuesPage();
            queuesPage.isQueueDisplayed("Pending CC Fraud Check");

            if (invoices.isBtnVoidTicketActive()) {
                invoices.btnVoidTickets_clickJavascript();
            }
            flight.cancelBooking();

            MonitLogs monitLogs = new MonitLogs(new XmlManipulation(), makeBooking, paramObject);
            monitLogs.validateOTA_AirDemandTicketRQ(confirmDetail);
            monitLogs.validateOTA_AirDemandTicketRS();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testOneHundredAndTen(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            BookFlight makeBooking = new BookFlight();
            FlightDetail confirmDetail = makeBooking.sharedBookFlight(paramObject);

            SaveReference savereference = new SaveReference();
            savereference.SaveReference(confirmDetail, paramObject.getsTestCaseID(), paramObject.getsTestCase(), paramObject.getsMakeBooking());

            System.out.println("Waiting for auto-ticketing to execute...");
            Thread.sleep(300000);

            FlightDetail test = makeBooking.getVoucherDetail();
            String voucher = test.getVoucherCode();
            System.out.println("voucher " + voucher);

            TccModule tccModule = new TccModule(makeBooking, paramObject);
            tccModule.tccModule(paramObject, confirmDetail, voucher);

            Customer customer = new Customer();
            String status = customer.getStatus();
            Assert.assertEquals(status, "Paid", "Failed to verify that the booking is autoticketed and Paid");
            System.out.println("Validating text 'Ticketed'");
            customer.validateTicketed();

            Invoices invoices = new Invoices();

            PassengersAndFlights flight = new PassengersAndFlights();

            QueuesPage queuesPage = new QueuesPage();
            queuesPage.isQueueDisplayed("Post Ticket Reprice");
            queuesPage.isQueueDisplayed("Successful Auto Ticketing");

            System.out.println("Validating VoidTickets button...");
            invoices.validateVoidTicketsButton();

            invoices.btnVoidTickets_click();
            flight.cancelBooking();

            System.out.println("Validating booking ticket...");
            NotificationModule notify = new NotificationModule(new VerifyNotifications(paramObject));
            notify.verifyBookingTicketEmail(confirmDetail, makeBooking, paramObject.getsRegion());

            MonitLogs monitLogs = new MonitLogs(new XmlManipulation(), makeBooking, paramObject);
            monitLogs.validateOTA_AirDemandTicketRQ(confirmDetail);
            monitLogs.validateOTA_AirDemandTicketRS();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testOneHundredAndTwelve(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);

            GetLinks links = new GetLinks();
            links.validatelinks();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);

        }

    }

    @Test(enabled = true)
    public void testOneHundredAndThirteen(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            HomeModule homemodule = new HomeModule(new NewHomePage(paramObject), paramObject);
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);

            homemodule.validatePages();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);

        }

    }

    @Test(enabled = true)
    public void testOneHundredAndFourteen(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            HomeModule homemodule = new HomeModule();
            FlightDetail homeDetail = homemodule.HomeModule(paramObject);
            SearchModule searchmodule = new SearchModule();
            searchmodule.SearchModule(paramObject, homeDetail);

            ResultsModule resultsModule = new ResultsModule(new NewResultsPage(paramObject));
            resultsModule.validateCurrencies(paramObject);

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);

        }

    }

    @Test(enabled = true)
    public void testOneHundredAndFifteen(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            AccountHomeModule homemodule = new AccountHomeModule(new NewHomePage(paramObject), new NewSearchPage(paramObject),
                    new NewResultsPage(paramObject), paramObject);
            List<FlightDetail> homeDetail = homemodule.AccountHomeModule(paramObject);

            // Test links with http requests
            GetLinks links = new GetLinks();
            links.validatelinks();

            // Test links with selenium WebDriver by performing user actions
            homemodule.validateLinks();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testOneHundredAndSixteen(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            AccountHomeModule homemodule = new AccountHomeModule();
            homemodule.navigateToHome(paramObject.getsRegion());

            SignInAndSignUpModule signInAndSignUpModule = new SignInAndSignUpModule(new SignUpAndSignInPage(paramObject), paramObject);
            signInAndSignUpModule.performSignUp();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testOneHundredAndSeventeen(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            AccountHomeModule homemodule = new AccountHomeModule();
            homemodule.navigateToHome(paramObject.getsRegion());

            SignInAndSignUpModule signInAndSignUpModule = new SignInAndSignUpModule(new SignUpAndSignInPage(paramObject), paramObject);
            BusinessProfileModule businessProfileModule = new BusinessProfileModule(new SignUpAndSignInPage(paramObject), new ProfilePage(paramObject), paramObject);
            signInAndSignUpModule.performSignIn();
            businessProfileModule.editProfile();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testOneHundredAndEighteen(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            AccountHomeModule homemodule = new AccountHomeModule();
            homemodule.navigateToHome(paramObject.getsRegion());

            SignInAndSignUpModule signInAndSignUpModule = new SignInAndSignUpModule(new SignUpAndSignInPage(paramObject), paramObject);
            CompanyTravellerModule companyTravellerModule = new CompanyTravellerModule(new CompanyTravellersPage(paramObject), paramObject);
            signInAndSignUpModule.performSignIn();
            signInAndSignUpModule.navigateToProfile();
            companyTravellerModule.addCompanyTraveller();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testOneHundredAndNineteen(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            AccountHomeModule homemodule = new AccountHomeModule();
            homemodule.navigateToHome(paramObject.getsRegion());

            SignInAndSignUpModule signInAndSignUpModule = new SignInAndSignUpModule(new SignUpAndSignInPage(paramObject), paramObject);
            BusinessPaymentModule businessPaymentModule = new BusinessPaymentModule(new PaymentPage(paramObject), paramObject);
            signInAndSignUpModule.performSignIn();
            signInAndSignUpModule.navigateToProfile();
            businessPaymentModule.addCard();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testOneHundredAndTwenty(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            AccountHomeModule homemodule = new AccountHomeModule();
            homemodule.navigateToHome(paramObject.getsRegion());

            SignInAndSignUpModule signInAndSignUpModule = new SignInAndSignUpModule(new SignUpAndSignInPage(paramObject), paramObject);
            signInAndSignUpModule.forgotPassword();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testOneHundredAndTwentyOne(TSParameterObject paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {

            System.out.println("Test case started");
            System.out.println(paramObject.getsTestCaseID() + ": " + paramObject.getsDescription());

            GetURL url = new GetURL();
            url.getURL(paramObject.getsRegion());

            // Test links with http requests
            GetLinks links = new GetLinks();
            links.validateRedirect(paramObject);
            links.validatelinks();

            if (bResult == true) {
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);
        }

    }

    @Test(enabled = true)
    public void testOneHundredAndTwentytwo(CMS_Object paramObject) throws InterruptedException, IOException, FindFailed, Exception {

        try {
            CMSLogin cmsLoging = new CMSLogin();
            cmsLoging.logintoCMS(paramObject.getsRegion());
            //CMSAirportPage cmsAirportPage = new CMSAirportPage(paramObject);
            CMSRoutes cmsRoutes;
            cmsRoutes = new CMSRoutes(paramObject);

            if (paramObject.getsTestCase().equals("CMSRoutes")) {
                cmsRoutes.navigateToRoutePages();
                cmsRoutes.travelInformation();
                //cmsAirportPage.createAirportPage();
            }

            if (bResult == true) {
                System.out.println("Test is successful");
                System.out.println();
                ExcelReader.setCellData("Passed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);

            } else {
                throw new Exception("Test Case Failed, please check logs");
            }

        } catch (Exception e) {

            ExcelReader.setCellData("Failed", paramObject.getsTestCaseID(), GLOBAL_CONSTANTS.Col_Result, GLOBAL_CONSTANTS.Col_TestCaseID);
            TakeScreenshot screenshot = new TakeScreenshot();
            GetSessionID session = new GetSessionID();
            session.getSessionID(paramObject.getsTestCaseID());
            screenshot.takeScreenshot(paramObject.getsTestCaseID(), paramObject.getsTestCase());
            Log.error(e.getMessage());
            throw (e);

        }
    }
}

