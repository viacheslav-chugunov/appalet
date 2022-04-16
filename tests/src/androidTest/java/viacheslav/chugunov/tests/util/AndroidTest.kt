package viacheslav.chugunov.tests.util

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry

open class AndroidTest {
    val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
}