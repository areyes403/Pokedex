package mx.edu.itm.link.pokedex.pokemon.presenter.show_pokemons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.edu.itm.link.pokedex.pokemon.domain.model.Pokemon
import mx.edu.itm.link.pokedex.databinding.ItemPokemonBinding

class ShowPokemonsAdapter(private val pokemons:List<Pokemon>): RecyclerView.Adapter<ShowPokemonsAdapter.ShowPokemonsHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowPokemonsHolder {
        val binding= ItemPokemonBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ShowPokemonsHolder(binding)
    }

    override fun onBindViewHolder(holder: ShowPokemonsHolder, position: Int) {
        holder.render(pokemons[position])
    }

    override fun getItemCount(): Int=pokemons.size

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


}