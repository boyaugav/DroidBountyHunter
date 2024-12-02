package mx.dwtraining.droidbountyhunter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mx.dwtraining.droidbountyhunter.databinding.FragmentAcercaDeBinding

class AcercaDeFragment : Fragment() {
    private var _binding: FragmentAcercaDeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Se hace referencia al Fragment generado por XML en los Layouts y
// se instancia en una View...
        _binding = FragmentAcercaDeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var rating = "0.0" // Variable para lectura del rating guardado en Properties
        if (System.getProperty("rating") != null) {
            rating = System.getProperty("rating")
        }
        if (
            rating.isEmpty()) {
            rating = "0.0"
        }
        binding.ratingBar.rating = rating.toFloat()
        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            System.setProperty("rating", rating.toString())
            ratingBar.rating = rating
        }
    }
}