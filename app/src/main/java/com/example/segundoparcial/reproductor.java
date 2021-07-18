package com.example.segundoparcial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class reproductor extends AppCompatActivity {
    Button reproduce, stop, pause;
    Boolean visto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);
        reproduce = (Button) findViewById(R.id.reproducir);
        stop = (Button) findViewById(R.id.stop);
        pause = (Button) findViewById(R.id.pause);
        Uri myUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video);
        VideoView videoView = (VideoView) findViewById(R.id.video2);
        videoView.setVideoURI(myUri);
        videoView.setMediaController(new MediaController(this));

        videoView.requestFocus();

        AlertDialog.Builder alert = new AlertDialog.Builder(reproductor.this);
        alert.setTitle("ATENCIÓN");
        alert.setMessage("Para poder reproducir es necesario que se agregue una foto de perfil");
        alert.setPositiveButton(R.string.dialog_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Se tomará una foto",Toast.LENGTH_SHORT).show();
                TomarFoto();
                reproduce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        videoView.start();
                    }
                });
                stop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        videoView.stopPlayback();
                        videoView.seekTo(0);
                    }
                });
                pause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        videoView.pause();
                    }
                });
            }
        });
        alert.setNegativeButton(R.string.dialog_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Volveremos al menu...",Toast.LENGTH_SHORT).show();
                visto = true;
                Intent i= new Intent(getApplicationContext(),Menu.class);
                startActivity(i);
            }
        });

        AlertDialog dialog = alert.create();
        dialog.show();

    }

    private  void  TomarFoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }
}


