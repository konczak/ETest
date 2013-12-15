package pl.konczak.etest.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import pl.konczak.etest.core.Validate;

@Entity
@Table(name = "exams")
public class ExamEntity
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private TestTemplateEntity testTemplate;
    private UserGroupEntity userGroup;
    private String titleSuffix;
    private UserEntity examiner;
    private LocalDateTime createdAt;
    private LocalDateTime activeFrom;
    private LocalDateTime activeTo;
    private Set<UserExamEntity> generatedExams = new HashSet<UserExamEntity>();

    public ExamEntity() {
    }

    public ExamEntity(TestTemplateEntity testTemplate, UserGroupEntity userGroup, String titleSuffix,
            UserEntity examiner, LocalDateTime activeFrom, LocalDateTime activeTo) {
        Validate.notNull(testTemplate);
        Validate.notNull(userGroup);
        Validate.notNull(examiner);
        Validate.notNull(activeFrom);
        Validate.notNull(activeTo);
        this.testTemplate = testTemplate;
        this.userGroup = userGroup;
        this.titleSuffix = titleSuffix;
        this.examiner = examiner;
        this.createdAt = LocalDateTime.now();
        this.activeFrom = activeFrom;
        this.activeTo = activeTo;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "examsId",
            unique = true,
            nullable = false,
            updatable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "testTemplatesId",
                nullable = false)
    public TestTemplateEntity getTestTemplate() {
        return testTemplate;
    }

    public void setTestTemplate(TestTemplateEntity testTemplate) {
        this.testTemplate = testTemplate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userGroupsId",
                nullable = false)
    public UserGroupEntity getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroupEntity userGroup) {
        this.userGroup = userGroup;
    }

    @Column(length = 25)
    public String getTitleSuffix() {
        return titleSuffix;
    }

    public void setTitleSuffix(String titleSuffix) {
        this.titleSuffix = titleSuffix;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usersId",
                nullable = false)
    public UserEntity getExaminer() {
        return examiner;
    }

    public void setExaminer(UserEntity examiner) {
        this.examiner = examiner;
    }

    @Column(nullable = false,
            updatable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    public LocalDateTime getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(LocalDateTime activeFrom) {
        this.activeFrom = activeFrom;
    }

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    public LocalDateTime getActiveTo() {
        return activeTo;
    }

    public void setActiveTo(LocalDateTime activeTo) {
        this.activeTo = activeTo;
    }

    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "exam")
    @Cascade(CascadeType.ALL)
    public Set<UserExamEntity> getGeneratedExams() {
        return generatedExams;
    }

    public void addUserExam(UserEntity examined, Map<ClosedQuestionEntity, Set<ClosedAnswerEntity>> mapOfClosedQuestionWithAnswers) {
        UserExamEntity userExamEntity = new UserExamEntity(this, examined);
        for (Map.Entry<ClosedQuestionEntity, Set<ClosedAnswerEntity>> entry : mapOfClosedQuestionWithAnswers.entrySet()) {
            userExamEntity.addClosedQuestion(entry.getKey(), entry.getValue());
        }
        this.generatedExams.add(userExamEntity);
    }

    public void setGeneratedExams(Set<UserExamEntity> generatedExams) {
        this.generatedExams = generatedExams;
    }
}
