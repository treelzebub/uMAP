package net.treelzebub.umap.activity.login

import net.treelzebub.umap.conduit.LoginInteractor

/**
 * Created by Tre Murillo on 8/6/16.
 */
object LoginMvp {

    interface View {

    }

    class Presenter(val view: View) {

        fun set() {

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
