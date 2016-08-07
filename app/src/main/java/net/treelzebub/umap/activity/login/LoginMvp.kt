package net.treelzebub.umap.activity.login

import net.treelzebub.umap.conduit.LoginInteractor
import net.treelzebub.umap.conduit.RequestTokenInteractor

/**
 * Created by Tre Murillo on 8/6/16.
 */
object LoginMvp {

    interface View {

    }

    class Presenter(val view: View) {

        fun loadAuthUrl(interactor: RequestTokenInteractor) {
            interactor.load()
        }

        fun getAccessToken(interactor: LoginInteractor, url: String) {
            interactor.load(url)
        }
    }
}

class LoginView : LoginMvp.View {

}

class MockLoginView :LoginMvp.View {

}
