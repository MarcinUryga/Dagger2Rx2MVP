package com.example.marcin.mypodcasts.ui.login

import com.example.marcin.mypodcasts.di.ScreenScope
import com.example.marcin.mypodcasts.model.RegisterRequest
import com.example.marcin.mypodcasts.mvp.BasePresenter
import com.example.marcin.mypodcasts.ui.common.LoginManager
import com.example.marcin.mypodcasts.ui.login.LoginContract.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by marci on 2017-10-13.
 */

@ScreenScope
class LoginPresenter @Inject constructor(
    private val userLoginUseCase: UserLoginUseCase,
    private val loginManager: LoginManager
) : BasePresenter<LoginContract.View>(), Presenter {

  override fun onViewCreated() {
    super.onViewCreated()
    if (loginManager.isLogged()) {
      view.startMainMenuActivity()
    }
  }

  override fun onLoginClicked(email: String, password: String) {
    view.hideKeyboard()
    validateLoginInput(email, password)
  }

  override fun onRegisterClicked() {
    view.startRegisterActivity()
  }

  private fun validateLoginInput(username: String, password: String) {
    if (view.validateEmailField()) {
      view.showEmailError()
    }
    if (view.validatePasswordField()) {
      view.showPasswordError()
    } else {
      loginUser(RegisterRequest(
          username = username,
          password = password
      ))
    }
  }

  private fun loginUser(registerRequest: RegisterRequest) {
    val disposable = userLoginUseCase.tryToLogin(registerRequest)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { view.showProgressBar() }
        .doFinally { view.hideProgressBar() }
        .subscribe({ user ->
          loginManager.login(user.objectId, user.sessionToken)
          view.startMainMenuActivity()
        }, { error ->
          view.showError(error.localizedMessage)
        })
    disposables?.add(disposable)


  }
}