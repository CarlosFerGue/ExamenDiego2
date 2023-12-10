package action;

import model.Usuario;

public class MyLoginData {
    int idUsuario;
    Usuario usuario;

    public MyLoginData() {
    }

    public int getId() {
        return idUsuario;
    }

    public void setId(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "MyLoginData{" +
                "idUsuario=" + idUsuario +
                '}';
    }
}

