package za.co.itschools.dev.data.android.database.extionsions

import org.koin.core.scope.Scope
import za.co.itschools.dev.data.android.database.contract.IStore

/**
 * Facade for obtaining a database contract
 */
internal fun Scope.store() = get<IStore>()