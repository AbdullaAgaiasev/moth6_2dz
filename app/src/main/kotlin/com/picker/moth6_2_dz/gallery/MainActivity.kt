package com.picker.moth6_2_dz.gallery

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import by.kirich1409.viewbindingdelegate.viewBinding
import com.picker.moth6_2_dz.selected.SelectedImagesActivity
import com.picker.moth6_2_dz.utils.ImagesGallery
import com.picker.moth6_2_dz.R
import com.picker.moth6_2_dz.databinding.ActivityMainBinding
import com.picker.moth6_2_dz.models.ImageModel

class MainActivity : AppCompatActivity(R.layout.activity_main), ImageAdapter.OnItemClick {

    private val binding by viewBinding(ActivityMainBinding::bind)

    private var imageAdapter = ImageAdapter()
    private var images = arrayListOf<ImageModel>()
    private var selectedImages: ArrayList<ImageModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                90
            )
        } else {
            loadImages()
        }
    }

    private fun loadImages() {
        images = ImagesGallery.listOfImages(this)
        imageAdapter.setImageList(images)
        imageAdapter.setListener(this)
        binding.recycler.adapter = imageAdapter
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 90 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            loadImages()
        } else {
            finish()
        }
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onClicked(image: ImageModel, position: Int) {
        (!image.isSelected).also { image.isSelected = it }
        images[position] = image
        imageAdapter.setImageList(images)
        selectedImages.clear()
        selectedImages.addAll(images.filter { it.isSelected })
        binding.tvCounter.text = "${selectedImages.size} photos selected"
            binding.btnGet.setOnClickListener {
            sendSelectedImages()
        }
    }


    private fun sendSelectedImages() {
        Intent(this@MainActivity, SelectedImagesActivity::class.java).apply {
            putExtra(IMAGE, selectedImages)
            startActivity(this)
        }
    }

    companion object {
        const val IMAGE = "image"
    }


}




