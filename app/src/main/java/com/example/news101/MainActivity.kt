package com.example.news101

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val topics = arrayOf("Politics","Science","Food")
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,topics)

        spinner1.adapter = arrayAdapter
        spinner1.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                spinnerResult.text = topics[position]



            }

        }
        toolbar.setNavigationOnClickListener {
            Toast.makeText(this,"Navigation Menu Clicked",Toast.LENGTH_SHORT).show()
        }
        val listView = findViewById<ListView>(R.id.main_listview)
//        val redColor = Color.parseColor("#FF0000")
//        listView.setBackgroundColor(redColor)

        listView.adapter = MyCustomAdapter(this)



    }
    class Model(val img:Int){

    }


    private class MyCustomAdapter(context: Context): BaseAdapter() {
        private val mContext: Context

        private val names = arrayListOf<String>(
                "Donald Trump survives Covid-19", "Boris Johnson disappears", "The EU has disbanded!","Witcher",
                "Conservatives are set to lose the election!","Four-hundred thousand voters"
        )

        private val list = arrayOf<Int>(
                R.drawable.trump123,
                R.drawable.bojo123,
                R.drawable.eu123,
                R.drawable.eu123,
                R.drawable.trump123,
                R.drawable.trump123

        )

        private val description = arrayListOf<String>(
                "Donald Trump has made a miraculous recovery from Covid-19, how can he be more powerful than Hercules himself!",
                "Boris Johnson has gotten lost again! We've set-up pints of fosters round London in an aims to lure him out again!",
                "The EU has decided that they no longer want to deal with everyones rubbish anymore, deal with it yourselves they say!",
                "Hello","Hello","Hello")



        init{
            mContext = context
        }

        //Responsible for rows in List
        override fun getCount(): Int {
            return names.size
        }

        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }

        override fun getItemId(position: Int): Long {

            return position.toLong()
        }
        //responsbile for rendering out each row
        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
            val layoutInflater =  LayoutInflater.from(mContext)
            val rowMain = layoutInflater.inflate(R.layout.row,viewGroup, false)

            val nameTextView = rowMain.findViewById<TextView>(R.id.name_textView)
            nameTextView.text = names.get(position)

            val positionTextView = rowMain.findViewById<TextView>(R.id.position_textview)
            //positionTextView.text = "Article number: $position"
            positionTextView.text = description.get(position)

            val imageView= rowMain.findViewById<ImageView>(R.id.news_image)
            imageView.setImageResource(list.get(position))
            return rowMain
//            val textView = TextView(mContext)
//            textView.text = "Here is my row for my listview"
//            return textView
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemview = item.itemId
        when(itemview){
            R.id.add -> Toast.makeText(applicationContext, "Add Clicked", Toast.LENGTH_SHORT).show()
            R.id.Notify -> Toast.makeText(applicationContext, "Notification Clicked", Toast.LENGTH_SHORT).show()

        }
        return false
    }
}