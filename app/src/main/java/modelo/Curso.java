package modelo;

/**
 * Created by DReyna on 18/05/2015.
 */
public class Curso {
    private Integer _id;
    private String nom_curso;

    public Curso() {
    }

    public Curso(Integer _id, String nom_curso) {
        this._id = _id;
        this.nom_curso = nom_curso;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getNom_curso() {
        return nom_curso;
    }

    public void setNom_curso(String nom_curso) {
        this.nom_curso = nom_curso;
    }
}
