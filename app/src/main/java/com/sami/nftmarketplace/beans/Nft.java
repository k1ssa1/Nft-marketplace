package com.sami.nftmarketplace.beans;
// Class for the item displayed

public class Nft {
    private int id;
    private String nftName;
    private String nftImage;
    private String ether;
    private String price;
    private float star;
    private String desc;
    private String coin;

    private static int comp;

    // Constructor
    public Nft(String nftName, String nftImage, String ether, String price, float star, String desc, String coin){
        this.id = ++comp;
        this.nftName = nftName;
        this.nftImage = nftImage;
        this.ether = ether;
        this.price = price;
        this.star = star;
        this.desc = desc;
        this.coin = coin;
    }

    // Getters and setters
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNftName(){
        return nftName;
    }

    public void setNftName(String nftName){
        this.nftName = nftName;
    }

    public String getNftImage(){
        return nftImage;
    }

    public void setNftImage(String nftImage){
        this.nftImage = nftImage;
    }

    public String getEther(){
        return ether;
    }

    public void setEther(String ether){
        this.ether = ether;
    }

    public String getPrice(){
        return price;
    }

    public void setPrice(String price){
        this.price = price;
    }

    public float getStar (){
        return star;
    }

    public void setStar(float star){
        this.star = star;
    }

    public String getDesc (){
        return desc;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public String getCoin(){
        return coin;
    }

    public void setCoin(String coin){
        this.coin = coin;
    }


}
