    package com.example.firstproject;

    import android.content.Context;
    import android.content.Intent;
    import android.os.Bundle;
    import android.view.ContextMenu;
    import android.view.LayoutInflater;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.AdapterView;
    import android.widget.BaseAdapter;
    import android.widget.Button;
    import android.widget.CheckedTextView;
    import android.widget.ImageView;
    import android.widget.ListView;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.activity.EdgeToEdge;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.content.ContextCompat;
    import androidx.core.graphics.Insets;
    import androidx.core.view.ViewCompat;
    import androidx.core.view.WindowInsetsCompat;

    public class MainActivity extends AppCompatActivity {
        private TextView textView, textView2,textView4,textView8;
        ListView listView;
        private Button button2;
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
            textView4 = (TextView) findViewById(R.id.textView4);
            textView8 = (TextView) findViewById(R.id.textView8);
            listView = (ListView) findViewById(R.id.listView);
            Button button = (Button) findViewById(R.id.button);
            button2 =(Button) findViewById(R.id.button2);

            TextView textShow2=(TextView)findViewById(R.id.textView);

            MyAdapter Country = new MyAdapter(this);
            listView.setAdapter(Country);
            listView.setOnItemClickListener(listViewOnItemClick);

            button.setOnClickListener(buttonOnClick);
            button2.setOnClickListener(buttonOnClick2);

            registerForContextMenu(listView);
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
                CheckedTextView checkedTextView = (CheckedTextView)
                        convertView.findViewById(R.id.checkedTextView);

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
                    convertView.setBackgroundColor(ContextCompat.getColor(parent.getContext(),
                            R.color.dark_blue));
                }
                else {
                    checkedTextView.setCheckMarkDrawable(null);
                    convertView.setBackgroundColor(ContextCompat.getColor(parent.getContext(),
                            android.R.color.transparent));
                }
                return convertView;
            }
        }

        // 跳轉至第二頁
        private Button.OnClickListener buttonOnClick = new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentStatus = textView4.getText().toString();
                if(currentStatus.equals("未登入")){
                    Toast toast =Toast.makeText(MainActivity.this,"請先登入",Toast.LENGTH_LONG);
                    toast.show();
                }
                else{
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, com.example.firstproject.Second.class);

                    String name = textView2.getText().toString();
                    Bundle bundle = new Bundle();

                    bundle.putString("country",name);
                    intent.putExtras(bundle);
                    startActivityForResult(intent,2);
                }
            }
        };

        //跳到登入畫面
        private Button.OnClickListener buttonOnClick2 =new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                String currentStatus = textView4.getText().toString();
                if (currentStatus.equals("未登入")) {
                    Intent intent = new Intent(MainActivity.this, login.class);
                    startActivityForResult(intent, 1);
                } else {
                    textView4.setText("未登入");
                    button2.setText("登入");
                    Toast.makeText(MainActivity.this, "已退出", Toast.LENGTH_SHORT).show();
                }
            }
        };

        String x;
        private ListView.OnItemClickListener listViewOnItemClick = new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView checkedTextView = view.findViewById(R.id.checkedTextView);
                checkedTextView.toggle();

                String all = "";
                for (int i = 0; i < listView.getCount(); i++) {
                    if (listView.isItemChecked(i)) {
                        all += Countrys[i] + " ";
                        x=all;
                    }
                }
                textView2.setText("你選擇了："+all);
                ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
            }
        };

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.action_about) {
                Toast.makeText(this, "目前處於Beta版本", Toast.LENGTH_LONG).show();
                return true;
            } else if (id == R.id.action_quit) {
                finish();
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

        String[] exchangeRates = {"1 USD = 32.72 TWD", "1 JPY = 0.2133 TWD", "1 CNY = 4.565 TWD", "1 KRW = 0.02534 TWD", "1 THB = 0.9979 TWD"};

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo
                menuInfo) {
            super.onCreateContextMenu(menu, v, menuInfo);
            if (v.getId() == R.id.listView) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                menu.setHeaderTitle(Countrys[info.position]);
                menu.add(Menu.NONE, 0, 0, "顯示當前匯率");
            }
        }

        @Override
        public boolean onContextItemSelected(MenuItem item) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)
                    item.getMenuInfo();
            if (item.getItemId() == 0) {
                String exchangeRate = exchangeRates[info.position];
                Toast.makeText(this, exchangeRate, Toast.LENGTH_SHORT).show();
                return true;
            }
            return super.onContextItemSelected(item);
        }

        //接收回傳資料
        protected void onActivityResult(int requestCode,int resultCode,Intent data){
            super.onActivityResult(requestCode,resultCode,data);
            //login回傳資料
            if(requestCode==1){
                if(resultCode==11){
                    Bundle bundle=data.getExtras();
                    String name=bundle.getString("account");
                    String s=name;
                    textView4.setText(s);
                    button2.setText("已登入");
                }
            }
            //訂票頁面
            else if(requestCode==2){
                //否
                if(resultCode==22){
                    Toast toast =Toast.makeText(MainActivity.this,"你已取消訂票",Toast.LENGTH_LONG);
                    toast.show();
                }
                //是
                else if(resultCode==23){
                    textView8.setText("已訂購機票："+x);
                    Toast toast =Toast.makeText(MainActivity.this,"訂票成功",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        }
    }