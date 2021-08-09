package com.rightdirection.megapet.util

interface DomainModelMapper<T,DomainModel>  {

    fun mapToDomainModel(model:T): DomainModel

    fun mapFromDomainModel(domainModel:DomainModel): T


}