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
import cat_II_III_Pages.CAT_III_TrainingPage;
import cat_II_III_Pages.CAT_II_III_CheckPage;
import cat_II_III_Pages.CAT_II_III_GradingPage;
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
		cat_II_III_GradingPage.enterRegistrationNumber("Test User");
		cat_II_III_GradingPage.selectLocationDropdown("BLR");
		cat_II_III_GradingPage.clickCM1LHSRadioButton();
		cat_II_III_GradingPage.clickCM1RHSRadioButton();
		cat_II_III_GradingPage.clickCM2LHSRadioButton();
		cat_II_III_GradingPage.clickCM2RHSRadioButton();
		cat_II_III_GradingPage.clickSaveAndNextButton();

		// lhs CAT II grading
		cat_II_III_TrainingPage.clickCATIIPanel();
		cat_II_TrainingPage.clickCAT_II_LHSGrade("PRO", "4");
		cat_II_TrainingPage.clickAllLHSMinus("PRO");
		cat_II_TrainingPage.enterLHSComments("PRO", "Entering the OB Comments");
		cat_II_TrainingPage.clickLHSOBDoneButton("PRO");

		// rhs CAT II grading
		cat_II_TrainingPage.clickCAT_II_RHSGrade("FPM", "4");
		cat_II_TrainingPage.clickAllRHSMinus("FPM");
		cat_II_TrainingPage.enterLHSComments("FPM", "Entering the OB Comments");
		cat_II_TrainingPage.clickLHSOBDoneButton("FPM");
		cat_II_TrainingPage.enterCAT_II_LHSRemarks("adding cat II lhs remarks");
		cat_II_TrainingPage.enterCAT_II_RHSRemarks("adding cat II rhs remarks");

		// lhs CAT III grading
		cat_II_III_TrainingPage.clickCATIIIPanel();
		cat_III_TrainingPage.clickCAT_III_LHSGrade("KNO", "4");
		cat_III_TrainingPage.clickAllLHSMinusButtons("KNO");
		cat_III_TrainingPage.enterLHSComments("KNO", "Entering the OB Comments");
		cat_III_TrainingPage.clickLHSOBDoneButton("KNO");

		// rhs CAT III grading
		cat_III_TrainingPage.clickCAT_III_RHSGrade("FPA", "4");
		cat_III_TrainingPage.clickAllRHSMinusButtons("FPA");
		cat_III_TrainingPage.enterRHSComments("FPA", "Entering the OB Comments");
		cat_III_TrainingPage.clickRHSOBDoneButton("FPA");
		cat_III_TrainingPage.enterCAT_III_LHSRemarks("adding cat III lhs remarks");
		cat_III_TrainingPage.enterCAT_III_RHSRemarks("adding cat III rhs remarks");
		cat_II_III_TrainingPage.clickSaveAndNextButton();

		// check page
		// lhs
		cat_II_III_CheckPage.clickCAT_II_III_LHSGrade("PRO", "4");
		cat_II_III_CheckPage.clickAllLHSMinus("PRO");
		cat_II_III_CheckPage.enterLHSComments("PRO", "entering ob comments");
		cat_II_III_CheckPage.clickLHSOBDoneButton("PR");

		// rhs
		cat_II_III_CheckPage.clickCAT_II_III_RHSGrade("COM", "4");
		cat_II_III_CheckPage.clickAllRHSMinus("COM");
		cat_II_III_CheckPage.enterRHSComments("COM", "entering ob comments");
		cat_II_III_CheckPage.clickRHSOBDoneButton("COM");
		cat_II_III_CheckPage.enterCAT_II_III_LHSRemarks("entering lhs remarks");
		cat_II_III_CheckPage.enterCAT_II_III_RHSRemarks("entering rhs remarks");
		cat_II_III_CheckPage.selectQaulification("SFI");
		cat_II_III_CheckPage.clickNextAndSaveButton();
	}
}
