package com.bookstore.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.bookstore.BookDatabase.Book
import com.bookstore.BookDatabase.BookDb
import com.bookstore.BookDatabase.bookAdapter
import com.example.bookstore.databinding.FragmentMainpageBinding


class Mainpage : Fragment() {

    lateinit var binding: FragmentMainpageBinding
    private val adapter = bookAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainpageBinding.inflate(inflater)

        val db = BookDb.getBookDb(requireActivity())

        db.getBookDao().getAllBook().asLiveData().observe(viewLifecycleOwner){list->
            list.forEach{
                adapter.addBook(it)
            }
            binding.rcView.layoutManager = LinearLayoutManager(activity)
            binding.rcView.adapter = adapter
        }

        binding.sortAsc.setOnClickListener {
            db.getBookDao().getAllBookInAscOrder().asLiveData().observe(viewLifecycleOwner){list->
                adapter.clearList()
                list.forEach{
                    adapter.addBook(it)
//                    Log.d("baha", "${it.cost}")
                }

                binding.rcView.layoutManager = LinearLayoutManager(activity)
                binding.rcView.adapter = adapter
            }
        }

        binding.sortDesc.setOnClickListener {
            db.getBookDao().getAllBookInDescOrder().asLiveData().observe(viewLifecycleOwner){list->
                adapter.clearList()
                list.forEach{
                    adapter.addBook(it)
                }
                binding.rcView.layoutManager = LinearLayoutManager(activity)
                binding.rcView.adapter = adapter
            }
        }

        binding.search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null){
                    val filteredBooks = ArrayList<Book>()
                    db.getBookDao().getAllBook().asLiveData().observe(viewLifecycleOwner){list->
                        list.forEach{
                            if(it.title.lowercase().contains(newText)){
                                filteredBooks.add(it)
                            }
                        }
                        if(filteredBooks.size == 0){
                            adapter.clearList()
                        }
                        else{
                            adapter.clearList()
                            filteredBooks.forEach{its->
                                adapter.addBook(its)
                            }
                            binding.rcView.layoutManager = LinearLayoutManager(activity)
                            binding.rcView.adapter = adapter
                        }
                    }
                }
                return true
            }
        })


        return binding.root
    }


    companion object {
        @JvmStatic
        fun newInstance() = Mainpage()
    }
}