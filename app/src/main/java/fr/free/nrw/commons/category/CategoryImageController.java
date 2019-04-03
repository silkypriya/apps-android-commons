package fr.free.nrw.commons.category;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.mwapi.OkHttpJsonApiClient;
import io.reactivex.Single;
import timber.log.Timber;
import fr.free.nrw.commons.mwapi.MediaWikiApi;


@Singleton
public class CategoryImageController {

    private OkHttpJsonApiClient okHttpJsonApiClient;
    @Inject
    MediaWikiApi mwApi;

    @Inject
    public CategoryImageController(OkHttpJsonApiClient okHttpJsonApiClient) {
        this.okHttpJsonApiClient = okHttpJsonApiClient;
    }

    /**
     * Takes a category name as input and calls the API to get a list of images for that category
     * @param categoryName
     * @return
     */
    public Single<List<Media>> getCategoryImages(String categoryName) {
  //      return okHttpJsonApiClient.getMediaList("category", categoryName);

        List<Media> after_filter = new ArrayList<>();
        List<Media> before_filter = okHttpJsonApiClient.getMediaList("category",categoryName);

        if(categoryName.equals("Category:Uploaded_with_Mobile/Android")){
            for(int i=0 ; i<before_filter.size() ; i++){
                Media temp = before_filter.get(i);
                String wikitext = "";
                try {
                    wikitext = mwApi.getwikitext(temp.getFilePageTitle());
                } catch (IOException e){
                    Timber.d("Error");
                }

                if(!wikitext.contains("{{delete")){
                    after_filter.add(temp);
                }
            }
            return after_filter;
        }
        return before_filter;
    }
    }
}
