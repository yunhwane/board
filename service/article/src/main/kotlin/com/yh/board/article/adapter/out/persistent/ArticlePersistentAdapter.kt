package com.yh.board.article.adapter.out.persistent

import com.yh.board.annotation.PersistentAdapter
import com.yh.board.article.adapter.out.persistent.entity.ArticleEntity
import com.yh.board.article.adapter.out.persistent.repository.JpaArticleRepository
import com.yh.board.article.application.port.`in`.ArticleCreateCommand
import com.yh.board.article.application.port.out.LoadArticlePort
import com.yh.board.article.application.port.out.SaveArticlePort
import com.yh.board.article.domain.Article
import com.yh.board.snowflake.Snowflake

@PersistentAdapter
class ArticlePersistentAdapter(
    private val jpaArticleRepository: JpaArticleRepository,
) : SaveArticlePort, LoadArticlePort {

    private val snowflake = Snowflake();

    override fun save(command: Article): Article {
        val articleEntity = ArticleEntity(
            id = snowflake.nextId(),
            title = command.title,
            content = command.content,
            boardId = command.boardId,
            writerId = command.writerId
        )

        val saveArticleEntity = jpaArticleRepository.save(articleEntity)

        return Article(
            articleId = saveArticleEntity.id!!,
            title = saveArticleEntity.title,
            content = saveArticleEntity.content,
            boardId = saveArticleEntity.boardId,
            writerId = saveArticleEntity.writerId,
            createdAt = saveArticleEntity.createdAt,
        )
    }

    override fun update(article: Article): Article {
        val article = getArticle(articleId = article.articleId!!)

        val articleEntity = ArticleEntity(
            id = article.articleId,
            title = article.title,
            content = article.content,
            boardId = article.boardId,
            writerId = article.writerId,
            updatedAt = article.updatedAt
        )

        val saveArticleEntity = jpaArticleRepository.save(articleEntity)

        return Article(
            articleId = saveArticleEntity.id!!,
            title = saveArticleEntity.title,
            content = saveArticleEntity.content,
            boardId = saveArticleEntity.boardId,
            writerId = saveArticleEntity.writerId,
            createdAt = saveArticleEntity.createdAt,
            updatedAt = saveArticleEntity.updatedAt
        )
    }

    override fun delete(articleId: Long) {
        val article = getArticle(articleId = articleId)
        jpaArticleRepository.deleteById(article.articleId!!)
    }

    override fun getArticle(articleId: Long): Article {
        val articleEntity = jpaArticleRepository.findById(articleId).orElseThrow {
            IllegalArgumentException("Article not found with id: $articleId")
        }

        return Article(
            articleId = articleEntity.id!!,
            title = articleEntity.title,
            content = articleEntity.content,
            boardId = articleEntity.boardId,
            writerId = articleEntity.writerId,
            createdAt = articleEntity.createdAt,
        )
    }
}