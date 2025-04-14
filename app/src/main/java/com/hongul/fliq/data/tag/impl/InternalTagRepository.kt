package com.hongul.fliq.data.tag.impl

import com.hongul.fliq.data.tag.TagDao
import com.hongul.fliq.data.tag.TagRepository

class InternalTagRepository(
    private val dao: TagDao
): TagRepository {
}