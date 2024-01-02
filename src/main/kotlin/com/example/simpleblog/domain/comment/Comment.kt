package com.example.simpleblog.domain.comment

import com.example.simpleblog.domain.AuditingEntity
import com.example.simpleblog.domain.post.Post
import jakarta.persistence.*

@Entity
@Table(name = "Comment")
class Comment(
    title: String,
    content: String,
    post: Post
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
}