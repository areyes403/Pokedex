package mx.edu.itm.link.pokedex.ViewModels

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import mx.edu.itm.link.pokedex.database.Pokemon
import mx.edu.itm.link.pokedex.databinding.ItemPokemonBinding
import mx.edu.itm.link.pokedex.interfaces.PokemonListCallback

class ShowPokemonsAdapter(private val pokemons:List<Pokemon>,private val callBack:PokemonListCallback, private val context:Context): RecyclerView.Adapter<ShowPokemonsAdapter.ShowPokemonsHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowPokemonsAdapter.ShowPokemonsHolder {
        val binding= ItemPokemonBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ShowPokemonsHolder(binding,callBack,context)
    }

    override fun onBindViewHolder(holder: ShowPokemonsAdapter.ShowPokemonsHolder, position: Int) {
        holder.render(pokemons[position])
    }

    override fun getItemCount(): Int=pokemons.size

    class ShowPokemonsHolder(val binding:ItemPokemonBinding,val callBack:PokemonListCallback,val context: Context):RecyclerView.ViewHolder(binding.root){
        val url="https://pbs.twimg.com/profile_images/1178942318981701634/d5qM22Ft_400x400.jpg"
        fun render(pokemon:Pokemon){
            binding.txtName.setText(pokemon.namepokemon)
            binding.txtAtaque.setText(pokemon.attack)
            binding.txtDefensa.setText(pokemon.defense)
            binding.imgImagenPokemon
            Glide.with(context).load(url).into(binding.imgImagenPokemon)
            binding.root.setOnClickListener {
                callBack.onClick(pokemon)
            }
            binding.btnEliminarPokemonItem.setOnClickListener {
                callBack.delete(pokemon)
            }

        }
    }


}