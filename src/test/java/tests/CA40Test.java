package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import adminPages.AdminDashBoardPage;
import adminPages.BecomeUserPage;
import base.BaseClass;
import ca4041Pages.CA4041GeneralInfoGradingPage;
import ca4041Pages.CA4041TaskGradePage;
import ca4041Pages.GradingTraineeListPage;
import commonPages.TraineeGradingPage;
import commonPages.TrainerDashBoradPage;

public class CA40Test extends BaseClass {
	private GradingTraineeListPage gradingTraineeListPage;
	private TrainerDashBoradPage trainerDashBoradPage;
	private TraineeGradingPage traineeGradingPage;
	private CA4041GeneralInfoGradingPage cA4041GeneralInfoGradingPage;
	private CA4041TaskGradePage cA4041TaskGradePage;

	@BeforeMethod(alwaysRun = true)
	public void initPages() {
		adminDashBoardPage = new AdminDashBoardPage(getDriver());
		becomeUserPage = new BecomeUserPage(getDriver());
		gradingTraineeListPage = new GradingTraineeListPage(getDriver());
		trainerDashBoradPage = new TrainerDashBoradPage(getDriver());
		traineeGradingPage = new TraineeGradingPage(getDriver());
		cA4041GeneralInfoGradingPage = new CA4041GeneralInfoGradingPage(getDriver());
		cA4041TaskGradePage = new CA4041TaskGradePage(getDriver());
	}

	@Test(description = "CA 40//41 Form e2e Happy Path test case")
	public void happyPathCA4041Test() throws InterruptedException {
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
		cA4041GeneralInfoGradingPage.enterRegistrationNumber("TESTUSER");
		cA4041GeneralInfoGradingPage.selectLocationDropDown("BLR");
		cA4041GeneralInfoGradingPage.selectSimulatorLevelDropDown("FFS Level D");
		cA4041GeneralInfoGradingPage.selectSeatOccupiedDropDown("LHS");
		cA4041GeneralInfoGradingPage.selectTypeOfCheckDropDown("IR");
		cA4041GeneralInfoGradingPage.clickDayIconButton();
		cA4041GeneralInfoGradingPage.selectCrewStatusDropDown("Trainee Co-Pilot");
		cA4041GeneralInfoGradingPage.clickNextButton();
		cA4041TaskGradePage.clickFlightPreparationPanel();
		cA4041TaskGradePage.clickAllYesButtons();
		cA4041TaskGradePage.selectGarde("PRO", "2");
		cA4041TaskGradePage.clickAllMinusButtons("PRO");
		cA4041TaskGradePage.enterObComment("entering OB Comment", "PRO");
		cA4041TaskGradePage.clickObDoneButton("PRO");
		cA4041TaskGradePage.clickTakeOffPanel();
		cA4041TaskGradePage.clickAllYesButtons();
		cA4041TaskGradePage.selectGarde("PRO", "2");
		cA4041TaskGradePage.clickAllMinusButtons("PRO");
		cA4041TaskGradePage.enterObComment("entering OB Comment", "PRO");
		cA4041TaskGradePage.clickObDoneButton("PRO");
		cA4041TaskGradePage.clickFlightmanoeuresAndProcedurePanel();
		cA4041TaskGradePage.clickAllYesButtons();
		cA4041TaskGradePage.clickNormalAndAbnormalOperationsPanel();
		cA4041TaskGradePage.clickAllYesButtons();
		cA4041TaskGradePage.clickNormalAndAbnormalEmergencyProceduresPanel();
		cA4041TaskGradePage.clickAllYesButtons();
		cA4041TaskGradePage.clickMIRILS_ApprochesPanel();
		cA4041TaskGradePage.clickAllYesButtons();
		cA4041TaskGradePage.clickMissedApproacheProceduresPanel();
		cA4041TaskGradePage.clickAllYesButtons();
		cA4041TaskGradePage.clickLandingPanel();
		cA4041TaskGradePage.clickAllYesButtons();
		cA4041TaskGradePage.clickLowVisibilityOperationsPanel();
		cA4041TaskGradePage.clickAllYesButtons();
		cA4041TaskGradePage.clickSaveButton();
		cA4041TaskGradePage.clickWishToMaintainDefaultGradingPopupYesButton();
	}
}
