import com.example.flickrimagesearch.data.model.remote.FlickrImage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatPublishedDate(dateString: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH)
        val outputFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.ENGLISH)
        val date = inputFormat.parse(dateString)
        outputFormat.format(date ?: Date())
    } catch (e: Exception) {
        dateString
    }
}

fun createShareIntent(image: FlickrImage?): android.content.Intent {
    val shareText = """
        Check out this image from Flickr!
        Title: ${image?.title}
        Author: ${image?.author}
        Link: ${image?.link}
    """.trimIndent()

    return android.content.Intent().apply {
        action = android.content.Intent.ACTION_SEND
        type = "text/plain"
        putExtra(android.content.Intent.EXTRA_TEXT, shareText)
    }
}