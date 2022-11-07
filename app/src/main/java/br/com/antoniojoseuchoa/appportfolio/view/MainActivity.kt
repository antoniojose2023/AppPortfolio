package br.com.antoniojoseuchoa.appportfolio.view

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
import br.com.antoniojoseuchoa.appportfolio.R
import br.com.antoniojoseuchoa.appportfolio.data.RepositoryGitHub
import br.com.antoniojoseuchoa.appportfolio.databinding.ActivityMainBinding
import br.com.antoniojoseuchoa.appportfolio.domain.RepositoryElement
import br.com.antoniojoseuchoa.appportfolio.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.URL

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var adapter: AdapterRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        adapter = AdapterRepository(listOf())

        binding.buttonBusca.setOnClickListener {
            val usuarioRepositorio = binding.editBusca.text.toString()
            if (usuarioRepositorio.isEmpty()) {
                Toast.makeText(this, "Campo vázio", Toast.LENGTH_LONG).show()
            } else {
                mainViewModel.getListRepository(usuarioRepositorio)
                ouvineteListaRepositorio()
            }
        }

    }

    fun ouvineteListaRepositorio() {
        mainViewModel.repository.observe(this) {
            when (it) {
                MainViewModel.States.Loader -> {
                       binding.progressCircular.isVisible = true
                }
                is MainViewModel.States.OnErro -> {
                    Toast.makeText(this, "Ops, erro tente novamente", Toast.LENGTH_LONG).show()
                    binding.progressCircular.isVisible = false
                }
                is MainViewModel.States.OnSucess -> {
                    binding.rvRepositorios.layoutManager = LinearLayoutManager(this)
                    adapter = AdapterRepository(it.repositories)
                    binding.rvRepositorios.adapter = adapter
                    ouvinteClicks()
                    binding.progressCircular.isVisible = false
                }
            }
        }
    }

    fun ouvinteClicks(){
        adapter.clickItemCompartilhar = {
                compartilharUrlReṕositorio(it)
        }

        adapter.clickItemRepositorio = {
               exibirRepositorioNavegador(it)
        }
    }

    fun compartilharUrlReṕositorio(repositoryElement: RepositoryElement){
          val intent = Intent(Intent.ACTION_SEND)
          intent.putExtra("repository", repositoryElement.html_url)
          intent.type = "*/*"
          startActivity(Intent.createChooser(intent, "Escolha seu app para compartilhar o repositóio."))

    }

    fun exibirRepositorioNavegador(repositoryElement: RepositoryElement){
        var url = Uri.parse(repositoryElement.html_url)
        val intent = Intent(Intent.ACTION_VIEW, url)
        startActivity(intent)
    }

    fun exibirLoader(){

    }

}