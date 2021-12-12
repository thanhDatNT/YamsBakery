package com.thanhdat.yams.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thanhdat.yams.Models.Cart;
import com.thanhdat.yams.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>  {
    final int VIEW_TYPE_LOADING =0;
    final int VIEW_TYPE_ITEM=1;
    private  boolean isLoadingAdd;
    private Context context;
    private ArrayList<Cart>carts;

    public CartAdapter(Context context, ArrayList<Cart> carts) {
        this.context = context;
        this.carts = carts;
    }



    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_cart, parent,false);
        return new CartViewHolder(view);


    }


    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.imvThumb.setImageResource(carts.get(position).getCartThumb());
        holder.txtName.setText(carts.get(position).getCartName());
        holder.txtSize.setText(carts.get(position).getCartSize());
        DecimalFormat decimalFormat = new DecimalFormat("######");
        holder.txtPrice.setText(decimalFormat.format(carts.get(position).getCartPrice())+"đ");
//        holder.txtPrice.setText(String.valueOf(carts.get(position).getCartPrice()));
        holder.txtNumber.setText(String.valueOf(carts.get(position).getCartNumber()));
        holder.txtRemain.setText(String.valueOf(carts.get(position).getCartRemain()));

    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        ImageView imvThumb;
        TextView txtName,txtSize, txtPrice,txtNumber,txtRemain;
        LinearLayout layoutForeground;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imvThumb=itemView.findViewById(R.id.imvThumb);
            txtName = itemView.findViewById(R.id.txtName);
            txtSize = itemView.findViewById(R.id.txtSize);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtNumber = itemView.findViewById(R.id.txtNumber);
            txtRemain = itemView.findViewById(R.id.txtRemain);
            layoutForeground=itemView.findViewById(R.id.layoutForeground);

        }
    }
    public  void removeItem(int index){
        carts.remove(index);
        notifyItemRemoved(index);
    }
    public  void undoItem( Cart cart, int index){
        carts.add(index,cart);
        notifyItemInserted(index);
    }


//    public void setData(ArrayList<Cart>carts){
//        carts.addAll(carts);
//        //notify UI
//        this.notifyDataSetChanged();
//    }
//    public void AddListItemAdapter(ArrayList<Cart> itemPlus)
//    {
//        //add list  to current array list of data
//        carts.addAll(itemPlus);
//        //notify UI
//        this.notifyDataSetChanged();
//    }
//
//    public CartAdapter(Activity context, int items_listview, List<Cart> cartList) {
//        this.context = context;
//        this.items_listview = items_listview;
//        this.cartList = cartList;
//    }
//
//
//
//    @Override
//    public int getCount() {
//        return cartList.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return cartList.get(i);
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        ViewHolder holder;
//        if(view==null)
//        {
//            holder=new ViewHolder();
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = inflater.inflate(items_listview,null);
//            holder.imvThumb=view.findViewById(R.id.imvThumb);
//            holder.txtName=view.findViewById(R.id.txtName);
//            holder.txtSize=view.findViewById(R.id.txtSize);
//            holder.txtPrice= view.findViewById(R.id.txtPrice);
//            holder.txtNumber=view.findViewById(R.id.txtNumber);
//            holder.txtRemain=view.findViewById(R.id.txtRemain);
//            view.setTag(holder);
//        }
//        else{
//            holder= (ViewHolder) view.getTag();
//        }
//        Cart cart = cartList.get(i);
//        holder.imvThumb.setImageResource(cart.getCartThumb());
//        holder.txtName.setText(cart.getCartName());
//        holder.txtSize.setText(cart.getCartSize());
//        DecimalFormat decimalFormat = new DecimalFormat("######");
//        holder.txtPrice.setText(decimalFormat.format(cart.getCartPrice())+"đ");
//        holder.txtNumber.setText(String.valueOf(cart.getCartNumber()));
//        holder.txtRemain.setText(String.valueOf(cart.getCartRemain()));
//
//        return view;
//    }
//    public static  class ViewHolder{
//        ImageView imvThumb;
//        TextView txtName,txtSize, txtPrice,txtNumber,txtRemain;
//
//    }
}
