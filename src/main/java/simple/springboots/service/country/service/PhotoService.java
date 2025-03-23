package simple.springboots.service.country.service;

import simple.springboots.service.country.domain.Photo;

import java.util.List;

public interface PhotoService {
    List<Photo> allPhotos();
    Photo photoByDescription();
}
