
package com.thunderclicker.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
    var targetName by remember { mutableStateOf("") }
    var interval by remember { mutableStateOf("500") }
    var running by remember { mutableStateOf(false) }

    val targets = remember { mutableStateListOf<String>() }

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
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

                Text("Image sequence automation setup")

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = targetName,
                    onValueChange = { targetName = it },
                    label = { Text("Target name") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        if (targetName.isNotBlank()) {
                            targets.add(targetName.trim())
                            targetName = ""
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add target")
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = interval,
                    onValueChange = { interval = it },
                    label = { Text("Interval (milliseconds)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text("Target sequence")

                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    items(targets.toList()) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    item,
                                    modifier = Modifier.weight(1f)
                                )

                                TextButton(
                                    onClick = { targets.remove(item) }
                                ) {
                                    Text("Remove")
                                }
                            }
                        }
                    }
                }

                Button(
                    onClick = { running = !running },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (running) "Stop setup" else "Start setup")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "Targets: ${targets.size}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
