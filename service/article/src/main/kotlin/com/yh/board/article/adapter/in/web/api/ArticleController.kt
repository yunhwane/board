package com.yh.board.article.adapter.`in`.web.api

import com.yh.board.article.adapter.`in`.web.request.ArticleCreateRequest
import com.yh.board.article.adapter.`in`.web.request.ArticleUpdateRequest
import com.yh.board.article.adapter.`in`.web.response.ArticleCreateResponse
import com.yh.board.article.adapter.`in`.web.response.ArticleUpdateResponse
import com.yh.board.article.adapter.`in`.web.support.ApiResponse
import com.yh.board.article.application.port.`in`.*
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import java.net.URI

@RestController
@RequestMapping("/v1/articles")
class ArticleController(
    private val articleCreateUseCase: ArticleCreateUseCase,
    private val articleUpdateUseCase: ArticleUpdateUseCase,
    private val articleDeleteUseCase: ArticleDeleteUseCase,
) {

    @PostMapping
    fun create(@RequestBody articleCreateRequest: ArticleCreateRequest): ResponseEntity<ApiResponse<ArticleCreateResponse>> {
        val command = ArticleCreateCommand(
            title = articleCreateRequest.title,
            content = articleCreateRequest.content,
            writerId = articleCreateRequest.writerId,
            boardId = articleCreateRequest.boardId,
        )

        val result = articleCreateUseCase.execute(command)

        val uri = URI("/v1/articles/${result.articleId}")

        return ResponseEntity.created(uri).body(
            ApiResponse.with(
                httpStatus = HttpStatus.CREATED,
                message = "게시글이 생성되었습니다.",
                data = ArticleCreateResponse(
                    articleId = result.articleId,
                    title = result.title,
                    content = result.content,
                    boardId = result.boardId,
                    writerId = result.writerId,
                    createdAt = result.createdAt,
                    updatedAt = result.updatedAt,
                )
            )
        )
    }

    @PutMapping("/{articleId}")
    fun update(@PathVariable articleId: Long, @RequestBody request: ArticleUpdateRequest): ResponseEntity<ApiResponse<ArticleUpdateResponse>> {
        val command = ArticleUpdateCommand(
            id = articleId,
            title = request.title,
            content = request.content,
        )

        val article = articleUpdateUseCase.execute(command)

        return ResponseEntity.ok().body(
            ApiResponse.with(
                httpStatus = HttpStatus.OK,
                message = "게시글이 수정되었습니다.",
                data = ArticleUpdateResponse(
                    articleId = article.articleId,
                    title = article.title,
                    content = article.content,
                    boardId = article.boardId,
                    writerId = article.writerId,
                    createdAt = article.createdAt,
                    updatedAt = article.updatedAt,
                )
            )
        )
    }

    @DeleteMapping("/{articleId}")
    fun delete(@PathVariable articleId: Long): ResponseEntity<ApiResponse<Void?>> {
        articleDeleteUseCase.execute(articleId = articleId)
        return ResponseEntity.ok().body(
            ApiResponse.with(
                httpStatus = HttpStatus.OK,
                message = "게시글이 삭제되었습니다.",
                data = null
            )
        )
    }
}