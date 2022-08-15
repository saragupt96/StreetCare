package org.brightmindenrichment.street_care.YouTube

import android.util.Log
import kotlinx.coroutines.*


//https://www.googleapis.com/youtube/v3/playlistItems?key=AIzaSyAV5713sUQ-j8KxDjuPGtyVq1aQY1iJkuY&part=id,contentDetails,snippet&playlistId=PLh7GZtyt8qiLKwO_WoE0Vmcu6UMV1AtV9


class YouTubeController {

    var playlist: Playlist? = null



    fun refresh(playlistId: String, errorHandler: CoroutineExceptionHandler, completion: () -> Unit) {

        val mainActivityJob = Job()
        val coroutineScope = CoroutineScope(mainActivityJob + Dispatchers.Main)

        coroutineScope.launch(errorHandler) {
            playlist = YouTubeDataAdapter().getPlaylist("PLh7GZtyt8qiLKwO_WoE0Vmcu6UMV1AtV9")

            completion()
        }
    }


} // end class