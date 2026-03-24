package focusflow.dao;

import focusflow.models.Usuario;

public interface IUsuarioDAO {
    Usuario getUsuarioPorNombre(String nombre);
    String insertarUsuario(Usuario usuario);
}