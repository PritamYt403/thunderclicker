
package com.thunderclicker.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Text(
                    "THUNDERAUTOCLICKER",
                    style = MaterialTheme.typography.headlineSmall
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Image Target Sequence")

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        imagePicker.launch("image/*")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Select Target Image")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text("Selected Targets: ${targets.size}")

                Spacer(modifier = Modifier.height(12.dp))

                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(targets.toList()) { image ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp)
                            ) {
                                Text("Image Target")

                                Spacer(
                                    modifier = Modifier.height(4.dp)
                                )

                                Text(
                                    image,
                                    style = MaterialTheme.typography.bodySmall
                                )

                                TextButton(
                                    onClick = {
                                        targets.remove(image)
                                    }
                                ) {
                                    Text("Remove")
                                }
                            }
                        }
                    }
                }

                Button(
                    onClick = {
                        targets.clear()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Clear Targets")
                }
            }
        }
    }
}
