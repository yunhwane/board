package com.yh.board.article.domain

import java.time.LocalDateTime

data class Article(
    val articleId: Long,
    val title: String,
    val content: String,
    val boardId: Long,
    val writerId: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
) {

    fun update(title: String, content: String, ): Article {
        return this.copy(
            title = title,
            content = content,
            updatedAt = LocalDateTime.now()
        )
    }
}