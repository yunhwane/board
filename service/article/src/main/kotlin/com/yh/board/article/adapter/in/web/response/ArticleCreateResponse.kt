package com.yh.board.article.adapter.`in`.web.response

import java.time.LocalDateTime

data class ArticleCreateResponse(
    val articleId: Long,
    val title: String,
    val content: String,
    val boardId: Long,
    val writerId: Long,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
) {
}