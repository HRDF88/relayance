package com.kirabium.relayance.test
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kirabium.relayance.R
import com.kirabium.relayance.ui.activity.DetailActivity
import com.kirabium.relayance.ui.activity.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra

@RunWith(AndroidJUnit4::class)
class ListClickTest {

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun clickOnFirstItem_opensDetailActivityWithCorrectIntent() {
        ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.customerRecyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        Intents.intended(
            hasExtra(DetailActivity.EXTRA_CUSTOMER_ID, 1)
        )
    }
}