package com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.fragment.navhost

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.R
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.fragment.blankfragment.BaseDataBindingFragment
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.databinding.FragmentNavhostNotificationBinding
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.util.Event
import com.smarttoolfactory.tutorial7_2bnv_viewpager2_complexarchitecture.viewmodel.AppbarViewModel


class NotificationHostFragment : BaseDataBindingFragment<FragmentNavhostNotificationBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_navhost_notification

    private val appbarViewModel by activityViewModels<AppbarViewModel>()

    private var navController: NavController? = null

    private val nestedNavHostFragmentId = R.id.nestedNotificationNavHostFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(nestedNavHostFragmentId) as? NavHostFragment
        navController = nestedNavHostFragment?.navController

    }


    override fun onResume() {
        super.onResume()

        // Set this navController as ViewModel's navController
        navController?.let {
            appbarViewModel.currentNavController.value = Event(it)
        }
    }


}