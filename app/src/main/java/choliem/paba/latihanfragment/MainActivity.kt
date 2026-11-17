package choliem.paba.latihanfragment

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private lateinit var _etAngka1: EditText
    private lateinit var _etAngka2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        _etAngka1 = findViewById(R.id.etAngka1)
        _etAngka2 = findViewById(R.id.etAngka2)

        val _btnTambah = findViewById<Button>(R.id.btnTambah)
        val _btnKurang = findViewById<Button>(R.id.btnKurang)
        val _btnKali = findViewById<Button>(R.id.btnKali)
        val _btnBagi = findViewById<Button>(R.id.btnBagi)

        if (savedInstanceState == null) {
            replaceFragment(TambahFragment())
        }

        _btnTambah.setOnClickListener { hitung("+") }
        _btnKurang.setOnClickListener { hitung("-") }
        _btnKali.setOnClickListener { hitung("*") }
        _btnBagi.setOnClickListener { hitung("/") }
    }

    private fun hitung(operasi: String) {
        val angka1 = _etAngka1.text.toString().toDoubleOrNull()
        val angka2 = _etAngka2.text.toString().toDoubleOrNull()

        if (angka1 == null || angka2 == null) {
            Toast.makeText(this, "Isi kedua angka dulu", Toast.LENGTH_SHORT).show()
            return
        }
        if (operasi == "/" && angka2 == 0.0) {
            Toast.makeText(this, "Tidak bisa dibagi 0", Toast.LENGTH_SHORT).show()
            return
        }

        val hasil = when (operasi) {
            "+" -> angka1 + angka2
            "-" -> angka1 - angka2
            "*" -> angka1 * angka2
            else -> angka1 / angka2
        }

        val riwayat = Riwayat("${rapi(angka1)} $operasi ${rapi(angka2)} = ${rapi(hasil)}", hasil)

        when (operasi) {
            "+" -> {
                DataHistory.tambah.add(riwayat)
                replaceFragment(TambahFragment())
            }
            "-" -> {
                DataHistory.kurang.add(riwayat)
                replaceFragment(KurangFragment())
            }
            "*" -> {
                DataHistory.kali.add(riwayat)
                replaceFragment(KaliFragment())
            }
            else -> {
                DataHistory.bagi.add(riwayat)
                replaceFragment(BagiFragment())
            }
        }
    }

    private fun rapi(angka: Double): String {
        return if (angka == angka.toLong().toDouble()) angka.toLong().toString() else angka.toString()
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}