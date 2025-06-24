
package domain

import com.example.network.data.model.CatImageModel
import com.example.network.data.repositorty.CatRepository
import javax.inject.Inject

class GetCatImagesUseCase @Inject constructor(
    private val repository: CatRepository
) {
    suspend operator fun invoke(page: Int, limit: Int = 10): Result<List<CatImageModel>> {
        return repository.fetchCatImages(page, limit)
    }
}
