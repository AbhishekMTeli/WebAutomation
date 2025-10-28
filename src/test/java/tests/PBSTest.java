package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import adminPages.AdminDashBoardPage;
import adminPages.BecomeUserPage;
import adminPages.EFormReportsPage;
import adminPages.SLFHistoryPage;
import adminPages.TrainingManagerReviewPage;
import adminPages.UserDocsPage;
import base.BaseClass;
import commonPages.GradingHistoryPage;
import commonPages.LogoutPage;
import commonPages.PendingHistoryPage;
import commonPages.PopupPage;
import commonPages.TraineeGradingPage;
import commonPages.TrainerDashBoradPage;
import practiceBookSessionPages.PracticeBookSessionGradingPage;
import practiceBookSessionPages.PracticeBookSessionOverallOutcomePage;
import traineePages.TraineeReviewPage;

public class PBSTest extends BaseClass {
	private TrainerDashBoradPage trainerDashBoradPage;
	private TraineeGradingPage traineeGradingPage;
	private BecomeUserPage becomeUserPage;
	private AdminDashBoardPage adminDashBoardPage;
	private SLFHistoryPage slfHistoryPage;
	private GradingHistoryPage gradingHistoryPage;
	private TraineeReviewPage traineeReviewPage;
	private LogoutPage logoutPage;
	private PendingHistoryPage pendingHistoryPage;
	private PopupPage popupPage;
	private TrainingManagerReviewPage trainingManagerReviewPage;
	private EFormReportsPage eFormReportsPage;
	private UserDocsPage userDocsPage;
	private String lhsTraineeIdWithName;
	private String lhsTraineeId;
	private String rhsTraineeIdWithName;
	private String rhsTraineeId;
	private PracticeBookSessionGradingPage practiceBookSessionGradingPage;
	private PracticeBookSessionOverallOutcomePage practiceBookSessionOverallOutcomePage;

	@BeforeMethod(alwaysRun = true)
	public void initPages() {
		adminDashBoardPage = new AdminDashBoardPage(getDriver());
		becomeUserPage = new BecomeUserPage(getDriver());
		trainerDashBoradPage = new TrainerDashBoradPage(getDriver());
		traineeGradingPage = new TraineeGradingPage(getDriver());
		slfHistoryPage = new SLFHistoryPage(getDriver());
		traineeReviewPage = new TraineeReviewPage(getDriver());
		logoutPage = new LogoutPage(getDriver());
		trainingManagerReviewPage = new TrainingManagerReviewPage(getDriver());
		gradingHistoryPage = new GradingHistoryPage(getDriver());
		popupPage = new PopupPage(getDriver());
		userDocsPage = new UserDocsPage(getDriver());
		eFormReportsPage = new EFormReportsPage(getDriver());
		pendingHistoryPage = new PendingHistoryPage(getDriver());
		practiceBookSessionGradingPage = new PracticeBookSessionGradingPage(getDriver());
		practiceBookSessionOverallOutcomePage = new PracticeBookSessionOverallOutcomePage(getDriver());
	}

	@Test(description = "Happy Path for Practice Book Session")

