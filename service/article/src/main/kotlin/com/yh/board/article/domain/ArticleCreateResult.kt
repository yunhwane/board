package com.yh.board.article.domain

import java.time.LocalDateTime

data class ArticleCreateResult(
    val articleId: Long,
    val title: String,
    val content: String,
    val boardId: Long,
    val writerId: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
) {
}