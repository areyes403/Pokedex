package mx.edu.itm.link.pokedex.pokemon.presenter.show_pokemons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.edu.itm.link.pokedex.pokemon.domain.model.Pokemon
import mx.edu.itm.link.pokedex.databinding.ItemPokemonBinding

class ShowPokemonsAdapter(
    private val data:List<Pokemon>
): RecyclerView.Adapter<ShowPokemonsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding= ItemPokemonBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.txtName.text=data[position].namepokemon
            //Glide.with(context).load(url).into(binding.imgImagenPokemon)
            binding.root.setOnClickListener {
                //callBack.onClick(pokemon)
            }
            binding.btnEliminarPokemonItem.setOnClickListener {
                //callBack.delete(pokemon)
            }
        }
    }

    override fun getItemCount(): Int=data.size

    inner class ViewHolder(val binding:ItemPokemonBinding):RecyclerView.ViewHolder(binding.root)

    /*
    class ShowPokemonsHolder(val binding:ItemPokemonBinding):RecyclerView.ViewHolder(binding.root){
        val url="https://pbs.twimg.com/profile_images/1178942318981701634/d5qM22Ft_400x400.jpg"
        fun render(pokemon: Pokemon){
            binding.txtName.setText(pokemon.namepokemon)
            binding.txtAtaque.setText(pokemon.attack)
            binding.txtDefensa.setText(pokemon.defense)
            binding.imgImagenPokemon
            //Glide.with(context).load(url).into(binding.imgImagenPokemon)
            binding.root.setOnClickListener {
                //callBack.onClick(pokemon)
            }
            binding.btnEliminarPokemonItem.setOnClickListener {
                //callBack.delete(pokemon)
            }

        }
    }

     */


}