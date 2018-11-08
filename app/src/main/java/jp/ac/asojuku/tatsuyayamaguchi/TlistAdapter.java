package jp.ac.asojuku.tatsuyayamaguchi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TlistAdapter extends Array.Adapter<ImageViewHolder> {
    private ArrayList<ImageViewHolder> mItems;
    private int mResource;
    private LayoutInflater mInflater;

    public TlistAdapter(Context context, int resource, List<ImageViewHolder> items) {
        super(context, resource, items);
        mResource = resource;
        mItems = (ArrayList<ImageViewHolder>) items;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFATER_SERVICE);
    }

    @Override
    public View getView(int Position, View ConvertView, ViewGroup parent) {
        View view;

        if (ConvertView != null) {
            view = convertView;
        } else {
            view = mInflater.inflate(mResource, null);
        }
    }

    ImageViewHolder items = mItems.get(position);

    ImageView imageView = (ImageView) view.findViewById(R.id.imageBeil);
        imageView.setImageBitmap(item.getImageview());
    TextView title = (TextView) view.findViewById(R.id.title);
        title.setTitle(item.getTitle());
        return view;
}
