package com.kirabium.relayance.test


import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.kirabium.relayance.ui.activity.DetailActivity
import io.cucumber.java.en.Given

class CucumberComposeMyExampleTest(
    private val composeRuleHolder: ComposeRuleHolder,
    private val scenarioHolder: ActivityScenarioHolder
):
    SemanticsNodeInteractionsProvider
    by composeRuleHolder.composeRule{

    @Given("^Lancer fenetre details")
    fun initializeApp(){
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        scenarioHolder.launch(DetailActivity.create(instrumentation.targetContext,2))
    }

}