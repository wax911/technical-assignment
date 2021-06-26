package za.co.itschools.dev.data.android.database.contract

import za.co.itschools.dev.data.login.source.local.LoginLocalSource

/**
 * Database DAO container
 */
internal interface IStore {
    fun loginDao(): LoginLocalSource
}