package net.treelzebub.umap.api.discogs

import com.squareup.okhttp.OkHttpClient
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer
import se.akerfeldt.okhttp.signpost.SigningInterceptor

/**
 * Created by Tre Murillo on 10/11/15
 */
public class SigningOkClient : OkHttpClient {

    constructor(consumer: OkHttpOAuthConsumer) {
        interceptors().add(SigningInterceptor(consumer))
    }

}
