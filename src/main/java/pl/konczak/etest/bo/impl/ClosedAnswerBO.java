package pl.konczak.etest.bo.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.bo.IClosedAnswerBO;
import pl.konczak.etest.entity.ClosedAnswerEntity;
import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.entity.ImageEntity;
import pl.konczak.etest.repository.IClosedAnswerRepository;
import pl.konczak.etest.repository.IClosedQuestionRepository;
import pl.konczak.etest.repository.IImageRepository;

@Service
public class ClosedAnswerBO
        implements IClosedAnswerBO {

    private static final Logger LOGGER = Logger.getLogger(ClosedAnswerBO.class);
    @Autowired
    private IClosedQuestionRepository closedQuestionRepository;
    @Autowired
    private IClosedAnswerRepository closedAnswerRepository;
    @Autowired
    private IImageRepository imageRepository;

    @Transactional
    @Override
    public ClosedAnswerEntity add(Integer closedQuestionId, String answer, boolean correct) {
        ClosedQuestionEntity closedQuestionEntity = closedQuestionRepository.getById(closedQuestionId);

        ClosedAnswerEntity closedAnswerEntity = new ClosedAnswerEntity(closedQuestionEntity, answer, correct);

        closedQuestionEntity.addClosedAnswer(closedAnswerEntity);
        closedAnswerRepository.save(closedAnswerEntity);

        LOGGER.info(String.format("Add ClosedAnswer <%s> to ClosedQuestion <%s>",
                closedAnswerEntity.getId(), closedQuestionEntity.getId()));
        return closedAnswerEntity;
    }

    @Transactional
    @Override
    public ClosedAnswerEntity addPicture(Integer closedAnswerId, byte[] image) {
        ClosedAnswerEntity closedAnswerEntity = closedAnswerRepository.getById(closedAnswerId);

        ImageEntity imageEntity = new ImageEntity(image);

        imageRepository.save(imageEntity);

        closedAnswerEntity.setImage(imageEntity);

        closedAnswerRepository.save(closedAnswerEntity);

        LOGGER.info(String.format("Add picture <%s> to ClosedAnswer <%s>",
                imageEntity.getId(), closedAnswerEntity.getId()));

        return closedAnswerEntity;
    }

    @Transactional
    @Override
    public void remove(Integer closedAnswerId) {
        ClosedAnswerEntity closedAnswerEntity = closedAnswerRepository.getById(closedAnswerId);

        closedAnswerRepository.delete(closedAnswerEntity);

        LOGGER.info(String.format("Removed ClosedAnswer <%s>", closedAnswerEntity.getId()));
    }
}
