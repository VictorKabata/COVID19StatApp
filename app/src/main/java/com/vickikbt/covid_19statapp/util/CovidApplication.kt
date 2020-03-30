package com.vickikbt.covid_19statapp.util

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.vickikbt.covid_19statapp.db.CoronaStatDatabase
import com.vickikbt.covid_19statapp.db.repository.CoronaRepository
import com.vickikbt.covid_19statapp.db.repository.CoronaRepositoryImpl
import com.vickikbt.covid_19statapp.network.*
import com.vickikbt.covid_19statapp.ui.global.GlobalViewFragmentModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.androidModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class CovidApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidModule(this@CovidApplication))

        bind() from singleton { CoronaStatDatabase(instance()) }
        bind() from singleton { instance<CoronaStatDatabase>().globalCoronaStatDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { CoronaAPIService(instance()) }
        bind<CoronaStatNetworkDataSource>() with singleton {CoronaStatNetworkDataSourceImpl(instance())}
        bind<CoronaRepository>() with singleton { CoronaRepositoryImpl(instance(), instance()) }
        bind() from provider { GlobalViewFragmentModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }

}