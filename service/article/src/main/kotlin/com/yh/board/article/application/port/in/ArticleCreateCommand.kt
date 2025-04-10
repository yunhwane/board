package com.yh.board.article.application.port.`in`

data class ArticleCreateCommand(
    val title: String,
    val content: String,
    val writerId: Long,
    val boardId: Long,
)