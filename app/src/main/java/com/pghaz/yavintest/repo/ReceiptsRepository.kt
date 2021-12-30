package com.pghaz.yavintest.repo

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class ReceiptsRepository @Inject constructor(
    val localReceiptsSource: LocalReceiptsSource
)