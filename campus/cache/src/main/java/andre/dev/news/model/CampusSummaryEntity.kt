package andre.dev.news.model

import andre.dev.campus.model.CampusSummary
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CampusSummaryEntity(
    @PrimaryKey val id: String,
    val name: String
)

fun CampusSummaryEntity.toDomainModel() = CampusSummary(
    id = this.id,
    name = this.name
)

fun CampusSummary.toEntity() = CampusSummaryEntity(
    id = this.id,
    name = this.name
)