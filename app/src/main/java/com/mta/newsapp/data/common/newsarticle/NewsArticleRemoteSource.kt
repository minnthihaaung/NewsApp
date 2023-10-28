package com.mta.newsapp.data.common.newsarticle

import com.mta.newsapp.domain.newsarticle.model.NewsArticle

interface NewsArticleRemoteSource {

  fun getNewsArticles(
  ): List<NewsArticle>

  fun getNewsArticlesWithCategory(
    category: String,
  ): List<NewsArticle>
}