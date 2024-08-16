package sonata.open.drugsdosage

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/*
 * When unit testing, we have to replace Dispatchers.Main with a different dispatcher
 * than it has by default, because the default implementation of Dispatchers.
 * Main doesn't exist when not running a full application.
 * To do this, we need to have the kotlinx-coroutines-test test dependency and
 * to create a coroutine testing rule to set this up before and after each test
 */
@OptIn(ExperimentalCoroutinesApi::class)
class MainCoroutineRule(
    private val dispatcher: TestDispatcher = StandardTestDispatcher()
) : TestWatcher() {

    override fun starting(description: Description?) = Dispatchers.setMain(dispatcher)

    override fun finished(description: Description?) = Dispatchers.resetMain()
}