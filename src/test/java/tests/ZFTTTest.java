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
import commonPages.GradingHistoryPage;
import commonPages.LogoutPage;
import commonPages.PendingHistoryPage;
import commonPages.PopupPage;
import commonPages.TraineeGradingPage;
import commonPages.TrainerDashBoradPage;
import traineePages.TraineeReviewPage;
import zfttPages.GradingTraineeListPage;
import zfttPages.ZFTTGradingPage;
import zfttPages.ZFTTOverallOutcomePage;

public class ZFTTTest extends BaseClass {
	private TrainerDashBoradPage trainerDashBoradPage;
	private TraineeGradingPage traineeGradingPage;
	private BecomeUserPage becomeUserPage;
	private AdminDashBoardPage adminDashBoardPage;
	private GradingHistoryPage gradingHistoryPage;
	private TraineeReviewPage traineeReviewPage;
	private LogoutPage logoutPage;
	private PendingHistoryPage pendingHistoryPage;
	private PopupPage popupPage;
	private TrainingManagerReviewPage trainingManagerReviewPage;
	private EFormReportsPage eFormReportsPage;
	private UserDocsPage userDocsPage;
	private String traineeIdWithName;
	private String traineeId;
	private GradingTraineeListPage gradingTraineeListPage;
	private ZFTTGradingPage zfttGradingPage;
	private ZFTTOverallOutcomePage zfttOverallOutcomePage;

	@BeforeMethod(alwaysRun = true)
	public void initPages() {
		adminDashBoardPage = new AdminDashBoardPage(getDriver());
		becomeUserPage = new BecomeUserPage(getDriver());
		trainerDashBoradPage = new TrainerDashBoradPage(getDriver());
		traineeGradingPage = new TraineeGradingPage(getDriver());
		traineeReviewPage = new TraineeReviewPage(getDriver());
		logoutPage = new LogoutPage(getDriver());
		trainingManagerReviewPage = new TrainingManagerReviewPage(getDriver());
		gradingHistoryPage = new GradingHistoryPage(getDriver());
		popupPage = new PopupPage(getDriver());
		userDocsPage = new UserDocsPage(getDriver());
		eFormReportsPage = new EFormReportsPage(getDriver());
		pendingHistoryPage = new PendingHistoryPage(getDriver());
		gradingTraineeListPage = new GradingTraineeListPage(getDriver());
		zfttGradingPage = new ZFTTGradingPage(getDriver());
		zfttOverallOutcomePage = new ZFTTOverallOutcomePage(getDriver());
	}

