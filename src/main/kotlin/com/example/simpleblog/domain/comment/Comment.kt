package com.example.simpleblog.domain.comment

import com.example.simpleblog.domain.AuditingEntity
import com.example.simpleblog.domain.member.Member
import com.example.simpleblog.domain.post.Post
import jakarta.persistence.*

@Entity
@Table(name = "Comment")
class Comment(
    content: String,
    post: Post,
    member: Member
) : AuditingEntity() {
    /**
     * 본문
     */
    @Column(name = "content", nullable = false)
    var content: String = content // 본문
        protected set

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Post::class)
    var post: Post = post
        protected set


    @ManyToOne(fetch = FetchType.LAZY)
    var member: Member = member
        protected set

    override fun toString(): String {
        return "Comment(content='$content', post=$post, member=$member)"
    }
}

