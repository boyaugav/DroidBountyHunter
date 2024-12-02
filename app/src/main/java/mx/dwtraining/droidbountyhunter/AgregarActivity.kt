package mx.dwtraining.droidbountyhunter

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import mx.dwtraining.droidbountyhunter.data.DatabaseBountyHunter
import mx.dwtraining.droidbountyhunter.databinding.ActivityAgregarBinding
import mx.dwtraining.droidbountyhunter.models.Fugitivo


class AgregarActivity : AppCompatActivity(){
    private lateinit var binding: ActivityAgregarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.botonGuardar.setOnClickListener {
//            guardarFugitivoPresionado()
//        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    private fun guardarFugitivoPresionado(){
        val nombre = binding.nombreFugitivoTextView.text.toString()
        if (nombre.isNotEmpty()){
            val database = DatabaseBountyHunter(this)
            database.insertarFugitivo(Fugitivo(0, nombre, 0))
            setResult(0)
            finish()
        }else{
            AlertDialog.Builder(this)
                .setTitle("Alerta")
                .setMessage("Favor de capturar el nombre del fugitivo.")
                .show()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(mx.dwtraining.droidbountyhunter.R.menu.menu_agregar, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        if (item.itemId == R.id.item_guardar)
        {
            guardarFugitivoPresionado()
        }

        return super.onOptionsItemSelected(item)
    }
}