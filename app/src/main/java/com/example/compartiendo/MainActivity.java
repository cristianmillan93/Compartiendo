package com.example.compartiendo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button botonLanzar, buttonWhatsApp, buttonFacebook, botonEmail;
    EditText editTextWeb, editTextCompartir, editText_Email;
    static String coordenadas = "38.2983583,-5.2671759,18";
    Button botonCoordenadas;
    TextView a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        botonLanzar = findViewById(R.id.button);
        editTextWeb = findViewById(R.id.editTextWeb);
        botonCoordenadas = findViewById(R.id.buttonAbrirMapa);
        buttonWhatsApp = findViewById(R.id.buttonWhatsApp);
        buttonFacebook = findViewById(R.id.buttonFacebook);
        editTextCompartir = findViewById(R.id.editTextWhatsApp);
        botonEmail = findViewById(R.id.buttonEmail);
        editText_Email = findViewById(R.id.editTextEmail);
        a = findViewById(R.id.textViewAsunto);
        botonLanzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(editTextWeb.getText().toString()));
                Intent seleccionador = Intent.createChooser(intent, "Elige tu navegador");
                startActivity(seleccionador);
            }
        });

        botonCoordenadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(Intent.ACTION_VIEW);
                intento.setData(Uri.parse(editTextWeb.getText().toString()));
                Intent seleccionador = Intent.createChooser(intento, "Abre la direccion con tu programa favorito");
                startActivity(seleccionador);
            }
        });

        buttonWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentoWhatsApp = new Intent(Intent.ACTION_SEND);
                intentoWhatsApp.setType("text/plain");
                intentoWhatsApp.putExtra(Intent.EXTRA_TEXT, editTextCompartir.getText().toString());
                intentoWhatsApp.setPackage("com.whatsapp");

                if(getPackageManager().getLaunchIntentForPackage("com.whatsapp")!=null){
                    startActivity(intentoWhatsApp);
                }else{
                    Toast.makeText(MainActivity.this, "Debes instalar WhatsApp", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentoFacebook = new Intent(Intent.ACTION_SEND);
                intentoFacebook.setType("text/plain");
                intentoFacebook.putExtra(Intent.EXTRA_TEXT, editTextCompartir.getText().toString());
                intentoFacebook.setPackage("com.facebook.katana");

                if(getPackageManager().getLaunchIntentForPackage("com.facebook.katana")!=null){
                    startActivity(intentoFacebook);
                }else{
                    Toast.makeText(MainActivity.this, "Debes instalar Facebook", Toast.LENGTH_LONG).show();
                }
            }
        });

        botonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentoemail = new Intent(Intent.ACTION_SEND);
                intentoemail.putExtra(Intent.EXTRA_EMAIL, new String[]{editText_Email.getText().toString()});
                intentoemail.putExtra(Intent.EXTRA_SUBJECT, a.getText().toString());
                intentoemail.putExtra(Intent.EXTRA_TEXT, editTextCompartir.getText().toString());

                startActivity(Intent.createChooser(intentoemail, "Escoge tu app favorita"));
            }
        });
    }
}
