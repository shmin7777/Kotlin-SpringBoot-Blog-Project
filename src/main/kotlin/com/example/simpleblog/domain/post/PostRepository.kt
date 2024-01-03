package com.example.simpleblog.domain.post

import com.example.simpleblog.domain.post.QPost.post
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.support.PageableExecutionUtils

interface PostRepository : JpaRepository<Post, Long>, PostCustomRepository {
}

interface PostCustomRepository {
    fun findPosts(pageable: Pageable): Page<Post>
}

class PostCustomRepositoryImpl(
    private val queryFactory: JPAQueryFactory
) : PostCustomRepository {
    override fun findPosts(pageable: Pageable): Page<Post> {
        val countQuery: Long = queryFactory.select(post.count()).from(post).fetchFirst()

        val results = queryFactory.selectFrom(post)
            .orderBy(post.id.desc())
            .limit(pageable.pageSize.toLong())
            .offset(pageable.offset)
            .fetch()


        return PageableExecutionUtils.getPage(results, pageable) { countQuery }
    }
}
