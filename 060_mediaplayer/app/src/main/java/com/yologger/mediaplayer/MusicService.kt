package com.yologger.mediaplayer

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.lang.Exception

class MusicService : Service(), MediaPlayer.OnPreparedListener {

    private val binder = MusicBinder()
    private var player: MediaPlayer? = null

    val url = "https://sites.google.com/site/ubiaccessmobile/sample_audio.amr"

    inner class MusicBinder: Binder() {
        fun getService(): MusicService = this@MusicService
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent): IBinder = binder

    fun startMusic() {
        if (player == null) {
            try {
                player = MediaPlayer()
                player?.apply {
                    isLooping = true
                    setDataSource(url)
                    setOnPreparedListener(this@MusicService)
                    prepareAsync()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            if (!player!!.isPlaying) player!!.start()
        }
    }

    override fun onPrepared(p0: MediaPlayer?) {
        player?.start()
    }

    fun pauseMusic() {
        if (player != null && player!!.isPlaying) player!!.pause()
    }

    fun rewind() {
        player?.run {
            val currentPosition = currentPosition
            seekTo(currentPosition-(3*1000) )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.stop()
        player?.release()
        player = null
    }
}