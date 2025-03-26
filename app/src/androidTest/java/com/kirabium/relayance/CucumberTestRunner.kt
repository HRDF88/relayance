package com.kirabium.relayance

import io.cucumber.android.runner.CucumberAndroidJUnitRunner
import io.cucumber.junit.CucumberOptions


@CucumberOptions(
    features = ["src/androidTest/assets/features"],
    glue = ["com.kirabium.relayance"]
)
class CucumberTestRunner : CucumberAndroidJUnitRunner() {
}