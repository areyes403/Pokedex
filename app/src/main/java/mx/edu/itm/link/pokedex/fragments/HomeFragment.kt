package mx.edu.itm.link.pokedex.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import mx.edu.itm.link.pokedex.R
import mx.edu.itm.link.pokedex.databinding.FragmentHomeBinding
import org.json.JSONObject


class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding= FragmentHomeBinding.inflate(layoutInflater)
        val view=binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController=Navigation.findNavController(view)

        //usuario
        val user = Firebase.auth.currentUser
        if (user != null) {
            //val user = Firebase.auth.currentUser
            database= FirebaseDatabase.getInstance()
            dbReference=database.reference.child("users")
            dbReference.child(user!!.uid).get().addOnSuccessListener {
                Log.i("firebase", "Got value ${it.value}")
                val json=JSONObject(it.value.toString())
                //json.getString("name")
                Log.i("firebase", "Desde el name ${json.getString("name")}")
                binding.txtName.text=json.getString("name")
                binding.txtUser.text=json.getString("email")
                //binding.txtLevel.text=json.getString("level")
                binding.txtLvl.text=json.getString("level")
                lvl(json.getString("level"))
            }.addOnFailureListener {

            }

        } else {
            Log.d("logged","Usuario null")
        }

        binding.btnSearchPokemon.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_searchFragment)
        }
        binding.btnviewPokemons.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_showPokemonsFragment)
        }
        binding.btnEditProfile.setOnClickListener {
            navController.navigate(R.id.action_homeFragment_to_editProfileFragment)
        }



        binding.btnLogOut.setOnClickListener {
            activity?.onBackPressed()
        }
        try {
            val bundle=Bundle()
            val num=bundle.getString("name")
            print("El numero obtenido es: $num")
        }catch (e:ExceptionInInitializerError){
            print(e)
        }

    }

    private fun lvl(nivel: String){
        val lvl=nivel
        //barra
        binding.circularProgressBar.apply {
            var float1 : Float = lvl.toFloat();
            setProgressWithAnimation(float1*10,1000)
            progressBarColorStart = Color.GREEN
            progressBarColorEnd = Color.BLUE
            progressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM
        }

    }
}