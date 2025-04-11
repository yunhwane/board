package com.yh.board.article.application.port.out

import com.yh.board.article.domain.Article

interface LoadArticlePort {
    fun getArticle(articleId: Long): Article
}