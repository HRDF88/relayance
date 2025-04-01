package com.kirabium.relayance.test

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.kirabium.relayance.R
import com.kirabium.relayance.ui.activity.MainActivity
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When


class AddCustomerSteps {

    private lateinit var decorView: View

    @Given("je suis sur l'écran d'ajout d'un client")
    fun jeSuisSurEcranDajoutClient() {
        ActivityScenario.launch(MainActivity::class.java).onActivity { activity ->
            decorView = activity.window.decorView
        }
        onView(withId(R.id.addCustomerFab)).perform(click())
    }

    @When("je saisis {string} comme nom")
    fun jeSaisisNom(name: String) {
        onView(withId(R.id.nameEditText)).perform(typeText(name))
    }

    @And("je saisis {string} comme email")
    fun jeSaisisEmail(email: String) {
        onView(withId(R.id.emailEditText)).perform(typeText(email))
    }

    @And("je valide l'ajout")
    fun jeValideAjout() {
        onView(withId(R.id.saveFab)).perform(click())
    }

    @Then("le client {string} apparaît dans la liste des clients")
    fun leClientApparaîtDansListe(name: String) {
        onView(withText(name)).check(matches(isDisplayed()))
    }

    @Then("un message d'erreur {string} est affiché")
    fun unMessageDerreurEstAffiche(message: String) {
        onView(withText(message))
            .check(matches(isDisplayed()))
    }



}