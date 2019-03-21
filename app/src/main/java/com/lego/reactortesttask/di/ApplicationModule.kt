package com.lego.reactortesttask.di

import android.content.Context
import android.os.Build
import com.lego.reactortesttask.BuildConfig
import com.lego.reactortesttask.data.gateaway.ApiProvider
import com.lego.reactortesttask.data.gateaway.GiphyApi
import com.lego.reactortesttask.data.gateaway.RetrofitApiProvider
import com.lego.reactortesttask.data.remote.GiphyRemoteDataSource
import com.lego.reactortesttask.data.remote.GiphyRemoteDataSourceImpl
import com.lego.reactortesttask.data.repository.GiphyDataRepository
import com.lego.reactortesttask.domain.repository.GiphyRepository
import com.lego.reactortesttask.domain.usecase.SearchGifsUseCase
import com.lego.reactortesttask.flow.giphy.GiphyViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import java.util.*

val appModule: List<Module>
    get() = listOf(
        netModule,
        remoteModule,
        viewModelModule
    )


val remoteModule = module {
    single<GiphyRemoteDataSource> { GiphyRemoteDataSourceImpl(get()) }
    single<GiphyRepository> { GiphyDataRepository(get()) }
    factory { SearchGifsUseCase(get()) }
}

val netModule = module {
    single { get<ApiProvider>().provide(GiphyApi::class.java) }
    single<ApiProvider> { RetrofitApiProvider(BuildConfig.API_ENDPOINT, getLocale(androidApplication())) }
}

val viewModelModule: Module = module {
    viewModel { GiphyViewModel(get(), get()) }
}

fun getLocale(context: Context): Locale {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        context.resources.configuration.locales[0] // gets default locale
    } else {
        context.resources.configuration.locale
    }
}