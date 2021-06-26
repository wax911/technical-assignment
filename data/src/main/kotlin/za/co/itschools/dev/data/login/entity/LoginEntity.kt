package za.co.itschools.dev.data.login.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "login",
    primaryKeys = ["id"],
)
internal data class LoginEntity(
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "hash") val hash: String,
    @ColumnInfo(name = "id") val id: Long = 0
)