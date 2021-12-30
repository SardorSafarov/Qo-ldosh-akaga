package com.example.app1.ui.home


import android.content.res.AssetManager

import android.os.Bundle
import android.util.Log.d
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app1.databinding.FragmentHomeBinding
import com.example.app1.entity.User
import com.example.app1.ui.UserAdapter
import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.poifs.filesystem.POIFSFileSystem
import java.io.InputStream
import org.apache.poi.hssf.usermodel.HSSFRow
import java.lang.Exception

import android.view.*


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    var list: MutableList<User> = mutableListOf()



    lateinit var adapter:UserAdapter

    private val binding get() = _binding!!



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        search()
        readExcelFileFromAssets()
        adapter=UserAdapter()
        binding.listrecyc.adapter=adapter
        binding.listrecyc.layoutManager=LinearLayoutManager(context)

    }




    private fun search() {



        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null)
                    searchDebtorMarket(query)
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null)
                    searchDebtorMarket(query)
                return true
            }

        })
    }

    private fun searchDebtorMarket(query: String) {
        var list1: MutableList<User> = mutableListOf()
    binding.searchB.setOnClickListener {
        binding.textHome.visibility=View.GONE
        list.forEach {
            if(it.ustun2.uppercase().contains(query.uppercase()))
                list1.add(it)
        }
        list1.forEach {
            d("sardor","${it.ustun2}")
        }
        adapter.setdata(list1)
    }

    }


    fun readExcelFileFromAssets() {
        try {
            val myInput: InputStream
            // initialize asset manager
            val assetManager: AssetManager = requireActivity().getAssets()
            //  open excel sheet
            myInput = assetManager.open("soqqa.xls")
            // Create a POI File System object
            val myFileSystem = POIFSFileSystem(myInput)
            // Create a workbook using the File System
            val myWorkBook = HSSFWorkbook(myFileSystem)
            // Get the first sheet from workbook
            val mySheet = myWorkBook.getSheetAt(0)
            // We now need something to iterate through the cells.
            val rowIter = mySheet.rowIterator()
            var rowno = 0
            //  textView.append("\n")
            while (rowIter.hasNext()) {
                //   Log.e(TAG, " row no $rowno")
                val myRow = rowIter.next() as HSSFRow
                if (rowno != 0) {
                    val cellIter = myRow.cellIterator()
                    var colno = 0
                    var ikkinch = ""
                    var uchinch = ""
                    var turtinch = ""
                    var beshinchi = ""
                    while (cellIter.hasNext()) {
                        val myCell = cellIter.next() as HSSFCell
                        when (colno) {
                            1 ->
                                ikkinch = myCell.toString()
                            2 ->
                                uchinch = myCell.toString()
                            3 ->
                                turtinch = myCell.toString()
                            4 ->
                                beshinchi = myCell.toString()
                        }

                        colno++
                        //  Log.e(TAG, " Index :" + myCell.columnIndex + " -- " + myCell.toString())
                    }
                    if (turtinch != "")
                        list.add(
                            User(
                                ustun1 = ikkinch,
                                ustun2 = uchinch,
                                ustun3 = turtinch,
                                ustun4 = beshinchi
                            )
                        )
                }
                rowno++
            }
        } catch (e: Exception) {
            // Log.e(TAG, "error $e")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}