	@Test(description = "ZFTT Form e2e Happy Path test case")
	public void happyPathZFTTTest() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		zfttGradingPage.validateAllBaseTrainingGradingPageTexts();
		zfttGradingPage.enterRegistrationNumber("Test User");
		zfttGradingPage.selectLocation("BLR");
		zfttGradingPage.selectSeatOccupied("LHS");
		zfttGradingPage.clickSaveAndNextButton();
		zfttOverallOutcomePage.validateZFTTOverallOutComePage();
		zfttOverallOutcomePage.clickGrade("PRO", "2");
		zfttOverallOutcomePage.clickAllPlus("PRO");
		zfttOverallOutcomePage.enterOBComments("PRO", "adding OB comment");
		zfttOverallOutcomePage.clickOBDoneButton("PRO");
		zfttOverallOutcomePage.selectCompetentRadioButton();
		zfttOverallOutcomePage.enterRemarks("adding remarks");
		zfttOverallOutcomePage.selectTakeOffAndLandingsConducted("3");
		zfttOverallOutcomePage.selectQualification("SFI");
		zfttOverallOutcomePage.clickSaveAndNextButton();
		zfttOverallOutcomePage.reasonForDelayLabelIsPresent();
		zfttOverallOutcomePage.addDelayComments("adding delay comments");
		zfttOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		zfttOverallOutcomePage.visibilityOfPreviewHeader();
		zfttOverallOutcomePage.clickPreviewNextButton();
		zfttOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		zfttOverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		zfttOverallOutcomePage.digitalSignitureLabelIsPresent();
		zfttOverallOutcomePage.digitalSign();
		zfttOverallOutcomePage.clickClearForDigitalSigniture();
		zfttOverallOutcomePage.digitalSign();
		zfttOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickPopupOkButton();
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
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		zfttGradingPage.validateAllBaseTrainingGradingPageTexts();
		zfttGradingPage.enterRegistrationNumber("");
		zfttGradingPage.clickSaveAndNextButton();
		String expectedResult = "OK Please enter Registration number.";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch expected is : " + expectedResult + " but got : " + actualResult);
	}

	@Test(description = "OK Please select location.")
	public void validatePleaseSelectLocationPopup() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		zfttGradingPage.validateAllBaseTrainingGradingPageTexts();
		zfttGradingPage.enterRegistrationNumber("Test User");
		zfttGradingPage.selectLocation("select");
		zfttGradingPage.clickSaveAndNextButton();
		String expectedResult = "OK Please select location.";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch expected is : " + expectedResult + " but got : " + actualResult);
	}

	@Test(description = "OK Please enter duration.")
	public void validatePleaseEnterDurationPopup() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		zfttGradingPage.validateAllBaseTrainingGradingPageTexts();
		zfttGradingPage.enterRegistrationNumber("Test User");
		zfttGradingPage.selectLocation("BLR");
		zfttGradingPage.enterDuration("");
		zfttGradingPage.clickSaveAndNextButton();
		String expectedResult = "OK Please enter duration.";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch expected is : " + expectedResult + " but got : " + actualResult);
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
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		zfttGradingPage.validateAllBaseTrainingGradingPageTexts();
		zfttGradingPage.enterRegistrationNumber("Test User");
		zfttGradingPage.selectLocation("BLR");
		zfttGradingPage.selectSeatOccupied(0);
		zfttGradingPage.clickSaveAndNextButton();
		String expectedResult = "OK Please select Seat Occupied.";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch expected is : " + expectedResult + " but got : " + actualResult);
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
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		zfttGradingPage.validateAllBaseTrainingGradingPageTexts();
		zfttGradingPage.enterRegistrationNumber("Test User");
		zfttGradingPage.selectLocation("BLR");
		zfttGradingPage.selectSeatOccupied("LHS");
		zfttGradingPage.clickSaveAndNextButton();
		zfttOverallOutcomePage.validateZFTTOverallOutComePage();
		zfttOverallOutcomePage.clickGrade("PRO", "2");
		zfttOverallOutcomePage.clickAllPlus("PRO");
		zfttOverallOutcomePage.enterOBComments("PRO", "adding OB comment");
		zfttOverallOutcomePage.clickOBDoneButton("PRO");
		zfttOverallOutcomePage.clickSaveAndNextButton();
		String expectedResult = "OK You must select overall assessment.";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch expected is : " + expectedResult + " but got : " + actualResult);
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
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		zfttGradingPage.validateAllBaseTrainingGradingPageTexts();
		zfttGradingPage.enterRegistrationNumber("Test User");
		zfttGradingPage.selectLocation("BLR");
		zfttGradingPage.selectSeatOccupied("LHS");
		zfttGradingPage.clickSaveAndNextButton();
		zfttOverallOutcomePage.validateZFTTOverallOutComePage();
		zfttOverallOutcomePage.clickGrade("PRO", "1");
		zfttOverallOutcomePage.clickAllPlus("PRO");
		zfttOverallOutcomePage.enterOBComments("PRO", "adding OB comment");
		zfttOverallOutcomePage.clickOBDoneButton("PRO");
		String expectedResult = "OK The Overall Outcome 'NOT YET COMPETENT' has been assigned";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch expected is : " + expectedResult + " but got : " + actualResult);
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
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		zfttGradingPage.validateAllBaseTrainingGradingPageTexts();
		zfttGradingPage.enterRegistrationNumber("Test User");
		zfttGradingPage.selectLocation("BLR");
		zfttGradingPage.selectSeatOccupied("LHS");
		zfttGradingPage.clickSaveAndNextButton();
		zfttOverallOutcomePage.validateZFTTOverallOutComePage();
		zfttOverallOutcomePage.clickGrade("PRO", "1");
		zfttOverallOutcomePage.clickOBDoneButton("PRO");
		String expectedResult = "OK At least one Observable Behaviour must be selected";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch expected is : " + expectedResult + " but got : " + actualResult);
	}

	@Test(description = "OK Please enter or change comments")
	public void validatePleaseEnterOrChangeCommentsPopup() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		zfttGradingPage.validateAllBaseTrainingGradingPageTexts();
		zfttGradingPage.enterRegistrationNumber("Test User");
		zfttGradingPage.selectLocation("BLR");
		zfttGradingPage.selectSeatOccupied("LHS");
		zfttGradingPage.clickSaveAndNextButton();
		zfttOverallOutcomePage.validateZFTTOverallOutComePage();
		zfttOverallOutcomePage.clickGrade("PRO", "1");
		zfttOverallOutcomePage.clickAllPlus("PRO");
		zfttOverallOutcomePage.clickOBDoneButton("PRO");
		String expectedResult = "OK Please enter or change comments";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch expected is : " + expectedResult + " but got : " + actualResult);
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
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		zfttGradingPage.validateAllBaseTrainingGradingPageTexts();
		zfttGradingPage.enterRegistrationNumber("Test User");
		zfttGradingPage.selectLocation("BLR");
		zfttGradingPage.selectSeatOccupied("LHS");
		zfttGradingPage.clickSaveAndNextButton();
		zfttOverallOutcomePage.validateZFTTOverallOutComePage();
		zfttOverallOutcomePage.clickGrade("PRO", "3");
		zfttOverallOutcomePage.clickAllPlus("PRO");
		zfttOverallOutcomePage.enterOBComments("PRO", "adding OB comment");
		zfttOverallOutcomePage.clickOBDoneButton("PRO");
