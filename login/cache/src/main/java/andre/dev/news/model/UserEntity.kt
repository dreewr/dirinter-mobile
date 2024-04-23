package andre.dev.news.model

import andre.dev.news.domain.model.User
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey val id: String,
    val name: String,
    val expirationTimestamp: Long
)

fun UserEntity.toUser() = User(
    id = id,
    name = name
)

fun User.toEntity(expirationOffsetMillis: Long = 24 * 60 * 60 * 1000) = UserEntity(
    id = id,
    name = name,
    expirationTimestamp = System.currentTimeMillis() + expirationOffsetMillis
)