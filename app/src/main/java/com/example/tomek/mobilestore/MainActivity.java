package com.example.tomek.mobilestore;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.tomek.mobilestore.Model.Product;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements RecylerViewOnItemClick {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private Map<String, List<Product>> shopDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initProducts();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        mFragmentManager = getFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.container, new StartFragment());
        mFragmentTransaction.commit();

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_start:
                        mFragmentManager = getFragmentManager();
                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        mFragmentTransaction.replace(R.id.container, new StartFragment());
                        mFragmentTransaction.commit();
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_category:
                        mFragmentManager = getFragmentManager();
                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        CategoryFragment tmpFragment = new CategoryFragment();
                        tmpFragment.setmContext(MainActivity.this);
                        mFragmentTransaction.replace(R.id.container, tmpFragment);
                        mFragmentTransaction.commit();
                        mDrawerLayout.closeDrawers();
                        break;
                }
                return true;
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_basket, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnItemClick(View view, int position) {
        TextView textView = (TextView) view.findViewById(R.id.category_name);

        mFragmentManager = getFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        ProductsFragment productsFragment = new ProductsFragment();
        productsFragment.setmProducts(shopDataSet.get(textView.getText().toString()));

        mFragmentTransaction.replace(R.id.container, productsFragment);
        mFragmentTransaction.addToBackStack("FRAGMENT");
        mFragmentTransaction.commit();

    }

    private void initProducts() {
        shopDataSet = new LinkedHashMap<>();

        List<Product> books = new ArrayList<>();
        books.add(new Product(1, "CleanCode", R.drawable.book_cleancode, 51.75));
        books.add(new Product(2, "Java", R.drawable.book_javacompendium, 54.90));
        books.add(new Product(3, "Linux", R.drawable.book_linux, 18.70));
        books.add(new Product(4, "Spring", R.drawable.book_spring, 60.90));
        books.add(new Product(5, "Angular", R.drawable.book_angular, 44.25));
        books.add(new Product(6, "HTML & CSS", R.drawable.book_html, 52.90));

        shopDataSet.put("Ksiazki", books);

        List<Product> agdrtv = new ArrayList<>();
        agdrtv.add(new Product(1, "Żelazko", R.drawable.rtv_zelazko, 92.50));
        agdrtv.add(new Product(2, "Sokowirówka", R.drawable.rtv_juicer, 119.00));

        shopDataSet.put("AGD i RTV", agdrtv);

        List<Product> electronic = new ArrayList<>();
        electronic.add(new Product(1, "Drukarka", R.drawable.electronic_printer, 129.00));
        electronic.add(new Product(2, "Dysk SSD", R.drawable.electronic_ssd, 349.00));

        shopDataSet.put("Elektronika", electronic);

        List<Product> clothes = new ArrayList<>();
        clothes.add(new Product(1, "Garnitur", R.drawable.clothes_suit, 690.00));
        clothes.add(new Product(2, "Koszula", R.drawable.clothes_shirt, 59.00));

        shopDataSet.put("Odziez", clothes);
    }
}
