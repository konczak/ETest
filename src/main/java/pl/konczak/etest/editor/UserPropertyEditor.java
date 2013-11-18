package pl.konczak.etest.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.entity.User;
import pl.konczak.etest.repository.IUserRepository;

public class UserPropertyEditor
        extends PropertyEditorSupport {

    private IUserRepository userRepository;

    public UserPropertyEditor(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public void setAsText(String id) {
        User user = userRepository.getById(Integer.parseInt(id));

        setValue(user);
    }

    @Override
    public String getAsText() {
        User user = (User) getValue();
        return user.getId().toString();
    }
}
