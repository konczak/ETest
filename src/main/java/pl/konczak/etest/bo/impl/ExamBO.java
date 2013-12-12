package pl.konczak.etest.bo.impl;

import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.bo.IExamBO;
import pl.konczak.etest.core.Validate;
import pl.konczak.etest.entity.ExamEntity;
import pl.konczak.etest.entity.TestTemplateEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.entity.UserGroupEntity;
import pl.konczak.etest.repository.IExamRepository;
import pl.konczak.etest.repository.ITestTemplateRepository;
import pl.konczak.etest.repository.IUserGroupRepository;
import pl.konczak.etest.repository.IUserRepository;

@Service
public class ExamBO
        implements IExamBO {

    private static final Logger LOGGER = Logger.getLogger(ExamBO.class);
    @Autowired
    private ITestTemplateRepository testTemplateRepository;
    @Autowired
    private IUserGroupRepository userGroupRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IExamRepository examRepository;

    @Transactional
    @Override
    public ExamEntity add(Integer testTemplateId, Integer userGroupId, String titleSufix, Integer examinerId,
            LocalDateTime activeFrom, LocalDateTime activeTo) {
        Validate.notNull(testTemplateId);
        Validate.notNull(userGroupId);
        Validate.notNull(examinerId);
        Validate.notNull(activeFrom);
        Validate.notNull(activeTo);
        Validate.isTrue(activeFrom.isBefore(activeTo));

        TestTemplateEntity testTemplateEntity = testTemplateRepository.getById(testTemplateId);
        UserGroupEntity userGroupEntity = userGroupRepository.getById(userGroupId);
        UserEntity userEntity = userRepository.getById(examinerId);

        ExamEntity examEntity = new ExamEntity(testTemplateEntity, userGroupEntity, titleSufix,
                userEntity, activeFrom, activeTo);

        examRepository.save(examEntity);

        LOGGER.info(String.format("Add Exam <%s>", examEntity.getId()));

        return examEntity;
    }
}
