package com.example.ortalamahesapla

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.ortalamahesapla.databinding.ActivityMainBinding
import com.example.ortalamahesapla.databinding.YeniDersLayoutBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var binding2: YeniDersLayoutBinding
    private var tumDerslerinBilgileri: ArrayList<Dersler> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        var dersler = arrayOf("Matematik", "Fizik", "Kimya", "Türkçe", "Algoritma")
        var adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, dersler)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
        binding.textEditDersGir.setAdapter(adapter)
        if (binding.rootLayout.childCount == 0) {
            binding.btnHesapla.visibility = View.INVISIBLE
        } else {
            binding.btnHesapla.visibility = View.VISIBLE
        }
        binding.btnEkle.setOnClickListener {
            //var inflater = LayoutInflater.from(this)
            /*  var inflater2 = layoutInflater
            var inflater3 = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            inflater3.inflate(R.layout.yeni_ders_layout) */

            // 1.kısım xml den javaya çevirdiğimiz kısım
            // 2. kısım nerenin altına eklicem ==> null: hiçbir yere eklemek istemiyorum ben kendim eklicem
            //var yeniDersView = inflater.inflate(R.layout.yeni_ders_layout, null)

            if (!binding.textEditDersGir.text.isNullOrEmpty()) {
                binding2 = YeniDersLayoutBinding.inflate(layoutInflater)
                var view2 = binding2.root


                //statik alandan kullanıcının girdiği değerleri almak
                var dersAdi = binding.textEditDersGir.text.toString()
                var kredi = binding.spinnerKredi.selectedItemPosition
                var harfNotu = binding.spinnerHarfNotu.selectedItemPosition

                //yeniDersView.girilenDers.setText(dersAdi)
                binding2.girilenDers.setText(dersAdi)
                binding2.spnKredi.setSelection(kredi)
                binding2.spnHarfNotu.setSelection(harfNotu)

                binding2.btnSil.setOnClickListener {
                    binding.rootLayout.removeView(view2)
                    if (binding.rootLayout.childCount == 0) {
                        binding.btnHesapla.visibility = View.INVISIBLE
                    } else {
                        binding.btnHesapla.visibility = View.VISIBLE
                    }
                }

                binding.rootLayout.addView(view2)
                refresh()
                binding.btnHesapla.visibility = View.VISIBLE
            } else {
                Toast.makeText(this, "Ders Giriniz", Toast.LENGTH_LONG).show()
            }

            binding.btnHesapla.setOnClickListener {
                var toplamNot = 0.0
                var toplamKredi = 0.0
                for (gecici in 0 until binding.rootLayout.childCount) {
                    var ders = Dersler(
                        binding2.girilenDers.text.toString(),
                        ((binding2.spnKredi.selectedItemPosition) + 1).toString(),
                        binding2.spnHarfNotu.selectedItem.toString()
                    )
                    tumDerslerinBilgileri.add(ders)
                }
                for (gecici in tumDerslerinBilgileri){
                    toplamNot += (harfiNotaCevir(gecici.harfNotu)) * (gecici.dersKredi.toDouble())
                    toplamKredi += gecici.dersKredi.toDouble()
                }
                Toast.makeText(this, "Notunuz: ${toplamNot / toplamKredi}", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun refresh() {
        binding.textEditDersGir.setText("")
        binding.spinnerKredi.setSelection(0)
        binding.spinnerHarfNotu.setSelection(0)
    }


    private fun harfiNotaCevir(not : String) : Double{
        var notDegeri = 0.0
        when(not){
            "AA" -> notDegeri = 4.0
            "AB" -> notDegeri = 3.5
            "BB" -> notDegeri = 3.0
            "BC" -> notDegeri = 2.5
            "CC" -> notDegeri = 2.0
            "DC" -> notDegeri = 1.5
            "DD" -> notDegeri = 1.0
            "FF" -> notDegeri = 0.0
        }

        return notDegeri

    }
}