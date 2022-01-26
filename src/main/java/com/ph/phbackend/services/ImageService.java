package com.ph.phbackend.services;

import com.ph.phbackend.models.Images;
import com.ph.phbackend.models.NursingMeasure;
import com.ph.phbackend.payload.request.ImageRequest;
import com.ph.phbackend.repository.ImageRepository;
import com.ph.phbackend.repository.NursingMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Set;

@Service
public class ImageService {
    private static final String UPLOAD_FOLDER = "/home/phBackend/images";
    //private static final String UPLOAD_FOLDER_WIN = "C:\\Users\\Klaus\\Desktop\\images\\";

    private ImageRepository imageRepository;
    private NursingMeasureRepository nursingMeasureRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository, NursingMeasureRepository nursingMeasureRepository) {
        this.imageRepository = imageRepository;
        this.nursingMeasureRepository = nursingMeasureRepository;
    }

    @Transactional
    public String saveImage(ImageRequest imageRequest) throws IOException {
        String nursingMeasureTitle = imageRequest.getTitle();
        MultipartFile file = imageRequest.getFile();
        Optional<NursingMeasure> measure = nursingMeasureRepository.findNursingMeasureByCareRecommendationTitle(nursingMeasureTitle);
        Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
        if (measure.isPresent()) {
            Images image = new Images(file.getOriginalFilename(), path.toString());
            imageRepository.save(image);
            Set<Images> imagesSet = measure.get().getImages();
            imagesSet.add(image);
            measure.get().setImages(imagesSet);
            nursingMeasureRepository.save(measure.get());
        } else {
            return null;
        }

        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Files.write(path, bytes);
            return file.getOriginalFilename();
        } else {
            return null;
        }
    }
    @Transactional
    public InputStreamResource getImages(String filename) {
        try {
            FileInputStream is = new FileInputStream( UPLOAD_FOLDER + filename );

            //final InputStream in = ImageService.class.getResourceAsStream(UPLOAD_FOLDER_WIN + filename);
            return new InputStreamResource(is);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}