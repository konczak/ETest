package pl.konczak.etest.bo.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.bo.IClosedQuestionBO;
import pl.konczak.etest.entity.CategoryOfQuestionEntity;
import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.entity.ImageEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.repository.ICategoryOfQuestionRepository;
import pl.konczak.etest.repository.IClosedQuestionRepository;
import pl.konczak.etest.repository.IImageRepository;
import pl.konczak.etest.repository.IUserRepository;

@Service
public class ClosedQuestionBO
        implements IClosedQuestionBO {

    private static final Logger LOGGER = Logger.getLogger(ClosedQuestionBO.class);
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ICategoryOfQuestionRepository categoryOfQuestionRepository;
    @Autowired
    private IClosedQuestionRepository closedQuestionRepository;
    @Autowired
    private IImageRepository imageRepository;

    @Transactional
    @Override
    public ClosedQuestionEntity add(String question, Integer authorId) {
        UserEntity author = userRepository.getById(authorId);
        ClosedQuestionEntity closedQuestionEntity = new ClosedQuestionEntity(question, author);

        closedQuestionRepository.save(closedQuestionEntity);

        LOGGER.info(String.format("Add ClosedQuestion <%s>", closedQuestionEntity.getId()));
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

        LOGGER.info(String.format("Add picture <%s> to ClosedQuestion <%s>",
                imageEntity.getId(), closedQuestionEntity.getId()));

        return closedQuestionEntity;
    }

    @Transactional
    @Override
    public void remove(Integer closedQuestionId) {
        ClosedQuestionEntity closedQuestionEntity = closedQuestionRepository.getById(closedQuestionId);

        closedQuestionRepository.delete(closedQuestionEntity);

        LOGGER.info(String.format("Removed ClosedQuestion <%s>", closedQuestionEntity.getId()));
    }

    @Transactional
    @Override
    public ClosedQuestionEntity addCategoryOfQuestion(Integer closedQuestionId,
            Integer categoryOfQuestionId) {
        CategoryOfQuestionEntity categoryOfQuestionEntity = categoryOfQuestionRepository
                .getById(categoryOfQuestionId);
        ClosedQuestionEntity closedQuestionEntity = closedQuestionRepository.getById(closedQuestionId);

        closedQuestionEntity.addCategoryOfQuestion(categoryOfQuestionEntity);

        closedQuestionRepository.save(closedQuestionEntity);

        return closedQuestionEntity;
    }
}
