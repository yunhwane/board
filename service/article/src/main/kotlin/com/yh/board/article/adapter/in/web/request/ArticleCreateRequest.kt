package com.yh.board.article.adapter.`in`.web.request

data class ArticleCreateRequest(
    val title: String,
    val content: String,
    val writerId: Long,
    val boardId: Long,
)
