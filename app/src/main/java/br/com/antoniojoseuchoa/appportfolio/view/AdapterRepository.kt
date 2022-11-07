package br.com.antoniojoseuchoa.appportfolio.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import br.com.antoniojoseuchoa.appportfolio.databinding.ActivityMainBinding
import br.com.antoniojoseuchoa.appportfolio.databinding.ItemRepositorioBinding
import br.com.antoniojoseuchoa.appportfolio.domain.RepositoryElement

class AdapterRepository(val list: List<RepositoryElement>): RecyclerView.Adapter<AdapterRepository.ViewHolder>() {

    var clickItemCompartilhar: (RepositoryElement) -> Unit = {}
    var clickItemRepositorio: (RepositoryElement) -> Unit = {}

    class ViewHolder(val binding: ItemRepositorioBinding): RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
          val layoutInflater = LayoutInflater.from(parent.context)
          val binding = ItemRepositorioBinding.inflate( layoutInflater, parent, false )
          return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository = list[position]
        holder.binding.tvNomeRepositorio.text = repository.name

        holder.binding.imageView.setOnClickListener {
              clickItemCompartilhar(repository)

        }
        holder.itemView.setOnClickListener{
              clickItemRepositorio(repository)
        }

    }

    override fun getItemCount() = list.size
}