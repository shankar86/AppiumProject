package com.zee5.tata.cucumber.appium.stepdefs;

import com.jayway.restassured.response.Response;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefs {
	public static Response response = null;

	@Given("^Run Collection API \"([^\"]*)\"$")
	public void run_collection_api(String arg) throws Throwable {
		MainPage.CollectionAPI(arg);
	}
	
	@Given("^Run New Collection API \"([^\"]*)\"$")
	public void run__new_collection_api(String arg) throws Throwable {
		MainPage.NewCollectionAPI(arg);
	}

	@Then("^Play Watch Trailer Content$")
	public void navigate_between_Screen() throws Throwable {
		MainPage.PlayWatchTrailerContent();
	}

	@Given("^Launch the App$")
	public void launch_the_app() throws Throwable {
		MainPage.LaunchTheApp();
	}
	
	@Given("^Launch the App1$")
	public void launch_the_app1() throws Throwable {
		MainPage.LaunchTheApp1();
	}

	@Given("^Validate WelCome Screen$")
	public void validate_welcome_screen() throws Throwable {
		MainPage.ValidateWelcomeScreen();
	}

	@When("^Click on Login button$")
	public void click_on_login_button() throws Throwable {
		MainPage.ClickOnLoginButton();
	}

	@When("^Click on Skip button$")
	public void click_on_skip_button() throws Throwable {
		MainPage.ClickOnSkipButton();
	}

	@When("^Click RCU back button$")
	public void click_rcu_back_button() throws Throwable {
		MainPage.ClickRCUBackButton();
	}

	@When("^Validate Authentication Code$")
	public void validate_authenticate_code() throws Throwable {
		MainPage.ValidateAuthenticationCode();
	}

	@Then("^Verify Authentication Screen$")
	public void verify_authentication_screen() throws Throwable {
		MainPage.VerifyAuthenticationScreen();
	}

	@When("^Launch Browser and Authenticate the Device \"([^\"]*)\" \"([^\"]*)\"$")
	public void launch_the_browser_authenticate_device(String arg1, String arg2) throws Throwable {
		MainPage.LaunchBrowserAndAuthenticateDevice(arg1, arg2);
	}

	@When("^Click On Continue button$")
	public void click_on_continue_button() throws Throwable {
		MainPage.ClickOnContinueButton();
	}

	@Then("^Validate Home Screen \"([^\"]*)\"$")
	public void validate_home_screen(String lang) throws Throwable {
		MainPage.ValidateHomeScreen(lang);
	}
	
	@Then("^Get the App Version$")
	public void get_the_app_version() throws Throwable {
		MainPage.getTheAppVersion();
	}

	@Then("^Click On Search Screen$")
	public void click_on_search_screen() throws Throwable {
		MainPage.ClickOnSearchScreen();
	}

	@Then("^Single Click On Search Screen$")
	public void single_click_on_search_screen() throws Throwable {
		MainPage.singleClickOnSearchScreen();
	}

	@Then("^Verify Search Screen$")
	public void verify_search_screen() throws Throwable {
		MainPage.VerifySearchScreen();
	}

	@Then("^Verify Searched Content \"([^\"]*)\"$")
	public void verify_searched_Content(String arg) throws Throwable {
		MainPage.VerifySearchedContent(arg);
	}

	@Then("^Search \"([^\"]*)\" Content for \"([^\"]*)\"$")
	public void search_content(String arg1, String arg2) throws Throwable {
		MainPage.SearchContent(arg1, arg2);
	}

	@Then("^Click On Search Content$")
	public void click_on_search_content() throws Throwable {
		MainPage.ClickOnSerachContent();
	}

	@Then("^Click on Before TV Content$")
	public void click_on_beforetv_content() throws Throwable {
		MainPage.ClickOnBeforeTvContent();
	}

	@Then("^Play the Previous Episode of Before TV Episode$")
	public void play_previous_episode_of_beforetv_episode() throws Throwable {
		MainPage.PlayPreviousEpisodeOfBeforeTvEpisode();
	}
	
	@Then("^Play the Previous Episode$")
	public void play_previous_episode() throws Throwable {
		MainPage.PlayPreviousEpisode();
	}

	@Then("^Scrub the Content Till End$")
	public void scrub_the_content_till_end() throws Throwable {
		MainPage.ScrubTheContentTillEnd();
	}

	@Then("^Validate Auto Play Screen$")
	public void validate_auto_play_screen() throws Throwable {
		MainPage.validateAutoPlayScreen();
	}

	@Then("^Click On Auto Play Progress Loader$")
	public void click_on_auto_play_progress_loader() throws Throwable {
		MainPage.clickOnautoPlayProgressLoader();
	}
	
	@Then("^Click On Cancel button in Auto Play Screen$")
	public void click_on_cancel_button_in_auto_play_screen() throws Throwable {
		MainPage.clickOnCancelButtonInAutoPlayScreen();
	}
	
	@Then("^Verify Player Screen$")
	public void verify_player_screen() throws Throwable {
		MainPage.verifyPlayerScreen();
	}

	@Then("^Verify Auto Played Content Title In Player$")
	public void verify_auto_played_content_title_in_player() throws Throwable {
		MainPage.verifyContentTitleInPlayer();
	}

	@Then("^Verify Content Title In Player$")
	public void verify_content_title_in_player() throws Throwable {
		MainPage.verifyContentTitleInPlayer();
	}

	@Then("^Verify Content Metadata In Player$")
	public void verify_content_metadata_in_player() throws Throwable {
		MainPage.verifyContentMetadataInPlayer();
	}

	@Then("^Verify Player Controls in Player$")
	public void verify_player_controls_in_player() throws Throwable {
		MainPage.verifyPlayerControlsInPlayer();
	}

	@Then("^Verify Seekbar In Player$")
	public void verify_seekbar_in_player() throws Throwable {
		MainPage.verifyseekbarInPlayer();
	}

	@Then("^Verify Settings button in Player$")
	public void verify_settings_button_in_player() throws Throwable {
		MainPage.verifySettingsButtonInPlayer();
	}

	@Then("^Verify Reco button in Player$")
	public void verify_reco_button_in_player() throws Throwable {
		MainPage.verifyRecoButtonInPlayer();
	}

	@Then("^Verify Before TV Pop Up for \"([^\"]*)\"$")
	public void verify_before_tv_pop_up(String arg) throws Throwable {
		MainPage.VerifyBeforeTvPopup(arg);
	}

	@Then("^Verify Splash Screen$")
	public void verify_splash_screen() throws Throwable {
		MainPage.VerifySpashScreen();
	}
	
	@Then("^Verify Thumbnail Image$")
	public void verify_thumbnail_image() throws Throwable {
		MainPage.VerifyThumbnailImage();
	}

	@Then("^Navigate to Screen \"([^\"]*)\"$")
	public void navigate_to_screen(String arg) throws Throwable {
		MainPage.NavigateToScreen(arg);
	}

	@Then("^Click on Play button$")
	public void click_on_play_button() throws Throwable {
		MainPage.ClickOnPlayButton();
	}

	@Then("^Verify Login Pop Up$")
	public void verify_login_pop_up() throws Throwable {
		MainPage.VerifyLoginPopUp();
	}

	@Then("^Click on Login Pop Up button$")
	public void click_on_login_popup_button() throws Throwable {
		MainPage.ClickOnLoginPopUpButton();
	}

	@Then("^Navigate to Settings$")
	public void navigate_to_settings() throws Throwable {
		MainPage.navigateToSettings();
	}

	@Then("^Click On Logout button$")
	public void click_on_logout_button() throws Throwable {
		MainPage.clickOnLogoutButton();
	}

	@Then("^Click on Add Watchlist button$")
	public void click_on_add_watchlist_button() throws Throwable {
		MainPage.clickOnAddWatchlistButton();
	}

	@Then("^Verify Add Watchlist button$")
	public void verify_add_watchlist_button() throws Throwable {
		MainPage.verifyAddWatchlistButton();
	}

	@Then("^Navigate to All Channels Screen$")
	public void navigate_to_all_channels_screen() throws Throwable {
		MainPage.navigateToAllChannelsScreen();
	}

	@Then("^Play Live TV Service$")
	public void play_live_tv_service() throws Throwable {
		MainPage.playLiveTvService();
	}

	@Then("^Verify Played Live TV Service$")
	public void verify_played_live_tv_service() throws Throwable {
		MainPage.verifyPlayedLiveTvService();
	}

	@Given("^Launch PMR Content$")
	public void launch_pmr_content() throws Throwable {
		MainPage.LaunchPMRContent();
	}

	@Given("^Navigate to Settings Screen \"([^\"]*)\"$")
	public void navigate_to_settings_screen(String user) throws Throwable {
		MainPage.NavigateToSettingsScreen(user);
	}

	@When("^Click on My Plans Tab$")
	public void click_on_my_plans_tab() throws Throwable {
		MainPage.ClickOnMyPlansTab();
	}

	@When("^Verify All Plans Screen$")
	public void verify_all_plans_screen() throws Throwable {
		MainPage.VerifyAllPlansScreen();
	}

	@When("^Validate Landing Screen Trays Titles$")
	public void validate_landing_screen_trays_titles() throws Throwable {
		MainPage.ValidateLandingScreenTraysTitles();
	}
	
	@Then("^Verify Show Call Out Pop Up$")
	public void verify_show_call_out_pop_up() throws Throwable {
		MainPage.verifyShowCallOutPopUp();
	}
	
	@Then("^Click on Yes Now button$")
	public void click_on_yes_now_button() throws Throwable {
		MainPage.clickOnYesNowButton();
	}
	
	@Then("^Run Search API for \"([^\"]*)\"$")
	public void run_search_api(String tvShow) throws Throwable {
		MainPage.runSearchApi(tvShow);
	}
	
	@Then("^Navigate to Searched Content \"([^\"]*)\"$")
	public void navigate_to_searched_content(String tvShow) throws Throwable {
		MainPage.navigateToSearchedContent(tvShow);
	}
	
	@Then("^Navigate and Click on Language Settings Screen$")
	public void navigate_and_click_on_language_settings_screen() throws Throwable {
		MainPage.navigateAndClickLangSettings();
	}
	
	@Then("^Verify Display Language Screen$")
	public void verify_display_language_screen() throws Throwable {
		MainPage.verifyDisplayLanguageScreen();
	}
	
	@Then("^Verify Content Language Screen$")
	public void verify_content_language_screen() throws Throwable {
		MainPage.verifyContentLanguageScreen();
	}
	
	@Then("^Select \"([^\"]*)\" Display Language$")
	public void select_display_language(String lang) throws Throwable {
		MainPage.selectDisplayLanguage(lang);
	}
	
	@Then("^Verify Active Plans$")
	public void verify_active_plans() throws Throwable {
		MainPage.verifyActivePlans();
	}
	
	@Then("^Verify Expired Plans$")
	public void verify_expired_plans() throws Throwable {
		MainPage.verifyExpiredPlans();
	}
	
	@Then("^Verify Renew Plan and button$")
	public void verify_renew_plan_and_button() throws Throwable {
		MainPage.verifyRenewPlan();
	}
	
	@Then("^Click on Contact Us$")
	public void click_on_contact_us() throws Throwable {
		MainPage.clickOnContactUs();
	}
	
	@Then("^Verify Contact Us Screen$")
	public void verify_contact_us_screen() throws Throwable {
		MainPage.verifyContactusScreen();
	}
	
	@Then("^Type Some Description and Submit$")
	public void type_some_description_and_submit() throws Throwable {
		MainPage.typeDescriptionAndSubmit();
	}
	
	@Then("^Verify Success Pop Up$")
	public void verify_success_pop_up() throws Throwable {
		MainPage.verifySuccessPopUp();
	}
	
	@Then("^Verify FAQ's Screen$")
	public void verify_faq_screen() throws Throwable {
		MainPage.verifyFaqScreen();
	}
	
	@Then("^Verify About Us Screen$")
	public void verify_about_us_screen() throws Throwable {
		MainPage.verifyAboutUsScreen();
	}
	
	@Then("^Verify Terms of Use Screen$")
	public void verify_terms_of_use_screen() throws Throwable {
		MainPage.verifyTermsOfUseScreen();
	}
	
	@Then("^Verify Privacy Policy Screen$")
	public void verify_privacy_policy_screen() throws Throwable {
		MainPage.verifyPrivacyPolicyScreen();
	}
	
	@Then("^Verify Get Premium button in Carousel$")
	public void verify_get_premium_button_in_carousel() throws Throwable {
		MainPage.verifyGetPremiumButtonInCarousel();
	}
	
	@Then("^Verify Plan Screen on Click of Get Premium button$")
	public void verify_plan_screen_on_click_of_get_premium_button() throws Throwable {
		MainPage.verifyPlanScreenOnClickGetPremiumBtn();
	}
	
	@Then("^Exit the App By Pressing Back button$")
	public void exit_the_app_by_pressing_back_button() throws Throwable {
		MainPage.exitTheAppByPressingBackButton();
	}
	
	@Then("^Click on Seasons and Episode button$")
	public void click_on_seasons_and_episode_button() throws Throwable {
		MainPage.clickOnSeasonsAndEpisodeButton();
	}
	
	@Then("^Verify Seasons and Episode Screen$")
	public void verify_seasons_and_episode_screen() throws Throwable {
		MainPage.verifySeasonsAndEpisodeScreen();
	}
	
	@Then("^Verify Search Screen for \"([^\"]*)\" Display Language$")
	public void verify_search_screen_for_display_language(String lang) throws Throwable {
		MainPage.verifySearchScreenForDisplayLanguage(lang);
	}
	
	@Then("^Verify All Channels Screen$")
	public void verify_all_channels_screen() throws Throwable {
		MainPage.verifyAllChannelsScreen();
	}

	@Then("^Exit the App$")
	public void exit_the_App() throws Throwable {
		MainPage.ExitApp();
	}

}
