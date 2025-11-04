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
import ca4041Pages.CA4041GeneralInfoGradingPage;
import ca4041Pages.CA4041OverallOutComePage;
import ca4041Pages.CA4041TaskGradePage;
import ca4041Pages.GradingTraineeListPage;
import cat_II_III_Pages.CAT_III_TrainingPage;
import cat_II_III_Pages.CAT_II_III_CheckPage;
import cat_II_III_Pages.CAT_II_III_GradingPage;
import cat_II_III_Pages.CAT_II_III_OverallOutcomePage;
import cat_II_III_Pages.CAT_II_III_TrainingPage;
import cat_II_III_Pages.CAT_II_TrainingPage;
import commonPages.GradingHistoryPage;
import commonPages.LogoutPage;
import commonPages.PendingHistoryPage;
import commonPages.PopupPage;
import commonPages.TraineeGradingPage;
import commonPages.TrainerDashBoradPage;
import rhsPages.RHSCheckPage;
import rhsPages.RHSGradingPage;
import rhsPages.RHSTrainingPage;
import traineePages.TraineeReviewPage;
import utils.SeleniumUtils;

public class CATII_III_Test extends BaseClass {
	private GradingTraineeListPage gradingTraineeListPage;
	private TrainerDashBoradPage trainerDashBoradPage;
	private TraineeGradingPage traineeGradingPage;
	private CA4041GeneralInfoGradingPage cA4041GeneralInfoGradingPage;
	private CA4041TaskGradePage cA4041TaskGradePage;
	private CA4041OverallOutComePage cA4041OverallOutComePage;
	private RHSGradingPage rHSGradingPage;
	private RHSTrainingPage rHSTrainingPage;
	private PopupPage popupPage;
	private String designation;
	private RHSCheckPage rHSCheckPage;
	private String traineeIdWithName;
	private String traineeId;
	private GradingHistoryPage gradingHistoryPage;
	private LogoutPage logoutPage;
	private TraineeReviewPage traineeReviewPage;
	private PendingHistoryPage pendingHistoryPage;
	private TrainingManagerReviewPage trainingManagerReviewPage;
	private UserDocsPage userDocsPage;
	private EFormReportsPage eFormReportsPage;
	private CAT_II_III_GradingPage cat_II_III_GradingPage;
	private CAT_II_III_TrainingPage cat_II_III_TrainingPage;
	private CAT_II_TrainingPage cat_II_TrainingPage;
	private CAT_III_TrainingPage cat_III_TrainingPage;
	private CAT_II_III_CheckPage cat_II_III_CheckPage;
	private CAT_II_III_OverallOutcomePage cat_II_III_OverallOutcomePage;
	private String lhsTraineeIdWithName;
	private String lhsTraineeId;
	private String rhsTraineeIdWithName;
	private String rhsTraineeId;

	@BeforeMethod(alwaysRun = true)
	public void initPages() {
		adminDashBoardPage = new AdminDashBoardPage(getDriver());
		becomeUserPage = new BecomeUserPage(getDriver());
		gradingTraineeListPage = new GradingTraineeListPage(getDriver());
		trainerDashBoradPage = new TrainerDashBoradPage(getDriver());
		traineeGradingPage = new TraineeGradingPage(getDriver());
		cA4041GeneralInfoGradingPage = new CA4041GeneralInfoGradingPage(getDriver());
		cA4041TaskGradePage = new CA4041TaskGradePage(getDriver());
		cA4041OverallOutComePage = new CA4041OverallOutComePage(getDriver());
		rHSGradingPage = new RHSGradingPage(getDriver());
		popupPage = new PopupPage(getDriver());
		rHSTrainingPage = new RHSTrainingPage(getDriver());
		rHSCheckPage = new RHSCheckPage(getDriver());
		gradingHistoryPage = new GradingHistoryPage(getDriver());
		logoutPage = new LogoutPage(getDriver());
		traineeReviewPage = new TraineeReviewPage(getDriver());
		pendingHistoryPage = new PendingHistoryPage(getDriver());
		trainingManagerReviewPage = new TrainingManagerReviewPage(getDriver());
		userDocsPage = new UserDocsPage(getDriver());
		eFormReportsPage = new EFormReportsPage(getDriver());
		cat_II_III_GradingPage = new CAT_II_III_GradingPage(getDriver());
		cat_II_III_TrainingPage = new CAT_II_III_TrainingPage(getDriver());
		cat_II_TrainingPage = new CAT_II_TrainingPage(getDriver());
		cat_III_TrainingPage = new CAT_III_TrainingPage(getDriver());
		cat_II_III_CheckPage = new CAT_II_III_CheckPage(getDriver());
		cat_II_III_OverallOutcomePage = new CAT_II_III_OverallOutcomePage(getDriver());
	}

	@Test(description = "CAT II III Form e2e Happy Path test case")
	public void happyPathCAT_II_III_Test() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		boolean rhsUserPresent = cat_II_III_GradingPage.isRHSUserPresent();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickCM2LHSRadioButton();

		if (rhsUserPresent) {
			cat_II_III_GradingPage.clickCM1RHSRadioButton();
			cat_II_III_GradingPage.clickCM2RHSRadioButton();
		}
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		cat_II_TrainingPage.clickCAT_II_LHSGrade("PRO", "3");
		cat_II_TrainingPage.clickAllLHSMinus("PRO");
		cat_II_TrainingPage.enterLHSComments("PRO", "Entering the OB Comments");
		cat_II_TrainingPage.clickLHSOBDoneButton("PRO");
		cat_II_TrainingPage.enterCAT_II_LHSRemarks("adding cat II lhs remarks");

		// rhs CAT II grading
		if (rhsUserPresent) {
			cat_II_TrainingPage.clickCAT_II_RHSGrade("FPM", "3");
			cat_II_TrainingPage.clickAllRHSMinus("FPM");
			cat_II_TrainingPage.enterLHSComments("FPM", "Entering the OB Comments");
			cat_II_TrainingPage.clickLHSOBDoneButton("FPM");
			cat_II_TrainingPage.enterCAT_II_RHSRemarks("adding cat II rhs remarks");
		}

		// lhs CAT III grading
		cat_II_III_TrainingPage.clickCATIIIPanel();
		cat_III_TrainingPage.clickCAT_III_LHSGrade("KNO", "3");
		cat_III_TrainingPage.clickAllLHSMinusButtons("KNO");
		cat_III_TrainingPage.enterLHSComments("KNO", "Entering the OB Comments");
		cat_III_TrainingPage.clickLHSOBDoneButton("KNO");
		cat_III_TrainingPage.enterCAT_III_LHSRemarks("adding cat III lhs remarks");

		// rhs CAT III grading
		if (rhsUserPresent) {
			cat_III_TrainingPage.clickCAT_III_RHSGrade("FPA", "3");
			cat_III_TrainingPage.clickAllRHSMinusButtons("FPA");
			cat_III_TrainingPage.enterRHSComments("FPA", "Entering the OB Comments");
			cat_III_TrainingPage.clickRHSOBDoneButton("FPA");
			cat_III_TrainingPage.enterCAT_III_RHSRemarks("adding cat III rhs remarks");
		}

		cat_II_III_TrainingPage.clickSaveAndNextButton();
		popupPage.clickPopupOrAlertYesButton();

		// check page
		// lhs
		cat_II_III_CheckPage.clickCAT_II_III_LHSGrade("LTW", "3");
		cat_II_III_CheckPage.clickAllLHSMinus("LTW");
		cat_II_III_CheckPage.enterLHSComments("LTW", "entering ob comments");
		cat_II_III_CheckPage.clickLHSOBDoneButton("LTW");
		cat_II_III_CheckPage.enterCAT_II_III_LHSRemarks("entering lhs remarks");

		// rhs
		if (rhsUserPresent) {
			cat_II_III_CheckPage.clickCAT_II_III_RHSGrade("COM", "3");
			cat_II_III_CheckPage.clickAllRHSMinus("COM");
			cat_II_III_CheckPage.enterRHSComments("COM", "entering ob comments");
			cat_II_III_CheckPage.clickRHSOBDoneButton("COM");
			cat_II_III_CheckPage.enterCAT_II_III_RHSRemarks("entering rhs remarks");
		}

