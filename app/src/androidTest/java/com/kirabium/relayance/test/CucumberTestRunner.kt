package com.kirabium.relayance.test

import android.app.Application
import android.content.Context
import io.cucumber.android.runner.CucumberAndroidJUnitRunner
import io.cucumber.junit.CucumberOptions


@CucumberOptions(
    features = ["features"],
    glue = ["com.kirabium.relayance.test"],

)
class CucumberTestRunner : CucumberAndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, "dagger.hilt.android.testing.HiltTestApplication", context)

    }
}