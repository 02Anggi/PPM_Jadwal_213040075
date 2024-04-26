package id.ac.unpas.jadwal.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.benasher44.uuid.uuid4
import id.ac.unpas.jadwal.models.Todo
import id.ac.unpas.jadwal.persistences.TodoDao
import kotlinx.coroutines.launch

@Composable
fun FormTodoScreen(todoDao: TodoDao) {

    val scope = rememberCoroutineScope()

    // State for each input field
    val nama = remember { mutableStateOf(TextFieldValue("")) }
    val nama_makanan = remember { mutableStateOf(TextFieldValue("")) }
    val jenis_makanan = remember { mutableStateOf(TextFieldValue("")) }
    val deskripsi = remember { mutableStateOf(TextFieldValue("")) }
    val tanggal = remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {

        OutlinedTextField(
            label = { Text(text = "Nama") },
            modifier = Modifier.fillMaxWidth(),
            value = nama.value, onValueChange = {
                nama.value = it
            })

        OutlinedTextField(
            label = { Text(text = "Nama Makanan") },
            modifier = Modifier.fillMaxWidth(),
            value = nama_makanan.value, onValueChange = {
                nama_makanan.value = it
            })

        OutlinedTextField(
            label = { Text(text = "Jenis Makanan") },
            modifier = Modifier.fillMaxWidth(),
            value = jenis_makanan.value, onValueChange = {
                jenis_makanan.value = it
            })

        OutlinedTextField(
            label = { Text(text = "Deskripsi") },
            modifier = Modifier.fillMaxWidth(),
            value = deskripsi.value, onValueChange = {
                deskripsi.value = it
            })

        OutlinedTextField(
            label = { Text(text = "Tanggal") },
            modifier = Modifier.fillMaxWidth(),
            value = tanggal.value, onValueChange = {
                tanggal.value = it
            })

        Row {
            Button(modifier = Modifier.weight(5f), onClick = {
                val item = Todo(
                    uuid4().toString(),
                    nama.value.text,
                    nama_makanan.value.text,
                    jenis_makanan.value.text,
                    deskripsi.value.text,
                    tanggal.value.text
                )
                scope.launch {
                    todoDao.upsert(item)
                }
                // Reset input fields after submission
                nama.value = TextFieldValue("")
                nama_makanan.value = TextFieldValue("")
                jenis_makanan.value = TextFieldValue("")
                deskripsi.value = TextFieldValue("")
                tanggal.value = TextFieldValue("")
            }) {
                Text(text = "Simpan")
            }

            Button(modifier = Modifier.weight(5f), onClick = {
                // Clear input fields
                nama.value = TextFieldValue("")
                nama_makanan.value = TextFieldValue("")
                jenis_makanan.value = TextFieldValue("")
                deskripsi.value = TextFieldValue("")
                tanggal.value = TextFieldValue("")
            }) {
                Text(text = "Batal")
            }
        }

    }

}
