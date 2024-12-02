package mx.dwtraining.droidbountyhunter.network

import android.content.Context
import android.widget.Toast

object NetworkHelper {
    const val ERR_NAME_NOT_RESOLVED = 105
    fun manageError(context: Context, code: Int, message: String) {
        Toast.makeText(context, "Error: $code, \nMensaje: $message",
            Toast.LENGTH_LONG).show()
    }
}