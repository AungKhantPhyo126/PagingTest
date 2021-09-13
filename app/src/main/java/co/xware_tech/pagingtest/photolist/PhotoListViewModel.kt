package co.xware_tech.pagingtest.photolist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import co.xware_tech.pagingtest.domain.Photo
import co.xware_tech.pagingtest.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow


class PhotoListViewModel :ViewModel() {
    private val photoRepository=PhotoRepository()
    var photoListLive : Flow<PagingData<Photo>>? =null
    var testValue = MutableLiveData<Int>(0)

    fun getPhotoList(){
        photoListLive = photoRepository.getPhotoList()
    }

    init {
        getPhotoList()
        testValue.value=12
    }
}