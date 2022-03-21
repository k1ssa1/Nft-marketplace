package com.sami.nftmarketplace.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sami.nftmarketplace.R;
import com.sami.nftmarketplace.beans.Nft;
import com.sami.nftmarketplace.service.NftService;

import java.util.ArrayList;
import java.util.List;

public class NftAdapter extends RecyclerView.Adapter<NftAdapter.NftViewHolder> implements Filterable {
    private static final String TAG = "NftAdapter";
    private List<Nft> nfts;
    private Context context;
    private LayoutInflater inflater;
    private NewFilter mfilter;
    private List<Nft> nftFilter;

    public NftAdapter(Context context, List<Nft> nfts) {
        this.nfts = nfts;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.nftFilter = new ArrayList<>();
        this.nftFilter.addAll(nfts);
        mfilter = new NewFilter(this);
    }

    @NonNull
    @Override
    public NftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.nft_card_component, parent, false);
        return new NftViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NftViewHolder holder, int position) {
        holder.nftName.setText(nftFilter.get(position).getNftName());
        holder.nftPrice.setText(nftFilter.get(position).getPrice()+"");
        holder.desc.setText(nftFilter.get(position).getDesc());
        holder.id.setText(nftFilter.get(position).getId()+"");
        holder.stars.setRating(nftFilter.get(position).getStar());
        Glide
                .with(context)
                .load(nftFilter.get(position).getNftImage())
                .centerCrop()
                .apply(new RequestOptions().override(100, 100))
                .into(holder.nftImage);

        Glide
                .with(context)
                .load(nftFilter.get(position).getEther())
                .centerCrop()
                .apply(new RequestOptions().override(10, 10))
                .into(holder.ether);

        Glide
                .with(context)
                .load(nftFilter.get(position).getCoin())
                .centerCrop()
                .apply(new RequestOptions().override(69, 71))
                .into(holder.coin);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View popup = LayoutInflater.from(context).inflate(R.layout.nft_item_popup, null,
                        false);
                final TextView name = popup.findViewById(R.id.nftName_popup);
                final ImageView img = popup.findViewById(R.id.nftimg_popup);
                final RatingBar bar = popup.findViewById(R.id.stars_popup);
                final TextView idss = popup.findViewById(R.id.idss);
                Bitmap bitmap = ((BitmapDrawable)((ImageView)v.findViewById(R.id.nftimg)).getDrawable()).getBitmap();img.setImageBitmap(bitmap);
                bar.setRating(((RatingBar)v.findViewById(R.id.stars)).getRating());
                name.setText(((TextView)v.findViewById(R.id.nftName)).getText().toString());
                idss.setText(((TextView)v.findViewById(R.id.ids)).getText().toString());
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle("NFT RATING ")
                        .setMessage("Rate the Nft from 1 to 5:")
                        .setView(popup)
                        .setPositiveButton("Validate", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                float s = bar.getRating();
                                int ids = Integer.parseInt(idss.getText().toString());
                                Nft nft = NftService.getInstance().findById(ids);
                                nft.setStar(s);
                                NftService.getInstance().update(nft);
                                notifyItemChanged(holder.getAdapterPosition());
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return nftFilter.size();
    }

    @Override
    public Filter getFilter() {
        return mfilter;
    }


    public class NftViewHolder  extends RecyclerView.ViewHolder {

            TextView nftName, nftPrice, desc, id;
            ImageView nftImage, ether, coin;
            RatingBar stars;


             public NftViewHolder(@NonNull View itemView) {
                super(itemView);
                id = itemView.findViewById(R.id.ids);
                nftName = itemView.findViewById(R.id.nftName);
                nftImage = itemView.findViewById(R.id.nftimg);
                ether = itemView.findViewById(R.id.ether);
                nftPrice = itemView.findViewById(R.id.nftPrice);
                stars = itemView.findViewById(R.id.stars);
                desc = itemView.findViewById(R.id.desc);
                coin = itemView.findViewById(R.id.coin);

            }


        }

    public class NewFilter extends Filter {
        public RecyclerView.Adapter mAdapter;
        public NewFilter(RecyclerView.Adapter mAdapter) {
            super();
            this.mAdapter = mAdapter;
        }
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            nftFilter.clear();
            final FilterResults results = new FilterResults();
            if (charSequence.length() == 0) {
                nftFilter.addAll(nfts);
            } else {
                final String filterPattern = charSequence.toString().toLowerCase().trim();
                for (Nft a : nfts) {
                    if (a.getNftName().toLowerCase().startsWith(filterPattern)) {
                        nftFilter.add(a);
                    }
                }
            }
            results.values = nftFilter;
            results.count = nftFilter.size();
            return results;
        }
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            nftFilter = (List<Nft>) filterResults.values;
            this.mAdapter.notifyDataSetChanged();
        }
    }

    }

