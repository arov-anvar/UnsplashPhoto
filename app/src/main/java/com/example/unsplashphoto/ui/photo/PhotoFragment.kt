package com.example.unsplashphoto.ui.photo

import android.annotation.SuppressLint
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.unsplashphoto.R
import com.example.unsplashphoto.loadImage
import com.example.unsplashphoto.ui.UnsplashViewModel
import kotlinx.android.synthetic.main.photo_fragment.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.ref.WeakReference


class PhotoFragment: Fragment() {

    lateinit var viewModel: UnsplashViewModel
    lateinit var urlPhoto: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.photo_fragment, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve", "SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(UnsplashViewModel::class.java)
        val photoId = arguments!!.getString("photoId")
        if (photoId != null) {
            viewModel.getPhoto(photoId).observe(this@PhotoFragment, Observer {photo ->
                urlPhoto = photo.urls.small
                fullPhotoImageView.loadImage(photo.urls.full)
                countLikesTxt.text = "${photo.likes}"
                imageScaleTxt.text = "${photo.width}x${photo.height}px"
                countDownloadsTxt.text = "${photo.downloads}"
                photoDescriptionTxt.text = photo.description
            })
        }

        downloadPhotoBtn.setOnClickListener {
            DownloadAndSaveImageTask(activity!!.applicationContext).execute(urlPhoto)
        }
    }

    inner class GlideTarget(private val fileName: String): CustomTarget<Bitmap>(){

        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            saveImage(resource)
        }

        private fun saveImage(image: Bitmap) {
            try {
                val cw =
                    ContextWrapper(requireContext())
                val filePath = Environment.getExternalStorageDirectory()
                val directory: File = cw.getDir("imageDir", Context.MODE_PRIVATE)
                val file = File(directory, "UniqueFileName" + ".jpg")
                if (!file.exists()) {
                    Log.d("path", file.toString())
                    var fos: FileOutputStream? = null
                    try {
                        fos = FileOutputStream(file)
                        image.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                        fos!!.flush()
                        fos.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                Toast.makeText(context, "Cool", Toast.LENGTH_SHORT).show()
            } catch (error: Exception) {
                Toast.makeText(context, "fuck ${error.toString()}", Toast.LENGTH_SHORT).show()
                error.printStackTrace()
            }
        }

        override fun onLoadCleared(placeholder: Drawable?) {}
    }

    inner class DownloadAndSaveImageTask(context: Context) : AsyncTask<String, Unit, Unit>() {
        private var mContext: WeakReference<Context> = WeakReference(context)

        override fun doInBackground(vararg params: String?) {
            val url = params[0]
            val requestOptions = RequestOptions().override(100)
                .downsample(DownsampleStrategy.CENTER_INSIDE)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)

            mContext.get()?.let {
                val bitmap = Glide.with(it)
                    .asBitmap()
                    .load(url)
                    .apply(requestOptions)
                    .submit()
                    .get()

                try {
                    var file = it.getDir("Images", Context.MODE_PRIVATE)
                    file = File(file, "img1999.jpg")
                    val out = FileOutputStream(file)
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 85, out)
                    out.flush()
                    out.close()
                    Log.i("Seiggailion", "Image saved.")
                } catch (e: Exception) {
                    Log.i("Seiggailion", "Failed to save image.")
                }
            }
        }
    }
}