package mx.dwtraining.droidbountyhunter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import mx.dwtraining.droidbountyhunter.R
import mx.dwtraining.droidbountyhunter.models.Fugitivo

class FugitivoAdapter(private val context: Context, private val fugitivos: Array<Fugitivo>) : android.widget.BaseAdapter() {

    override fun getCount(): Int = fugitivos.size

    override fun getItem(position: Int): Fugitivo = fugitivos[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_fugitivo_list_photo, parent, false)
        val imageView = view.findViewById<ImageView>(R.id.fotoFugitivoItem)
        val textView = view.findViewById<TextView>(R.id.textFugitivoItem)
        val fechaFugitivoView = view.findViewById<TextView>(R.id.fechaFugitivoItem)

        val fugitivo = getItem(position)
        textView.text = fugitivo.name
        if (fugitivo.fechaCaptura!!.isNotEmpty())
        {
            fechaFugitivoView.text = fugitivo.fechaCaptura
        }
        else
        {
            fechaFugitivoView.text = "Sin fecha de captura"
        }
        val x = fugitivo.photo
        if (fugitivo.photo!!.isNotEmpty()){
            fugitivo!!.photo?.let {
                Glide.with(context)
                    .load(it)
                    .into(imageView)
            }
        }
        // Ejemplo: Si tienes fotos en drawables, puedes usar esto:
//        val imageResId = context.resources.getIdentifier(fugitivo.photo, "drawable", context.packageName)
//        imageView.setImageDrawable(ContextCompat.getDrawable(context, imageResId))

        return view
    }
}