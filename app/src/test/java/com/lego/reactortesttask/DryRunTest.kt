package com.lego.reactortesttask

import android.app.Application
import android.content.Context
import com.lego.reactortesttask.di.appModule
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.test.KoinTest
import org.koin.test.checkModules
import org.mockito.Mockito.mock

class DryRunTest : KoinTest {

    private lateinit var mockedAndroidContext: Module

    @Before
    fun before() {
        mockedAndroidContext = module {
            single { mock(Application::class.java) }
            single { mock(Context::class.java) }
        }
    }

    @Test
    fun testGraphConfiguration() {
        startKoin(appModule + mockedAndroidContext)
        checkModules(appModule + mockedAndroidContext)
    }

    @After
    fun after() {
        stopKoin()
    }

}