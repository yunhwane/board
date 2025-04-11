package com.yh.board.article.application.port.`in`

data class ArticleUpdateCommand(
    val id: Long,
    val title: String,
    val content: String,
){
}