package com.kirabium.relayance.util

import android.content.Intent
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kirabium.relayance.ui.activity.DetailActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<DetailActivity>()


    @Test
    fun detailScreenDisplaysCorrectCustomerData() {
        val intent =
            Intent(ApplicationProvider.getApplicationContext(), DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_CUSTOMER_ID, 1)
            }

        ActivityScenario.launch<DetailActivity>(intent)

        composeTestRule.onNodeWithText("Alice Wonderland").assertIsDisplayed()
    }
}
