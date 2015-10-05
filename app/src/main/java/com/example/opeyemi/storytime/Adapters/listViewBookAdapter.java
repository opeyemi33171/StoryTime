package com.example.opeyemi.storytime.Adapters;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;


        import com.example.opeyemi.storytime.DataModels.Book;
        import com.example.opeyemi.storytime.R;

        import java.util.ArrayList;
public class listViewBookAdapter extends ArrayAdapter<Book> {


    public listViewBookAdapter(Context context, ArrayList<Book>books) {
        super(context, R.layout.book_row_layout,books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflator=LayoutInflater.from(getContext());
        View bookRow=inflator.inflate(R.layout.book_row_layout, parent, false);
        Book book=getItem(position);
        TextView bookNameLabel=(TextView)bookRow.findViewById(R.id.bookNameLabel);
        bookNameLabel.setText(book.getName());

        return bookRow;

    }
}
