package com.sami.nftmarketplace.service;

import com.sami.nftmarketplace.beans.Nft;
import com.sami.nftmarketplace.dao.IDao;

import java.util.ArrayList;
import java.util.List;

public class NftService implements IDao<Nft> {
    private List<Nft> nfts;
    private static NftService instance;

    private NftService(){
        this.nfts = new ArrayList<>();
    }

    public static NftService getInstance(){
        if(instance == null)
            instance = new NftService();
        return instance;
    }

    @Override
    public boolean create(Nft o) {
        return nfts.add(o);
    }

    @Override
    public boolean update(Nft o) {
        for(Nft n : nfts){
            if(n.getStar() == o.getId()){
                n.setNftName(o.getNftName());
                n.setNftImage(o.getNftImage());
                n.setEther(o.getEther());
                n.setPrice(o.getPrice());
                n.setStar(o.getStar());
                n.setDesc(o.getDesc());
                n.setCoin(o.getCoin());
            }
        }
        return true;
    }

    @Override
    public boolean delete(Nft o) {
        return nfts.remove(o);
    }

    @Override
    public Nft findById(int id) {
        for(Nft n : nfts){
            if(n.getId() == id)
                return n;
        }
        return null;
    }

    @Override
    public List<Nft> findAll() {
        return nfts;
    }
}