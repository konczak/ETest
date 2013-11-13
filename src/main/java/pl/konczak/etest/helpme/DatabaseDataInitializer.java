package pl.konczak.etest.helpme;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.dto.UserRegistration;
import pl.konczak.etest.entity.CategoryOfQuestion;
import pl.konczak.etest.entity.Role;
import pl.konczak.etest.facade.ICategoryOfQuestionFacade;
import pl.konczak.etest.facade.IUserRegisterFacade;
import pl.konczak.etest.repository.IRoleRepository;
import pl.konczak.etest.repository.IUserRepository;

@Component
public class DatabaseDataInitializer {

    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IUserRegisterFacade userRegisterFacade;
    @Autowired
    private ICategoryOfQuestionFacade categoryOfQuestionFacade;

    @Transactional
    @PostConstruct
    public void addDatabaseDate() {
        if (roleRepository.findAll().isEmpty()) {
            prepareRoles();
        }

        if (userRepository.findByEmail("konczak.piotrek@gmail.com") == null) {
            prepareUsers();
        }
        if (categoryOfQuestionFacade.searchAll().isEmpty()) {
            prepareCategoriesOfQuestion();
        }
    }

    private void prepareRoles() {
        addRole("ADMIN");
        addRole("TEACHER");
        addRole("STUDENT");
    }

    private void addRole(String name) {
        Role role = new Role(name);
        roleRepository.save(role);
    }

    private void prepareUsers() {
        UserRegistration userRegistration = new UserRegistration();
        userRegistration.setEmail("konczak.piotrek@gmail.com");
        userRegistration.setPassword("haslo");
        userRegistration.setFirstname("Piotr");
        userRegistration.setLastname("Konczak");
        userRegisterFacade.register(userRegistration);
    }

    private void prepareCategoriesOfQuestion() {
        addCategoryOfQuestion("Fizyka");
        addCategoryOfQuestion("Matematyka");
        addCategoryOfQuestion("Optyka");
        addCategoryOfQuestion("Magnetyzm");
        addCategoryOfQuestion("Siły");
        addCategoryOfQuestion("Grawitacje");
        addCategoryOfQuestion("Opór");
        addCategoryOfQuestion("Pęd");
        addCategoryOfQuestion("Moc");
        addCategoryOfQuestion("Prąd");
    }

    private void addCategoryOfQuestion(String title) {
        CategoryOfQuestion categoryOfQuestion = new CategoryOfQuestion(title);
        categoryOfQuestionFacade.add(categoryOfQuestion);
    }
}
