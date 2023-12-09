package com.example.mvvmrecipeapp.util

/**
 * Utility functions to map data from [network] to [domainmodel]
 * and  from [domainmodel] to [network]
 */
interface EntityMapper <Entity, DomainModel>{
    fun mapFromEntity(entity: Entity): DomainModel

    fun mapToEntity(domainModel: DomainModel): Entity
}