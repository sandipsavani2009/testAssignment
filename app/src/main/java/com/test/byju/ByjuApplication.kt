package com.test.byju

import android.app.Application

class ByjuApplication: Application() {

    init {
        INSTANT = this
    }

    companion object {
        lateinit var INSTANT: ByjuApplication
            private set
    }

}