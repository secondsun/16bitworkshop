package net.saga.a16_bitstudio.util


import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.CallSuper
import androidx.annotation.RequiresApi

@RequiresApi(21)
open class OpenDocumentTreeWithPersistPermission : ActivityResultContracts.OpenDocumentTree() {
    @CallSuper
    override fun createIntent(context: Context, input: Uri?): Intent {
        val intent = super.createIntent(context, input)
        intent.addFlags(
            Intent.FLAG_GRANT_READ_URI_PERMISSION
                    or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    or Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION
                    or Intent.FLAG_GRANT_PREFIX_URI_PERMISSION
        )
        return intent
    }

}