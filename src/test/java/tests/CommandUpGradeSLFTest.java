package tests;

import java.awt.AWTException;

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
import commandUpgradeSLFPages.CommandUpgradeSLFOverallOutcomePage;
import commandUpgradeSLFPages.CommandUpgradeSLFTraineeGradingMangeSectorPage;
import commandUpgradeSLFPages.CommandUpgradeSLFTraineeGradingSallabusPage;
import commonPages.GradingHistoryPage;
import commonPages.LogoutPage;
import commonPages.PendingHistoryPage;
import commonPages.PopupPage;
import commonPages.TraineeGradingPage;
import commonPages.TrainerDashBoradPage;
import traineePages.TraineeReviewPage;

public class CommandUpGradeSLFTest extends BaseClass {
	private TrainerDashBoradPage trainerDashBoradPage;
	private TraineeGradingPage traineeGradingPage;
	private CommandUpgradeSLFTraineeGradingMangeSectorPage manageSectorPage;
	private CommandUpgradeSLFTraineeGradingSallabusPage syllabusPage;
	private CommandUpgradeSLFOverallOutcomePage outcomePage;
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
	private String traineeIdWithName;
	private String traineeId;
	private String traineeName;

	@BeforeMethod(alwaysRun = true)
	public void initPages() {
		adminDashBoardPage = new AdminDashBoardPage(getDriver());
		becomeUserPage = new BecomeUserPage(getDriver());
		trainerDashBoradPage = new TrainerDashBoradPage(getDriver());
		traineeGradingPage = new TraineeGradingPage(getDriver());
		manageSectorPage = new CommandUpgradeSLFTraineeGradingMangeSectorPage(getDriver());
		syllabusPage = new CommandUpgradeSLFTraineeGradingSallabusPage(getDriver());
		outcomePage = new CommandUpgradeSLFOverallOutcomePage(getDriver());
		slfHistoryPage = new SLFHistoryPage(getDriver());
		traineeReviewPage = new TraineeReviewPage(getDriver());
		logoutPage = new LogoutPage(getDriver());
		trainingManagerReviewPage = new TrainingManagerReviewPage(getDriver());
		gradingHistoryPage = new GradingHistoryPage(getDriver());
		popupPage = new PopupPage(getDriver());
		userDocsPage = new UserDocsPage(getDriver());
		eFormReportsPage = new EFormReportsPage(getDriver());
		pendingHistoryPage = new PendingHistoryPage(getDriver());

	}

	@Test(description = "Happy Path for Command Upgrade SLF")

