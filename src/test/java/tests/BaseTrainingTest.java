package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import adminPages.AdminDashBoardPage;
import adminPages.BecomeUserPage;
import adminPages.EFormReportsPage;
import adminPages.TrainingManagerReviewPage;
import adminPages.UserDocsPage;
import base.BaseClass;
import baseTrainingPages.BaseTrainingGradingPage;
import baseTrainingPages.BaseTrainingOverallOutcomePage;
import commonPages.GradingHistoryPage;
import commonPages.LogoutPage;
import commonPages.PendingHistoryPage;
import commonPages.PopupPage;
import commonPages.TraineeGradingPage;
import commonPages.TrainerDashBoradPage;
import traineePages.TraineeReviewPage;

public class BaseTrainingTest extends BaseClass {
	private TrainerDashBoradPage trainerDashBoradPage;
	private TraineeGradingPage traineeGradingPage;
	private PopupPage popupPage;
	private GradingHistoryPage gradingHistoryPage;
	private LogoutPage logoutPage;
	private TraineeReviewPage traineeReviewPage;
	private PendingHistoryPage pendingHistoryPage;
	private TrainingManagerReviewPage trainingManagerReviewPage;
	private UserDocsPage userDocsPage;
	private EFormReportsPage eFormReportsPage;
	private String traineeIdWithName;
	private String traineeId;
	private BaseTrainingGradingPage baseTrainingGradingPage;
	private BaseTrainingOverallOutcomePage baseTrainingOverallOutcomePage;

	@BeforeMethod(alwaysRun = true)
	public void initPages() {
		adminDashBoardPage = new AdminDashBoardPage(getDriver());
		becomeUserPage = new BecomeUserPage(getDriver());
		trainerDashBoradPage = new TrainerDashBoradPage(getDriver());
		traineeGradingPage = new TraineeGradingPage(getDriver());
		popupPage = new PopupPage(getDriver());
		gradingHistoryPage = new GradingHistoryPage(getDriver());
		logoutPage = new LogoutPage(getDriver());
		traineeReviewPage = new TraineeReviewPage(getDriver());
		pendingHistoryPage = new PendingHistoryPage(getDriver());
		trainingManagerReviewPage = new TrainingManagerReviewPage(getDriver());
		userDocsPage = new UserDocsPage(getDriver());
		eFormReportsPage = new EFormReportsPage(getDriver());
		baseTrainingGradingPage = new BaseTrainingGradingPage(getDriver());
		baseTrainingOverallOutcomePage = new BaseTrainingOverallOutcomePage(getDriver());
	}

