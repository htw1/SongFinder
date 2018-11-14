package com.htw.songfinder.ui;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.htw.songfinder.R;
import com.htw.songfinder.viewModel.ViewModelApp;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class StartActivity extends AppCompatActivity {

    MaterialSearchView searchView;
    private ViewModelApp viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        searchView = findViewById(R.id.search_view);

        viewModel = ViewModelProviders
                .of(this)
                .get(ViewModelApp.class);

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewModel.getAll(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        if (savedInstanceState == null){

            MainListFragment mainListFragment = new MainListFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container,mainListFragment,MainListFragment.KEY_MAIN_FRAGMENT_ID)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }


}