	public void e2eHappyPathTestForCommandUpgradeSLF() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		traineeIdWithName = manageSectorPage.getTrainerId();
		System.out.println("Trainee Name and Id is " + traineeIdWithName);
		traineeId = traineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();
		outcomePage.performObAction("PRO", 0, "plus", "adding OB Comment");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.clickPopupOkButton();
		outcomePage.performObAction("KNO", 3, "minus", "adding OB Comment");
		outcomePage.clickObDoneButton("KNO");
		outcomePage.addRemark("adding remarks");
		outcomePage.competencyRemarksLabelIsPresent();
		outcomePage.selectQualification();
		outcomePage.clickSaveAndNextButton();
		outcomePage.reasonForDelayLabelIsPresent();
		outcomePage.addDelayComments("adding delay comments");
		outcomePage.clickSubmitCommentButtonForDelayComment();
		outcomePage.instructorAcknowldgementLabelIsPresent();
		outcomePage.clickSubmitButtonForInstructorAcknowldgement();
		outcomePage.digitalSignitureLabelIsPresent();
		outcomePage.digitalSign();
		outcomePage.clickClearForDigitalSigniture();
		outcomePage.digitalSign();
		outcomePage.clickSaveSignitureButtonForDigitalSigniture();
		outcomePage.dataSuccessfullyUploadedIsPresent();
		outcomePage.clickOkPop_up();
		traineeGradingPage.validateAllStaticTexts();
	}

	@Test(description = "Verify trainer can select up to 8 options in 'No. of Selected for this SLF Period' dropdown")
	public void validatePleaseEnterDuration() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("8");
		manageSectorPage.enterDepDate();
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
	}

	@Test(description = "Verify error popup when 'Duration' field is empty")
	public void shouldShowErrorPopupWhenDurationFieldIsEmpty() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();

		manageSectorPage.clearDuration();
		manageSectorPage.clickNextButton();

		String actualPopupText = manageSectorPage.popupGetText().trim();
		String expectedPopupText = "Please enter duration.";
		System.out.println(actualPopupText);

		extentTest.info("Expected popup: " + expectedPopupText);
		extentTest.info("Actual popup: " + actualPopupText);

		Assert.assertEquals(actualPopupText, expectedPopupText,
				"Popup text mismatch! Expected: " + expectedPopupText + " but got: " + actualPopupText);

		manageSectorPage.clickPopupOkButton();
	}

	@Test(description = "Verify error popup when navigating from Grading to Overall Outcome without completing required fields")
	public void shouldShowErrorPopupWhenSwitchingFromGradingToOutcomeWithoutSaving() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.clickOverallOutcomeTab();
		String actualPopupText = manageSectorPage.popupGetText();
		String expectedPopupText = "Please Save Grading Data";
		extentTest.info("Expected popup: " + expectedPopupText);
		extentTest.info("Actual popup: " + actualPopupText);

		Assert.assertEquals(actualPopupText, expectedPopupText,
				"Popup text mismatch! Expected: " + expectedPopupText + " but got: " + actualPopupText);

		manageSectorPage.clickPopupOkButton();
	}

	@Test(description = "Verify error popup when navigating from Manage Sectors to Syllabus without completing required fields")
	public void shouldShowErrorPopupWhenNavigatingFromManageSectorsToSyllabusWithoutCompletingFields()
			throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.clickSyllabusTab();
		String actualPopupText = manageSectorPage.popupGetText();
		String expectedPopupText = "You must select PM/PF";
		extentTest.info("Expected popup: " + expectedPopupText);
		extentTest.info("Actual popup: " + actualPopupText);

		Assert.assertEquals(actualPopupText, expectedPopupText,
				"Popup text mismatch! Expected: " + expectedPopupText + " but got: " + actualPopupText);

		manageSectorPage.clickPopupOkButton();
	}

	@Test(description = "Verify error popup when 'From' field is empty on Save")
	public void shouldShowErrorPopupWhenFromFieldIsEmptyOnSave() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("8");
		manageSectorPage.clearFrom();
		manageSectorPage.clickNextButton();
		String actualPopupText = manageSectorPage.popupGetText();
		String expectedPopupText = "You must select Airport FROM";
		extentTest.info("Expected popup: " + expectedPopupText);
		extentTest.info("Actual popup: " + actualPopupText);

		Assert.assertEquals(actualPopupText, expectedPopupText,
				"Popup text mismatch! Expected: " + expectedPopupText + " but got: " + actualPopupText);

		manageSectorPage.clickPopupOkButton();
	}

	@Test(description = "Verify error popup when 'From' field is empty on Save")
	public void shouldShowErrorPopupWhenToFieldIsEmptyOnSave() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		manageSectorPage.clearTo();
		manageSectorPage.clickNextButton();
		String actualPopupText = manageSectorPage.popupGetText();
		String expectedPopupText = "You must select Airport TO";
		extentTest.info("Expected popup: " + expectedPopupText);
		extentTest.info("Actual popup: " + actualPopupText);

		Assert.assertEquals(actualPopupText, expectedPopupText,
				"Popup text mismatch! Expected: " + expectedPopupText + " but got: " + actualPopupText);

		manageSectorPage.clickPopupOkButton();
	}

	@Test(description = "Verify error popup when 'RegNo' is empty on Save")
	public void shouldShowErrorPopupWhenRegNoFieldIsEmptyOnSave() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		manageSectorPage.clearRegNo();
		manageSectorPage.clickNextButton();
		String actualPopupText = manageSectorPage.popupGetText();
		String expectedPopupText = "Please enter REGNO";
		extentTest.info("Expected popup: " + expectedPopupText);
		extentTest.info("Actual popup: " + actualPopupText);

		Assert.assertEquals(actualPopupText, expectedPopupText,
				"Popup text mismatch! Expected: " + expectedPopupText + " but got: " + actualPopupText);

		manageSectorPage.clickPopupOkButton();
	}

	@Test(description = "Verify error popup when 'AirCraft Type' is empty on Save")
	public void shouldShowErrorPopupWhenAircraftTypeFieldIsEmptyOnSave() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		manageSectorPage.clearAircraftType();
		manageSectorPage.clickNextButton();
		String actualPopupText = manageSectorPage.popupGetText();
		String expectedPopupText = "Please select Aircraft Type";
		extentTest.info("Expected popup: " + expectedPopupText);
		extentTest.info("Actual popup: " + actualPopupText);

		Assert.assertEquals(actualPopupText, expectedPopupText,
				"Popup text mismatch! Expected: " + expectedPopupText + " but got: " + actualPopupText);

		manageSectorPage.clickPopupOkButton();
	}

	@Test(description = "Verify error popup when 'PM/PF' is not selected on Save")
	public void shouldShowErrorPopupWhenPMorPFNotSelectedOnSave() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		manageSectorPage.clickNextButton();
		String actualPopupText = manageSectorPage.popupGetText();
		String expectedPopupText = "You must select PM/PF";
		extentTest.info("Expected popup: " + expectedPopupText);
		extentTest.info("Actual popup: " + actualPopupText);

		Assert.assertEquals(actualPopupText, expectedPopupText,
				"Popup text mismatch! Expected: " + expectedPopupText + " but got: " + actualPopupText);

		manageSectorPage.clickPopupOkButton();
	}

	@Test(description = "Verify no action when deleting the only row in Sector Details")
	public void shouldNotDeleteOnlyRowInSectorDetails() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		manageSectorPage.clickDeleteIcon();
		manageSectorPage.selectPMRadio();
		manageSectorPage.clickNextButton();
	}

	@Test(description = "Verify no action when deleting the only row in Sector Details")
	public void shouldAllowNavigationFromSectorsToSyllabusAfterFillingData() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		manageSectorPage.selectPMRadio();
		manageSectorPage.clickSyllabusTab();
	}

	// Still need to add the Popup here
	@Test(description = "Verify no action when deleting the only row in Sector Details")
	public void shouldShowPopupWhenSyllabusToggleIsDefaultOnNext() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		manageSectorPage.selectPMRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickNextButton();
	}

	@Test(description = "Verify Delete popup for multiple sectors")
	public void shouldShowDeletePopupForMultipleSectors() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("8");
		manageSectorPage.clickDeleteIcon();
	}

	@Test(description = "Verify Delete Functionality for Multiple Sectors")
	public void verifyDeleteFunctionalityForMultipleSectors() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("8");
		manageSectorPage.clickDeleteIcon();
	}

	@Test(description = "Verify Competent radio auto-selected and Not Yet Competent disabled")
	public void shouldAutoSelectCompetentAndDisableNotYetCompetent() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();
		outcomePage.performObAction("PRO", 3, "plus", "adding OB Comment");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.performObAction("COM", 3, "plus", "adding OB Comment");
		outcomePage.clickObDoneButton("COM");
		outcomePage.validateRadioSelectionBasedOnOptionIndex(4);
		outcomePage.addRemark("adding remarks");
	}

	@Test(description = "Validate manual selection of Competent/Not Yet Competent for value 2 in any section")
	public void shouldAllowManualSelectionOfCompetentOrNotYetCompetentForValue2() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();
		outcomePage.performObAction("PRO", 1, "plus", "adding OB Comment");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.performObAction("COM", 1, "plus", "adding OB Comment");
		outcomePage.clickObDoneButton("COM");
		outcomePage.validateRadioSelectionBasedOnOptionIndex(2);
		outcomePage.clickCompetentRadioButton();
		outcomePage.addRemark("adding remarks");
	}

	@Test(description = "Validate auto-selection of Not Yet Competent for value 1 in any section")
	public void shouldAutoSelectNotYetCompetentForValue1AndHandleOBs() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();
		outcomePage.performObAction("PRO", 0, "plus", "adding OB Comment");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.clickOkPop_up();
		outcomePage.performObAction("COM", 0, "plus", "adding OB Comment");
		outcomePage.clickObDoneButton("COM");
		outcomePage.clickOkPop_up();
		outcomePage.validateRadioSelectionBasedOnOptionIndex(1);
		outcomePage.addRemark("adding remarks");
	}

	@Test(description = "Validate error when adding comments without selecting any OBs")
	public void shouldShowErrorWhenAddingCommentsWithoutSelectingOBs() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		// Navigate to Grading Assessment
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();

		// Fill Manage Sector details
		manageSectorPage.selectNoOfSectors("1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();

		// Syllabus step
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();

		// Outcome step - try adding comment without OBs
		outcomePage.performObAction("PRO", 1, "invalid", "adding OB Comment");
		outcomePage.clickObDoneButton("PRO");

		// Validate popup
		String expectedPopupText = "At least one Observable Behaviour must be selected";
		String actualPopupText = outcomePage.popupGetText();

		extentTest.info("Expected popup: " + expectedPopupText);
		extentTest.info("Actual popup: " + actualPopupText);

		Assert.assertEquals(actualPopupText, expectedPopupText,
				"❌ Popup text mismatch! Expected: " + expectedPopupText + " but got: " + actualPopupText);

		manageSectorPage.clickPopupOkButton();
	}

	@Test(description = "Validate comment requirement and Competent/Not Yet Competent auto/manual selection across all sections")
	public void validateCommentRequirementAndRadioSelectionAcrossAllSections() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		// Navigate to Grading Assessment
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();

		// Fill Manage Sector details
		manageSectorPage.selectNoOfSectors("1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();

		// Syllabus step
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();

		// Outcome step - try adding comment without OBs
		outcomePage.performObAction("PRO", 1, "plus", null);
		outcomePage.clickObDoneButton("PRO");

		// Validate popup
		String expectedPopupText = "Please enter or change comments";
		String actualPopupText = outcomePage.popupGetText();

		extentTest.info("Expected popup: " + expectedPopupText);
		extentTest.info("Actual popup: " + actualPopupText);

		Assert.assertEquals(actualPopupText, expectedPopupText,
				"❌ Popup text mismatch! Expected: " + expectedPopupText + " but got: " + actualPopupText);

		outcomePage.clickPopupOkButton();
		outcomePage.enterObComment("adding the comments after the checking the error nessage", "PRO");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.performObAction("COM", 3, "plus", null);
		outcomePage.clickObDoneButton("COM");
	}

	@Test(description = "Validate error when Competent/Not Yet Competent is not selected after adding OBs")
	public void validateErrorWhenNoCompetentSelectionAfterAddingOBs() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();
		outcomePage.performObAction("PRO", 1, "plus", "adding the OB Comment");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.clickSaveAndNextButton();
		// Validate popup
		String expectedPopupText = "You must select 'OVERALL ASSESSMENT'";
		String actualPopupText = outcomePage.popupGetText();

		extentTest.info("Expected popup: " + expectedPopupText);
		extentTest.info("Actual popup: " + actualPopupText);

		Assert.assertEquals(actualPopupText, expectedPopupText,
				"❌ Popup text mismatch! Expected: " + expectedPopupText + " but got: " + actualPopupText);

		outcomePage.clickPopupOkButton();
	}

	@Test(description = "Validate mandatory Qualification dropdown")
	public void validateMandatoryQualificationDropdown() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();
		outcomePage.performObAction("PRO", 1, "plus", "adding the OB Comment");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.clickCompetentRadioButton();
		outcomePage.clickSaveAndNextButton();
		// Validate popup
		String expectedPopupText = "Please select qualification!";
		String actualPopupText = outcomePage.popupGetText();

		extentTest.info("Expected popup: " + expectedPopupText);
		extentTest.info("Actual popup: " + actualPopupText);

		Assert.assertEquals(actualPopupText, expectedPopupText,
				"❌ Popup text mismatch! Expected: " + expectedPopupText + " but got: " + actualPopupText);

		outcomePage.clickPopupOkButton();
	}

	@Test(description = "Validate mandatory Overall Comments for Not Yet Competent")
	public void validateMandatoryOverallCommentsForNotYetCompetent() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();
		outcomePage.performObAction("PRO", 1, "plus", "adding the OB Comment");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.clickNotYetCompetentRadioButton();
		outcomePage.selectQualification();
		outcomePage.clickSaveAndNextButton();
		String expectedPopupText = "Overall comments mandatory for Not Yet Competent outcome.";
		String actualPopupText = outcomePage.popupGetText();

		extentTest.info("Expected popup: " + expectedPopupText);
		extentTest.info("Actual popup: " + actualPopupText);

		Assert.assertEquals(actualPopupText, expectedPopupText,
				"❌ Popup text mismatch! Expected: " + expectedPopupText + " but got: " + actualPopupText);

		outcomePage.clickPopupOkButton();
	}

	@Test(description = "Validate error message when 'Delay Comment' field is left empty")
	public void shouldShowErrorWhenDelayCommentIsEmpty() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();
		outcomePage.performObAction("PRO", 1, "plus", "adding the OB Comment");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.clickNotYetCompetentRadioButton();
		outcomePage.selectQualification();
		outcomePage.addRemark("adding remarks");
		outcomePage.clickSaveAndNextButton();
		outcomePage.clickSubmitCommentButtonForDelayComment();
		String expectedPopupText = "Please enter a comment before submitting.";
		String actualPopupText = outcomePage.popupGetText();

		extentTest.info("Expected popup: " + expectedPopupText);
		extentTest.info("Actual popup: " + actualPopupText);

		Assert.assertEquals(actualPopupText, expectedPopupText,
				"❌ Popup text mismatch! Expected: " + expectedPopupText + " but got: " + actualPopupText);

		outcomePage.clickPopupOkButton();
	}

	@Test(description = "Validate Close button and X icon functionality on Delay Comment popup")
	public void shouldCloseDelayCommentPopupUsingCloseButtonAndXIcon() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();
		outcomePage.performObAction("PRO", 1, "plus", "adding the OB Comment");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.clickNotYetCompetentRadioButton();
		outcomePage.selectQualification();
		outcomePage.addRemark("adding remarks");
		outcomePage.clickSaveAndNextButton();
		outcomePage.clickCloseCommentButtonForDelayComment();
	}

	@Test(description = "Validate Sign canvas")
	public void validateSignCanvas() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();
		outcomePage.performObAction("PRO", 1, "plus", "adding the OB Comment");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.clickNotYetCompetentRadioButton();
		outcomePage.selectQualification();
		outcomePage.addRemark("adding remarks");
		outcomePage.clickSaveAndNextButton();
		outcomePage.addDelayComments("adding delay comments");
		outcomePage.clickSubmitCommentButtonForDelayComment();
		outcomePage.instructorAcknowldgementLabelIsPresent();
		outcomePage.clickSubmitButtonForInstructorAcknowldgement();
		outcomePage.digitalSign();
		outcomePage.clickClearForDigitalSigniture();
		outcomePage.clickSaveSignitureButtonForDigitalSigniture();
	}

	@Test(description = "Validate Data Submission popup")
	public void validateDataSubmissionPopup() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();
		outcomePage.performObAction("PRO", 1, "plus", "adding the OB Comment");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.clickNotYetCompetentRadioButton();
		outcomePage.selectQualification();
		outcomePage.addRemark("adding remarks");
		outcomePage.clickSaveAndNextButton();
		outcomePage.addDelayComments("adding delay comments");
		outcomePage.clickSubmitCommentButtonForDelayComment();
		outcomePage.instructorAcknowldgementLabelIsPresent();
		outcomePage.clickSubmitButtonForInstructorAcknowldgement();
		outcomePage.digitalSign();
		outcomePage.clickClearForDigitalSigniture();
		outcomePage.digitalSign();
		outcomePage.clickSaveSignitureButtonForDigitalSigniture();
		String expectedPopupText = "Data successfully uploaded!";
		String actualPopupText = outcomePage.popupGetText();

		extentTest.info("Expected popup: " + expectedPopupText);
		extentTest.info("Actual popup: " + actualPopupText);

		Assert.assertEquals(actualPopupText, expectedPopupText,
				"Popup text mismatch! Expected: " + expectedPopupText + " but got: " + actualPopupText);

		outcomePage.clickPopupOkButton();
	}

	@Test(description = "Validate that trainer cannot submit form if Total Sectors Completed exceeds 15")
	public void shouldNotAllowFormSubmissionWhenTotalSectorsExceedsFifteen() throws InterruptedException, AWTException {
		adminDashBoardPage.clickGradingAndAssessmentTab();
		adminDashBoardPage.clickSlfHistorySubTab();
		slfHistoryPage.clickAddButton();
		slfHistoryPage.validateAllStaticTexts();
		slfHistoryPage.enterTraineeNameAndSelectSuggestion("85123");
		slfHistoryPage.selectCurriculum("Command Upgrade Course");
		slfHistoryPage.enterPM("5");
		slfHistoryPage.enterPF("5");
		slfHistoryPage.clickSaveOrUpdateButton();
		Thread.sleep(5000);
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
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		traineeIdWithName = manageSectorPage.getTrainerId();
		System.out.println("Trainee Name and Id is " + traineeIdWithName);
		traineeId = traineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();
		outcomePage.performObAction("PRO", 0, "plus", "adding OB Comment");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.clickPopupOkButton();
		outcomePage.performObAction("KNO", 3, "minus", "adding OB Comment");
		outcomePage.clickObDoneButton("KNO");
		outcomePage.addRemark("adding remarks");
		outcomePage.competencyRemarksLabelIsPresent();
		outcomePage.selectQualification();
		outcomePage.clickSaveAndNextButton();
		outcomePage.reasonForDelayLabelIsPresent();
		outcomePage.addDelayComments("adding delay comments");
		outcomePage.clickSubmitCommentButtonForDelayComment();
		outcomePage.instructorAcknowldgementLabelIsPresent();
		outcomePage.clickSubmitButtonForInstructorAcknowldgement();
		outcomePage.digitalSignitureLabelIsPresent();
		outcomePage.digitalSign();
		outcomePage.clickClearForDigitalSigniture();
		outcomePage.digitalSign();
		outcomePage.clickSaveSignitureButtonForDigitalSigniture();
		outcomePage.dataSuccessfullyUploadedIsPresent();
		outcomePage.clickOkPop_up();
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
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		traineeIdWithName = manageSectorPage.getTrainerId();
		System.out.println("Trainee Name and Id is " + traineeIdWithName);
		traineeId = traineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();
		outcomePage.performObAction("PRO", 3, "plus", "adding OB Comment");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.addRemark("adding remarks");
		outcomePage.competencyRemarksLabelIsPresent();
		outcomePage.selectQualification();
		outcomePage.clickSaveAndNextButton();
		outcomePage.reasonForDelayLabelIsPresent();
		outcomePage.addDelayComments("adding delay comments");
		outcomePage.clickSubmitCommentButtonForDelayComment();
		outcomePage.instructorAcknowldgementLabelIsPresent();
		outcomePage.clickSubmitButtonForInstructorAcknowldgement();
		outcomePage.digitalSignitureLabelIsPresent();
		outcomePage.digitalSign();
		outcomePage.clickSaveSignitureButtonForDigitalSigniture();
		outcomePage.dataSuccessfullyUploadedIsPresent();
		outcomePage.clickOkPop_up();
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
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		traineeIdWithName = manageSectorPage.getTrainerId();
		System.out.println("Trainee Name and Id is " + traineeIdWithName);
		traineeId = traineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();
		outcomePage.performObAction("PRO", 3, "plus", "adding OB Comment");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.addRemark("adding remarks");
		outcomePage.competencyRemarksLabelIsPresent();
		outcomePage.selectQualification();
		outcomePage.clickSaveAndNextButton();
		outcomePage.reasonForDelayLabelIsPresent();
		outcomePage.addDelayComments("adding delay comments");
		outcomePage.clickSubmitCommentButtonForDelayComment();
		outcomePage.instructorAcknowldgementLabelIsPresent();
		outcomePage.clickSubmitButtonForInstructorAcknowldgement();
		outcomePage.digitalSignitureLabelIsPresent();
		outcomePage.digitalSign();
		outcomePage.clickSaveSignitureButtonForDigitalSigniture();
		outcomePage.dataSuccessfullyUploadedIsPresent();
		outcomePage.clickOkPop_up();
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
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		traineeIdWithName = manageSectorPage.getTrainerId();
		System.out.println("Trainee Name and Id is " + traineeIdWithName);
		traineeId = traineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();
		outcomePage.performObAction("PRO", 3, "plus", "adding OB Comment");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.addRemark("adding remarks");
		outcomePage.competencyRemarksLabelIsPresent();
		outcomePage.selectQualification();
		outcomePage.clickSaveAndNextButton();
		outcomePage.reasonForDelayLabelIsPresent();
		outcomePage.addDelayComments("adding delay comments");
		outcomePage.clickSubmitCommentButtonForDelayComment();
		outcomePage.instructorAcknowldgementLabelIsPresent();
		outcomePage.clickSubmitButtonForInstructorAcknowldgement();
		outcomePage.digitalSignitureLabelIsPresent();
		outcomePage.digitalSign();
		outcomePage.clickSaveSignitureButtonForDigitalSigniture();
		outcomePage.dataSuccessfullyUploadedIsPresent();
		outcomePage.clickOkPop_up();
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
		manageSectorPage.validateAllStaticTexts();
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPMRadio();
		manageSectorPage.clickNextButton();
		pendingHistoryPage.validateUpdateGeneralInfoPopupLabelText();
		pendingHistoryPage.clickUpdateGeneralInfoPopupNoButton();
		manageSectorPage.clickNextButton();
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
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		traineeIdWithName = manageSectorPage.getTrainerId();
		System.out.println("Trainee Name and Id is " + traineeIdWithName);
		traineeId = traineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();
		outcomePage.performObAction("PRO", 3, "plus", "adding OB Comment");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.addRemark("adding remarks");
		outcomePage.competencyRemarksLabelIsPresent();
		outcomePage.selectQualification();
		outcomePage.clickSaveAndNextButton();
		outcomePage.reasonForDelayLabelIsPresent();
		outcomePage.addDelayComments("adding delay comments");
		outcomePage.clickSubmitCommentButtonForDelayComment();
		outcomePage.instructorAcknowldgementLabelIsPresent();
		outcomePage.clickSubmitButtonForInstructorAcknowldgement();
		outcomePage.digitalSignitureLabelIsPresent();
		outcomePage.digitalSign();
		outcomePage.clickSaveSignitureButtonForDigitalSigniture();
		outcomePage.dataSuccessfullyUploadedIsPresent();
		outcomePage.clickOkPop_up();
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
		manageSectorPage.validateAllStaticTexts();
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPMRadio();
		manageSectorPage.clickNextButton();
		pendingHistoryPage.validateUpdateGeneralInfoPopupLabelText();
		pendingHistoryPage.clickUpdateGeneralInfoPopupNoButton();
		manageSectorPage.clickNextButton();
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
		pendingHistoryPage.clickFeedbackButton("adding feedback comments");
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		traineeIdWithName = manageSectorPage.getTrainerId();
		System.out.println("Trainee Name and Id is " + traineeIdWithName);
		traineeId = traineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();
		outcomePage.performObAction("PRO", 3, "plus", "adding OB Comment");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.addRemark("adding remarks");
		outcomePage.competencyRemarksLabelIsPresent();
		outcomePage.selectQualification();
		outcomePage.clickSaveAndNextButton();
		outcomePage.reasonForDelayLabelIsPresent();
		outcomePage.addDelayComments("adding delay comments");
		outcomePage.clickSubmitCommentButtonForDelayComment();
		outcomePage.instructorAcknowldgementLabelIsPresent();
		outcomePage.clickSubmitButtonForInstructorAcknowldgement();
		outcomePage.digitalSignitureLabelIsPresent();
		outcomePage.digitalSign();
		outcomePage.clickSaveSignitureButtonForDigitalSigniture();
		outcomePage.dataSuccessfullyUploadedIsPresent();
		outcomePage.clickOkPop_up();
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
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		traineeIdWithName = manageSectorPage.getTrainerId();
		System.out.println("Trainee Name and Id is " + traineeIdWithName);
		traineeId = traineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();
		outcomePage.performObAction("PRO", 3, "plus", "adding OB Comment");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.addRemark("adding remarks");
		outcomePage.competencyRemarksLabelIsPresent();
		outcomePage.selectQualification();
		outcomePage.clickSaveAndNextButton();
		outcomePage.reasonForDelayLabelIsPresent();
		outcomePage.addDelayComments("adding delay comments");
		outcomePage.clickSubmitCommentButtonForDelayComment();
		outcomePage.instructorAcknowldgementLabelIsPresent();
		outcomePage.clickSubmitButtonForInstructorAcknowldgement();
		outcomePage.digitalSignitureLabelIsPresent();
		outcomePage.digitalSign();
		outcomePage.clickSaveSignitureButtonForDigitalSigniture();
		outcomePage.dataSuccessfullyUploadedIsPresent();
		outcomePage.clickOkPop_up();
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
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		traineeIdWithName = manageSectorPage.getTrainerId();
		System.out.println("Trainee Name and Id is " + traineeIdWithName);
		traineeId = traineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();
		outcomePage.performObAction("PRO", 3, "plus", "adding OB Comment");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.addRemark("adding remarks");
		outcomePage.competencyRemarksLabelIsPresent();
		outcomePage.selectQualification();
		outcomePage.clickSaveAndNextButton();
		outcomePage.reasonForDelayLabelIsPresent();
		outcomePage.addDelayComments("adding delay comments");
		outcomePage.clickSubmitCommentButtonForDelayComment();
		outcomePage.instructorAcknowldgementLabelIsPresent();
		outcomePage.clickSubmitButtonForInstructorAcknowldgement();
		outcomePage.digitalSignitureLabelIsPresent();
		outcomePage.digitalSign();
		outcomePage.clickSaveSignitureButtonForDigitalSigniture();
		outcomePage.dataSuccessfullyUploadedIsPresent();
		outcomePage.clickOkPop_up();
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
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		traineeIdWithName = manageSectorPage.getTrainerId();
		System.out.println("Trainee Name and Id is " + traineeIdWithName);
		traineeId = traineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();
		outcomePage.performObAction("PRO", 3, "plus", "adding OB Comment");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.addRemark("adding remarks");
		outcomePage.competencyRemarksLabelIsPresent();
		outcomePage.selectQualification();
		outcomePage.clickSaveAndNextButton();
		outcomePage.reasonForDelayLabelIsPresent();
		outcomePage.addDelayComments("adding delay comments");
		outcomePage.clickSubmitCommentButtonForDelayComment();
		outcomePage.instructorAcknowldgementLabelIsPresent();
		outcomePage.clickSubmitButtonForInstructorAcknowldgement();
		outcomePage.digitalSignitureLabelIsPresent();
		outcomePage.digitalSign();
		outcomePage.clickSaveSignitureButtonForDigitalSigniture();
		outcomePage.dataSuccessfullyUploadedIsPresent();
		outcomePage.clickOkPop_up();
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
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		traineeIdWithName = manageSectorPage.getTrainerId();
		System.out.println("Trainee Name and Id is " + traineeIdWithName);
		traineeId = traineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();
		outcomePage.performObAction("PRO", 3, "plus", "adding OB Comment");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.addRemark("adding remarks");
		outcomePage.competencyRemarksLabelIsPresent();
		outcomePage.selectQualification();
		outcomePage.clickSaveAndNextButton();
		outcomePage.reasonForDelayLabelIsPresent();
		outcomePage.addDelayComments("adding delay comments");
		outcomePage.clickSubmitCommentButtonForDelayComment();
		outcomePage.instructorAcknowldgementLabelIsPresent();
		outcomePage.clickSubmitButtonForInstructorAcknowldgement();
		outcomePage.digitalSignitureLabelIsPresent();
		outcomePage.digitalSign();
		outcomePage.clickSaveSignitureButtonForDigitalSigniture();
		outcomePage.dataSuccessfullyUploadedIsPresent();
		outcomePage.clickOkPop_up();
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
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		traineeIdWithName = manageSectorPage.getTrainerId();
		System.out.println("Trainee Name and Id is " + traineeIdWithName);
		traineeId = traineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();
		outcomePage.performObAction("PRO", 3, "plus", "adding OB Comment");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.addRemark("adding remarks");
		outcomePage.competencyRemarksLabelIsPresent();
		outcomePage.selectQualification();
		outcomePage.clickSaveAndNextButton();
		outcomePage.reasonForDelayLabelIsPresent();
		outcomePage.addDelayComments("adding delay comments");
		outcomePage.clickSubmitCommentButtonForDelayComment();
		outcomePage.instructorAcknowldgementLabelIsPresent();
		outcomePage.clickSubmitButtonForInstructorAcknowldgement();
		outcomePage.digitalSignitureLabelIsPresent();
		outcomePage.digitalSign();
		outcomePage.clickSaveSignitureButtonForDigitalSigniture();
		outcomePage.dataSuccessfullyUploadedIsPresent();
		outcomePage.clickOkPop_up();
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
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		traineeIdWithName = manageSectorPage.getTrainerId();
		System.out.println("Trainee Name and Id is " + traineeIdWithName);
		traineeId = traineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();
		outcomePage.performObAction("PRO", 3, "plus", "adding OB Comment");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.addRemark("adding remarks");
		outcomePage.competencyRemarksLabelIsPresent();
		outcomePage.selectQualification();
		outcomePage.clickSaveAndNextButton();
		outcomePage.reasonForDelayLabelIsPresent();
		outcomePage.addDelayComments("adding delay comments");
		outcomePage.clickSubmitCommentButtonForDelayComment();
		outcomePage.instructorAcknowldgementLabelIsPresent();
		outcomePage.clickSubmitButtonForInstructorAcknowldgement();
		outcomePage.digitalSignitureLabelIsPresent();
		outcomePage.digitalSign();
		outcomePage.clickSaveSignitureButtonForDigitalSigniture();
		outcomePage.dataSuccessfullyUploadedIsPresent();
		outcomePage.clickOkPop_up();
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
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		traineeIdWithName = manageSectorPage.getTrainerId();
		System.out.println("Trainee Name and Id is " + traineeIdWithName);
		traineeId = traineeIdWithName.replaceAll(".*\\((\\d+)\\).*", "$1");
		manageSectorPage.enterFrom();
		manageSectorPage.enterTo();
		manageSectorPage.enterRegNo();
		manageSectorPage.enterAircraftType();
		manageSectorPage.selectPFRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickAllVisibleAndInteractableNoButtons();
		syllabusPage.clickNextButton();
		outcomePage.performObAction("PRO", 3, "plus", "adding OB Comment");
		outcomePage.clickObDoneButton("PRO");
		outcomePage.addRemark("adding remarks");
		outcomePage.competencyRemarksLabelIsPresent();
		outcomePage.selectQualification();
		outcomePage.clickSaveAndNextButton();
		outcomePage.reasonForDelayLabelIsPresent();
		outcomePage.addDelayComments("adding delay comments");
		outcomePage.clickSubmitCommentButtonForDelayComment();
		outcomePage.instructorAcknowldgementLabelIsPresent();
		outcomePage.clickSubmitButtonForInstructorAcknowldgement();
		outcomePage.digitalSignitureLabelIsPresent();
		outcomePage.digitalSign();
		outcomePage.clickSaveSignitureButtonForDigitalSigniture();
		outcomePage.dataSuccessfullyUploadedIsPresent();
		outcomePage.clickOkPop_up();
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

//	@Test(description = "Validate that trainer cannot submit form if Total Sectors Completed exceeds 15", dependsOnMethods = "e2eHappyPathTestForCommandUpgradeSLF")
//	public void test() throws InterruptedException {
//		adminDashBoardPage.clickGradingAndAssessmentTab();
//		adminDashBoardPage.clickSlfHistorySubTab();
//		slfHistoryPage.inputSearchField(traineeId);
//		slfHistoryPage.clickOnViewOrEdit();
//		slfHistoryPage.enterPM("10");
//		slfHistoryPage.enterPF("5");
//		slfHistoryPage.clickSaveOrUpdateButton();
//		Assert.assertEquals(popupPages.popupGetText(), "Data saved successfully");
//		Thread.sleep(5000);
//		traineeGradingPage.clickFeedbackButton("adding feedback comments");
//	}
}