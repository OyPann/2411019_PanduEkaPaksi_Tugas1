package com.example.praktikumprofilmhs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                FormProfil()
            }
        }
    }
}

@Composable
fun FormProfil() {
    var nama by rememberSaveable { mutableStateOf("") }
    var nim by rememberSaveable { mutableStateOf("") }
    var prodi by rememberSaveable { mutableStateOf("") }
    var sudahSimpan by rememberSaveable { mutableStateOf(false) }

    val valid = nama.isNotBlank() && nim.isNotBlank() && prodi.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Text("Form Profil Mahasiswa", style = MaterialTheme.typography.titleLarge)

        // Input Nama
        OutlinedTextField(
            value = nama,
            onValueChange = {
                nama = it
                sudahSimpan = false
            },
            label = { Text("Nama Lengkap") },
            modifier = Modifier.fillMaxWidth()
        )

        // Input NIM
        OutlinedTextField(
            value = nim,
            onValueChange = {
                nim = it
                sudahSimpan = false
            },
            label = { Text("NIM") },
            modifier = Modifier.fillMaxWidth()
        )

        // Input Program Studi
        OutlinedTextField(
            value = prodi,
            onValueChange = {
                prodi = it
                sudahSimpan = false
            },
            label = { Text("Program Studi") },
            modifier = Modifier.fillMaxWidth()
        )

        // Pesan Error kalau ada inpu yg kosong
        if (!valid) {
            Text(
                text = "Lengkapi semua kolom terlebih dahulu",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        // Tombol Simpan
        Button(
            onClick = { sudahSimpan = true },
            enabled = valid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan Profil")
        }

        // Tmbl reset form
        OutlinedButton(
            onClick = {
                nama = ""
                nim = ""
                prodi = ""
                sudahSimpan = false
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reset Form")
        }

        // kartu hasil profil nya
        if (sudahSimpan) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp)) {
                    Text("Profil Tersimpan", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Nama: $nama")
                    Text("NIM: $nim")
                    Text("Program Studi: $prodi")
                }
            }
        }
    }
}