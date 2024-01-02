package com.example.simpleblog.domain.member

import com.example.simpleblog.domain.member.QMember.member
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.support.PageableExecutionUtils

interface MemberRepository : JpaRepository<Member, Long>, MemberCustomRepository {

}

interface MemberCustomRepository {
    fun findMembers(pageable: Pageable): Page<Member>
}

class MemberCustomRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : MemberCustomRepository {

    override fun findMembers(pageable: Pageable): Page<Member> {
        val countQuery: Long = queryFactory.select(member.count()).from(member).fetchFirst()

        val results = queryFactory.selectFrom(member)
            .orderBy(member.id.desc())
            .limit(pageable.pageSize.toLong())
            .offset(pageable.offset)
            .fetch()


        return PageableExecutionUtils.getPage(results, pageable) { countQuery }
    }

}