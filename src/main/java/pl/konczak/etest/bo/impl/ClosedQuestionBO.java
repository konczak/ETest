package pl.konczak.etest.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.bo.IClosedQuestionBO;
import pl.konczak.etest.core.Validate;
import pl.konczak.etest.entity.CategoryEntity;
import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.entity.ImageEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.repository.ICategoryRepository;
import pl.konczak.etest.repository.IClosedQuestionRepository;
import pl.konczak.etest.repository.IImageRepository;
import pl.konczak.etest.repository.IUserRepository;

@Service
public class ClosedQuestionBO
        implements IClosedQuestionBO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClosedQuestionBO.class);
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IClosedQuestionRepository closedQuestionRepository;
    @Autowired
    private IImageRepository imageRepository;
    @Autowired
    private ICategoryRepository categoryRepository;

    @Transactional
    @Override
    public ClosedQuestionEntity add(String question, Integer authorId, Integer categoryId) {
        Validate.notEmpty(question);
        Validate.notNull(authorId);
        Validate.notNull(categoryId);
        UserEntity author = userRepository.getById(authorId);
        CategoryEntity category = categoryRepository.getById(categoryId);
        ClosedQuestionEntity closedQuestionEntity = new ClosedQuestionEntity(question, author, category);

        closedQuestionRepository.save(closedQuestionEntity);

        LOGGER.info("Add ClosedQuestion <{}> <{}>, Author <{}> <{}>, into Category <{}> <{}>",
                closedQuestionEntity.getId(), closedQuestionEntity.getQuestion(),
                author.getId(), author.getEmail(),
                category.getId(), category.getName());
        return closedQuestionEntity;
    }

    @Transactional
    @Override
    public ClosedQuestionEntity addPicture(Integer closedQuestionId, byte[] image) {
        ClosedQuestionEntity closedQuestionEntity = closedQuestionRepository.getById(closedQuestionId);

        ImageEntity imageEntity = new ImageEntity(image);

        imageRepository.save(imageEntity);

        closedQuestionEntity.setImage(imageEntity);

        closedQuestionRepository.save(closedQuestionEntity);

        LOGGER.info("Add picture <{}> to ClosedQuestion <{}>",
                imageEntity.getId(), closedQuestionEntity.getId());

        return closedQuestionEntity;
    }

    @Transactional
    @Override
    public void remove(Integer closedQuestionId) {
        ClosedQuestionEntity closedQuestionEntity = closedQuestionRepository.getById(closedQuestionId);

        closedQuestionRepository.delete(closedQuestionEntity);

        LOGGER.info("Removed ClosedQuestion <{}>", closedQuestionEntity.getId());
    }
}
