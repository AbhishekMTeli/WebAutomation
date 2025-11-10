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
		zfttOverallOutcomePage.clickSaveAndNextButton();
		String expectedResult = "OK Please select qualification.";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch expected is : " + expectedResult + " but got : " + actualResult);
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
		popupPage.clickPopupOkButton();
		zfttOverallOutcomePage.enterRemarks("");
		zfttOverallOutcomePage.clickSaveAndNextButton();
		String expectedResult = "OK Overall comments mandatory for Not Yet Competent outcome.";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch expected is : " + expectedResult + " but got : " + actualResult);
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
		zfttOverallOutcomePage.enterRemarks("entering remarks");
		zfttOverallOutcomePage.selectQualification("SFI");
		zfttOverallOutcomePage.clickSaveAndNextButton();
		zfttOverallOutcomePage.reasonForDelayLabelIsPresent();
		zfttOverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		String expectedResult = "OK Please enter a comment before submitting.";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch expected is : " + expectedResult + " but got : " + actualResult);
	}

	@Test(description = "OK Please provide a signature first.")
	public void validatePleaseProvideASignitureFirstPopup() throws InterruptedException {
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
		zfttOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		String expectedResult = "OK Please provide a signature first.";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch expected is : " + expectedResult + " but got : " + actualResult);
	}

	@Test(description = "validate auto selection of not yet competent on selecting grade as one")
	public void validateAutoSelectionOfNotYetCompetentOnSelectingGradeAsOne() throws InterruptedException {
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
		popupPage.clickPopupOkButton();
		Assert.assertTrue(zfttOverallOutcomePage.notYetCompetentIsSelected(),
				"Expected Not Yet Competent should be auto selected");
		Assert.assertFalse(zfttOverallOutcomePage.competentIsSelected(),
				"Expected Competent should be auto desabled and not selected");
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
		Assert.assertTrue(zfttOverallOutcomePage.competentIsSelected(), "Expected Competent should be auto selected");
		Assert.assertFalse(zfttOverallOutcomePage.notYetCompetentIsSelected(),
				"Expected Not Yet Competent should be auto desabled and not selected");
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
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		zfttGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = zfttGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		zfttGradingPage.enterRegistrationNumber("Test User");
		zfttGradingPage.selectLocation("BLR");
		zfttGradingPage.selectSeatOccupied("LHS");
		zfttGradingPage.clickSaveAndNextButton();
		zfttOverallOutcomePage.validateZFTTOverallOutComePage();
		zfttOverallOutcomePage.clickGrade("PRO", "3");
		zfttOverallOutcomePage.clickAllPlus("PRO");
		zfttOverallOutcomePage.enterOBComments("PRO", "adding OB comment");
		zfttOverallOutcomePage.clickOBDoneButton("PRO");
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
		zfttOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickPopupOkButton();
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
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		zfttGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = zfttGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		zfttGradingPage.enterRegistrationNumber("Test User");
		zfttGradingPage.selectLocation("BLR");
		zfttGradingPage.selectSeatOccupied("LHS");
		zfttGradingPage.clickSaveAndNextButton();
		zfttOverallOutcomePage.validateZFTTOverallOutComePage();
		zfttOverallOutcomePage.clickGrade("PRO", "3");
		zfttOverallOutcomePage.clickAllPlus("PRO");
		zfttOverallOutcomePage.enterOBComments("PRO", "adding OB comment");
		zfttOverallOutcomePage.clickOBDoneButton("PRO");
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
		zfttOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickPopupOkButton();
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
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		zfttGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = zfttGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		zfttGradingPage.enterRegistrationNumber("Test User");
		zfttGradingPage.selectLocation("BLR");
		zfttGradingPage.selectSeatOccupied("LHS");
		zfttGradingPage.clickSaveAndNextButton();
		zfttOverallOutcomePage.validateZFTTOverallOutComePage();
		zfttOverallOutcomePage.clickGrade("PRO", "3");
		zfttOverallOutcomePage.clickAllPlus("PRO");
		zfttOverallOutcomePage.enterOBComments("PRO", "adding OB comment");
		zfttOverallOutcomePage.clickOBDoneButton("PRO");
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
		zfttOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickPopupOkButton();
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
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		zfttGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = zfttGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		zfttGradingPage.enterRegistrationNumber("Test User");
		zfttGradingPage.selectLocation("BLR");
		zfttGradingPage.selectSeatOccupied("LHS");
		zfttGradingPage.clickSaveAndNextButton();
		zfttOverallOutcomePage.validateZFTTOverallOutComePage();
		zfttOverallOutcomePage.clickGrade("PRO", "3");
		zfttOverallOutcomePage.clickAllPlus("PRO");
		zfttOverallOutcomePage.enterOBComments("PRO", "adding OB comment");
		zfttOverallOutcomePage.clickOBDoneButton("PRO");
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
		zfttOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickPopupOkButton();
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
		zfttOverallOutcomePage.selecteQualificationInreviewPage("TRI (A)");
		zfttGradingPage.clickSaveAndNextButton();
		pendingHistoryPage.validateUpdateGeneralInfoPopupLabelText();
		pendingHistoryPage.clickUpdateGeneralInfoPopupNoButton();
		zfttGradingPage.clickSaveAndNextButton();
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
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		zfttGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = zfttGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		zfttGradingPage.enterRegistrationNumber("Test User");
		zfttGradingPage.selectLocation("BLR");
		zfttGradingPage.selectSeatOccupied("LHS");
		zfttGradingPage.clickSaveAndNextButton();
		zfttOverallOutcomePage.validateZFTTOverallOutComePage();
		zfttOverallOutcomePage.clickGrade("PRO", "3");
		zfttOverallOutcomePage.clickAllPlus("PRO");
		zfttOverallOutcomePage.enterOBComments("PRO", "adding OB comment");
		zfttOverallOutcomePage.clickOBDoneButton("PRO");
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
		zfttOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickPopupOkButton();
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
		zfttOverallOutcomePage.selecteQualificationInreviewPage("TRI (A)");
		zfttGradingPage.clickSaveAndNextButton();
		pendingHistoryPage.validateUpdateGeneralInfoPopupLabelText();
		pendingHistoryPage.clickUpdateGeneralInfoPopupNoButton();
		zfttGradingPage.clickSaveAndNextButton();
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
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		zfttGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = zfttGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		zfttGradingPage.enterRegistrationNumber("Test User");
		zfttGradingPage.selectLocation("BLR");
		zfttGradingPage.selectSeatOccupied("LHS");
		zfttGradingPage.clickSaveAndNextButton();
		zfttOverallOutcomePage.validateZFTTOverallOutComePage();
		zfttOverallOutcomePage.clickGrade("PRO", "3");
		zfttOverallOutcomePage.clickAllPlus("PRO");
		zfttOverallOutcomePage.enterOBComments("PRO", "adding OB comment");
		zfttOverallOutcomePage.clickOBDoneButton("PRO");
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
		zfttOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickPopupOkButton();
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
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		zfttGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = zfttGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		zfttGradingPage.enterRegistrationNumber("Test User");
		zfttGradingPage.selectLocation("BLR");
		zfttGradingPage.selectSeatOccupied("LHS");
		zfttGradingPage.clickSaveAndNextButton();
		zfttOverallOutcomePage.validateZFTTOverallOutComePage();
		zfttOverallOutcomePage.clickGrade("PRO", "3");
		zfttOverallOutcomePage.clickAllPlus("PRO");
		zfttOverallOutcomePage.enterOBComments("PRO", "adding OB comment");
		zfttOverallOutcomePage.clickOBDoneButton("PRO");
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
		zfttOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickPopupOkButton();
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
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		zfttGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = zfttGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		zfttGradingPage.enterRegistrationNumber("Test User");
		zfttGradingPage.selectLocation("BLR");
		zfttGradingPage.selectSeatOccupied("LHS");
		zfttGradingPage.clickSaveAndNextButton();
		zfttOverallOutcomePage.validateZFTTOverallOutComePage();
		zfttOverallOutcomePage.clickGrade("PRO", "3");
		zfttOverallOutcomePage.clickAllPlus("PRO");
		zfttOverallOutcomePage.enterOBComments("PRO", "adding OB comment");
		zfttOverallOutcomePage.clickOBDoneButton("PRO");
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
		zfttOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickPopupOkButton();
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
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		zfttGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = zfttGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		zfttGradingPage.enterRegistrationNumber("Test User");
		zfttGradingPage.selectLocation("BLR");
		zfttGradingPage.selectSeatOccupied("LHS");
		zfttGradingPage.clickSaveAndNextButton();
		zfttOverallOutcomePage.validateZFTTOverallOutComePage();
		zfttOverallOutcomePage.clickGrade("PRO", "3");
		zfttOverallOutcomePage.clickAllPlus("PRO");
		zfttOverallOutcomePage.enterOBComments("PRO", "adding OB comment");
		zfttOverallOutcomePage.clickOBDoneButton("PRO");
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
		zfttOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickPopupOkButton();
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
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		zfttGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = zfttGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		zfttGradingPage.enterRegistrationNumber("Test User");
		zfttGradingPage.selectLocation("BLR");
		zfttGradingPage.selectSeatOccupied("LHS");
		zfttGradingPage.clickSaveAndNextButton();
		zfttOverallOutcomePage.validateZFTTOverallOutComePage();
		zfttOverallOutcomePage.clickGrade("PRO", "3");
		zfttOverallOutcomePage.clickAllPlus("PRO");
		zfttOverallOutcomePage.enterOBComments("PRO", "adding OB comment");
		zfttOverallOutcomePage.clickOBDoneButton("PRO");
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
		zfttOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickPopupOkButton();
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
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		zfttGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = zfttGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		zfttGradingPage.enterRegistrationNumber("Test User");
		zfttGradingPage.selectLocation("BLR");
		zfttGradingPage.selectSeatOccupied("LHS");
		zfttGradingPage.clickSaveAndNextButton();
		zfttOverallOutcomePage.validateZFTTOverallOutComePage();
		zfttOverallOutcomePage.clickGrade("PRO", "3");
		zfttOverallOutcomePage.clickAllPlus("PRO");
		zfttOverallOutcomePage.enterOBComments("PRO", "adding OB comment");
		zfttOverallOutcomePage.clickOBDoneButton("PRO");
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
		zfttOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickPopupOkButton();
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
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		zfttGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = zfttGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		zfttGradingPage.enterRegistrationNumber("Test User");
		zfttGradingPage.selectLocation("BLR");
		zfttGradingPage.selectSeatOccupied("LHS");
		zfttGradingPage.clickSaveAndNextButton();
		zfttOverallOutcomePage.validateZFTTOverallOutComePage();
		zfttOverallOutcomePage.clickGrade("PRO", "3");
		zfttOverallOutcomePage.clickAllPlus("PRO");
		zfttOverallOutcomePage.enterOBComments("PRO", "adding OB comment");
		zfttOverallOutcomePage.clickOBDoneButton("PRO");
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
		zfttOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickPopupOkButton();
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
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		zfttGradingPage.validateAllBaseTrainingGradingPageTexts();
		traineeIdWithName = zfttGradingPage.getTraineeUsernameAndId();
		traineeId = traineeIdWithName.replaceAll("\\D", "");
		zfttGradingPage.enterRegistrationNumber("Test User");
		zfttGradingPage.selectLocation("BLR");
		zfttGradingPage.selectSeatOccupied("LHS");
		zfttGradingPage.clickSaveAndNextButton();
		zfttOverallOutcomePage.validateZFTTOverallOutComePage();
		zfttOverallOutcomePage.clickGrade("PRO", "3");
		zfttOverallOutcomePage.clickAllPlus("PRO");
		zfttOverallOutcomePage.enterOBComments("PRO", "adding OB comment");
		zfttOverallOutcomePage.clickOBDoneButton("PRO");
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
		zfttOverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.clickPopupOkButton();
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
