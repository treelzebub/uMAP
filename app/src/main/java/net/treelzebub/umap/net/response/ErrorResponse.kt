package net.treelzebub.umap.net.response

/**
 * Created by Tre Murillo on 8/13/16
 */
class ErrorResponse {
    var error: Error? = null

    class Error {
        var data: Data? = null

        class Data {
            var message: String = ""
        }
    }
}