	@Test(description = "Base Training Form e2e Happy Path test case")
	public void happyPathCAT_II_III_Test() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("LSGG");
		baseTrainingGradingPage.enterTo("VANP");
		baseTrainingGradingPage.selectAircraftType("A321");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickGrade("PRO", "2");
		baseTrainingGradingPage.clickAllMinus("PRO");
		baseTrainingGradingPage.enterOBComments("PRO", "adding OB comment");
		baseTrainingGradingPage.clickOBDoneButton("PRO");
		baseTrainingGradingPage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.validateBaseTrainingOverallOutComePage();
		baseTrainingOverallOutcomePage.selectCompetentRadioButton();
		baseTrainingOverallOutcomePage.enterRemarks("adding remarks");
		baseTrainingOverallOutcomePage.selectQualification("TRI (A)");
		baseTrainingOverallOutcomePage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.reasonForDelayLabelIsPresent();
		baseTrainingOverallOutcomePage.addDelayComments("adding delay comments");
		baseTrainingOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		baseTrainingOverallOutcomePage.visibilityOfPreviewHeader();
		baseTrainingOverallOutcomePage.clickPreviewNextButton();
		baseTrainingOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		baseTrainingOverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		baseTrainingOverallOutcomePage.digitalSignitureLabelIsPresent();
		baseTrainingOverallOutcomePage.digitalSign();
		baseTrainingOverallOutcomePage.clickClearForDigitalSigniture();
		baseTrainingOverallOutcomePage.digitalSign();
		baseTrainingOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		popupPage.handelSpinner();
		traineeGradingPage.validateAllStaticTexts();
	}

	@Test(description = "OK Please enter Registration number.")
	public void validatePleaseEnterRegistrationNumberPopup() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		baseTrainingGradingPage.enterRegistrationNumber("");
		baseTrainingGradingPage.clickSaveAndNextButton();
		String actualResult = popupPage.popupGetText();
		String expectedResult = "OK Please enter Registration number.";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
	}

	@Test(description = "OK Please select from location.")
	public void validatePleaseSelectFromLocationPopup() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("");
		baseTrainingGradingPage.clickSaveAndNextButton();
		String actualResult = popupPage.popupGetText();
		String expectedResult = "OK Please select from location.";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
	}

	@Test(description = "OK Please select to location.")
	public void validatePleaseSelectToLocationPopup() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("VIDD");
		baseTrainingGradingPage.enterTo("");
		baseTrainingGradingPage.clickSaveAndNextButton();
		String actualResult = popupPage.popupGetText();
		String expectedResult = "OK Please select to location.";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
	}

	@Test(description = "OK Please select Aircraft Type.")
	public void validatePleaseSelectAircraftTypePopup() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("VIDD");
		baseTrainingGradingPage.enterTo("DGAA");
		baseTrainingGradingPage.clickSaveAndNextButton();
		String actualResult = popupPage.popupGetText();
		String expectedResult = "OK Please select Aircraft Type.";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
	}

	@Test(description = "OK Please select Seat Occupied.")
	public void validatePleaseSelectSeatOccupiedPopup() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("VIDD");
		baseTrainingGradingPage.enterTo("DGAA");
		baseTrainingGradingPage.selectAircraftType("A320");
		baseTrainingGradingPage.clickSaveAndNextButton();
		String actualResult = popupPage.popupGetText();
		String expectedResult = "OK Please select Seat Occupied.";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
	}

	@Test(description = "OK Please select No. of Take offs and Landings Conducted.")
	public void validatePleaseSelectNumberOfTakeOffsAndLandingsConductedPopup() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("VIDD");
		baseTrainingGradingPage.enterTo("DGAA");
		baseTrainingGradingPage.selectAircraftType("A320");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.clickSaveAndNextButton();
		String actualResult = popupPage.popupGetText();
		String expectedResult = "OK Please select No. of Take offs and Landings Conducted.";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
	}

	@Test(description = "OK 2 Take offs and Landings Conducted.")
	public void validateTwoTakeOffsAndLandingsConductedPopup() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("VIDD");
		baseTrainingGradingPage.enterTo("DGAA");
		baseTrainingGradingPage.selectAircraftType("A320");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		String actualResult = popupPage.popupGetText();
		String expectedResult = "OK 2 Take offs and Landings Conducted.";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
	}

	@Test(description = "OK You must Select General Information")
	public void validateYouMustSelectGeneralInformationPopup() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		baseTrainingGradingPage.clickOverallOutcomeTab();
		String actualResult = popupPage.popupGetText();
		String expectedResult = "OK You must Select General Information";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
	}

	@Test(description = "OK At least one Observable Behaviour must be selected")
	public void validateAtLeastOneObservableBehaviourMustBeSelectedPopup() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("VIDD");
		baseTrainingGradingPage.enterTo("DGAA");
		baseTrainingGradingPage.selectAircraftType("A320");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickGrade("PRO", "2");
		baseTrainingGradingPage.clickOBDoneButton("PRO");
		String actualResult = popupPage.popupGetText();
		String expectedResult = "OK At least one Observable Behaviour must be selected";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
	}

	@Test(description = "OK Please add or change comments")
	public void validatePleaseAddOrChangeCommentsPopup() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("VIDD");
		baseTrainingGradingPage.enterTo("DGAA");
		baseTrainingGradingPage.selectAircraftType("A320");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickGrade("PRO", "2");
		baseTrainingGradingPage.clickAllMinus("PRO");
		baseTrainingGradingPage.clickOBDoneButton("PRO");
		String actualResult = popupPage.popupGetText();
		String expectedResult = "OK Please add or change comments";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
	}

	@Test(description = "OK The Overall Outcome 'NOT YET COMPETENT' has been assigned")
	public void validateTheOverallOutcomeNotYetCompetentHasBeenAssignedPopup() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("VIDD");
		baseTrainingGradingPage.enterTo("DGAA");
		baseTrainingGradingPage.selectAircraftType("A320");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickGrade("PRO", "1");
		baseTrainingGradingPage.clickAllMinus("PRO");
		baseTrainingGradingPage.enterOBComments("PRO", "adding OB comment");
		baseTrainingGradingPage.clickOBDoneButton("PRO");
		String actualResult = popupPage.popupGetText();
		String expectedResult = "OK The Overall Outcome 'NOT YET COMPETENT' has been assigned";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
	}

	@Test(description = "OK Overall comments mandatory for Not Yet Competent outcome.")
	public void validateOverallCommentsMandatoryForNotYetCompetentOutcomePopup() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("VIDD");
		baseTrainingGradingPage.enterTo("DGAA");
		baseTrainingGradingPage.selectAircraftType("A320");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickGrade("PRO", "1");
		baseTrainingGradingPage.clickAllMinus("PRO");
		baseTrainingGradingPage.enterOBComments("PRO", "adding OB comment");
		baseTrainingGradingPage.clickOBDoneButton("PRO");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.validateBaseTrainingOverallOutComePage();
		baseTrainingOverallOutcomePage.enterRemarks("");
		baseTrainingOverallOutcomePage.clickSaveAndNextButton();
		String actualResult = popupPage.popupGetText();
		String expectedResult = "OK Overall comments mandatory for Not Yet Competent outcome.";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
	}

	@Test(description = "OK You must select overall assessment.")
	public void validateYouMustSelectOverallAssessmentPopup() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("VIDD");
		baseTrainingGradingPage.enterTo("DGAA");
		baseTrainingGradingPage.selectAircraftType("A320");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickGrade("PRO", "2");
		baseTrainingGradingPage.clickAllMinus("PRO");
		baseTrainingGradingPage.enterOBComments("PRO", "adding OB comment");
		baseTrainingGradingPage.clickOBDoneButton("PRO");
		baseTrainingGradingPage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.validateBaseTrainingOverallOutComePage();
		baseTrainingOverallOutcomePage.clickSaveAndNextButton();
		String actualResult = popupPage.popupGetText();
		String expectedResult = "OK You must select overall assessment.";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
	}

	@Test(description = "OK Please select qualification.")
	public void validatePleaseSelectQualificationPopup() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("VIDD");
		baseTrainingGradingPage.enterTo("DGAA");
		baseTrainingGradingPage.selectAircraftType("A320");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickGrade("PRO", "2");
		baseTrainingGradingPage.clickAllMinus("PRO");
		baseTrainingGradingPage.enterOBComments("PRO", "adding OB comment");
		baseTrainingGradingPage.clickOBDoneButton("PRO");
		baseTrainingGradingPage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.validateBaseTrainingOverallOutComePage();
		baseTrainingOverallOutcomePage.selectCompetentRadioButton();
		baseTrainingOverallOutcomePage.enterRemarks("adding remarks");
		baseTrainingOverallOutcomePage.clickSaveAndNextButton();
		String actualResult = popupPage.popupGetText();
		String expectedResult = "OK Please select qualification.";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
	}

	@Test(description = "OK Please enter a comment before submitting.")
	public void validatePleaseEnterACommentBeforeSubmittingPopup() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("VIDD");
		baseTrainingGradingPage.enterTo("DGAA");
		baseTrainingGradingPage.selectAircraftType("A320");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickGrade("PRO", "2");
		baseTrainingGradingPage.clickAllMinus("PRO");
		baseTrainingGradingPage.enterOBComments("PRO", "adding OB comment");
		baseTrainingGradingPage.clickOBDoneButton("PRO");
		baseTrainingGradingPage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.validateBaseTrainingOverallOutComePage();
		baseTrainingOverallOutcomePage.selectCompetentRadioButton();
		baseTrainingOverallOutcomePage.enterRemarks("adding remarks");
		baseTrainingOverallOutcomePage.selectQualification("TRI (A)");
		baseTrainingOverallOutcomePage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.reasonForDelayLabelIsPresent();
		baseTrainingOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		String actualResult = popupPage.popupGetText();
		String expectedResult = "OK Please enter a comment before submitting.";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
	}

	@Test(description = "OK Please provide a signature first.")
	public void validatePleaseProvideSignatureFirstPopup() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("VIDD");
		baseTrainingGradingPage.enterTo("DGAA");
		baseTrainingGradingPage.selectAircraftType("A320");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickGrade("PRO", "2");
		baseTrainingGradingPage.clickAllMinus("PRO");
		baseTrainingGradingPage.enterOBComments("PRO", "adding OB comment");
		baseTrainingGradingPage.clickOBDoneButton("PRO");
		baseTrainingGradingPage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.validateBaseTrainingOverallOutComePage();
		baseTrainingOverallOutcomePage.selectCompetentRadioButton();
		baseTrainingOverallOutcomePage.enterRemarks("adding remarks");
		baseTrainingOverallOutcomePage.selectQualification("TRI (A)");
		baseTrainingOverallOutcomePage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.reasonForDelayLabelIsPresent();
		baseTrainingOverallOutcomePage.addDelayComments("adding delay comments");
		baseTrainingOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		baseTrainingOverallOutcomePage.visibilityOfPreviewHeader();
		baseTrainingOverallOutcomePage.clickPreviewNextButton();
		baseTrainingOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		baseTrainingOverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		baseTrainingOverallOutcomePage.digitalSignitureLabelIsPresent();
		baseTrainingOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		String actualResult = popupPage.popupGetText();
		String expectedResult = "OK Please provide a signature first.";
		Assert.assertEquals(actualResult, expectedResult,
				"Text mismatch expected : " + expectedResult + " but got : " + actualResult);
	}

	@Test(description = "Validate Auto Selection of the Not yet Competent on selecting the Grade as 1")
	public void validateAutoSelectionOfNotYetCompetentOnSelectingGradeAsOne() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("VIDD");
		baseTrainingGradingPage.enterTo("DGAA");
		baseTrainingGradingPage.selectAircraftType("A320");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickGrade("PRO", "1");
		baseTrainingGradingPage.clickAllMinus("PRO");
		baseTrainingGradingPage.enterOBComments("PRO", "adding OB comment");
		baseTrainingGradingPage.clickOBDoneButton("PRO");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickSaveAndNextButton();
		Assert.assertTrue(baseTrainingOverallOutcomePage.notYetCompetentIsSelected(),
				"Expected Not Yet Competent is auto selected");
		Assert.assertFalse(baseTrainingOverallOutcomePage.competentIsSelected(), "Expected Competent is not selected");
	}

	@Test(description = "Validate Auto Selection of the Competent on selecting the Grade as 3,4,5")
	public void validateAutoSelectionOfCompetentOnSelectingGradeAsThree() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("VIDD");
		baseTrainingGradingPage.enterTo("DGAA");
		baseTrainingGradingPage.selectAircraftType("A320");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickGrade("PRO", "3");
		baseTrainingGradingPage.clickAllMinus("PRO");
		baseTrainingGradingPage.enterOBComments("PRO", "adding OB comment");
		baseTrainingGradingPage.clickOBDoneButton("PRO");
		baseTrainingGradingPage.clickSaveAndNextButton();
		Assert.assertTrue(baseTrainingOverallOutcomePage.competentIsSelected(), "Expected Competent is auto selected");
		Assert.assertFalse(baseTrainingOverallOutcomePage.notYetCompetentIsSelected(),
				"Expected Not Yet Competent is not selected");
	}

	@Test(description = "Validate trainer can access submitted form through Grading History")
	public void validateTrainerCanAccessSubmittedFormThroughGradingHistory() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = baseTrainingGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("LSGG");
		baseTrainingGradingPage.enterTo("VANP");
		baseTrainingGradingPage.selectAircraftType("A321");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.validateBaseTrainingOverallOutComePage();
		baseTrainingOverallOutcomePage.selectQualification("TRI (A)");
		baseTrainingOverallOutcomePage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.reasonForDelayLabelIsPresent();
		baseTrainingOverallOutcomePage.addDelayComments("adding delay comments");
		baseTrainingOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		baseTrainingOverallOutcomePage.visibilityOfPreviewHeader();
		baseTrainingOverallOutcomePage.clickPreviewNextButton();
		baseTrainingOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		baseTrainingOverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		baseTrainingOverallOutcomePage.digitalSignitureLabelIsPresent();
		baseTrainingOverallOutcomePage.digitalSign();
		baseTrainingOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		popupPage.handelSpinner();
		traineeGradingPage.validateAllStaticTexts();
		gradingHistoryPage.clickGradingHistoryButton();
		gradingHistoryPage.validateAllStaticTexts();
		gradingHistoryPage.enterLessonName(traineeId);
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
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = baseTrainingGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("LSGG");
		baseTrainingGradingPage.enterTo("VANP");
		baseTrainingGradingPage.selectAircraftType("A321");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.validateBaseTrainingOverallOutComePage();
		baseTrainingOverallOutcomePage.selectQualification("TRI (A)");
		baseTrainingOverallOutcomePage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.reasonForDelayLabelIsPresent();
		baseTrainingOverallOutcomePage.addDelayComments("adding delay comments");
		baseTrainingOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		baseTrainingOverallOutcomePage.visibilityOfPreviewHeader();
		baseTrainingOverallOutcomePage.clickPreviewNextButton();
		baseTrainingOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		baseTrainingOverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		baseTrainingOverallOutcomePage.digitalSignitureLabelIsPresent();
		baseTrainingOverallOutcomePage.digitalSign();
		baseTrainingOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		popupPage.handelSpinner();
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
	}

	@Test(description = "Validate trainee can mark the submitted form for review")
	public void validateTraineeCanMarkFormForReview() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = baseTrainingGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("LSGG");
		baseTrainingGradingPage.enterTo("VANP");
		baseTrainingGradingPage.selectAircraftType("A321");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.validateBaseTrainingOverallOutComePage();
		baseTrainingOverallOutcomePage.selectQualification("TRI (A)");
		baseTrainingOverallOutcomePage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.reasonForDelayLabelIsPresent();
		baseTrainingOverallOutcomePage.addDelayComments("adding delay comments");
		baseTrainingOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		baseTrainingOverallOutcomePage.visibilityOfPreviewHeader();
		baseTrainingOverallOutcomePage.clickPreviewNextButton();
		baseTrainingOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		baseTrainingOverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		baseTrainingOverallOutcomePage.digitalSignitureLabelIsPresent();
		baseTrainingOverallOutcomePage.digitalSign();
		baseTrainingOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		popupPage.handelSpinner();
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
		traineeReviewPage.clickMarkForReviewButton();
		String actualText = traineeReviewPage.getMarkForReviewConfirmationText();
		String expectedText = "Are you sure you want to mark this event for review by trainer ?";
		Assert.assertEquals(actualText, expectedText,
				"Text missmatch" + expectedText + "is expected but found " + actualText);
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
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = baseTrainingGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("LSGG");
		baseTrainingGradingPage.enterTo("VANP");
		baseTrainingGradingPage.selectAircraftType("A321");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.validateBaseTrainingOverallOutComePage();
		baseTrainingOverallOutcomePage.selectQualification("TRI (A)");
		baseTrainingOverallOutcomePage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.reasonForDelayLabelIsPresent();
		baseTrainingOverallOutcomePage.addDelayComments("adding delay comments");
		baseTrainingOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		baseTrainingOverallOutcomePage.visibilityOfPreviewHeader();
		baseTrainingOverallOutcomePage.clickPreviewNextButton();
		baseTrainingOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		baseTrainingOverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		baseTrainingOverallOutcomePage.digitalSignitureLabelIsPresent();
		baseTrainingOverallOutcomePage.digitalSign();
		baseTrainingOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		popupPage.handelSpinner();
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
		traineeReviewPage.clickMarkForReviewButton();
		String actualText = traineeReviewPage.getMarkForReviewConfirmationText();
		String expectedText = "Are you sure you want to mark this event for review by trainer ?";
		Assert.assertEquals(actualText, expectedText,
				"Text Mismatch" + expectedText + "is expected but found " + actualText);
		traineeReviewPage.clickNoButton();
		traineeReviewPage.clickMarkForReviewButton();
		traineeReviewPage.clickYesButton();
		traineeReviewPage.validateAllStaticElements();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.validatePengingGradingStaticTexts();
		pendingHistoryPage.enterSearchText(traineeId);
		pendingHistoryPage.clickReviewButton();
		baseTrainingGradingPage.selectAircraftType("A320");
		baseTrainingGradingPage.selectSeatOccupied("RHS");
		baseTrainingGradingPage.clickSaveAndNextButton();
		pendingHistoryPage.validateUpdateGeneralInfoPopupLabelText();
		pendingHistoryPage.clickUpdateGeneralInfoPopupNoButton();
		baseTrainingGradingPage.clickSaveAndNextButton();
		pendingHistoryPage.clickUpdateGeneralInfoPopupYesButton();
	}

	@Test(description = "Validate trainee cannot mark the form for review after it has been reviewed and submitted by trainer")
	public void validateTraineeCannotMarkFormForReviewAfterTrainerSubmission() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = baseTrainingGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("LSGG");
		baseTrainingGradingPage.enterTo("VANP");
		baseTrainingGradingPage.selectAircraftType("A321");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.validateBaseTrainingOverallOutComePage();
		baseTrainingOverallOutcomePage.selectQualification("TRI (A)");
		baseTrainingOverallOutcomePage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.reasonForDelayLabelIsPresent();
		baseTrainingOverallOutcomePage.addDelayComments("adding delay comments");
		baseTrainingOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		baseTrainingOverallOutcomePage.visibilityOfPreviewHeader();
		baseTrainingOverallOutcomePage.clickPreviewNextButton();
		baseTrainingOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		baseTrainingOverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		baseTrainingOverallOutcomePage.digitalSignitureLabelIsPresent();
		baseTrainingOverallOutcomePage.digitalSign();
		baseTrainingOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		popupPage.handelSpinner();
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
		traineeReviewPage.clickMarkForReviewButton();
		String actualText = traineeReviewPage.getMarkForReviewConfirmationText();
		String expectedText = "Are you sure you want to mark this event for review by trainer ?";
		Assert.assertEquals(actualText, expectedText,
				"Text Mismatch" + expectedText + "is expected but found " + actualText);
		traineeReviewPage.clickNoButton();
		traineeReviewPage.clickMarkForReviewButton();
		traineeReviewPage.clickYesButton();
		traineeReviewPage.validateAllStaticElements();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.validatePengingGradingStaticTexts();
		pendingHistoryPage.enterSearchText(traineeId);
		pendingHistoryPage.clickReviewButton();
		baseTrainingGradingPage.selectAircraftType("A320");
		baseTrainingGradingPage.selectSeatOccupied("RHS");
		baseTrainingGradingPage.clickSaveAndNextButton();
		pendingHistoryPage.validateUpdateGeneralInfoPopupLabelText();
		pendingHistoryPage.clickUpdateGeneralInfoPopupNoButton();
		baseTrainingGradingPage.clickSaveAndNextButton();
		pendingHistoryPage.clickUpdateGeneralInfoPopupYesButton();
		traineeGradingPage.validateAllStaticTexts();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendTraineeId(traineeId);
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
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = baseTrainingGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("LSGG");
		baseTrainingGradingPage.enterTo("VANP");
		baseTrainingGradingPage.selectAircraftType("A321");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.validateBaseTrainingOverallOutComePage();
		baseTrainingOverallOutcomePage.selectQualification("TRI (A)");
		baseTrainingOverallOutcomePage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.reasonForDelayLabelIsPresent();
		baseTrainingOverallOutcomePage.addDelayComments("adding delay comments");
		baseTrainingOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		baseTrainingOverallOutcomePage.visibilityOfPreviewHeader();
		baseTrainingOverallOutcomePage.clickPreviewNextButton();
		baseTrainingOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		baseTrainingOverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		baseTrainingOverallOutcomePage.digitalSignitureLabelIsPresent();
		baseTrainingOverallOutcomePage.digitalSign();
		baseTrainingOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		popupPage.handelSpinner();
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
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = baseTrainingGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("LSGG");
		baseTrainingGradingPage.enterTo("VANP");
		baseTrainingGradingPage.selectAircraftType("A321");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.validateBaseTrainingOverallOutComePage();
		baseTrainingOverallOutcomePage.selectQualification("TRI (A)");
		baseTrainingOverallOutcomePage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.reasonForDelayLabelIsPresent();
		baseTrainingOverallOutcomePage.addDelayComments("adding delay comments");
		baseTrainingOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		baseTrainingOverallOutcomePage.visibilityOfPreviewHeader();
		baseTrainingOverallOutcomePage.clickPreviewNextButton();
		baseTrainingOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		baseTrainingOverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		baseTrainingOverallOutcomePage.digitalSignitureLabelIsPresent();
		baseTrainingOverallOutcomePage.digitalSign();
		baseTrainingOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		popupPage.handelSpinner();
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
		popupPage.handelSpinner();
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
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = baseTrainingGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		System.out.println(traineeId);
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("LSGG");
		baseTrainingGradingPage.enterTo("VANP");
		baseTrainingGradingPage.selectAircraftType("A321");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.validateBaseTrainingOverallOutComePage();
		baseTrainingOverallOutcomePage.selectQualification("TRI (A)");
		baseTrainingOverallOutcomePage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.reasonForDelayLabelIsPresent();
		baseTrainingOverallOutcomePage.addDelayComments("adding delay comments");
		baseTrainingOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		baseTrainingOverallOutcomePage.visibilityOfPreviewHeader();
		baseTrainingOverallOutcomePage.clickPreviewNextButton();
		baseTrainingOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		baseTrainingOverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		baseTrainingOverallOutcomePage.digitalSignitureLabelIsPresent();
		baseTrainingOverallOutcomePage.digitalSign();
		baseTrainingOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		popupPage.handelSpinner();
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
		popupPage.handelSpinner();
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
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = baseTrainingGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("LSGG");
		baseTrainingGradingPage.enterTo("VANP");
		baseTrainingGradingPage.selectAircraftType("A321");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.validateBaseTrainingOverallOutComePage();
		baseTrainingOverallOutcomePage.selectQualification("TRI (A)");
		baseTrainingOverallOutcomePage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.reasonForDelayLabelIsPresent();
		baseTrainingOverallOutcomePage.addDelayComments("adding delay comments");
		baseTrainingOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		baseTrainingOverallOutcomePage.visibilityOfPreviewHeader();
		baseTrainingOverallOutcomePage.clickPreviewNextButton();
		baseTrainingOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		baseTrainingOverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		baseTrainingOverallOutcomePage.digitalSignitureLabelIsPresent();
		baseTrainingOverallOutcomePage.digitalSign();
		baseTrainingOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		popupPage.handelSpinner();
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
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = baseTrainingGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("LSGG");
		baseTrainingGradingPage.enterTo("VANP");
		baseTrainingGradingPage.selectAircraftType("A321");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.validateBaseTrainingOverallOutComePage();
		baseTrainingOverallOutcomePage.selectQualification("TRI (A)");
		baseTrainingOverallOutcomePage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.reasonForDelayLabelIsPresent();
		baseTrainingOverallOutcomePage.addDelayComments("adding delay comments");
		baseTrainingOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		baseTrainingOverallOutcomePage.visibilityOfPreviewHeader();
		baseTrainingOverallOutcomePage.clickPreviewNextButton();
		baseTrainingOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		baseTrainingOverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		baseTrainingOverallOutcomePage.digitalSignitureLabelIsPresent();
		baseTrainingOverallOutcomePage.digitalSign();
		baseTrainingOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		popupPage.handelSpinner();
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
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = baseTrainingGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("LSGG");
		baseTrainingGradingPage.enterTo("VANP");
		baseTrainingGradingPage.selectAircraftType("A321");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.validateBaseTrainingOverallOutComePage();
		baseTrainingOverallOutcomePage.selectQualification("TRI (A)");
		baseTrainingOverallOutcomePage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.reasonForDelayLabelIsPresent();
		baseTrainingOverallOutcomePage.addDelayComments("adding delay comments");
		baseTrainingOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		baseTrainingOverallOutcomePage.visibilityOfPreviewHeader();
		baseTrainingOverallOutcomePage.clickPreviewNextButton();
		baseTrainingOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		baseTrainingOverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		baseTrainingOverallOutcomePage.digitalSignitureLabelIsPresent();
		baseTrainingOverallOutcomePage.digitalSign();
		baseTrainingOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		popupPage.handelSpinner();
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
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = baseTrainingGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("LSGG");
		baseTrainingGradingPage.enterTo("VANP");
		baseTrainingGradingPage.selectAircraftType("A321");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.validateBaseTrainingOverallOutComePage();
		baseTrainingOverallOutcomePage.selectQualification("TRI (A)");
		baseTrainingOverallOutcomePage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.reasonForDelayLabelIsPresent();
		baseTrainingOverallOutcomePage.addDelayComments("adding delay comments");
		baseTrainingOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		baseTrainingOverallOutcomePage.visibilityOfPreviewHeader();
		baseTrainingOverallOutcomePage.clickPreviewNextButton();
		baseTrainingOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		baseTrainingOverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		baseTrainingOverallOutcomePage.digitalSignitureLabelIsPresent();
		baseTrainingOverallOutcomePage.digitalSign();
		baseTrainingOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		popupPage.handelSpinner();
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
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		baseTrainingGradingPage.validateLessonName();
		baseTrainingGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = baseTrainingGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		baseTrainingGradingPage.enterRegistrationNumber("Test User");
		baseTrainingGradingPage.enterFrom("LSGG");
		baseTrainingGradingPage.enterTo("VANP");
		baseTrainingGradingPage.selectAircraftType("A321");
		baseTrainingGradingPage.selectSeatOccupied("LHS");
		baseTrainingGradingPage.selectTakeOffAndLandingsConducted("2");
		popupPage.clickPopupOkButton();
		baseTrainingGradingPage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.validateBaseTrainingOverallOutComePage();
		baseTrainingOverallOutcomePage.selectQualification("TRI (A)");
		baseTrainingOverallOutcomePage.clickSaveAndNextButton();
		baseTrainingOverallOutcomePage.reasonForDelayLabelIsPresent();
		baseTrainingOverallOutcomePage.addDelayComments("adding delay comments");
		baseTrainingOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		baseTrainingOverallOutcomePage.visibilityOfPreviewHeader();
		baseTrainingOverallOutcomePage.clickPreviewNextButton();
		baseTrainingOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		baseTrainingOverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		baseTrainingOverallOutcomePage.digitalSignitureLabelIsPresent();
		baseTrainingOverallOutcomePage.digitalSign();
		baseTrainingOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickAlertOkButton();
		popupPage.handelSpinner();
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
