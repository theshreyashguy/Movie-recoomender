package com.example.intern_process.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.intern_app.Movie
import com.example.intern_app.callingobj
import com.example.intern_process.R
import com.example.intern_process.databinding.FragmentHomeBinding
import com.example.intern_process.mainadapter
import com.example.intern_process.ui.db.AppDatabase
import com.example.intern_process.ui.db.Dataapplication1
import com.example.intern_process.ui.db.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.schedule

class HomeFragment : Fragment() {

    private var isLoading = true
    private var _binding: FragmentHomeBinding? = null
    private lateinit var badilist: List<Movie>


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        var mll: List<Movie>? = null
          val recyclerView: RecyclerView = root.findViewById(R.id.listofitems)

        CoroutineScope(Dispatchers.IO).launch {
            val result = callingobj.apiinterface.getData()
            val  result2 = callingobj.apiinterface1.getData()
            badilist = result2.body()!!.movie_list

            mll = result.body()?.movie_list
            var o = 0
            val dd = AppDatabase.getDatabase(requireContext())
            for( i in mll!!){

                val ss :Student? = i.let { Student(o,it.Cast,it.Director,it.Genres,it.IMDBID,it.Rating,it.Runtime,it.Summary,it.Title,it.Writers,it.Year) }
                ss?.let { dd.studentDao().insert(it) }
                o = o+1
            }


            Timer().schedule(0){
                for(i in result2.body()!!.movie_list){
                    badilist.plus((mll))
                }
            }


            Timer().schedule(0) {


                requireActivity().runOnUiThread{

                    recyclerView.adapter = mll?.let { mainadapter(it as ArrayList<Movie>)}
                    recyclerView.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
                    recyclerView.adapter!!.notifyDataSetChanged()
                    recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
                        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                            super.onScrolled(recyclerView, dx, dy)

                            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                            val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                            val ff = lastVisibleItemPosition
                            val totalItemCount = recyclerView.adapter?.itemCount

                            // Load more data if the user has reached the end of the list
                            if (lastVisibleItemPosition + 1 == totalItemCount && isLoading) {
                                isLoading = false
                                badilist?.let { recyclerView.adapter = mainadapter(it as ArrayList<Movie>) }
                                recyclerView.adapter!!.notifyDataSetChanged()

                            }
                        }


                    })
                }
            }

                }


    

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}