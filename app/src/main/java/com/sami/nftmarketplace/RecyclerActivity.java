package com.sami.nftmarketplace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.sami.nftmarketplace.adapter.NftAdapter;
import com.sami.nftmarketplace.beans.Nft;
import com.sami.nftmarketplace.service.NftService;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private List<Nft> nfts;
    private RecyclerView recyclerView;
    private NftAdapter nftAdapter = null;
    private NftService service;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        nfts = new ArrayList<>();
        service = NftService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycle_view);

        nftAdapter = new NftAdapter(this, service.findAll());
        recyclerView.setAdapter(nftAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if (nftAdapter != null){
                    nftAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.share:
                String txt = "Nfts";
                String mimeType = "text/plain";
                ShareCompat.IntentBuilder
                        .from(this)
                        .setType(mimeType)
                        .setChooserTitle("Nfts")
                        .setText(txt)
                        .startChooser();
                        return true;
        }
        return false;
    }

    public void init(){
        service.create(new Nft("AFRO PINKY","android.resource://com.sami.nftmarketplace/mipmap/afro_pinky", "android.resource://com.sami.nftmarketplace/mipmap/ether","10ETH",3,"creation of pinkplanet inc", "android.resource://com.sami.nftmarketplace/mipmap/coin"));
        service.create(new Nft("KRON THE KING","android.resource://com.sami.nftmarketplace/mipmap/kron_the_king", "android.resource://com.sami.nftmarketplace/mipmap/ether","20ETH",5,"creation of kronusus inc", "android.resource://com.sami.nftmarketplace/mipmap/coin"));
        service.create(new Nft("MONKEY ANGEL","android.resource://com.sami.nftmarketplace/mipmap/monkey_angel", "android.resource://com.sami.nftmarketplace/mipmap/ether","5ETH", 2.5F,"creation of apes inc", "android.resource://com.sami.nftmarketplace/mipmap/coin"));
        service.create(new Nft("CYBER APE","android.resource://com.sami.nftmarketplace/mipmap/cyber_ape", "android.resource://com.sami.nftmarketplace/mipmap/ether","18ETH", 5,"creation of futuristics inc", "android.resource://com.sami.nftmarketplace/mipmap/coin"));
        service.create(new Nft("LIPSTICK ALEXA","android.resource://com.sami.nftmarketplace/mipmap/lipstick_alexa", "android.resource://com.sami.nftmarketplace/mipmap/ether","20ETH", 2,"creation of flirty inc", "android.resource://com.sami.nftmarketplace/mipmap/coin"));
        service.create(new Nft("LAWRENCE THE SAILOR","android.resource://com.sami.nftmarketplace/mipmap/lawrence_the_sailor", "android.resource://com.sami.nftmarketplace/mipmap/ether","24ETH", 2.5F,"creation of sailors inc", "android.resource://com.sami.nftmarketplace/mipmap/coin"));
        service.create(new Nft("FREEZY THE HIPPY","android.resource://com.sami.nftmarketplace/mipmap/freezy_the_hippy", "android.resource://com.sami.nftmarketplace/mipmap/ether","15ETH", 2,"creation of hippies inc", "android.resource://com.sami.nftmarketplace/mipmap/coin"));
        service.create(new Nft("VICK THE ROBBER","android.resource://com.sami.nftmarketplace/mipmap/vick_the_robber", "android.resource://com.sami.nftmarketplace/mipmap/ether","15ETH", 4,"creation of scam inc", "android.resource://com.sami.nftmarketplace/mipmap/coin"));
        service.create(new Nft("IGOR THE RUSSIAN","android.resource://com.sami.nftmarketplace/mipmap/igor_the_russian", "android.resource://com.sami.nftmarketplace/mipmap/ether","12ETH", 3.5F,"creation of gorsky inc", "android.resource://com.sami.nftmarketplace/mipmap/coin"));
        service.create(new Nft("TWINKY THE GAMER","android.resource://com.sami.nftmarketplace/mipmap/twinky_the_gamer", "android.resource://com.sami.nftmarketplace/mipmap/ether","1.5ETH", 1,"creation of gamedev inc", "android.resource://com.sami.nftmarketplace/mipmap/coin"));
        service.create(new Nft("TREY_THE_TATTOOED","android.resource://com.sami.nftmarketplace/mipmap/trey_the_tattoed", "android.resource://com.sami.nftmarketplace/mipmap/ether","17ETH", 4.5F,"creation of apes inc", "android.resource://com.sami.nftmarketplace/mipmap/coin"));
        service.create(new Nft("SALEM THE CAT","android.resource://com.sami.nftmarketplace/mipmap/salem_the_cat", "android.resource://com.sami.nftmarketplace/mipmap/ether","8ETH", 1.5F,"creation of salem inc", "android.resource://com.sami.nftmarketplace/mipmap/coin"));
        service.create(new Nft("ARTHUR DA GOAT","android.resource://com.sami.nftmarketplace/mipmap/arthur_da_goat", "android.resource://com.sami.nftmarketplace/mipmap/ether","7ETH", 2.5F,"creation of goats inc", "android.resource://com.sami.nftmarketplace/mipmap/coin"));
    }
}