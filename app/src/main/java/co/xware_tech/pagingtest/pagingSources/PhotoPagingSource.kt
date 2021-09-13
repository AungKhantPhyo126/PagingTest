package co.xware_tech.pagingtest.pagingSources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import co.xware_tech.pagingtest.domain.Photo
import co.xware_tech.pagingtest.dto.PhotoDto
import co.xware_tech.pagingtest.dto.asDomain
import co.xware_tech.pagingtest.network.service.PhotoService
import co.xware_tech.pagingtest.network.service.PhotoServiceInterface
import retrofit2.HttpException
import java.io.IOException

class PhotoPagingSource(
    private val photoService: PhotoServiceInterface
):PagingSource<Int,Photo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val currentPageNumber = params.key ?: 1
            val response = photoService.getPhotoLists(currentPageNumber, params.loadSize)
            LoadResult.Page(
                data = response.body()?.map { it.asDomain() }.orEmpty(),
                prevKey = null,
                nextKey =  currentPageNumber + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

}