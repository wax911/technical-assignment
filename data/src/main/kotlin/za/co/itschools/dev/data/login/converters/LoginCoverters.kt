package za.co.itschools.dev.data.login.converters

import za.co.itschools.dev.data.core.mapper.AbstractMapper
import za.co.itschools.dev.data.login.entity.LoginEntity
import za.co.itschools.dev.data.login.model.container.LoginModelContainer

internal typealias LoginConverter = AbstractMapper<LoginModelContainer, LoginEntity>

internal class LoginModelConverter(
    override val fromType: (LoginModelContainer) -> LoginEntity = ::transform
) : LoginConverter() {
    private companion object {
        fun transform(model: LoginModelContainer): LoginEntity {
            return LoginEntity(
                username = model.user.username,
                hash = model.hash
            )
        }
    }
}