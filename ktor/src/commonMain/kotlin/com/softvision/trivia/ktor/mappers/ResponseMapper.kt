package com.softvision.trivia.ktor.mappers

import com.softvision.trivia.ktor.models.Response

abstract class ResponseMapper<ApiModel, out DomainModel> {

    fun toDomain(response: Response<ApiModel>): List<DomainModel> =
        response.takeIf { it.responseCode == 0 }?.results?.map(this::toDomain) ?: emptyList()

    abstract fun toDomain(apiModel: ApiModel): DomainModel
}