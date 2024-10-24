    package com.example.firstproject;

    import android.content.Context;
    import android.os.Bundle;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.AdapterView;
    import android.widget.BaseAdapter;
    import android.widget.CheckedTextView;
    import android.widget.ImageView;
    import android.widget.ListView;
    import android.widget.TextView;

    import androidx.activity.EdgeToEdge;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.content.ContextCompat;
    import androidx.core.graphics.Insets;
    import androidx.core.view.ViewCompat;
    import androidx.core.view.WindowInsetsCompat;

    public class MainActivity extends AppCompatActivity {
        private TextView textView, textView2;
        ListView listView;
        int[] image = {R.drawable.usa, R.drawable.jp, R.drawable.cn, R.drawable.kr, R.drawable.thai};
        String[] Countrys = {"美國", "日本", "中國", "韓國", "泰國"};
        String[] engname = {"USA", "Japan", "China", "Korea", "Thailand"};

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_main);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

            textView = (TextView) findViewById(R.id.textView);
            textView2 = (TextView) findViewById(R.id.textView2);
            listView = (ListView) findViewById(R.id.listView);

            MyAdapter Country = new MyAdapter(this);
            listView.setAdapter(Country);
            listView.setOnItemClickListener(listViewOnItemClick);

        }
        // 自訂介面設定
        public class MyAdapter extends BaseAdapter {
            private LayoutInflater myInflater;

            public MyAdapter(Context c) {
                myInflater = LayoutInflater.from(c);
            }

            @Override
            public int getCount() {
                return Countrys.length;
            }

            @Override
            public Object getItem(int position) {
                return Countrys[position];
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                convertView = myInflater.inflate(R.layout.country_layout, null);
                ImageView flag = (ImageView) convertView.findViewById(R.id.flag);
                TextView country = (TextView) convertView.findViewById(R.id.countryName);
                TextView countryengName = (TextView) convertView.findViewById(R.id.countryengName);
                CheckedTextView checkedTextView = (CheckedTextView) convertView.findViewById(R.id.checkedTextView);

                flag.setImageResource(image[position]);
                country.setText(Countrys[position]);
                countryengName.setText(engname[position]);

                checkedTextView.setChecked(listView.isItemChecked(position));

                //更改選取項目的勾勾
                boolean isChecked = listView.isItemChecked(position);
                checkedTextView.setChecked(isChecked);
                if (isChecked) {
                    checkedTextView.setCheckMarkDrawable(R.drawable.checked);
                    //設定被選取時的背景顏色
                    convertView.setBackgroundColor(ContextCompat.getColor(parent.getContext(), R.color.dark_blue));
                } else {
                    checkedTextView.setCheckMarkDrawable(null);
                    convertView.setBackgroundColor(ContextCompat.getColor(parent.getContext(), android.R.color.transparent));
                }

                return convertView;
            }

        }

        private ListView.OnItemClickListener listViewOnItemClick = new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView checkedTextView = view.findViewById(R.id.checkedTextView);
                checkedTextView.toggle();

                String all = "";
                for (int i = 0; i < listView.getCount(); i++) {
                    if (listView.isItemChecked(i)) {
                        all += Countrys[i] + " ";
                    }
                }
                textView2.setText("你選擇了:" + all);
                ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
            }
        };
    }