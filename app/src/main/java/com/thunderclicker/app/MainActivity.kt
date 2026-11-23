
package com.thunderclicker.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThunderClickerApp()
        }
    }
}

@Composable
fun ThunderClickerApp() {
    val targets = remember { mutableStateListOf<String>() }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            targets.add(it.toString())
        }
    }

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Text(
                    "THUNDERAUTOCLICKER",
                    style = MaterialTheme.typography.headlineSmall
                )

                Text("Image Target Manager")

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        imagePicker.launch("image/*")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add Image Target")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text("Targets: ${targets.size}")

                Spacer(modifier = Modifier.height(12.dp))

                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    itemsIndexed(targets.toList()) { index, image ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp)
                            ) {
                                Text("Target ${index + 1}")

                                Spacer(
                                    modifier = Modifier.height(8.dp)
                                )

                                AsyncImage(
                                    model = image,
                                    contentDescription = "Target ${index + 1}",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(180.dp)
                                )

                                Spacer(
                                    modifier = Modifier.height(8.dp)
                                )

                                TextButton(
                                    onClick = {
                                        targets.removeAt(index)
                                    }
                                ) {
                                    Text("Remove Target")
                                }
                            }
                        }
                    }
                }

                Button(
                    onClick = { targets.clear() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Clear All Images")
                }
            }
        }
    }
}
