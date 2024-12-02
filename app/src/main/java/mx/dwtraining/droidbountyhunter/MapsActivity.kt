package mx.dwtraining.droidbountyhunter

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.dwtraining.droidbountyhunter.databinding.ActivityMapsBinding
import mx.dwtraining.droidbountyhunter.models.Fugitivo

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMapsBinding
    private var mMap: GoogleMap? = null
    private var fugitivo: Fugitivo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fugitivo = IntentCompat.getParcelableExtra(intent, "fugitivo"
            ,Fugitivo::class.java)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                mapFragment.getMapAsync(this@MapsActivity)
            }
        }
        title = fugitivo!!.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val position: LatLng = if(fugitivo == null || (fugitivo!!.latitude == 0.0 && fugitivo!!.longitude ==
            0.0)) {
            LatLng(-34.0, 151.0)
        } else {
            LatLng(fugitivo!!.latitude, fugitivo!!.longitude)
        }
        mMap?.apply {
            addMarker(MarkerOptions().position(position).title(fugitivo?.name ?: "Ubicacion desconocida"))
            moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15f))
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}