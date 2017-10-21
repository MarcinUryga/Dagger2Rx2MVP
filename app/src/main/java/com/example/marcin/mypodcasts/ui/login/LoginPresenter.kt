package com.example.marcin.mypodcasts.ui.login

import android.content.SharedPreferences
import com.example.marcin.mypodcasts.di.ScreenScope
import com.example.marcin.mypodcasts.model.RegisterRequest
import com.example.marcin.mypodcasts.storage.UserStorage
import com.example.marcin.mypodcasts.mvp.BasePresenter
import com.example.marcin.mypodcasts.ui.login.LoginContract.Presenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by marci on 2017-10-13.
 */

@ScreenScope
class LoginPresenter @Inject constructor(
    private val userLoginUseCase: UserLoginUseCase,
    private val sharedPreferences: SharedPreferences
) : BasePresenter<LoginContract.View>(), Presenter {

  private val userStorage = UserStorage(sharedPreferences)

  override fun onViewCreated() {
    super.onViewCreated()
    if (userStorage.logged()) {
      view.startMainMenuActivity()
    }
  }

  override fun onLoginClicked(username: String, password: String) {
    view.hideKeyboard()
    validateLoginInput(username, password)
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
        .subscribe({ response ->
          Timber.d(response.toString())
          userStorage.saveToStorage(response)
          view.startMainMenuActivity()
        }, { error ->
          view.showError("Wrong email/password")
        })
    disposables?.add(disposable)


  }
}