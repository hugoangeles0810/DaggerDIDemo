package io.github.hugoangeles0810.daggerdidemo.data.datasource

import io.github.hugoangeles0810.daggerdidemo.domain.entities.Configuration

interface ConfigurationDataSource {

    fun get(): Configuration
}