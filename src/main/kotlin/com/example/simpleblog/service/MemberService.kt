package com.example.simpleblog.service

import com.example.simpleblog.domain.member.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    @Transactional(readOnly = true)
    fun findAll(pageable: Pageable): Page<MemberRes> = memberRepository.findMembers(pageable).map { it.toDto() }

    @Transactional
    fun saveMember(dto: MemberSaveReq): MemberRes {
        return memberRepository.save(dto.toEntity()).toDto()
    }

    @Transactional
    fun deleteMember(id: Long) {
        return memberRepository.deleteById(id)
    }

    @Transactional(readOnly = true)
    fun findMember(id: Long): MemberRes {
        return memberRepository.findById(id).orElseThrow().toDto()
    }

}




