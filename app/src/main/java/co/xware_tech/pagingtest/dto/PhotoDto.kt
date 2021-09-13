package co.xware_tech.pagingtest.dto

import co.xware_tech.pagingtest.domain.Photo

data class PhotoDto(
    val id: String?,
    val author: String?,
    val width: Long?,
    val height: Long?,
    val url: String?,
    val download_url:String?
)

fun PhotoDto.asDomain():Photo{
    return Photo(
        id = id.orEmpty(),
        author = author.orEmpty(),
        width = width?:0,
        height = height?:0,
        url = url.orEmpty(),
        download_url = download_url.orEmpty()
    )
}
