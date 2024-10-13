package com.canerdogan.jetnews.domain.usecases.manager

import com.canerdogan.jetnews.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {

    operator fun invoke(): Flow<Boolean>{
        return localUserManager.readAppEntry()
    }

}