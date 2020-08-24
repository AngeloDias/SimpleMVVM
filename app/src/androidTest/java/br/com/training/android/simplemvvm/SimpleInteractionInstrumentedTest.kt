package br.com.training.android.simplemvvm

import android.content.Intent
import android.content.pm.PackageManager
import android.widget.TextView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.*
import br.com.training.android.simplemvvm.ui.main.view.MainActivity
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SimpleInteractionInstrumentedTest {
    private lateinit var uiDevice: UiDevice

    @get:Rule
    val activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Before
    fun setup() {
        uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        // Start from the home screen
        uiDevice.pressHome()

        uiDevice.wait(Until.hasObject(By.pkg(getLauncherPackageName()).depth(0)), 1000)
    }

    private fun getLauncherPackageName(): String? {
        // Create launcher Intent
        val intent = Intent(Intent.ACTION_MAIN)

        intent.addCategory(Intent.CATEGORY_HOME)

        // Use PackageManager to get the launcher package name
        val pm: PackageManager = InstrumentationRegistry.getInstrumentation().context.packageManager
        val resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)

        return resolveInfo.activityInfo.packageName
    }

    @Test
    fun scrollListTest(){
        uiDevice.pressHome()

        uiDevice.findObject(UiSelector().description("Apps")).clickAndWaitForNewWindow()

        val appViews = UiScrollable(UiSelector().scrollable(true))

        appViews.setAsVerticalList()

        val simpleApp = appViews
            .getChildByText(UiSelector()
                .className(TextView::class.java.name),
                activityRule.activity.resources.getString(R.string.app_name))

        simpleApp.clickAndWaitForNewWindow()

        val simpleValidation = uiDevice
            .findObject(
                UiSelector()
                    .packageName(activityRule.activity.applicationContext.packageName)
            )

        assertTrue(simpleValidation.exists())

        val recyclerView = uiDevice.findObject(UiSelector().className("androidx.recyclerview.widget.RecyclerView"))

        assertTrue(recyclerView.getChild(UiSelector().index(7)).exists())

        recyclerView.swipeDown(5)
    }

}
