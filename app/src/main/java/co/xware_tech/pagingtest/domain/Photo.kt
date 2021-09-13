package co.xware_tech.pagingtest.domain

data class Photo(
    val id: String,
    val author: String,
    val width: Long,
    val height: Long,
    val url: String,
    val download_url:String
)