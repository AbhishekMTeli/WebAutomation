package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.CommandUpgradeSLFOverallOutcomePage;
import pages.CommandUpgradeSLFTraineeGradingMangeSectorPage;
import pages.CommandUpgradeSLFTraineeGradingSallabusPage;
import pages.TraineeGradingPage;
import pages.TrainerDashBoradPage;

public class CommandUpGradeSLF extends BaseClass {
	private TrainerDashBoradPage trainerDashBoradPage;
	private TraineeGradingPage traineeGradingPage;
	private CommandUpgradeSLFTraineeGradingMangeSectorPage manageSectorPage;
	private CommandUpgradeSLFTraineeGradingSallabusPage syllabusPage;
	private CommandUpgradeSLFOverallOutcomePage outcomePage;

	@BeforeMethod(alwaysRun = true)
	public void initPages() {
		trainerDashBoradPage = new TrainerDashBoradPage(driver);
		traineeGradingPage = new TraineeGradingPage(driver);
		manageSectorPage = new CommandUpgradeSLFTraineeGradingMangeSectorPage(driver);
		syllabusPage = new CommandUpgradeSLFTraineeGradingSallabusPage(driver);
		outcomePage = new CommandUpgradeSLFOverallOutcomePage(driver);
	}

	@Test(description = "Happy Path for Command Upgrade SLF")

	public void e2eHappyPathTestForCommandUpgradeSLF() throws InterruptedException {
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
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
		outcomePage.performObAction("KNO", 3, "minus", "adding OB Comment");
		outcomePage.clickObDoneButton("KNO");
		outcomePage.clickCompetentRadioButton();
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
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
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
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();

		manageSectorPage.clearDuration();
		manageSectorPage.clickNextButton();

		String actualPopupText = manageSectorPage.popupGetText();
		String expectedPopupText = "Please enter duration.";

		extentTest.info("Expected popup: " + expectedPopupText);
		extentTest.info("Actual popup: " + actualPopupText);

		Assert.assertEquals(actualPopupText, expectedPopupText,
				"Popup text mismatch! Expected: " + expectedPopupText + " but got: " + actualPopupText);

		manageSectorPage.clickPopupOkButton();
	}

	@Test(description = "Verify error popup when navigating from Grading to Overall Outcome without completing required fields")
	public void shouldShowErrorPopupWhenSwitchingFromGradingToOutcomeWithoutSaving() {
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
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
	public void shouldShowErrorPopupWhenNavigatingFromManageSectorsToSyllabusWithoutCompletingFields() {
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
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
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
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
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
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
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
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
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
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
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
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
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		manageSectorPage.clickDeleteIcon();
		manageSectorPage.selectPMRadio();
		manageSectorPage.clickNextButton();
	}

	@Test(description = "Verify no action when deleting the only row in Sector Details")
	public void shouldAllowNavigationFromSectorsToSyllabusAfterFillingData() throws InterruptedException {
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		manageSectorPage.selectPMRadio();
		manageSectorPage.clickSyllabusTab();
	}

	// Still need to add the Popup here
	@Test(description = "Verify no action when deleting the only row in Sector Details")
	public void shouldShowPopupWhenSyllabusToggleIsDefaultOnNext() throws InterruptedException {
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("1");
		manageSectorPage.selectPMRadio();
		manageSectorPage.clickNextButton();
		syllabusPage.clickNextButton();
	}

	@Test(description = "Verify Delete popup for multiple sectors")
	public void shouldShowDeletePopupForMultipleSectors() throws InterruptedException {
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("8");
		manageSectorPage.clickDeleteIcon();
	}

	@Test(description = "Verify Delete Functionality for Multiple Sectors")
	public void verifyDeleteFunctionalityForMultipleSectors() throws InterruptedException {
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		traineeGradingPage.handelNoSlfHistoryPopup();
		manageSectorPage.selectNoOfSectors("8");
		manageSectorPage.clickDeleteIcon();
	}

	@Test(description = "Verify Competent radio auto-selected and Not Yet Competent disabled")
	public void shouldAutoSelectCompetentAndDisableNotYetCompetent() throws InterruptedException {
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
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
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
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
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
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
		// Navigate to Grading Assessment
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
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
		// Navigate to Grading Assessment
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
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
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
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
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
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
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
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
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
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
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
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
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
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
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
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
				"❌ Popup text mismatch! Expected: " + expectedPopupText + " but got: " + actualPopupText);

		outcomePage.clickPopupOkButton();
	}
}