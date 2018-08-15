package com.aribhatt.automate.ui;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialcab.MaterialCab;
import com.aribhatt.automate.R;
import com.aribhatt.automate.data.Repository;
import com.aribhatt.automate.data.db.entity.PriorityContact;
import com.aribhatt.automate.ui.adapters.ContactListAdapter;
import com.aribhatt.automate.ui.adapters.PriorityContactsAdapter;
import com.aribhatt.automate.util.Utils;
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PriorityContactsActivity extends AppCompatActivity implements ContactListAdapter.Callback, MaterialCab.Callback{
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.list)
    FastScrollRecyclerView list;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    private Repository repository;
    PriorityContactsAdapter adapter;
    MaterialCab cab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priority_contacts);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PriorityContactsActivity.this, ContactsActivity.class));
            }
        });

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.pcontacts_title);
        }

        adapter = new PriorityContactsAdapter(this, this);

        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        list.addItemDecoration(itemDecoration);

        repository = new Repository(getApplication());
        repository.getAllPriorityContacts().observe(this, new Observer<List<PriorityContact>>() {
            @Override
            public void onChanged(@Nullable List<PriorityContact> priorityContacts) {
                adapter.addAll((ArrayList<PriorityContact>) priorityContacts);
            }
        });

        if (savedInstanceState != null) {
            cab = MaterialCab.restoreState(savedInstanceState, this, this);
            adapter.restoreState(savedInstanceState);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (cab != null) {
            // If the CAB isn't null, save it's state for restoration in onCreate()
            cab.saveState(outState);
        }
    }

    @Override
    public boolean onCabCreated(MaterialCab cab, Menu menu) {
        Log.d("CALLBACK", "Cab created " + menu.getClass().getSimpleName());
        // Makes the icons in the overflow menu visible
        if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
            try {
                Field field = menu.getClass().getDeclaredField("mOptionalIconsVisible");
                field.setAccessible(true);
                field.setBoolean(menu, true);
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
        return true; // allow creation
    }

    @Override
    public boolean onCabItemClicked(MenuItem item) {
        Log.d("CALLBACK", "Menu item clicked "+ item.getTitle());
        ArrayList<PriorityContact> selected = adapter.getSelected();
        String str = "";
        for (int i = 0; i < selected.size(); i++) {
            if (selected.get(i) != null) {
                str = str.concat("  " + i + 1 + ": " + selected.get(i).toString() + "  ");
                repository.deletePriorityContact(selected.get(i));
            }
        }
        if (cab != null && cab.isActive()) {
            cab.finish();
            cab = null;
        }
        Utils.showToast(this, ((String) item.getTitle()) + str);
        return true;
    }

    @Override
    public boolean onCabFinished(MaterialCab cab) {
        adapter.clearSelected();
        cab = null;
        return true;
    }

    @Override
    public void onItemClicked(int index, boolean longClick) {
        if(!longClick && cab !=null && cab.isActive()){
            Log.d("CALLBACK", "Item clicked " + index);
            adapter.toggleSelected(index);
            Log.d("CALLBACK", "Selected " + adapter.getSelectedCount());
            if (adapter.getSelectedCount() == 0 && cab != null) {
                cab.finish();
                cab = null;
                return;
            }
        }
        else if(longClick){
            Log.d("CALLBACK", "Item long clicked "+index);
            if (cab == null) {
                Log.d("CALLBACK", "Creating cab");

                cab = new MaterialCab(this, R.id.cab_stub)
                        .setMenu(R.menu.prioritycontact_cab_menu)
                        //.setPopupMenuTheme(R.style.ThemeOverlay_AppCompat_Dark)
                        //.setBackgroundColorRes(R.color.colorPrimary)
                        .setCloseDrawableRes(R.drawable.ic_clear_white_24dp)
                        .start(this);
            } else if (!cab.isActive()) {
                Log.d("CALLBACK","Reseting cab");
                cab.reset().setMenu(R.menu.prioritycontact_cab_menu).setCloseDrawableRes(R.drawable.ic_clear_white_24dp).start(this);
            }
        }
        //cab.title(getString(R.string.x_selected, adapter.getSelectedCount()));
        if(cab != null) {
            cab.setTitle((CharSequence) (adapter.getSelectedCount() + " selected"));
        }

    }

    @Override
    public void onBackPressed() {
        if (cab != null && cab.isActive()) {
            cab.finish();
            cab = null;
        } else {
            super.onBackPressed();
        }
    }
}
