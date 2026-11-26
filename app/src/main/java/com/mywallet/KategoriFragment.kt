package com.mywallet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mywallet.databinding.FragmentKategoriBinding
import java.util.UUID

// TODO: Rename parameter arguments, choose names that match
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [KategoriFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class KategoriFragment : Fragment() {

    private lateinit var binding: FragmentKategoriBinding
    private val arKategori = ArrayList<DcKategori>()
    private lateinit var adapterKategori: AdapterRecView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentKategoriBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val jenis = arrayOf("Income", "Expense")

        val adapterJenis = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            jenis
        )

        adapterJenis.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        binding.spJenis.adapter = adapterJenis
        tampilkanData()
        binding.btnSimpan.setOnClickListener {
            tambahData()
        }
    }

    private fun tampilkanData() {
        binding.rvKategori.layoutManager = LinearLayoutManager(requireContext())
        adapterKategori = AdapterRecView(arKategori)
        binding.rvKategori.adapter = adapterKategori
    }

    private fun tambahData() {
        val nama = binding.edtNamaKategori.text.toString()
        val jenis = binding.spJenis.selectedItem.toString()

        if (nama.isEmpty()) {
            Toast.makeText(
                requireContext(),
                "Nama kategori itu wajib diisi",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        val kategori = DcKategori(
            UUID.randomUUID().toString(),
            nama,
            jenis
        )

        arKategori.add(kategori)
        adapterKategori.notifyItemInserted(arKategori.size - 1)

        binding.edtNamaKategori.text.clear()

        Toast.makeText(
            requireContext(),
            "Kategori berhasil ditambahkan",
            Toast.LENGTH_SHORT
        ).show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment KategoriFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            KategoriFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}