package max.com.insectdex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 * Created by Max on 18/07/2015.
 */
public final class Especies {


    private Map<String,Vector<String>> mapEspecieAtributos;
    private Vector<String> listaPerguntas;
    private int idPergunta;

    public Especies(InputStream file) {
        setMapEspecieAtributos(new HashMap<String, Vector<String>>());
        setListaPerguntas(new Vector<String>());
        carregaArquivo(file);

    }

    private void carregaArquivo(InputStream file) {
        try {

            InputStreamReader isr = new InputStreamReader(file);
            BufferedReader br = new BufferedReader(isr);
            String line = null;

            int iNumLinha=0;
            while ((line = br.readLine()) != null) {

                String[] listStrings = line.split(";");

                iNumLinha++;
                if (iNumLinha == 1){
                    for (String s : listStrings){
                        getListaPerguntas().add(s);
                    }
                    getListaPerguntas().remove(0);
                }
                else {
                    String strNomeEspecie = listStrings[0];
                    Vector<String> vecAtributos = new Vector<String>();
                    for (String s : listStrings){
                        vecAtributos.add(s);
                    }
                    vecAtributos.remove(0);

                    getMapEspecieAtributos().put(strNomeEspecie, vecAtributos);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void setResposta(String strResposta)
    {
        ArrayList<String> listaApagar = new ArrayList<>();
        for (Map.Entry<String, Vector<String>> entry : mapEspecieAtributos.entrySet())
        {
            if (!entry.getValue().elementAt(idPergunta).equals(strResposta))
            {
                listaApagar.add(entry.getKey());
            }
        }

        for (String s : listaApagar)
        {
            mapEspecieAtributos.remove(s);
        }

        incrementaPergunta();

    }
    public void incrementaPergunta() {
        idPergunta++;
        if (idPergunta == listaPerguntas.size()){
            idPergunta=0;
        }
    }

    public String getPergunta()
    {
        return listaPerguntas.elementAt(idPergunta);
    }
    public Set<String> getRespostas()
    {
        Set<String> setRespostas = new LinkedHashSet<String>();
        for (Map.Entry<String, Vector<String>> entry : mapEspecieAtributos.entrySet()){
            if (!entry.getValue().elementAt(idPergunta).isEmpty())
                setRespostas.add(entry.getValue().elementAt(idPergunta));
        }
        return setRespostas;
    }
    public Vector<String> getEspecies()
    {
        Vector<String> vecEspecies = new Vector<String>();
        for (Map.Entry<String, Vector<String>> entry : mapEspecieAtributos.entrySet()){
            vecEspecies.add(entry.getKey());
        }
        return vecEspecies;
    }

    public Map<String, Vector<String>> getMapEspecieAtributos() {
        return mapEspecieAtributos;
    }

    public void setMapEspecieAtributos(Map<String, Vector<String>> mapEspecieAtributos) {
        this.mapEspecieAtributos = mapEspecieAtributos;
    }

    public Vector<String> getListaPerguntas() {
        return listaPerguntas;
    }

    public void setListaPerguntas(Vector<String> listaPerguntas) {
        this.listaPerguntas = listaPerguntas;
    }

    public int getIdPergunta() {
        return idPergunta;
    }


}
