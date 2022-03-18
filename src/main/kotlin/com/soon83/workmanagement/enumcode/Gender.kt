package com.soon83.workmanagement.enumcode

enum class Gender(
    val title: String,
) {
    MALE("남성"),
    FEMALE("여성");

    companion object {
        private val TITLE_CACHE: Map<String, Gender> = values().associateBy { it.title }

        fun of(title: String): Gender? {
            return TITLE_CACHE[title]
        }
    }
}