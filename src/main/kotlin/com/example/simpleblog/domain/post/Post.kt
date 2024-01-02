package com.example.simpleblog.domain.post

import com.example.simpleblog.domain.AuditingEntity
import com.example.simpleblog.domain.member.Member
import jakarta.persistence.*

/**
 * 게시물 entity
 */
@Entity
@Table(name = "Post")
class Post(
    title: String,
    content: String,
    member: Member
) : AuditingEntity() {

    /**
     * 제목
     */
    @Column(name = "title", nullable = false)
    var title: String = title
        protected set

    /**
     * 본문
     */
    @Column(name = "content")
    var content: String = content // 본문
        protected set

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Member::class)
    var member: Member = member
        protected set
}