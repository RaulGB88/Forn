package es.granel.forn.db;

import java.util.ArrayList;
import java.util.List;

import es.granel.forn.R;

public class ImageDB {

    public static List<Integer> initImages() {
        List<Integer> listImage = new ArrayList<>();

        listImage.add(R.drawable.cake);
        listImage.add(R.drawable.croissant);
        listImage.add(R.drawable.pan);
        listImage.add(R.drawable.muffin);
        listImage.add(R.drawable.pepito_pan);

        return listImage;
    }

}
