package com.yh.board.article.adapter.out.persistent.repository

import com.yh.board.article.adapter.out.persistent.entity.ArticleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaArticleRepository : JpaRepository<ArticleEntity, Long> {
}