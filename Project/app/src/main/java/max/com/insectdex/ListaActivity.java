package max.com.insectdex;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Max on 19/07/2015.
 */

public class ListaActivity extends Fragment implements View.OnClickListener {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private Button m_buttonOK;
    private Button m_buttonCancel;

    private static ListaActivity m_listaActivity;
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ListaActivity getInstance(int sectionNumber) {

        if (m_listaActivity == null) {
            m_listaActivity = new ListaActivity();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            m_listaActivity.setArguments(args);
        }
        return m_listaActivity;
    }

    public ListaActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lista, container, false);

        ListView listView = (ListView)rootView.findViewById(R.id.listView_lista);

        List<String> listaEspecies = MainActivity.especies.getEspecies();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplication().getApplicationContext(), android.R.layout.simple_list_item_checked, listaEspecies);
        listView.setAdapter(adapter);

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

    }

}
