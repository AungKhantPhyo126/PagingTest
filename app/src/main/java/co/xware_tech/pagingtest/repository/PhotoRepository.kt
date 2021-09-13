package co.xware_tech.pagingtest.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import co.xware_tech.pagingtest.domain.Photo
import co.xware_tech.pagingtest.network.service.PhotoService
import co.xware_tech.pagingtest.pagingSources.PhotoPagingSource
import kotlinx.coroutines.flow.Flow

class PhotoRepository {
    private val photoService= PhotoService.photoService
    fun getPhotoList(): Flow<PagingData<Photo>> {
        return Pager(
            PagingConfig(10)
        ) {
            PhotoPagingSource(photoService)
        }.flow
    }
}