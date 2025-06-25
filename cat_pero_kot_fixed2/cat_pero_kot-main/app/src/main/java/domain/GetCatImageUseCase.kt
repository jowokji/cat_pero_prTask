package domain

import com.example.network.data.model.CatImageModel
import com.example.network.data.repositorty.CatRepository
import com.example.network.data.repository.CatRepository
import javax.inject.Inject

class GetCatImagesUseCase @Inject constructor(
    private val repository: CatRepository
) {
    suspend operator fun invoke(limit: Int): List<CatImageModel> {
        return repository.fetchCatImages(limit)
    }
}

annotation class Inject
