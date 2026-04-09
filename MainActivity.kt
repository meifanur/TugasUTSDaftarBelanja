package com.example.tugasutsdaftarbelanja

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var etNamaBarang: EditText
    private lateinit var btnTambah: Button
    private lateinit var btnHapus: Button
    private lateinit var tvDaftarBarang: TextView

    private val daftarBarang = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main) // ← WAJIB DI SINI

        etNamaBarang = findViewById(R.id.etNamaBarang)
        btnTambah = findViewById(R.id.btnTambah)
        btnHapus = findViewById(R.id.btnHapus)
        tvDaftarBarang = findViewById(R.id.tvDaftarBarang)

        btnTambah.setOnClickListener {
            val namaBarang = etNamaBarang.text.toString().trim()

            if (namaBarang.isEmpty()) {
                Toast.makeText(this, "Nama barang tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            daftarBarang.add(namaBarang)
            etNamaBarang.setText("")
            tampilkanDaftar()
            Toast.makeText(this, "$namaBarang ditambahkan!", Toast.LENGTH_SHORT).show()
        }

        btnHapus.setOnClickListener {
            if (daftarBarang.isEmpty()) {
                Toast.makeText(this, "Daftar sudah kosong!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            daftarBarang.clear()
            tampilkanDaftar()
            Toast.makeText(this, "Semua barang dihapus!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun tampilkanDaftar() {
        if (daftarBarang.isEmpty()) {
            tvDaftarBarang.text = "(Belum ada barang)"
        } else {
            val sb = StringBuilder()
            daftarBarang.forEachIndexed { index, barang ->
                sb.appendLine("${index + 1}. $barang")
            }
            tvDaftarBarang.text = sb.toString().trim()
        }
    }
}
