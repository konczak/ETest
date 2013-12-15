package pl.konczak.etest.helpme;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.bo.ICategoryOfQuestionBO;
import pl.konczak.etest.bo.IClosedAnswerBO;
import pl.konczak.etest.bo.IClosedQuestionBO;
import pl.konczak.etest.bo.IUserBO;
import pl.konczak.etest.bo.IUserGroupBO;
import pl.konczak.etest.entity.CategoryOfQuestionEntity;
import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.entity.RoleEntity;

import pl.konczak.etest.repository.ICategoryOfQuestionRepository;
import pl.konczak.etest.repository.IClosedQuestionRepository;
import pl.konczak.etest.repository.IRoleRepository;
import pl.konczak.etest.repository.IUserGroupRepository;
import pl.konczak.etest.repository.IUserRepository;

@Component
public class DatabaseDataInitializer {

    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IUserBO userBO;
    @Autowired
    private ICategoryOfQuestionRepository categoryOfQuestionRepository;
    @Autowired
    private ICategoryOfQuestionBO categoryOfQuestionBO;
    @Autowired
    private IClosedQuestionRepository closedQuestionRepository;
    @Autowired
    private IClosedQuestionBO closedQuestionBO;
    @Autowired
    private IClosedAnswerBO closedAnswerBO;
    @Autowired
    private IUserGroupRepository userGroupRepository;
    @Autowired
    private IUserGroupBO userGroupBO;

    @Transactional
    @PostConstruct
    public void addDatabaseDate() {
        if (roleRepository.findAll().isEmpty()) {
            prepareRoles();
        }
        if (userGroupRepository.findAll().isEmpty()) {
            prepareUserGroups();
        }
        if (userRepository.findByEmail("konczak.piotrek@gmail.com") == null) {
            prepareUsers();
        }
        if (categoryOfQuestionRepository.findAll().isEmpty()) {
            prepareCategoriesOfQuestion();
        }
        if (closedQuestionRepository.findAll().isEmpty()) {
            prepareClosedQuestions();
        }
    }

    private void prepareRoles() {
        addRole("ADMIN");
        addRole("TEACHER");
        addRole("STUDENT");
    }

    private void addRole(String name) {
        RoleEntity role = new RoleEntity(name);
        roleRepository.save(role);
    }

    private void prepareUserGroups() {
        userGroupBO.add("Klasa I A 2013-14");
        userGroupBO.add("Klasa I B 2013-14");
        userGroupBO.add("Klasa II A 2013-14");
        userGroupBO.add("Klasa III A 2013-14");
        userGroupBO.add("Szostkowi");
        userGroupBO.add("Madrale");
        userGroupBO.add("Admini");
        userGroupBO.add("Nauczyciele");
        userGroupBO.add("Klasa III C 2002-03");
    }

    private void prepareUsers() {
        userBO.register(
                "konczak.piotrek@gmail.com", "haslo", "Piotr", "Konczak");
        userBO.register(
                "bodziogodzio@gmail.com", "haslo", "Boguslaw", "Godziatkowski");
        userBO.register(
                "paw.ale@gmail.com", "haslo", "Pawel", "Aleksandrowicz");
        userBO.register(
                "prz.grz@gmail.com", "haslo", "Przemek", "Grzybowski");
        userBO.register(
                "aga.boc@gmail.com", "haslo", "Agata", "Bociek");
        userBO.register(
                "luk.bor@gmail.com", "haslo", "Lukasz", "Boronczyk");
        userBO.register(
                "dar.smi@gmail.com", "haslo", "Darek", "Smigielski");

        userGroupBO.addUserAsMember(1, 7);
        userGroupBO.addUserAsMember(2, 7);
        userGroupBO.addUserAsMember(2, 8);
        userGroupBO.addUserAsMember(1, 9);
        userGroupBO.addUserAsMember(3, 9);
        userGroupBO.addUserAsMember(4, 9);
        userGroupBO.addUserAsMember(5, 9);
        userGroupBO.addUserAsMember(6, 9);
        userGroupBO.addUserAsMember(7, 9);
        
    }

