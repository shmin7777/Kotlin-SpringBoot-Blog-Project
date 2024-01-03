package com.example.simpleblog.service

import com.example.simpleblog.domain.post.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val postRepository: PostRepository
) {

    @Transactional(readOnly = true)
    fun findPosts(pageable: Pageable): Page<PostRes> =
        postRepository.findPosts(pageable).map { it.toDto() }

    @Transactional
    fun savePost(dto: PostSaveReq): PostRes {
        return postRepository.save(dto.toEntity()).toDto()

    }

    @Transactional
    fun deletePost(id: Long) {
        return postRepository.deleteById(id)
    }

    @Transactional(readOnly = true)
    fun findPost(id: Long): PostRes {
        return postRepository.findById(id).orElseThrow().toDto()
    }
}
