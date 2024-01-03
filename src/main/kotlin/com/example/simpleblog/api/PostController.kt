package com.example.simpleblog.api

import com.example.simpleblog.domain.post.PostSaveReq
import com.example.simpleblog.service.PostService
import com.example.simpleblog.util.value.CmResDto
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class PostController(
    private val postService: PostService
) {
    @GetMapping("/posts")
    fun findPosts(@PageableDefault(size = 10) pageable: Pageable): CmResDto<Any> {
        return CmResDto(HttpStatus.OK, "find posts", postService.findPosts(pageable))
    }

    @GetMapping("/post/{id}")
    fun findById(@PathVariable id: Long): CmResDto<Any> {
        return CmResDto(HttpStatus.OK, "find post by id", postService.findPost(id))
    }

    @DeleteMapping("/post/{id}")
    fun deleteById(@PathVariable id: Long): CmResDto<Any> {
        return CmResDto(HttpStatus.OK, "delete post by id", postService.deletePost(id))
    }

    @PostMapping("/post")
    fun save(@RequestBody dto: PostSaveReq): CmResDto<Any> {
        return CmResDto(HttpStatus.OK, "save post", postService.savePost(dto))
    }
}