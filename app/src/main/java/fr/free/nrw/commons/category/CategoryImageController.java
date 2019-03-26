package fr.free.nrw.commons.category;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import fr.free.nrw.commons.Media;
import fr.free.nrw.commons.mwapi.MediaWikiApi;
import timber.log.Timber;

@Singleton
public class CategoryImageController {

    private MediaWikiApi mediaWikiApi;

    @Inject
    public CategoryImageController(MediaWikiApi mediaWikiApi) {
        this.mediaWikiApi = mediaWikiApi;
    }

    /**
     * Takes a category name as input and calls the API to get a list of images for that category
     * @param categoryName
     * @return
     */
    public List<Media> getCategoryImages(String categoryName) {
        List<Media> after_filter = new ArrayList<>();
        List<Media> before_filter = mediaWikiApi.getCategoryImages(categoryName);

        if(categoryName.equals("Category:Uploaded_with_Mobile/Android")){
            for(int i=0 ; i<before_filter.size() ; i++){
                Media temp = before_filter.get(i);
                if(!temp.getRequestedDeletion()){
                    after_filter.add(temp);
                }
            }
            return after_filter;
        }
        return before_filter;
    }
}