		cat_II_III_CheckPage.selectQaulification("SFI");
		cat_II_III_CheckPage.clickNextAndSaveButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_OverallOutcomePage.reasonForDelayLabelIsPresent();
		cat_II_III_OverallOutcomePage.addDelayComments("adding delay comments");
		cat_II_III_OverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		cat_II_III_OverallOutcomePage.visibilityOfPreviewHeader();
		cat_II_III_OverallOutcomePage.clickPreviewNextButton();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.digitalSignitureLabelIsPresent();
		cat_II_III_OverallOutcomePage.digitalSign();
		cat_II_III_OverallOutcomePage.clickClearForDigitalSigniture();
		cat_II_III_OverallOutcomePage.digitalSign();
		cat_II_III_OverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.handelSpinner();
		popupPage.clickPopupOkButton();
		traineeGradingPage.validateAllStaticTexts();
	}

	@Test(description = "OK Please enter Registration number.")
	public void validatePleaseEnterRegistrationNumber() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		cat_II_III_GradingPage.enterRegistrationNumber("");
		cat_II_III_GradingPage.clickSaveAndNextButton();
		String expectedResult = "OK Please enter Registration number.";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch Expected is : " + expectedResult + " but got : " + actualResult);
		popupPage.clickPopupOkButton();
	}

	@Test(description = "OK Please select location.")
	public void validatePleaseEnterLocation() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		cat_II_III_GradingPage.enterRegistrationNumber("test user");
		cat_II_III_GradingPage.selectLocationDropdown("Select");
		cat_II_III_GradingPage.clickSaveAndNextButton();
		String expectedResult = "OK Please select location.";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch Expected is : " + expectedResult + " but got : " + actualResult);
		popupPage.clickPopupOkButton();
	}

	@Test(description = "OK Please select CM1 for either LHS or RHS.")
	public void validatePleaseSelectCM1ForEitherLHSOrRHS() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		cat_II_III_GradingPage.enterRegistrationNumber("test user");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickSaveAndNextButton();
		String expectedResult = "OK Please select CM1 for either LHS or RHS.";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch Expected is : " + expectedResult + " but got : " + actualResult);
		popupPage.clickPopupOkButton();
	}

	@Test(description = "OK You must Select General Information")
	public void validateYouMustSelectGeneralInformation() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		cat_II_III_GradingPage.clickCheckTab();
		String expectedResult = "OK You must Select General Information";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch Expected is : " + expectedResult + " but got : " + actualResult);
		popupPage.clickPopupOkButton();
	}

	@Test(description = "OK At least one Observable Behaviour must be selected")
	public void validateAtLeastOneObservableBehaviourMustBeSelected() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		cat_II_TrainingPage.clickCAT_II_LHSGrade("PRO", "3");
		cat_II_TrainingPage.clickLHSOBDoneButton("PRO");
		String expectedResult = "OK At least one Observable Behaviour must be selected";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch Expected is : " + expectedResult + " but got : " + actualResult);
		popupPage.clickPopupOkButton();
	}

	@Test(description = "OK Please enter or change comments")
	public void validatePleaseEnterOrChangeComments() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		cat_II_TrainingPage.clickCAT_II_LHSGrade("PRO", "1");
		cat_II_TrainingPage.clickAllLHSMinus("PRO");
		cat_II_TrainingPage.clickLHSOBDoneButton("PRO");
		String expectedResult = "OK Please enter or change comments";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch Expected is : " + expectedResult + " but got : " + actualResult);
		popupPage.clickPopupOkButton();
	}

	@Test(description = "OK The Overall Outcome 'NOT YET COMPETENT' has been assigned")
	public void validateTheOverallOutcomeNotYetCompetentHasBeenAssigned() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		cat_II_TrainingPage.clickCAT_II_LHSGrade("PRO", "1");
		cat_II_TrainingPage.clickAllLHSMinus("PRO");
		cat_II_TrainingPage.enterLHSComments("PRO", "adding OB comments");
		cat_II_TrainingPage.clickLHSOBDoneButton("PRO");
		String expectedResult = "OK The Overall Outcome 'NOT YET COMPETENT' has been assigned";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch Expected is : " + expectedResult + " but got : " + actualResult);
		popupPage.clickPopupOkButton();
	}

	@Test(description = "OK Too few competencies have been graded. At least 3 Competencies must be Graded.")
	public void validateTooFewCompetenciesHaveBeenGradedAtLeast3CompetenciesMustBeGraded() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		cat_II_TrainingPage.clickCAT_II_LHSNOGrade();
		String expectedResult = "OK Too few competencies have been graded. At least 3 Competencies must be Graded.";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch Expected is : " + expectedResult + " but got : " + actualResult);
		popupPage.clickPopupOkButton();
	}

	@Test(description = "OK You must select left side overall assessment.")
	public void validateYouMustSelectLeftSideOverallAssessment() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		cat_II_TrainingPage.clickCAT_II_LHSGrade("PRO", "2");
		cat_II_TrainingPage.clickAllLHSMinus("PRO");
		cat_II_TrainingPage.enterLHSComments("PRO", "adding OB comments");
		cat_II_TrainingPage.clickLHSOBDoneButton("PRO");
		cat_II_III_TrainingPage.clickSaveAndNextButton();
		String expectedResult = "OK You must select left side overall assessment.";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch Expected is : " + expectedResult + " but got : " + actualResult);
		popupPage.clickPopupOkButton();
	}

	@Test(description = "OK You must select right side overall assessment.")
	public void validateYouMustSelectRightSideOverallAssessment() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		boolean rhsUserPresent = cat_II_III_GradingPage.isRHSUserPresent();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		cat_II_TrainingPage.clickCAT_II_LHSGrade("PRO", "3");
		cat_II_TrainingPage.clickAllLHSMinus("PRO");
		cat_II_TrainingPage.enterLHSComments("PRO", "adding OB comments");
		cat_II_TrainingPage.clickLHSOBDoneButton("PRO");

		// rhs
		if (rhsUserPresent) {
			cat_II_TrainingPage.clickCAT_II_RHSGrade("FPM", "2");
			cat_II_TrainingPage.clickAllRHSMinus("FPM");
			cat_II_TrainingPage.enterLHSComments("FPM", "Entering the OB Comments");
			cat_II_TrainingPage.clickLHSOBDoneButton("FPM");
			cat_II_TrainingPage.enterCAT_II_RHSRemarks("adding cat II rhs remarks");
			cat_II_III_TrainingPage.clickSaveAndNextButton();
			String expectedResult = "OK You must select right side overall assessment.";
			String actualResult = popupPage.popupGetText();
			Assert.assertEquals(expectedResult, actualResult,
					"Text mismatch Expected is : " + expectedResult + " but got : " + actualResult);
			popupPage.clickPopupOkButton();
		}
	}

	@Test(description = "OK Overall comments mandatory for NOT YET COMPETENT outcome")
	public void validateOverallCommentsMandatoryForNotYetCompetentOutcome() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		cat_II_TrainingPage.clickCAT_II_LHSGrade("PRO", "1");
		cat_II_TrainingPage.clickAllLHSMinus("PRO");
		cat_II_TrainingPage.enterLHSComments("PRO", "adding OB comments");
		cat_II_TrainingPage.clickLHSOBDoneButton("PRO");
		popupPage.clickPopupOkButton();
		cat_II_TrainingPage.enterCAT_II_LHSRemarks("");
		cat_II_III_TrainingPage.clickSaveAndNextButton();
		String expectedResult = "OK Overall comments mandatory for NOT YET COMPETENT outcome";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch Expected is : " + expectedResult + " but got : " + actualResult);
		popupPage.clickPopupOkButton();

	}

	@Test(description = "OK Please select qualification.")
	public void validatePleaseSelectQualification() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		boolean rhsUserPresent = cat_II_III_GradingPage.isRHSUserPresent();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickCM2LHSRadioButton();

		if (rhsUserPresent) {
			cat_II_III_GradingPage.clickCM1RHSRadioButton();
			cat_II_III_GradingPage.clickCM2RHSRadioButton();
		}
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		cat_II_TrainingPage.clickCAT_II_LHSGrade("PRO", "3");
		cat_II_TrainingPage.clickAllLHSMinus("PRO");
		cat_II_TrainingPage.enterLHSComments("PRO", "Entering the OB Comments");
		cat_II_TrainingPage.clickLHSOBDoneButton("PRO");
		cat_II_TrainingPage.enterCAT_II_LHSRemarks("adding cat II lhs remarks");

		// rhs CAT II grading
		if (rhsUserPresent) {
			cat_II_TrainingPage.clickCAT_II_RHSGrade("FPM", "3");
			cat_II_TrainingPage.clickAllRHSMinus("FPM");
			cat_II_TrainingPage.enterLHSComments("FPM", "Entering the OB Comments");
			cat_II_TrainingPage.clickLHSOBDoneButton("FPM");
			cat_II_TrainingPage.enterCAT_II_RHSRemarks("adding cat II rhs remarks");
		}

		// lhs CAT III grading
		cat_II_III_TrainingPage.clickCATIIIPanel();
		cat_III_TrainingPage.clickCAT_III_LHSGrade("KNO", "3");
		cat_III_TrainingPage.clickAllLHSMinusButtons("KNO");
		cat_III_TrainingPage.enterLHSComments("KNO", "Entering the OB Comments");
		cat_III_TrainingPage.clickLHSOBDoneButton("KNO");
		cat_III_TrainingPage.enterCAT_III_LHSRemarks("adding cat III lhs remarks");

		// rhs CAT III grading
		if (rhsUserPresent) {
			cat_III_TrainingPage.clickCAT_III_RHSGrade("FPA", "3");
			cat_III_TrainingPage.clickAllRHSMinusButtons("FPA");
			cat_III_TrainingPage.enterRHSComments("FPA", "Entering the OB Comments");
			cat_III_TrainingPage.clickRHSOBDoneButton("FPA");
			cat_III_TrainingPage.enterCAT_III_RHSRemarks("adding cat III rhs remarks");
		}

		cat_II_III_TrainingPage.clickSaveAndNextButton();
		popupPage.clickPopupOrAlertYesButton();

		// check page
		// lhs
		cat_II_III_CheckPage.clickCAT_II_III_LHSGrade("LTW", "3");
		cat_II_III_CheckPage.clickAllLHSMinus("LTW");
		cat_II_III_CheckPage.enterLHSComments("LTW", "entering ob comments");
		cat_II_III_CheckPage.clickLHSOBDoneButton("LTW");
		cat_II_III_CheckPage.enterCAT_II_III_LHSRemarks("entering lhs remarks");

		// rhs
		if (rhsUserPresent) {
			cat_II_III_CheckPage.clickCAT_II_III_RHSGrade("COM", "3");
			cat_II_III_CheckPage.clickAllRHSMinus("COM");
			cat_II_III_CheckPage.enterRHSComments("COM", "entering ob comments");
			cat_II_III_CheckPage.clickRHSOBDoneButton("COM");
			cat_II_III_CheckPage.enterCAT_II_III_RHSRemarks("entering rhs remarks");
		}
		cat_II_III_CheckPage.clickNextAndSaveButton();
		popupPage.clickPopupOrAlertYesButton();
		String expectedResult = "OK Please select qualification.";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch Expected is : " + expectedResult + " but got : " + actualResult);
		popupPage.clickPopupOkButton();
	}

	@Test(description = "Please enter a comment before submitting.")
	public void validatePleaseEnterACommentBeforeSubmitting() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		boolean rhsUserPresent = cat_II_III_GradingPage.isRHSUserPresent();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		cat_II_TrainingPage.clickCAT_II_LHSGrade("PRO", "3");
		cat_II_TrainingPage.clickAllLHSMinus("PRO");
		cat_II_TrainingPage.enterLHSComments("PRO", "Entering the OB Comments");
		cat_II_TrainingPage.clickLHSOBDoneButton("PRO");
		cat_II_TrainingPage.enterCAT_II_LHSRemarks("adding cat II lhs remarks");

		// rhs CAT II grading
		if (rhsUserPresent) {
			cat_II_TrainingPage.clickCAT_II_RHSGrade("FPM", "3");
			cat_II_TrainingPage.clickAllRHSMinus("FPM");
			cat_II_TrainingPage.enterLHSComments("FPM", "Entering the OB Comments");
			cat_II_TrainingPage.clickLHSOBDoneButton("FPM");
			cat_II_TrainingPage.enterCAT_II_RHSRemarks("adding cat II rhs remarks");
		}

		// lhs CAT III grading
		cat_II_III_TrainingPage.clickCATIIIPanel();
		cat_III_TrainingPage.clickCAT_III_LHSGrade("KNO", "3");
		cat_III_TrainingPage.clickAllLHSMinusButtons("KNO");
		cat_III_TrainingPage.enterLHSComments("KNO", "Entering the OB Comments");
		cat_III_TrainingPage.clickLHSOBDoneButton("KNO");
		cat_III_TrainingPage.enterCAT_III_LHSRemarks("adding cat III lhs remarks");

		// rhs CAT III grading
		if (rhsUserPresent) {
			cat_III_TrainingPage.clickCAT_III_RHSGrade("FPA", "3");
			cat_III_TrainingPage.clickAllRHSMinusButtons("FPA");
			cat_III_TrainingPage.enterRHSComments("FPA", "Entering the OB Comments");
			cat_III_TrainingPage.clickRHSOBDoneButton("FPA");
			cat_III_TrainingPage.enterCAT_III_RHSRemarks("adding cat III rhs remarks");
		}

		cat_II_III_TrainingPage.clickSaveAndNextButton();
		popupPage.clickPopupOrAlertYesButton();

		// check page
		// lhs
		cat_II_III_CheckPage.clickCAT_II_III_LHSGrade("LTW", "3");
		cat_II_III_CheckPage.clickAllLHSMinus("LTW");
		cat_II_III_CheckPage.enterLHSComments("LTW", "entering ob comments");
		cat_II_III_CheckPage.clickLHSOBDoneButton("LTW");
		cat_II_III_CheckPage.enterCAT_II_III_LHSRemarks("entering lhs remarks");

		// rhs
		if (rhsUserPresent) {
			cat_II_III_CheckPage.clickCAT_II_III_RHSGrade("COM", "3");
			cat_II_III_CheckPage.clickAllRHSMinus("COM");
			cat_II_III_CheckPage.enterRHSComments("COM", "entering ob comments");
			cat_II_III_CheckPage.clickRHSOBDoneButton("COM");
			cat_II_III_CheckPage.enterCAT_II_III_RHSRemarks("entering rhs remarks");
		}
		cat_II_III_CheckPage.selectQaulification("SFI");
		cat_II_III_CheckPage.clickNextAndSaveButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_OverallOutcomePage.reasonForDelayLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		String expectedResult = "Please enter a comment before submitting.";
		String actualResult = SeleniumUtils.getAlertText(getDriver());
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch Expected is : " + expectedResult + " but got : " + actualResult);
		SeleniumUtils.acceptAlert(getDriver(), 1);
	}

	@Test(description = "Please provide a signature first.")
	public void validatePleaseProvideAsignatureFirst() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		boolean rhsUserPresent = cat_II_III_GradingPage.isRHSUserPresent();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		cat_II_TrainingPage.clickCAT_II_LHSGrade("PRO", "3");
		cat_II_TrainingPage.clickAllLHSMinus("PRO");
		cat_II_TrainingPage.enterLHSComments("PRO", "Entering the OB Comments");
		cat_II_TrainingPage.clickLHSOBDoneButton("PRO");
		cat_II_TrainingPage.enterCAT_II_LHSRemarks("adding cat II lhs remarks");

		// rhs CAT II grading
		if (rhsUserPresent) {
			cat_II_TrainingPage.clickCAT_II_RHSGrade("FPM", "3");
			cat_II_TrainingPage.clickAllRHSMinus("FPM");
			cat_II_TrainingPage.enterLHSComments("FPM", "Entering the OB Comments");
			cat_II_TrainingPage.clickLHSOBDoneButton("FPM");
			cat_II_TrainingPage.enterCAT_II_RHSRemarks("adding cat II rhs remarks");
		}

		// lhs CAT III grading
		cat_II_III_TrainingPage.clickCATIIIPanel();
		cat_III_TrainingPage.clickCAT_III_LHSGrade("KNO", "3");
		cat_III_TrainingPage.clickAllLHSMinusButtons("KNO");
		cat_III_TrainingPage.enterLHSComments("KNO", "Entering the OB Comments");
		cat_III_TrainingPage.clickLHSOBDoneButton("KNO");
		cat_III_TrainingPage.enterCAT_III_LHSRemarks("adding cat III lhs remarks");

		// rhs CAT III grading
		if (rhsUserPresent) {
			cat_III_TrainingPage.clickCAT_III_RHSGrade("FPA", "3");
			cat_III_TrainingPage.clickAllRHSMinusButtons("FPA");
			cat_III_TrainingPage.enterRHSComments("FPA", "Entering the OB Comments");
			cat_III_TrainingPage.clickRHSOBDoneButton("FPA");
			cat_III_TrainingPage.enterCAT_III_RHSRemarks("adding cat III rhs remarks");
		}

		cat_II_III_TrainingPage.clickSaveAndNextButton();
		popupPage.clickPopupOrAlertYesButton();

		// check page
		// lhs
		cat_II_III_CheckPage.clickCAT_II_III_LHSGrade("LTW", "3");
		cat_II_III_CheckPage.clickAllLHSMinus("LTW");
		cat_II_III_CheckPage.enterLHSComments("LTW", "entering ob comments");
		cat_II_III_CheckPage.clickLHSOBDoneButton("LTW");
		cat_II_III_CheckPage.enterCAT_II_III_LHSRemarks("entering lhs remarks");

		// rhs
		if (rhsUserPresent) {
			cat_II_III_CheckPage.clickCAT_II_III_RHSGrade("COM", "3");
			cat_II_III_CheckPage.clickAllRHSMinus("COM");
			cat_II_III_CheckPage.enterRHSComments("COM", "entering ob comments");
			cat_II_III_CheckPage.clickRHSOBDoneButton("COM");
			cat_II_III_CheckPage.enterCAT_II_III_RHSRemarks("entering rhs remarks");
		}
		cat_II_III_CheckPage.selectQaulification("SFI");
		cat_II_III_CheckPage.clickNextAndSaveButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_OverallOutcomePage.reasonForDelayLabelIsPresent();
		cat_II_III_OverallOutcomePage.addDelayComments("adding delay comments");
		cat_II_III_OverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		cat_II_III_OverallOutcomePage.visibilityOfPreviewHeader();
		cat_II_III_OverallOutcomePage.clickPreviewNextButton();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.digitalSignitureLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		String expectedResult = "Please provide a signature first.";
		String actualResult = SeleniumUtils.getAlertText(getDriver());
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch Expected is : " + expectedResult + " but got : " + actualResult);
		SeleniumUtils.acceptAlert(getDriver(), 1);
	}

	@Test(description = "OK You have selected seat support for both trainees")
	public void validateYouHaveSelectedSeatSupportForBothTrainees() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		boolean rhsUserPresent = cat_II_III_GradingPage.isRHSUserPresent();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.selectSeatSupportLHSCheckbox();
		if (rhsUserPresent) {
			cat_II_III_GradingPage.selectSeatSupportRHSCheckbox();
		}
		cat_II_III_GradingPage.clickSaveAndNextButton();
		String expectedResult = "OK You have selected seat support for both trainees";
		String actualResult = popupPage.popupGetText();
		Assert.assertEquals(expectedResult, actualResult,
				"Text mismatch Expected is : " + expectedResult + " but got : " + actualResult);
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
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		lhsTraineeIdWithName = cat_II_III_GradingPage.getLHSDesignation();
		rhsTraineeIdWithName = cat_II_III_GradingPage.getRHSDesignation();
		lhsTraineeId = lhsTraineeIdWithName.replaceAll("[^0-9]", "");
		rhsTraineeId = rhsTraineeIdWithName.replaceAll("[^0-9]", "");
		cat_II_III_TrainingPage.clickSaveAndNextButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_CheckPage.selectQaulification("SFI");
		cat_II_III_CheckPage.clickNextAndSaveButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_OverallOutcomePage.reasonForDelayLabelIsPresent();
		cat_II_III_OverallOutcomePage.addDelayComments("adding delay comments");
		cat_II_III_OverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		cat_II_III_OverallOutcomePage.visibilityOfPreviewHeader();
		cat_II_III_OverallOutcomePage.clickPreviewNextButton();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.digitalSignitureLabelIsPresent();
		cat_II_III_OverallOutcomePage.digitalSign();
		cat_II_III_OverallOutcomePage.clickClearForDigitalSigniture();
		cat_II_III_OverallOutcomePage.digitalSign();
		cat_II_III_OverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.handelSpinner();
		popupPage.clickPopupOkButton();
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
		if (rhsTraineeId != null && !rhsTraineeId.equals("")) {
			gradingHistoryPage.enterLessonName(rhsTraineeId);
			gradingHistoryPage.clickViewButton();
			gradingHistoryPage.validatePreviewHeader();
			gradingHistoryPage.clickCloseButton();
			gradingHistoryPage.clickViewButton();
			gradingHistoryPage.clickCloseIcon();
		}
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
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		lhsTraineeIdWithName = cat_II_III_GradingPage.getLHSDesignation();
		rhsTraineeIdWithName = cat_II_III_GradingPage.getRHSDesignation();
		lhsTraineeId = lhsTraineeIdWithName.replaceAll("[^0-9]", "");
		rhsTraineeId = rhsTraineeIdWithName.replaceAll("[^0-9]", "");
		cat_II_III_TrainingPage.clickSaveAndNextButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_CheckPage.selectQaulification("SFI");
		cat_II_III_CheckPage.clickNextAndSaveButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_OverallOutcomePage.reasonForDelayLabelIsPresent();
		cat_II_III_OverallOutcomePage.addDelayComments("adding delay comments");
		cat_II_III_OverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		cat_II_III_OverallOutcomePage.visibilityOfPreviewHeader();
		cat_II_III_OverallOutcomePage.clickPreviewNextButton();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.digitalSignitureLabelIsPresent();
		cat_II_III_OverallOutcomePage.digitalSign();
		cat_II_III_OverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.handelSpinner();
		popupPage.clickPopupOkButton();
		traineeGradingPage.validateAllStaticTexts();
		if (lhsTraineeId != null && !lhsTraineeId.equals("")) {
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
		}
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		if (rhsTraineeId != null && !rhsTraineeId.equals("")) {
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
		}
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
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		lhsTraineeIdWithName = cat_II_III_GradingPage.getLHSDesignation();
		rhsTraineeIdWithName = cat_II_III_GradingPage.getRHSDesignation();
		lhsTraineeId = lhsTraineeIdWithName.replaceAll("[^0-9]", "");
		rhsTraineeId = rhsTraineeIdWithName.replaceAll("[^0-9]", "");
		System.out.println(lhsTraineeId);
		System.out.println(rhsTraineeId);
		cat_II_III_TrainingPage.clickSaveAndNextButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_CheckPage.selectQaulification("SFI");
		cat_II_III_CheckPage.clickNextAndSaveButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_OverallOutcomePage.reasonForDelayLabelIsPresent();
		cat_II_III_OverallOutcomePage.addDelayComments("adding delay comments");
		cat_II_III_OverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		cat_II_III_OverallOutcomePage.visibilityOfPreviewHeader();
		cat_II_III_OverallOutcomePage.clickPreviewNextButton();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.digitalSignitureLabelIsPresent();
		cat_II_III_OverallOutcomePage.digitalSign();
		cat_II_III_OverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.handelSpinner();
		popupPage.clickPopupOkButton();
		if (lhsTraineeId != null && !lhsTraineeId.equals("")) {
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
		}
		if (rhsTraineeId != null && !rhsTraineeId.equals("")) {
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
		}
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
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		lhsTraineeIdWithName = cat_II_III_GradingPage.getLHSDesignation();
		rhsTraineeIdWithName = cat_II_III_GradingPage.getRHSDesignation();
		lhsTraineeId = lhsTraineeIdWithName.replaceAll("[^0-9]", "");
		rhsTraineeId = rhsTraineeIdWithName.replaceAll("[^0-9]", "");
		System.out.println(lhsTraineeId);
		System.out.println(rhsTraineeId);
		cat_II_III_TrainingPage.clickSaveAndNextButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_CheckPage.selectQaulification("SFI");
		cat_II_III_CheckPage.clickNextAndSaveButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_OverallOutcomePage.reasonForDelayLabelIsPresent();
		cat_II_III_OverallOutcomePage.addDelayComments("adding delay comments");
		cat_II_III_OverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		cat_II_III_OverallOutcomePage.visibilityOfPreviewHeader();
		cat_II_III_OverallOutcomePage.clickPreviewNextButton();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.digitalSignitureLabelIsPresent();
		cat_II_III_OverallOutcomePage.digitalSign();
		cat_II_III_OverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.handelSpinner();
		popupPage.clickPopupOkButton();
		traineeGradingPage.validateAllStaticTexts();

		if (lhsTraineeId != null && !lhsTraineeId.equals("")) {
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
		}

		// rhs
		if (rhsTraineeId != null && !rhsTraineeId.equals("")) {
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
		}

		// trainer side
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		if (lhsTraineeId != null && !lhsTraineeId.equals("")) {
			pendingHistoryPage.validatePengingGradingStaticTexts();
			pendingHistoryPage.enterSearchText(lhsTraineeId);
			pendingHistoryPage.clickReviewButton();
			popupPage.handelSpinner();
			cat_II_III_GradingPage.clickEditEventButton();
			pendingHistoryPage.validateUpdateGeneralInfoPopupLabelText();
			pendingHistoryPage.clickUpdateGeneralInfoPopupNoButton();
			cat_II_III_GradingPage.clickEditEventButton();
			pendingHistoryPage.clickUpdateGeneralInfoPopupYesButton();
			popupPage.handelOneBeforeUnload();
		}
		if (rhsTraineeId != null && !rhsTraineeId.equals("")) {
			pendingHistoryPage.validatePengingGradingStaticTexts();
			pendingHistoryPage.enterSearchText(rhsTraineeId);
			pendingHistoryPage.clickReviewButton();
			popupPage.handelSpinner();
			cat_II_III_GradingPage.clickEditEventButton();
			pendingHistoryPage.validateUpdateGeneralInfoPopupLabelText();
			pendingHistoryPage.clickUpdateGeneralInfoPopupNoButton();
			cat_II_III_GradingPage.clickEditEventButton();
			pendingHistoryPage.clickUpdateGeneralInfoPopupYesButton();
			popupPage.handelOneBeforeUnload();
		}
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
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		lhsTraineeIdWithName = cat_II_III_GradingPage.getLHSDesignation();
		rhsTraineeIdWithName = cat_II_III_GradingPage.getRHSDesignation();
		lhsTraineeId = lhsTraineeIdWithName.replaceAll("[^0-9]", "");
		rhsTraineeId = rhsTraineeIdWithName.replaceAll("[^0-9]", "");
		System.out.println(lhsTraineeId);
		System.out.println(rhsTraineeId);
		cat_II_III_TrainingPage.clickSaveAndNextButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_CheckPage.selectQaulification("SFI");
		cat_II_III_CheckPage.clickNextAndSaveButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_OverallOutcomePage.reasonForDelayLabelIsPresent();
		cat_II_III_OverallOutcomePage.addDelayComments("adding delay comments");
		cat_II_III_OverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		cat_II_III_OverallOutcomePage.visibilityOfPreviewHeader();
		cat_II_III_OverallOutcomePage.clickPreviewNextButton();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.digitalSignitureLabelIsPresent();
		cat_II_III_OverallOutcomePage.digitalSign();
		cat_II_III_OverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.handelSpinner();
		popupPage.clickPopupOkButton();
		traineeGradingPage.validateAllStaticTexts();

		// lhs
		if (lhsTraineeId != null && !lhsTraineeId.equals("")) {
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
		}

		// rhs
		if (rhsTraineeId != null && !rhsTraineeId.equals("")) {
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
		}

		// trainer for lhs user

		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		if (lhsTraineeId != null && !lhsTraineeId.equals("")) {
			pendingHistoryPage.validatePengingGradingStaticTexts();
			pendingHistoryPage.enterSearchText(lhsTraineeId);
			pendingHistoryPage.clickReviewButton();
			popupPage.handelSpinner();
			cat_II_III_GradingPage.clickEditEventButton();
			pendingHistoryPage.validateUpdateGeneralInfoPopupLabelText();
			pendingHistoryPage.clickUpdateGeneralInfoPopupNoButton();
			cat_II_III_GradingPage.clickEditEventButton();
			pendingHistoryPage.clickUpdateGeneralInfoPopupYesButton();
			popupPage.handelOneBeforeUnload();
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
			traineeReviewPage.clickSubmitButtonForInstructorAcknowldgement();
			traineeReviewPage.digitalSignitureLabelIsPresent();
			traineeReviewPage.digitalSign();
			traineeReviewPage.clickSaveSignitureButtonForDigitalSigniture();
			traineeReviewPage.clickOkPop_up();
			popupPage.handelSpinner();
			logoutPage.clickProfileIcon();
			logoutPage.clickLogoutButton();
			adminDashBoardPage.clickBecomeUserTab();
			becomeUserPage.sendUserId();
			becomeUserPage.clickOnBecomeUser();
			trainerDashBoradPage.clickOnGradingAssessmentTab();
			trainerDashBoradPage.clickOnGradingSubTab();
			traineeGradingPage.validateAllStaticTexts();
		}
		if (rhsTraineeId != null && !rhsTraineeId.equals("")) {
			pendingHistoryPage.validatePengingGradingStaticTexts();
			pendingHistoryPage.enterSearchText(rhsTraineeId);
			pendingHistoryPage.clickReviewButton();
			popupPage.handelSpinner();
			cat_II_III_GradingPage.clickEditEventButton();
			pendingHistoryPage.validateUpdateGeneralInfoPopupLabelText();
			pendingHistoryPage.clickUpdateGeneralInfoPopupNoButton();
			cat_II_III_GradingPage.clickEditEventButton();
			pendingHistoryPage.clickUpdateGeneralInfoPopupYesButton();
			popupPage.handelOneBeforeUnload();
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
			traineeReviewPage.clickSubmitButtonForInstructorAcknowldgement();
			traineeReviewPage.digitalSignitureLabelIsPresent();
			traineeReviewPage.digitalSign();
			traineeReviewPage.clickSaveSignitureButtonForDigitalSigniture();
			traineeReviewPage.clickOkPop_up();
			traineeReviewPage.validateAllStaticElements();
		}
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
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		lhsTraineeIdWithName = cat_II_III_GradingPage.getLHSDesignation();
		rhsTraineeIdWithName = cat_II_III_GradingPage.getRHSDesignation();
		lhsTraineeId = lhsTraineeIdWithName.replaceAll("[^0-9]", "");
		rhsTraineeId = rhsTraineeIdWithName.replaceAll("[^0-9]", "");
		System.out.println(lhsTraineeId);
		System.out.println(rhsTraineeId);
		cat_II_III_TrainingPage.clickSaveAndNextButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_CheckPage.selectQaulification("SFI");
		cat_II_III_CheckPage.clickNextAndSaveButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_OverallOutcomePage.reasonForDelayLabelIsPresent();
		cat_II_III_OverallOutcomePage.addDelayComments("adding delay comments");
		cat_II_III_OverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		cat_II_III_OverallOutcomePage.visibilityOfPreviewHeader();
		cat_II_III_OverallOutcomePage.clickPreviewNextButton();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.digitalSignitureLabelIsPresent();
		cat_II_III_OverallOutcomePage.digitalSign();
		cat_II_III_OverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.handelSpinner();
		popupPage.clickPopupOkButton();
		traineeGradingPage.validateAllStaticTexts();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();

		// lhs
		if (lhsTraineeId != null && !lhsTraineeId.equals("")) {
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
			adminDashBoardPage.clickGradingAndAssessmentTab();
			adminDashBoardPage.clickTrainingManagerReviewSubTab();
			trainingManagerReviewPage.validateAllTableHeaders();
			trainingManagerReviewPage.presenceOfViewButton();
			trainingManagerReviewPage.searchforTrainee(lhsTraineeId);
			trainingManagerReviewPage.clickViewButton();
			trainingManagerReviewPage.enterComment("adding comments");
			trainingManagerReviewPage.clickApproveButton();
			trainingManagerReviewPage.validateTextAreSureYouWantToApprove();
			trainingManagerReviewPage.clickYesButtonForApprove();
			String actualLHSText = trainingManagerReviewPage.getTextOfPopup();
			String expectedLHSText = "OK Approved successfully";
			Assert.assertEquals(actualLHSText, expectedLHSText,
					"Text mismatch : expected " + expectedLHSText + " but got " + actualLHSText);
			trainingManagerReviewPage.clickOkPopupButton();
		}

		// rhs

		if (rhsTraineeId != null && !rhsTraineeId.equals("")) {
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
			logoutPage.clickProfileIcon();
			logoutPage.clickLogoutButton();
			adminDashBoardPage.clickGradingAndAssessmentTab();
			adminDashBoardPage.clickTrainingManagerReviewSubTab();
			trainingManagerReviewPage.validateAllTableHeaders();
			trainingManagerReviewPage.presenceOfViewButton();
			trainingManagerReviewPage.searchforTrainee(rhsTraineeId);
			trainingManagerReviewPage.clickViewButton();
			trainingManagerReviewPage.enterComment("adding comments");
			trainingManagerReviewPage.clickApproveButton();
			trainingManagerReviewPage.validateTextAreSureYouWantToApprove();
			trainingManagerReviewPage.clickYesButtonForApprove();
			String actualRHSText = trainingManagerReviewPage.getTextOfPopup();
			String expectedRHSText = "OK Approved successfully";
			Assert.assertEquals(actualRHSText, expectedRHSText,
					"Text mismatch : expected " + expectedRHSText + " but got " + actualRHSText);
			trainingManagerReviewPage.clickOkPopupButton();
		}
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
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		lhsTraineeIdWithName = cat_II_III_GradingPage.getLHSDesignation();
		rhsTraineeIdWithName = cat_II_III_GradingPage.getRHSDesignation();
		lhsTraineeId = lhsTraineeIdWithName.replaceAll("[^0-9]", "");
		rhsTraineeId = rhsTraineeIdWithName.replaceAll("[^0-9]", "");
		System.out.println(lhsTraineeId);
		System.out.println(rhsTraineeId);
		cat_II_III_TrainingPage.clickSaveAndNextButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_CheckPage.selectQaulification("SFI");
		cat_II_III_CheckPage.clickNextAndSaveButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_OverallOutcomePage.reasonForDelayLabelIsPresent();
		cat_II_III_OverallOutcomePage.addDelayComments("adding delay comments");
		cat_II_III_OverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		cat_II_III_OverallOutcomePage.visibilityOfPreviewHeader();
		cat_II_III_OverallOutcomePage.clickPreviewNextButton();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.digitalSignitureLabelIsPresent();
		cat_II_III_OverallOutcomePage.digitalSign();
		cat_II_III_OverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.handelSpinner();
		popupPage.clickPopupOkButton();
		traineeGradingPage.validateAllStaticTexts();

		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();

		if (lhsTraineeId != null && !lhsTraineeId.equals("")) {
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
			adminDashBoardPage.clickGradingAndAssessmentTab();
			adminDashBoardPage.clickTrainingManagerReviewSubTab();
			trainingManagerReviewPage.validateAllTableHeaders();
			trainingManagerReviewPage.presenceOfViewButton();
			trainingManagerReviewPage.searchforTrainee(lhsTraineeId);
			trainingManagerReviewPage.clickViewButton();
			trainingManagerReviewPage.clickMarkForReviewButton();
			String actualLHSText = trainingManagerReviewPage.getTextOfPopup();
			String expectedLHSText = "OK Please enter comments.";
			Assert.assertEquals(actualLHSText, expectedLHSText,
					"Text mismatch : expected " + expectedLHSText + " but got " + actualLHSText);
			trainingManagerReviewPage.clickOkPopupButton();
			trainingManagerReviewPage.clickCloseIcon();
		}
		// rhs
		if (rhsTraineeId != null && !rhsTraineeId.equals("")) {
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
			logoutPage.clickProfileIcon();
			logoutPage.clickLogoutButton();
			adminDashBoardPage.clickGradingAndAssessmentTab();
			adminDashBoardPage.clickTrainingManagerReviewSubTab();
			trainingManagerReviewPage.validateAllTableHeaders();
			trainingManagerReviewPage.presenceOfViewButton();
			trainingManagerReviewPage.searchforTrainee(rhsTraineeId);
			trainingManagerReviewPage.clickViewButton();
			trainingManagerReviewPage.clickMarkForReviewButton();
			String actualRHSText = trainingManagerReviewPage.getTextOfPopup();
			String expectedRHSText = "OK Please enter comments.";
			Assert.assertEquals(actualRHSText, expectedRHSText,
					"Text mismatch : expected " + expectedRHSText + " but got " + actualRHSText);
			trainingManagerReviewPage.clickOkPopupButton();
		}
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
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		lhsTraineeIdWithName = cat_II_III_GradingPage.getLHSDesignation();
		rhsTraineeIdWithName = cat_II_III_GradingPage.getRHSDesignation();
		lhsTraineeId = lhsTraineeIdWithName.replaceAll("[^0-9]", "");
		rhsTraineeId = rhsTraineeIdWithName.replaceAll("[^0-9]", "");
		System.out.println(lhsTraineeId);
		System.out.println(rhsTraineeId);
		cat_II_III_TrainingPage.clickSaveAndNextButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_CheckPage.selectQaulification("SFI");
		cat_II_III_CheckPage.clickNextAndSaveButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_OverallOutcomePage.reasonForDelayLabelIsPresent();
		cat_II_III_OverallOutcomePage.addDelayComments("adding delay comments");
		cat_II_III_OverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		cat_II_III_OverallOutcomePage.visibilityOfPreviewHeader();
		cat_II_III_OverallOutcomePage.clickPreviewNextButton();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.digitalSignitureLabelIsPresent();
		cat_II_III_OverallOutcomePage.digitalSign();
		cat_II_III_OverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.handelSpinner();
		popupPage.clickPopupOkButton();
		traineeGradingPage.validateAllStaticTexts();

		popupPage.handelSpinner();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();

		// lhs
		if (lhsTraineeId != null && !lhsTraineeId.equals("")) {
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
			adminDashBoardPage.clickGradingAndAssessmentTab();
			adminDashBoardPage.clickTrainingManagerReviewSubTab();
			trainingManagerReviewPage.validateAllTableHeaders();
			trainingManagerReviewPage.presenceOfViewButton();
			trainingManagerReviewPage.searchforTrainee(lhsTraineeId);
			trainingManagerReviewPage.clickViewButton();
			trainingManagerReviewPage.enterComment("entering review comments");
			trainingManagerReviewPage.clickMarkForReviewButton();
			trainingManagerReviewPage.validateTextAreSureYouWantToMarkForReview();
			trainingManagerReviewPage.clickYesButtonForReview();
			String actualLHSText = popupPage.alertGetText();
			String expectedLHSText = "The training event has been marked for review.";
			Assert.assertEquals(actualLHSText, expectedLHSText,
					"Text mismatch : expected " + expectedLHSText + " but got " + actualLHSText);
			trainingManagerReviewPage.clickOkPopupButton();
		}
		// rhs
		if (rhsTraineeId != null && !rhsTraineeId.equals("")) {
			popupPage.handelSpinner();
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
			logoutPage.clickProfileIcon();
			logoutPage.clickLogoutButton();
			adminDashBoardPage.clickGradingAndAssessmentTab();
			adminDashBoardPage.clickTrainingManagerReviewSubTab();
			trainingManagerReviewPage.validateAllTableHeaders();
			trainingManagerReviewPage.presenceOfViewButton();
			trainingManagerReviewPage.searchforTrainee(rhsTraineeId);
			trainingManagerReviewPage.clickViewButton();
			trainingManagerReviewPage.enterComment("entering review comments");
			trainingManagerReviewPage.clickMarkForReviewButton();
			trainingManagerReviewPage.validateTextAreSureYouWantToMarkForReview();
			trainingManagerReviewPage.clickYesButtonForReview();
			String actualRHSText = popupPage.alertGetText();
			String expectedRHSText = "The training event has been marked for review.";
			Assert.assertEquals(actualRHSText, expectedRHSText,
					"Text mismatch : expected " + expectedRHSText + " but got " + actualRHSText);
			trainingManagerReviewPage.clickOkPopupButton();
		}
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
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		lhsTraineeIdWithName = cat_II_III_GradingPage.getLHSDesignation();
		rhsTraineeIdWithName = cat_II_III_GradingPage.getRHSDesignation();
		lhsTraineeId = lhsTraineeIdWithName.replaceAll("[^0-9]", "");
		rhsTraineeId = rhsTraineeIdWithName.replaceAll("[^0-9]", "");
		System.out.println(lhsTraineeId);
		System.out.println(rhsTraineeId);
		cat_II_III_TrainingPage.clickSaveAndNextButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_CheckPage.selectQaulification("SFI");
		cat_II_III_CheckPage.clickNextAndSaveButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_OverallOutcomePage.reasonForDelayLabelIsPresent();
		cat_II_III_OverallOutcomePage.addDelayComments("adding delay comments");
		cat_II_III_OverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		cat_II_III_OverallOutcomePage.visibilityOfPreviewHeader();
		cat_II_III_OverallOutcomePage.clickPreviewNextButton();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.digitalSignitureLabelIsPresent();
		cat_II_III_OverallOutcomePage.digitalSign();
		cat_II_III_OverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.handelSpinner();
		popupPage.clickPopupOkButton();
		traineeGradingPage.validateAllStaticTexts();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();

		// lhs
		if (lhsTraineeId != null && !lhsTraineeId.equals("")) {
			adminDashBoardPage.clickGradingAndAssessmentTab();
			adminDashBoardPage.clickTrainingManagerReviewSubTab();
			trainingManagerReviewPage.validateAllTableHeaders();
			trainingManagerReviewPage.presenceOfViewButton();
			trainingManagerReviewPage.searchforTrainee(lhsTraineeId);
			trainingManagerReviewPage.clickViewButton();
			boolean isAbsent = trainingManagerReviewPage.isMarkForReviewButtonAbsent();
			Assert.assertTrue(isAbsent,
					"Mark for Review button should be absent for LHS trainee after trainer form submission");
			trainingManagerReviewPage.clickCloseIcon();
		}
		// rhs
		if (rhsTraineeId != null && !rhsTraineeId.equals("")) {
			adminDashBoardPage.clickGradingAndAssessmentTab();
			adminDashBoardPage.clickTrainingManagerReviewSubTab();
			trainingManagerReviewPage.validateAllTableHeaders();
			trainingManagerReviewPage.presenceOfViewButton();
			trainingManagerReviewPage.searchforTrainee(rhsTraineeId);
			trainingManagerReviewPage.clickViewButton();
			boolean isRHSAbsent = trainingManagerReviewPage.isMarkForReviewButtonAbsent();
			Assert.assertTrue(isRHSAbsent,
					"Mark for Review button should be absent for RHS trainee after trainer form submission");
			trainingManagerReviewPage.clickCloseIcon();
		}
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
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		lhsTraineeIdWithName = cat_II_III_GradingPage.getLHSDesignation();
		rhsTraineeIdWithName = cat_II_III_GradingPage.getRHSDesignation();
		lhsTraineeId = lhsTraineeIdWithName.replaceAll("[^0-9]", "");
		rhsTraineeId = rhsTraineeIdWithName.replaceAll("[^0-9]", "");
		System.out.println(lhsTraineeId);
		System.out.println(rhsTraineeId);
		cat_II_III_TrainingPage.clickSaveAndNextButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_CheckPage.selectQaulification("SFI");
		cat_II_III_CheckPage.clickNextAndSaveButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_OverallOutcomePage.reasonForDelayLabelIsPresent();
		cat_II_III_OverallOutcomePage.addDelayComments("adding delay comments");
		cat_II_III_OverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		cat_II_III_OverallOutcomePage.visibilityOfPreviewHeader();
		cat_II_III_OverallOutcomePage.clickPreviewNextButton();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.digitalSignitureLabelIsPresent();
		cat_II_III_OverallOutcomePage.digitalSign();
		cat_II_III_OverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.handelSpinner();
		popupPage.clickPopupOkButton();
		traineeGradingPage.validateAllStaticTexts();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();

		// lhs
		if (lhsTraineeId != null && !lhsTraineeId.equals("")) {
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
			adminDashBoardPage.clickGradingAndAssessmentTab();
			adminDashBoardPage.clickTrainingManagerReviewSubTab();
			trainingManagerReviewPage.validateAllTableHeaders();
			trainingManagerReviewPage.presenceOfViewButton();
			trainingManagerReviewPage.searchforTrainee(lhsTraineeId);
			trainingManagerReviewPage.clickViewButton();
			trainingManagerReviewPage.enterComment("adding comments");
			trainingManagerReviewPage.clickApproveButton();
			trainingManagerReviewPage.validateTextAreSureYouWantToApprove();
			trainingManagerReviewPage.clickYesButtonForApprove();
			String actualLHSText = trainingManagerReviewPage.getTextOfPopup();
			String expectedLHSText = "OK Approved successfully";
			Assert.assertEquals(actualLHSText, expectedLHSText,
					"Text mismatch : expected " + expectedLHSText + " but got " + actualLHSText);
			trainingManagerReviewPage.clickOkPopupButton();
			userDocsPage.clickUserDocsTab();
			userDocsPage.clickApprovedDocsTab();
			userDocsPage.validateAllTexts();
			userDocsPage.clickEyeIcon();
			userDocsPage.clickCloseIcon();
			userDocsPage.clickEyeIcon();
			userDocsPage.clickUploadedDocumentLink();
			userDocsPage.clickCloseIcon();
		}

		// rhs
		if (rhsTraineeId != null && !rhsTraineeId.equals("")) {
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
			logoutPage.clickProfileIcon();
			logoutPage.clickLogoutButton();
			adminDashBoardPage.clickGradingAndAssessmentTab();
			adminDashBoardPage.clickTrainingManagerReviewSubTab();
			trainingManagerReviewPage.validateAllTableHeaders();
			trainingManagerReviewPage.presenceOfViewButton();
			trainingManagerReviewPage.searchforTrainee(rhsTraineeId);
			trainingManagerReviewPage.clickViewButton();
			trainingManagerReviewPage.enterComment("adding comments");
			trainingManagerReviewPage.clickApproveButton();
			trainingManagerReviewPage.validateTextAreSureYouWantToApprove();
			trainingManagerReviewPage.clickYesButtonForApprove();
			String actualRHSText = trainingManagerReviewPage.getTextOfPopup();
			String expectedRHSText = "OK Approved successfully";
			Assert.assertEquals(actualRHSText, expectedRHSText,
					"Text mismatch : expected " + expectedRHSText + " but got " + actualRHSText);
			trainingManagerReviewPage.clickOkPopupButton();
			userDocsPage.clickUserDocsTab();
			userDocsPage.clickApprovedDocsTab();
			userDocsPage.validateAllTexts();
			userDocsPage.clickEyeIcon();
			userDocsPage.clickCloseIcon();
			userDocsPage.clickEyeIcon();
			userDocsPage.clickUploadedDocumentLink();
			userDocsPage.clickCloseIcon();
		}
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
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		lhsTraineeIdWithName = cat_II_III_GradingPage.getLHSDesignation();
		rhsTraineeIdWithName = cat_II_III_GradingPage.getRHSDesignation();
		lhsTraineeId = lhsTraineeIdWithName.replaceAll("[^0-9]", "");
		rhsTraineeId = rhsTraineeIdWithName.replaceAll("[^0-9]", "");
		System.out.println(lhsTraineeId);
		System.out.println(rhsTraineeId);
		cat_II_III_TrainingPage.clickSaveAndNextButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_CheckPage.selectQaulification("SFI");
		cat_II_III_CheckPage.clickNextAndSaveButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_OverallOutcomePage.reasonForDelayLabelIsPresent();
		cat_II_III_OverallOutcomePage.addDelayComments("adding delay comments");
		cat_II_III_OverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		cat_II_III_OverallOutcomePage.visibilityOfPreviewHeader();
		cat_II_III_OverallOutcomePage.clickPreviewNextButton();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.digitalSignitureLabelIsPresent();
		cat_II_III_OverallOutcomePage.digitalSign();
		cat_II_III_OverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.handelSpinner();
		popupPage.clickPopupOkButton();
		traineeGradingPage.validateAllStaticTexts();
		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();

		// lhs
		if (lhsTraineeId != null && !lhsTraineeId.equals("")) {
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
			adminDashBoardPage.clickGradingAndAssessmentTab();
			adminDashBoardPage.clickTrainingManagerReviewSubTab();
			trainingManagerReviewPage.validateAllTableHeaders();
			trainingManagerReviewPage.presenceOfViewButton();
			trainingManagerReviewPage.searchforTrainee(lhsTraineeId);
			trainingManagerReviewPage.clickViewButton();
			trainingManagerReviewPage.enterComment("adding comments");
			trainingManagerReviewPage.clickApproveButton();
			trainingManagerReviewPage.validateTextAreSureYouWantToApprove();
			trainingManagerReviewPage.clickYesButtonForApprove();
			String actualLHSText = trainingManagerReviewPage.getTextOfPopup();
			String expectedLHSText = "OK Approved successfully";
			Assert.assertEquals(actualLHSText, expectedLHSText,
					"Text mismatch : expected " + expectedLHSText + " but got " + actualLHSText);
			trainingManagerReviewPage.clickOkPopupButton();
			adminDashBoardPage.clickReportsTab();
			adminDashBoardPage.clickEformReportsSubTab();
			eFormReportsPage.validateAllStaticElements();
		}

		// rhs
		if (rhsTraineeId != null && !rhsTraineeId.equals("")) {
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
			logoutPage.clickProfileIcon();
			logoutPage.clickLogoutButton();
			adminDashBoardPage.clickGradingAndAssessmentTab();
			adminDashBoardPage.clickTrainingManagerReviewSubTab();
			trainingManagerReviewPage.validateAllTableHeaders();
			trainingManagerReviewPage.presenceOfViewButton();
			trainingManagerReviewPage.searchforTrainee(rhsTraineeId);
			trainingManagerReviewPage.clickViewButton();
			trainingManagerReviewPage.enterComment("adding comments");
			trainingManagerReviewPage.clickApproveButton();
			trainingManagerReviewPage.validateTextAreSureYouWantToApprove();
			trainingManagerReviewPage.clickYesButtonForApprove();
			String actualRHSText = trainingManagerReviewPage.getTextOfPopup();
			String expectedRHSText = "OK Approved successfully";
			Assert.assertEquals(actualRHSText, expectedRHSText,
					"Text mismatch : expected " + expectedRHSText + " but got " + actualRHSText);
			trainingManagerReviewPage.clickOkPopupButton();
			adminDashBoardPage.clickReportsTab();
			adminDashBoardPage.clickEformReportsSubTab();
			eFormReportsPage.validateAllStaticElements();
		}
	}

	// Starts from here
	@Test(description = "Validate reports can be downloaded as a .zip file when multiple checkboxes are selected")
	public void validateReportsDownloadAsZipWhenMultipleCheckboxesSelected() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		lhsTraineeIdWithName = cat_II_III_GradingPage.getLHSDesignation();
		rhsTraineeIdWithName = cat_II_III_GradingPage.getRHSDesignation();
		lhsTraineeId = lhsTraineeIdWithName.replaceAll("[^0-9]", "");
		rhsTraineeId = rhsTraineeIdWithName.replaceAll("[^0-9]", "");
		System.out.println(lhsTraineeId);
		System.out.println(rhsTraineeId);
		cat_II_III_TrainingPage.clickSaveAndNextButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_CheckPage.selectQaulification("SFI");
		cat_II_III_CheckPage.clickNextAndSaveButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_OverallOutcomePage.reasonForDelayLabelIsPresent();
		cat_II_III_OverallOutcomePage.addDelayComments("adding delay comments");
		cat_II_III_OverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		cat_II_III_OverallOutcomePage.visibilityOfPreviewHeader();
		cat_II_III_OverallOutcomePage.clickPreviewNextButton();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.digitalSignitureLabelIsPresent();
		cat_II_III_OverallOutcomePage.digitalSign();
		cat_II_III_OverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.handelSpinner();
		popupPage.clickPopupOkButton();
		traineeGradingPage.validateAllStaticTexts();

		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();

		// lhs
		if (lhsTraineeId != null && !lhsTraineeId.equals("")) {
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
			adminDashBoardPage.clickGradingAndAssessmentTab();
			adminDashBoardPage.clickTrainingManagerReviewSubTab();
			trainingManagerReviewPage.validateAllTableHeaders();
			trainingManagerReviewPage.presenceOfViewButton();
			trainingManagerReviewPage.searchforTrainee(lhsTraineeId);
			trainingManagerReviewPage.clickViewButton();
			trainingManagerReviewPage.enterComment("adding comments");
			trainingManagerReviewPage.clickApproveButton();
			trainingManagerReviewPage.validateTextAreSureYouWantToApprove();
			trainingManagerReviewPage.clickYesButtonForApprove();
			String actualLHSText = trainingManagerReviewPage.getTextOfPopup();
			String expectedLHSText = "OK Approved successfully";
			Assert.assertEquals(actualLHSText, expectedLHSText,
					"Text mismatch : expected " + expectedLHSText + " but got " + actualLHSText);
			trainingManagerReviewPage.clickOkPopupButton();
			adminDashBoardPage.clickReportsTab();
			adminDashBoardPage.clickEformReportsSubTab();
			eFormReportsPage.validateAllStaticElements();
			eFormReportsPage.clickCheckBoxesButton();
			eFormReportsPage.clickDownloadButtton();
		}

		// rhs
		if (rhsTraineeId != null && !rhsTraineeId.equals("")) {
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
			logoutPage.clickProfileIcon();
			logoutPage.clickLogoutButton();
			adminDashBoardPage.clickGradingAndAssessmentTab();
			adminDashBoardPage.clickTrainingManagerReviewSubTab();
			trainingManagerReviewPage.validateAllTableHeaders();
			trainingManagerReviewPage.presenceOfViewButton();
			trainingManagerReviewPage.searchforTrainee(rhsTraineeId);
			trainingManagerReviewPage.clickViewButton();
			trainingManagerReviewPage.enterComment("adding comments");
			trainingManagerReviewPage.clickApproveButton();
			trainingManagerReviewPage.validateTextAreSureYouWantToApprove();
			trainingManagerReviewPage.clickYesButtonForApprove();
			String actualRHSText = trainingManagerReviewPage.getTextOfPopup();
			String expectedRHSText = "OK Approved successfully";
			Assert.assertEquals(actualRHSText, expectedRHSText,
					"Text mismatch : expected " + expectedRHSText + " but got " + actualRHSText);
			trainingManagerReviewPage.clickOkPopupButton();
			adminDashBoardPage.clickReportsTab();
			adminDashBoardPage.clickEformReportsSubTab();
			eFormReportsPage.validateAllStaticElements();
			eFormReportsPage.clickCheckBoxesButton();
			eFormReportsPage.clickDownloadButtton();
		}
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
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		lhsTraineeIdWithName = cat_II_III_GradingPage.getLHSDesignation();
		rhsTraineeIdWithName = cat_II_III_GradingPage.getRHSDesignation();
		lhsTraineeId = lhsTraineeIdWithName.replaceAll("[^0-9]", "");
		rhsTraineeId = rhsTraineeIdWithName.replaceAll("[^0-9]", "");
		System.out.println(lhsTraineeId);
		System.out.println(rhsTraineeId);
		cat_II_III_TrainingPage.clickSaveAndNextButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_CheckPage.selectQaulification("SFI");
		cat_II_III_CheckPage.clickNextAndSaveButton();
		popupPage.clickPopupOrAlertYesButton();
		cat_II_III_OverallOutcomePage.reasonForDelayLabelIsPresent();
		cat_II_III_OverallOutcomePage.addDelayComments("adding delay comments");
		cat_II_III_OverallOutcomePage.clickSubmitCommentButtonForDelayComment();
		popupPage.handelSpinner();
		cat_II_III_OverallOutcomePage.visibilityOfPreviewHeader();
		cat_II_III_OverallOutcomePage.clickPreviewNextButton();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.instructorAcknowldgementLabelIsPresent();
		cat_II_III_OverallOutcomePage.clickSubmitButtonForInstructorAcknowldgement();
		cat_II_III_OverallOutcomePage.digitalSignitureLabelIsPresent();
		cat_II_III_OverallOutcomePage.digitalSign();
		cat_II_III_OverallOutcomePage.clickSaveSignitureButtonForDigitalSigniture();
		popupPage.handelSpinner();
		popupPage.clickPopupOkButton();
		traineeGradingPage.validateAllStaticTexts();

		logoutPage.clickProfileIcon();
		logoutPage.clickLogoutButton();

		// lhs
		if (lhsTraineeId != null && !lhsTraineeId.equals("")) {
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
			adminDashBoardPage.clickGradingAndAssessmentTab();
			adminDashBoardPage.clickTrainingManagerReviewSubTab();
			trainingManagerReviewPage.validateAllTableHeaders();
			trainingManagerReviewPage.presenceOfViewButton();
			trainingManagerReviewPage.searchforTrainee(lhsTraineeId);
			trainingManagerReviewPage.clickViewButton();
			trainingManagerReviewPage.enterComment("adding comments");
			trainingManagerReviewPage.clickApproveButton();
			trainingManagerReviewPage.validateTextAreSureYouWantToApprove();
			trainingManagerReviewPage.clickYesButtonForApprove();
			String actualLHSText = trainingManagerReviewPage.getTextOfPopup();
			String expectedLHSText = "OK Approved successfully";
			Assert.assertEquals(actualLHSText, expectedLHSText,
					"Text mismatch : expected " + expectedLHSText + " but got " + actualLHSText);
			trainingManagerReviewPage.clickOkPopupButton();
			adminDashBoardPage.clickReportsTab();
			adminDashBoardPage.clickEformReportsSubTab();
			eFormReportsPage.validateAllStaticElements();
			eFormReportsPage.searchForTrainee(lhsTraineeId);
			eFormReportsPage.clickCheckBoxesButton();
			eFormReportsPage.clickDownloadButtton();
		}

		// rhs
		if (rhsTraineeId != null && !rhsTraineeId.equals("")) {
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
			logoutPage.clickProfileIcon();
			logoutPage.clickLogoutButton();
			adminDashBoardPage.clickGradingAndAssessmentTab();
			adminDashBoardPage.clickTrainingManagerReviewSubTab();
			trainingManagerReviewPage.validateAllTableHeaders();
			trainingManagerReviewPage.presenceOfViewButton();
			trainingManagerReviewPage.searchforTrainee(rhsTraineeId);
			trainingManagerReviewPage.clickViewButton();
			trainingManagerReviewPage.enterComment("adding comments");
			trainingManagerReviewPage.clickApproveButton();
			trainingManagerReviewPage.validateTextAreSureYouWantToApprove();
			trainingManagerReviewPage.clickYesButtonForApprove();
			String actualRHSText = trainingManagerReviewPage.getTextOfPopup();
			String expectedRHSText = "OK Approved successfully";
			Assert.assertEquals(actualRHSText, expectedRHSText,
					"Text mismatch : expected " + expectedRHSText + " but got " + actualRHSText);
			trainingManagerReviewPage.clickOkPopupButton();
			adminDashBoardPage.clickReportsTab();
			adminDashBoardPage.clickEformReportsSubTab();
			eFormReportsPage.validateAllStaticElements();
			eFormReportsPage.searchForTrainee(rhsTraineeId);
			eFormReportsPage.clickCheckBoxesButton();
			eFormReportsPage.clickDownloadButtton();
		}
	}
}