//		zfttOverallOutcomePage.selectCompetentRadioButton();
//		zfttOverallOutcomePage.enterRemarks("adding remarks");
//		zfttOverallOutcomePage.selectTakeOffAndLandingsConducted("3");
//		zfttOverallOutcomePage.selectQualification("SFI");
//		zfttOverallOutcomePage.clickSaveAndNextButton();
//		zfttOverallOutcomePage.reasonForDelayLabelIsPresent();
//		zfttOverallOutcomePage.addDelayComments("adding delay comments");
//		zfttOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
//		popupPage.handelSpinner();
		String expectedResult = "OK Please enter or change comments";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch expected is : " + expectedResult + " but got : " + actualResult);
//		zfttOverallOutcomePage.visibilityOfPreviewHeader();
//		zfttOverallOutcomePage.clickPreviewNextButton();
//		zfttOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
//		zfttOverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
//		zfttOverallOutcomePage.digitalSignitureLabelIsPresent();
//		zfttOverallOutcomePage.digitalSign();
//		zfttOverallOutcomePage.clickClearForDigitalSigniture();
//		zfttOverallOutcomePage.digitalSign();
//		zfttOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
//		popupPage.clickPopupOkButton();
//		popupPage.handelSpinner();
//		traineeGradingPage.validateAllStaticTexts();
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
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		zfttGradingPage.validateAllBaseTrainingGradingPageTexts();
		zfttGradingPage.enterRegistrationNumber("Test User");
		zfttGradingPage.selectLocation("BLR");
		zfttGradingPage.selectSeatOccupied("LHS");
		zfttGradingPage.clickSaveAndNextButton();
		zfttOverallOutcomePage.validateZFTTOverallOutComePage();
		zfttOverallOutcomePage.clickGrade("PRO", "1");
		zfttOverallOutcomePage.clickAllPlus("PRO");
		zfttOverallOutcomePage.enterOBComments("PRO", "adding OB comment");
		zfttOverallOutcomePage.clickOBDoneButton("PRO");
//		zfttOverallOutcomePage.selectCompetentRadioButton();
		zfttOverallOutcomePage.enterRemarks("");
//		zfttOverallOutcomePage.selectTakeOffAndLandingsConducted("3");
//		zfttOverallOutcomePage.selectQualification("SFI");
//		zfttOverallOutcomePage.clickSaveAndNextButton();
//		zfttOverallOutcomePage.reasonForDelayLabelIsPresent();
//		zfttOverallOutcomePage.addDelayComments("adding delay comments");
//		zfttOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
//		popupPage.handelSpinner();
		String expectedResult = "OK Overall comments mandatory for Not Yet Competent outcome.";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch expected is : " + expectedResult + " but got : " + actualResult);
//		zfttOverallOutcomePage.visibilityOfPreviewHeader();
//		zfttOverallOutcomePage.clickPreviewNextButton();
//		zfttOverallOutcomePage.instructorAcknowldgementLabelIsPresent();
//		zfttOverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
//		zfttOverallOutcomePage.digitalSignitureLabelIsPresent();
//		zfttOverallOutcomePage.digitalSign();
//		zfttOverallOutcomePage.clickClearForDigitalSigniture();
//		zfttOverallOutcomePage.digitalSign();
//		zfttOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
//		popupPage.clickPopupOkButton();
//		popupPage.handelSpinner();
//		traineeGradingPage.validateAllStaticTexts();
	}
}
