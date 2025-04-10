package com.yh.board.article.adapter.`in`.web.api

import com.yh.board.article.adapter.`in`.web.request.ArticleCreateRequest
import com.yh.board.article.adapter.`in`.web.response.ArticleCreateResponse
import com.yh.board.article.adapter.`in`.web.support.ApiResponse
import com.yh.board.article.application.port.`in`.ArticleCreateCommand
import com.yh.board.article.application.port.`in`.ArticleCreateUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity
import java.net.URI

@RestController
@RequestMapping("/v1/articles")
class ArticleController(
    private val articleCreateUseCase: ArticleCreateUseCase,
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
}