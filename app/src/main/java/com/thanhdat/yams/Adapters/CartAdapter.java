package com.thanhdat.yams.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.thanhdat.yams.Activities.CartActivity;
import com.thanhdat.yams.Interfaces.OnClickInterface;
import com.thanhdat.yams.Models.Cart;
import com.thanhdat.yams.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>  {
    final int VIEW_TYPE_LOADING =0;
    final int VIEW_TYPE_ITEM=1;
    private  boolean isLoadingAdd;
    private CartActivity context;
    private ArrayList<Cart> carts;
    OnClickInterface onClickInterface;

    public CartAdapter(Context context, ArrayList<Cart> carts, OnClickInterface onClickInterface) {
        this.context = (CartActivity) context;
        this.carts = carts;
        this.onClickInterface = onClickInterface;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_cart, parent,false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Picasso.get().load(carts.get(position).getThumb()).into(holder.imvThumb);
        holder.txtName.setText(carts.get(position).getProductName() + " ("+ carts.get(position).getProductSize()+")");
        holder.txtTopping.setText(carts.get(position).getTopping());
        DecimalFormat decimalFormat = new DecimalFormat("######");
        holder.txtPrice.setText(decimalFormat.format(carts.get(position).getPrice())+"đ");
        holder.txtQuantity.setText(String.valueOf(carts.get(position).getQuantity()));
        holder.txtStock.setText(String.valueOf(carts.get(position).getAvailable()));
        holder.chkCheckItemCart.setChecked(carts.get(position).isChecked());
        onClickInterface.setClick(position);
        holder.chkCheckItemCart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                holder.chkCheckItemCart.setChecked(isChecked);
                carts.get(position).setChecked(isChecked);
                onClickInterface.setClick(position);
            }
        });
        holder.imbPlus.setOnClickListener(v -> context.updateQuantity(carts.get(position), "plus"));
        holder.imbMinus.setOnClickListener(v -> context.updateQuantity(carts.get(position), "minus"));
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        ImageView imvThumb;
        CheckBox chkCheckItemCart;
        TextView txtName,txtTopping, txtPrice,txtQuantity,txtStock;
        LinearLayout layoutForeground;
        ImageButton imbMinus, imbPlus;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            chkCheckItemCart= itemView.findViewById(R.id.chkCheckItem);
            imvThumb=itemView.findViewById(R.id.imvThumb);
            txtName = itemView.findViewById(R.id.txtName);
            txtTopping = itemView.findViewById(R.id.txtTopping);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtQuantity = itemView.findViewById(R.id.txtNumber);
            txtStock = itemView.findViewById(R.id.txtRemain);
            layoutForeground=itemView.findViewById(R.id.layoutForeground);
            imbMinus= itemView.findViewById(R.id.btnMinusItem);
            imbPlus= itemView.findViewById(R.id.btnPlusItem);
        }
    }

    public  void undoItem( Cart cart, int index){
        carts.add(index, cart);
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
//            holder.txtTopping=view.findViewById(R.id.txtTopping);
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
//        holder.txtTopping.setText(cart.getCartSize());
//        DecimalFormat decimalFormat = new DecimalFormat("######");
//        holder.txtPrice.setText(decimalFormat.format(cart.getCartPrice())+"đ");
//        holder.txtNumber.setText(String.valueOf(cart.getCartNumber()));
//        holder.txtRemain.setText(String.valueOf(cart.getCartRemain()));
//
//        return view;
//    }
//    public static  class ViewHolder{
//        ImageView imvThumb;
//        TextView txtName,txtTopping, txtPrice,txtNumber,txtRemain;
//
//    }
}
