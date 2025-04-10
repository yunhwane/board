package com.yh.board.article.adapter.out.persistent

import com.yh.board.annotation.PersistentAdapter
import com.yh.board.article.adapter.out.persistent.entity.ArticleEntity
import com.yh.board.article.adapter.out.persistent.repository.JpaArticleRepository
import com.yh.board.article.application.port.`in`.ArticleCreateCommand
import com.yh.board.article.application.port.out.SaveArticlePort
import com.yh.board.article.domain.ArticleCreateResult
import com.yh.board.snowflake.Snowflake

@PersistentAdapter
class ArticlePersistentAdapter(
    private val jpaArticleRepository: JpaArticleRepository,
) : SaveArticlePort {

    private val snowflake = Snowflake();

    override fun save(command: ArticleCreateCommand): ArticleCreateResult {
        val articleEntity = ArticleEntity(
            id = snowflake.nextId(),
            title = command.title,
            content = command.content,
            boardId = command.boardId,
            writerId = command.writerId
        )

        val saveArticleEntity = jpaArticleRepository.save(articleEntity)

        return ArticleCreateResult(
            articleId = saveArticleEntity.id!!,
            title = saveArticleEntity.title,
            content = saveArticleEntity.content,
            boardId = saveArticleEntity.boardId,
            writerId = saveArticleEntity.writerId,
            createdAt = saveArticleEntity.createdAt,
        )
    }
}