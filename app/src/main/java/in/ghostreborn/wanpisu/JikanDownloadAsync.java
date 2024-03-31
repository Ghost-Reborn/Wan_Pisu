package in.ghostreborn.wanpisu;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import in.ghostreborn.wanpisu.constants.WanPisuConstants;
import in.ghostreborn.wanpisu.parser.AllAnimeParser;

public class JikanDownloadAsync extends AsyncTask<Void, Void, String> {

    Context context;
    public JikanDownloadAsync(Context context){
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... voids) {
        return AllAnimeParser.getAllAnimeServer(
                WanPisuConstants.wanPisuSharedPreference.getString(WanPisuConstants.WAN_PISU_PREFERENCE_ANIME_ID, "")
        );
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Log.e("TAG", "Url: " + s);


        String fileName =
                WanPisuConstants.wanPisuSharedPreference.getString(WanPisuConstants.WAN_PISU_PREFERENCE_ANIME_NAME, "") +
                        " " +
                        WanPisuConstants.ALL_ANIME_EPISODE_NUMBER +
                ".mp4";

        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(s));

        // Set request properties
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);

        downloadManager.enqueue(request);
    }
}
