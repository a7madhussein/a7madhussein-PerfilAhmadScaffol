package com.example.perfilahmadscaffol

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.White
            ) {
                val context = LocalContext.current

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        SmallTopAppBar(
                            title = { Text("Perfil de Ahmad") },
                            actions = {
                                IconButton(onClick = {
                                    // Intent para enviar un correo
                                    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                                        data = Uri.parse("mailto:ahmad@example.com")
                                        putExtra(Intent.EXTRA_SUBJECT, "Contacto desde App")
                                    }
                                    context.startActivity(emailIntent)
                                }) {
                                    Icon(imageVector = Icons.Default.Email, contentDescription = "Email")
                                }

                                IconButton(onClick = {
                                    // Intent para compartir perfil
                                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                        type = "text/plain"
                                        putExtra(Intent.EXTRA_TEXT, "¡Mira mi perfil! https://miwebpersonal.com")
                                    }
                                    context.startActivity(Intent.createChooser(shareIntent, "Compartir perfil"))
                                }) {
                                    Icon(imageVector = Icons.Default.Share, contentDescription = "Compartir")
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    ProfileScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        item {
            Spacer(modifier = Modifier.height(60.dp))
            Image(
                painter = painterResource(id = R.drawable.mofoto),
                contentDescription = "Foto de Ahmad",
                modifier = Modifier
                    .size(110.dp)
                    .clip(CircleShape)
                    .background(Color.White, CircleShape)
                    .padding(0.dp)
                    .background(Color.LightGray, CircleShape),
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Ahmad Hussein",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Estudiante de desarrollo de aplicaciones multiplataforma",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(35.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                HobbyIcon(icon = R.drawable.musica, description = "Tocar instrumentos")
                HobbyIcon(icon = R.drawable.viajar, description = "Viajar")
                HobbyIcon(icon = R.drawable.desarrollo, description = "Desarrollo de apps")
            }
        }

        item {
            Spacer(modifier = Modifier.height(290.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                SocialIcon(
                    icon = R.drawable.instagram,
                    description = "Instagram",
                    url = "https://instagram.com/ahmad"
                )
                SocialIcon(
                    icon = R.drawable.github,
                    description = "Github",
                    url = "https://github.com/ahmad"
                )
                SocialIcon(
                    icon = R.drawable.whatsapp,
                    description = "WhatsApp",
                    url = "https://wa.me/123456789"
                )
            }
        }
    }

}

@Composable
fun HobbyIcon(icon: Int, description: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = description,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = description)
    }
}

@Composable
fun SocialIcon(icon: Int, description: String, url: String) {
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            }
            context.startActivity(intent)
        }
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = description,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = description)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        ProfileScreen()
    }
}