package com.example.tomek.mobilestore;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
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
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements RecylerViewOnItemClick {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView mNavigationView;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private List<Product> mBasket;
    private Map<String, List<Product>> shopDataSet;
    private ActionBar mActionBar;
    private Handler mHandler;
    private Runnable mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initProducts();

        mBasket = new ArrayList<>();
        mHandler = new Handler();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        mActionBar = getSupportActionBar();

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

        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowCustomEnabled(true);
        mActionBar.setCustomView(R.layout.actionbar_price);

 //       TextView price = (TextView) mActionBar.getCustomView().findViewById(R.id.menu_price);

        mThread = new Runnable() {
            @Override
            public void run() {
                double price = 0;
                for(Product tmp : mBasket) {
                    price += tmp.getPrice();
                }

                ((TextView) mActionBar.getCustomView().findViewById(R.id.menu_price)).setText(String.format(Locale.ENGLISH,
                        "%.2f zł", price));

                mHandler.postDelayed(this, 500);
            }
        };

        mHandler.postDelayed(mThread, 500);


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
        else if(item.getItemId() == R.id.menu_basket) {
            mFragmentManager = getFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            BasketFragment basketFragment = new BasketFragment();
            basketFragment.setmProducts(mBasket);

            mFragmentTransaction.replace(R.id.container, basketFragment);
            mFragmentTransaction.addToBackStack("BASKET");
            mFragmentTransaction.commit();
        }

        return true;
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

    public void addToBasket(Product product) {
        mBasket.add(product);
    }

    public void removeFromBasket(Product product) {
        mBasket.remove(product);
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

        List<Product> albums = new ArrayList<>();
        albums.add(new Product(1, "Ed Sheeran", R.drawable.album_sheeran, 99.90, R.raw.sheeran));
        albums.add(new Product(2, "Depeche Mode", R.drawable.album_depeche, 119.99, R.raw.depeche));
        albums.add(new Product(3, "Rolling Stones", R.drawable.albums_stones, 15.50, R.raw.stones));
        albums.add(new Product(4, "Modern Talking", R.drawable.albums_modern, 10.99, R.raw.modern));
        albums.add(new Product(5, "Dawid Podsiadło", R.drawable.albums_podsiadlo, 159.90, R.raw.podsiadlo));

        shopDataSet.put("Muzyka", albums);
    }


    @Override
    protected void onDestroy() {
        mHandler.removeCallbacks(mThread);
        super.onDestroy();
    }
}
