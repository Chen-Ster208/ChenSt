import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.Adapter {
    private String[] mDataSet;

    public ListAdapter(String[] data){
        mDataSet = data;
    }

    @Override
    public RecyclerView.Adapter onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView textView = new TextView(parent.getContext());
        RecyclerView.Adapter holder = new MyViewHolder(textView);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String textForDisplay = mDataSet[position];
        ((MyViewHolder)holder).mTextView.setText(textForDisplay);
    }

    @Override
    public int getItemCount() {
        return mDataSet length;
    }
}
