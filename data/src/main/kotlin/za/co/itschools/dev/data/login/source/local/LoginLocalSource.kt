package za.co.itschools.dev.data.login.source.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import za.co.itschools.dev.data.android.database.common.ILocalSource
import za.co.itschools.dev.data.login.entity.LoginEntity

@Dao
internal interface LoginLocalSource : ILocalSource<LoginEntity> {

    @Query("""
        select * from login
        where id = :userId
    """)
    suspend fun getLoginUser(userId: Long): LoginEntity?
}