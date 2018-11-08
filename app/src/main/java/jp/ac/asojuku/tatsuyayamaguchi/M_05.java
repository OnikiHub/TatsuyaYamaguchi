package jp.ac.asojuku.tatsuyayamaguchi;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class M_05 extends AppCompatActivity {

    private final String[] FROM = {"check","name"};
    private final int[] TO = {R.id.checkBoxdelete,R.id.textViewname};
    private class MyAdapter extends SimpleAdapter{
        public Map<Integer,Boolean> checkList = new HashMap<>();

        public MyAdapter(Context context, List<? extends Map<String, ?>> data,
                         int resource, String[] from, int[] to){
            super(context,data,resource,from,to);

            for(int i=0; i<data.size();i++){
                Map map = (Map)data.get(i);
                checkList.put(i,(Boolean)map.get("check"));
            }
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = super.getView(position,convertView,parent);
            CheckBox ch = view.findViewById(R.id.checkBoxdelete);

            ch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    checkList.put(position,isChecked);
                }
            });
            return view;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_05);

        ListView lv = findViewById(R.id.listViewcheck);

        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();

        MyAdapter adapter = new MyAdapter(M_05.this,list,R.layout.checklist,FROM,TO);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        findViewById(R.id.checkBoxdelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
