package cm.zy.service;


import cm.zy.pojo.User;

public interface UserService {
    // buscar usuario por nombre de usuario
    User findByUsername(String username) ;

    // registrar usuario
    void register(String username ,String password);

    // actualizar usuario
    void update(User user);

    /**
     *     actualizar imagen de perfil
     * @param avatarUrl
     */
    void updateAvatar(String avatarUrl);


    /**
     * actualizar password
     * @param username
     * @param newPassword
     */
    void updatePwd(String newPassword);
}
