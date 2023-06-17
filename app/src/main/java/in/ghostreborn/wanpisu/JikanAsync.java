package in.ghostreborn.wanpisu;

import static in.ghostreborn.wanpisu.ui.EpisodeSelectActivity.jikanContainer;
import static in.ghostreborn.wanpisu.ui.EpisodeSelectActivity.jikanGroupContainer;

import android.content.Context;
import android.os.AsyncTask;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import in.ghostreborn.wanpisu.adapter.JikanAdapter;
import in.ghostreborn.wanpisu.adapter.JikanGroupAdapter;
import in.ghostreborn.wanpisu.constants.WanPisuConstants;
import in.ghostreborn.wanpisu.parser.JikanParser;

public class JikanAsync extends AsyncTask<Void, Void, Void> {

    Context context;
    int page;

    public JikanAsync(Context context, int page){
        this.context = context;
        this.page = page;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        JikanParser.parseJikan(
                WanPisuConstants.JIKAN_EPISODES_URL_HEAD +
                        WanPisuConstants.wanPisuSharedPreference.getString(WanPisuConstants.WAN_PISU_PREFERENCE_ANIME_MAL_ID, "21") +
                        WanPisuConstants.JIKAN_EPISODES_URL_TAIL +
                        page

        );
        return null;
    }

    @Override
    protected void onPostExecute(Void s) {
        super.onPostExecute(s);

        LinearLayoutManager manager = new LinearLayoutManager(context);
        GridLayoutManager groupManager = new GridLayoutManager(context, 1);
        groupManager.setOrientation(GridLayoutManager.HORIZONTAL);
        JikanAdapter adapter = new JikanAdapter();
        JikanGroupAdapter groupAdapter = new JikanGroupAdapter();

        jikanContainer.setLayoutManager(manager);
        jikanGroupContainer.setLayoutManager(groupManager);
        jikanContainer.setAdapter(adapter);
        jikanGroupContainer.setAdapter(groupAdapter);
    }
}
