package com.aribhatt.automate.ui;

import android.app.SearchManager;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.afollestad.materialcab.MaterialCab;
import com.aribhatt.automate.R;
import com.aribhatt.automate.data.Permissions;
import com.aribhatt.automate.data.Repository;
import com.aribhatt.automate.data.db.entity.PriorityContact;
import com.aribhatt.automate.data.model.Contact;
import com.aribhatt.automate.ui.adapters.ContactListAdapter;
import com.aribhatt.automate.util.Utils;
import com.simplecityapps.recyclerview_fastscroll.views.FastScrollRecyclerView;

import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactsActivity extends AppCompatActivity implements ContactListAdapter.Callback, MaterialCab.Callback, SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.list)
    FastScrollRecyclerView list;

    /*@BindView(R.id.search_bar)
    SearchView searchView;*/
    ArrayList<Contact> contacts;
    ArrayList<Contact> allContacts;
    MaterialCab cab;
    ContactListAdapter adapter;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        /*cab = new MaterialCab(this, R.id.cab_stub)*/
        /*        .setMenu(R.menu.contact_cab_menu)*/
        /*        //.setPopupMenuTheme(R.style.ThemeOverlay_AppCompat_Dark)*/
        /*        //.setBackgroundColorRes(R.color.colorPrimary)*/
        /*        .setCloseDrawableRes(R.drawable.ic_clear_white_24dp);*/


        contacts = new ArrayList<>();
        allContacts =  new ArrayList<>();
        allContacts.add(new Contact(1, "Aritra", "", "", new ArrayList<String>()));
        allContacts.add(new Contact(2, "Betty", "", "", new ArrayList<String>()));
        allContacts.add(new Contact(3, "Chuck", "", "", new ArrayList<String>()));
        allContacts.add(new Contact(4, "Dick", "", "", new ArrayList<String>()));
        allContacts.add(new Contact(5, "Emma", "", "", new ArrayList<String>()));
        allContacts.add(new Contact(6, "Francois", "", "", new ArrayList<String>()));
        allContacts.add(new Contact(7, "Garry", "", "", new ArrayList<String>()));
        allContacts.add(new Contact(8, "Hannah", "", "", new ArrayList<String>()));
        allContacts.add(new Contact(9, "Igrid", "", "", new ArrayList<String>()));
        allContacts.add(new Contact(10, "Jon", "", "", new ArrayList<String>()));

        repository = new Repository(getApplication());



        Permissions.checkContactsPermission(this);

        //allContacts = Library.scanContacts(this);

        contacts.addAll(allContacts);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.contacts_title);
        }
        adapter = new ContactListAdapter(this, this);
        adapter.setLoading(true);

        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        list.addItemDecoration(itemDecoration);

        if (savedInstanceState != null) {
            cab = MaterialCab.restoreState(savedInstanceState, this, this);
            adapter.restoreState(savedInstanceState);
        } else {
            adapter.setLoading(false);
            adapter.addAll(contacts);
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
                        .setMenu(R.menu.contact_cab_menu)
                        //.setPopupMenuTheme(R.style.ThemeOverlay_AppCompat_Dark)
                        //.setBackgroundColorRes(R.color.colorPrimary)
                        .setCloseDrawableRes(R.drawable.ic_clear_white_24dp)
                        .start(this);
            } else if (!cab.isActive()) {
                Log.d("CALLBACK","Reseting cab");
                cab.reset().setMenu(R.menu.contact_cab_menu).setCloseDrawableRes(R.drawable.ic_clear_white_24dp).start(this);
            }
        }
        //cab.title(getString(R.string.x_selected, adapter.getSelectedCount()));
        if(cab != null) {
            cab.setTitle((CharSequence) (adapter.getSelectedCount() + " selected"));
        }
    }

    @Override
    public boolean onCabCreated(@NonNull MaterialCab cab, Menu menu) {
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
    public boolean onCabFinished(@NonNull MaterialCab cab) {
        adapter.clearSelected();
        cab = null;
        return true;
    }

    @Override
    public boolean onCabItemClicked(@NonNull MenuItem item) {
        Log.d("CALLBACK", "Menu item clicked "+ item.getTitle());
        ArrayList<Contact> selected = adapter.getSelected();
        String str = "";
        for (int i = 0; i < selected.size(); i++) {
            if (selected.get(i) != null) {
                str = str.concat("  " + i + 1 + ": " + selected.get(i).toString() + "  ");
                repository.insertPriorityContact(new PriorityContact(selected.get(i).getContactId(), selected.get(i).getContactName(), 1));
            }
        }
        Utils.showToast(this, ((String) item.getTitle()) + str);
        return true;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true); // Do not iconify the widget; expand it by default
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        searchContacts(newText);
        adapter.addAll(contacts);
        adapter.notifyDataSetChanged();
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        searchContacts(query);
        adapter.addAll(contacts);
        adapter.notifyDataSetChanged();
        return true;
    }

    @Override
    public boolean onClose() {
        searchContacts(null);
        adapter.addAll(contacts);
        adapter.notifyDataSetChanged();
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void searchContacts(String pattern){
        if(pattern == null || pattern.isEmpty()){
            contacts.addAll(allContacts);
            return;
        }

        contacts = new ArrayList<>();

        for(Contact c : allContacts){
            if(c.getContactName().toLowerCase().contains(pattern.toLowerCase())){
                contacts.add(c);
            }
        }
    }

    private static class AsyncScanContacts extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
