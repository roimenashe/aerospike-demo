package com.example.aerospikedemo.stuff

import com.aerospike.client.AerospikeClient
import com.aerospike.client.policy.ClientPolicy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringBootConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.data.aerospike.cache.AerospikeCacheConfiguration
import org.springframework.data.aerospike.cache.AerospikeCacheManager
import org.springframework.data.aerospike.convert.AerospikeCustomConversions
import org.springframework.data.aerospike.convert.AerospikeTypeAliasAccessor
import org.springframework.data.aerospike.convert.MappingAerospikeConverter
import org.springframework.data.aerospike.mapping.AerospikeMappingContext
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories
import org.springframework.data.mapping.model.SimpleTypeHolder

@SpringBootConfiguration
@EnableAerospikeRepositories
@Import(
    value = [
        AerospikeMappingContext::class,
        AerospikeTypeAliasAccessor::class,
        AerospikeCustomConversions::class,
        SimpleTypeHolder::class,
        MappingAerospikeConverter::class,
    ]
)
class AerospikeConfiguration {

    @Autowired
    lateinit var mappingAerospikeConverter: MappingAerospikeConverter

    @Bean(destroyMethod = "close")
    fun aerospikeClient(): AerospikeClient {
        val clientPolicy = ClientPolicy()
        clientPolicy.failIfNotConnected = true

        return AerospikeClient(
            clientPolicy,
            "127.0.0.1",
            3000
        )
    }

    @Bean
    fun cacheManager(aerospikeClient: AerospikeClient): AerospikeCacheManager {
        val defaultConfiguration = AerospikeCacheConfiguration("test")
        return AerospikeCacheManager(aerospikeClient, mappingAerospikeConverter, defaultConfiguration)
    }
}
