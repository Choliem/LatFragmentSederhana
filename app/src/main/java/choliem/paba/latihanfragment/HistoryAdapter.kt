package choliem.paba.latihanfragment

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class HistoryAdapter(context: Context, private val data: List<Riwayat>) :
    ArrayAdapter<Riwayat>(context, android.R.layout.simple_list_item_1, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = super.getView(position, convertView, parent) as TextView
        val item = data[position]

        view.text = item.teks

        if (item.hasil < 0) {
            view.setBackgroundColor(Color.RED)
            view.setTextColor(Color.WHITE)
        } else {
            view.setBackgroundColor(Color.TRANSPARENT)
            view.setTextColor(Color.BLACK)
        }
        return view
    }
}