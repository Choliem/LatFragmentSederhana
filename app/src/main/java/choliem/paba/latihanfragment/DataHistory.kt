package choliem.paba.latihanfragment

data class Riwayat(
    val teks: String,
    val hasil: Double
)

object DataHistory {
    val tambah = mutableListOf<Riwayat>()
    val kurang = mutableListOf<Riwayat>()
    val kali = mutableListOf<Riwayat>()
    val bagi = mutableListOf<Riwayat>()
}