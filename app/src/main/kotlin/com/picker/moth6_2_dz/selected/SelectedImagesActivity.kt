package com.picker.moth6_2_dz.selected


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.picker.moth6_2_dz.databinding.ActivitySelectedImagesBinding
import com.picker.moth6_2_dz.gallery.MainActivity.Companion.IMAGE
import com.picker.moth6_2_dz.models.ImageModel

class SelectedImagesActivity : AppCompatActivity() {

    private var selectedAdapter = SelectedAdapter()
    private lateinit var binding: ActivitySelectedImagesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectedImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getImages()
        initClick()

    }

    private fun initClick() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getImages() {
        val images = intent.getStringArrayListExtra(IMAGE) as ArrayList<ImageModel>
        selectedAdapter.setImageList(images)
        binding.selectedRecycler.adapter = selectedAdapter
    }

}
