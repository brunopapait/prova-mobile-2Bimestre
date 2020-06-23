package br.com.prova2bimestre;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    try {

      //CRIANDO BANCO DE DADOS
      SQLiteDatabase database = openOrCreateDatabase("app", MODE_PRIVATE, null);

      //CRIANDO A TABELA FRASE
      database.execSQL("CREATE TABLE IF NOT EXISTS frases (id INTEGER PRIMARY KEY AUTOINCREMENT, frase VARCHAR(255))");

//      //INSERINDO FRASES NO BANCO DE DADOS
//      database.execSQL("INSERT INTO frases (frase) VALUES ('Como dizia meu avô mudo.')");
//      database.execSQL("INSERT INTO frases (frase) VALUES ('Às vezes os idiotas são capazes de coisas maravilhosas.')");
//      database.execSQL("INSERT INTO frases (frase) VALUES ('Um mesmo lugar não cai duas vezes em um raio!')");
//      database.execSQL("INSERT INTO frases (frase) VALUES ('Se o mundo acaba em 2012, porque já lançaram o Fifa 2013?')");
//      database.execSQL("INSERT INTO frases (frase) VALUES ('O que preciso descobrir é descobrir do que preciso.')");
//      database.execSQL("INSERT INTO frases (frase) VALUES ('Gente feia é igual gente bonita, só que feia.')");
//      database.execSQL("INSERT INTO frases (frase) VALUES ('Eu nasci pobre, e ainda nasci otário.')");
//      database.execSQL("INSERT INTO frases (frase) VALUES ('Preciso de um shampoo. Para que tipo de cabelo? Sujo!')");
//      database.execSQL("INSERT INTO frases (frase) VALUES ('De ontem em diante o amanhã é hoje!')");
//      database.execSQL("INSERT INTO frases (frase) VALUES ('O limão é uma laranja de mau humor.')");

      //CRIANDO A CONSULTA INICIAL
      String consulta = "SELECT id, frase FROM frases";

      //CRIANDO CURSOR
      Cursor cursor = database.rawQuery(consulta, null);

      //      //EXCLUIR TABELA
//      database.execSQL("DROP TABLE frases");

      //CRIANDO INDICES
      int indexId = cursor.getColumnIndex("id");
      int indexFrase = cursor.getColumnIndex("frase");

      //CRIANDO LAÇO DE REPETIÇÃO PARA IMPRIMIR OS REGISTROS DA APLICAÇÃO NO CONSOLE DA IDE.

      if(cursor != null && cursor.moveToFirst()){
        do{
          String id = cursor.getString(indexId);
          String frase = cursor.getString(indexFrase);
          Log.i("RESULTADO", "ID: " + id + " - FRASE: " + frase + ".");
        }while(cursor.moveToNext());
      }

      //PEGANDO A QUANTIDADE DE REGISTROS NO BANCO DE DADOS
      int quantidade = cursor.getCount();

      //GERANDO NUMEROS ALEATÓRIOS CONFORME A QUANTIDADE DE REGISTROS NO BANCO DE DADOS
      Random random = new Random();
      int numeroAleatorio = random.nextInt(quantidade) + 1;

      //REALIZAR CONSULTA PELO ID (NÚMERO GERADO ALEATÓRIAMENTE)
      String queryForId = "SELECT frase FROM frases WHERE id = " +numeroAleatorio;
      cursor = database.rawQuery(queryForId, null);
      cursor.moveToFirst();

      //CRIANDO INDICE E CURSOR PARA RESGATAR A FRASE OBTIDA NA QUERY POR ID
      int indiceFrase = cursor.getColumnIndex("frase");
      String getFrase = cursor.getString(indiceFrase);

      TextView txtFrase = findViewById(R.id.txtFrase);

      txtFrase.setText(getFrase);



    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
