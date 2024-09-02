package com.learn.app.kotlins.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.learn.app.kotlins.database.ContactEntity
import com.learn.app.kotlins.databinding.FragmentAddContanctBinding
import com.learn.app.kotlins.di.viewmodels.DatabaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AddContanctFragment : DialogFragment() {
    @Inject
    lateinit var entity: ContactEntity
    private val viewModel: DatabaseViewModel by viewModels()
    private var contactd = 0;
    private var name = ""
    private var phone = ""
    private lateinit var binding: FragmentAddContanctBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddContanctBinding.inflate(layoutInflater)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            imgClose.setOnClickListener {
                dismiss()
            }
            btnSave.setOnClickListener {
                name = edtName.text.toString()
                phone = edtPhone.text.toString()
                if (name.isEmpty() && phone.isEmpty()) {
                    Snackbar.make(it, "Name and Phone can not be Empty", Snackbar.LENGTH_SHORT)
                        .show()
                } else {
                    entity.id = contactd
                    entity.name = name
                    entity.phone = phone
                    viewModel.saveContacts(entity)
                    edtName.setText("")
                    edtPhone.setText("")
                    dismiss()
                }
            }
        }
    }

}