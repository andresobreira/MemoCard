package br.edu.ifba.mobile.memocard.BD;

/**
 * Created by andre on 20/06/2016.
 */
public class User {

    private long codigo = -1;
    private String aluno;
    private String professor;
    private String escola;
    private String serie;
    private String materia;
    private String turno;

    //getters and setters:

    public long getCodigo() {
        return codigo;
    }
    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }
    public String getAluno() {
        return aluno;
    }
    public void setAluno(String aluno) {
        this.aluno = aluno;
    }
    public String getProfessor() {
        return professor;
    }
    public void setProfessor(String professor) {
        this.professor = professor;
    }
    public String getEscola() {
        return escola;
    }
    public void setEscola(String escola) {
        this.escola = escola;
    }
    public String getSerie() {
        return serie;
    }
    public void setSerie(String serie) {
        this.serie = serie;
    }
    public String getMateria() {
        return materia;
    }
    public void setMateria(String materia) {
        this.materia = materia;
    }
    public String getTurno() {
        return turno;
    }
    public void setTurno(String turno) {
        this.turno = turno;
    }
}
