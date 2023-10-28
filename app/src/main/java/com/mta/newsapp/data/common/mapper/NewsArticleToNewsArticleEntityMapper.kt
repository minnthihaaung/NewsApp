package com.mta.newsapp.data.common.mapper

import com.mta.newsapp.data.cache.entity.NewsArticleCacheEntity
import com.mta.newsapp.domain.newsarticle.model.NewsArticle

object NewsArticleToNewsArticleEntityMapper {

  fun mapToNewsArticleCacheEntity(newsArticle: NewsArticle) = NewsArticleCacheEntity(
    sourceName = newsArticle.sourceName,
    title = newsArticle.title,
    author = newsArticle.author,
    content = newsArticle.content,
    description = newsArticle.description,
    publishedAt = newsArticle.publishedAt,
    url = newsArticle.url,
    imageUrl = newsArticle.imageUrl,
    category = newsArticle.category.name,
    isFavourite = newsArticle.isFavourite
  )
}