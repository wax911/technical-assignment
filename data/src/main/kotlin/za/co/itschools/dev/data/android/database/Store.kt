package za.co.itschools.dev.data.android.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.jetbrains.annotations.TestOnly
import za.co.itschools.dev.data.android.database.contract.IStore
import za.co.itschools.dev.data.login.entity.LoginEntity

@Database(
    entities = [LoginEntity::class],
    version = Store.SCHEMA_VERSION
)
internal abstract class Store : RoomDatabase(), IStore {

    companion object {
        const val SCHEMA_VERSION = 1

        internal fun create(context: Context): IStore {
            return Room.databaseBuilder(
                context,
                Store::class.java,
                "sample-db"
            ).fallbackToDestructiveMigration()
                .build()
        }

        @TestOnly
        internal fun createInMemory(context: Context): IStore {
            return Room.inMemoryDatabaseBuilder(
                context, Store::class.java,
            ).build()
        }
    }
}