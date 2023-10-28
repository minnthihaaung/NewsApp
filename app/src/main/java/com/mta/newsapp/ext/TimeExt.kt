package com.mta.newsapp.ext

import org.ocpsoft.prettytime.PrettyTime
import java.time.LocalDateTime

fun LocalDateTime.toPrettyTime(): String {
  return PrettyTime().format(this)
}