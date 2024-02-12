package il.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;

    private ArrayList<DataModel> dataset = new ArrayList<DataModel>();

    private CharactersAdapter adapter;

    private AppCompatEditText searchedText;

    private String searchedVar="";

    private ImageButton imageBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        imageBtn = findViewById(R.id.search_btn);
        searchedText = findViewById(R.id.text_search);


        searchedText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchedVar = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchedVar!=""){
                    dataset = listSearch(dataset,searchedVar);
                    adapter.setList(dataset);
                    recyclerView.setAdapter(adapter);
                }
                else {
                    createDataSetTable();
                }
            }
        });
        createDataSetTable();
    }

    public ArrayList<DataModel> listSearch(ArrayList<DataModel> list,String searchedCharacter){
       ArrayList<DataModel> newList = new ArrayList<DataModel>();
       for(DataModel obj : list){
           searchedCharacter = searchedCharacter.toLowerCase();// searching everything in lowered chars in order to create unity
           if(obj.getName().toLowerCase().contains(searchedCharacter) ||
                   obj.getDescription().toLowerCase().contains(searchedCharacter)){
               newList.add(obj);
           }
       }
       for(int i=0;i<list.size();i++){
           DataModel d1 = list.get(i);
           if(!newList.contains(d1)){
               newList.add(d1);
           }
       }
        return newList;
    }

    public void createDataSetTable(){
        for (int i=0;i<Models.names.length;i++){
            dataset.add(new DataModel(
                    Models.names[i],
                    Models.desc[i],
                    Models._id[i],
                    Models.extraDesc[i]
            ));
        }

        adapter = new CharactersAdapter(dataset, new CharactersAdapter.ItemListener() {
            @Override
            public void onItemClicked(int index) {
                View rootView = findViewById(android.R.id.content);
                Snackbar snack = Snackbar.make(rootView, dataset.get(index).getExtraText(), Snackbar.LENGTH_LONG)
                        .setAction("Dismiss", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        }).setDuration(15000);
                View snackBarView = snack.getView();
                TextView snackBarText = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
                snackBarText.setMaxLines(30);
                snack.show();
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
