package net.treelzebub.umap.auth

import net.treelzebub.umap.Constants
import org.scribe.builder.api.DefaultApi10a
import org.scribe.model.Token

/**
 * Created by Tre Murillo on 5/28/15
 */
public class DiscogsOauth() : DefaultApi10a() {

    override fun getRequestTokenEndpoint(): String {
        return Constants.DISCOGS_REQUEST_TOKEN_URL
    }

    override fun getAccessTokenEndpoint(): String {
        return Constants.DISCOGS_ACCESS_TOKEN_URL
    }

    override fun getAuthorizationUrl(requestToken: Token): String {
        return Constants.DISCOGS_AUTHORIZATION_URL
    }
}
