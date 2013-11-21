package pl.konczak.etest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "images")
public class ImageEntity {

    private Integer id;
    private byte[] image;
    private ClosedQuestionEntity closedQuestion;
    private ClosedAnswerEntity closedAnswer;

    public ImageEntity() {
    }

    public ImageEntity(byte[] image) {
        this.image = image;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imagesId",
            unique = true,
            nullable = false,
            updatable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(nullable = false,
            updatable = false)
    @Lob
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @OneToOne(fetch = FetchType.LAZY,
              mappedBy = "image")
    public ClosedQuestionEntity getClosedQuestion() {
        return closedQuestion;
    }

    public void setClosedQuestion(ClosedQuestionEntity closedQuestion) {
        this.closedQuestion = closedQuestion;
    }

    @OneToOne(fetch = FetchType.LAZY,
              mappedBy = "image")
    public ClosedAnswerEntity getClosedAnswer() {
        return closedAnswer;
    }

    public void setClosedAnswer(ClosedAnswerEntity closedAnswer) {
        this.closedAnswer = closedAnswer;
    }
}
