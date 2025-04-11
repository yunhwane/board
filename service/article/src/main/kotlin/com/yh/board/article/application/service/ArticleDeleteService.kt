package com.yh.board.article.application.service

import com.yh.board.annotation.UseCase
import com.yh.board.article.application.port.`in`.ArticleDeleteUseCase
import com.yh.board.article.application.port.out.LoadArticlePort
import com.yh.board.article.application.port.out.SaveArticlePort
import org.springframework.transaction.annotation.Transactional


@UseCase
class ArticleDeleteService(
    private val saveArticlePort: SaveArticlePort,
    private val loadArticlePort: LoadArticlePort,
) : ArticleDeleteUseCase {


    override fun execute(articleId: Long) {
        saveArticlePort.delete(articleId)
    }

}