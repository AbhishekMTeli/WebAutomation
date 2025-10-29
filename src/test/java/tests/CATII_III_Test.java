package tests;

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
import cat_II_III_Pages.CAT_II_III_GradingPage;
import cat_II_III_Pages.CAT_II_III_TrainingPage;
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
	}

	@Test(description = "CAT II III Form e2e Happy Path test case")
	public void happyPathCA4041Test() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		cat_II_III_GradingPage.validateCAT_II_III_GradingPageTexts();
	}
}
