package pl.konczak.etest.repository;

import pl.konczak.etest.entity.ImageEntity;

public interface IImageRepository {

    ImageEntity getById(Integer id);

    void save(ImageEntity image);
}
