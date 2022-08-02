package me.dio.businesscard

import android.app.Application
import me.dio.businesscard.data.AppDatabase
import me.dio.businesscard.data.businessCardRepository

class App : Application() {
    val database by lazy {AppDatabase.getDatabase(this)}
    val repository by lazy { businessCardRepository(database.businessDao()) }
}