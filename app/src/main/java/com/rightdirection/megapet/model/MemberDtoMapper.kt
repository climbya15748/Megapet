package com.rightdirection.megapet.model

import com.rightdirection.megapet.util.DomainModelMapper
import retrofit2.Response

class MemberDtoMapper : DomainModelMapper<MemberDto,Member> {

    override fun mapToDomainModel(model: MemberDto): Member {
        return Member(
            id = model.id,
            firstname = model.firstname,
            lastname = model.lastname,
            password = model.password,
            email = model.email,
            point_bal = model.point_bal,
            point_earned = model.point_earned,
            point_redeemed = model.point_redeemed,
            phone = model.phone,
            type = model.type,
            join_date = null,
            order_history = null
        )
    }

    override fun mapFromDomainModel(domainModel: Member): MemberDto {
        return MemberDto(
            id = domainModel.id,
            firstname = domainModel.firstname,
            lastname = domainModel.lastname,
            password = domainModel.password,
            email = domainModel.email,
            point_bal = domainModel.point_bal,
            point_earned = domainModel.point_earned,
            point_redeemed = domainModel.point_redeemed,
            phone = domainModel.phone,
            type = domainModel.type
        )
    }

    fun toDomainModelList(initial: List<MemberDto>): List<Member>{
        return  initial.map {mapToDomainModel(it)}
    }

    fun fromDomainModelList(initial: List<Member>): List<MemberDto>{
        return  initial.map{ mapFromDomainModel(it)}
    }

}