package net.treelzebub.umap.net

import com.squareup.okhttp.Request
import com.squareup.okhttp.RequestBody
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import org.scribe.exceptions.OAuthException
import org.scribe.model.Token
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import java.io.IOException

/**
 * Created by Tre Murillo on 10/11/15
 */
class SigningOkClient {

    companion object {
        fun create(consumer: OkHttpOAuthConsumer, token: Token): OkHttpClient {
            consumer.setTokenWithSecret(token.token, token.secret)
            return OkHttpClient.Builder()
                    .addInterceptor(OauthInterceptor(consumer))
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
        }
    }
}

private class OauthInterceptor(private val consumer: OkHttpOAuthConsumer) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return try {
            val wrapRequest = (consumer.sign(request).unwrap() as Request).ok3()
            chain.proceed(wrapRequest)
        } catch (e: OAuthException) {
            throw IOException("Could not sign request", e)
        }
    }

    private fun Request.ok3(): okhttp3.Request {
        val builder = okhttp3.Request.Builder()
        val headers = headers()
        headers.names().forEach {
            builder.addHeader(it, headers[it])
        }
        builder.url(url())
        builder.method(method(), body().ok3())
        return builder.build()
    }

    private fun RequestBody.ok3(): okhttp3.RequestBody {
        val mediaType = MediaType.parse(contentType().toString())
        val buffer = Buffer().apply { writeTo(this) }
        return okhttp3.RequestBody.create(mediaType, buffer.readByteArray())
    }
}