    private void prepareCategoriesOfQuestion() {
        categoryOfQuestionBO.add("Fizyka");
        categoryOfQuestionBO.add("Matematyka");
        categoryOfQuestionBO.add("Optyka");
        categoryOfQuestionBO.add("Magnetyzm");
        categoryOfQuestionBO.add("Siły");
        categoryOfQuestionBO.add("Grawitacja");
        categoryOfQuestionBO.add("Opór");
        categoryOfQuestionBO.add("Pęd");
        categoryOfQuestionBO.add("Moc");
        categoryOfQuestionBO.add("Prąd");
        categoryOfQuestionBO.add("Technika");
        categoryOfQuestionBO.add("Przyciąganie");
        categoryOfQuestionBO.add("Ciśnienie");
    }

    private void prepareClosedQuestions() {
        CategoryOfQuestionEntity fizyka =
                categoryOfQuestionRepository.findByTitle("Fizyka");
        CategoryOfQuestionEntity grawitacja =
                categoryOfQuestionRepository.findByTitle("Grawitacja");
        ClosedQuestionEntity entity;

        entity = closedQuestionBO.add("I zasada dynamiki Newtona brzmi:", 2);
        closedQuestionBO.addCategoryOfQuestion(entity.getId(), fizyka.getId());
        closedAnswerBO.add(entity.getId(), "Jesli cialo A dziala na cialo B z sila, to cialo B dziala na cialo A z sila o takiej samej wartosci i kierunku, lecz o przeciwnym zwrocie.", false);
        closedAnswerBO.add(entity.getId(), "Kazde cialo trwa w swym stanie spoczynku lub ruchu prostoliniowego jednostajnego, jezeli sily przylozone nie zmusza ciala do zmiany tego stanu.", true);
        closedAnswerBO.add(entity.getId(), "Zmiana ruchu jest proporcjonalna do przylozonej sily poruszajacej i odbywa sie w kierunku prostej, wzdluz ktorej sila jest przylozona.", false);
        closedAnswerBO.add(entity.getId(), "Zadna nie jest poprawna", false);

        entity = closedQuestionBO.add("II zasada dynamiki Newtona brzmi:", 2);
        closedQuestionBO.addCategoryOfQuestion(entity.getId(), fizyka.getId());
        closedAnswerBO.add(entity.getId(), "Jesli cialo A dziala na cialo B z sila, to cialo B dziala na cialo A z sila o takiej samej wartosci i kierunku, lecz o przeciwnym zwrocie.", false);
        closedAnswerBO.add(entity.getId(), "Kazde cialo trwa w swym stanie spoczynku lub ruchu prostoliniowego jednostajnego, jezeli sily przylozone nie zmusza ciala do zmiany tego stanu.", false);
        closedAnswerBO.add(entity.getId(), "Zmiana ruchu jest proporcjonalna do przylozonej sily poruszajacej i odbywa sie w kierunku prostej, wzdluz ktorej sila jest przylozona.", true);
        closedAnswerBO.add(entity.getId(), "Zadna nie jest poprawna", false);

        entity = closedQuestionBO.add("III zasada dynamiki Newtona brzmi:", 2);
        closedQuestionBO.addCategoryOfQuestion(entity.getId(), fizyka.getId());
        closedAnswerBO.add(entity.getId(), "Jesli cialo A dziala na cialo B z sila, to cialo B dziala na cialo A z sila o takiej samej wartosci i kierunku, lecz o przeciwnym zwrocie.", true);
        closedAnswerBO.add(entity.getId(), "Kazde cialo trwa w swym stanie spoczynku lub ruchu prostoliniowego jednostajnego, jezeli sily przylozone nie zmusza ciala do zmiany tego stanu.", false);
        closedAnswerBO.add(entity.getId(), "Zmiana ruchu jest proporcjonalna do przylozonej sily poruszajacej i odbywa sie w kierunku prostej, wzdluz ktorej sila jest przylozona.", false);
        closedAnswerBO.add(entity.getId(), "Zadna nie jest poprawna", false);

        entity = closedQuestionBO.add("Ile wynosi stala G przyciagania ziemskiego:", 2);
        closedQuestionBO.addCategoryOfQuestion(entity.getId(), fizyka.getId());
        closedQuestionBO.addCategoryOfQuestion(entity.getId(), grawitacja.getId());
        closedAnswerBO.add(entity.getId(), "ok 9.81 m/s<sup>2</sup>", true);
        closedAnswerBO.add(entity.getId(), "ok 9.91 m/s<sup>2</sup>", false);
        closedAnswerBO.add(entity.getId(), "ok 9.81 cm/s<sup>2</sup>", false);
        closedAnswerBO.add(entity.getId(), "Zadna nie jest poprawna", false);
        closedAnswerBO.add(entity.getId(), "Wszystkie odpowiedzi sa poprawne", false);
        
        entity = closedQuestionBO.add("Ile wynosi stala Plancka:", 2);
        closedQuestionBO.addCategoryOfQuestion(entity.getId(), fizyka.getId());
        closedAnswerBO.add(entity.getId(), "ok 6,626 069 57(29)*10<sup>–34</sup> Js", true);
        closedAnswerBO.add(entity.getId(), "ok 2,626 069 57(29)*10<sup>–34</sup> Js", false);
        closedAnswerBO.add(entity.getId(), "ok 3,626 069 57(29)*10<sup>–34</sup> Js", false);
        closedAnswerBO.add(entity.getId(), "ok 6,626 069 57(29)*10<sup>–34</sup> Js", false);
        closedAnswerBO.add(entity.getId(), "Zadna nie jest poprawna", false);
        closedAnswerBO.add(entity.getId(), "Wszystkie odpowiedzi sa poprawne", false);
        
        entity = closedQuestionBO.add("Prąd elektryczny to:", 2);
        closedQuestionBO.addCategoryOfQuestion(entity.getId(), fizyka.getId());
        closedQuestionBO.addCategoryOfQuestion(entity.getId(), 9);
        closedAnswerBO.add(entity.getId(), "piorun", true);
        closedAnswerBO.add(entity.getId(), "niewidzialne kropelki", false);
        closedAnswerBO.add(entity.getId(), "elektronki latajace mieszy znakami + i -", false);
        closedAnswerBO.add(entity.getId(), "uporządkowany (skierowany) ruch ładunków elektrycznych", true);
        closedAnswerBO.add(entity.getId(), "Zadna nie jest poprawna", false);
        closedAnswerBO.add(entity.getId(), "Wszystkie odpowiedzi sa poprawne", false);
        
        entity = closedQuestionBO.add("Pierwsze prawo Kirchhoffa:", 2);
        closedQuestionBO.addCategoryOfQuestion(entity.getId(), fizyka.getId());
        closedQuestionBO.addCategoryOfQuestion(entity.getId(), 9);
        closedAnswerBO.add(entity.getId(), "Suma natezen pradow wplywajacych do wezla jest rowna sumie natezen pradow wyplywajacych z tego wezla.", true);
        closedAnswerBO.add(entity.getId(), "prad nie ginie tylko znika", false);
        closedAnswerBO.add(entity.getId(), "jak prad wszedl to i wyjdzie", false);
        closedAnswerBO.add(entity.getId(), "porazonego pradem nalezy polewac zimna woda", false);
        closedAnswerBO.add(entity.getId(), "Zadna nie jest poprawna", false);
        closedAnswerBO.add(entity.getId(), "Wszystkie odpowiedzi sa poprawne", false);
        
        entity = closedQuestionBO.add("Drugie prawo Kirchhoffa:", 2);
        closedQuestionBO.addCategoryOfQuestion(entity.getId(), fizyka.getId());
        closedQuestionBO.addCategoryOfQuestion(entity.getId(), 9);
        closedAnswerBO.add(entity.getId(), "prad nie ginie tylko znika", false);
        closedAnswerBO.add(entity.getId(), "jak prad wszedl to i wyjdzie", false);
        closedAnswerBO.add(entity.getId(), "porazonego pradem nalezy polewac zimna woda", false);
        closedAnswerBO.add(entity.getId(), "Zadna nie jest poprawna", false);
        closedAnswerBO.add(entity.getId(), "Wszystkie odpowiedzi sa poprawne", false);
        closedAnswerBO.add(entity.getId(), "Suma spadkow napiecia w obwodzie zamknietym jest rowna zeru", true);
        
        entity = closedQuestionBO.add("Prawo Ohma mowi ze:", 2);
        closedQuestionBO.addCategoryOfQuestion(entity.getId(), fizyka.getId());
        closedQuestionBO.addCategoryOfQuestion(entity.getId(), 9);
        closedAnswerBO.add(entity.getId(), "prad nie ginie tylko znika", false);
        closedAnswerBO.add(entity.getId(), "jak prad wszedl to i wyjdzie", false);
        closedAnswerBO.add(entity.getId(), "porazonego pradem nalezy polewac zimna woda", false);
        closedAnswerBO.add(entity.getId(), "U = R*I", true);
        closedAnswerBO.add(entity.getId(), "Zadna nie jest poprawna", false);
        closedAnswerBO.add(entity.getId(), "Wszystkie odpowiedzi sa poprawne", false);
        
        entity = closedQuestionBO.add("Kondensator to:", 2);
        closedQuestionBO.addCategoryOfQuestion(entity.getId(), fizyka.getId());
        closedQuestionBO.addCategoryOfQuestion(entity.getId(), 9);
        closedAnswerBO.add(entity.getId(), "dziecieca zabawka", false);
        closedAnswerBO.add(entity.getId(), "jest to element elektryczny (elektroniczny), zbudowany z dwoch przewodnikow (okladek) rozdzielonych dielektrykiem", true);
        closedAnswerBO.add(entity.getId(), "2 lopaty polaczone kablem", false);
        closedAnswerBO.add(entity.getId(), "bateria", true);
        closedAnswerBO.add(entity.getId(), "Zadna nie jest poprawna", false);
        closedAnswerBO.add(entity.getId(), "Wszystkie odpowiedzi sa poprawne", false);
        
        entity = closedQuestionBO.add("Pojemnosc kondensatora wyraza sie wzorem:", 2);
        closedQuestionBO.addCategoryOfQuestion(entity.getId(), fizyka.getId());
        closedQuestionBO.addCategoryOfQuestion(entity.getId(), 9);
        closedAnswerBO.add(entity.getId(), "nie ma takiego wzoru", false);
        closedAnswerBO.add(entity.getId(), "U=I*R", false);
        closedAnswerBO.add(entity.getId(), "C=Q/U", true);
        closedAnswerBO.add(entity.getId(), "Zadna nie jest poprawna", false);
        closedAnswerBO.add(entity.getId(), "Wszystkie odpowiedzi sa poprawne", false);
        
        entity = closedQuestionBO.add("Pojemnosc kondensatora wyraza sie w jednostkach:", 2);
        closedQuestionBO.addCategoryOfQuestion(entity.getId(), fizyka.getId());
        closedQuestionBO.addCategoryOfQuestion(entity.getId(), 9);
        closedAnswerBO.add(entity.getId(), "fanfarach", false);
        closedAnswerBO.add(entity.getId(), "frodach", false);
        closedAnswerBO.add(entity.getId(), "farfadach", false);
        closedAnswerBO.add(entity.getId(), "faradach", true);
    }
}
