package smir.shitab.graphqlapicalldemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.graphql.query.ListOfCharactersResultQuery
import kotlinx.coroutines.*
import okhttp3.OkHttpClient

@DelicateCoroutinesApi
class MainActivity : AppCompatActivity() {

    lateinit var apolloClient: ApolloClient
    val BASE_URL = "https://rickandmortyapi.com/graphql"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()

        apolloClient = ApolloClient.Builder()
            .serverUrl(BASE_URL)
            .okHttpClient(okHttpClient)
            .build()

        GlobalScope.launch {
            delay(1000)
            getData()
        }

    }

    private suspend fun getData() {
        val response = apolloClient.query(ListOfCharactersResultQuery()).execute()
        Log.d("LaunchList", "Success ${response.data}")
    }

}