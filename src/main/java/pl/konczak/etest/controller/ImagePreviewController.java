package pl.konczak.etest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.konczak.etest.entity.ImageEntity;
import pl.konczak.etest.repository.IImageRepository;

@Controller
@RequestMapping("image/{id}")
public class ImagePreviewController {

    @Autowired
    private IImageRepository imageRepository;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public byte[] getById(@PathVariable("id") Integer id) {
        ImageEntity imageEntity = imageRepository.getById(id);
        return imageEntity.getImage();
    }
}
