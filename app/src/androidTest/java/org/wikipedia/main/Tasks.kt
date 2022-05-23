package org.wikipedia.main

import android.app.Instrumentation
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import io.qameta.allure.android.rules.ScreenshotRule
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import io.qameta.allure.kotlin.Epic
import io.qameta.allure.kotlin.junit4.DisplayName
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.wikipedia.R
import org.wikipedia.TestUtil


@RunWith(AllureAndroidJUnit4::class)
@Epic("Wikipedia")
@DisplayName("Главный экран")

class Tasks {
    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val logcatRule = ScreenshotRule(mode = ScreenshotRule.Mode.END, screenshotName = "ss_end")

    @Before
    fun before() {
        Intents.init()
    }

    @After
    fun after() {
        Intents.release()
    }

    @Test
    @DisplayName("Открытие браузера по кнопке 'Пожертвовать'")
    fun openingBrowser() {

        onView(allOf(withId(R.id.fragment_onboarding_skip_button), isDisplayed())).perform(
            ViewActions.click()
        )
        TestUtil.delay(2)
        onView(allOf(withId(R.id.nav_more_container), isDisplayed())).perform(ViewActions.click())
        TestUtil.delay(2)
        val expectedIntent = allOf(
            hasAction(Intent.ACTION_CHOOSER),
            hasExtraWithKey(Intent.EXTRA_EXCLUDE_COMPONENTS)
        )
        onView(withId(org.wikipedia.R.id.main_drawer_donate_container)).perform(click())
        intending(expectedIntent).respondWith(Instrumentation.ActivityResult(0, null))
        intended(expectedIntent)
    }

    @Test
    @DisplayName("проверка чекбокса Избранная статья в Настройке ленты")
    fun checkFeedFeaturedArticle() {

        onView(allOf(withId(R.id.fragment_onboarding_skip_button), isDisplayed())).perform(
            click()
        )
        TestUtil.delay(2)
        onView(allOf(withId(R.id.nav_more_container), isDisplayed())).perform(click())
        TestUtil.delay(2)
        onView(allOf(withId(R.id.main_drawer_settings_container), isDisplayed())).perform(click())
        TestUtil.delay(2)
        onView(allOf(withText(R.string.preference_title_customize_explore_feed))).perform(click())
        onView(
            allOf(
                withId(R.id.feed_content_type_checkbox),
                withParent(withChild(withChild(withText(R.string.view_featured_article_card_title))))
            )
        ).check(matches(isChecked()))
    }

    @Test
    @DisplayName("проверка чекбокса Изображение дня в Настройке ленты")
    fun checkFeedPictureOfTheDay() {

        onView(allOf(withId(R.id.fragment_onboarding_skip_button), isDisplayed())).perform(
            click()
        )
        TestUtil.delay(2)
        onView(allOf(withId(R.id.nav_more_container), isDisplayed())).perform(click())
        TestUtil.delay(2)
        onView(allOf(withId(R.id.main_drawer_settings_container), isDisplayed())).perform(click())
        TestUtil.delay(2)
        onView(allOf(withText(R.string.preference_title_customize_explore_feed))).perform(click())
        onView(
            allOf(
                withId(R.id.feed_content_type_checkbox),
                withParent(withChild(withChild(withText(R.string.view_featured_image_card_title))))
            )
        ).check(matches(isChecked()))
    }

    @Test
    @DisplayName("проверка чекбокса На основе прочитанного в Настройке ленты")
    fun checkFeedBecauseYouRead() {

        onView(allOf(withId(R.id.fragment_onboarding_skip_button), isDisplayed())).perform(
            click()
        )
        TestUtil.delay(2)
        onView(allOf(withId(R.id.nav_more_container), isDisplayed())).perform(click())
        TestUtil.delay(2)
        onView(allOf(withId(R.id.main_drawer_settings_container), isDisplayed())).perform(click())
        TestUtil.delay(2)
        onView(allOf(withText(R.string.preference_title_customize_explore_feed))).perform(click())
        onView(
            allOf(
                withId(R.id.feed_content_type_checkbox),
                withParent(withChild(withChild(withText(R.string.view_because_you_read_card_title))))
            )
        ).check(matches(isChecked()))
    }

