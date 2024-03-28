package id.ac.unpas.jadwal.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @PrimaryKey
    val id: String,
    val nama: String,
    val nama_makanan: String,
    val jenis_makanan: String,
    val deskripsi: String,
    val tanggal: String
)

