package br.edu.ifba.mobile.memocard.BD;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by André Sobreira on 20/06/2016.
 */
public class FachadaBD extends SQLiteOpenHelper {

    private static FachadaBD instancia = null;

    public static FachadaBD criarInstancia(Context context){ // singleton
        if(instancia == null){
            instancia = new FachadaBD(context);
        }
        return instancia;
    }

    public static FachadaBD getInstancia(){
        return instancia;
    }

    private static final String NOME_BANCO = "MemoCard";
    private static final int VERSAO_BANCO = 1;
    private FachadaBD(Context context) { super(context, NOME_BANCO, null, VERSAO_BANCO); }// construtor

    private static final String CREATE_TABLE_CARDS="CREATE TABLE CARDS(codigo INTEGER PRIMARY KEY AUTOINCREMENT, frente TEXT, verso TEXT)";
    private static final String CREATE_TABLE_USER="CREATE TABLE USER(codigo INTEGER PRIMARY KEY AUTOINCREMENT, aluno TEXT, professor TEXT, escola TEXT, serie TEXT, materia TEXT, turno TEXT";

    @Override
    public void onCreate(SQLiteDatabase db) { // criacao da tabela CARDS
            db.execSQL(CREATE_TABLE_CARDS);
            db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versaoAntiga, int versaoNova) {    }

    // METODOS CRUD Card:

    public long inserir(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("FRENTE", card.getFrente());
        valores.put("VERSO", card.getVerso());

        long insert = db.insert("CARDS", null, valores); //nomeTabela, null , valores(do card)
        return insert;
    }

    public long atualizar(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("FRENTE", card.getFrente());
        valores.put("VERSO", card.getVerso());

        long update = db.update("CARDS", valores, "CODIGO = " + card.getCodigo(), null);//update CARDS set VALORES where CODIGO = 'x'
        return update;
    }

    public int remover(Card card) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("CARDS", "CODIGO = " + card.getCodigo(), null);//delete from CARDS where CODIGO = 'x'
    }

    public List<Card> listarCards() {
        List<Card> cards = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selecao = "select CODIGO, FRENTE,VERSO from CARDS";

        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selecao, null);
        if(cursor != null){
            boolean temProximo = cursor.moveToFirst();
            while (temProximo){
                Card card = new Card();
                card.setCodigo(cursor.getLong(cursor.getColumnIndex("CODIGO")));
                card.setFrente(cursor.getString(cursor.getColumnIndex("FRENTE")));
                card.setVerso(cursor.getString(cursor.getColumnIndex("VERSO")));
                cards.add(card);
                temProximo = cursor.moveToNext();
            }
        }
        return cards;
    }

    // METODOS CRUD User:

    public long inserir(User usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        if(usuario.getCodigo() == -1) {
            valores.put("ALUNO", usuario.getAluno());
            valores.put("PROFESSOR", usuario.getProfessor());
            valores.put("ESCOLA", usuario.getEscola());
            valores.put("SERIE", usuario.getSerie());
            valores.put("MATERIA", usuario.getMateria());
            valores.put("TURNO", usuario.getTurno());
        }
        long insert = db.insert("USER", null, valores);
        return insert;
    }

    public long atualizar(User usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put("ALUNO", usuario.getAluno());
        valores.put("PROFESSOR", usuario.getProfessor());
        valores.put("ESCOLA", usuario.getEscola());
        valores.put("SERIE", usuario.getSerie());
        valores.put("MATERIA", usuario.getMateria());
        valores.put("TURNO", usuario.getTurno());

        long update = db.update("USER", valores, "CODIGO = " + usuario.getCodigo(), null);
        return update;
    }

// --Commented out by Inspection START (21/06/2016 23:58):
//    public int remover(User usuario) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete("USER", "CODIGO = " + usuario.getCodigo(), null);
//    }
// --Commented out by Inspection STOP (21/06/2016 23:58)

    public User listarUsuarios() {
        User usuario = new User();
        SQLiteDatabase db = this.getReadableDatabase();
        String selecao = "select * from USER";
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selecao, null);
        if (cursor != null) {
            usuario.setCodigo(cursor.getLong(cursor.getColumnIndex("CODIGO")));
            usuario.setAluno(cursor.getString(cursor.getColumnIndex("ALUNO")));
            usuario.setProfessor(cursor.getString(cursor.getColumnIndex("PROFESSOR")));
            usuario.setEscola(cursor.getString(cursor.getColumnIndex("ESCOLA")));
            usuario.setSerie(cursor.getString(cursor.getColumnIndex("SERIE")));
            usuario.setMateria(cursor.getString(cursor.getColumnIndex("MATERIA")));
            usuario.setTurno(cursor.getString(cursor.getColumnIndex("TURNO")));
        }
        return usuario;
    }
}
