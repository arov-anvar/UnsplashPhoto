package com.example.unsplashphoto.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class InfoBottomSheet : BottomSheetDialogFragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    companion object {
        fun newInstance(id: String): InfoBottomSheet {
            val fragment = InfoBottomSheet()
            val args = Bundle().apply {
                putString("photoId", id)
            }
            fragment.arguments = args
            return fragment
        }
    }

}