	public void e2eHappyPathTestForCommandUpgradeSLF() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "3");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		practiceBookSessionOverallOutcomePage.enterLHSRemarks("adding lhs remarks");
		practiceBookSessionOverallOutcomePage.enterRHSRemarks("adding rhs remarks");
		practiceBookSessionOverallOutcomePage.clickLHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSNumberOfApproaches8CheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSNumberOfApproaches3CheckBox();
		practiceBookSessionOverallOutcomePage.selectQualification("SFI");
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.reasonForDelayLabelIsPresent();
		practiceBookSessionOverallOutcomePage.addDelayComments("adding delay comments");
		practiceBookSessionOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		practiceBookSessionOverallOutcomePage.visibilityOfPreviewHeader();
		practiceBookSessionOverallOutcomePage.clickPreviewNextButton();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.digitalSignitureLabelIsPresent();
		practiceBookSessionOverallOutcomePage.digitalSign();
		practiceBookSessionOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		String actualResult = popupPage.alertGetText();
		String expectedResult = "Data successfully uploaded!";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
		popupPage.clickAlertOkButton();
		traineeGradingPage.validateAllStaticTexts();
	}

	@Test(description = "Please enter Registration number.")
	public void validateEnterRegistrationNumber() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		String actualResult = popupPage.alertGetText();
		String expectedResult = "Please enter Registration number.";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
		popupPage.clickAlertOkButton();
	}

	@Test(description = "Please select simulator level.")
	public void validatePleaseEnterSimulatorLevel() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		practiceBookSessionGradingPage.eneterRegistrationNumber("test user");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		String actualResult = popupPage.alertGetText();
		String expectedResult = "Please select simulator level.";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
		popupPage.clickAlertOkButton();
	}

	@Test(description = "Please enter duration.")
	public void validatePleaseEnterDuration() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		practiceBookSessionGradingPage.eneterRegistrationNumber("test user");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.enterDuration("00:00");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		String actualResult = popupPage.alertGetText();
		String expectedResult = "Please enter duration.";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
		popupPage.clickAlertOkButton();
	}

	@Test(description = "At least one Observable Behaviour must be selected")
	public void validateAtLeastOneOBMustBeSelected() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "3");
		practiceBookSessionGradingPage.clickOnObDoneButton("PRO");
		String actualResult = popupPage.alertGetText();
		String expectedResult = "At least one Observable Behaviour must be selected";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
		popupPage.clickAlertOkButton();
	}

	@Test(description = "Please select Aircraft Type.")
	public void validatePleaseSelectAirCraftType() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		String actualResult = popupPage.alertGetText();
		String expectedResult = "Please select Aircraft Type.";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
		popupPage.clickAlertOkButton();
	}

	@Test(description = "Please add or change comments")
	public void validatePleaseAddOrChangeComments() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "1");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.clickOnObDoneButton("PRO");
		String actualResult = popupPage.alertGetText();
		String expectedResult = "Please add or change comments";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
		popupPage.clickAlertOkButton();
	}

	@Test(description = "Verify compentent and not yet competent are not selected by default when the grading is 2")
	public void validateManualSectionOfCompetentAndNotYetCompetent() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "2");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		Assert.assertFalse(practiceBookSessionOverallOutcomePage.lhsCompetentIsSelected(),
				"LHS Competent radio button should not be selected.");
		Assert.assertTrue(practiceBookSessionOverallOutcomePage.rhsCompetentIsSelected(),
				"RHS Competent radio button should be selected.");
		Assert.assertFalse(practiceBookSessionOverallOutcomePage.lhsNotYetCompetentIsSelected(),
				"LHS Not Yet Competent radio button should be disabled.");
		Assert.assertFalse(practiceBookSessionOverallOutcomePage.rhsNotYetCompetentIsSelected(),
				"RHS Not Yet Competent radio button should be disabled.");
		practiceBookSessionOverallOutcomePage.selectLHSCompetentRadioButton();
	}

	@Test(description = "Verify auto selection of compentent grading is 3 , 4 ,5")
	public void validateAutoSelectionOfCompetent() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "4");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		Assert.assertTrue(practiceBookSessionOverallOutcomePage.lhsCompetentIsSelected(),
				"LHS Competent radio button should be selected.");
		Assert.assertTrue(practiceBookSessionOverallOutcomePage.rhsCompetentIsSelected(),
				"RHS Competent radio button should be selected.");
		Assert.assertFalse(practiceBookSessionOverallOutcomePage.lhsNotYetCompetentIsSelected(),
				"LHS Not Yet Competent radio button should be disabled.");
		Assert.assertFalse(practiceBookSessionOverallOutcomePage.rhsNotYetCompetentIsSelected(),
				"RHS Not Yet Competent radio button should be disabled.");
	}

	@Test(description = "Verify auto selection of not yet compentent grading is 1")
	public void validateAutoSelectionOfNotYetCompetent() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "1");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		Assert.assertFalse(practiceBookSessionOverallOutcomePage.lhsCompetentIsSelected(),
				"LHS Competent radio button should not be selected.");
		Assert.assertTrue(practiceBookSessionOverallOutcomePage.rhsCompetentIsSelected(),
				"RHS Competent radio button should be selected.");
		Assert.assertTrue(practiceBookSessionOverallOutcomePage.lhsNotYetCompetentIsSelected(),
				"LHS Not Yet Competent radio button should be auto slected");
		Assert.assertFalse(practiceBookSessionOverallOutcomePage.rhsNotYetCompetentIsSelected(),
				"RHS Not Yet Competent radio button should be disabled.");
	}

	@Test(description = "You must enter the remarks for")
	public void validateYouMustEnterRemarkTextWhenRemarksAreLeftBlankForNotYetCompetent() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "1");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		practiceBookSessionOverallOutcomePage.enterLHSRemarks("");
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		String actualResult = popupPage.alertGetText();
		String expectedResult = "You must enter the remarks for";
		Assert.assertTrue(actualResult.contains(expectedResult),
				"Text mismatch: expected alert to contain \"" + expectedResult + "\" but got \"" + actualResult + "\"");

		popupPage.clickAlertOkButton();
	}

	@Test(description = "You must select 'LVTO'")
	public void validateYouMustSelectLVTO() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "4");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		String actualResult = popupPage.alertGetText();
		String expectedResult = "You must select 'LVTO'";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
		popupPage.clickAlertOkButton();
	}

	@Test(description = "You must select right side 'LVTO'")
	public void validateYouMustSelectLVTORight() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "4");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		practiceBookSessionOverallOutcomePage.clickLHSNoLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		String actualResult = popupPage.alertGetText();
		String expectedResult = "You must select right side 'LVTO'";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
		popupPage.clickAlertOkButton();
	}

	@Test(description = "You must select 'CAT II/III'")
	public void validateYouMustSelectCAT() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "4");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		practiceBookSessionOverallOutcomePage.clickLHSNoLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSNoLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		String actualResult = popupPage.alertGetText();
		String expectedResult = "You must select 'CAT II/III'";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
		popupPage.clickAlertOkButton();
	}

	@Test(description = "You must select right side 'CAT II/III'")
	public void validateYouMustSelectRightSideCAT() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "4");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		practiceBookSessionOverallOutcomePage.clickLHSNoLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSNoLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		String actualResult = popupPage.alertGetText();
		String expectedResult = "You must select right side 'CAT II/III'";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
		popupPage.clickAlertOkButton();
	}

	@Test(description = "You must select 'CAT II/III Minimum approaches'")
	public void validateYouMustSelectCATMinimumApproaches() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "4");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		practiceBookSessionOverallOutcomePage.clickLHSNoLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSNoLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		String actualResult = popupPage.alertGetText();
		String expectedResult = "You must select 'CAT II/III Minimum approaches'";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
		popupPage.clickAlertOkButton();
	}

	@Test(description = "You must select right side 'CAT II/III Minimum approaches'")
	public void validateYouMustSelectRightSideCATMinimumApproaches() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "4");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		practiceBookSessionOverallOutcomePage.clickLHSNoLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSNoLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSNumberOfApproaches3CheckBox();
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		String actualResult = popupPage.alertGetText();
		String expectedResult = "You must select right side 'CAT II/III Minimum approaches'";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
		popupPage.clickAlertOkButton();
	}

	@Test(description = "Please select qualification.")
	public void validatePleaseSelectQualification() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "4");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		practiceBookSessionOverallOutcomePage.clickLHSNoLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSNoLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSNumberOfApproaches3CheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSNumberOfApproaches3CheckBox();
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		String actualResult = popupPage.alertGetText();
		String expectedResult = "Please select qualification.";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
		popupPage.clickAlertOkButton();
	}

	@Test(description = "Please enter a comment before submitting.")
	public void validatePleaseEnterACommentBeforeSubmitting() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "4");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		practiceBookSessionOverallOutcomePage.clickLHSNoLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSNoLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSNumberOfApproaches3CheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSNumberOfApproaches3CheckBox();
		practiceBookSessionOverallOutcomePage.selectQualification("SFI");
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.reasonForDelayLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		String actualResult = popupPage.alertGetText();
		String expectedResult = "Please enter a comment before submitting.";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
		popupPage.clickAlertOkButton();
	}

	@Test(description = "Please provide a signature first.")
	public void validatePleaseProvideASignatureFirst() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "4");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		practiceBookSessionOverallOutcomePage.clickLHSNoLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSNoLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSNumberOfApproaches3CheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSNumberOfApproaches3CheckBox();
		practiceBookSessionOverallOutcomePage.selectQualification("SFI");
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.reasonForDelayLabelIsPresent();
		practiceBookSessionOverallOutcomePage.addDelayComments("adding delay comments");
		practiceBookSessionOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		practiceBookSessionOverallOutcomePage.visibilityOfPreviewHeader();
		practiceBookSessionOverallOutcomePage.clickPreviewNextButton();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.digitalSignitureLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		String actualResult = popupPage.alertGetText();
		String expectedResult = "Please provide a signature first.";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
		popupPage.clickAlertOkButton();
	}

	@Test(description = "Validate trainer can access submitted form through Grading History")
	public void validateTrainerCanAccessSubmittedFormThroughGradingHistory() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		lhsTraineeIdWithName = practiceBookSessionGradingPage.getLHSTrainerId();
		System.out.println("Trainee Name and Id is " + lhsTraineeIdWithName);
		lhsTraineeId = lhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		rhsTraineeIdWithName = practiceBookSessionGradingPage.getRHSTrainerId();
		System.out.println("Trainee Name and Id is " + rhsTraineeIdWithName);
		rhsTraineeId = rhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "3");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		practiceBookSessionOverallOutcomePage.enterLHSRemarks("adding lhs remarks");
		practiceBookSessionOverallOutcomePage.enterRHSRemarks("adding rhs remarks");
		practiceBookSessionOverallOutcomePage.clickLHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSNumberOfApproaches8CheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSNumberOfApproaches3CheckBox();
		practiceBookSessionOverallOutcomePage.selectQualification("SFI");
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.reasonForDelayLabelIsPresent();
		practiceBookSessionOverallOutcomePage.addDelayComments("adding delay comments");
		practiceBookSessionOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		practiceBookSessionOverallOutcomePage.visibilityOfPreviewHeader();
		practiceBookSessionOverallOutcomePage.clickPreviewNextButton();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.digitalSignitureLabelIsPresent();
		practiceBookSessionOverallOutcomePage.digitalSign();
		practiceBookSessionOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		traineeGradingPage.validateAllStaticTexts();
		gradingHistoryPage.clickGradingHistoryButton();
		gradingHistoryPage.validateAllStaticTexts();
		gradingHistoryPage.enterLessonName(lhsTraineeId);
		gradingHistoryPage.clickViewButton();
		gradingHistoryPage.validatePreviewHeader();
		gradingHistoryPage.clickCloseButton();
		gradingHistoryPage.clickViewButton();
		gradingHistoryPage.clickCloseIcon();
		gradingHistoryPage.validateAllStaticTexts();
		gradingHistoryPage.enterLessonName(rhsTraineeId);
		gradingHistoryPage.clickViewButton();
		gradingHistoryPage.validatePreviewHeader();
		gradingHistoryPage.clickCloseButton();
		gradingHistoryPage.clickViewButton();
		gradingHistoryPage.clickCloseIcon();
	}

	@Test(description = "Validate trainee can acknowledge the submitted form")
	public void validateTraineeCanAcknowledgeSubmittedForm() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		lhsTraineeIdWithName = practiceBookSessionGradingPage.getLHSTrainerId();
		System.out.println("Trainee Name and Id is " + lhsTraineeIdWithName);
		lhsTraineeId = lhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		rhsTraineeIdWithName = practiceBookSessionGradingPage.getRHSTrainerId();
		System.out.println("Trainee Name and Id is " + rhsTraineeIdWithName);
		rhsTraineeId = rhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "3");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		practiceBookSessionOverallOutcomePage.enterLHSRemarks("adding lhs remarks");
		practiceBookSessionOverallOutcomePage.enterRHSRemarks("adding rhs remarks");
		practiceBookSessionOverallOutcomePage.clickLHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSNumberOfApproaches8CheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSNumberOfApproaches3CheckBox();
		practiceBookSessionOverallOutcomePage.selectQualification("SFI");
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.reasonForDelayLabelIsPresent();
		practiceBookSessionOverallOutcomePage.addDelayComments("adding delay comments");
		practiceBookSessionOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		practiceBookSessionOverallOutcomePage.visibilityOfPreviewHeader();
		practiceBookSessionOverallOutcomePage.clickPreviewNextButton();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.digitalSignitureLabelIsPresent();
		practiceBookSessionOverallOutcomePage.digitalSign();
		practiceBookSessionOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		traineeGradingPage.validateAllStaticTexts();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendTraineeId(lhsTraineeId);
		becomeUserPage.clickOnBecomeUser();
		traineeReviewPage.clickGradingAndAssessmentTab();
		traineeReviewPage.clickTraineeReviewTab();
		traineeReviewPage.validateAllStaticElements();
		traineeReviewPage.clickViewButton();
		traineeReviewPage.clickAcknowledgeButton();
		traineeReviewPage.clickSubmitButtonForInstructorAcknowldgement();
		traineeReviewPage.digitalSignitureLabelIsPresent();
		traineeReviewPage.digitalSign();
		traineeReviewPage.clickSaveSignitureButtonForDigitalSigniture();
		traineeReviewPage.clickOkPop_up();
		traineeReviewPage.validateAllStaticElements();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendTraineeId(rhsTraineeId);
		becomeUserPage.clickOnBecomeUser();
		traineeReviewPage.clickGradingAndAssessmentTab();
		traineeReviewPage.clickTraineeReviewTab();
		traineeReviewPage.validateAllStaticElements();
		traineeReviewPage.clickViewButton();
		traineeReviewPage.clickAcknowledgeButton();
		traineeReviewPage.clickSubmitButtonForInstructorAcknowldgement();
		traineeReviewPage.digitalSignitureLabelIsPresent();
		traineeReviewPage.digitalSign();
		traineeReviewPage.clickSaveSignitureButtonForDigitalSigniture();
		traineeReviewPage.clickOkPop_up();
		traineeReviewPage.validateAllStaticElements();
	}

	@Test(description = "Validate trainee can mark the submitted form for review")
	public void validateTraineeCanMarkFormForReview() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		lhsTraineeIdWithName = practiceBookSessionGradingPage.getLHSTrainerId();
		System.out.println("Trainee Name and Id is " + lhsTraineeIdWithName);
		lhsTraineeId = lhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		rhsTraineeIdWithName = practiceBookSessionGradingPage.getRHSTrainerId();
		System.out.println("Trainee Name and Id is " + rhsTraineeIdWithName);
		rhsTraineeId = rhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "3");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		practiceBookSessionOverallOutcomePage.enterLHSRemarks("adding lhs remarks");
		practiceBookSessionOverallOutcomePage.enterRHSRemarks("adding rhs remarks");
		practiceBookSessionOverallOutcomePage.clickLHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSNumberOfApproaches8CheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSNumberOfApproaches3CheckBox();
		practiceBookSessionOverallOutcomePage.selectQualification("SFI");
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.reasonForDelayLabelIsPresent();
		practiceBookSessionOverallOutcomePage.addDelayComments("adding delay comments");
		practiceBookSessionOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		practiceBookSessionOverallOutcomePage.visibilityOfPreviewHeader();
		practiceBookSessionOverallOutcomePage.clickPreviewNextButton();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.digitalSignitureLabelIsPresent();
		practiceBookSessionOverallOutcomePage.digitalSign();
		practiceBookSessionOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		traineeGradingPage.validateAllStaticTexts();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendTraineeId(lhsTraineeId);
		becomeUserPage.clickOnBecomeUser();
		traineeReviewPage.clickGradingAndAssessmentTab();
		traineeReviewPage.clickTraineeReviewTab();
		traineeReviewPage.validateAllStaticElements();
		traineeReviewPage.clickViewButton();
		traineeReviewPage.clickMarkForReviewButton();
		String actualLHSText = traineeReviewPage.getMarkForReviewConfirmationText();
		String expectedLHSText = "Are you sure you want to mark this event for review by trainer ?";
		Assert.assertEquals(actualLHSText, expectedLHSText,
				"Text missmatch" + expectedLHSText + "is expected but found " + actualLHSText);
		traineeReviewPage.clickNoButton();
		traineeReviewPage.clickMarkForReviewButton();
		traineeReviewPage.clickYesButton();
		traineeReviewPage.validateAllStaticElements();

		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendTraineeId(rhsTraineeId);
		becomeUserPage.clickOnBecomeUser();
		traineeReviewPage.clickGradingAndAssessmentTab();
		traineeReviewPage.clickTraineeReviewTab();
		traineeReviewPage.validateAllStaticElements();
		traineeReviewPage.clickViewButton();
		traineeReviewPage.clickMarkForReviewButton();
		String actualRHSText = traineeReviewPage.getMarkForReviewConfirmationText();
		String expectedRHSText = "Are you sure you want to mark this event for review by trainer ?";
		Assert.assertEquals(actualRHSText, expectedRHSText,
				"Text missmatch" + expectedRHSText + "is expected but found " + actualRHSText);
		traineeReviewPage.clickNoButton();
		traineeReviewPage.clickMarkForReviewButton();
		traineeReviewPage.clickYesButton();
		traineeReviewPage.validateAllStaticElements();
	}

	@Test(description = "Validate trainer can see action under pending grading after trainee marks form for review")
	public void validateTrainerSeesPendingGradingActionAfterTraineeMarksForReview() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		lhsTraineeIdWithName = practiceBookSessionGradingPage.getLHSTrainerId();
		System.out.println("Trainee Name and Id is " + lhsTraineeIdWithName);
		lhsTraineeId = lhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		rhsTraineeIdWithName = practiceBookSessionGradingPage.getRHSTrainerId();
		System.out.println("Trainee Name and Id is " + rhsTraineeIdWithName);
		rhsTraineeId = rhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "3");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		practiceBookSessionOverallOutcomePage.enterLHSRemarks("adding lhs remarks");
		practiceBookSessionOverallOutcomePage.enterRHSRemarks("adding rhs remarks");
		practiceBookSessionOverallOutcomePage.clickLHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSNumberOfApproaches8CheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSNumberOfApproaches3CheckBox();
		practiceBookSessionOverallOutcomePage.selectQualification("SFI");
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.reasonForDelayLabelIsPresent();
		practiceBookSessionOverallOutcomePage.addDelayComments("adding delay comments");
		practiceBookSessionOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		practiceBookSessionOverallOutcomePage.visibilityOfPreviewHeader();
		practiceBookSessionOverallOutcomePage.clickPreviewNextButton();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.digitalSignitureLabelIsPresent();
		practiceBookSessionOverallOutcomePage.digitalSign();
		practiceBookSessionOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		traineeGradingPage.validateAllStaticTexts();

		// lhs
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendTraineeId(lhsTraineeId);
		becomeUserPage.clickOnBecomeUser();
		traineeReviewPage.clickGradingAndAssessmentTab();
		traineeReviewPage.clickTraineeReviewTab();
		traineeReviewPage.validateAllStaticElements();
		traineeReviewPage.clickViewButton();
		traineeReviewPage.clickMarkForReviewButton();
		String actualLHSText = traineeReviewPage.getMarkForReviewConfirmationText();
		String expectedLHSText = "Are you sure you want to mark this event for review by trainer ?";
		Assert.assertEquals(actualLHSText, expectedLHSText,
				"Text Mismatch" + expectedLHSText + "is expected but found " + actualLHSText);
		traineeReviewPage.clickNoButton();
		traineeReviewPage.clickMarkForReviewButton();
		traineeReviewPage.clickYesButton();
		traineeReviewPage.validateAllStaticElements();

		// rhs
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendTraineeId(rhsTraineeId);
		becomeUserPage.clickOnBecomeUser();
		traineeReviewPage.clickGradingAndAssessmentTab();
		traineeReviewPage.clickTraineeReviewTab();
		traineeReviewPage.validateAllStaticElements();
		traineeReviewPage.clickViewButton();
		traineeReviewPage.clickMarkForReviewButton();
		String actualRHSText = traineeReviewPage.getMarkForReviewConfirmationText();
		String expectedRHSText = "Are you sure you want to mark this event for review by trainer ?";
		Assert.assertEquals(actualRHSText, expectedRHSText,
				"Text Mismatch" + expectedRHSText + "is expected but found " + actualRHSText);
		traineeReviewPage.clickNoButton();
		traineeReviewPage.clickMarkForReviewButton();
		traineeReviewPage.clickYesButton();
		traineeReviewPage.validateAllStaticElements();

		// trainer side
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.validatePengingGradingStaticTexts();
		pendingHistoryPage.enterSearchText(lhsTraineeId);
		pendingHistoryPage.clickReviewButton();
		practiceBookSessionGradingPage.selectAircraftType("A321");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		pendingHistoryPage.validateUpdateGeneralInfoPopupLabelText();
		pendingHistoryPage.clickUpdateGeneralInfoPopupNoButton();
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		pendingHistoryPage.clickUpdateGeneralInfoPopupYesButton();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.validatePengingGradingStaticTexts();
		pendingHistoryPage.enterSearchText(rhsTraineeId);
		pendingHistoryPage.clickReviewButton();
		practiceBookSessionGradingPage.selectAircraftType("A321");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		pendingHistoryPage.validateUpdateGeneralInfoPopupLabelText();
		pendingHistoryPage.clickUpdateGeneralInfoPopupNoButton();
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		pendingHistoryPage.clickUpdateGeneralInfoPopupYesButton();
	}

	// from here
	@Test(description = "Validate trainee cannot mark the form for review after it has been reviewed and submitted by trainer")
	public void validateTraineeCannotMarkFormForReviewAfterTrainerSubmission() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		lhsTraineeIdWithName = practiceBookSessionGradingPage.getLHSTrainerId();
		System.out.println("Trainee Name and Id is " + lhsTraineeIdWithName);
		lhsTraineeId = lhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		rhsTraineeIdWithName = practiceBookSessionGradingPage.getRHSTrainerId();
		System.out.println("Trainee Name and Id is " + rhsTraineeIdWithName);
		rhsTraineeId = rhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "3");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		practiceBookSessionOverallOutcomePage.enterLHSRemarks("adding lhs remarks");
		practiceBookSessionOverallOutcomePage.enterRHSRemarks("adding rhs remarks");
		practiceBookSessionOverallOutcomePage.clickLHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSNumberOfApproaches8CheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSNumberOfApproaches3CheckBox();
		practiceBookSessionOverallOutcomePage.selectQualification("SFI");
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.reasonForDelayLabelIsPresent();
		practiceBookSessionOverallOutcomePage.addDelayComments("adding delay comments");
		practiceBookSessionOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		practiceBookSessionOverallOutcomePage.visibilityOfPreviewHeader();
		practiceBookSessionOverallOutcomePage.clickPreviewNextButton();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.digitalSignitureLabelIsPresent();
		practiceBookSessionOverallOutcomePage.digitalSign();
		practiceBookSessionOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		traineeGradingPage.validateAllStaticTexts();

		// lhs
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendTraineeId(lhsTraineeId);
		becomeUserPage.clickOnBecomeUser();
		traineeReviewPage.clickGradingAndAssessmentTab();
		traineeReviewPage.clickTraineeReviewTab();
		traineeReviewPage.validateAllStaticElements();
		traineeReviewPage.clickViewButton();
		traineeReviewPage.clickMarkForReviewButton();
		String actualLHSText = traineeReviewPage.getMarkForReviewConfirmationText();
		String expectedLHSText = "Are you sure you want to mark this event for review by trainer ?";
		Assert.assertEquals(actualLHSText, expectedLHSText,
				"Text Mismatch" + expectedLHSText + "is expected but found " + actualLHSText);
		traineeReviewPage.clickNoButton();
		traineeReviewPage.clickMarkForReviewButton();
		traineeReviewPage.clickYesButton();
		traineeReviewPage.validateAllStaticElements();

		// rhs
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendTraineeId(rhsTraineeId);
		becomeUserPage.clickOnBecomeUser();
		traineeReviewPage.clickGradingAndAssessmentTab();
		traineeReviewPage.clickTraineeReviewTab();
		traineeReviewPage.validateAllStaticElements();
		traineeReviewPage.clickViewButton();
		traineeReviewPage.clickMarkForReviewButton();
		String actualRHSText = traineeReviewPage.getMarkForReviewConfirmationText();
		String expectedRHSText = "Are you sure you want to mark this event for review by trainer ?";
		Assert.assertEquals(actualRHSText, expectedRHSText,
				"Text Mismatch" + expectedRHSText + "is expected but found " + actualRHSText);
		traineeReviewPage.clickNoButton();
		traineeReviewPage.clickMarkForReviewButton();
		traineeReviewPage.clickYesButton();
		traineeReviewPage.validateAllStaticElements();

		// trainer for lhs user
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.validatePengingGradingStaticTexts();
		pendingHistoryPage.enterSearchText(lhsTraineeId);
		pendingHistoryPage.clickReviewButton();
		practiceBookSessionGradingPage.selectAircraftType("A321");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		pendingHistoryPage.validateUpdateGeneralInfoPopupLabelText();
		pendingHistoryPage.clickUpdateGeneralInfoPopupNoButton();
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		pendingHistoryPage.clickUpdateGeneralInfoPopupYesButton();
		traineeGradingPage.validateAllStaticTexts();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendTraineeId(lhsTraineeId);
		becomeUserPage.clickOnBecomeUser();
		traineeReviewPage.clickGradingAndAssessmentTab();
		traineeReviewPage.clickTraineeReviewTab();
		traineeReviewPage.validateAllStaticElements();
		traineeReviewPage.enterTrainId(becomeUserPage.getTraineeId());
		traineeReviewPage.clickViewButton();
		traineeReviewPage.markForReviewButtonNotVisible();
		traineeReviewPage.clickAcknowledgeButton();

		// trainer for rhs user
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.validatePengingGradingStaticTexts();
		pendingHistoryPage.enterSearchText(rhsTraineeId);
		pendingHistoryPage.clickReviewButton();
		practiceBookSessionGradingPage.selectAircraftType("A321");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		pendingHistoryPage.validateUpdateGeneralInfoPopupLabelText();
		pendingHistoryPage.clickUpdateGeneralInfoPopupNoButton();
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		pendingHistoryPage.clickUpdateGeneralInfoPopupYesButton();
		traineeGradingPage.validateAllStaticTexts();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendTraineeId(rhsTraineeId);
		becomeUserPage.clickOnBecomeUser();
		traineeReviewPage.clickGradingAndAssessmentTab();
		traineeReviewPage.clickTraineeReviewTab();
		traineeReviewPage.validateAllStaticElements();
		traineeReviewPage.enterTrainId(becomeUserPage.getTraineeId());
		traineeReviewPage.clickViewButton();
		traineeReviewPage.markForReviewButtonNotVisible();
		traineeReviewPage.clickAcknowledgeButton();
	}

	@Test(description = "Validate admin can approve the form after trainee acknowledgement")
	public void validateAdminCanApproveFormAfterTraineeAcknowledgement() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		lhsTraineeIdWithName = practiceBookSessionGradingPage.getLHSTrainerId();
		System.out.println("Trainee Name and Id is " + lhsTraineeIdWithName);
		lhsTraineeId = lhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		rhsTraineeIdWithName = practiceBookSessionGradingPage.getRHSTrainerId();
		System.out.println("Trainee Name and Id is " + rhsTraineeIdWithName);
		rhsTraineeId = rhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "3");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		practiceBookSessionOverallOutcomePage.enterLHSRemarks("adding lhs remarks");
		practiceBookSessionOverallOutcomePage.enterRHSRemarks("adding rhs remarks");
		practiceBookSessionOverallOutcomePage.clickLHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSNumberOfApproaches8CheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSNumberOfApproaches3CheckBox();
		practiceBookSessionOverallOutcomePage.selectQualification("SFI");
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.reasonForDelayLabelIsPresent();
		practiceBookSessionOverallOutcomePage.addDelayComments("adding delay comments");
		practiceBookSessionOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		practiceBookSessionOverallOutcomePage.visibilityOfPreviewHeader();
		practiceBookSessionOverallOutcomePage.clickPreviewNextButton();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.digitalSignitureLabelIsPresent();
		practiceBookSessionOverallOutcomePage.digitalSign();
		practiceBookSessionOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		traineeGradingPage.validateAllStaticTexts();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendTraineeId(traineeId);
		becomeUserPage.clickOnBecomeUser();
		traineeReviewPage.clickGradingAndAssessmentTab();
		traineeReviewPage.clickTraineeReviewTab();
		traineeReviewPage.validateAllStaticElements();
		traineeReviewPage.clickViewButton();
		traineeReviewPage.clickAcknowledgeButton();
		traineeReviewPage.clickSubmitButtonForInstructorAcknowldgement();
		traineeReviewPage.digitalSignitureLabelIsPresent();
		traineeReviewPage.digitalSign();
		traineeReviewPage.clickSaveSignitureButtonForDigitalSigniture();
		traineeReviewPage.clickOkPop_up();
		traineeReviewPage.validateAllStaticElements();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickGradingAndAssessmentTab();
		adminDashBoardPage.clickTrainingManagerReviewSubTab();
		trainingManagerReviewPage.validateAllTableHeaders();
		trainingManagerReviewPage.presenceOfViewButton();
		trainingManagerReviewPage.searchforTrainee(traineeId);
		trainingManagerReviewPage.clickViewButton();
		trainingManagerReviewPage.enterComment("adding comments");
		trainingManagerReviewPage.clickApproveButton();
		trainingManagerReviewPage.validateTextAreSureYouWantToApprove();
		trainingManagerReviewPage.clickYesButtonForApprove();
		String actualText = trainingManagerReviewPage.getTextOfPopup();
		String expectedText = "OK Approved successfully";
		Assert.assertEquals(actualText, expectedText,
				"Text mismatch : expected " + expectedText + " but got " + actualText);
		trainingManagerReviewPage.clickOkPopupButton();
	}

	@Test(description = "Validate admin cannot mark for review without adding comments")
	public void validateAdminCannotMarkForReviewWithoutComments() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		lhsTraineeIdWithName = practiceBookSessionGradingPage.getLHSTrainerId();
		System.out.println("Trainee Name and Id is " + lhsTraineeIdWithName);
		lhsTraineeId = lhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		rhsTraineeIdWithName = practiceBookSessionGradingPage.getRHSTrainerId();
		System.out.println("Trainee Name and Id is " + rhsTraineeIdWithName);
		rhsTraineeId = rhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "3");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		practiceBookSessionOverallOutcomePage.enterLHSRemarks("adding lhs remarks");
		practiceBookSessionOverallOutcomePage.enterRHSRemarks("adding rhs remarks");
		practiceBookSessionOverallOutcomePage.clickLHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSNumberOfApproaches8CheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSNumberOfApproaches3CheckBox();
		practiceBookSessionOverallOutcomePage.selectQualification("SFI");
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.reasonForDelayLabelIsPresent();
		practiceBookSessionOverallOutcomePage.addDelayComments("adding delay comments");
		practiceBookSessionOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		practiceBookSessionOverallOutcomePage.visibilityOfPreviewHeader();
		practiceBookSessionOverallOutcomePage.clickPreviewNextButton();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.digitalSignitureLabelIsPresent();
		practiceBookSessionOverallOutcomePage.digitalSign();
		practiceBookSessionOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		traineeGradingPage.validateAllStaticTexts();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendTraineeId(traineeId);
		becomeUserPage.clickOnBecomeUser();
		traineeReviewPage.clickGradingAndAssessmentTab();
		traineeReviewPage.clickTraineeReviewTab();
		traineeReviewPage.validateAllStaticElements();
		traineeReviewPage.clickViewButton();
		traineeReviewPage.clickAcknowledgeButton();
		traineeReviewPage.clickSubmitButtonForInstructorAcknowldgement();
		traineeReviewPage.digitalSignitureLabelIsPresent();
		traineeReviewPage.digitalSign();
		traineeReviewPage.clickSaveSignitureButtonForDigitalSigniture();
		traineeReviewPage.clickOkPop_up();
		traineeReviewPage.validateAllStaticElements();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickGradingAndAssessmentTab();
		adminDashBoardPage.clickTrainingManagerReviewSubTab();
		trainingManagerReviewPage.validateAllTableHeaders();
		trainingManagerReviewPage.presenceOfViewButton();
		trainingManagerReviewPage.searchforTrainee(traineeId);
		trainingManagerReviewPage.clickViewButton();
		trainingManagerReviewPage.clickMarkForReviewButton();
		String actualText = trainingManagerReviewPage.getTextOfPopup();
		String expectedText = "OK Please enter comments.";
		Assert.assertEquals(actualText, expectedText,
				"Text mismatch : expected " + expectedText + " but got " + actualText);
		trainingManagerReviewPage.clickOkPopupButton();
	}

	@Test(description = "Validate admin can mark for review with adding comments")
	public void validateAdminCanMarkForReviewWithComments() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		lhsTraineeIdWithName = practiceBookSessionGradingPage.getLHSTrainerId();
		System.out.println("Trainee Name and Id is " + lhsTraineeIdWithName);
		lhsTraineeId = lhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		rhsTraineeIdWithName = practiceBookSessionGradingPage.getRHSTrainerId();
		System.out.println("Trainee Name and Id is " + rhsTraineeIdWithName);
		rhsTraineeId = rhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "3");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		practiceBookSessionOverallOutcomePage.enterLHSRemarks("adding lhs remarks");
		practiceBookSessionOverallOutcomePage.enterRHSRemarks("adding rhs remarks");
		practiceBookSessionOverallOutcomePage.clickLHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSNumberOfApproaches8CheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSNumberOfApproaches3CheckBox();
		practiceBookSessionOverallOutcomePage.selectQualification("SFI");
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.reasonForDelayLabelIsPresent();
		practiceBookSessionOverallOutcomePage.addDelayComments("adding delay comments");
		practiceBookSessionOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		practiceBookSessionOverallOutcomePage.visibilityOfPreviewHeader();
		practiceBookSessionOverallOutcomePage.clickPreviewNextButton();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.digitalSignitureLabelIsPresent();
		practiceBookSessionOverallOutcomePage.digitalSign();
		practiceBookSessionOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		traineeGradingPage.validateAllStaticTexts();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendTraineeId(traineeId);
		becomeUserPage.clickOnBecomeUser();
		traineeReviewPage.clickGradingAndAssessmentTab();
		traineeReviewPage.clickTraineeReviewTab();
		traineeReviewPage.validateAllStaticElements();
		traineeReviewPage.clickViewButton();
		traineeReviewPage.clickAcknowledgeButton();
		traineeReviewPage.clickSubmitButtonForInstructorAcknowldgement();
		traineeReviewPage.digitalSignitureLabelIsPresent();
		traineeReviewPage.digitalSign();
		traineeReviewPage.clickSaveSignitureButtonForDigitalSigniture();
		traineeReviewPage.clickOkPop_up();
		traineeReviewPage.validateAllStaticElements();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickGradingAndAssessmentTab();
		adminDashBoardPage.clickTrainingManagerReviewSubTab();
		trainingManagerReviewPage.validateAllTableHeaders();
		trainingManagerReviewPage.presenceOfViewButton();
		trainingManagerReviewPage.searchforTrainee(traineeId);
		trainingManagerReviewPage.clickViewButton();
		trainingManagerReviewPage.enterComment("entering review comments");
		trainingManagerReviewPage.clickMarkForReviewButton();
		trainingManagerReviewPage.validateTextAreSureYouWantToMarkForReview();
		trainingManagerReviewPage.clickYesButtonForReview();
		String actualText = popupPage.alertGetText();
		String expectedText = "The training event has been marked for review.";
		Assert.assertEquals(actualText, expectedText,
				"Text mismatch : expected " + expectedText + " but got " + actualText);
		trainingManagerReviewPage.clickOkPopupButton();
	}

	@Test(description = "Validate \"Mark for Review\" button absence after trainer form submission")
	public void validateMarkForReviewButtonAbsentAfterTrainerSubmission() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		lhsTraineeIdWithName = practiceBookSessionGradingPage.getLHSTrainerId();
		System.out.println("Trainee Name and Id is " + lhsTraineeIdWithName);
		lhsTraineeId = lhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		rhsTraineeIdWithName = practiceBookSessionGradingPage.getRHSTrainerId();
		System.out.println("Trainee Name and Id is " + rhsTraineeIdWithName);
		rhsTraineeId = rhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "3");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		practiceBookSessionOverallOutcomePage.enterLHSRemarks("adding lhs remarks");
		practiceBookSessionOverallOutcomePage.enterRHSRemarks("adding rhs remarks");
		practiceBookSessionOverallOutcomePage.clickLHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSNumberOfApproaches8CheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSNumberOfApproaches3CheckBox();
		practiceBookSessionOverallOutcomePage.selectQualification("SFI");
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.reasonForDelayLabelIsPresent();
		practiceBookSessionOverallOutcomePage.addDelayComments("adding delay comments");
		practiceBookSessionOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		practiceBookSessionOverallOutcomePage.visibilityOfPreviewHeader();
		practiceBookSessionOverallOutcomePage.clickPreviewNextButton();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.digitalSignitureLabelIsPresent();
		practiceBookSessionOverallOutcomePage.digitalSign();
		practiceBookSessionOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		traineeGradingPage.validateAllStaticTexts();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickGradingAndAssessmentTab();
		adminDashBoardPage.clickTrainingManagerReviewSubTab();
		trainingManagerReviewPage.validateAllTableHeaders();
		trainingManagerReviewPage.presenceOfViewButton();
		trainingManagerReviewPage.searchforTrainee(traineeId);
		trainingManagerReviewPage.clickViewButton();
		boolean isAbsent = trainingManagerReviewPage.isMarkForReviewButtonAbsent();
		Assert.assertTrue(isAbsent, "Mark for Review button should be absent after trainer form submission");
	}

	@Test(description = "Validate approved form is visible under Approved Docs and can be viewed via Training Records Approval page")
	public void validateApprovedFormVisibleUnderApprovedDocs() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		lhsTraineeIdWithName = practiceBookSessionGradingPage.getLHSTrainerId();
		System.out.println("Trainee Name and Id is " + lhsTraineeIdWithName);
		lhsTraineeId = lhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		rhsTraineeIdWithName = practiceBookSessionGradingPage.getRHSTrainerId();
		System.out.println("Trainee Name and Id is " + rhsTraineeIdWithName);
		rhsTraineeId = rhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "3");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		practiceBookSessionOverallOutcomePage.enterLHSRemarks("adding lhs remarks");
		practiceBookSessionOverallOutcomePage.enterRHSRemarks("adding rhs remarks");
		practiceBookSessionOverallOutcomePage.clickLHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSNumberOfApproaches8CheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSNumberOfApproaches3CheckBox();
		practiceBookSessionOverallOutcomePage.selectQualification("SFI");
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.reasonForDelayLabelIsPresent();
		practiceBookSessionOverallOutcomePage.addDelayComments("adding delay comments");
		practiceBookSessionOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		practiceBookSessionOverallOutcomePage.visibilityOfPreviewHeader();
		practiceBookSessionOverallOutcomePage.clickPreviewNextButton();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.digitalSignitureLabelIsPresent();
		practiceBookSessionOverallOutcomePage.digitalSign();
		practiceBookSessionOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		traineeGradingPage.validateAllStaticTexts();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendTraineeId(traineeId);
		becomeUserPage.clickOnBecomeUser();
		traineeReviewPage.clickGradingAndAssessmentTab();
		traineeReviewPage.clickTraineeReviewTab();
		traineeReviewPage.validateAllStaticElements();
		traineeReviewPage.clickViewButton();
		traineeReviewPage.clickAcknowledgeButton();
		traineeReviewPage.clickSubmitButtonForInstructorAcknowldgement();
		traineeReviewPage.digitalSignitureLabelIsPresent();
		traineeReviewPage.digitalSign();
		traineeReviewPage.clickSaveSignitureButtonForDigitalSigniture();
		traineeReviewPage.clickOkPop_up();
		traineeReviewPage.validateAllStaticElements();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickGradingAndAssessmentTab();
		adminDashBoardPage.clickTrainingManagerReviewSubTab();
		trainingManagerReviewPage.validateAllTableHeaders();
		trainingManagerReviewPage.presenceOfViewButton();
		trainingManagerReviewPage.searchforTrainee(traineeId);
		trainingManagerReviewPage.clickViewButton();
		trainingManagerReviewPage.enterComment("adding comments");
		trainingManagerReviewPage.clickApproveButton();
		trainingManagerReviewPage.validateTextAreSureYouWantToApprove();
		trainingManagerReviewPage.clickYesButtonForApprove();
		String actualText = trainingManagerReviewPage.getTextOfPopup();
		String expectedText = "OK Approved successfully";
		Assert.assertEquals(actualText, expectedText,
				"Text mismatch : expected " + expectedText + " but got " + actualText);
		trainingManagerReviewPage.clickOkPopupButton();
		userDocsPage.clickUserDocsTab();
		userDocsPage.clickApprovedDocsTab();
		userDocsPage.validateAllTexts();
		userDocsPage.clickEyeIcon();
		userDocsPage.clickCloseIcon();
		userDocsPage.clickEyeIcon();
		userDocsPage.clickUploadedDocumentLink();
	}

	@Test(description = "Validate approved form report is visible under E-Form Reports")
	public void validateApprovedFormReportVisibleUnderEFormReports() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		lhsTraineeIdWithName = practiceBookSessionGradingPage.getLHSTrainerId();
		System.out.println("Trainee Name and Id is " + lhsTraineeIdWithName);
		lhsTraineeId = lhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		rhsTraineeIdWithName = practiceBookSessionGradingPage.getRHSTrainerId();
		System.out.println("Trainee Name and Id is " + rhsTraineeIdWithName);
		rhsTraineeId = rhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "3");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		practiceBookSessionOverallOutcomePage.enterLHSRemarks("adding lhs remarks");
		practiceBookSessionOverallOutcomePage.enterRHSRemarks("adding rhs remarks");
		practiceBookSessionOverallOutcomePage.clickLHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSNumberOfApproaches8CheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSNumberOfApproaches3CheckBox();
		practiceBookSessionOverallOutcomePage.selectQualification("SFI");
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.reasonForDelayLabelIsPresent();
		practiceBookSessionOverallOutcomePage.addDelayComments("adding delay comments");
		practiceBookSessionOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		practiceBookSessionOverallOutcomePage.visibilityOfPreviewHeader();
		practiceBookSessionOverallOutcomePage.clickPreviewNextButton();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.digitalSignitureLabelIsPresent();
		practiceBookSessionOverallOutcomePage.digitalSign();
		practiceBookSessionOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		traineeGradingPage.validateAllStaticTexts();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendTraineeId(traineeId);
		becomeUserPage.clickOnBecomeUser();
		traineeReviewPage.clickGradingAndAssessmentTab();
		traineeReviewPage.clickTraineeReviewTab();
		traineeReviewPage.validateAllStaticElements();
		traineeReviewPage.clickViewButton();
		traineeReviewPage.clickAcknowledgeButton();
		traineeReviewPage.clickSubmitButtonForInstructorAcknowldgement();
		traineeReviewPage.digitalSignitureLabelIsPresent();
		traineeReviewPage.digitalSign();
		traineeReviewPage.clickSaveSignitureButtonForDigitalSigniture();
		traineeReviewPage.clickOkPop_up();
		traineeReviewPage.validateAllStaticElements();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickGradingAndAssessmentTab();
		adminDashBoardPage.clickTrainingManagerReviewSubTab();
		trainingManagerReviewPage.validateAllTableHeaders();
		trainingManagerReviewPage.presenceOfViewButton();
		trainingManagerReviewPage.searchforTrainee(traineeId);
		trainingManagerReviewPage.clickViewButton();
		trainingManagerReviewPage.enterComment("adding comments");
		trainingManagerReviewPage.clickApproveButton();
		trainingManagerReviewPage.validateTextAreSureYouWantToApprove();
		trainingManagerReviewPage.clickYesButtonForApprove();
		String actualText = trainingManagerReviewPage.getTextOfPopup();
		String expectedText = "OK Approved successfully";
		Assert.assertEquals(actualText, expectedText,
				"Text mismatch : expected " + expectedText + " but got " + actualText);
		trainingManagerReviewPage.clickOkPopupButton();
		adminDashBoardPage.clickReportsTab();
		adminDashBoardPage.clickEformReportsSubTab();
		eFormReportsPage.validateAllStaticElements();
	}

	@Test(description = "Validate reports can be downloaded as a .zip file when multiple checkboxes are selected")
	public void validateReportsDownloadAsZipWhenMultipleCheckboxesSelected() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		lhsTraineeIdWithName = practiceBookSessionGradingPage.getLHSTrainerId();
		System.out.println("Trainee Name and Id is " + lhsTraineeIdWithName);
		lhsTraineeId = lhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		rhsTraineeIdWithName = practiceBookSessionGradingPage.getRHSTrainerId();
		System.out.println("Trainee Name and Id is " + rhsTraineeIdWithName);
		rhsTraineeId = rhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "3");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		practiceBookSessionOverallOutcomePage.enterLHSRemarks("adding lhs remarks");
		practiceBookSessionOverallOutcomePage.enterRHSRemarks("adding rhs remarks");
		practiceBookSessionOverallOutcomePage.clickLHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSNumberOfApproaches8CheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSNumberOfApproaches3CheckBox();
		practiceBookSessionOverallOutcomePage.selectQualification("SFI");
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.reasonForDelayLabelIsPresent();
		practiceBookSessionOverallOutcomePage.addDelayComments("adding delay comments");
		practiceBookSessionOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		practiceBookSessionOverallOutcomePage.visibilityOfPreviewHeader();
		practiceBookSessionOverallOutcomePage.clickPreviewNextButton();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.digitalSignitureLabelIsPresent();
		practiceBookSessionOverallOutcomePage.digitalSign();
		practiceBookSessionOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		traineeGradingPage.validateAllStaticTexts();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendTraineeId(traineeId);
		becomeUserPage.clickOnBecomeUser();
		traineeReviewPage.clickGradingAndAssessmentTab();
		traineeReviewPage.clickTraineeReviewTab();
		traineeReviewPage.validateAllStaticElements();
		traineeReviewPage.clickViewButton();
		traineeReviewPage.clickAcknowledgeButton();
		traineeReviewPage.clickSubmitButtonForInstructorAcknowldgement();
		traineeReviewPage.digitalSignitureLabelIsPresent();
		traineeReviewPage.digitalSign();
		traineeReviewPage.clickSaveSignitureButtonForDigitalSigniture();
		traineeReviewPage.clickOkPop_up();
		traineeReviewPage.validateAllStaticElements();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickGradingAndAssessmentTab();
		adminDashBoardPage.clickTrainingManagerReviewSubTab();
		trainingManagerReviewPage.validateAllTableHeaders();
		trainingManagerReviewPage.presenceOfViewButton();
		trainingManagerReviewPage.searchforTrainee(traineeId);
		trainingManagerReviewPage.clickViewButton();
		trainingManagerReviewPage.enterComment("adding comments");
		trainingManagerReviewPage.clickApproveButton();
		trainingManagerReviewPage.validateTextAreSureYouWantToApprove();
		trainingManagerReviewPage.clickYesButtonForApprove();
		String actualText = trainingManagerReviewPage.getTextOfPopup();
		String expectedText = "OK Approved successfully";
		Assert.assertEquals(actualText, expectedText,
				"Text mismatch : expected " + expectedText + " but got " + actualText);
		trainingManagerReviewPage.clickOkPopupButton();
		adminDashBoardPage.clickReportsTab();
		adminDashBoardPage.clickEformReportsSubTab();
		eFormReportsPage.validateAllStaticElements();
		eFormReportsPage.clickCheckBoxesButton();
		eFormReportsPage.clickDownloadButtton();
	}

	@Test(description = "Validate reports can be downloaded as a .zip file when a single checkbox is selected")
	public void validateReportDownloadAsZipWhenSingleCheckboxSelected() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		practiceBookSessionGradingPage.validatePBSGradePageStaticTexts();
		lhsTraineeIdWithName = practiceBookSessionGradingPage.getLHSTrainerId();
		System.out.println("Trainee Name and Id is " + lhsTraineeIdWithName);
		lhsTraineeId = lhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		rhsTraineeIdWithName = practiceBookSessionGradingPage.getRHSTrainerId();
		System.out.println("Trainee Name and Id is " + rhsTraineeIdWithName);
		rhsTraineeId = rhsTraineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		practiceBookSessionGradingPage.eneterRegistrationNumber("TestUser");
		practiceBookSessionGradingPage.selectLocation("DEL");
		practiceBookSessionGradingPage.selectSimulatorLevel("FFS Level D");
		practiceBookSessionGradingPage.selectAircraftType("A320");
		practiceBookSessionGradingPage.clickPanel(0);
		practiceBookSessionGradingPage.selectGrade("lhs", "PRO", "3");
		practiceBookSessionGradingPage.clickAllMinusButtons("PRO");
		practiceBookSessionGradingPage.enterObComment("entering OB Comment", "PRO");
		practiceBookSessionGradingPage.clickObDoneButton("PRO");
		practiceBookSessionGradingPage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.validatePBSOverallOutComePage();
		practiceBookSessionOverallOutcomePage.enterLHSRemarks("adding lhs remarks");
		practiceBookSessionOverallOutcomePage.enterRHSRemarks("adding rhs remarks");
		practiceBookSessionOverallOutcomePage.clickLHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesLVTOCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSYesCATCheckBox();
		practiceBookSessionOverallOutcomePage.clickLHSNumberOfApproaches8CheckBox();
		practiceBookSessionOverallOutcomePage.clickRHSNumberOfApproaches3CheckBox();
		practiceBookSessionOverallOutcomePage.selectQualification("SFI");
		practiceBookSessionOverallOutcomePage.clickSaveAndNextButton();
		practiceBookSessionOverallOutcomePage.reasonForDelayLabelIsPresent();
		practiceBookSessionOverallOutcomePage.addDelayComments("adding delay comments");
		practiceBookSessionOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		practiceBookSessionOverallOutcomePage.visibilityOfPreviewHeader();
		practiceBookSessionOverallOutcomePage.clickPreviewNextButton();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		practiceBookSessionOverallOutcomePage.clickOkPop_up();
		practiceBookSessionOverallOutcomePage.digitalSignitureLabelIsPresent();
		practiceBookSessionOverallOutcomePage.digitalSign();
		practiceBookSessionOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		traineeGradingPage.validateAllStaticTexts();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendTraineeId(traineeId);
		becomeUserPage.clickOnBecomeUser();
		traineeReviewPage.clickGradingAndAssessmentTab();
		traineeReviewPage.clickTraineeReviewTab();
		traineeReviewPage.validateAllStaticElements();
		traineeReviewPage.clickViewButton();
		traineeReviewPage.clickAcknowledgeButton();
		traineeReviewPage.clickSubmitButtonForInstructorAcknowldgement();
		traineeReviewPage.digitalSignitureLabelIsPresent();
		traineeReviewPage.digitalSign();
		traineeReviewPage.clickSaveSignitureButtonForDigitalSigniture();
		traineeReviewPage.clickOkPop_up();
		traineeReviewPage.validateAllStaticElements();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickGradingAndAssessmentTab();
		adminDashBoardPage.clickTrainingManagerReviewSubTab();
		trainingManagerReviewPage.validateAllTableHeaders();
		trainingManagerReviewPage.presenceOfViewButton();
		trainingManagerReviewPage.searchforTrainee(traineeId);
		trainingManagerReviewPage.clickViewButton();
		trainingManagerReviewPage.enterComment("adding comments");
		trainingManagerReviewPage.clickApproveButton();
		trainingManagerReviewPage.validateTextAreSureYouWantToApprove();
		trainingManagerReviewPage.clickYesButtonForApprove();
		String actualText = trainingManagerReviewPage.getTextOfPopup();
		String expectedText = "OK Approved successfully";
		Assert.assertEquals(actualText, expectedText,
				"Text mismatch : expected " + expectedText + " but got " + actualText);
		trainingManagerReviewPage.clickOkPopupButton();
		adminDashBoardPage.clickReportsTab();
		adminDashBoardPage.clickEformReportsSubTab();
		eFormReportsPage.validateAllStaticElements();
		eFormReportsPage.searchForTrainee(traineeId);
		eFormReportsPage.clickCheckBoxesButton();
		eFormReportsPage.clickDownloadButtton();
	}
}
