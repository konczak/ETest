package pl.konczak.etest.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.bo.IClosedQuestionBO;
import pl.konczak.etest.entity.CategoryOfQuestionEntity;
import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.repository.ICategoryOfQuestionRepository;
import pl.konczak.etest.repository.IClosedQuestionRepository;
import pl.konczak.etest.repository.IUserRepository;

@Service
public class ClosedQuestionBO
        implements IClosedQuestionBO {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private ICategoryOfQuestionRepository categoryOfQuestionRepository;
    @Autowired
    private IClosedQuestionRepository closedQuestionRepository;

    @Transactional
    @Override
    public ClosedQuestionEntity add(String question, Integer authorId) {
        UserEntity author = userRepository.getById(authorId);
        ClosedQuestionEntity closedQuestionEntity = new ClosedQuestionEntity(question, author);

        closedQuestionRepository.save(closedQuestionEntity);

        return closedQuestionEntity;
    }

    @Override
    public ClosedQuestionEntity addPicture(Integer closedQuestionId, byte[] image) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Integer closedQuestionId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    @Override
    public ClosedQuestionEntity addCategoryOfQuestion(Integer closedQuestionId, Integer categoryOfQuestionId) {
        CategoryOfQuestionEntity categoryOfQuestionEntity = categoryOfQuestionRepository
                .getById(categoryOfQuestionId);
        ClosedQuestionEntity closedQuestionEntity = closedQuestionRepository.getById(closedQuestionId);

        closedQuestionEntity.addCategoryOfQuestion(categoryOfQuestionEntity);
        //categoryOfQuestionEntity.addClosedQuestion(closedQuestionEntity);

        closedQuestionRepository.save(closedQuestionEntity);

        return closedQuestionEntity;
    }
}
