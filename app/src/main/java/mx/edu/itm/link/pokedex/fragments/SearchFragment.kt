package mx.edu.itm.link.pokedex.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import mx.edu.itm.link.pokedex.ViewModels.SavePokemonsViewModel
import mx.edu.itm.link.pokedex.pokemon.domain.model.Pokemon
import mx.edu.itm.link.pokedex.databinding.FragmentSearchBinding
import org.json.JSONObject


class SearchFragment : Fragment() {

    private lateinit var queue: RequestQueue
    private lateinit var binding:FragmentSearchBinding
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private val saveViewModel:SavePokemonsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding= FragmentSearchBinding.inflate(layoutInflater)
        val view=binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        queue= Volley.newRequestQueue(activity)
        binding.btnSearch.setOnClickListener {
            var url="https://pokeapi.co/api/v2/pokemon/${binding.etxtNamePokemon.text}"
            val jsonReques= JsonObjectRequest(url, Response.Listener <JSONObject>{
                Log.d("response","$it")
                binding.txtIdPokemon.text=it.getString("id")
                binding.txtWeight.text=it.getString("weight")
                binding.txtName.text="Nombre: ${it.getString("name")}"
                binding.txtAtaque.text="Ataque: ${it.getJSONArray("stats").getJSONObject(1).getString("base_stat")}"
                binding.txtDefensa.text="Defensa: ${it.getJSONArray("stats").getJSONObject(2).getString("base_stat")}"
                binding.txtHP.text="Hp: ${it.getJSONArray("stats").getJSONObject(0).getString("base_stat")}"
                binding.txtVelocidad.text="Velocidad: ${it.getJSONArray("stats").getJSONObject(5).getString("base_stat")}"
            },
                Response.ErrorListener { Log.d("response","Error") }
            )
            queue.add(jsonReques)
            binding.etxtNamePokemon.text.clear()
            binding.btnFavorite.visibility=View.VISIBLE
            binding.cardView.visibility=View.VISIBLE
        }

        binding.btnFavorite.setOnClickListener {
            val user = Firebase.auth.currentUser
            user?.let {
                val uid = it.uid
                Log.d("uid","$uid")
                //print(uid)
                try{
                    val poke= Pokemon(
                        binding.txtIdPokemon.text.toString().toInt(),
                        uid,
                        binding.txtName.text.toString(),
                        binding.txtHP.text.toString(),
                        binding.txtAtaque.text.toString(),
                        binding.txtDefensa.text.toString(),
                        binding.txtVelocidad.text.toString(),
                        binding.txtWeight.text.toString()
                    )
                    val id=poke.idUser
                    val na=poke.namepokemon
                    Log.d("id","Este es un test:  $id $na")
                        saveViewModel.save(poke)
                        Toast.makeText(activity,"Se agrego a favoritos",Toast.LENGTH_SHORT)

                }catch (e:ExceptionInInitializerError){
                    print(e)
                }
            }

            activity?.onBackPressed()
        }

        binding.btnbackSearch.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onStop(){
        super.onStop()
        queue.cancelAll("stopped")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return super.onOptionsItemSelected(item)
    }
}