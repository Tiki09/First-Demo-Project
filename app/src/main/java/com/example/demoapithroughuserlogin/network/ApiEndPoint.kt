import com.example.demoapithroughuserlogin.model.Album
import retrofit2.http.GET

interface ApiEndPoint {
    @GET("/albums")
    suspend fun getAlbum():Album
}