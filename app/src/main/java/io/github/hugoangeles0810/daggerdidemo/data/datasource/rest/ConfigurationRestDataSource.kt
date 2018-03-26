package io.github.hugoangeles0810.daggerdidemo.data.datasource.rest

import io.github.hugoangeles0810.daggerdidemo.data.datasource.ConfigurationDataSource
import io.github.hugoangeles0810.daggerdidemo.data.datasource.rest.api.ApiConfiguration
import io.github.hugoangeles0810.daggerdidemo.domain.entities.Configuration as ConfigurationEntity

class ConfigurationRestDataSource(private val apiConfiguration: ApiConfiguration) : BaseRestDataStore(), ConfigurationDataSource {

    override fun get(): ConfigurationEntity {
        val configResponse = parseResult { apiConfiguration.getConfiguration().execute() }!!
        return ConfigurationEntity(
                configResponse.imageConfig.baseUrl,
                configResponse.imageConfig.posterSizes[3],
                configResponse.imageConfig.posterSizes[1])
    }
}