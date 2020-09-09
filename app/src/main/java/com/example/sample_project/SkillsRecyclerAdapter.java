package com.example.sample_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

//<LearnRecyclerAdapter.MyViewHolder>
public class SkillsRecyclerAdapter extends RecyclerView.Adapter {

    private Context  mContext ;
    // GoogleLearningBoard dataModel ;
    private  LayoutInflater mLayoutInflater;
   // ArrayList<GoogleLearningBoard> dataModel ;

    // when using retrofit api
    List<GoogleLearningBoard> dataModel ;


    // We also implement for retrofit for arrayList
   /* public SkillsRecyclerAdapter(Context _context, ArrayList<GoogleLearningBoard>_data){
        this.mContext = _context ;
        this.dataModel = _data ;
        mLayoutInflater = LayoutInflater.from(_context);
    }
*/
    // when using retrofit
    public SkillsRecyclerAdapter(Context _context, List<GoogleLearningBoard> _data){
        this.mContext = _context ;
        dataModel= _data ;
        mLayoutInflater = LayoutInflater.from(_context);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //  View view = LayoutInflater.from(parent.getContext())
        View view =     mLayoutInflater.inflate(R.layout.skills_card_list, parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view) ;

        return myViewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ( (MyViewHolder)holder).mTextViewName.setText(dataModel.get(position).getName() );
        String value = Integer.toString(dataModel.get(position).getValue()) ;
        ( (MyViewHolder)holder).mTextViewValue.setText( value );
        ( (MyViewHolder)holder).mTextViewCountry.setText(dataModel.get(position).getCountry() );

        // i want to use glide api to show our image
        //  ( (MyViewHolder)holder).mImage.setImageResource(getImage(dataModel.get(position).getBadgeUrl()) );
        //   ( (MyViewHolder)holder)holder).mImage.setImageResource(dataModel.get(position).getBadgeUrl());

        // using glide
        Glide.with(mContext)
                .load( dataModel.get(position).getBadgeUrl() )
                .into(( (MyViewHolder)holder).mImage) ;
    }

    // MyViewHolder


  /*  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            holder.mTextViewName.setText(dataModel.get(position).getName() );
            holder.mTextViewValue.setText(dataModel.get(position).getValue() );
            holder.mTextViewCountry.setText(dataModel.get(position).getCountry() );

            // i want to use glide api to show our image
            holder.mImage.setImageResource(getImage(dataModel.get(position).getBadgeUrl()) );
          //  holder.mImage.setImageResource(dataModel.get(position).getBadgeUrl());

    }*/

    private int getImage(String imageName) {
        int drawableResourceId = mContext.getResources().getIdentifier(imageName, "drawable", mContext.getPackageName());
        return drawableResourceId;
    }

    @Override
    public int getItemCount() {
        return dataModel.size();
    }


    public  class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImage ;
        TextView mTextViewName ;
        public  TextView mTextViewValue ;
        public  TextView mTextViewCountry ;
        View itemView ;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mImage = (ImageView) itemView.findViewById(R.id.skills_imageView);
            this.mTextViewName = (TextView) itemView.findViewById(R.id.skills_text_name);
            this.mTextViewValue = (TextView) itemView.findViewById(R.id.skills_text_value) ;
            this.mTextViewCountry = (TextView) itemView.findViewById(R.id.skills_text_country) ;
            this.itemView = itemView ;

        }
    }

}
