package com.example.hatiyaonlinesheba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    TextView marqueeText1, calender;
    ImageView menu;
    ImageSlider imageSlider;
    GridView gridView;
    HashMap<String, String> hashMap;
    ArrayList<HashMap<String, String> > arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.gridView);
        menu = findViewById(R.id.menu);
        //
        calender = findViewById(R.id.calender);
        imageSlider = findViewById(R.id.image_slider);
        // this TextView will marquee because it is selected
        marqueeText1 = (TextView) findViewById(R.id.marquee_text_1);
        marqueeText1.setSelected(true);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        calender.setText(currentDate);

        //=======================================================
        //=======================================================





        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //==========
                cmDialog();
            }
        });





        createSlider();

        myAdapter adapter = new myAdapter();
        gridView.setAdapter(adapter);

        arrayList();



    }//onCreate end===========================================================



    // slider =========================================================
    // ================================================================
    // ================================================================

    String img1 = "https://i.ytimg.com/vi/ncn0sYrxAJk/maxresdefault.jpg";
    String img2 = "https://shweb.me/infointeriors/wp-content/uploads/2016/11/Nijhum-Dwip.jpg";
    String img3 = "https://shampratikdeshkal.com/uploads/2019/09/online/photos/Nijhum-deep-5d709e8f25f27.jpg";
    String img4 = "https://adarbepari.com/wp-content/uploads/2016/05/nijhum-dwip-04.jpg";
    String img5 = "https://dailyindustry.news/wp-content/uploads/2022/01/Nijhum-Dwip-becomes.jpg";

    private void createSlider(){

        ArrayList<SlideModel> imageList = new ArrayList<>();
        //imageList.add(new SlideModel(R.drawable.slide_1, null));
        imageList.add(new SlideModel(img1,  null));
        imageList.add(new SlideModel(img2,  null));
        imageList.add(new SlideModel(img3,  null));
        imageList.add(new SlideModel(img4,  null));
        imageList.add(new SlideModel(img5,  null));
        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP);


        /*
        imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int position) {

                if (position==0){
                    Toast.makeText(getBaseContext(), "Image 1: Do something", Toast.LENGTH_SHORT).show();
                }

                if (position==1){
                    Toast.makeText(getBaseContext(), "Image 2: Do something", Toast.LENGTH_SHORT).show();
                }
            }
        });

         */


    }
    // ================================================================
    // ================================================================
    // ================================================================


    //dialog mathod===================================================================
    private void cmDialog(){
        new AlertDialog.Builder(MainActivity.this)
                .setMessage("Comming Soon")
                .show();
    }



    //listView helper===========================================================
    public class myAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.item, null);

            LinearLayout item = myView.findViewById(R.id.item);
            ImageView itemImg = myView.findViewById(R.id.itemImg);
            TextView itemName = myView.findViewById(R.id.itemName);

            hashMap = arrayList.get(position);
            String title = hashMap.get("title");
            int icon = Integer.parseInt(hashMap.get("imgicon"));

            itemName.setText(title);
            itemImg.setImageResource(icon);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //====================



                    if (itemName.getText().toString().contains("ব্লাড ডোনার")) {
                        startActivity(new Intent(MainActivity.this, BloodDoner.class));
                    }




                }
            });


            return myView;
        }
    }
    //=====================================================================
    //=====================================================================


    //Arraylist========================================================
    private void arrayList(){

        hashMap = new HashMap<>();
        hashMap.put("title", "খবর");
        hashMap.put("imgicon", ""+R.drawable.news);
        arrayList.add(hashMap);
        //
        hashMap = new HashMap<>();
        hashMap.put("title", "হাসপাতাল");
        hashMap.put("imgicon", ""+R.drawable.hospital);
        arrayList.add(hashMap);
        //
        hashMap = new HashMap<>();
        hashMap.put("title", "ব্লাড ডোনার");
        hashMap.put("imgicon", ""+R.drawable.blood);
        arrayList.add(hashMap);
        //
        hashMap = new HashMap<>();
        hashMap.put("title", "এম্বুলেন্স");
        hashMap.put("imgicon", ""+R.drawable.ambulance);
        arrayList.add(hashMap);
        //
        hashMap = new HashMap<>();
        hashMap.put("title", "ডাক্তার");
        hashMap.put("imgicon", ""+R.drawable.doctor);
        arrayList.add(hashMap);
        //
        hashMap = new HashMap<>();
        hashMap.put("title", "ফায়ার সার্ভিস");
        hashMap.put("imgicon", ""+R.drawable.fire_service);
        arrayList.add(hashMap);
        //
        hashMap = new HashMap<>();
        hashMap.put("title", "হেল্পলাইন");
        hashMap.put("imgicon", ""+R.drawable.helpline);
        arrayList.add(hashMap);
        //
        hashMap = new HashMap<>();
        hashMap.put("title", "থানা পুলিশ");
        hashMap.put("imgicon", ""+R.drawable.police);
        arrayList.add(hashMap);
        //
        hashMap = new HashMap<>();
        hashMap.put("title", "উকিল");
        hashMap.put("imgicon", ""+R.drawable.lawyer);
        arrayList.add(hashMap);
        //
        hashMap = new HashMap<>();
        hashMap.put("title", "সাংবাদিক");
        hashMap.put("imgicon", ""+R.drawable.reporter);
        arrayList.add(hashMap);
        //
        hashMap = new HashMap<>();
        hashMap.put("title", "বিদ্যুৎ");
        hashMap.put("imgicon", ""+R.drawable.electric_tower);
        arrayList.add(hashMap);
        //
        hashMap = new HashMap<>();
        hashMap.put("title", "হোটেল");
        hashMap.put("imgicon", ""+R.drawable.hotel);
        arrayList.add(hashMap);
        //
        hashMap = new HashMap<>();
        hashMap.put("title", "শিক্ষা প্রতিষ্ঠান");
        hashMap.put("imgicon", ""+R.drawable.education);
        arrayList.add(hashMap);
        //
        hashMap = new HashMap<>();
        hashMap.put("title", "শীপ / লঞ্ছ");
        hashMap.put("imgicon", ""+R.drawable.ship);
        arrayList.add(hashMap);
        //
        hashMap = new HashMap<>();
        hashMap.put("title", "দর্শনীয় স্থান");
        hashMap.put("imgicon", ""+R.drawable.loc);
        arrayList.add(hashMap);
        //
        hashMap = new HashMap<>();
        hashMap.put("title", "সংগঠন সমূহ");
        hashMap.put("imgicon", ""+R.drawable.support_people);
        arrayList.add(hashMap);
        //
        hashMap = new HashMap<>();
        hashMap.put("title", "ইউনিয়ন");
        hashMap.put("imgicon", ""+R.drawable.union);
        arrayList.add(hashMap);
        //
        hashMap = new HashMap<>();
        hashMap.put("title", "Developer");
        hashMap.put("imgicon", ""+R.drawable.developer);
        arrayList.add(hashMap);

    }

    //custom method end===================



}