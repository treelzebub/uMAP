package net.treelzebub.umap.net

import com.squareup.okhttp.OkHttpClient
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor

/**
 * Created by Tre Murillo on 10/11/15
 */
class SigningOkClient(consumer: OkHttpOAuthConsumer) : OkHttpClient() {
    init {
        interceptors().add(SigningInterceptor(consumer))
    }
}
