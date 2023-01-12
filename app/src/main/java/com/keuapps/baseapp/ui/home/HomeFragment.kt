package com.keuapps.baseapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.keuapps.baseapp.data.remote.model.Kisiler
import com.keuapps.baseapp.databinding.FragmentHomeBinding
import com.keuapps.baseapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment  : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : HomeFragmentViewModel
    var contactRecyclerAdapter : ContactRecyclerAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[HomeFragmentViewModel::class.java]
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactRecyclerAdapter = ContactRecyclerAdapter()
        binding.rvContacts.adapter = contactRecyclerAdapter
        binding.rvContacts.layoutManager = LinearLayoutManager(requireContext())


        binding.btnSave.setOnClickListener {
            saveData(binding.etName.text.toString(),binding.etNumber.text.toString())
        }
        binding.btnGetData.setOnClickListener {
            getDataFromApi()
        }
    }


    private fun saveData(name : String, phone : String ) {
        viewModel.addNewPerson(name,phone)

        viewModel.addNewContact.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    binding.pbLoading.visibility = View.GONE
                    Toast.makeText(requireContext(), "Kayıt Başarılı", Toast.LENGTH_SHORT).show()
                }
                is Resource.Error -> {
                    binding.pbLoading.visibility = View.GONE
                    Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    binding.pbLoading.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun getDataFromApi () {
        viewModel.getKisiler()
        viewModel.toDoList.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    binding.pbLoading.visibility = View.GONE
                    it.data?.let {
                       contactRecyclerAdapter!!.contacts= it.kisiler
                    }
                    binding.tvData.text = it.data!!.kisiler.last().kisi_ad
                }
                is Resource.Error -> {
                    binding.pbLoading.visibility = View.GONE
                    Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    binding.pbLoading.visibility = View.VISIBLE
                }
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}