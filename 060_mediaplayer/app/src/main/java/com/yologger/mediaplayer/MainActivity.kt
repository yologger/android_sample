package com.yologger.mediaplayer

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var buttonPlay: Button
    private lateinit var buttonPause: Button
    private lateinit var buttonRewind: Button

    private var isBound = false
    private lateinit var musicService: MusicService

    private val connection = object: ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            val musicBinder = binder as MusicService.MusicBinder
            musicService = musicBinder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonPlay = findViewById(R.id.button_play)
        buttonPause = findViewById(R.id.button_pause)
        buttonRewind = findViewById(R.id.button_rewind)

        val intent = Intent(this@MainActivity, MusicService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)

        buttonPlay.setOnClickListener {
            startAudio()
        }

        buttonPause.setOnClickListener {
            stopAudio()
        }

        buttonRewind.setOnClickListener {
            rewindAudio()
        }
    }



    private fun startAudio() {
        musicService.startMusic()
    }

    private fun stopAudio() {
        musicService.pauseMusic()
    }

    private fun rewindAudio() {
        musicService.rewind()
    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
        isBound = false
    }
}