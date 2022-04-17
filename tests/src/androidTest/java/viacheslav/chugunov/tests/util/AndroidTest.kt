package viacheslav.chugunov.tests.util

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import viacheslav.chugunov.tests.util.factory.RepositoryFactory
import viacheslav.chugunov.tests.util.factory.UseCaseFactory

open class AndroidTest {
    protected val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
    protected val repositoryFactory = RepositoryFactory()
    protected val useCaseFactory = UseCaseFactory(repositoryFactory)
}