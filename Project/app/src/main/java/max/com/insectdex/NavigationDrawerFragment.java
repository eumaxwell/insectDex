package max.com.insectdex;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Max on 18/07/2015.
 */
public class SearchActivity extends Fragment implements View.OnClickListener {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private Button m_buttonOK;
    private Button m_buttonCancel;
    private TextView m_textViewPergunta;
    private ListView m_listView;

    private static SearchActivity searchActivity;
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static SearchActivity getInstance(int sectionNumber) {

        if (searchActivity == null) {
            searchActivity = new SearchActivity();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            searchActivity.setArguments(args);
        }
        return searchActivity;
    }

    public SearchActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        m_textViewPergunta = (TextView) rootView.findViewById(R.id.textView_search);

        m_buttonOK = (Button) rootView.findViewById(R.id.buttonOK_search);
        m_buttonOK.setOnClickListener(this);

        m_buttonCancel = (Button) rootView.findViewById(R.id.buttonCancel_search);
        m_buttonCancel.setOnClickListener(this);

        m_listView = (ListView) rootView.findViewById(R.id.listView);
        m_listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        update();

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onClick(View v)
    {
        if (m_buttonOK == v)
        {
            if (m_listView.getCheckedItemPosition() != -1) {
                String item = m_listView.getAdapter().getItem(
                        m_listView.getCheckedItemPosition()).toString();
                MainActivity.especies.setResposta(item);

                if (MainActivity.especies.temResultado()) {
                    this.getFragmentManager().beginTransaction()
                            .replace(R.id.container, ListaActivity.getInstance(2))
                            .commit();
                } else
                    update();
            }
        }
        if (m_buttonCancel == v)
        {
            MainActivity.especies.incrementaPergunta();
            update();
        }
    }

    public void update(){

        Set<String> respostas;
        do {
            respostas = MainActivity.especies.getRespostas();
            if (respostas.size() < 1)
                MainActivity.especies.incrementaPergunta();
        }while (respostas.size() < 1);

        String strPergunta = MainActivity.especies.getPergunta();
        m_textViewPergunta.setText(strPergunta);

        List<String> arrayOpcoes = new ArrayList<String>();
        for (String s : respostas){
            arrayOpcoes.add(s);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplication().getApplicationContext(), android.R.layout.simple_list_item_checked, arrayOpcoes){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setTextColor(Color.BLACK);

                return textView;
            }
        };

        m_listView.setAdapter(adapter);
    }
}
