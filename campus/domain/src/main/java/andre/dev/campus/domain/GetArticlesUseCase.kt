package andre.dev.campus.domain

import andre.dev.campus.domain.model.CampusSummary

fun interface GetArticlesUseCase {
    suspend fun getArticles(startTimestamp: Long, pageSize: Int): List<CampusSummary>

}