    @Test
    @DisplayName("проверка чекбокса В новостях в Настройке ленты")
    fun checkFeedInTheNews() {

        onView(allOf(withId(R.id.fragment_onboarding_skip_button), isDisplayed())).perform(
            click()
        )
        TestUtil.delay(2)
        onView(allOf(withId(R.id.nav_more_container), isDisplayed())).perform(click())
        TestUtil.delay(2)
        onView(allOf(withId(R.id.main_drawer_settings_container), isDisplayed())).perform(click())
        TestUtil.delay(2)
        onView(allOf(withText(R.string.preference_title_customize_explore_feed))).perform(click())
        onView(
            allOf(
                withId(R.id.feed_content_type_checkbox),
                withParent(withChild(withChild(withText(R.string.view_card_news_title))))
            )
        ).check(matches(isChecked()))
    }

    @Test
    @DisplayName("проверка чекбокса В этот день в Настройке ленты")
    fun checkFeedOnThisDay() {

        onView(allOf(withId(R.id.fragment_onboarding_skip_button), isDisplayed())).perform(
            click()
        )
        TestUtil.delay(2)
        onView(allOf(withId(R.id.nav_more_container), isDisplayed())).perform(click())
        TestUtil.delay(2)
        onView(allOf(withId(R.id.main_drawer_settings_container), isDisplayed())).perform(click())
        TestUtil.delay(2)
        onView(allOf(withText(R.string.preference_title_customize_explore_feed))).perform(click())
        onView(
            allOf(
                withId(R.id.feed_content_type_checkbox),
                withParent(withChild(withChild(withText(R.string.on_this_day_card_title))))
            )
        ).check(matches(isChecked()))
    }

    @Test
    @DisplayName("проверка чекбокса Рандомизатор в Настройке ленты")
    fun checkFeedRandomizer() {

        onView(allOf(withId(R.id.fragment_onboarding_skip_button), isDisplayed())).perform(
            click()
        )
        TestUtil.delay(2)
        onView(allOf(withId(R.id.nav_more_container), isDisplayed())).perform(click())
        TestUtil.delay(2)
        onView(allOf(withId(R.id.main_drawer_settings_container), isDisplayed())).perform(click())
        TestUtil.delay(2)
        onView(allOf(withText(R.string.preference_title_customize_explore_feed))).perform(click())
        onView(withId(R.id.content_types_recycler)).perform(ViewActions.swipeUp())
        onView(
            allOf(
                withId(R.id.feed_content_type_checkbox),
                withParent(withChild(withChild(withText(R.string.view_random_card_title))))
            )
        ).check(matches(isChecked()))
    }

    @Test
    @DisplayName("проверка чекбокса Сегодня в Википедии в Настройке ленты")
    fun checkFeedTodayOnWikipedia() {

        onView(allOf(withId(R.id.fragment_onboarding_skip_button), isDisplayed())).perform(
            click()
        )
        TestUtil.delay(2)
        onView(allOf(withId(R.id.nav_more_container), isDisplayed())).perform(click())
        TestUtil.delay(2)
        onView(allOf(withId(R.id.main_drawer_settings_container), isDisplayed())).perform(click())
        TestUtil.delay(2)
        onView(allOf(withText(R.string.preference_title_customize_explore_feed))).perform(click())
        onView(withId(R.id.content_types_recycler)).perform(ViewActions.swipeUp())
        onView(
            allOf(
                withId(R.id.feed_content_type_checkbox),
                withParent(withChild(withChild(withText(R.string.view_main_page_card_title))))
            )
        ).check(matches(isChecked()))
    }
}