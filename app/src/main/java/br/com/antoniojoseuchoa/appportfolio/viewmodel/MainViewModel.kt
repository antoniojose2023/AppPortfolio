package br.com.antoniojoseuchoa.appportfolio.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.antoniojoseuchoa.appportfolio.data.RepositoryGitHub
import br.com.antoniojoseuchoa.appportfolio.domain.RepositoryElement
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var _reposotiry = MutableLiveData<States>()
    val repository: LiveData<States> = _reposotiry

    fun getListRepository(user: String){
        _reposotiry.value = States.Loader

        RepositoryGitHub.retrofit.getRepository(user).enqueue(object : Callback<List<RepositoryElement>>{
            override fun onResponse(call: Call<List<RepositoryElement>>, response: Response<List<RepositoryElement>>) {
                     response.let {
                         _reposotiry.value = States.OnSucess(it.body()!!)
                     }
            }
            override fun onFailure(call: Call<List<RepositoryElement>>, t: Throwable) {
                    _reposotiry.value = States.OnErro(t.message.toString())
            }

        })
    }


    sealed class States{
        object Loader: States()
        class OnSucess(val repositories: List<RepositoryElement>): States()
        class OnErro(val mensagem: String): States()
    }

}