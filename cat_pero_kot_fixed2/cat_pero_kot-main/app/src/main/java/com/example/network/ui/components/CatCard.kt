import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.catapi.R
import kotlin.random.Random
import coil.transform.Transformation
import android.graphics.Bitmap
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DelayTransformation(private val delayMillis: Long = 1000L) : Transformation {
    override val cacheKey: String = "delay_$delayMillis"
    override suspend fun transform(input: Bitmap, size: coil.size.Size): Bitmap {
        withContext(Dispatchers.Default) {
            Thread.sleep(delayMillis)
        }
        return input
    }
}

@Composable
fun CatCard(imageUrl: String, contentDescription: String?) {
    val placeholders = listOf(
        R.drawable.placeholder,
        R.drawable.placeholder2,
        R.drawable.placeholder3
    )
    val randomPlaceholder = remember { placeholders[Random.nextInt(placeholders.size)] }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = coil.request.ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .transformations(DelayTransformation(1000L))
                .build(),
            contentDescription = contentDescription,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .aspectRatio(1.2f),
            placeholder = painterResource(randomPlaceholder),
            error = painterResource(R.drawable.error)
        )
    }
}