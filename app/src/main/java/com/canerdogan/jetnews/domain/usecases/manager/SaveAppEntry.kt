package com.canerdogan.jetnews.domain.usecases.manager

import com.canerdogan.jetnews.domain.manager.LocalUserManager

class SaveAppEntry(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }

}