package mx.edu.itm.link.pokedex.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import mx.edu.itm.link.pokedex.R
import mx.edu.itm.link.pokedex.databinding.FragmentEditProfileBinding
import org.json.JSONObject


class EditProfileFragment : Fragment() {

    private lateinit var binding:FragmentEditProfileBinding
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding= FragmentEditProfileBinding.inflate(layoutInflater)
        val view=binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //usuario
        val user = Firebase.auth.currentUser
        if (user != null) {
            //val user = Firebase.auth.currentUser
            database= FirebaseDatabase.getInstance()
            dbReference=database.reference.child("users")
            dbReference.child(user.uid).get().addOnSuccessListener {
                Log.i("firebase", "Got value ${it.value}")
                val json= JSONObject(it.value.toString())
                //json.getString("name")
                Log.i("firebase", "Desde el name ${json.getString("name")}")
                binding.etxtNameEdittingProfile.setText(json.getString("name"))
                binding.etxtMailEdittingProfile.setText(json.getString("email"))
                binding.etxtLastNameEdittingProfile.setText(json.getString("lastname"))
            }.addOnFailureListener {
            }
        } else {
            Log.d("logged","Usuario null")
        }

        binding.btnSendNewProfile.setOnClickListener {
            val name=binding.etxtNameEdittingProfile.text.toString()
            val lastname=binding.etxtLastNameEdittingProfile.text.toString()
            val email=binding.etxtMailEdittingProfile.text.toString()
            //val pass=binding.etxtPasswordRegistrandose.text.toString()
            //usuario
            val user = Firebase.auth.currentUser
            if (user != null) {
                //val user = Firebase.auth.currentUser
                database= FirebaseDatabase.getInstance()
                dbReference=database.reference.child("users")
                dbReference.child(user.uid).child("name").setValue(name)
                dbReference.child(user.uid).child("email").setValue(email)
                dbReference.child(user.uid).child("lastname").setValue(lastname)

            } else {
                Log.d("logged","Usuario null")
            }

            activity?.onBackPressed()
        }
    }
}