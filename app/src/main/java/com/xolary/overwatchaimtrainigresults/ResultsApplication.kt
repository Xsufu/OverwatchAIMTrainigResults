package com.xolary.overwatchaimtrainigresults

import android.app.Application
import com.xolary.overwatchaimtrainigresults.data.ItemRoomDatabase

class ResultsApplication: Application() {
    val database: ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(this) }
}