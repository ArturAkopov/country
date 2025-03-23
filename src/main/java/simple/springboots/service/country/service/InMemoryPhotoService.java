package simple.springboots.service.country.service;

import org.springframework.stereotype.Component;
import simple.springboots.service.country.domain.Photo;

import java.util.Date;
import java.util.List;

@Component
public class InMemoryPhotoService implements PhotoService {
    @Override
    public List<Photo> allPhotos() {
        return List.of(
                new Photo("Фото 1",new Date(),""),
                new Photo("Фото 2",new Date(),"")
        );
    }

    @Override
    public Photo photoByDescription() {
        return null;
    }
}
