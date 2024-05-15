package mx.edu.itm.link.pokedex.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import mx.edu.itm.link.pokedex.databinding.FragmentPokemonBinding
import org.json.JSONObject


class PokemonFragment : Fragment() {

    private lateinit var queue: RequestQueue
    private lateinit var binding:FragmentPokemonBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding= FragmentPokemonBinding.inflate(layoutInflater)
        val view= binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        queue= Volley.newRequestQueue(activity)
        val id=arguments?.getString("idFragmentPokemon").toString()
        print("----------------$id")

        val urli="https://pbs.twimg.com/profile_images/1178942318981701634/d5qM22Ft_400x400.jpg"
        context?.let { Glide.with(it).load(urli).into(binding.imgPokemon) }
        var url="https://pokeapi.co/api/v2/pokemon/${id}"
        val jsonReques= JsonObjectRequest(url, Response.Listener <JSONObject>{
            Log.d("response","$it")
            binding.txtNameFragmentPokemon.text=it.getString("name")
            binding.txtHpFragmentPokemon.text=it.getJSONArray("stats").getJSONObject(0).getString("base_stat")
            binding.txtAttakFragmentPokemon.text=it.getJSONArray("stats").getJSONObject(1).getString("base_stat")
            binding.txtDefensaFragmentPokemon.text=it.getJSONArray("stats").getJSONObject(2).getString("base_stat")
            binding.txtVelocidadFragmentPokemon.text=it.getJSONArray("stats").getJSONObject(5).getString("base_stat")
            binding.txtPesoFragmentPokemon.text=it.getString("weight")
           },
            Response.ErrorListener { Log.d("response","Error") }
        )
        queue.add(jsonReques)


    }

    override fun onStop(){
        super.onStop()
        queue.cancelAll("stopped")
    }


}