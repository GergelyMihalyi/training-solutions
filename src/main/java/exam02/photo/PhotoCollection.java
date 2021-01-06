package exam02.photo;

import java.util.ArrayList;
import java.util.List;

public class PhotoCollection {
    private List<Photo> photos = new ArrayList<>();

    public List<Photo> getPhotos() {
        return photos;
    }

    public void addPhoto(String... names) {
        for (String name : names) {
            photos.add(new Photo(name));
        }
    }

    public void starPhoto(String name, Quality quality) {
        boolean searchSucces = false;
        for (Photo photo : photos) {
            if (photo.getName().equals(name)) {
                searchSucces = true;
                photo.setQuality(quality);
            }
        }
        if (!searchSucces) {
            throw new PhotoNotFoundException("There is no photo with this name");
        }
    }

    public int numberOfStars() {
        int numberOfStar = 0;
        for (Photo photo : photos) {
            numberOfStar += photo.getQuality().getValue();
        }
        return numberOfStar;
    }

}
