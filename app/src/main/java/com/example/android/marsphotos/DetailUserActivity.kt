package com.example.android.marsphotos

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class DetailUserActivity : AppCompatActivity() {
    companion object {
        const val SEARCH_PREFIX = "https://www.google.com/search?q=lebih+tau+tentang+"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_users_acivity)



        val gambarDiLayout = findViewById<ImageView>(R.id.foto)
        val namaDiLayout = findViewById<TextView>(R.id.tv_name)
        val nimDiLayout = findViewById<TextView>(R.id.tv_nim)
        val alamatDiLayout = findViewById<TextView>(R.id.alamat)
        val deskripsiDiLayout = findViewById<TextView>(R.id.deskripsi)

        val gambar = intent.getStringExtra("Gambar")
        val nama = intent.getStringExtra("Nama")
        val nim = intent.getStringExtra("Nim")
        val alamat = intent.getStringExtra("Alamat")
        val deskripsi = intent.getStringExtra("Deskripsi")

        namaDiLayout.text = nama
        nimDiLayout.text = nim
        alamatDiLayout.text = alamat
        deskripsiDiLayout.text = deskripsi




//        val namaDiLayout = findViewById<TextView>(R.id.tv_nama)
//        val ilmiahDiLayout = findViewById<TextView>(R.id.tv_nama_ilmiah)
//        val deskripsiDiLayout = findViewById<TextView>(R.id.tv_item_description)
//        val filumDiLayout = findViewById<TextView>(R.id.nama_filum_teks)
//        val habitatDiLayout = findViewById<TextView>(R.id.habitat_teks)
//        val spesiesDiLayout = findViewById<TextView>(R.id.nama_spesies_teks)
//        val gambarDiLayout = findViewById<ImageView>(R.id.img_item_photo)
//        val buttonn = findViewById<Button>(R.id.button_id)

        val actionBar: ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        //get data from putExtra
//        var intent = intent
//        val nama = intent.getStringExtra("Nama")
//        val ilmiah = intent.getStringExtra("Ilmiah")
//        val gambar = intent.getIntExtra("Gambar", 0)
//        val deskripsi = intent.getStringExtra("Deskripsi")
//        val filum = intent.getStringExtra("Filum")
//        val habitat = intent.getStringExtra("Habitat")
//        val spesies = intent.getStringExtra("Spesies")

//        actionBar.setTitle(nama)
//        namaDiLayout.text = nama
//        ilmiahDiLayout.text = "($ilmiah)"
//        filumDiLayout.text = filum
//        spesiesDiLayout.text = spesies
//        habitatDiLayout.text = habitat
//
//        deskripsiDiLayout.text = deskripsi
//        gambarDiLayout.setImageResource(gambar)
//        ilmiahDiLayout.text = ilmiah

////        buttonn.setOnClickListener {
//            val queryUrl: Uri = Uri.parse("${DetailUserActivity.SEARCH_PREFIX}${nama}")
//            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
//            startActivity(intent)


        val imageUrl = gambar
        val imageView: ImageView = findViewById(R.id.foto)

        Glide.with(this)
            .asBitmap()
            .load(imageUrl)
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    // Konversi gambar menjadi string
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    resource.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
                    val byteArray = byteArrayOutputStream.toByteArray()
                    val imageString = Base64.encodeToString(byteArray, Base64.DEFAULT)

                    // Gunakan string gambar
                    // ...

                    // Set gambar ke ImageView
                    imageView.setImageBitmap(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // Gambar dihapus dari ImageView
                }
            })
        }

}