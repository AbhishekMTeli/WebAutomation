package tests;

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
import practiceBookSessionPages.PracticeBookSessionGradingPage;
import practiceBookSessionPages.PracticeBookSessionOverallOutcomePage;
import traineePages.TraineeReviewPage;

public class PBSTest extends BaseClass {
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
	}
}
