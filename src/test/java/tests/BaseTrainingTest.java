package tests;

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
	private String lhsTraineeIdWithName;
	private String lhsTraineeId;
	private String rhsTraineeIdWithName;
	private String rhsTraineeId;
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
}
