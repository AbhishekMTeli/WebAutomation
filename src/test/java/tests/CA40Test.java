package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import adminPages.AdminDashBoardPage;
import adminPages.BecomeUserPage;
import base.BaseClass;
import ca4041Pages.CA4041GeneralInfoGradingPage;
import ca4041Pages.GradingTraineeListPage;
import commonPages.TraineeGradingPage;
import commonPages.TrainerDashBoradPage;

public class CA40Test extends BaseClass {
	private GradingTraineeListPage gradingTraineeListPage;
	private TrainerDashBoradPage trainerDashBoradPage;
	private TraineeGradingPage traineeGradingPage;
	private CA4041GeneralInfoGradingPage cA4041GeneralInfoGradingPage;

	@BeforeMethod(alwaysRun = true)
	public void initPages() {
		adminDashBoardPage = new AdminDashBoardPage(getDriver());
		becomeUserPage = new BecomeUserPage(getDriver());
		gradingTraineeListPage = new GradingTraineeListPage(getDriver());
		trainerDashBoradPage = new TrainerDashBoradPage(getDriver());
		traineeGradingPage = new TraineeGradingPage(getDriver());
		cA4041GeneralInfoGradingPage = new CA4041GeneralInfoGradingPage(getDriver());
	}

	@Test
	public void test() throws InterruptedException {
		adminDashBoardPage.clickBecomeUserTab();
		becomeUserPage.sendUserId();
		becomeUserPage.clickOnBecomeUser();
		trainerDashBoradPage.clickOnGradingAssessmentTab();
		trainerDashBoradPage.clickOnGradingSubTab();
		traineeGradingPage.validateAllStaticTexts();
		traineeGradingPage.clickOnGradeButtonWithRetries(10);
		gradingTraineeListPage.validateTableHeadersForGradingTraineeListPage();
		gradingTraineeListPage.clickOnFirstGradeButton();
		cA4041GeneralInfoGradingPage.validateGeneralInfoLabels();
	}
}
