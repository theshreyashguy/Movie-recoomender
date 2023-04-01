package com.example.intern_process

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.intern_app.Movie
import com.example.intern_process.ui.db.FavItem
import com.squareup.picasso.Picasso
import java.net.URL


private var context1: Context? = null

class mainadapter(val mlist: ArrayList<Movie>):RecyclerView.Adapter<mainadapter.mainviewholder>() {
    class mainviewholder(val view: View)

        :RecyclerView.ViewHolder(view){
        val delitem = view.findViewById<ImageButton>(R.id.dlete)
        val favi = view.findViewById<ImageButton>(R.id.favitm)
        val img = view.findViewById<ImageView>(R.id.poster)

            fun itembind(ml : Movie){

                view.findViewById<TextView>(R.id.title11).setText(ml.Title)
                view.findViewById<TextView>(R.id.year).setText(ml.Year.toString())
                view.findViewById<TextView>(R.id.summary).setText(ml.Summary.toString())
                view.findViewById<TextView>(R.id.shortsum).setText(ml.ShortSummary.toString())
                view.findViewById<TextView>(R.id.genre).setText(ml.Genres.toString())
                view.findViewById<TextView>(R.id.runtime).setText(ml.Runtime.toString())
                view.findViewById<TextView>(R.id.rating).setText(ml.Rating.toString())
//                val uri1 = Uri.parse(ml.MoviePoster)
//                view.findViewById<ImageView>(R.id.poster).setImageURI(uri1)
                view.findViewById<Button>(R.id.imbd).setOnClickListener {
                    var ff = "https://www.google.com/search?q=" + ml.IMDBID

                    val uri: Uri = Uri.parse(ff)
                    val intent = Intent.makeMainSelectorActivity(
                        Intent.ACTION_MAIN,
                        Intent.CATEGORY_APP_BROWSER
                    )


                    intent.data = uri
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    try {
                       context1?.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
//                        Logging.loge("No activity for intent $intent", e)
                    }
                }

                view.findViewById<Button>(R.id.trailer).setOnClickListener {
                    var ff = "https://www.google.com/search?q=" + ml.YouTubeTrailer

                    val uri: Uri = Uri.parse(ff)
                    val intent = Intent.makeMainSelectorActivity(
                        Intent.ACTION_MAIN,
                        Intent.CATEGORY_APP_BROWSER
                    )


                    intent.data = uri
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    try {
                        context1?.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
//                        Logging.loge("No activity for intent $intent", e)
                    }
                }

                view.findViewById<TextView>(R.id.director).setText(ml.Director.toString())
                view.findViewById<TextView>(R.id.writer).setText(ml.Writers.toString())
                view.findViewById<TextView>(R.id.cast).setText(ml.Cast.toString())


            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mainviewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listlayout, parent, false)
        context1  = parent.context


        return mainviewholder(view)

    }

    override fun onBindViewHolder(holder: mainviewholder, position: Int) {
        val m = mlist[position]
        holder.favi.setOnClickListener {
            FavItem.fav.add(m)
        }
//        holder.img.setImageURI(uri1)
//        Picasso.get().load(m.MoviePoster).into(holder.img)
        val url = m.MoviePoster
        Picasso.get().load(url).error(R.mipmap.ic_launcher).into(holder.img)
        holder.delitem.setOnClickListener {
            mlist.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, mlist.size)
        }
        holder.itembind(m)

    }

    override fun getItemCount(): Int {
        return mlist.size
    }
}